package bpmis.pxc.system.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码图片
 * @author panxiaochao
 * @ClassName SafeCodeImageServlet
 * @Description TODO
 * @date 2013-8-10
 */
public class SafeCodeImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final char[] dictionary = { '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	/*
	 * '1','l','0','O','o','#','@','$','%','&','(',')','|','/','*'//暂时不用特殊字符(包括：数字1
	 * ,0；字母：l,o,O) ,'^','!','~','\\'
	 */
	};
	static Random random = new Random();

	// 产生随即的字体
	/**
	 * new Font(String 字体，int 风格，int 字号);
	 */
	static Font getFont() {
		// GraphicsEnvironment gg =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();// 所有字体本体系列名称的数组
		// String fontName[] = gg.getAvailableFontFamilyNames();
		String fontName[] = { "Ravie", "Antique Olive Compact", "Forte",
				"Wide Latin", "Gill Sans Ultra Bold", "Garamond", "Gulim",
				"Impact", "Trebuchet MS", "Serif", "Tahoma", "Times New Roman",
				"Verdana", "Microsoft Sans Serif" };
		int fontStyle[] = { Font.BOLD, Font.CENTER_BASELINE,
				Font.CENTER_BASELINE, Font.ITALIC, Font.PLAIN,
				Font.ROMAN_BASELINE, Font.TRUETYPE_FONT };
		int fontSize[] = { 20, 21, 22, 23, 24, 25, 26, 27 };
		Random random = new Random();
		Font font[] = new Font[6];
		for (int i = 0; i < font.length; i++) {
			// System.out.println(fontName[random.nextInt(fontName.length)] +
			// "-"
			// + fontStyle[random.nextInt(fontStyle.length)] + "-"
			// + fontSize[random.nextInt(fontSize.length)]);
			font[i] = new Font(fontName[random.nextInt(fontName.length)],
					fontStyle[random.nextInt(fontStyle.length)], 20);
		}
		int randomInt = random.nextInt(6);
		return font[randomInt];
	}

	/**
	 * 产生n[4,4+]个随机数
	 * 
	 * @param n
	 * @return
	 */
	static String getRandomString(int n) {
		StringBuffer buffer = new StringBuffer();
		if (n < 4) {
			n = 4;
		}
		for (int i = 0; i < n; i++) {
			buffer.append(dictionary[random.nextInt(dictionary.length)]);
		}
		return buffer.toString();
	}

	/**
	 * 随机颜色
	 * 
	 * @return
	 */
	static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random
				.nextInt(255));
	}

	/**
	 * 颜色反色
	 * 
	 * @param c
	 * @return
	 */
	static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c
				.getBlue());
	}

	/**
	 * 产生0--num的随机数,不包括num
	 * 
	 * @param num
	 *            数字
	 * @return int 随机数字
	 */
	public static int num(int num) {
		return random.nextInt(num);
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @return Color 随机颜色
	 */
	protected Color color(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + num(bc - fc);
		int g = fc + num(bc - fc);
		int b = fc + num(bc - fc);
		return new Color(r, g, b);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置响应头 Content-type类型
		response.setContentType("image/jpeg");
		// 以下三句是用于设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);

		OutputStream os = response.getOutputStream();
		String randomString = getRandomString(5);
		request.getSession(true).setAttribute("code", randomString);

		int width = 80; // 验证码图片宽度
		int height = 30; // 验证码图片高度

		Color color = getRandomColor();

		Color reverse = getReverseColor(color);

		// 创建一个彩图
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 绘图对象
		Graphics2D g = image.createGraphics();
		g.setFont(getFont());

		// g.setColor(color); //背景色
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(randomString, 5, 20);

		// 绘制最多100个噪音点
		for (int i = 0, n = num(15); i < n; i++) {
			color = color(150, 250);
			g.setColor(color);
			g.drawRect(num(width), num(height), 1, 1); // 这是点
			g.drawOval(num(width), num(height), 3, 3);// 这是蛋蛋
			// color = null;
		}
		g.dispose();
		//System.out.println(randomString);
		ImageIO.write(image, "png", os);
		// os.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}