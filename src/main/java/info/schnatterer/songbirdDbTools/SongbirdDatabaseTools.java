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
package info.schnatterer.songbirdDbTools;

import info.schnatterer.java.util.jar.Jar;
import info.schnatterer.songbirdDbTools.cli.SongbirdDatabaseToolsCli;
import info.schnatterer.songbirdDbTools.cli.SongbirdDatabaseToolsCli.ExportPlaylists;
import info.schnatterer.songbirdDbTools.commands.playlist.ExportPlaylistsCommand;
import info.schnatterer.songbirddbapi4.SongbirdDb;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.ParameterException;

/**
 * Entry point for {@link SongbirdDatabaseTools}. Reads parameters from command line and passes them to the services.
 * 
 * @author schnatterer
 * 
 */
public class SongbirdDatabaseTools {
	/** SLF4J-Logger. */
	private final Logger logger = LoggerFactory.getLogger(SongbirdDatabaseTools.class);

	/**
	 * Entry point of the application.
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(final String[] args) {
		SongbirdDatabaseTools songbirdDatabaseTools = new SongbirdDatabaseTools();
		try {
			if (!songbirdDatabaseTools.evaluateParams(args)) {
				// Parameters not correct
				System.exit(-1); // NOSONAR: This is where the application ends in case of error
			}
		} catch (Exception e) {
			// Failure
			songbirdDatabaseTools.logger.error(e.getMessage(), e);
			System.exit(-1); // //NOSONAR: This is where the application ends in case of error
		}
	}

	/**
	 * Reads parameters from command line and passes them to the command objects.
	 * 
	 * @param args
	 *            command line parameters to be evaluated
	 * @return <code>false</code> if there was an error relating to parameters (the error message has been logged).
	 *         <code>true</code> if the requested command was executed.
	 */
	static final String PROG_NAME = "songbirdDbTools";

	private boolean evaluateParams(final String[] args) {
		logger.debug(PROG_NAME + " started...");
		logger.debug("Reading command line arguments...");

		/* Parse command line arguments/parameter (command line interface) */
		Object commandParams = null;
		SongbirdDatabaseToolsCli applicationParams = new SongbirdDatabaseToolsCli();

		printWelcomeMessage();

		try {
			commandParams = applicationParams.readParams(args, PROG_NAME);
		} catch (ParameterException e) { // NOSONAR: Exception already logged
			return false;
		}

		if (commandParams != null) {
			String pathToDb = applicationParams.getSongbirdDB();
			SongbirdDb db = new SongbirdDb(pathToDb);

			/*
			 * Successfully read command line params, determine which command was called
			 */
			if (commandParams instanceof ExportPlaylists) {
				ExportPlaylists params = (ExportPlaylists) commandParams;
				new ExportPlaylistsCommand(db).exportPlaylists(params.getDestinationPath(), params.getFormat(),
						params.getPlaylists(), params.isRelativePaths(), params.isSkipDynamicPlaylists());
			}
			// else if (cliParams instanceof SongbirdDatabaseToolsCli.??) {
			return true;
		}
		return false;
	}

	/**
	 * Writes a welcome message to the log/console, including a build number, if available.
	 */
	private void printWelcomeMessage() {
		String welcomeMessage = "Welcome to " + PROG_NAME;
		String buildNumber;
		try {
			buildNumber = Jar.getBuildNumberFromManifest();
			if (buildNumber != null) {
				welcomeMessage = welcomeMessage + " (" + buildNumber + ")";
			}
		} catch (IOException e) {
			// If something fails we just don't print the build number
		}
		logger.info(welcomeMessage);
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}
}
