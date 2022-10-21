package ie.gmit.dip;

import java.io.IOException;
import java.io.File;
import java.io.FilenameFilter;

public class ApplyToAllFiles {

	public ApplyToAllFiles() throws IOException { // This method loops through all files in folder calling convoluter on
													// each one

		File folder = new File(Settings.Get("inputFolder"));
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File folder, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});

		for (File file : listOfFiles) { // Loop through files in folder
			if (file.isFile()) { // Check it's not a folder
				Settings.Set("inputFilename", file.getName()); // Set input names
				Settings.Set("outputLabel", file.getName().split("\\.")[0]); // Split off extension for output naming

				if (Settings.Get("applyAllFilters").equals("true")) { // Send execution flow to applyAllFilters() if
																		// necessary
					new ApplyAllFilters();
				} else { // If not applying all filters - run filter for this file and then onto next

					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(file.getName().split("\\.")[0]);
					stringBuilder.append("_" + Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))]);
					stringBuilder.append(".png");

					Settings.Set("outputFilename", stringBuilder.toString());

					new Convoluter();
				}
			}
		}
	}

}
