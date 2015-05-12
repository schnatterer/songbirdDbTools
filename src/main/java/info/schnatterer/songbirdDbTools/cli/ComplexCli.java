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
package info.schnatterer.songbirdDbTools.cli;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

/**
 * Helper base class to complex Command Line Interfaces using {@link JCommander} A complex CLI is built like SVN or Git,
 * e.g. for calls like this: <code>programName command [options]</code>.
 * 
 * This class allows for concrete classes to focus on declaring the command line interface. These concrete classes then
 * specify inner classes for each command whose instances are returned by {@link #readParams(String[], String)} when a
 * specific command is called.
 * 
 * @author schnatterer
 * 
 */
public abstract class ComplexCli {

	/** System-dependent end of line string. */
	public static final String EOL = System.getProperty("line.separator");

	/** Description for help parameter. */
	private static final String DESC_HELP = "(optional) Show this message";

	/** Help parameter. */
	@Parameter(names = "--help", help = true, description = DESC_HELP)
	private boolean help;

	/**
	 * Using the {@link JCommander} framework to parse parameters.
	 * 
	 * See <a href="http://jcommander.org/">jcommander.org</a>
	 */
	private JCommander commander = null;

	/**
	 * Reads the command line parameters and prints error messages when something went wrong.
	 * 
	 * @param argv
	 *            the command line parameters to be read
	 * @param programmName
	 *            program name to be output in case of error
	 * @return a concrete instance of the available commands when everything went OK
	 * 
	 * @throws ParameterException
	 *             when something went wrong
	 */
	public Object readParams(final String[] argv, final String programmName) throws ParameterException {
		commander = new JCommander(this);
		commander.setProgramName(programmName);

		Map<String, Object> commandString2Instance = createCommands();
		try {
			commander.parse(argv);
		} catch (ParameterException e) { // NOSONAR: Method call preserves exception!
			printErrorThrowException(e);
		}

		Object parsedCommandObject = null;
		if (help) {
			// Help parameter was issued
			commander.usage();
		} else if (commander.getParsedCommand() == null) {
			// No command parameter was passed
			printErrorThrowException("Missing command parameter.");
		} else {
			// Determine the command object, which was passed to the CLI
			parsedCommandObject = commandString2Instance.get(commander.getParsedCommand().toLowerCase());
		}
		return parsedCommandObject;
	}

	/**
	 * Returns a list of all annotated command classes.
	 * 
	 * @return a list of all command classes or an empty {@link List}, if none present
	 */
	public List<Class<?>> getCommandClasses() {
		List<Class<?>> commandClasses = new LinkedList<Class<?>>();
		for (Class<?> innerClass : SongbirdDatabaseToolsCli.class.getDeclaredClasses()) {
			if (innerClass.isAnnotationPresent(Parameters.class)) {
				commandClasses.add(innerClass);
			}
		}
		return commandClasses;
	}

	/**
	 * Creates the command instances and adds them to {@link #commander}.
	 * 
	 * @return a map mapping command string to command instance (command string in <b>lower case letters</b>)
	 */
	private Map<String, Object> createCommands() {

		Map<String, Object> commandString2Instance = new HashMap<String, Object>();

		for (Class<?> innerClass : getCommandClasses()) {
			if (innerClass.isAnnotationPresent(Parameters.class) && Object.class.isAssignableFrom(innerClass)) {
				Object command = null;
				try {
					/*
					 * Create an instance of the inner class relating to this instance of the outer class
					 */
					command =
							innerClass.getDeclaredConstructor(new Class[] { this.getClass() }).newInstance(
									new Object[] { this });

				} catch (Exception e) { // NOSONAR: Method call preserves exception!
					printErrorThrowException(e);
				}
				String commandStr = innerClass.getSimpleName();
				String commandStrLower = commandStr.toLowerCase();
				String commandStrUpper = commandStr.toUpperCase();

				commandString2Instance.put(commandStrLower, command);
				// Add command, define lower and upper version as aliases
				commander.addCommand(commandStr, command, commandStrLower, commandStrUpper);
			}

		}
		return commandString2Instance;
	}

	/**
	 * Prints out the error message contained in <code>e</code> and tries to append the usage info.
	 * 
	 * @param msg
	 *            the error message
	 */
	private void printError(final String msg) {
		StringBuilder errStr = new StringBuilder(msg + EOL);
		if (commander != null) {
			// Append usage
			commander.usage(errStr, "  ");
		}
		System.err.println(errStr.toString());
	}

	/**
	 * Just like {@link #printError(String)}, but in addition <b>always</b> throws a {@link ParameterException}
	 * (containing <code>e</code>), so the main application knows something went wrong.
	 * 
	 * @param e
	 *            the exception containing the error message. Is passed to the {@link ParameterException} as cause
	 * @throws ParameterException
	 *             is thrown on <b>each</b> method call!
	 */
	private void printErrorThrowException(final Throwable e) throws ParameterException {
		printError(e.getMessage());
		/*
		 * Throw an Exception, so the main application knows something went wrong
		 */
		if (e instanceof ParameterException) {
			// Rethrow
			throw (ParameterException) e;
		} else {
			throw new ParameterException(e);
		}
	}

	/**
	 * Convenience method for {@link #printErrorThrowException(Throwable)}.
	 * 
	 * @param msg
	 *            the error message
	 * @throws ParameterException
	 *             is thrown on <b>each</b> method call!
	 */
	private void printErrorThrowException(final String msg) throws ParameterException {
		printError(msg);
		/*
		 * Throw an Exception, so the main application knows something went wrong
		 */
		throw new ParameterException(msg);
	}

	/**
	 * @return the help
	 */
	protected boolean isHelp() {
		return help;
	}

}
