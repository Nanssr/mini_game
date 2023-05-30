package daydayparku7;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Person  extends Thread{

	 BufferedImage img;
		int x=150 ;
		int y=300;
		int w=240;
		int h=250;
        int index=0;
        int blood=400;
        int energy=200;
        int jumpcount=2;
        int  score=0;
        double nextscore=100;
		List<BufferedImage>imgs;
		

List<BufferedImage>imgs_run;
List<BufferedImage>imgs_down;
List<BufferedImage>imgs_jump;
List<BufferedImage>imgs_skill_1;
List<BufferedImage>imgs_fly;
List<BufferedImage>imgs_dead;


double v0=0;//初始速度
double t;//时间
double s;//距离
double g;
boolean status_run=true;
boolean status_skill_1=false;
boolean status_fly=false;
boolean status_jump=false;
boolean status_down=false;
boolean status_dead=false;

   public Person() {
		v0=0;
		t=2;
		s=0;
		g=3.5;
		

	
//
//		try {
//			img=Tools.getImg("../../../img/run_bashen/run_bashen(1).png");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		x=150;
		y=330;
		w=200;
		h=220;
	imgs_run = new ArrayList<BufferedImage>();
	imgs_down = new ArrayList<BufferedImage>();
	imgs_jump = new ArrayList<BufferedImage>();
	imgs_skill_1 = new ArrayList<BufferedImage>();
	imgs_fly = new ArrayList<BufferedImage>();
	imgs_dead = new ArrayList<BufferedImage>();

	for(int i=1;i<=8;i++) {
		try {
//			imgs_run.add(Tools.getImg("img/run_bashen/run_bashen("+i+").png"));
			imgs_run.add(ImageIO.read(new File("img/run_bashen/run_bashen("+i+").png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	for(int i=2;i<=9;i++) {
		try {
//			imgs_down.add(Tools.getImg("img/crouch_bashen/crouch_bashen("+i+").png"));
			imgs_down.add(ImageIO.read(new File("img/crouch_bashen/crouch_bashen("+i+").png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    for(int i=1;i<=12;i++) {
    	try {
//			imgs_jump.add(Tools.getImg("img/jump_bashen/jump_bashen ("+i+").png"));
			imgs_jump.add(ImageIO.read(new File("img/jump_bashen/jump_bashen ("+i+").png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    for(int i=1;i<=18;i++) {
    	try {
//			imgs_skill_1.add(Tools.getImg("img/skill1_bashen/skill1_bashen("+i+").png"));
			imgs_skill_1.add(ImageIO.read(new File("img/skill1_bashen/skill1_bashen("+i+").png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    for(int i=1;i<=6;i++) {
    	try {
//			imgs_fly.add(Tools.getImg("img/fly_bashen/"+i+".png"));
			imgs_fly.add(ImageIO.read(new File("img/fly_bashen/"+i+".png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    for(int i=4;i<=22;i++) {
    	try {
//			imgs_dead.add(Tools.getImg("img/dead_bashen/"+i+".png"));
			imgs_dead.add(ImageIO.read(new File("img/dead_bashen/"+i+".png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   }
public void setBlood_energy() {
	   blood = 400;
	   energy= 200;
   }
   public void changeImgs() {
		if(status_fly&&(!status_dead)) {
       y=-100;
			img=imgs_fly.get(index%imgs_fly.size());
		}

	   if(status_down) {
     		if(index <8)
			img=imgs_down.get(index%imgs_down.size());
			else
			img=imgs_down.get(6);

		}
	   if(status_dead) {

     		if(index <18)
			img=imgs_dead.get(index%imgs_dead.size());
			else {
				img = imgs_dead.get(17);
//			status_run=true;
			}
		}



		if(status_run&&(!status_dead)) {

			img=imgs_run.get(index%imgs_run.size());
		}

		if(status_jump) {				
			if(index <=7) {
				img=imgs_jump.get(index%imgs_jump.size());
                  
			}
				else {
					status_jump=false;
					status_run=true;
				}

		}
		if(status_skill_1) {
		jump(6);
		
			if(index <=13) {
				img=imgs_skill_1.get(index%imgs_skill_1.size());
//				w=230;
//				h=260;
			}
				else {
					status_skill_1=false;
					status_run=true;
                    h=220;
                    w=200;
				}

		}

		
		
		
		index++;
	}
   
   
public void step() {
	s=v0*t;
	y=y-(int)s;
	double v2=v0-g*t;
	v0=v2;
	if(y>=550-h) {
		y=550-h;
        jumpcount=2;
	}
	else if(y<-120)
      y=-120;
	
}
public void jump(int v1) {

	this.v0=v1;

}

public void draw_blood(Graphics g) {
	   Color co=g.getColor();
	   Font f=g.getFont();
	   g.setColor(Color.white);
	   g.fillRect(750, 10, 400, 20);
	   g.setColor(Color.red);
	   g.fillRect(750, 10,blood,20);
	   g.setFont(new Font("黑体", Font.ITALIC, 20));
	   g.drawString("血条:", 700, 28);
	   g.setColor(co);
	   g.setFont(f);


}
public void draw_energy(Graphics g) {
      Color col=g.getColor();
      Font f=g.getFont();
      g.setColor(Color.white);
      g.fillRect(500,10,200,20);
      g.setColor(Color.blue);
      g.fillRect(500, 10,energy,20);
      g.setFont(new Font("黑体", Font.ITALIC, 20));
      g.drawString("蓝条:", 450, 30);
      g.setColor(col);
      g.setFont(f);
      
}
public void draw_score(Graphics g) {
    Color col=g.getColor();
    Font f=g.getFont();
    g.setColor(Color.white);
    g.fillRect(84,10,80,20);
    g.setColor(Color.blue);
//    g.fillRect(80, 10,score,20);
    g.setFont(new Font("黑体", Font.BOLD, 25));
    g.drawString(score+"", 90, 28);
    g.setFont(new Font("黑体", Font.BOLD, 20));
    g.drawString("得分:", 20, 30);
    g.setColor(col);
    g.setFont(f);
    
}




}
