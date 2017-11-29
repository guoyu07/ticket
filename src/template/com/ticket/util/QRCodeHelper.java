package com.ticket.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.snowstore.poseidon.entity.user.Member;

public class QRCodeHelper {

	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;

	public static void main(String[] args) throws Exception {
		try {

			String content = "123456789013";
			String path = "/Users/xiongwei/图片", imgPath = "1_copy.jpg";
			
			encoderQRCode(content,true,path,imgPath);
//			BufferedImage bufferedImage = createImagePath(content,  true);
//			File file1 = new File(path, "二维码.jpg");
//
//			ImageIO.write(bufferedImage, "jpg", file1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 生成指定路径下的二维码
	 * @throws Exception 
	 */
	public static void encoderQRCode(String content,boolean needCompress,String parentPath,String cPath) throws Exception{
		BufferedImage bufferedImage = createImagePath(content,  true);
		File file1 = new File(parentPath,cPath);

		ImageIO.write(bufferedImage, "jpg", file1);
	}

	private String urlPre;

//	@Autowired
//	private GridFsTemplate gridFsTemplate;

	private Image defaultLogo;

//	public void createQrCode(String referralCode, Member member, Image logo) {
//		if (null == logo) {
//			logo = defaultLogo;
//		}
//		try {
//			save(member, createImageInputStream(urlPre + referralCode, logo, true));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 
	 * 保存PDF文件（将文件保存到MongoDB相关信息保存到Oracle）
	 */
//	public void save(Member member, InputStream input) {
//		gridFsTemplate.store(input, member.getCode(), "image/jpg");
//	}

//	private static InputStream createImageInputStream(String content, Image logo, boolean needCompress) throws Exception {
//		BufferedImage bufferedImage = createImage(content, logo, needCompress);
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
//		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//	}

	private static BufferedImage createImagePath(String content, boolean needCompress) throws Exception {
//		File file = new File(imgPath);
//		if (!file.exists()) {
//			System.err.println("" + imgPath + "   该文件不存在！");
//			return null;
//		}
//		Image logo = ImageIO.read(new File(imgPath));
		return createImage(content, needCompress);
	}

	private static BufferedImage createImage(String content, boolean needCompress) throws Exception {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		hints.put(EncodeHintType.CHARACTER_SET, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		// 插入图片
//		insertLogo(image, logo, needCompress);
		return image;
	}

	public static void insertLogo(BufferedImage source, Image logo, boolean needCompress) throws IOException {

		int width = logo.getWidth(null);
		int height = logo.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = logo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			logo = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(logo, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

}
