package com.pdf.compare.images;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.im4java.core.CompareCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

/**
* This class contains logic to compare images and create a new resultant folder
* @author Tanuj Bhakre
* @version 1.0
*/
public class ImageComparatorImageMagic
{
	//folder middle name where resultant files are to be placed
	public static final String RESULT_FOLDER_NAME_SEPARATOR="-ComapredTo-";
	public static final String IMAGE_EXTENSION=".png";
	//Path of image magic in the system, will vary system to system
	public static String IMAGE_MAGICK_PATH="C:\\Program Files\\ImageMagick-6.8.8-Q16";
	
	/**
	   * This method take list of images to be compared and creates a new image with the result of comparison
	   * @param firstPDFToImage list of images to be compared 
	   * @param secondPDFToImage list of images to be compared to
	   * @param imageResultRootFolder Folder where result is to stored
	   * @return List list of newly created result images
	   * @throws ImageNotCreatedException 
	   */
    public List<File> compare(List<File> firstPDFToImage, List<File> secondPDFToImage, File imageResultRootFolder) throws IOException, InterruptedException, IM4JavaException
    {
    	//Making folder where resultant image is to be stored
    	File resultImageFolder=new File(imageResultRootFolder+File.separator+firstPDFToImage.get(0).getParentFile().getName()+RESULT_FOLDER_NAME_SEPARATOR+secondPDFToImage.get(0).getParentFile().getName());
    	//Creating the directory
    	makeDirectory(resultImageFolder);
    	List<File> resultImages=new ArrayList<File>();
    	//number of validations done e.g number of pages equal or not etc
    	for (File firstImage : firstPDFToImage) {
    		File secondImage=new File(secondPDFToImage.get(0).getParent()+File.separator+firstImage.getName());
    				if(secondPDFToImage.contains(secondImage)){
    					//Comparing one page at a time
    					File result=compareImages(firstImage, secondImage, resultImageFolder);
    					resultImages.add(result);
    				}
		}
    	//Checking if resultant image is created jor not
    	fiesCreatedOrNot(resultImages);
    	return resultImages;
    }
    /**
	   * This method take list of images to be compared and creates a new image with the result of comparison
	   * @param firstPDFToImage First of image to be compared 
	   * @param secondPDFToImage Second of image to be compared to
	   * @param imageResultRootFolder Folder where result is to stored
	   * @return File Newly created result images
	   */
    public File compareImages(File firstImage,File secondImage, File resultImageFolder) throws  IOException, InterruptedException{
    	CompareCmd compare = new CompareCmd();
    	//Setting Image Magick path
    	compare.setSearchPath(IMAGE_MAGICK_PATH);
        compare.setErrorConsumer(StandardStream.STDERR); // for metric-output
        //creating operation which is to be performed
        IMOperation cmpOp = new IMOperation();
        cmpOp.addImage();
        cmpOp.addImage();
        cmpOp.metric("RMSE"); // root mean squared (normalized root mean squared)
        cmpOp.addImage();
        //Creating resultant image name
        String reslutantImageName=removeFileExtension(firstImage)+"-"+removeFileExtension(secondImage)+IMAGE_EXTENSION;
    	File resultantImage=new File(resultImageFolder+File.separator+reslutantImageName);
        try{
        	//comparing images
        	compare.run(cmpOp,firstImage.getAbsolutePath(),secondImage.getAbsolutePath(),resultantImage.getAbsolutePath());
        }catch(IM4JavaException e){
        	//This exception is always thrown even  if the comparison is successful
        	//Ignore it has no effect 
        }
        return resultantImage;
    }
    
     /**
	   * This method creates the file which is passed
	   */
	public static void makeDirectory(File folder){
		if(!folder.exists()){
			boolean flag=folder.mkdirs();
			if (!flag) {
				throw new IllegalArgumentException(folder.getAbsolutePath()+" can not be created.");
			}
		}
	}
	/**
	   * This method take list of images and determine if they are physically present in the system or not
	   * @param files List of files  
	   * @throws ImageNotCreatedException  
	   */
	public static void fiesCreatedOrNot(List<File> files){
		if(files!=null && files.size()!=0){
			for(File file:files){
				if(!file.exists()){
					throw new IllegalArgumentException(file.getAbsolutePath()+" image not created");
				}
			}
		}else{
			throw new IllegalArgumentException("Images not created");
		}
	}
	/**
	   * This method returns file name without extension
	   * @return String File Name without extension
	   */
	public static String removeFileExtension(File file){
		int lastIndexOfDot=file.getName().lastIndexOf(".");
		if(lastIndexOfDot==-1){
			throw new IllegalArgumentException(file.getName()+" is not a valid file name");
		}else{
			return file.getName().substring(0,lastIndexOfDot);
		}
	}
}
