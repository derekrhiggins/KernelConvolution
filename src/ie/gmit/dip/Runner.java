package ie.gmit.dip;

import java.io.IOException;
import java.lang.System;


public class Runner {

	static boolean Running = true;

	public static void main(String[] args) throws IOException {

			new SplashScreen(); // Display the starting splash
			new Settings(); // Load default options

			Settings.displayAll(); // Display currents

			Commands.Display(); // Display commands
			
			while (Running) { // Loop while running

				System.out.println("Choose your command: ");
				Commands.DoCommand(Commands.getInput());
			
			}

		System.out.println();
		System.out.println("Goodbye!");
	}
}