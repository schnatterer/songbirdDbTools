package info.schnatterer.songbirdDbTools.commands.playlist;

import java.util.List;

/**
 * @author schnatterer
 * 
 */
public interface PlaylistExporter {

	/**
	 * Export playlist from songbird database. In case of errors just logs the problem.
	 * 
	 * @param playlistName
	 *            the name of the playlist (use this as file name)
	 * @param absoluteMemberPaths
	 *            the absolute paths to the member files to be written to the playlist
	 * @param destinationFolder
	 *            the folder to write the playlist to. Creates if it does not exist.
	 * @param playlistFormat
	 *            desired format for the playlist (e.g. "m3u" or "pls")
	 * @param useRelativePaths
	 *            if <code>true</code> tries to create relative paths from the playlist members to the playlist file
	 * @param exportDynamicLists
	 *            <code>true</code> also exports dynamic playlists
	 * @throws PlaylistExporterException
	 *             when the creation of the playlists fails (encapsulates any exception occurred).
	 * @return a list of files that could not be written to the playlist because they did not exist.
	 */
	List<String> export(final String playlistName, final List<String> absoluteMemberPaths, String destinationFolder,
			String playlistFormat, boolean useRelativePaths, boolean exportDynamicLists)
			throws PlaylistExporterException;
}