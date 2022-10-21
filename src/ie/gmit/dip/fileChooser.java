package ie.gmit.dip;

import java.io.IOException;
import java.io.File;

public class fileChooser {

	public static void setInputFilename() throws IOException {

		// RESET SOME FLAGS ON METHOD CALL
		if (Settings.Get("applyToURL").equals("true")) { // Reset URL flag to off if already ON
			Settings.Set("applyToURL", "");
			fileChooser.setOutputFilename("output"); // Also reset output filename
		}
		if (Settings.Get("applyToAllFiles").equals("true")) { // Reset applyToAllFiles flag
			Settings.Set("applyToAllFiles", "");
		}

		System.out.println(
				"Enter filename excluding extension, enter a full URL or enter * to process all .png files in the chosen input folder.");

		String input = Commands.getInput();

		if (input.toString().equals("*")) { // If * set flag applyToAllFiles to true to iterate over a folder contents

			System.out.println("Filter will be applied to all files in folder...");
			Settings.Set("applyToAllFiles", "");
			if (Settings.Get("appendFilter").equals("false")) { // Switch append filter if false to create unique
																// filenames
				Settings.Set("appendFilter", "");
			}

		} else if (input.toString().startsWith("http://") || input.toString().startsWith("https://")) { // URL TYPE
																										// HANDLED HERE
			Settings.Set("applyToURL", ""); // Set the flag that we're pulling from a URL
			Settings.Set("inputFilename", input); // Used for menu display purposes only
			Settings.Set("inputURL", input); // Store the input URL for use by Convoluter

			// THIS CODE STRIPS OFF .PNG ADDS THE FILTER NAME AND APPENDS IT AGAIN
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append((input.substring(1 + (input.length() - (input.length() - input.lastIndexOf("/")))))
					.split("\\.")[0]);
			if (Settings.Get("appendFilter").equals("true")) {
				stringBuilder.append("_" + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
			}
			stringBuilder.append(".png");
			Settings.Set("outputFilename", stringBuilder.toString());

		} else { // This handles a single file
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(input);
			stringBuilder.append(".png");
			Settings.Set("inputFilename", stringBuilder.toString());
		}

	}

	public static void setOutputFilename() throws IOException { // This method calls the user to enter the
																// OutputFilename

		System.out.println("Enter Output filename excluding extension:"); // Ask user to specify the file to process.
																			// Do NOT hardcode paths or file names
		String label = Commands.getInput();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(label);
		if (Settings.Get("appendFilter").equals("true")) {
			stringBuilder.append("_" + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
		}
		stringBuilder.append(".png");

		Settings.Set("outputLabel", label);
		Settings.Set("outputFilename", stringBuilder.toString());

	}

	public static void setOutputFilename(String name) { // This method takes a string to rename the OutputFilename

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(name);
		if (Settings.Get("appendFilter").equals("true")) {
			stringBuilder.append("_" + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
		}
		stringBuilder.append(".png");

		Settings.Set("outputLabel", name);
		Settings.Set("outputFilename", stringBuilder.toString());

	}

	public static void setOutputFilenameOnFilterChange() { // This method grabs outputLabel to create the OutputFilename

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Settings.Get("outputLabel"));
		if (Settings.Get("appendFilter").equals("true")) { 
			stringBuilder.append("_" + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
		}
		stringBuilder.append(".png");

		Settings.Set("outputFilename", stringBuilder.toString());

	}

	public static void setInputFolder() throws IOException {

		System.out.println("Enter Input Image Folder:"); 

		String input = Commands.getInput();

		File file = new File(input); // Lets check if it exists

		if (file.isDirectory()) {

			// Append a / if the user hasn't
			if (input.endsWith("/")) {
				Settings.Set("inputFolder", input); // It ends in a '/' so set the variable
			} else { // Add the / because they didn't
				StringBuilder stringBuilder = new StringBuilder(); 
				stringBuilder.append(input);
				stringBuilder.append("/");
				Settings.Set("inputFolder", stringBuilder.toString());
			}
		} else {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Directory doesn't exist!!");
			System.out.println(ConsoleColour.RESET);
		}
	}

	public static void setOutputFolder() throws IOException {
		// Add validation to check if folder exists
		System.out.println("Enter Output Image Folder:"); // Ask user to specify the file to process.

		String input = Commands.getInput();

		File file = new File(input); // Lets check if it exists

		if (file.isDirectory()) {

			// Append a / if the user hasn't
			if (input.endsWith("/")) {
				Settings.Set("outputFolder", input); // It ends in a '/' so set the variable
			} else {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(input);
				stringBuilder.append("/");
				Settings.Set("outputFolder", stringBuilder.toString());
			}
		} else {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Directory doesn't exist!!");
			System.out.println(ConsoleColour.RESET);

			System.out.println("Do you want to create the directory? (Y/N)");

			String query = Commands.getInput();

			if (query.toLowerCase().equals("y")) {
				if (input.endsWith("/")) {
					file.mkdirs();
					Settings.Set("outputFolder", input); // It ends in a '/' so set the variable
				} else {
					file.mkdirs();
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(input);
					stringBuilder.append("/");
					Settings.Set("outputFolder", stringBuilder.toString());
				}
			}
		}
	}

}
