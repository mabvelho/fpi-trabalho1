package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	BufferedImage imgSource, imgTarget;

	String fileSaveName;

	JFrame frameMenu, frameImgSource, frameImgTarget;
	JLabel labelFileSaveNameMessage, labelImgGain, labelImgBias;
	
	JButton btnCopy, btnSave, btnFlipH, btnFlipV, btnQuit, btnContrast, btnBrightness;
	JButton btnGaussian, btnLaplacian, btnHighPass, btnPrewittX, btnPrewittY, btnGrayScale;
	JButton btnSobelX, btnSobelY, btnRotateCCW,btnRotateCW,btnHistogram,btnZoomIn;
	
	JTextField textFieldFileSaveName, textFieldImgGain, textFieldImgBias;

	private JButton btnNegative;


	
	
	private static final long serialVersionUID = 1L;

	// Creates a frame for the source image
	private void frameSourceCreate(ImageIcon icon, JFrame frame) {
		frame.setLayout(new FlowLayout());
		frame.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frame.add(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 0);
	}

	// Recreates the image frame for target image
	private void frameTargetCreate(ImageIcon icon) {
		// hide window
		frameImgTarget.setVisible(false);

		// close window
		frameImgTarget.removeAll();

		// Create a new frame
		// frameImgTarget.setLocation(500, 500);
		frameImgTarget = new JFrame("Target Image");
		frameImgTarget.setLayout(new FlowLayout());
		frameImgTarget.setSize(icon.getIconWidth() + 25, icon.getIconHeight() + 45);

		JLabel label = new JLabel();
		label.setIcon(icon);

		frameImgTarget.add(label);
		frameImgTarget.setVisible(true);
		frameImgTarget.setLocationRelativeTo(frameImgSource);
		frameImgTarget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameImgTarget.setLocation(325 + imgSource.getWidth(), 0);
	}

	// Reads image from args[0] or from default image cameraman.jpg
	private void imgFileRead(String[] args) {
		if (args.length != 0) {
			try {
				imgSource = ImageIO.read(new File(args[0]));
				imgTarget = ImageIO.read(new File(args[0]));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				imgSource = ImageIO.read(getClass().getResource("bird.jpg"));
				imgTarget = ImageIO.read(getClass().getResource("bird.jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Saves image to <fileSaveName>.jpg
	private void imgFileSave(BufferedImage image, String fileSaveName) {
		try {
			File outputFile = new File(fileSaveName + ".jpg");
			ImageIO.write(image, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageApp() {
		// Menu frame creation
		frameMenu = new JFrame("Menu");
		frameImgSource = new JFrame("Source Image");
		frameImgTarget = new JFrame("Target Image");

		// File save name label
		labelFileSaveNameMessage = new JLabel();
		labelFileSaveNameMessage.setText("Save Name: ");
		labelFileSaveNameMessage.setBounds(10, 15, 100, 10);
		frameMenu.add(labelFileSaveNameMessage);

		// File save name textfield
		textFieldFileSaveName = new JTextField();
		textFieldFileSaveName.setBounds(80, 10, 200, 20);
		frameMenu.add(textFieldFileSaveName);

		// Save image button
		btnSave = new JButton("Save File");
		btnSave.setBounds(80, 40, 200, 20);
		frameMenu.add(btnSave);

		// Copy/Reset button
		btnCopy = new JButton("Copy/Reset");
		btnCopy.setBounds(80, 80, 200, 20);
		frameMenu.add(btnCopy);

		// Horizontal Flip button
		btnFlipH = new JButton("Flip - Horizontal");
		btnFlipH.setBounds(80, 110, 200, 20);
		frameMenu.add(btnFlipH);

		// Vertical Flip button
		btnFlipV = new JButton("Flip - Vertical");
		btnFlipV.setBounds(80, 140, 200, 20);
		frameMenu.add(btnFlipV);

		// Quit button
		btnHistogram = new JButton("Histogram");
		btnHistogram.setBounds(80, 170, 200, 20);
		frameMenu.add(btnHistogram);

		// Gain label
		labelImgGain = new JLabel();
		labelImgGain.setText("Gain: ");
		labelImgGain.setBounds(10, 205, 100, 10);
		frameMenu.add(labelImgGain);

		// Gain textfield
		textFieldImgGain = new JTextField();
		textFieldImgGain.setBounds(80, 200, 200, 20);
		frameMenu.add(textFieldImgGain);

		// Contrast button
		btnContrast = new JButton("Contrast");
		btnContrast.setBounds(80, 230, 200, 20);
		frameMenu.add(btnContrast);

		// Bias label
		labelImgBias = new JLabel();
		labelImgBias.setText("Bias: ");
		labelImgBias.setBounds(10, 265, 100, 10);
		frameMenu.add(labelImgBias);

		// Bias textfield
		textFieldImgBias = new JTextField();
		textFieldImgBias.setBounds(80, 260, 200, 20);
		frameMenu.add(textFieldImgBias);

		// Brightness button
		btnBrightness = new JButton("Brightness");
		btnBrightness.setBounds(80, 290, 200, 20);
		frameMenu.add(btnBrightness);

		// Negative Button
		btnNegative = new JButton("Negative");
		btnNegative.setBounds(80, 320, 200, 20);
		frameMenu.add(btnNegative);

		// Gaussian convolution Button
		btnGaussian = new JButton("Gaussian");
		btnGaussian.setBounds(80, 350, 100, 20);
		frameMenu.add(btnGaussian);

		// Laplacian convolution Button
		btnLaplacian = new JButton("Laplacian");
		btnLaplacian.setBounds(180, 350, 100, 20);
		frameMenu.add(btnLaplacian);

		// High Pass convolution Button
		btnHighPass = new JButton("High Pass");
		btnHighPass.setBounds(80, 380, 100, 20);
		frameMenu.add(btnHighPass);

		// Prewitt X convolution Button
		btnPrewittX = new JButton("Prewitt X");
		btnPrewittX.setBounds(80, 410, 100, 20);
		frameMenu.add(btnPrewittX);

		// Prewitt Y convolution Button
		btnPrewittY = new JButton("Prewitt Y");
		btnPrewittY.setBounds(180, 410, 100, 20);
		frameMenu.add(btnPrewittY);
		
		// Sobel X convolution Button
		btnSobelX = new JButton("Sobel X");
		btnSobelX.setBounds(80, 440, 100, 20);
		frameMenu.add(btnSobelX);
		
		// Sobel Y convolution Button
		btnSobelY = new JButton("Sobel Y");
		btnSobelY.setBounds(180, 440, 100, 20);
		frameMenu.add(btnSobelY);
		
		// Rotate clockwise button
		btnRotateCW = new JButton("Clockwise");
		btnRotateCW.setBounds(80, 470, 100, 20);
		frameMenu.add(btnRotateCW);
		
		// Rotate counterclockwise button
		btnRotateCCW = new JButton("CounterCW");
		btnRotateCCW.setBounds(180, 470, 100, 20);
		frameMenu.add(btnRotateCCW);
		
		// Zoom in button
		btnZoomIn = new JButton("Zoom In");
		btnZoomIn.setBounds(80, 500, 200, 20);
		frameMenu.add(btnZoomIn);
		
		// Grayscale button
		btnGrayScale = new JButton("Grayscale");
		btnGrayScale.setBounds(80, 530, 200, 20);
		frameMenu.add(btnGrayScale);
		
		// Frame specifications
		frameMenu.setSize(300, 600);
		frameMenu.setLayout(null);
		frameMenu.setVisible(true);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// BUTTON BEHAVIOR BELOW
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (textFieldFileSaveName.getText().length() > 0) {
					fileSaveName = textFieldFileSaveName.getText();
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
				imgTarget = ImageHandler.imgFlipHorizontal(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});

		btnFlipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				imgTarget = ImageHandler.imgFlipVertical(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});

//		btnQuit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent actionEvent) {
//				System.out.println("Application closed, goodbye!");
//				System.exit(0);
//			}
//		});

		btnHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgHistogram(imgSource);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});
		
		btnContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				double gain;
				try {
					gain = Double.parseDouble(textFieldImgGain.getText());
				} catch (NumberFormatException e) {
					gain = 1.0;
					e.printStackTrace();
				}
				imgTarget = ImageHandler.imgContrast(imgTarget, gain);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});
		
		btnBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int bias;
				try {
					bias = Integer.parseInt(textFieldImgBias.getText());
				} catch (NumberFormatException e) {
					bias = 0;
					e.printStackTrace();
				}
				imgTarget = ImageHandler.imgBrightness(imgTarget, bias);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}
		});

		btnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgNegative(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnGaussian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_GAUSSIAN);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnLaplacian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_LAPLACIAN);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnHighPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_HIGHPASS);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnPrewittX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_PREWITT_X);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnPrewittY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_PREWITT_Y);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnPrewittY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_PREWITT_Y);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnSobelX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_SOBEL_X);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});

		btnSobelY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgConvolution(imgTarget, ImageHandler.KERNEL_SOBEL_Y);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});
		
		btnRotateCW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgRotateCW(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});
		
		btnRotateCCW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgRotateCCW(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});
		
		btnZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgZoomIn(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});
		
		btnGrayScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgTarget = ImageHandler.imgToGrayscale(imgTarget);
				ImageIcon iconTarget = new ImageIcon(imgTarget);
				frameTargetCreate(iconTarget);
			}

		});
		
		

	}

	public static void main(String[] args) {
		ImageApp imgApp = new ImageApp();

		imgApp.imgFileRead(args);

		ImageIcon iconSource = new ImageIcon(imgApp.imgSource);
		imgApp.frameSourceCreate(iconSource, imgApp.frameImgSource);

		// imgApp.imgTarget = imgApp.imgFlipHorizontal(imgApp.imgSource);
		// ImageIcon iconTarget = new ImageIcon(imgApp.imgTarget);
		// imgApp.frameCreate(iconTarget, imgApp.frameImgTarget);

		// imgTarget = imgApp.imgFlipHorizontal(imgSource);
		// imgTarget = imgApp.imgFlipVertical(imgSource);

	}

}
