package ie.gmit.dip;

import java.io.IOException;
import java.util.Arrays;

public class ApplyAllFilters {

	public ApplyAllFilters() throws IOException { // This method loops through each filter executing the convolution
													// matrix each time

		for (int i = 0; i < (Arrays.asList(Kernel.values()).size() - 1); i++) { // Loop over array listing options
			// Call for change of Kernel and then convolute
			Settings.Set("kernel", String.valueOf(i));
			new Convoluter();
		}
	}
}
