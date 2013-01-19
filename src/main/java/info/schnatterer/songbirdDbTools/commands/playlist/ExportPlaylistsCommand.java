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

import info.schnatterer.songbirdDbTools.Utils.ResourceUtils;
import info.schnatterer.songbirdDbTools.backend.connection.SongbirdDatabaseConnection;
import info.schnatterer.songbirdDbTools.backend.domain.MediaItem;
import info.schnatterer.songbirdDbTools.backend.domain.MemberMediaItem;
import info.schnatterer.songbirdDbTools.backend.domain.Property;
import info.schnatterer.songbirdDbTools.backend.domain.SimpleMediaList;
import info.schnatterer.songbirdDbTools.backend.service.PlaylistService;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command that handles exporting Sonbgird playlists to playlist files.
 * 
 * @author schnatterer
 * 
 */
public final class ExportPlaylistsCommand {
	/** Don't instantiate utility classes! */
	private ExportPlaylistsCommand() {
	}

	/** System-dependent End Of Line string. */
	public static final String EOL = System.getProperty("line.separator");

	/** SLF4J-Logger. */
	private static Logger logger = LoggerFactory.getLogger(ExportPlaylistsCommand.class);

	/**
	 * Playlist exporter implementation used by the command to write the actual playlist files.
	 */
	private static PlaylistExporter playlistExporter = new PlaylistExporterImplLizzy();

	/**
	 * Exemplary usage of this class.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws ClassNotFoundException
	 */
	public static void main(final String[] args) {
		try {
			logger.info("startup");
			SongbirdDatabaseConnection.setDbFile(args[0]);
			ExportPlaylistsCommand.exportPlaylists("etc/", "m3u", true, false);
		} finally {
			SongbirdDatabaseConnection.close();
			logger.info("shutdown");
		}
	}

	/**
	 * Exports all songbird playlists in a specific format to a specified destination folder.
	 * 
	 * @param destinationFolder
	 *            the folder to write the playlist to
	 * @param playlistFormat
	 *            desired format for the playlist (e.g. "m3u" or "pls") if
	 * @param useRelativePaths
	 *            <code>true</code> tries to create relative paths from the playlist members to the playlist file
	 * @param skipDynamicLists
	 *            <code>true</code> skips dynamic playlists
	 */
	public static void exportPlaylists(final String destinationFolder, final String playlistFormat,
			final boolean useRelativePaths, final boolean skipDynamicLists) {

		// Check if playlist can be written to destination folder.
		try {
			checkDirectory(destinationFolder);
		} catch (Exception e) {
			logger.warn("Error writing playlist: " + e.getMessage());
			return;
		}

		try {
			List<SimpleMediaList> playlists = new PlaylistService().getPlayLists(true, skipDynamicLists);
			for (SimpleMediaList simpleMediaList : playlists) {

				// String playlistName = simpleMediaList.getList().getProperty(
				// Property.PROP_MEDIA_LIST_NAME);

				// if (logger.isDebugEnabled()) {
				// // Print playlist info
				// logger.debug("Playlist: ID: "
				// + simpleMediaList.getList().getId()
				// + ": \""
				// + simpleMediaList.getList().getProperty(
				// Property.PROP_MEDIA_LIST_NAME)
				// + "\"; Type: "
				// + simpleMediaList.getList().getListType());
				// }

				// Export Playlist
				// try {
				String playlistName = simpleMediaList.getList().getProperty(Property.PROP_MEDIA_LIST_NAME);
				if (playlistName == null) {
					logger.warn("Found playlist with no name. Skipping list. " + simpleMediaList);
					return;
				}
				if (playlistName.startsWith("&smart.defaultlist.")) {
					playlistName = playlistName.substring("&smart.defaultlist.".length());
				}

				try {
					List<String> omittedFiles = playlistExporter.export(playlistNameToFileName(playlistName),
							getMemberPaths(simpleMediaList), destinationFolder, playlistFormat, useRelativePaths,
							skipDynamicLists);
					String output = "Finished writing playlist " + playlistName;
					if (omittedFiles != null && omittedFiles.size() > 0) {
						output += ". The following files were omitted because they did not exist: " + EOL;
						output += StringUtils.join(omittedFiles, EOL);
					}
					logger.info(output);
				} catch (PlaylistExporterException e) {
					logger.warn("Error creating playlist: " + e.getMessage());
				}
				// } catch (IOException e) {
				// logger.error("Unable to write playlist file", e);
				// } catch (Exception e) {
				// logger.error("Error while trying to write playlist file", e);
				// } finally {
				// try {
				// out.close();
				// } catch (IOException e) {
				// logger.error("Unable to close playlist file", e);
				// }
				// }
				// } catch (FileNotFoundException e) {
				// logger.error("Unable to open playlist file for writing",
				// e);
				// }
			}
		} catch (SQLException e) {
			/*
			 * if the error message is "out of memory", it probably means no database file is found
			 */
			logger.error("Error reading songbird database", e);
		}
	}

	/**
	 * Checks a path, if it exists, is a directory and if the application can write to it.
	 * 
	 * @param destinationFolder
	 *            the path to be checked.
	 * @throws FileSystemException
	 *             if any of the mentioned checks fails.
	 */
	private static void checkDirectory(final String destinationFolder) throws FileSystemException {
		File destinationFile = new File(destinationFolder);
		if (!destinationFile.exists()) {
			throw new FileSystemException("Destination folder does not exist: " + destinationFile.getAbsolutePath());
		}
		if (!destinationFile.isDirectory()) {
			throw new FileSystemException("Destination folder is not a directory: " 
					+ destinationFile.getAbsolutePath());
		}
		if (!destinationFile.canWrite()) {
			throw new FileSystemException("Destination folder is read only: " + destinationFile.getAbsolutePath());
		}
	}

	/**
	 * Changes the playlist name, so it can be used as file name (if necessary).
	 * 
	 * @param playlistName
	 *            the name of the playlist
	 * @return playlistName as a valid filename.
	 */
	private static String playlistNameToFileName(final String playlistName) {
		String newPlaylistName = playlistName;
		if (!ResourceUtils.isLegalFilename(playlistName)) {
			newPlaylistName = ResourceUtils.legalizeFileName(playlistName);
			logger.info("Playlist name \"" + playlistName + "\" cannot be used as file name. Replacing by \""
					+ newPlaylistName);
		}

		return newPlaylistName;
	}

	/**
	 * Aggregates a list of member absolute paths from a {@link SimpleMediaList}.
	 * 
	 * @param simpleMediaList
	 *            the media list to read the member objects from
	 * @return all member files as absolute string Urls
	 */
	private static List<String> getMemberPaths(final SimpleMediaList simpleMediaList) {
		List<String> memberFiles = new LinkedList<String>();

		for (MemberMediaItem member : simpleMediaList.getMembers()) {
			MediaItem m = member.getMember();

			try {
				File file = new File(new URI(m.getContentUrl()));
				/*
				 * Make sure Songbird's slash-separated URI work (even on Windows). If this step is omitted,
				 * relativize() is not going to work
				 */
				file = file.getCanonicalFile();
				memberFiles.add(file.getAbsolutePath());
			} catch (Throwable t) {
				logger.warn(simpleMediaList.getList().getProperty(Property.PROP_MEDIA_LIST_NAME)
						+ ": Unable to add path to playlist: " + m.getContentUrl() + ": \"" + t.getMessage()
						+ "\". Omitting file...");
				// So we use the absolute path instead...
			}
		}
		return memberFiles;
	}
}
