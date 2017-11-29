package com.ticket.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 上传图片的裁剪工具类
 * @ClassName: ImageUtil   
 * @Description: 用于上传图片时在线裁剪和保存新图片   
 * @author HiSay  
 * @date Aug 21, 2013 6:58:22 AM   
 */
@SuppressWarnings("unused")
public class ImageUtil {

	private ImageUtil(){}
	
	private BufferedImage image = null;

	public void load(File imageFile) throws IOException {
		image = ImageIO.read(imageFile);
	}

	public int getImageWidth() {
		return image.getWidth();
	}

	public int getImageHeight() {
		return image.getHeight();
	}

	//图片裁剪
	public void cutTo(int x,int  y,int tarWidth, int tarHeight) throws FileNotFoundException {
		if (image == null) {
			throw new FileNotFoundException(
					"image file not be load. please execute 'load' function agin.");
		}

		int iSrcWidth = getImageWidth(); // 得到源图宽
		int iSrcHeight = getImageHeight(); // 得到源图长

		// 如果源图片的宽度或高度小于目标图片的宽度或高度，则直接返回出错
		if (iSrcWidth < tarWidth || iSrcHeight < tarHeight) {
			
			throw new RuntimeException("source image size too small.");
		}

		// 先求源图和目标图的尺寸比例
		double dSrcScale = iSrcWidth * 1.0 / iSrcHeight;
		double dDstScale = tarWidth * 1.0 / tarHeight;

		// 先确定剪裁尺寸
		int iDstLeft, iDstTop, iDstWidth, iDstHeight;
		if (dDstScale > dSrcScale) { // 目标图片宽
			iDstWidth = iSrcWidth;
			iDstHeight = (int) (iDstWidth * 1.0 / dDstScale);
		} else { // 目标图片高
			iDstHeight = iSrcHeight;
			iDstWidth = (int) (iDstHeight * dDstScale);
		}
		iDstLeft = (iSrcWidth - iDstWidth) / 2;
		iDstTop = (iSrcHeight - iDstHeight) / 2;

		// 剪裁
		this.image = image
				.getSubimage(x, y, tarWidth, tarHeight);

	}

	/**
	 * 	//图片缩放 不生成新的图片
	 */
	public void zoomTo(int tarWidth, int tarHeight) {
		BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight,
				BufferedImage.TYPE_INT_RGB); // 缩放图像
		Image image = this.image.getScaledInstance(tarWidth, tarHeight,
				Image.SCALE_SMOOTH);
		Graphics g = tagImage.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制目标图
		g.dispose();
		this.image = tagImage;

	}

	/**
	 * 保存
	 * @param fileName
	 * @param formatName
	 * @throws IOException
	 */
	public void save(String fileName, String formatName) throws IOException {
		// 写文件
		FileOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(this.image, formatName, bos);// 输出到bos
			out = new FileOutputStream(fileName);
			out.write(bos.toByteArray()); // 写文件
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * //缩放图片 生成新的图片
	 */
	public static boolean zoomImage(String srcFile, String dstFile, int width,
			int height, String formatName) {
		try {
			ImageUtil zoom = new ImageUtil();
			zoom.load(new File(srcFile));
			zoom.zoomTo(width, height);
			zoom.save(dstFile, formatName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static ImageUtil fromImageFile(File file) throws IOException {
		ImageUtil utils = new ImageUtil();
		utils.load(file);
		return utils;
	}

	/**
	 * 加载图片
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static ImageUtil load(String fileName) throws IOException {
		File file = new File(fileName);
		return fromImageFile(file);
	}
	
	
	
	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg --
	 *            水印文件
	 * @param targetImg --
	 *            目标文件
	 * @param x
	 * @param y
	 */
	public final static void pressImage(String pressImg, String targetImg,
			float x, float y, float alpha) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			//处理透明图
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(src_biao, Math.round(wideth * x), Math.round(height * y), wideth_biao, height_biao, null);
			// /
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg --
	 *            目标图片
	 * @param fontName --
	 *            字体名
	 * @param fontStyle --
	 *            字体样式
	 * @param color --
	 *            字体颜色
	 * @param fontSize --
	 *            字体大小
	 * @param x --
	 *            偏移量
	 * @param y
	 */

	public static void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize, float x,
			float y, float alpha) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// String s="www.qhd.com.cn";
			g.setColor(new Color(color));
			g.setFont(new Font(fontName, fontStyle, fontSize));
			//处理透明图
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawString(pressText, Math.round(wideth * x), Math.round(height * y));
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	/**
	 * 测试方法
	 * @Title: ImageUtils
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param args 
	 * @return 
	 * @throws
	 */
	public static void main(String[] args) {
		/*String p =  "e:/090613221071ed5df7cf2bce72.jpg";
		ImageUtils image , image2;
		try {
			image = ImageUtils.load(p);
			//image.zoomImage(p, "e:/xxx.jpg", 500, 500, "jpg");
			image.zoomTo(500, 500);
			image.cutTo(50,50,100, 100);
			image.save("e:/111.jpg", "jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}