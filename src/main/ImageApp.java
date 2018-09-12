package main;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ImageApp extends JApplet {

	BufferedImage imgSource;
	BufferedImage imgTarget;

	String fileSaveName;

	JFrame frameMenu;
	JFrame frameImgSource;
	JFrame frameImgTarget;

	JLabel labelFileSaveNameMessage;

	JTextField textfieldFileSaveName;

	JButton btnCopy;
	JButton btnSave;
	JButton btnFlipH;
	JButton btnFlipV;
	JButton btnQuit;

	private static final long serialVersionUID = 1L;

	// Reads image either from args[0] or default cameraman.jpg
	private void frameSourceCreate(ImageIcon icon, JFrame frame) {
		frame.setLayout(new FlowLayout());
		frame.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void frameTargetCreate(ImageIcon icon) {
		frameImgTarget.setVisible(false); // hide window
		frameImgTarget.removeAll(); // close window
		//frameImgTarget.dispatchEvent(new WindowEvent(frameImgTarget, WindowEvent.WINDOW_CLOSING));


		//frame.dispose();
		frameImgTarget = new JFrame("Target Image");
		frameImgTarget.setLayout(new FlowLayout());
		frameImgTarget.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frameImgTarget.add(label);
		frameImgTarget.setVisible(true);
		frameImgTarget.setLocationRelativeTo(frameImgSource);
		frameImgTarget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void imgFileRead(String[] args) {
		if (args.length != 0) {
			try {
				this.imgSource = ImageIO.read(new File(args[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.imgSource = ImageIO.read(getClass().getResource("cameraman.jpg"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		;
	}

	private void imgFileSave(BufferedImage image, String fileSaveName) {
		try {
			File outputFile = new File(fileSaveName + ".jpg");
			ImageIO.write(image, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private BufferedImage imgFlipHorizontal(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int imgType = image.getType();

		BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, imgType);
		Graphics2D graphics = newImage.createGraphics();

		// graphics.drawImage(image, 0, 0, imgWidth, imgHeight, imgWidth, 0, 0,
		// imgHeight, null);
		graphics.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
		graphics.dispose();

		return newImage;
	}

	private BufferedImage imgFlipVertical(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int imgType = image.getType();

		BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, imgType);

		Graphics2D graphics = newImage.createGraphics();
		graphics.drawImage(image, 0, imgHeight, imgWidth, -imgHeight, null);
		graphics.dispose();

		return newImage;
	}

	public BufferedImage imgRotate(BufferedImage image, int angle) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int imgType = image.getType();

		BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, imgType);
		Graphics2D graphics = newImage.createGraphics();
		graphics.rotate(Math.toRadians(angle), imgWidth / 2, imgHeight / 2);
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

	
	public ImageApp() {
		frameMenu = new JFrame("Menu");
		frameImgSource = new JFrame("Source Image");
		frameImgTarget = new JFrame("Target Image");

		labelFileSaveNameMessage = new JLabel();
		labelFileSaveNameMessage.setText("File Save Name: ");
		labelFileSaveNameMessage.setBounds(10, 15, 100, 10);
		frameMenu.add(labelFileSaveNameMessage);

		textfieldFileSaveName = new JTextField();
		textfieldFileSaveName.setBounds(120, 10, 200, 20);
		frameMenu.add(textfieldFileSaveName);

		btnSave = new JButton("Save File");
		btnSave.setBounds(50, 40, 150, 20);
		frameMenu.add(btnSave);

		btnCopy = new JButton("Copy");
		btnCopy.setBounds(50, 80, 150, 20);
		frameMenu.add(btnCopy);
		
		btnFlipH = new JButton("Flip Horizontally");
		btnFlipH.setBounds(50, 110, 150, 20);
		frameMenu.add(btnFlipH);

		btnFlipV = new JButton("Flip Vertically");
		btnFlipV.setBounds(50, 140, 150, 20);
		frameMenu.add(btnFlipV);

		btnQuit = new JButton("Quit");
		btnQuit.setBounds(50, 170, 150, 20);
		frameMenu.add(btnQuit);

		frameMenu.setSize(400, 250);
		frameMenu.setLayout(null);
		frameMenu.setVisible(true);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (textfieldFileSaveName.getText().length() > 0) {
					fileSaveName = textfieldFileSaveName.getText();
					imgFileSave(imgTarget, fileSaveName);
				} else {
					imgFileSave(imgTarget, "saved_image");
				}
			}
		});

		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				imgTarget = imgSource;
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});
				
		btnFlipH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				imgTarget = imgFlipHorizontal(imgSource);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});
		
		btnFlipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				imgTarget = imgFlipVertical(imgSource);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
	}

	
	public static void main(String[] args) {
		ImageApp imgApp = new ImageApp();

		imgApp.imgFileRead(args);

		ImageIcon iconSource = new ImageIcon(imgApp.imgSource);
		imgApp.frameSourceCreate(iconSource, imgApp.frameImgSource);

		// imgApp.imgTarget = imgApp.imgFlipHorizontal(imgApp.imgSource);
		//ImageIcon iconTarget = new ImageIcon(imgApp.imgTarget);
		//imgApp.frameCreate(iconTarget, imgApp.frameImgTarget);

		// imgTarget = imgApp.imgFlipHorizontal(imgSource);
		// imgTarget = imgApp.imgFlipVertical(imgSource);

	}

}
