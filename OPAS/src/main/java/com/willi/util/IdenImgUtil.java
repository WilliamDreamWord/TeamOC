package com.willi.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//创建一个工具类用于生成验证码图片
public class IdenImgUtil extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 120;
        int hight = 30;
        //1.绘制一张内存上的图片
        BufferedImage bufferedImage = new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
        //2.图片绘制背景颜色 --通过绘图对象
        Graphics graphics = bufferedImage.getGraphics();   //得到图对象 - 画笔
        //绘制任何一个图像前都必须先指定一个颜色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, hight);
        //3.绘制边框
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width - 1, hight - 1);
        //4。四个随机数字
        Graphics2D graphics2D = (Graphics2D) graphics;
        //设置输出字体
        graphics2D.setFont(new Font("宋体", Font.BOLD, 18));
        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();   //生成随机数
        //为了将验证码保存到session
        StringBuffer buffer = new StringBuffer();
        //定义x坐标
        int x = 10;
        for (int i = 0; i < 4; i++) {
            //随机颜色
            graphics2D.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //旋转-30 - 30度
            int jiaodu = random.nextInt(60) - 30;
            //转化成弧度制
            double theda = jiaodu * Math.PI / 180;
            //生成一个随机数
            int index = random.nextInt(words.length());   //生成随机数0-length-1
            //获得字母数字
            char c = words.charAt(index);
            //将生成汉字加入到buffer
            buffer.append(c);
            //将c输出到图片
            graphics2D.rotate(theda, x, 20);
            graphics2D.drawString(String.valueOf(c), x, 20);
            graphics2D.rotate(-theda, x, 20);
            x += 30;
        }

        //将验证码保存到session
        request.getSession().setAttribute("checkCode", buffer.toString());

        //5.绘制干扰线
        graphics2D.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(hight);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + y1, x2 + y2);
        }
        //将上面的图片输出到浏览器中
        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }

    private Color getRandColor(int fc, int bc) {
        //取随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


