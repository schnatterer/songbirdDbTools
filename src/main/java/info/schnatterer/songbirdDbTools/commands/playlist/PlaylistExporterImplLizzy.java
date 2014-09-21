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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import christophedelory.content.Content;
import christophedelory.playlist.Media;
import christophedelory.playlist.Playlist;
import christophedelory.playlist.Sequence;
import christophedelory.playlist.SpecificPlaylist;
import christophedelory.playlist.SpecificPlaylistFactory;
import christophedelory.playlist.SpecificPlaylistProvider;

/**
 * Implementation of {@link PlaylistExporter}, that uses the <a href="http://lizzy.sourceforge.net/">Lizzy library.</a>.
 * 
 * @author schnatterer
 * 
 */
public class PlaylistExporterImplLizzy implements PlaylistExporter {
	// /** SLF4J-Logger. */
	// private static Logger logger = LoggerFactory.getLogger(PlaylistExporterImplLizzy.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.schnatterer.songbirdDbTools.commands.playlist.PlaylistExporter#export
	 * (info.schnatterer.songbirdDbTools.backend.domain.SimpleMediaList, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public List<String> export(final String playlistName, final List<String> absoluteMemberPaths,
			final String destinationFolder, final String playlistFormat, final boolean useRelativePaths,
			final boolean exportDynamicLists) throws PlaylistExporterException {
		List<String> omittedFiles = new LinkedList<String>();

		try {
			// Create destination directory if it does not exist
			new File(destinationFolder).mkdirs();
			final File outputFile =
					new File(destinationFolder + File.separator + playlistName + "." + playlistFormat)
							.getCanonicalFile();

			Playlist playlist = new Playlist();

			for (String memberUrl : absoluteMemberPaths) {
				File member = new File(memberUrl);
				try {
					/*
					 * Caution: the input string can also be an URL. Check it now.
					 */
					if (member.exists()) {
						/*
						 * The file exists: begin the file/directory scan process.
						 * 
						 * May throw SecurityException, IOException.
						 */
						addToPlaylist(playlist.getRootSequence(), member, true, outputFile, useRelativePaths);
					} else {
						// logger.info(playlistName + ": File doesn't exist: " + member.getAbsolutePath()
						// + ". Omitting file...");
						omittedFiles.add(member.getAbsolutePath());
					}
				} catch (Throwable e) {
					throw new PlaylistExporterException(playlistName + ": Unable to add path to playlist: "
							+ member.getAbsolutePath() + ": \"" + e.getMessage() + "\". Omitting file...", e);
				}
			}

			/* Write playlist file */
			OutputStream out = null;
			try {
				out = new FileOutputStream(outputFile);
				SpecificPlaylistProvider provider =
						SpecificPlaylistFactory.getInstance().findProviderById(playlistFormat);

				SpecificPlaylist newSpecificPlaylist = provider.toSpecificPlaylist(playlist);
				newSpecificPlaylist.writeTo(out, null);
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} catch (Throwable e) {
			throw new PlaylistExporterException("Unable to create playlist file in destination folder \""
					+ destinationFolder + "\", playlist name \"" + playlistName + "\" and format \"" + playlistFormat
					+ "\": " + e.getMessage(), e);
		}
		return omittedFiles;
	}

	/**
	 * Adds the specified file or directory, and optionally its sub-directories, to the input sequence.
	 * 
	 * @param sequence
	 *            the playlist sequence to add to. Shall not be <code>null</code>.
	 * @param member
	 *            a file or directory. Shall not be <code>null</code>.
	 * @param recurse
	 *            specifies if the sub-directories of this directory shall be recursively scanned or not.
	 * @param playlistFile
	 *            an optional file to exclude from the sequence. May be <code>null</code>.
	 * @param useRelativePaths
	 *            if <code>true</code>, the file paths of the members are relativized to the playlist (if possible).
	 * 
	 * @throws NullPointerException
	 *             if <code>sequence</code> is <code>null</code>.
	 * @throws NullPointerException
	 *             if <code>file</code> is <code>null</code>.
	 * @throws SecurityException
	 *             if a security manager exists and its {@link SecurityManager#checkRead(String)} method denies read
	 *             access to a file.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	private void addToPlaylist(final Sequence sequence, final File member, final boolean recurse,
			final File playlistFile, final boolean useRelativePaths) throws IOException, NullPointerException,
			SecurityException {
		boolean recursive = false;
		/*
		 * Throws NullPointerException if file is null. May throw SecurityException.
		 */
		if (member.isDirectory()) {
			if (recurse) {
				/* May throw SecurityException. */
				final File[] files = member.listFiles();

				if (files != null) {
					for (File child : files) {
						/*
						 * Throws NullPointerException if sequence is null. May throw SecurityException, IOException.
						 */
						addToPlaylist(sequence, child, recursive, playlistFile, useRelativePaths);
					}
				}
			}
			/* May throw SecurityException. */
		} else if (member.isFile()) {
			boolean include = true;
			String filePath = member.getPath();

			if (playlistFile != null) {
				// /* May throw IOException, SecurityException. */
				final File canonicalMember = member.getCanonicalFile();

				if (canonicalMember.equals(playlistFile)) {
					include = false;
				} else {
					/*
					 * Try to make the playlist entry file name RELATIVE to the playlist file.
					 */

					if (useRelativePaths) {
						filePath = ResourceUtils.getRelativePath(playlistFile, member);
					}
				}
			}

			if (include) {
				final Media media = new Media();
				final Content content = new Content(filePath);
				media.setSource(content);
				// Throws NullPointerException if sequence is null.
				sequence.addComponent(media);
			}
		}
	}
}
