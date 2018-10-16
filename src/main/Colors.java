package main;

import java.awt.image.BufferedImage;

public class Colors {

	int[][] alpha, red, green, blue;
		
	public Colors(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		
		alpha = new int[width][height];
		red 	= new int[width][height];
		green = new int[width][height];
		blue 	= new int[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = img.getRGB(x, y);

				alpha[x][y] = (pixel >> 24) & 0xff;
				red[x][y] 	= (pixel >> 16) & 0xff;
				green[x][y] = (pixel >> 8) & 0xff;
				blue[x][y] 	= pixel & 0xff;
				
			}
		}
	}
}
