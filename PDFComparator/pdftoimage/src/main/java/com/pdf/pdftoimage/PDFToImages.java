package com.pdf.pdftoimage;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
/**
* This class contains logic to convert PDFs to series of Images
* @author Tanuj Bhakre
* @version 1.0
*/
public class PDFToImages{
	
	//Image extension that is created as part of this
	public static final String IMAGE_EXTENSION=".png";
	/**
	   * This method transforms PDF to Series of images
	   * @return List returns list of newly created images from the PDF
	 * @throws InterruptedException 
	   */
	public List<File> transformFiletoImages(File pdf,File imageFolder) throws IOException, InterruptedException{
        // load a pdf from a byte buffer
        RandomAccessFile raf = new RandomAccessFile(pdf, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        int numPgs = pdffile.getNumPages();
        String fileNameWithoutExtention=removeFileExtension(pdf);
        File directory=new File(imageFolder.getAbsolutePath()+File.separator+fileNameWithoutExtention);
        if(directory.exists()){
        	directory=new File(imageFolder.getAbsolutePath()+File.separator+fileNameWithoutExtention+"_NEW");
        }
        //making directory where The new files are to be stored
        makeDirectory(directory);
        List<File> imageList=new ArrayList<File>();
        for (int i = 1; i <= numPgs; i++) {
            // draw the first page to an image
            PDFPage page = pdffile.getPage(i);
            // get the width and height for the doc at the default zoom
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
            // generate the image
            Image img = page.getImage(rect.width, rect.height, // width & height
                    rect, // clip rect
                    null, // null for the ImageObserver
                    true, // fill background with white
                    true // block until drawing is done
                    );
            // save it as a file
            BufferedImage bImg = toBufferedImage(img);
            File imageFile = new File(directory+File.separator+"page_" + i + IMAGE_EXTENSION);
            ImageIO.write(bImg, "png", imageFile);
            imageList.add(imageFile);
        }
        raf.close();
        return imageList;
    }
    // This method returns a buffered image with the contents of an image
    public BufferedImage toBufferedImage(Image image) throws InterruptedException {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent
        // Pixels
        boolean hasAlpha = hasAlpha(image);
        // Create a buffered image with a format that's compatible with the
        // screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }
            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        	throw e;
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    public boolean hasAlpha(Image image) throws InterruptedException {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }
        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        	throw e;
        }
        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }
    /**
	   * This method returns file name without extension
	   * @return String File Name without extension
	   */
	public static String removeFileExtension(File file) {
		int lastIndexOfDot=file.getName().lastIndexOf(".");
		if(lastIndexOfDot==-1){
			throw new IllegalArgumentException(file.getName()+" Invalid file name");
		}else{
			return file.getName().substring(0,lastIndexOfDot);
		}
	}
	/**
	   * This method creates the file which is passed
	   * @exception FolderCanNotBeCreated Thrown when folder can not be created due to some reason
	   */
	public static void makeDirectory(File folder){
		if(!folder.exists()){
			boolean flag=folder.mkdirs();
			if (!flag) {
				throw new IllegalArgumentException(folder.getAbsolutePath()+" can not be created.");
			}
		}
	}
}