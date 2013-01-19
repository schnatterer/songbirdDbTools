package info.schnatterer.songbirdDbTools.Utils;

/**
 * Contains utility methods for {@link String} operations.
 * 
 * @author schnatterer
 * 
 */
public final class StringUtils {
	/** Don't instantiate utility classes! */
	private StringUtils() {
	}

	/**
	 * Returns a substring before a certain index, excluding the character at
	 * the index.
	 * 
	 * @param str
	 *            string to find substring in
	 * @param index
	 *            position of the first character that is excluded.
	 * @return a substring before a certain index, excluding the character at
	 *         the index.
	 */
	public static String substringBefore(final String str, final int index) {
		return str.substring(0, index);
	}

	/**
	 * Returns a substring after a certain index, excluding the character at the
	 * index.
	 * 
	 * @param str
	 *            string to find substring in
	 * @param substring
	 *            the returned value begins after this substring.
	 * @return a substring after a certain index, excluding the character at the
	 *         index.
	 */
	public static String substringAfter(final String str, final String substring) {
		return str.substring(substring.length() + 1);
	}
}
