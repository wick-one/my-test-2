package com.zzq.web.servlet;

import com.sun.webkit.dom.RGBColorImpl;
import sun.plugin.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建rgb图片
        int width = 109;
        int height = 30;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取图像的graphics
        Graphics graphics = img.getGraphics();
        //给图片填充背景色
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, width, height);
        //绘制随机线条
        drawLineRnd(graphics);
        drawLineRnd(graphics);
        drawLineRnd(graphics);
        drawLineRnd(graphics);
        //绘制随机算术运算并返回结果
        int vCode = drawCalcRnd(graphics);

        //将结果存入session
        req.getSession().setAttribute("vCode", vCode + "");

        //输出图片对象ImageIO--图像、格式、响应的输出流
        ImageIO.write(img, "jpg", resp.getOutputStream());
    }

    //绘制算式
    protected int drawCalcRnd(Graphics graphics) {
        Random rnd = new Random();
        int sum;
        //随机获取两个数
        int numA = rnd.nextInt(100);
        int numB = rnd.nextInt(100);
        //随机获取索引为0或1的运算符
        char[] calcs = {'+', '-'};
        char calc = calcs[rnd.nextInt(2)];
        //计算
        if (calc == '+') {
            sum = numA + numB;
        } else {
            sum = numA - numB;
        }
        //绘制表达式
        String expr = "" + numA + calc + numB + '=' + '?';
        //随机颜色
        graphics.setColor(new Color(
                rnd.nextInt(244),
                rnd.nextInt(212),
                rnd.nextInt(223)
        ));
        //设置字体格式
        graphics.setFont(new Font("微软雅黑", Font.ITALIC, 16));
        graphics.drawString(expr, 20, 20);
        return sum;
    }

    //在图片中随机绘制线条
    protected void drawLineRnd(Graphics graphics) {
        Random rnd = new Random();
        //随机颜色
        graphics.setColor(new Color(
                rnd.nextInt(255),
                rnd.nextInt(255),
                rnd.nextInt(255)
        ));
        //随机位置
        graphics.drawLine(
                rnd.nextInt(100),
                rnd.nextInt(40),
                rnd.nextInt(100),
                rnd.nextInt(40)
        );
    }
}
