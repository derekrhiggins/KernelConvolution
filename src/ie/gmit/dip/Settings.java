package ie.gmit.dip;

public class Settings {

	private static String inputFilename;
	private static String inputFolder;
	private static String inputURL;
	private static String outputFolder;
	private static String outputFilename;
	private static int kernelChoice;
	private static boolean appendFilter;
	private static String outputLabel;
	private static boolean applyAllFilters;
	private static boolean applyToAllFiles;
	private static boolean applyToURL;

	Settings() {
		// default load conditions go here
		appendFilter = true;
		applyAllFilters = false;
		applyToAllFiles = false;
		applyToURL = false;
		kernelChoice = 0;
		inputFolder = "./";
		outputFolder = "./";
		inputFilename = "raw.png";
		fileChooser.setOutputFilename("output");
	}

	public static String Get(String Option) { // Method for getting stored settings

		String returnString;

		switch (Option) {
		case "inputFilename":
			returnString = inputFilename;
			break;
		case "inputFolder":

			if (applyToURL) {
				returnString = "[Loading single file from URL]";
			} else {
				returnString = inputFolder;
			}
			break;

		case "inputFilenameDisplay":
			if (applyToAllFiles) {
				returnString = "[All files in folder]";
			} else {
				returnString = inputFilename;
			}
			break;

		case "inputFilePath":
			StringBuilder inputFilePathBuilder = new StringBuilder();
			inputFilePathBuilder.append(inputFolder);
			inputFilePathBuilder.append(inputFilename);
			returnString = inputFilePathBuilder.toString();
			break;

		case "inputURL":
			returnString = inputURL;
			break;

		case "outputLabel":
			returnString = outputLabel;
			break;

		case "outputFilename":
			returnString = outputFilename;
			break;

		case "outputFolder":
			returnString = outputFolder;
			break;

		case "outputFilenameDisplay":
			if (applyAllFilters) {
				StringBuilder outputFilenameBuilder = new StringBuilder();
				if (applyToAllFiles) {
					outputFilenameBuilder.append("[Filename]");
				} else {
					outputFilenameBuilder.append(outputLabel);
				}
				outputFilenameBuilder.append("_[ALL FILTERS].png");
				returnString = outputFilenameBuilder.toString();
			} else {
				if (applyToAllFiles) {
					returnString = "[Filename]_[FILTER].png";
				} else {
					returnString = outputFilename;
				}
				
			}
			break;
		case "outputFilePath":
			StringBuilder outputFilePathBuilder = new StringBuilder();
			outputFilePathBuilder.append(outputFolder);
			outputFilePathBuilder.append(outputFilename);
			returnString = outputFilePathBuilder.toString();
			break;
		case "kernelChoice":
			returnString = String.valueOf(kernelChoice);
			break;

		case "kernelChoiceDisplay":
			if (applyAllFilters) {

				returnString = "All filters will be applied.";
			} else {
				returnString = String.valueOf(Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
			}
			break;
		case "appendFilter":
			returnString = String.valueOf(appendFilter);
			break;
		case "applyAllFilters":
			returnString = String.valueOf(applyAllFilters);
			break;
		case "applyToAllFiles":
			returnString = String.valueOf(applyToAllFiles);
			break;
		case "applyToURL":
			returnString = String.valueOf(applyToURL);
			break;
		default:
			System.out.println("[ERROR] Unknown variable - cannot retrieve.");
			returnString = null;

		}

		return returnString;

	}

	public static void Set(String Option, String NewValue) { // Method for storing settings
		switch (Option) {
		case "kernel":
			kernelChoice = Integer.parseInt(NewValue);
			fileChooser.setOutputFilenameOnFilterChange();
			break;
		case "inputFilename":
			inputFilename = NewValue;
			break;
		case "inputFolder":
			inputFolder = NewValue;
			break;
		case "inputURL":
			inputURL = NewValue;
			break;

		case "outputFolder":
			outputFolder = NewValue;
			break;

		case "outputLabel":
			outputLabel = NewValue;
			break;

		case "outputFilename":
			outputFilename = NewValue;
			break;

		case "appendFilter":
			// Toggle value
			appendFilter = !appendFilter;
			fileChooser.setOutputFilenameOnFilterChange();
			break;

		case "applyAllFilters":
			// Toggle value
			applyAllFilters = !applyAllFilters;
			break;

		case "applyToAllFiles":
			// Toggle value
			applyToAllFiles = !applyToAllFiles;
			break;

		case "applyToURL":
			// Toggle value
			applyToURL = !applyToURL;
			break;

		default:
			System.out.println("Unknown setting. Can't set.");
		}
	}

	public static String PadOut(String label, int spaces) {
		// Need a pad method to pad out the menu with spaces, taking two arguments, the
		// variable being displayed which we calculate length from and the full number
		// of spaces needed when empty
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < (spaces - Settings.Get(label).length()); i++) {
			stringBuilder.append(" ");
		}
		stringBuilder.append("║");
		return stringBuilder.toString();
	}

	public static void displayAll() { // To be reformatted to a table

		System.out.print(ConsoleColour.CYAN_BACKGROUND);
		System.out.println(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.print("╔══════════════════════════ ");
		System.out.print(ConsoleColour.CYAN_BOLD_BRIGHT);
		System.out.print("CURRENT SETTINGS ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("════════════════════════════╗");
		System.out.print("║ ");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Filter selected = ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(Settings.Get("kernelChoiceDisplay") + Settings.PadOut("kernelChoiceDisplay", 53));
		System.out.println("╟────────────────────────────────────────────────────────────────────────╢");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Input Folder (inputFolder)= ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(inputFolder + Settings.PadOut("inputFolder", 44));
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Input Filename (inputFile)= ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(Settings.Get("inputFilenameDisplay") + Settings.PadOut("inputFilenameDisplay", 44));
		System.out.println("╟────────────────────────────────────────────────────────────────────────╢");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Output Folder (outputFolder)= ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(outputFolder + Settings.PadOut("outputFolder", 42));
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Output Filename (outputFile)= ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(Settings.Get("outputFilenameDisplay") + Settings.PadOut("outputFilenameDisplay", 42));
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("Append Filter Name to Output Filename (appendFilter)= ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println(appendFilter + Settings.PadOut("appendFilter", 18));

		System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
		System.out.print("");
		System.out.println(ConsoleColour.RESET);
	}
}
