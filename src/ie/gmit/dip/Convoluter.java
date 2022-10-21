package ie.gmit.dip;

import java.lang.System;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Convoluter { // This class carries out the convolution

	public  Convoluter() throws IOException {
		System.out.print(ConsoleColour.YELLOW_BOLD);
        System.out.println("Loading... " + Settings.Get("inputFilePath"));
		System.out.print(ConsoleColour.RESET);
		
		BufferedImage input,output;
		
		if (Settings.Get("applyToURL").equals("true")) { //if URL load from inputURL
			input = ImageIO.read(new URL(Settings.Get("inputURL")));
		} else { //otherwise load from inputFilepath
			input = ImageIO.read(new File(Settings.Get("inputFilePath"))); 
		}
		
		int WIDTH = input.getWidth();
		int HEIGHT = input.getHeight();
		
		output = new BufferedImage(WIDTH, HEIGHT, input.getType());
		System.out.print(ConsoleColour.YELLOW_BOLD);
		System.out.println("Rendering the image...");
		System.out.print(ConsoleColour.RESET);
		for(int x=0;x<WIDTH;x++)
		{
			for(int y=0;y<HEIGHT;y++)
			{
				float red=0f,green=0f,blue=0f;
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{

						int imageX = (x - 3 / 2 + i + WIDTH) % WIDTH; 
						int imageY = (y - 3 / 2 + j + HEIGHT) % HEIGHT; 
						
						int RGB = input.getRGB(imageX,imageY); //Bitwise shifts for pixel data
						int R = (RGB >> 16) & 0xff; 
						int G = (RGB >> 8) & 0xff;	
						int B = (RGB) & 0xff;	
						
						red += (R*Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))].GetValue(i, j));
						green += (G*Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))].GetValue(i, j));
						blue += (B*Kernel.values()[Integer.parseInt(Settings.Get("kernelChoice"))].GetValue(i, j));
					}
				}
				int outR, outG, outB;
				
				outR = Math.min(Math.max((int)(red),0),255);
				outG = Math.min(Math.max((int)(green),0),255);
				outB = Math.min(Math.max((int)(blue),0),255);
			
				output.setRGB(x,y,new Color(outR,outG,outB).getRGB()); 	// Ouput pixel
			}
		}

		ImageIO.write(output,"PNG", new File(Settings.Get("outputFilePath")));
		output.flush();
		System.out.print(ConsoleColour.GREEN_BOLD);
		System.out.println("File saved as "+ Settings.Get("outputFilePath"));
		System.out.print(ConsoleColour.RESET);
	}
}
