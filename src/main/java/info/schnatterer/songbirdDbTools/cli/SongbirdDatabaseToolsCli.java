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

package info.schnatterer.songbirdDbTools.cli;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * The SongbirdDatabaseTools command line interface takes care of parsing the arguments and printing out potential
 * errors.
 * 
 * @author schnatterer
 * 
 */
public class SongbirdDatabaseToolsCli extends ComplexCli {
	// private static final String DESC_HELP = "(optional) Show this message";

	/** Description for parameter - command export playlist. */
	private static final String DESC_COMMAND_EXPORT = "Exports playlists";
	/** Description for parameter - path to songbird database. */
	private static final String DESC_DB = "Path to songbird database file";

	/** Definition of parameter - path to songbird database. */
	@Parameter(names = { "-d", "-db" }, description = DESC_DB, required = true)
	private String songbirdDB;

	/** Definition of parameter - command export playlist (subclass). */
	@Parameters(commandDescription = DESC_COMMAND_EXPORT)
	public class ExportPlaylists {
		/** Description for parameter - playlist format. */
		private static final String DESC_FORMAT = "Playlist format";
		/** Description for parameter - main parameter (destination folder). */
		private static final String DESC_MAIN = "[destination folder to export playlists]";
		/** Description for parameter - use relative paths? */
		private static final String DESC_RELATIVE_PATHS = 
				"Try to use paths relative to the playlist directory for members. ";
		/** Description for parameter - skip export dynamic playlists? */
		private static final String DESC_DYNAMIC_LISTS = 
				"Skip songbird's dynamic playlists (e.g. recently added, highest rated)";

		/** Definition of parameter - main parameter (destination folder). */
		@Parameter(description = DESC_MAIN, required = true)
		private List<String> mainParams;

		/** Definition of parameter - playlist format. */
		@Parameter(names = { "-f", "--format" }, description = DESC_FORMAT)
		private String format = "m3u";

		/** Definition of parameter - use relative paths? */
		@Parameter(names = { "-r", "--relative" }, description = DESC_RELATIVE_PATHS)
		private boolean relativePaths = false;

		/** Definition of parameter - skip dynamic playlists? */
		@Parameter(names = { "-d", "--skipdynamic" }, description = DESC_DYNAMIC_LISTS)
		private boolean skipDynamicPlaylists = false;

		/** @return the value of the destination path parameter. */
		public String getDestinationPath() {
			return mainParams.get(0);
		}

		/** @return the value of the playlist format parameter. */
		public String getFormat() {
			return format;
		}

		/** @return the value of the "use relative paths" paramter. */
		public boolean isRelativePaths() {
			return relativePaths;
		}

		/**
		 * @return the value of the "skip export dynamic playlists" paramter.
		 */
		public boolean isSkipDynamicPlaylists() {
			return skipDynamicPlaylists;
		}
	}

	/** @return the value of the path to songbird database parameter. */
	public String getSongbirdDB() {
		return songbirdDB;
	}
}
