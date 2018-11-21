package com.example.nfst;

import java.util.NoSuchElementException;
import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaState;

/**
 * This class returns tokens using non-alphanumberic characters as delimiters.
 */
public class NumberFormatStringTokenizer {

	/** Current position in the format string */
	private int currentPosition;

	/** Index of last character in the format string */
	private int maxPosition;

	/** Format string to be tokenized */
	private String str;

	/**
	 * Construct a NumberFormatStringTokenizer.
	 *
	 * @param str
	 *            Format string to be tokenized
	 */
	@EpaAction(name = "NumberFormatStringTokenizer(String)")
	public NumberFormatStringTokenizer(String str) {
		this.str = str;
		maxPosition = str.length();
	}

	/**
	 * Reset tokenizer so that nextToken() starts from the beginning.
	 */
//	@EpaAction(name = "reset()")
//	public void reset() {
//		currentPosition = 0;
//	}

	/**
	 * Returns the next token from this string tokenizer.
	 *
	 * @return the next token from this string tokenizer.
	 * @throws NoSuchElementException
	 *             if there are no more tokens in this tokenizer's string.
	 */
	@EpaAction(name = "nextToken()")
	public String nextToken() {

		if (currentPosition >= maxPosition) {
			throw new NoSuchElementException();
		}

		int start = currentPosition;

		while ((currentPosition < maxPosition) && Character.isLetterOrDigit(str.charAt(currentPosition))) {
			currentPosition++;
		}

		if ((start == currentPosition) && (!Character.isLetterOrDigit(str.charAt(currentPosition)))) {
			currentPosition++;
		}

		return str.substring(start, currentPosition);
	}

	/**
	 * Tells if there is a digit or a letter character ahead.
	 *
	 * @return true if there is a number or character ahead.
	 */
	@EpaAction(name = "isLetterOrDigitAhead()")
	public boolean isLetterOrDigitAhead() {

		int pos = currentPosition;

		while (pos < maxPosition) {
			if (Character.isLetterOrDigit(str.charAt(pos)))
				return true;

			pos++;
		}

		return false;
	}

	/**
	 * Tells if there is not a digit or a letter character ahead.
	 *
	 * @return true if there is not a number or character ahead.
	 */
	@EpaAction(name = "nextIsSep()")
	public boolean nextIsSep() {

		if (Character.isLetterOrDigit(str.charAt(currentPosition)))
			return false;
		else
			return true;
	}

	/**
	 * Tells if <code>nextToken</code> will throw an exception if it is called.
	 *
	 * @return true if <code>nextToken</code> can be called without throwing an
	 *         exception.
	 */
	@EpaAction(name = "hasMoreTokens()")
	public boolean hasMoreTokens() {
		return (currentPosition >= maxPosition) ? false : true;
	}

	/**
	 * Calculates the number of times that this tokenizer's
	 * <code>nextToken</code> method can be called before it generates an
	 * exception.
	 *
	 * @return the number of tokens remaining in the string using the current
	 *         delimiter set.
	 * @see java.util.StringTokenizer#nextToken()
	 */
//	@EpaAction(name = "countTokens()")
//	public int countTokens() {
//
//		int count = 0;
//		int currpos = currentPosition;
//
//		while (currpos < maxPosition) {
//			int start = currpos;
//
//			while ((currpos < maxPosition) && Character.isLetterOrDigit(str.charAt(currpos))) {
//				currpos++;
//			}
//
//			if ((start == currpos) && (Character.isLetterOrDigit(str.charAt(currpos)) == false)) {
//				currpos++;
//			}
//
//			count++;
//		}
//
//		return count;
//	}
	
	/*-------------------------------------------------------
	 * EPA State Methods
	 */

	private boolean nextIsSepIsEnabled() {
		return currentPosition >= 0 && currentPosition < str.length();
	}

	private boolean nextTokenIsEnabled() {
		return currentPosition < maxPosition;
	}
	
	private boolean hasMoreTokensIsEnabled() {
		return true;
	}
	
	private boolean isLetterOrDigitAheadIsEnabled() {
		return true;
	}
	

	@EpaState(name = "S1")
	private boolean stateS1() {
		return hasMoreTokensIsEnabled() && nextTokenIsEnabled() && isLetterOrDigitAheadIsEnabled() && nextIsSepIsEnabled();
	}

	@EpaState(name = "S2")
	private boolean stateS2() {
		return hasMoreTokensIsEnabled() && nextTokenIsEnabled() && isLetterOrDigitAheadIsEnabled() && !nextIsSepIsEnabled();
	}

	@EpaState(name = "S3")
	private boolean stateS3() {
		return hasMoreTokensIsEnabled() && !nextTokenIsEnabled() && isLetterOrDigitAheadIsEnabled() && !nextIsSepIsEnabled();
	}

}
