
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import static org.opencv.imgproc.Imgproc.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.*;

public class Tester {
	public static int numCards = 8;
	public static ITesseract instance = new Tesseract();
	static JLabel modeLabel = new JLabel();
	static JLabel resultLabel = new JLabel("");
	public static String[] solutions;

	static JLabel result2 = new JLabel("");

	static JLabel result3 = new JLabel("");

	public static void main(String[] args) throws AWTException, IOException {
		// TODO Auto-generated method stub

		makeGUI();
	    System.setProperty("java.library.path", "C:\\Users\\zzy\\Downloads\\opencv\\build\\java\\x64");
	    System.out.println(System.getProperty("java.library.path"));

		clearFolder();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// // run code
		File cardsFolder = new File("random2");
		CardMatching matcher = new CardMatching();
		instance.setDatapath("C:\\Users\\zzy\\Documents\\javaWorkspace\\AutomaticZhangLaoShi\\tessdata");

		for (final File cards : cardsFolder.listFiles()) {

			matcher.CardFileName = "random2\\" + cards.getName();
			matcher.run();

		}

		// Filter out all the garbage from the folder
		cardsFolder = new File("TestWrite");
		for (final File cards : cardsFolder.listFiles()) {

			try {

				System.out.println(matcher.GetRedTotal("TestWrite\\" + cards.getName()) + "  xxxx  " + cards.getName());

				if (matcher.GetRedTotal("TestWrite\\" + cards.getName()) <= 65000) {

					cards.delete();

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		matcher.recaptureCards(numCards);

		solutions = new String[numCards];

		int index = 0;
		// BOOOM MMMMMM
		cardsFolder = new File("Process");

		for (final File cards : cardsFolder.listFiles()) {

			pixelAnalysis("Process\\" + cards.getName(), index);
			index++;

		}

		ArrayList<SolutionSet> bestSolutions = SumSet.GetSolution(solutions);

		if (bestSolutions.size() >= 6) {
			resultLabel.setText("" + bestSolutions.get(0) + "    " + bestSolutions.get(1));
			result2.setText("" + bestSolutions.get(2) + "    " + bestSolutions.get(3));
			result3.setText("" + bestSolutions.get(4) + "    " + bestSolutions.get(5));

		} else if (bestSolutions.size() == 0) {

			resultLabel.setText(" no solutions");
			result2.setText("");
			result3.setText("");
		} else {

			for (int i = 0; i < bestSolutions.size(); i++) {

				if (i > 2) {

					result2.setText(result2.getText() + bestSolutions.get(i));

				} else if (i > 4) {
					result3.setText(result3.getText() + bestSolutions.get(i));

				} else {

					resultLabel.setText(resultLabel.getText() + bestSolutions.get(i));

				}

			}

		}

		// First Try to see if OCE Recognizes the number
		// try {
		// final File testsFolder = new File("10.PNG");
		// String result = instance.doOCR(imageFile);
		// System.out.println("xxxxxxxxxxxx" + result +"xxxxxxxxxxxxxxxx\n\n\n");

		// for (final File cards : testsFolder.listFiles()) {
		// imageFile = new File("TestWrite\\" + cards.getName());
		// String result = instance.doOCR(imageFile);
		// System.out.println(result + cards.getName() +"\n\n");
		//
		// }

		// } catch(TesseractException e) {
		//
		//
		// }
		//

		// Crop cards
		// cardsFolder = new File("Random2");
		// for (final File cards : cardsFolder.listFiles()) {
		// Mat card = Imgcodecs.imread("Random2\\" + cards.getName()
		// ,Imgcodecs.IMREAD_COLOR);
		// Rect rectCrop = new Rect( 0,0, card.width(), card.height()/2);
		// Mat image_output= card.submat(rectCrop);
		//
		// Imgcodecs.imwrite("Number\\" + cards.getName(), image_output);
		// }

		// matcher.CardFileName = "random2\\" + cards.getName();
		// matcher.run();
		// Mat card = Imgcodecs.imread("resized\\" + cards.getName()
		// ,Imgcodecs.IMREAD_COLOR);
		// Rect rectCrop = new Rect( 7,9, 18, 39);
		// Mat image_output= card.submat(rectCrop);
		//
		// Imgcodecs.imwrite("Random2\\" + cards.getName() + ".png", image_output);

		// testNum();

		// matcher.captureCards();
		// try {
		// matcher.CompareImages();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		automaticMove(bestSolutions);

	}

	// }
	public static void clearFolder() {

		File cardsFolder = new File("TestWrite");
		for (final File cards : cardsFolder.listFiles()) {
			cards.delete();
		}

		cardsFolder = new File("Process");
		for (final File cards : cardsFolder.listFiles()) {
			cards.delete();
		}
	}

	public static void testNum() {

		File folder = new File("Number");

		for (final File cards : folder.listFiles()) {

			try {
				System.out.print(cards.getName() + " ");
				System.out.println(
						CardMatching.TestCompareImages("TestWrite\\536.010.jpg", "Number\\" + cards.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		final File testsFolder = new File("TestWrite\\101.011.jpg");
		try {
			String result = instance.doOCR(testsFolder);
			System.out.println("FUCK YOU\n" + result);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void TestCardSuiteID(String file) {

		final File cardsFolder = new File("Random2");
		HashMap<Integer, String> cardMatchingData = new HashMap<Integer, String>();

		for (final File cards : cardsFolder.listFiles()) {
			try {

				cardMatchingData.put(CardMatching.TestCompareImages("Random2\\" + cards.getName(), file),
						cards.getName());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Map<Integer, String> map = new TreeMap<Integer, String>(cardMatchingData);
		Set set2 = map.entrySet();
		Iterator iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry) iterator2.next();
			System.out.print(me2.getKey() + ": ");
			System.out.println(me2.getValue());
		}

	}

	public static boolean removeBadPixels(Mat cardNumberFile, int row, int col) {

		int startPosX = (row - 1 < 0) ? row : row - 1;
		int startPosY = (col - 1 < 0) ? col : col - 1;
		int endPosX = (row + 1 > cardNumberFile.rows() - 1) ? row : row + 1;
		int endPosY = (col + 1 > cardNumberFile.cols() - 1) ? col : col + 1;

		// See how many are alive
		for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
			for (int colNum = startPosY; colNum <= endPosY; colNum++) {

				if (cardNumberFile.get(rowNum, colNum)[0] == 0) {

					cardNumberFile.row(row).col(col).setTo(new Scalar(255));
					removeBadPixels(cardNumberFile, rowNum, colNum);

				}

			}
		}

		return false;

	}

	public static void pixelAnalysis(String fileName, int i) {

		int value = 95;

		if (fileName.contains("Red")) {

			value = 150;

		} else if (fileName.contains("Neutral")) {

			// Dont go lower than 117
			value = 117;

		}

		Mat cardNumberFile = Imgcodecs.imread(fileName, Imgcodecs.IMREAD_GRAYSCALE);

		instance.setTessVariable("tessedit_char_whitelist", "1234567890AJQK");

		for (int row = 0; row < cardNumberFile.rows(); row++) {
			for (int col = 0; col < cardNumberFile.cols(); col++) {
				if (cardNumberFile.get(row, col)[0] > value) {
					cardNumberFile.row(row).col(col).setTo(new Scalar(255));

				} else {

					cardNumberFile.row(row).col(col).setTo(new Scalar(0));
				}
			}
		}

		// //Remove some garbage

		for (int row = 0; row < cardNumberFile.rows(); row++) {

			removeBadPixels(cardNumberFile, row, 0);

			removeBadPixels(cardNumberFile, row, cardNumberFile.cols() - 1);

		}

		for (int row = cardNumberFile.rows() - 1; row > cardNumberFile.rows() - 15; row--) {
			for (int col = 0; col < cardNumberFile.cols(); col++) {

				removeBadPixels(cardNumberFile, row, col);

			}
		}

		Rect rectCrop = new Rect(2, 2, cardNumberFile.width() - 3, cardNumberFile.height() - 15);
		Mat image_output = cardNumberFile.submat(rectCrop);

		Size sz = new Size(image_output.cols() * 5, image_output.rows() * 5);
		Mat resizeimage = new Mat();

		Imgproc.resize(image_output, resizeimage, sz, 0, 0, INTER_CUBIC);

		Imgcodecs.imwrite("PixelAnalysis.png", resizeimage);
		final File testsFolder = new File("PixelAnalysis.png");
		try {
			String result = instance.doOCR(testsFolder).trim();

			if (result.contains("Q7")) {

				result = "7";

			} else if (result.length() > 1 && !result.contains("10")) {

				result = result.substring(result.length() - 1, result.length());

			} else if (result.equals("1")) {

				result = "A";

			}

			solutions[i] = result;

			modeLabel.setText(modeLabel.getText() + "    " + result + ",");

		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Make card number transparent by adding it to a rgba mat

	}

	public static void automaticMove(ArrayList<SolutionSet> set) {

		// Where the mouse goes after dragging the card, and intially
		Point mouseResetCoordinate = new Point(12, 10);
		Point firstSetCoordinate = new Point(12, 10);
		Point secondSetCoordinate = new Point(12, 10);

		ArrayList<SolutionSet> setCopy = (ArrayList<SolutionSet>) set.clone();

		for (int i = 0; i < solutions.length; i++) {

			if (setCopy.get(0).firstSetContains(solutions[i])) {

				System.out.println("Moving " + solutions[i] + " to first set");
				setCopy.get(0).deleteValueFromFirstSet(solutions[i]);

				// Reset the mouse coordinate to where the card is

				// Click and drag to first set coordinate

			} else if (setCopy.get(0).secondSetContains(solutions[i])) {

				System.out.println("Moving " + solutions[i] + " to second set");
				setCopy.get(0).deleteValueFromSecondSet(solutions[i]);

				// Reset the mouse coordinate to where the card is

				// Click and drag to second set coordinate

			} else {

				System.out.println("Don't Move  " + solutions[i]);

				// Reset the coordinate, since we didn't move the card

			}

		}

	}

	public static void makeGUI() {

		// Creating the Frame
		JFrame frame = new JFrame("Chat Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 270);

		// Creating the panel at bottom and adding components
		JPanel panel = new JPanel(); // the panel is not visible in output
		JLabel label = new JLabel("Enter Text");
		result2 = new JLabel("Result2");

		result3 = new JLabel("Result3");

		JTextField tf = new JTextField(10); // accepts upto 10 characters
		JButton send = new JButton("Calculate");
		JButton reset = new JButton("Reset");
		panel.add(label); // Components Added using Flow Layout
		panel.add(tf);
		panel.add(send);
		panel.add(reset);

		JPanel panel2 = new JPanel(); // the panel is not visible in output
		JButton cards_4 = new JButton("4 cards");
		JButton cards_6 = new JButton("6 cards");
		JButton cards_8 = new JButton("8 cards");

		panel2.add(cards_4);
		panel2.add(cards_6);
		panel2.add(cards_8);

		// Text Area at the Center

		modeLabel.setText("Current Mode : ");
		modeLabel.setBounds(0, 50, 400, 10);
		result3.setBounds(0, 70, 300, 10);

		result2.setBounds(0, 90, 300, 10);
		// Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, panel2);
		frame.getContentPane().add(BorderLayout.CENTER, modeLabel);
		frame.getContentPane().add(BorderLayout.CENTER, result2);
		frame.getContentPane().add(BorderLayout.CENTER, result3);
		frame.add(resultLabel);

		frame.setVisible(true);

		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Starting");

				if (numCards != 0) {
					try {
						Robot robot = new Robot();
						clearFolder();
						String format = "png";
						String fileName = "Problem." + format;
						Rectangle captureRect = new Rectangle(781, 185, 624, 159);
						BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
						ImageIO.write(screenFullImage, format, new File(fileName));
						System.out.println("A partial screenshot saved!");

						resultLabel.setText("");

						CardMatching matcher = new CardMatching();
						matcher.recaptureCards(numCards);

						solutions = new String[numCards];

						int index = 0;
						// BOOOM MMMMMM
						File cardsFolder = new File("Process");
						for (final File cards : cardsFolder.listFiles()) {

							pixelAnalysis("Process\\" + cards.getName(), index);
							index++;

						}

						ArrayList<SolutionSet> bestSolutions = SumSet.GetSolution(solutions);

						if (bestSolutions.size() >= 6) {
							resultLabel.setText("" + bestSolutions.get(0) + "    " + bestSolutions.get(1));
							result2.setText("" + bestSolutions.get(2) + "    " + bestSolutions.get(3));
							result3.setText("" + bestSolutions.get(4) + "    " + bestSolutions.get(5));

						} else if (bestSolutions.size() == 0) {

							resultLabel.setText(" no solutions");
							result2.setText("");
							result3.setText("");
						} else {

							for (int i = 0; i < bestSolutions.size(); i++) {

								if (i > 2) {

									result2.setText(result2.getText() + bestSolutions.get(i));

								} else if (i > 4) {
									result3.setText(result3.getText() + bestSolutions.get(i));

								} else {

									resultLabel.setText(resultLabel.getText() + bestSolutions.get(i));

								}

							}

						}

					} catch (AWTException | IOException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		cards_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Setting mode to 4 cards!");
				numCards = 4;
				modeLabel.setText("Current Mode : 4 cards");
			}
		});

		cards_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Setting mode to 6 cards!");
				numCards = 6;
				modeLabel.setText("Current Mode : 6 cards");
			}
		});

		cards_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Setting mode to 8 cards!");
				numCards = 8;
				modeLabel.setText("Current Mode : 8 cards");
			}
		});

	}

}
