// This is a mutant program.
// Author : ysma

package com.example.resultset;


public class JDBCResultSet implements com.example.resultset.MockResultSet
{

    public  boolean next()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = next0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean next0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        rootWarning = null;
        return navigator.next();
    }

    public  void close()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.close0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void close0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (navigator == null) {
            return;
        }
        navigator.close();
        navigator = null;
        if (autoClose && statement != null) {
            statement.close();
        }
    }

    public  boolean wasNull()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = this.wasNull0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean wasNull0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        return wasNullValue;
    }

    public  int getInt( int columnIndex )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            int rv = getInt0( columnIndex );
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  int getInt0( int columnIndex )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        java.lang.Object o = getColumnInType( columnIndex, MockType.SQL_INTEGER );
        return o == null ? 0 : ((java.lang.Number) o).intValue();
    }

    public  int getInt( java.lang.String columnLabel )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            int rv = this.getInt0( columnLabel );
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  int getInt0( java.lang.String columnLabel )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        return getInt0( findColumn( columnLabel ) );
    }

    private  int findColumn( final java.lang.String columnLabel )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            int rv = this.findColumn0( columnLabel );
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  int findColumn0( final java.lang.String columnLabel )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (columnLabel == null) {
            throw MockUtil.nullArgument();
        }
        int columnIndex;
        if (columnMap != null) {
            columnIndex = columnMap.get( columnLabel, -1 );
            if (columnIndex > -1) {
                return columnIndex;
            }
        }
        final java.lang.String[] colLabels = resultMetaData.columnLabels;
        columnIndex = -1;
        for (int i = 0; i < columnCount; i++) {
            if (columnLabel.equalsIgnoreCase( colLabels[i] )) {
                columnIndex = i;
                break;
            }
        }
        final com.example.resultset.MockColumnBase[] columns = resultMetaData.columns;
        if (columnIndex < 0) {
            for (int i = 0; i < columnCount; i++) {
                if (columnLabel.equalsIgnoreCase( columns[i].getNameString() )) {
                    columnIndex = i;
                    break;
                }
            }
        }
        if (columnIndex < 0) {
            int position = columnLabel.indexOf( '.' );
            if (position < 0) {
                throw MockUtil.sqlException( MockErrorCode.JDBC_COLUMN_NOT_FOUND, columnLabel );
            }
            for (int i = 0; i < columnCount; i++) {
                final java.lang.String tabName = columns[i].getTableNameString();
                if (tabName == null || tabName.length() == 0) {
                    continue;
                }
                final java.lang.String colName = columns[i].getNameString();
                if (columnLabel.equalsIgnoreCase( tabName + '.' + colName )) {
                    columnIndex = i;
                    break;
                }
                final java.lang.String schemName = columns[i].getSchemaNameString();
                if (schemName == null || schemName.length() == 0) {
                    continue;
                }
                java.lang.String match = (new java.lang.StringBuffer( schemName )).append( '.' ).append( tabName ).append( '.' ).append( colName ).toString();
                if (columnLabel.equalsIgnoreCase( match )) {
                    columnIndex = i;
                    break;
                }
            }
        }
        if (columnIndex < 0) {
            throw MockUtil.sqlException( MockErrorCode.JDBC_COLUMN_NOT_FOUND, columnLabel );
        }
        columnIndex++;
        if (columnMap == null) {
            columnMap = new com.example.resultset.MockIntValueHashMap();
        }
        columnMap.put( columnLabel, columnIndex );
        return columnIndex;
    }

    public  boolean isBeforeFirst()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = isBeforeFirst0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean isBeforeFirst0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (isOnInsertRow) {
            return false;
        }
        return navigator.isBeforeFirst();
    }

    public  boolean isAfterLast()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = isAfterLast0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean isAfterLast0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (isOnInsertRow) {
            return false;
        }
        return navigator.isAfterLast();
    }

    public  boolean isFirst()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = isFirst0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean isFirst0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (isOnInsertRow) {
            return false;
        }
        return navigator.isFirst();
    }

    public  boolean isLast()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = isLast0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean isLast0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (isOnInsertRow) {
            return false;
        }
        return navigator.isLast();
    }

    public  void beforeFirst()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.beforeFirst0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void beforeFirst0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        navigator.beforeFirst();
    }

    public  void afterLast()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.afterLast0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void afterLast0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        navigator.afterLast();
    }

    public  boolean first()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = this.first0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean first0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        return navigator.first();
    }

    public  boolean last()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = last0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean last0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        return navigator.last();
    }

    public  int getRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            int rv = getRow0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  int getRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (navigator.isAfterLast()) {
            return 0;
        }
        return navigator.getRowNumber() + 1;
    }

    public  boolean absolute( int row )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = this.absolute0( row );
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean absolute0( int row )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        if (row > 0) {
            row--;
        } else {
            if (row == 0) {
                return navigator.beforeFirst();
            }
        }
        return navigator.absolute( row );
    }

    public  boolean relative( int rows )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = relative0( rows );
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean relative0( int rows )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        return navigator.relative( rows );
    }

    public  boolean previous()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            boolean rv = previous0();
            return rv;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean previous0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkNotForwardOnly();
        if (isOnInsertRow || isRowUpdated) {
            throw MockUtil.sqlExceptionSQL( MockErrorCode.X_24513 );
        }
        rootWarning = null;
        return navigator.previous();
    }

    public  void updateInt( int columnIndex, int x )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.updateInt0( columnIndex, x );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void updateInt0( int columnIndex, int x )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        startUpdate( columnIndex );
        preparedStatement.setIntParameter( columnIndex, x );
    }

    public  void updateInt( java.lang.String columnLabel, int x )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.updateInt0( columnLabel, x );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void updateInt0( java.lang.String columnLabel, int x )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        updateInt( findColumn( columnLabel ), x );
    }

    public  void insertRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            insertRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void insertRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        performInsert();
    }

    public  void updateRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.updateRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void updateRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        performUpdate();
    }

    public  void deleteRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.deleteRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void deleteRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        performDelete();
    }

    public  void refreshRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.refreshRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void refreshRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        clearUpdates();
    }

    public  void cancelRowUpdates()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.cancelRowUpdates0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void cancelRowUpdates0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        clearUpdates();
    }

    public  void moveToInsertRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            moveToInsertRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void moveToInsertRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        startInsert();
    }

    public  void moveToCurrentRow()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        try {
            this.moveToCurrentRow0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void moveToCurrentRow0()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        endInsert();
    }

    private com.example.resultset.MockRowSetNavigator navigator;

    private com.example.resultset.MockResultMetaData resultMetaData;

    private int columnCount;

    private boolean wasNullValue;

    private com.example.resultset.MockResultSetMetaData resultSetMetaData;

    private com.example.resultset.MockJDBCConnection connection;

    private com.example.resultset.MockIntValueHashMap columnMap;

    private com.example.resultset.MockSQLWarning rootWarning;

    private com.example.resultset.MockResult result;

    private com.example.resultset.MockJDBCStatementBase statement;

    private com.example.resultset.MockSessionInterface session;

    private boolean isScrollable;

    private boolean isUpdatable;

    private boolean isInsertable;

    private int rsProperties;

    private boolean autoClose;

    public static final int FETCH_FORWARD = 1000;

    public static final int FETCH_REVERSE = 1001;

    public static final int FETCH_UNKNOWN = 1002;

    public static final int TYPE_FORWARD_ONLY = 1003;

    public static final int TYPE_SCROLL_INSENSITIVE = 1004;

    public static final int TYPE_SCROLL_SENSITIVE = 1005;

    public static final int CONCUR_READ_ONLY = 1007;

    public static final int CONCUR_UPDATABLE = 1008;

    public static final int HOLD_CURSORS_OVER_COMMIT = 1;

    public static final int CLOSE_CURSORS_AT_COMMIT = 2;

    private  java.lang.Object[] getCurrent()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        final com.example.resultset.MockRowSetNavigator lnavigator = this.navigator;
        if (lnavigator == null) {
            throw MockUtil.sqlException( MockErrorCode.X_24501 );
        } else {
            if (lnavigator.isEmpty()) {
                throw MockUtil.sqlException( MockErrorCode.X_24504, MockErrorCode.M_RS_EMPTY );
            } else {
                if (lnavigator.isBeforeFirst()) {
                    throw MockUtil.sqlException( MockErrorCode.X_24504, MockErrorCode.M_RS_BEFORE_FIRST );
                } else {
                    if (lnavigator.isAfterLast()) {
                        throw MockUtil.sqlException( MockErrorCode.X_24504, MockErrorCode.M_RS_AFTER_LAST );
                    }
                }
            }
        }
        java.lang.Object[] data = lnavigator.getCurrent();
        if (data == null) {
            throw MockUtil.sqlException( MockErrorCode.X_24501 );
        }
        return data;
    }

    private  void checkClosed()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (navigator == null) {
            throw MockUtil.sqlException( MockErrorCode.X_24501 );
        }
    }

    private  void checkColumn( int columnIndex )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (navigator == null) {
            throw MockUtil.sqlException( MockErrorCode.X_24501 );
        }
        if (columnIndex < 1 || columnIndex > columnCount) {
            throw MockUtil.sqlException( MockErrorCode.JDBC_COLUMN_NOT_FOUND, String.valueOf( columnIndex ) );
        }
    }

    private  boolean trackNull( java.lang.Object o )
    {
        return wasNullValue = o == null;
    }

    private  java.lang.Object getColumnInType( int columnIndex, com.example.resultset.MockType targetType )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        java.lang.Object[] rowData = getCurrent();
        com.example.resultset.MockType sourceType;
        java.lang.Object value;
        checkColumn( columnIndex );
        sourceType = resultMetaData.columnTypes[--columnIndex];
        value = rowData[columnIndex];
        if (trackNull( value )) {
            return null;
        }
        if (sourceType.typeCode != targetType.typeCode) {
            try {
                value = targetType.convertToTypeJDBC( session, value, sourceType );
            } catch ( java.lang.Exception e ) {
                java.lang.String stringValue = value instanceof java.lang.Number || value instanceof java.lang.String ? value.toString() : "instance of " + value.getClass().getName();
                java.lang.String msg = "from SQL type " + sourceType.getNameString() + " to " + targetType.getJDBCClassName() + ", value: " + stringValue;
                MockUtil.throwError( MockError.error( MockErrorCode.X_42561, msg ) );
            }
        }
        return value;
    }

    private  void checkNotForwardOnly()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (!isScrollable) {
            throw MockUtil.notSupported();
        }
    }

    private com.example.resultset.MockJDBCPreparedStatement preparedStatement;

    private boolean isRowUpdated;

    private boolean isOnInsertRow;

    private int currentUpdateRowNumber;

    private  void checkUpdatable()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        if (!isUpdatable) {
            throw MockUtil.notUpdatableColumn();
        }
    }

    private  void checkUpdatable( int columnIndex )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkClosed();
        checkColumn( columnIndex );
        if (!isUpdatable) {
            throw MockUtil.notUpdatableColumn();
        }
        if (resultMetaData.colIndexes[--columnIndex] == -1) {
            throw MockUtil.notUpdatableColumn();
        }
        if (!resultMetaData.columns[columnIndex].isWriteable()) {
            throw MockUtil.notUpdatableColumn();
        }
    }

    private  void startUpdate( int columnIndex )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable( columnIndex );
        if (currentUpdateRowNumber != navigator.getRowNumber()) {
            preparedStatement.clearParameters();
        }
        currentUpdateRowNumber = navigator.getRowNumber();
        isRowUpdated = true;
    }

    private  void clearUpdates()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable();
        preparedStatement.clearParameters();
        isRowUpdated = false;
    }

    private  void startInsert()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable();
        isOnInsertRow = true;
    }

    private  void endInsert()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable();
        preparedStatement.clearParameters();
        isOnInsertRow = false;
    }

    private  void performUpdate()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        preparedStatement.parameterValues[columnCount] = getCurrent()[columnCount];
        for (int i = 0; i < columnCount; i++) {
            boolean set = preparedStatement.parameterSet[i] || preparedStatement.parameterStream[i];
            preparedStatement.resultOut.metaData.columnTypes[i] = set ? preparedStatement.parameterTypes[i] : MockType.SQL_ALL_TYPES;
        }
        preparedStatement.resultOut.setActionType( MockResultConstants.UPDATE_CURSOR );
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();
        rootWarning = preparedStatement.getWarnings();
        preparedStatement.clearWarnings();
        isRowUpdated = false;
    }

    private  void performInsert()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable();
        for (int i = 0; i < columnCount; i++) {
            boolean set = preparedStatement.parameterSet[i] || preparedStatement.parameterStream[i];
            if (!set) {
                throw MockUtil.sqlException( MockErrorCode.X_24515 );
            }
            preparedStatement.resultOut.metaData.columnTypes[i] = preparedStatement.parameterTypes[i];
        }
        preparedStatement.resultOut.setActionType( MockResultConstants.INSERT_CURSOR );
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();
        rootWarning = preparedStatement.getWarnings();
        preparedStatement.clearWarnings();
    }

    private  void performDelete()
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        checkUpdatable();
        preparedStatement.parameterValues[columnCount] = getCurrent()[columnCount];
        preparedStatement.resultOut.metaData.columnTypes[columnCount] = resultMetaData.columnTypes[columnCount];
        preparedStatement.resultOut.setActionType( MockResultConstants.DELETE_CURSOR );
        preparedStatement.fetchResult();
        preparedStatement.clearParameters();
        rootWarning = preparedStatement.getWarnings();
        preparedStatement.clearWarnings();
    }

    private JDBCResultSet( com.example.resultset.MockJDBCConnection conn, com.example.resultset.MockJDBCStatementBase s, com.example.resultset.MockResult r, com.example.resultset.MockResultMetaData metaData )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        this.session = conn.sessionProxy;
        this.statement = s;
        this.result = r;
        this.connection = conn;
        rsProperties = r.rsProperties;
        navigator = r.getNavigator();
        resultMetaData = metaData;
        columnCount = resultMetaData.getColumnCount();
        isScrollable = MockResultProperties.isScrollable( rsProperties );
        if (MockResultProperties.isUpdatable( rsProperties )) {
            isUpdatable = true;
            isInsertable = true;
            for (int i = 0; i < metaData.colIndexes.length; i++) {
                if (metaData.colIndexes[i] < 0) {
                    isInsertable = false;
                    break;
                }
            }
            preparedStatement = new com.example.resultset.MockJDBCPreparedStatement( s.connection, result );
        }
    }

    private JDBCResultSet( com.example.resultset.MockJDBCConnection conn, com.example.resultset.MockResult r, com.example.resultset.MockResultMetaData metaData )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        this.session = conn.sessionProxy;
        this.result = r;
        this.connection = conn;
        rsProperties = 0;
        navigator = r.getNavigator();
        resultMetaData = metaData;
        columnCount = resultMetaData.getColumnCount();
    }

    public static final java.lang.String COLUMN_LABEL_0 = "columnLabel0";

    public static final java.lang.String COLUMN_LABEL_1 = "columnLabel1";

    public static final java.lang.String COLUMN_LABEL_2 = "columnLabel2";

    public static  com.example.resultset.JDBCResultSet buildNewMJDBCResultSet( int columnsCount, int rows )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (columnsCount < 1) {
            throw new java.lang.IllegalArgumentException( "column labels has to be greater than zero" );
        }
        if (columnsCount > 5) {
            throw new java.lang.IllegalArgumentException( "Too many columns" );
        }
        if (rows < 0) {
            throw new java.lang.IllegalArgumentException( "Invalid row number" );
        }
        java.lang.String[] columnLabels = new java.lang.String[columnsCount];
        for (int i = 0; i < columnsCount; i++) {
            columnLabels[i] = "columnLabel" + i;
        }
        int[][] table = new int[rows][];
        for (int i = 0; i < rows; i++) {
            table[i] = new int[columnsCount];
        }
        return buildNewJDBCResultSet0( columnLabels, table );
    }

    private JDBCResultSet( com.example.resultset.MockSessionInterface session, com.example.resultset.MockJDBCStatementBase s, com.example.resultset.MockResult r, com.example.resultset.MockResultMetaData metaData, com.example.resultset.MockJDBCConnection conn )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        this.session = session;
        this.statement = s;
        this.result = r;
        this.connection = conn;
        rsProperties = r.rsProperties;
        navigator = r.getNavigator();
        resultMetaData = metaData;
        r.metaData = metaData;
        columnCount = resultMetaData.getColumnCount();
        isScrollable = MockResultProperties.isScrollable( rsProperties );
        if (MockResultProperties.isUpdatable( rsProperties )) {
            isUpdatable = true;
            isInsertable = true;
            for (int i = 0; i < metaData.colIndexes.length; i++) {
                if (metaData.colIndexes[i] < 0) {
                    isInsertable = false;
                    break;
                }
            }
            preparedStatement = new com.example.resultset.MockJDBCPreparedStatement( s.connection, result );
        }
    }

    private static  com.example.resultset.JDBCResultSet buildNewJDBCResultSet0( java.lang.String[] columnLabels, int[][] table )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        if (columnLabels == null) {
            throw new java.lang.IllegalArgumentException( "column label cannot be null" );
        }
        java.lang.String query = "select ";
        for (int i = 0; i < columnLabels.length; i++) {
            if (i > 0) {
                query += ",";
            }
            query += columnLabels[i];
        }
        query += " from TABLE";
        com.example.resultset.MockRowSetNavigator navigator = new com.example.resultset.MockRowSetNavigator( columnLabels, table );
        com.example.resultset.MockResult r = new com.example.resultset.MockResult( query, navigator );
        r.rsProperties = 0;
        r.rsProperties = MockResultProperties.addScrollable( r.rsProperties, true );
        r.rsProperties = MockResultProperties.addUpdatable( r.rsProperties, true );
        int[] colIndexes = new int[columnLabels.length + 1];
        for (int i = 0; i < columnLabels.length; i++) {
            colIndexes[i] = i + 1;
        }
        colIndexes[columnLabels.length] = -1;
        com.example.resultset.MockResultMetaData metaData = new com.example.resultset.MockResultMetaData( columnLabels, colIndexes, columnLabels.length, columnLabels.length + 1 );
        metaData.columnLabels = columnLabels;
        r.metaData = metaData;
        com.example.resultset.MockJDBCConnection conn = new com.example.resultset.MockJDBCConnection();
        com.example.resultset.MockSessionInterface sessionInterface = new com.example.resultset.MockSessionInterface();
        com.example.resultset.MockJDBCStatementBase stmt = new com.example.resultset.MockJDBCStatementBase();
        stmt.connection = sessionInterface;
        com.example.resultset.JDBCResultSet rs = new com.example.resultset.JDBCResultSet( sessionInterface, stmt, r, metaData, conn );
        return rs;
    }

    public static  com.example.resultset.JDBCResultSet buildNewJDBCResultSet( int... values )
        throws com.example.resultset.MockSQLException, java.lang.Exception
    {
        final java.lang.String columnLabel = COLUMN_LABEL_0;
        int[][] table = new int[values.length][];
        for (int i = 0; i < values.length; i++) {
            table[i] = new int[]{ values[i] };
        }
        java.lang.String[] label = new java.lang.String[1];
        label[0] = columnLabel;
        return buildNewJDBCResultSet0( label, table );
    }

}
