package info.schnatterer.songbirdDbTools.commands.playlist;

/**
 * A checked exception type encapsulating any exception occurred during playlist creation.
 * 
 * @author schnatterer
 * 
 */
public class PlaylistExporterException extends Exception {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently
	 * be initialized by a call to {@link Throwable#initCause(Throwable)}.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
	 *            method.
	 * @param cause
	 *            the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). (A null
	 *            value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public PlaylistExporterException(final String message, final Throwable cause) {
		super("Error when trying to write playlist: " + message, cause);
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently
	 * be initialized by a call to {@link Throwable#initCause(Throwable)}.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
	 *            method.
	 */
	public PlaylistExporterException(final String message) {
		super("Error when trying to write playlist: " + message);
	}
}
