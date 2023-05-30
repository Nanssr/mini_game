package daydayparku7;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Obstacle extends GamePhoto {
	double speed;
	double speed_x;
	double speed_y;
	
//	public Obstacle() {
//		try {
//			img = Tools.getImg("../../../img/obstacle_feilong/obstacle_feilong(1).png");
//			w=img.getWidth();
//			h=img.getHeight();
//			x=850;
//			y=300;
//			imgs = new ArrayList<BufferedImage>();
//			for(int i=1;i<=6;i++) {
//				imgs.add(Tools.getImg("../../../img/obstacle_feilong/obstacle_feilong("+i+").png"));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public Obstacle(String path,int x,int y) {
		try {
			img = ImageIO.read(new File(path));
			w=img.getWidth();
			h=img.getHeight();
			this.x=x;
		    this.y=y;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Obstacle(String path,int x,int y,int n) {
		try {
//			img = Tools.getImg(path+"1.png");
			img = ImageIO.read(new File(path+"1.png"));
			w=img.getWidth();
			h=img.getHeight();
			this.x=x;
		    this.y=y;
			imgs = new ArrayList<BufferedImage>();
			for(int i=1;i<=n;i++) {
				//imgs.add(Tools.getImg(path+"("+i+").png"));
//				imgs.add(Tools.getImg(path+i+".png"));
				imgs.add(ImageIO.read(new File(path+i+".png")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int index=0;
	public void changeImg(){

		img=imgs.get(index%imgs.size());
		index++;
	}
	
	   public void move(double speed) {
		   x=x-(int)speed;
		   if(x<=-100) {
			   x=1250;
		   }
		   }
	   public void move(double speed_x,double speed_y) {
//		   x=x-(int)speed;
//		   y=(int) (y+0.42*speed);
		   x=x-(int)speed_x;
		   y=(int) (y+speed_y);
		   if(x<=-100&&y>700) {
			   x=2400;
			   y=-650;
		   }
		   }

}
