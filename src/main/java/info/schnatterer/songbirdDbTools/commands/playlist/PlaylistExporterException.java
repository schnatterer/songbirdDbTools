/**
 * Copyright (C) 2013 Johannes Schnatterer
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
		super(message, cause);
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
		super(message);
	}
}
