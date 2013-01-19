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

package info.schnatterer.songbirdDbTools;

import info.schnatterer.songbirdDbTools.backend.connection.SongbirdDatabaseConnection;
import info.schnatterer.songbirdDbTools.cli.SongbirdDatabaseToolsCli;
import info.schnatterer.songbirdDbTools.cli.SongbirdDatabaseToolsCli.ExportPlaylists;
import info.schnatterer.songbirdDbTools.commands.playlist.ExportPlaylistsCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for {@link SongbirdDatabaseTools}. Reads parameters from command line and passes them to the services.
 * 
 * @author schnatterer
 * 
 */
public class SongbirdDatabaseTools {
	/** SLF4J-Logger. */
	private final Logger logger = LoggerFactory
			.getLogger(SongbirdDatabaseTools.class);

	/**
	 * Entry point of the application.
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(final String[] args) {
		SongbirdDatabaseTools songbirdDatabaseTools = new SongbirdDatabaseTools();
		try {
			songbirdDatabaseTools.evaluateParams(args);
		} catch (Throwable e) {
			songbirdDatabaseTools.logger.error(e.getMessage(), e);
			System.exit(-1);
		}
	}

	/**
	 * Reads parameters from command line and passes them to the command objects.
	 * 
	 * @param args
	 *            command line parameters to be evaluated
	 */
	private void evaluateParams(final String[] args) {
		logger.info(this.getClass().getSimpleName() + " started...");
		logger.info("Reading command line arguments...");

		/* Parse command line arguments/parameter (command line interface) */
		Object commandParams = null;
		SongbirdDatabaseToolsCli applicationParams = new SongbirdDatabaseToolsCli();

		commandParams = applicationParams.readParams(args,
				SongbirdDatabaseTools.class.getSimpleName());

		if (commandParams != null) {
			String pathToDb = applicationParams.getSongbirdDB();
			SongbirdDatabaseConnection.setDbFile(pathToDb);

			/*
			 * Successfully read command line params, determine which command was called
			 */
			if (commandParams instanceof ExportPlaylists) {
				ExportPlaylists params = (ExportPlaylists) commandParams;
				ExportPlaylistsCommand.exportPlaylists(
						params.getDestinationPath(), params.getFormat(),
						params.isRelativePaths(), params.isSkipDynamicPlaylists());
			}
			// else if (cliParams instanceof SongbirdDatabaseToolsCli.??) {
		}
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}
}
