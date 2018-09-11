package main;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
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

	private void createFrame(ImageIcon icon, JFrame frame) {
		frame.setLayout(new FlowLayout());
		frame.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void updateFrame(ImageIcon icon, JFrame frameTargetImg) {
		// TODO Auto-generated method stub
		
	}

	
	private BufferedImage imgFlipHorizontal(BufferedImage imgSource) {
	    BufferedImage imageTarget = new BufferedImage(imgSource.getWidth(), imgSource.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = imageTarget.createGraphics();
	    
	    graphics.drawImage(imgSource, imgSource.getHeight(), 0, -imgSource.getWidth(), imgSource.getHeight(), null);
	    graphics.dispose();
	    
	    return imageTarget;
	}
	
	private BufferedImage imgFlipVertical(BufferedImage imgSource) {
	    BufferedImage imageTarget = new BufferedImage(imgSource.getWidth(), imgSource.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = imageTarget.createGraphics();
	    
	    graphics.drawImage(imgSource, imgSource.getHeight(), 0, -imgSource.getWidth(), imgSource.getHeight(), null);
	    graphics.dispose();
	    
	    return imageTarget;
	}
	

	private void getImgPixels(BufferedImage imgSource, int[][] imgSourcePixels) {
		for (int i = 0; i < imgSource.getWidth(); i++) {
			for (int j = 0; j < imgSource.getHeight(); j++) {
				imgSourcePixels[i][j] = imgSource.getRGB(i, j);
			}
		}
	}
	
	public static void main(String[] args) {
		ImageApp imgApp = new ImageApp();

		BufferedImage imgSource = null;
		BufferedImage imgTarget = null;

		imgSource = imgApp.readImage(imgSource, args);
		imgTarget = imgApp.imgFlipHorizontal(imgSource);
		
		ImageIcon iconSource = new ImageIcon(imgSource);
		ImageIcon iconTarget = new ImageIcon(imgTarget);

		JFrame frameSourceImg = new JFrame();
		JFrame frameTargetImg = new JFrame();

		//int[][] imgSourcePixels = new int[imgWidth][imgHeight];

		//imgApp.getImgPixels(imgSource, imgSourcePixels);

		//int[][] imgTargetPixels = imgSourcePixels.clone();
		
		
		imgApp.createFrame(iconSource, frameSourceImg);
		imgApp.createFrame(iconTarget, frameTargetImg);
		
		
		imgTarget = imgApp.imgFlipHorizontal(imgTarget);
		imgTarget = imgApp.imgFlipHorizontal(imgTarget);
		
		imgApp.createFrame(iconTarget, frameTargetImg);
	}



}
