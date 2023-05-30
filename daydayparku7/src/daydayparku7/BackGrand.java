package daydayparku7;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGrand {
BufferedImage img;
int x;
int y;
int w;
int h;
double speed=10;
public BackGrand(int i) {
	try {
		
//	 img=Tools.getImg("img/background.jpg");
	img=ImageIO.read(new File("img/background.jpg"));
	  w=1520;
	  h=650;
	  x=1520*(i-1);
	  y=0;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


   public void move(double speed) {
	   x=x-(int)speed;
	   if(x<=-1520) {
		   x=1520;
	   }
	   }
   }
