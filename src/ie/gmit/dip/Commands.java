package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commands {

	public static String getInput() throws IOException { // Called by various methods to get input
		System.out.print(":->");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cmd = br.readLine();
		return cmd;
	}

	public static void DoCommand(String Arguments) throws IOException { // Method for acting on user input

		String[] arr = Arguments.toLowerCase().split(" "); //Split input into arguments

		try { // TRY/CATCH FOR ERRORS LIKE 'SET' WITH NO ARGUMENT
			switch (arr[0]) {

			case "run": // Run the convolution kernel

				if (Settings.Get("applyToAllFiles").equals("true")) { // If applyToAllFiles is true I want to pass
																		// execution to it
					new ApplyToAllFiles();

				} else {

					if (Settings.Get("applyAllFilters").equals("true")) { // If apply all filters true then pass
																			// execution
																			// flow to applyAllFilters()
						new ApplyAllFilters();

					} else {

						new Convoluter(); // Otherwise execute for single file

					}
				}
				break;

			case "set": // Pass the second argument to the Set METHOD
				Commands.Set(arr[1]);
				break;

			case "show": // Display settings
				Settings.displayAll();
				break;

			case "quit": // Graceful exit
				Runner.Running = false;
				break;

			case "help": // Display commands
				Commands.Display();
				break;

			default: // Unknown command
				System.out.println("Unknown Command");
				System.out.println();
			}
		} catch (ArrayIndexOutOfBoundsException e) { // Usually thrown by no argument on SET
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("SET commands requires an argument.");
			System.out.print(ConsoleColour.RESET);
			arr[0] = null; // Set the commands received back to nothing
			
		} catch (IOException e) { // Error loading input file. Possibly output/write issues also?
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Unable to load selected input file. Please check settings.");
			System.out.println(ConsoleColour.RESET);
		}

	}

	public static void Set(String Arg1) throws NumberFormatException, IOException { // SET method

		switch (Arg1) {
		case "filter":
			new KernelSelection();
			Settings.displayAll();
			break;

		case "inputfile":
			fileChooser.setInputFilename();
			Settings.displayAll();
			break;

		case "inputfolder":
			fileChooser.setInputFolder();
			Settings.displayAll();
			break;

		case "outputfile":
			fileChooser.setOutputFilename();
			Settings.displayAll();
			break;

		case "outputfolder":
			fileChooser.setOutputFolder();
			Settings.displayAll();
			break;

		case "appendfilter":
			if (Settings.Get("applyToAllFiles").equals("true") || Settings.Get("applyAllFilters").equals("true")) { // Only
																													// flip
																													// appendFilter
																													// if
																													// it's
																													// appropriate
				System.out.println(ConsoleColour.RED_BOLD);
				System.out.println(
						"Append filter must be on when apply to all files in a folder or applying all filters."); // If
																													// not
																													// appropriate,
																													// print
																													// warning
				System.out.println(ConsoleColour.RESET);
			} else {
				Settings.Set("appendFilter", ""); // Do it otherwise
				Settings.displayAll();
			}

			break;

		default:
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("SET: Unknown option. Enter 'help' for available commands.");
			System.out.println(ConsoleColour.RESET);
			break;

		}
	}

	public static void Display() throws NumberFormatException, IOException {

		// List out available commands

		System.out.println();
		System.out.print(ConsoleColour.CYAN_BACKGROUND);
		System.out.println(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.print("╔═════════════════════════ ");
		System.out.print(ConsoleColour.CYAN_BOLD_BRIGHT);
		System.out.print("AVAILABLE COMMANDS ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("═══════════════════════════╗");

		System.out.print("║ HELP");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t Displays this list of available commands.                       ");

		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.println("║                                                                        ║");
		System.out.print("║ SET");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t This command appended with an argument can be used to alter the ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t behaviour of the program. Legitimate arguments are as follows:  ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET filter");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print(" This generates a menu where the user can choose a    ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t filter or choose to apply all filters.                          ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET inputFolder");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print(" The user can set the inputFolder from which to  ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t load the image file(s).                                         ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET inputFile");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("   The user can enter a filename, a URL beginning  ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t with http:// or https:// or enter * to select all .png files    ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t within the inputFolder chosen.                                  ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET outputFolder");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print(" The user can set the outputFolder.             ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET outputFile");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("   The user can set the output filename excluding ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t the file extension.                                             ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║\t SET appendFilter");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print(" This sets a flag to append the chosen filter   ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t name to the output filename.                                    ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║ SHOW");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t Displays the state of the program settings which can be         ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");
		System.out.print("║");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t modified by the user using these commands.                      ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║ RUN");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t Run the convolution kernel on the selected input file(s).       ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.print("║ QUIT");
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.print("\t\t This will exit the program.                                     ");
		System.out.print(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("║");

		System.out.println("║                                                                        ║");
		System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
		System.out.println(ConsoleColour.RESET);
	}

}
