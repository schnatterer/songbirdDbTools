/**
 * Copyright (C) 2015 Johannes Schnatterer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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