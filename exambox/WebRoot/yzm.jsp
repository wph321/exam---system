<%@ page language="java" import="java.util.*,java.awt.*,java.io.*,javax.imageio.ImageIO,java.awt.image.BufferedImage" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'yzm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
   response.setContentType("image/jpeg");
        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图像，宽度为width，高度为height
        int width = 60, height = 20;
        BufferedImage pic = new BufferedImage(width, height,
                                              BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文环境
        Graphics gc = pic.getGraphics();
        // 设定背景色并进行填充
        gc.setColor(getRandColor(200, 250));
        gc.fillRect(0, 0, width, height);
        //设定图形上下文环境字体
        gc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        //设置干扰线
        Random r = new Random();
        for (int i = 0; i < 200; i++) {
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(15);
            int y2 = r.nextInt(15);
            gc.setColor(getRandColor(160, 200));
            gc.drawLine(x1, y1, x1 + x2, y1 + y2);
        }
        //设置干扰点
    	for (int i = 0; i < 100; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            gc.setColor(getRandColor(120, 240));
            gc.drawOval(x, y, 0, 0);
    }
    	// 随机产生4位数字的验证码
    	String RS = r.nextInt(9000) + 1000 + "";
//    	String RS ="";
//    	for(int i=0;i<4;i++){
//    		int dx=r.nextInt(3);
//    		int num=r.nextInt(26);
//    		if(dx==1){
//    			char  big = (char) (num+'A');
//    			RS+=big;
//    		}
//    		else if(dx==0){
//    			char  smi = (char) (num+'a');
//    			RS+=smi;
//    		}
//    		else{
//    			RS += r.nextInt(10);
//    		}
//    	}
//    	
    	
   //将认证码用drawString函数显示到图像里
       gc.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110),
                              20 + r.nextInt(110)));
        gc.drawString(RS, 10, 16);
   // 释放图形上下文环境
        gc.dispose();
       
	// 输出生成后的验证码图像到页面
        ImageIO.write(pic, "JPEG", response.getOutputStream());
        out.clear();
        out.flush();
        out=pageContext.pushBody();
	// 将认证码RS存入SESSION中共享
	    session.setAttribute("random", RS);
		
     %>
     <%!
        private static Color getRandColor(int min, int max) {  
        Random r = new Random();
        if (min > 255)  
            min = 255;  
        if (max > 255)  
            max = 255;  
        int red = r.nextInt(max - min) + min;  
        int green = r.nextInt(max - min) + min;  
        int blue = r.nextInt(max - min) + min;  
        return new Color(red, green, blue);  
    }  
      %>
  </body>
</html>
