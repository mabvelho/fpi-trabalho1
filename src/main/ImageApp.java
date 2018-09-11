package main;


import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageApp extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Reads image either from args[0] or default cameraman.jpg
	private BufferedImage readImage(BufferedImage img, String[] args) {
		if (args.length != 0) {
			try {
				img = ImageIO.read(new File( args[0] ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {			
			try {
				img = ImageIO.read(getClass().getResource("cameraman.jpg"));
				//img = ImageIO.read(new File( "C:/Users/mvelho/eclipse-workspace/fpi-trabalho1/images/cameraman.jpg" ));				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static void main(String[] args) {
		ImageApp imgApp = new ImageApp();
		
		BufferedImage img = null; 
		img = imgApp.readImage(img, args);
		int imgHeight 	= img.getHeight();
		int imgWidth 	= img.getWidth();	
		
		ImageIcon icon = new ImageIcon(img);
		
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(imgWidth, imgHeight);
		
		JLabel label = new JLabel();
		label.setIcon(icon);
		
		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
}
