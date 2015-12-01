
/**
 * The Logger class is used for the Output of the Programm.
 * At the moment System.out.println() is used for that.
 * The class is singleton.
 */
class Logger {

	/**The Instance of the Class (Singleton)*/
	private static Logger instance;


	/**
	 * Private Contructor of the Class
	 */
	private Logger() {
	}

	/**
	 * Returns the Instance of the Logger Object. If there is no Instance (the first call) it calls the private Contructor.
	 * It always returns the same Instance of the Class
	 * @return Instance ot the Class
     */
	public static Logger getInstance() {
		if (Logger.instance == null)
			Logger.instance = new Logger();
		return instance;
	}

	/**
	 * All Messages which are just Information. It adds the Level "Info" to the message
	 * It calls the log-Method
	 * @param to_log The Message which should be logged
     */
	public void info(String to_log) {
		log("Info", to_log);
	}

	/**
	 * All Messages which are Errors. It adds the Level "Error" to the message
	 * It calls the log-Method
	 * @param to_log The Message which should be logged
     */
	public void error(String to_log) {
		log("Error", to_log);
	}

	/**
	 * This Method is logging the Messages with the level. It uses System.out.println() to show the Message within the Console
	 * @param level Level of the Message
	 * @param to_log The Message
     */
	private void log(String level, String to_log) {
		System.out.println(level + ": " + to_log);
	}
}
