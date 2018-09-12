package main;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	private BufferedImage readImage(BufferedImage image, String[] args) {
		if (args.length != 0) {
			try {
				image = ImageIO.read(new File(args[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				image = ImageIO.read(getClass().getResource("cameraman.jpg"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;
	}

	private static void frameCreate(ImageIcon icon, JFrame frame) {
		frame.setLayout(new FlowLayout());
		frame.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static void frameUpdate(ImageIcon icon, JFrame frame) {
		// TODO Auto-generated method stub
		frame.removeAll();
		frameCreate(icon, frame);
	}

	
	private BufferedImage imgFlipHorizontal(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int imgType = image.getType();
		
	    BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, imgType);
	    Graphics2D graphics = newImage.createGraphics();
	    
	    
	    //graphics.drawImage(image, 0, 0, imgWidth, imgHeight, imgWidth, 0, 0, imgHeight, null);
	    graphics.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
	    graphics.dispose();
	    
	    return newImage;
	}
	
	private BufferedImage imgFlipVertical(BufferedImage image) {
		
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = newImage.createGraphics();
	    
	    graphics.drawImage(image, 0, image.getHeight(), image.getWidth(), -image.getHeight(), null);
	    graphics.dispose();
	    
	    return newImage;
	}
	
	public static BufferedImage imgRotate(BufferedImage image, int angle) {  
        int imgWidth = image.getWidth();  
        int imgHeight = image.getHeight();  
        int imgType = image.getType();
        
        BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, imgType);  
        Graphics2D graphics = newImage.createGraphics();  
        graphics.rotate(Math.toRadians(angle), imgWidth/2, imgHeight/2);  
        graphics.drawImage(image, null, 0, 0);  
        return newImage;  
    } 		
	
	private void imgExtractPixels(int[][] imgPixels, BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				imgPixels[i][j] = image.getRGB(i, j);
			}
		}
	}
	
	private BufferedImage imgReplacePixels(BufferedImage image, int[][] imgPixels) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image.setRGB(i, j, imgPixels[i][j]);
			}
		}
		return image;
	}
	
	public static void main(String[] args) {
		ImageApp imgApp = new ImageApp();

		BufferedImage imgSource = null;
		BufferedImage imgTarget = null;

		imgSource = imgApp.readImage(imgSource, args);
		imgTarget = imgApp.imgFlipHorizontal(imgSource);
		
		ImageIcon iconSource = new ImageIcon(imgSource);
		ImageIcon iconTarget = new ImageIcon(imgTarget);

		JFrame frameImgSource = new JFrame("Source Image");
		JFrame frameImgTarget = new JFrame("Target Image");
		
		//int[][] imgSourcePixels = new int[imgWidth][imgHeight];

		//imgApp.getImgPixels(imgSource, imgSourcePixels);

		//int[][] imgTargetPixels = imgSourcePixels.clone();
		
		
		ImageApp.frameCreate(iconSource, frameImgSource);
		ImageApp.frameCreate(iconTarget, frameImgTarget);
		//white 16777215
		
		try {
			File outputFile = new File ("mini2.jpg");
			ImageIO.write(imgTarget, "PNG", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(imgTarget.toString());
		imgTarget = imgApp.imgFlipVertical(imgSource);
		System.out.println(imgTarget.toString());
		iconTarget = null;
		iconTarget = new ImageIcon(imgTarget);
	}



}
