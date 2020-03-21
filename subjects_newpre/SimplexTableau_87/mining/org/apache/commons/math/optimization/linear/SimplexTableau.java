/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.math.optimization.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.util.MathUtils;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;
/**
 * A tableau for use in the Simplex method.
 * 
 * <p>
 * Example:
 * <pre>
 *   W |  Z |  x1 |  x2 |  x- | s1 |  s2 |  a1 |  RHS
 * ---------------------------------------------------
 *  -1    0    0     0     0     0     0     1     0   &lt;= phase 1 objective
 *   0    1   -15   -10    0     0     0     0     0   &lt;= phase 2 objective
 *   0    0    1     0     0     1     0     0     2   &lt;= constraint 1
 *   0    0    0     1     0     0     1     0     3   &lt;= constraint 2
 *   0    0    1     1     0     0     0     1     4   &lt;= constraint 3
 * </pre>
 * W: Phase 1 objective function</br>
 * Z: Phase 2 objective function</br>
 * x1 &amp; x2: Decision variables</br>
 * x-: Extra decision variable to allow for negative values</br>
 * s1 &amp; s2: Slack/Surplus variables</br>
 * a1: Artificial variable</br>
 * RHS: Right hand side</br>
 * </p>
 * @version $Revision$ $Date$
 * @since 2.0
 */
class SimplexTableau implements Serializable {

    /** Serializable version identifier. */
    private static final long serialVersionUID = -1369660067587938365L;

    /** Linear objective function. */
    private final LinearObjectiveFunction f;

    /** Linear constraints. */
    private final Collection<LinearConstraint> constraints;

    /** Whether to restrict the variables to non-negative values. */
    private final boolean restrictToNonNegative;

    /** Simple tableau. */
    private transient RealMatrix tableau;

    /** Number of decision variables. */
    private final int numDecisionVariables;

    /** Number of slack variables. */
    private final int numSlackVariables;

    /** Number of artificial variables. */
    private int numArtificialVariables;

    /** Amount of error to accept in floating point comparisons. */ 
    private final double epsilon;
    
