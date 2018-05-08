package info.balthaus.java;

public class OutputUtil {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void debug(String template, String computed, String expectedString2) {
		System.out.printf("%s should eval to %s and is %s\n", colorize(template), colorize(expectedString2), colorize(computed));
	}

	private static String colorize(String template) {
		return ANSI_RED + template + ANSI_RESET;
	}

}
