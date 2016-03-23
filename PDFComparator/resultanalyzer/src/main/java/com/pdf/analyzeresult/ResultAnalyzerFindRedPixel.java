package com.pdf.analyzeresult;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
* This class analyzes the result image created as part of image comparison
* @author Tanuj Bhakre
* @version 1.0
*/
public class ResultAnalyzerFindRedPixel{
	
	private static int MIN_RED=200;
	private static int MIN_GREEN=200;
	private static int MIN_BLUE=200;
	/**
	   * This method analyzes if the compared result generated as part of image comparison
	   *  contains any pixel which can prove if two images are equal or not
	   * @param images Images to check for unequality 
	   * @return boolean True if images are UNEQUAL and False if images are equal 
	   */
	public boolean isResultUnequal(File image) throws IOException {
		boolean resultUnequal = false;
		BufferedImage image1 = ImageIO.read(image);
		//checking each pixel in the image
		found: 
		for (int y = 0; y < image1.getHeight(); y++) {
			for (int x = 0; x < image1.getWidth(); x++) {
				int c = image1.getRGB(x, y);
				Color color = new Color(c);
				//If the pixel exits with an intensity above a threshold
				if (color.getRed() > MIN_RED && color.getGreen() < MIN_GREEN && color.getBlue() < MIN_BLUE) {
					resultUnequal = true;
					//break loop as we found unequality
					break found;
				}
			}
		}
		return resultUnequal;
	}
}