    /**
     * Build a tableau for a linear problem.
     * @param f linear objective function
     * @param constraints linear constraints
     * @param goalType type of optimization goal: either {@link GoalType#MAXIMIZE}
     * or {@link GoalType#MINIMIZE}
     * @param restrictToNonNegative whether to restrict the variables to non-negative values
     * @param epsilon amount of error to accept in floating point comparisons
     */
    @EpaAction(name="SimplexTableau1")
    SimplexTableau(final LinearObjectiveFunction f,
                   final Collection<LinearConstraint> constraints,
                   final GoalType goalType, final boolean restrictToNonNegative,
                   final double epsilon) {
        this.f                      = f;
        this.constraints            = constraints;
        this.restrictToNonNegative  = restrictToNonNegative;
        this.epsilon                = epsilon;
        this.numDecisionVariables   = getNumVariables_0() + (restrictToNonNegative ? 0 : 1);
        this.numSlackVariables      = getConstraintTypeCounts(Relationship.LEQ) +
                                      getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) +
                                      getConstraintTypeCounts(Relationship.GEQ);
        this.tableau = new RealMatrixImpl(createTableau(goalType == GoalType.MAXIMIZE));
        initialize();
    }

    /**
     * Create the tableau by itself.
     * @param maximize if true, goal is to maximize the objective function
     * @return created tableau
     */
    private double[][] createTableau(final boolean maximize) {

        // create a matrix of the correct size
        List<LinearConstraint> constraints = getNormalizedConstraints_0();
        int width = numDecisionVariables + numSlackVariables +
        numArtificialVariables + getNumObjectiveFunctions_0() + 1; // + 1 is for RHS
        int height = constraints.size() + getNumObjectiveFunctions_0();
        double[][] matrix = new double[height][width];

        // initialize the objective function rows
        if (getNumObjectiveFunctions_0() == 2) {
            matrix[0][0] = -1;
        }
        int zIndex = (getNumObjectiveFunctions_0() == 1) ? 0 : 1;
        matrix[zIndex][zIndex] = maximize ? 1 : -1;
        RealVector objectiveCoefficients =
            maximize ? f.getCoefficients().mapMultiply(-1) : f.getCoefficients();
            copyArray(objectiveCoefficients.getData(), matrix[zIndex], getNumObjectiveFunctions_0());
            matrix[zIndex][width - 1] =
                maximize ? f.getConstantTerm() : -1 * f.getConstantTerm();

                if (!restrictToNonNegative) {
                    matrix[zIndex][getSlackVariableOffset() - 1] =
                        getInvertedCoeffiecientSum(objectiveCoefficients);
                }

                // initialize the constraint rows
                int slackVar = 0;
                int artificialVar = 0;
                for (int i = 0; i < constraints.size(); i++) {
                    LinearConstraint constraint = constraints.get(i);
                    int row = getNumObjectiveFunctions_0() + i;

                    // decision variable coefficients
                    copyArray(constraint.getCoefficients().getData(), matrix[row], 1);

                    // x-
                    if (!restrictToNonNegative) {
                        matrix[row][getSlackVariableOffset() - 1] =
                            getInvertedCoeffiecientSum(constraint.getCoefficients());
                    }

                    // RHS
                    matrix[row][width - 1] = constraint.getValue();

                    // slack variables
                    if (constraint.getRelationship() == Relationship.LEQ) {
                        matrix[row][getSlackVariableOffset() + slackVar++] = 1;  // slack
                    } else if (constraint.getRelationship() == Relationship.GEQ) {
                        matrix[row][getSlackVariableOffset() + slackVar++] = -1; // excess
                    }

                    // artificial variables
                    if ((constraint.getRelationship() == Relationship.EQ) ||
                        (constraint.getRelationship() == Relationship.GEQ)) {
                        matrix[0][getArtificialVariableOffset_0() + artificialVar] = 1;
                        matrix[row][getArtificialVariableOffset_0() + artificialVar++] = 1;
                    }
                }

                return matrix;
    }

    /** Get the number of variables.
     * @return number of variables
     */
    @EpaAction(name="getNumVariables")
    public int getNumVariables() {
        return getNumVariables_0();
    }

    private int getNumVariables_0() {
        return f.getCoefficients().getDimension();
    }

    /**
     * Get new versions of the constraints which have positive right hand sides.
     * @return new versions of the constraints
     */
    @EpaAction(name="getNormalizedConstraints")
    public List<LinearConstraint> getNormalizedConstraints() {
        return getNormalizedConstraints_0();
    }

    private List<LinearConstraint> getNormalizedConstraints_0() {
        List<LinearConstraint> normalized = new ArrayList<LinearConstraint>();
        for (LinearConstraint constraint : constraints) {
            normalized.add(normalize(constraint));
        }
        return normalized;
    }

    /**
     * Get a new equation equivalent to this one with a positive right hand side.
     * @param constraint reference constraint
     * @return new equation
     */
    private LinearConstraint normalize(final LinearConstraint constraint) {
        if (constraint.getValue() < 0) {
            return new LinearConstraint(constraint.getCoefficients().mapMultiply(-1),
                                        constraint.getRelationship().oppositeRelationship(),
                                        -1 * constraint.getValue());
        }
        return new LinearConstraint(constraint.getCoefficients(), 
                                    constraint.getRelationship(), constraint.getValue());
    }

    /**
     * Get the number of objective functions in this tableau.
     * @return 2 for Phase 1.  1 for Phase 2.
     */
    @EpaAction(name="getNumObjectiveFunctions")
    public final int getNumObjectiveFunctions() {
        return getNumObjectiveFunctions_0();
    }

    private final int getNumObjectiveFunctions_0() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    /**
     * Get a count of constraints corresponding to a specified relationship.
     * @param relationship relationship to count
     * @return number of constraint with the specified relationship
     */
    private int getConstraintTypeCounts(final Relationship relationship) {
        int count = 0;
        for (final LinearConstraint constraint : constraints) {
            if (constraint.getRelationship() == relationship) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Puts the tableau in proper form by zeroing out the artificial variables
     * in the objective function via elementary row operations.
     */
    private void initialize() {
        for (int artificialVar = 0; artificialVar < numArtificialVariables; artificialVar++) {
            int row = getBasicRow_0(getArtificialVariableOffset_0() + artificialVar);
            subtractRow_0(0, row, 1.0);
        }
    }

    /**
     * Get the -1 times the sum of all coefficients in the given array.
     * @param coefficients coefficients to sum
     * @return the -1 times the sum of all coefficients in the given array.
     */
    private static double getInvertedCoeffiecientSum(final RealVector coefficients) {
        double sum = 0;
        for (double coefficient : coefficients.getData()) {
            sum -= coefficient;
        }
        return sum;
    }

    /**
     * Checks whether the given column is basic.
     * @param col index of the column to check
     * @return the row that the variable is basic in.  null if the column is not basic
     */
    @EpaAction(name="getBasicRow")
    public Integer getBasicRow(final int col) {
        return getBasicRow_0(col);
    }

    private Integer getBasicRow_0(final int col) {
        Integer row = null;
        for (int i = getNumObjectiveFunctions_0(); i < getHeight_0(); i++) {
            if (MathUtils.equals(getEntry_0(i, col), 1.0, epsilon) && (row == null)) {
                row = i;
            } else if (!MathUtils.equals(getEntry_0(i, col), 0.0, epsilon)) {
                return null;
            }
        }
        return row;
    }

    /**
     * Removes the phase 1 objective function and artificial variables from this tableau.
     */
    @EpaAction(name="discardArtificialVariables")
    public void discardArtificialVariables() {
        if (numArtificialVariables == 0) {
            return;
        }
        int width = getWidth() - numArtificialVariables - 1;
        int height = getHeight() - 1;
        double[][] matrix = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 1; j++) {
                matrix[i][j] = getEntry(i + 1, j + 1);
            }
            matrix[i][width - 1] = getEntry(i + 1, getRhsOffset());
        }
        this.tableau = new RealMatrixImpl(matrix);
        this.numArtificialVariables = 0;
    }


    /**
     * @param src the source array
     * @param dest the destination array
     * @param destPos the destination position
     */
    private void copyArray(final double[] src, final double[] dest,
                           final int destPos) {
        System.arraycopy(src, 0, dest, getNumObjectiveFunctions_0(), src.length);
    }

    /**
     * Get the current solution.
     * <p>
     * {link #solve} should be called first for this to be the optimal solution.
     * </p>
     * @return current solution
     */
    @EpaAction(name="getSolution")
    public RealPointValuePair getSolution() {
        double[] coefficients = new double[getOriginalNumDecisionVariables()];
        Integer basicRow =
            getBasicRow(getNumObjectiveFunctions() + getOriginalNumDecisionVariables());
        double mostNegative = basicRow == null ? 0 : getEntry(basicRow, getRhsOffset());
        Set<Integer> basicRows = new HashSet<Integer>();
        for (int i = 0; i < coefficients.length; i++) {
            basicRow = getBasicRow(getNumObjectiveFunctions() + i);
            if (basicRows.contains(basicRow)) {
                // if multiple variables can take a given value 
                // then we choose the first and set the rest equal to 0
                coefficients[i] = 0;
            } else {
                basicRows.add(basicRow);
                coefficients[i] =
                    (basicRow == null ? 0 : getEntry(basicRow, getRhsOffset())) -
                    (restrictToNonNegative ? 0 : mostNegative);
            }
        }
        return new RealPointValuePair(coefficients, f.getValue(coefficients));
    }

    /**
     * Subtracts a multiple of one row from another.
     * <p>
     * After application of this operation, the following will hold:
     *   minuendRow = minuendRow - multiple * subtrahendRow
     * </p>
     * @param dividendRow index of the row
     * @param divisor value of the divisor
     */
    @EpaAction(name="divideRow")
    public void divideRow(final int dividendRow, final double divisor) {
        for (int j = 0; j < getWidth(); j++) {
            tableau.setEntry(dividendRow, j, tableau.getEntry(dividendRow, j) / divisor);
        }
    }

    /**
     * Subtracts a multiple of one row from another.
     * <p>
     * After application of this operation, the following will hold:
     *   minuendRow = minuendRow - multiple * subtrahendRow
     * </p>
     * @param minuendRow row index
     * @param subtrahendRow row index
     * @param multiple multiplication factor
     */
    @EpaAction(name="subtractRow")
    public void subtractRow(final int minuendRow, final int subtrahendRow,
                               final double multiple) {
        subtractRow_0(minuendRow, subtrahendRow, multiple);
    }

    private void subtractRow_0(final int minuendRow, final int subtrahendRow,
                            final double multiple) {
        for (int j = 0; j < getWidth_0(); j++) {
            tableau.setEntry(minuendRow, j, tableau.getEntry(minuendRow, j) -
                    multiple * tableau.getEntry(subtrahendRow, j));
        }
    }

    /**
     * Get the width of the tableau.
     * @return width of the tableau
     */
    @EpaAction(name="getWidth")
    public final int getWidth() {
        return getWidth_0();
    }

    public final int getWidth_0() {
        return tableau.getColumnDimension();
    }

    /**
     * Get the height of the tableau.
     * @return height of the tableau
     */
    @EpaAction(name="getHeight")
    public final int getHeight() {
        return getHeight_0();
    }

    private final int getHeight_0() {
        return tableau.getRowDimension();
    }

    /** Get an entry of the tableau.
     * @param row row index
     * @param column column index
     * @return entry at (row, column)
     */
    @EpaAction(name="getEntry")
    public final double getEntry(final int row, final int column) {
        return getEntry_0(row, column);
    }

    private final double getEntry_0(final int row, final int column) {
        return tableau.getEntry(row, column);
    }

    /** Set an entry of the tableau.
     * @param row row index
     * @param column column index
     * @param value for the entry
     */
    private final void setEntry(final int row, final int column,
                                  final double value) {
        tableau.setEntry(row, column, value);
    }

    /**
     * Get the offset of the first slack variable.
     * @return offset of the first slack variable
     */
    private final int getSlackVariableOffset() {
        return getNumObjectiveFunctions_0() + numDecisionVariables;
    }

    /**
     * Get the offset of the first artificial variable.
     * @return offset of the first artificial variable
     */
    @EpaAction(name="getArtificialVariableOffset")
    public final int getArtificialVariableOffset() {
        return getArtificialVariableOffset_0();
    }

    private final int getArtificialVariableOffset_0() {
        return getNumObjectiveFunctions_0() + numDecisionVariables + numSlackVariables;
    }

    /**
     * Get the offset of the right hand side.
     * @return offset of the right hand side
     */
    @EpaAction(name="getRhsOffset")
    public final int getRhsOffset() {
        return getWidth() - 1;
    }

    /**
     * Get the number of decision variables.
     * <p>
     * If variables are not restricted to positive values, this will include 1
     * extra decision variable to represent the absolute value of the most
     * negative variable.
     * </p>
     * @return number of decision variables
     * @see #getOriginalNumDecisionVariables()
     */
    private final int getNumDecisionVariables() {
        return numDecisionVariables;
    }

    /**
     * Get the original number of decision variables.
     * @return original number of decision variables
     * @see #getNumDecisionVariables()
     */
    private final int getOriginalNumDecisionVariables() {
        return restrictToNonNegative ? numDecisionVariables : numDecisionVariables - 1;
    }

    /**
     * Get the number of slack variables.
     * @return number of slack variables
     */
    private final int getNumSlackVariables() {
        return numSlackVariables;
    }

    /**
     * Get the number of artificial variables.
     * @return number of artificial variables
     */
    @EpaAction(name="getNumArtificialVariables")
    public final int getNumArtificialVariables() {
        return numArtificialVariables;
    }

    /**
     * Get the tableau data.
     * @return tableau data
     */
    private final double[][] getData() {
        return tableau.getData();
    }

    /** {@inheritDoc} */
    @Override
    @EpaAction(name="equals")
    public boolean equals(Object other) {

      if (this == other) { 
        return true;
      }

      if (other == null) {
        return false;
      }

      try {

          SimplexTableau rhs = (SimplexTableau) other;
          return (restrictToNonNegative  == rhs.restrictToNonNegative) &&
                 (numDecisionVariables   == rhs.numDecisionVariables) &&
                 (numSlackVariables      == rhs.numSlackVariables) &&
                 (numArtificialVariables == rhs.numArtificialVariables) &&
                 (epsilon                == rhs.epsilon) &&
                 f.equals(rhs.f) &&
                 constraints.equals(rhs.constraints) &&
                 tableau.equals(rhs.tableau);

      } catch (ClassCastException ex) {
          // ignore exception
          return false;
      }

    }
    
    /** {@inheritDoc} */
    @Override
    @EpaAction(name="hashCode")
    public int hashCode() {
        return Boolean.valueOf(restrictToNonNegative).hashCode() ^
               numDecisionVariables ^
               numSlackVariables ^
               numArtificialVariables ^
               Double.valueOf(epsilon).hashCode() ^
               f.hashCode() ^
               constraints.hashCode() ^
               tableau.hashCode();
    }

    /** Serialize the instance.
     * @param oos stream where object should be written
     * @throws IOException if object cannot be written to stream
     */
    private void writeObject(ObjectOutputStream oos)
        throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(tableau, oos);
    }

    /** Deserialize the instance.
     * @param ois stream from which the object should be read
     * @throws ClassNotFoundException if a class in the stream cannot be found
     * @throws IOException if object cannot be read from the stream
     */
    private void readObject(ObjectInputStream ois)
      throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", ois);
    }

    /*
    EPA Methods Preconditions
     */

    @EpaActionPrecondition(name = "getSolution")
    private boolean isgetSolutionEnabled() {
        return tableau != null && f != null;
    }

    @EpaActionPrecondition(name = "getNumVariables")
    private boolean isgetNumVariablesEnabled() {
        return f != null;
    }

    @EpaActionPrecondition(name = "equals")
    private boolean isequalsEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "hashCode")
    private boolean ishashCodeEnabled() {
        return f != null && constraints != null && tableau != null;
    }

    @EpaActionPrecondition(name = "getNumObjectiveFunctions")
    private boolean isgetNumObjectiveFunctionsEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "getBasicRow")
    private boolean isgetBasicRowEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "discardArtificialVariables")
    private boolean isdiscardArtificialVariablesEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getNormalizedConstraints")
    private boolean isgetNormalizedConstraintsEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "divideRow")
    private boolean isdivideRowEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "subtractRow")
    private boolean issubtractRowEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getWidth")
    private boolean isgetWidthEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getHeight")
    private boolean isgetHeightEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getEntry")
    private boolean isgetEntryEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getArtificialVariableOffset")
    private boolean isgetArtificialVariableOffsetEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "getRhsOffset")
    private boolean isgetRhsOffsetEnabled() {
        return tableau != null;
    }

    @EpaActionPrecondition(name = "getNumArtificialVariables")
    private boolean isgetNumArtificialVariablesEnabled() {
        return true;
    }
}
