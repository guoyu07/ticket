package com.ticket.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import com.ticket.constants.ContextConstants;

/**
 * 验证码控制器
 * @ClassName: VerificateCodeAction   
 * @Description: 用于生成验证码   
 * @author HiSay  
 * @date Jul 8, 2013 9:38:34 PM   
 *
 */
public class VerificateCodeAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 1L;

	private static int WIDTH = 100;
	private static int HEIGHT = 44;

	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		// 设置浏览器不缓存此图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 创建内存图像并获得其图形上下文
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		// 产生随机的验证码
		char[] rands = generateCheckCode();

		// 产生图像
		drawBackground(g);
		drawRands(g, rands);

		// 结束图像的绘制过程,完成图像
		g.dispose();

		// 将图像输出到客户端
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();

		// 将当前验证码存入到session中
		session.setAttribute(ContextConstants.SCOPE_VERIFICATE_CODE, new String(rands).toLowerCase());
		return null;
	}

	private char[] generateCheckCode() {
		// 定义验证码的字符表
		String chars = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	private void drawRands(Graphics g, char[] rands) {
		g.setColor(Color.decode("#3777C0"));
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 25));
		// 在不同的高度上输出验证码的每个字符
		g.drawString("" + rands[0], 1, 30);
		g.drawString("" + rands[1], 25, 35);
		g.drawString("" + rands[2], 45, 29);
		g.drawString("" + rands[3], 65, 25);
	}

	private void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 随机产生120个干扰点
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}
}
