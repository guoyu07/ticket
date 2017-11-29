/**
 *  缩略图实现，将图片(jpg、bmp、png、gif等等)真实的变成想要的大小
 */
package com.ticket.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*******************************************************************************
 * 本类即可压缩图片，按指定的存储大小压缩图片compressAllExceptGif(long size)
 * 也可生成缩略图，changePicSizeAll()
 * @author: xw
 */
 public class CompressPicUtil { 
	 private Image img;
	 private File file; // 文件对象 
	//联结其他类时不需要，测试用
//	 private String inputDir; // 输入图路径
	 private String outputDir; // 输出图路径
	 //联结其他类时不需要，测试用
//	 private String inputFileName; // 输入图文件名
	 private String outputFileName; // 输出图文件名
	 private int outputWidth = 100; // 默认输出图片宽
	 private int outputHeight = 100; // 默认输出图片高
	 private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
	 private float quality = 1; //压缩的程度,0.0 通常被解释为“高度压缩很重要”， 1.0 通常被解释为“高图像质量很重要”。 
	 private double conversion = 10; //用来转换quality的参数
	 private double dividend = 10; //用来转换quality的参数
	 private static final double NUMBER_ONE = 1; 
	 /**
	  * 有参构造函数，可直接调用图片的处理方法，不用再加载图片数据
	  * 按照图片原长宽输出,等比缩放输出,输出到同一文件夹下
	  * @param file
	  * 		上传的文件
	  * @param type 上传的图片的格式
	  * @throws IOException
	  */
	 @SuppressWarnings("deprecation")
	public CompressPicUtil(File file,String type) throws IOException{
		 	 this.file = file; 
		 	 this.outputDir = ServletActionContext.getRequest().getRealPath("/upload") + "/"; 
		 	 this.outputFileName = new Date().getTime() + type; 
		 	 this.img = ImageIO.read(file); // 构造Image对象
		 	 this.outputWidth = img.getWidth(null); // 得到源图宽
		 	 this.outputHeight = img.getHeight(null); // 得到源图长
		 	 this.proportion = false;
	 }
	 /**
	  * 有参构造函数，可直接调用图片的处理方法，不用再加载图片数据
	  * 按照指定的长宽输出,自行确定是否等比缩放输出,输出到upload下
	  * @param file 上传的文件
	  * @param width 需要输出的图片的宽
	  * @param height 需要输出的图片的高
	  * @param prop 是否等比缩放输出
	  * @param type 上传的图片的格式
	 * @throws IOException 
	  */
	 @SuppressWarnings("deprecation")
	public CompressPicUtil(File file,Integer width,Integer height,boolean prop,String type) throws IOException{
		 this.file = file;
		 this.outputDir = ServletActionContext.getRequest().getRealPath("/upload") + "/";
		 this.outputFileName = new Date().getTime() + type;
		 this.outputWidth = width;
		 this.outputHeight = height;
		 this.proportion = prop;
	 }
//	 public void setInputDir(String inputDir) { 
//		 this.inputDir = inputDir; 
//	 } 
	 public void setOutputDir(String outputDir) { 
		 this.outputDir = outputDir; 
	 } 
//	 public void setInputFileName(String inputFileName) { 
//		 this.inputFileName = inputFileName;
//	 } 
	 public void setOutputFileName(String outputFileName) { 
		 this.outputFileName = outputFileName; 
	 } 
	 public void setOutputWidth(int outputWidth) {
		 this.outputWidth = outputWidth; 
	 } 
	 public void setOutputHeight(int outputHeight) { 
		 this.outputHeight = outputHeight; 
	 } 
	 public void setWidthAndHeight(int width, int height) { 
		 this.outputWidth = width;
		 this.outputHeight = height; 
	 } 
	 /**
	  * 返回压缩后的图片上传到服务器后的路径
	  * @return
	  */
	 public String getOutPath(){
		 String path = "/upload/" + outputFileName;
		 return path;
	 }
	 /**
	  * 返回缩略图的路径
	  * @return
	  */
	 public String getThumbnailPath(){
		 String path = "/upload/thumbnail/thumbnail" + outputFileName;
		 return path;
	 }
	 /**
	  * 传入源文件的路径,返回源文件内存大小
	  * @param path
	  * 		路径
	  * @return length
	  * 		返回源文件的内存大小
	  */
	 public int getPicSize(String path) { 
		 file = new File(path); 
		 int length = 0;
		 try {
			FileInputStream fs = new FileInputStream(file);
			length = fs.available();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return length; 
	 }
	 /**
	  * 压缩按照原图片原大小输出
	  * 改变像素以改变图片大储存大小
	  * 此方法对jpg格式压缩成png,jpg格式都不会有颜色的变化
	  * 但对png格式压缩成png,jpg格式都会有变化,只适用于jpg转为其他格式
	  * @param size
	  * 		需要的压缩到的存储大小(KB)
	  * @return
	  * 		false:失败
	  * 		true:成功
	  */
	 public boolean compressOnlyJpg(long size){
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		//指定写图片的方式为 jpg 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。 
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new JPEGImageWriteParam(null);
		
		/*
		 * setCompressionMode(int mode)
		 *  MODE_DISABLED - 如果该模式被设置为 MODE_DISABLED，则查询或修改压缩类型或参数的
		 *  方法将会抛出 IllegalStateException（如果插件通常支持压缩）。
		 *  一些 writer（比如 JPEG）通常不提供未压缩的输出。在这种情况下，
		 *  试图将模式设置为 MODE_DISABLED 将会抛出 UnsupportedOperationException 并且将不更改该模式。 
		 *  MODE_EXPLICIT - 使用此 ImageWriteParam 中指定的压缩类型和质量设置进行压缩。任何以前设置的 compression 参数都将被丢弃。 
		 *	MODE_COPY_FROM_METADATA - 使用传入 writer 的元数据对象中指定的 compression 参数。 
		 *	MODE_DEFAULT - 使用默认 compression 参数。 
		*/
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
//		这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality(quality);
		
		/*
		 * 指定 writer 使用逐步模式写出图像，从而输出流将包含一系列质量递增的扫描
		 * MODE_DISABLED - 不前进。使用此模式则停止前进。 
		 * MODE_COPY_FROM_METADATA - 输出图像将使用在传入 writer 的元数据对象中找到的任何 progression 参数。 
		 * MODE_DEFAULT - 该图像将被逐步写入，参数由 writer 选择。 
		 */
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_COPY_FROM_METADATA);
		ColorModel colorModel;
		try {
			colorModel = ImageIO.read(file).getColorModel();
//			setDestinationType()使用 ImageTypeSpecifier 设置目标图像的所需图像类型
//			createCompatibleSampleModel(16, 16)用指定宽度和高度创建的 SampleModel，具有与此 ColorModel 兼容的数据布局。
//			w - 应用于新的 SampleModel 的宽度
//			h - 应用于新的 SampleModel 的高度 
			imgWriteParams.setDestinationType(new ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			src = ImageIO.read(file);
			if (src.getWidth(null) == -1) {
				return false;
			} else {
				out = new FileOutputStream(outputDir + outputFileName);
				//将 ImageWriter 恢复到其初始状态。 
				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				//setOutput(Object output)
				//output - 用于以后的写入的 ImageOutputStream 或其他 Object。
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				/*	write(IIOMetadata streamMetadata,IIOImage image,ImageWriteParam param)
				 *  streamMetadata - 表示流元数据的 IIOMetadata 对象，或者为 null，表示使用默认值。
					image - 包含要写入的图像、缩略图和元数据的 IIOImage 对象。
					param - 一个 ImageWriteParam，或者为 null，表示使用默认 ImageWriteParam。 
				 */
				imgWrier.write(null, new IIOImage(src, null, null),
						imgWriteParams);
				int length = getPicSize(outputDir + outputFileName);
				if(size < length/1024){
					quality = (float) ((float)(conversion-NUMBER_ONE)/dividend);
					conversion--;
					if(quality >= 0){
						compressOnlyJpg(size);
					}
				}
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	 }
	 
	 /**
	  * 图片的处理方法，主要用来生成缩略图
	  * 根据用户需求对图片进行处理,不支持gif生成，生成以后是静态的gif
	  * 支持jpg,png格式之间的转换,颜色不会发生大变化
	  * @return
	  * 		false:失败
	  * 		true:成功
	  */
	 public boolean changePicSizeAll() { 
		 String outPath = ServletActionContext.getRequest().getRealPath("/upload") + "/thumbnail";
		 File newfile = new File(outPath);
		 if(!newfile.exists() || !newfile.isDirectory()){
			 try {
				newfile.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 try {
			 Image img = ImageIO.read(file); 
			 // 判断图片格式是否正确 
			 if (img.getWidth(null) == -1) {
//				 System.out.println(" can't read,retry!" + "<BR>"); 
				 return false; 
			 } else {
				 int newWidth; 
				 int newHeight; 
				 // 判断是否是等比缩放 
				 if (this.proportion == true) { 
					 // 为等比缩放计算输出的图片宽度及高度 
					 double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1; 
					 double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1; 
					 // 根据缩放比率大的进行缩放控制 
					 double rate = rate1 > rate2 ? rate1 : rate2; 
					 newWidth = (int) (((double) img.getWidth(null)) / rate); 
					 newHeight = (int) (((double) img.getHeight(null)) / rate); 
				 } else { 
					 newWidth = outputWidth; // 输出的图片宽度 
					 newHeight = outputHeight; // 输出的图片高度 
				 } 
			 	BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
			 	
			 	/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
				 * 优先级比速度高 生成的图片质量比较好 但速度慢
				 */ 
			 	tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
			 	FileOutputStream out = new FileOutputStream(outPath + "/thumbnail"+ outputFileName);
			 	//打桩
			 	//System.out.println(outputDir + outputFileName);
			 	// JPEGImageEncoder可适用于其他图片类型的转换 
			 	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
			 	encoder.encode(tag); 
			 	out.close(); 
			 } 
		 } catch (IOException ex) { 
			 ex.printStackTrace(); 
			 return false;
		 } 
		 return true; 
	} 
	/**
	 * 对图片进行压缩处理，可以压缩jpg,png格式==,可以轻松转换格式
	 * 适用除gif外的所有图片格式转换
	 * @param size 要压缩到的内存大小
	 * @return
	 */
	public boolean compressAllExceptGif(long size) {
		try {
			/* 对服务器上的临时文件进行处理 */
			Image srcFile = ImageIO.read(file);
			outputWidth = srcFile.getWidth(null);
			outputHeight = srcFile.getHeight(null);
			if(srcFile.getWidth(null) == -1){
				return false;
			}else{
				int newWidth; 
				int newHeight;
				//如果图片的宽大于浏览器的宽
				if(outputWidth>1000){
					newWidth = 800;
				}
				//如果图片的高大于浏览器的高
				if(outputHeight>972){
					newHeight = 800;
				}
				// 判断是否是等比缩放
				if (this.proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null))
							/ (double) outputWidth + 0.1;
					double rate2 = ((double) srcFile.getHeight(null))
							/ (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) srcFile.getWidth(null)) / rate);
					newHeight = (int) (((double) srcFile.getHeight(null)) / rate);
				} else {
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				/* 宽,高设定 */
				BufferedImage tag = new BufferedImage(newWidth, newHeight,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, newWidth, newHeight, null);
				
				FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);
				int length = getPicSize(outputDir + outputFileName);
				if(size < length/1024){
					quality = (float) ((float)(conversion-NUMBER_ONE)/dividend);
					conversion--;
//				System.out.println(quality);
					if(quality >= 0){
						compressAllExceptGif(size);
					}
				}
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
 }
