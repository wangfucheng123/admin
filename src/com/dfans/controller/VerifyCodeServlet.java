package com.dfans.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VerifyCodeServlet extends HttpServlet {
  // 验证码图片的宽度。         
    private int width = 60;         
    // 验证码图片的高度。         
    private int height = 20;         
    // 验证码字符个数         
    private int codeCount = 9;         
    private int x = 0;         
    // 字体高度         
    private int fontHeight;         
    private int codeY;         
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',         
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',         
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };         
    /**       
     * 初始化验证图片属性       
     */        
    public void initxuan() throws ServletException {         
        // 从web.xml中获取初始信息         
        // 宽度         
        String strWidth ="150";         
        // 高度         
        String strHeight ="50";         
        // 字符个数         
        String strCodeCount = "4";         
        // 将配置的信息转换成数值         
        try {         
            if (strWidth != null && strWidth.length() != 0) {         
                width = Integer.parseInt(strWidth);         
            }         
            if (strHeight != null && strHeight.length() != 0) {         
                height = Integer.parseInt(strHeight);         
            }         
            if (strCodeCount != null && strCodeCount.length() != 0) {         
                codeCount = Integer.parseInt(strCodeCount);         
            }         
        } catch (NumberFormatException e) {         
        }         
        x = width / (codeCount + 1);         
        fontHeight = height - 2;         
        codeY = height - 4;         
    }   
    @RequestMapping(value="xuan/verifyCode",method=RequestMethod.GET)
    public void service(HttpServletRequest req, HttpServletResponse resp)         
            throws ServletException, java.io.IOException { 
        initxuan();
        // 定义图像buffer         
        BufferedImage buffImg = new BufferedImage(width, height,         
                BufferedImage.TYPE_INT_RGB);         
        Graphics2D g = buffImg.createGraphics();         
        // 创建一个随机数生成器类         
        Random random = new Random();         
        // 将图像填充为白色         
        g.setColor(Color.WHITE);         
        g.fillRect(0, 0, width, height);         
        // 创建字体，字体的大小应该根据图片的高度来定。         
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 20);         
        // 设置字体。         
        g.setFont(font);         
        // 画边框。         
        g.setColor(Color.BLACK);         
        g.drawRect(0, 0, width - 1, height - 1);         
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。         
        g.setColor(Color.BLACK);         
        for (int i = 0; i < 10; i++) {         
            int x = random.nextInt(width);         
            int y = random.nextInt(height);         
            int xl = random.nextInt(12);         
            int yl = random.nextInt(12);         
            g.drawLine(x, y, x + xl, y + yl);         
        }         
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。         
        StringBuffer randomCode = new StringBuffer();         
        int red = 0, green = 0, blue = 0;  
        List<Integer> radom=new ArrayList();
        while(radom.size()!=4)
        {
        	int a=random.nextInt(10);
        	if(a==0)
        	{
        		a=1;
        	}
        	boolean flag=false;
        	for(int i=0;i<radom.size();i++)
        	{
        		if(radom.get(i)==a)
        		{
        			flag=true;
        		}
        	}
        	if(flag)
        	{
        		continue;
        	}
        	radom.add(a);
        }
        for(int k=0;k<radom.size();k++)
        {
        	//System.out.println(radom.get(k));
        }
        // 随机产生codeCount数字的验证码。         
        for (int i = 1; i < 10; i++) {         
            // 得到随机产生的验证码数字。         
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);         
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。         
                     
            // 用随机产生的颜色将验证码绘制到图像中。         
            //g.setColor(Color.BLUE); 
            
            boolean flag=false;
            for(int k=0;k<radom.size();k++)
            {
            	//System.out.println("ooo="+radom.get(k));
            	if(i==(int) radom.get(k))
                {
                	g.setColor(Color.blue);
                	g.drawString(strRand, (i-1)  * 15, 15);         
                // 将产生的四个随机数组合在一起。         
                	randomCode.append(strRand);
                	//radom.remove(k);
                	flag=false;
                	break;
                }
            	else
            	{
            		flag=true;
            	}
            }
            if(flag)
            {
            	g.setColor(Color.gray); 
            	g.drawString(strRand, (i-1)  * 15, 20); 
            }
        }     
        // 将四位数字的验证码保存到Session中。  
        Font font1 = new Font("Microsoft YaHei", Font.PLAIN, 18); 
        g.setFont(font1);   
        g.setColor(Color.gray); 
        g.drawString("请输入        的字符", 0, 40);
        g.setColor(Color.blue); 
        g.drawString("蓝色", 54, 40);
        req.getSession().setAttribute("validateCode", randomCode.toString());
        // 禁止图像缓存。         
        resp.setHeader("Pragma", "no-cache");         
        resp.setHeader("Cache-Control", "no-cache");         
        resp.setDateHeader("Expires", 0);         
        resp.setContentType("image/jpeg");         
        // 将图像输出到Servlet输出流中。         
        ServletOutputStream sos = resp.getOutputStream();         
        ImageIO.write(buffImg, "jpeg", sos);         
        sos.close();         
    }         
}