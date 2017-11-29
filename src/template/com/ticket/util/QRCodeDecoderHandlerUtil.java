package com.ticket.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.mchange.util.Base64Encoder;

import jp.sourceforge.qrcode.QRCodeDecoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class QRCodeDecoderHandlerUtil {
	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String decoderQRCode(InputStream input) throws Exception {
		 MultiFormatReader formatReader = new MultiFormatReader();
		
		 BufferedImage image = ImageIO.read(input);
		 LuminanceSource source = new BufferedImageLuminanceSource(image);
		 Binarizer binarizer = new HybridBinarizer(source);
		 BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		 
		 @SuppressWarnings("rawtypes")
		 Map hints = new HashMap();
		 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		 Result result = formatReader.decode(binaryBitmap, hints);

		return result.toString();
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public static String decoderQRCode(String imgPath) throws Exception {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;

		bufImg = ImageIO.read(imageFile);
		QRCodeDecoder decoder = new QRCodeDecoder();
		content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)),
				"utf-8");

		return content;
	}

	/**
	 * 解析二维码
	 * 
	 * @param imgStr
	 *            图片的Base64信息
	 * @return
	 */
	public static String decoderQRCodeForBase64(String imgStr) throws Exception {
		if (imgStr == null) {
			return "";
		}
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		InputStream input = new ByteArrayInputStream(b);

		String content = decoderQRCode(input);

		return content;

	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String((new BASE64Encoder()).encode(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) {
		//
		if (imgStr == null) // 图像数据为空
			return false;

		try {
			// Base64解码
			byte[] b = (new BASE64Decoder()).decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片

			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		String imgFile = "C:/Users/Administrator/Desktop/2.png";// 待处理的图片
		String imgbese = getImgStr(imgFile);
		System.out.println(imgbese.length());
		System.out.println(imgbese);
		String imgFilePath = "C:/Users/Administrator/Desktop/222.png";// 新生成的图片
		generateImage(imgbese, imgFilePath);
	}
	

}
