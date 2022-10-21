package ie.gmit.dip;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.System;

public class KernelSelection {

	public KernelSelection() throws NumberFormatException, IOException { // Method for the user to select a filter

		Iterator<Kernel> KernelOption = Arrays.asList(Kernel.values()).iterator(); // Get the enum into an array

		System.out.println();
		for (int i = 0; i < Arrays.asList(Kernel.values()).size(); i++) { // Loop over array listing options
			System.out.println(i + ". " + KernelOption.next());
		}

		System.out.println();
		System.out.println("Enter your choice [0 to " + (Arrays.asList(Kernel.values()).size() - 1) + "]:");

		try {
			String input;
			input = Commands.getInput();
			Settings.Set("kernel", input);

			if (input.equals("13")) {

				// Do things to activate applying all filters
				System.out.println("");
				System.out.println("You have selected to apply all filters.");
				Settings.Set("applyAllFilters", "");
				if (Settings.Get("appendFilter").equals("false")) { // Switch on append filter if false to create unique
																	// filenames
					Settings.Set("appendFilter", "");
				}

			} else { //Otherwise act on their selection
				System.out.println();
				System.out.println(
						"You have selected: " + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
				System.out.println();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						System.out.print("\t" + "\t"
								+ Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))].GetValue(i, j));
					}
					System.out.println();
				}
				System.out.println();
				if (Settings.Get("applyAllFilters").equals("true")) { // Switch applyAllFilters back to false if already
																		// enabled
					Settings.Set("applyAllFilters", "");
				}
			}
		} catch (Exception e) { //Catch invalid input

			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("You must enter a valid number when choosing filter.");
			System.out.println(ConsoleColour.RESET);

			Settings.Set("kernel", "0");
			new KernelSelection();

		}

	}
}
