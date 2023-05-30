package daydayparku7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean dead_player=false;

	BackGrand bg1;
	BackGrand bg2;
	Person P;
	Monitor cm;
	Obstacle feilong;
	Obstacle shizi;
	Obstacle fire;
	Obstacle blood;
	Obstacle energy;
	String root_feilong="img/obstacle_feilong/";
	String root_shizi="img/obstacle_shizi/";
    String root_fire="img/obstacle_fire/";
    String root_blood="img/blood_energy/blood.png";
    String root_energy="img/blood_energy/energy.png";
    Color color = Color.WHITE;
    
	public GamePanel(GameFrame frame) {
		
		P=new Person();
		bg1=new BackGrand(1);
		bg2=new BackGrand(2);
		cm=new Monitor (); 
		feilong=new Obstacle(root_feilong,850,300,6);
		shizi=new Obstacle(root_shizi,550,400,5);
        fire=new Obstacle(root_fire,2400,-650,7);
        blood=new Obstacle(root_blood,800,150);
        energy=new Obstacle(root_energy,600,140);
        feilong.speed= 20;
        shizi.speed=25;
        fire.speed_x=20;
		
		//键盘适配器
          KeyAdapter adapter =new KeyAdapter() {
        		int code;
        		boolean once = true;
			    public void keyPressed(KeyEvent e) {
				if(once) {
//				P.index=0;
				once = false;
				code = e.getKeyCode();
			System.out.println(code); 
		//	System.out.printf("%d,%d,%d,%d",feilong.w,feilong.h,shizi.w,shizi.h); 132,115,170,123,
			//j74;k75;l76;
			    switch(code) {
			    case 87:P.index=0;if(P.jumpcount>0) {
			    	     P.status_jump=true;P.status_run=false;P.status_skill_1=false;P.jump(45);P.w=200;P.h=220;P.jumpcount--;}break;
	   
			    case 83:P.index=0;P.status_down=true;P.status_run=false;P.status_skill_1=false;P.w=340;P.h=340;break;
			    case 68:if(P.energy>=10) {P.index=0;P.status_fly=true;P.status_run=false;P.status_skill_1=false;P.energy-=10;P.w=200;P.h=220;}break;
			    case 74:P.index=0; if(P.energy>=30) {
			    	      P.status_skill_1=true;P.status_run=false; P.energy-=40;P.w=300;P.h=340; }break;
			    case 76:System.out.println(shizi.speed);
			    default:break;
			    
			    }
				
			}
			}
			public void keyReleased(KeyEvent e) {
				once = true;
				
				code = e.getKeyCode();
			    switch(code) {
			    case 83:P.status_down=false;P.status_run=true;P.w=200;P.h=220;break;
			    case 68:P.status_fly=false;P.status_run=true;break;
			    case 87:P.status_jump=false;P.status_run=true;break;
			    default:break;
			    
			    }
				
			}
		};

		
	frame.addKeyListener(adapter);
	MouseMotionListener mouse1 = new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(P.status_dead&&(arg0.getX()<=620&&arg0.getX()>=450)&&(arg0.getY()<=362&&arg0.getY()>=338)) {
				color = Color.BLUE;
			}else {
				color = Color.WHITE;
			}
			
		}
	
	};
	frame.addMouseMotionListener(mouse1);
	MouseListener mouse2 = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
//			450, 300, 170, 25
			System.out.println(arg0.getY());
			if(P.status_dead&&(arg0.getX()<=620&&arg0.getX()>=450)&&(arg0.getY()<=362&&arg0.getY()>=338)) {
				System.out.println(123456);
				P.status_dead = false;
				P.status_run = true;
				P.w = 200;
				P.h = 220;
				P.setBlood_energy();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	frame.addMouseListener(mouse2);
	}
	

	public void start() throws InterruptedException {
		
		while(true) {
		while(!P.status_dead) {
			bg1.move(bg1.speed);
			bg2.move(bg2.speed);
			P.changeImgs();
	   	    P.step();
			feilong.changeImg();
			feilong.move(feilong.speed);
			shizi.changeImg();
			shizi.move(shizi.speed);
			fire.changeImg();
			fire.move(fire.speed_x,0.42*fire.speed_x);
			blood.move(8);
			energy.move(10);
			cm.crash_monitor_start();
			cm.energy_blood_monitor_start();
			change_speed();
			Thread.sleep(70);
			P.score+=1;
			repaint();
		}
		if(P.status_dead) {
		
			P.w=340;
			P.h=350;
	     	
		}
		
		
		
		
		while(P.status_dead) {
			
	
			P.changeImgs();
		
			P.step();
			Thread.sleep(200);
			
			repaint();
		}
		
		}
		
	}
	
   public void paint(Graphics g) {
	   g.drawImage(bg1.img,bg1.x,bg1.y,bg1.w,bg1.h,null);
	   g.drawImage(bg2.img,bg2.x,bg2.y,bg2.w,bg2.h,null);	   
	   g.drawImage(P.img,P.x,P.y,P.w,P.h,null);
	   g.drawImage(feilong.img,feilong.x,feilong.y,feilong.w,feilong.h,null);
	   g.drawImage(shizi.img,shizi.x,shizi.y,shizi.w,shizi.h,null);
	   g.drawImage(fire.img,fire.x,fire.y,fire.w,fire.h,null);
	   g.drawImage(fire.img,fire.x,fire.y,fire.w,fire.h,null);
	   g.drawImage(blood.img,blood.x,blood.y,blood.w,blood.h,null);
	   g.drawImage(energy.img,energy.x,energy.y,energy.w,energy.h,null);
	   P.draw_blood(g);
	   P.draw_energy(g);
	   P.draw_score(g);
//	   P.status_dead = true;
	   if(P.status_dead) {
		   Color c = g.getColor();
		   Font f = getFont();
		   g.setFont(new Font("宋体", Font.BOLD, 15));
		   g.setColor(color);
		   g.fillRect(450, 230, 170, 60);
		   g.fillRect(450, 300, 170, 25);
		   g.setColor(Color.black);
		   g.drawString("您已击败了全国", 480, 260);
		   g.drawString((double)P.score*150/50000+"%的玩家", 480, 280);
		   g.drawString("立即免费复活", 490, 320);
		   g.setColor(Color.BLUE);
		   g.drawLine(450, 300, 450, 325);
		   g.setColor(c);
		   g.setFont(f);
	   }

   }

   public class Monitor {
		int dot_x_player;
		int dot_y_player;
		int dot_x_feilong;
		int dot_y_feilong;
		int dot_x_shizi;
		int dot_y_shizi;
		int dot_x_fire;
		int dot_y_fire;
		int dot_x_blood;
		int dot_y_blood;
		int dot_x_energy;
		int dot_y_energy;
		int r_feilong=40;
		int r_shizi=40;
		int r_fire=30;
		int r_blood=20;
		int r_energy=20;
		int r_player;
		int r_obstacle;
		int distance_P_feilong;
		int distance_P_shizi;
		int distance_P_fire;
		int distance_P_blood;
		int distance_P_energy;
		
		public  void crash_monitor_start() {
			if(P.status_down) {
				dot_x_player=(int)(P.x+0.5*P.w);
				dot_y_player=(int)(P.y+0.8*P.h);
				r_player=60;
			}
			else if(P.status_skill_1) {
				dot_x_player=(int)(P.x+0.5*P.w);;
				dot_y_player=(int)(P.y+0.5*P.h);
				r_player=160;
			}
			else {
				dot_x_player=(int)(P.x+0.5*P.w);;
				dot_y_player=(int)(P.y+0.5*P.h);
				r_player=70;
			}
			dot_x_feilong=feilong.x+(int)(0.5*feilong.w);
			dot_y_feilong=feilong.y+(int)(0.5*feilong.h);
			dot_x_shizi=shizi.x+(int)(0.5*shizi.w);
			dot_y_shizi=shizi.y+(int)(0.5*shizi.h);
			dot_x_fire=fire.x+(int)(0.5*fire.w);
			dot_y_fire=fire.y+(int)(0.5*fire.h);
			dot_x_blood=blood.x+(int)(0.5*blood.w);
			dot_y_blood=blood.y+(int)(0.5*blood.h);
			dot_x_energy=energy.x+(int)(0.5*energy.w);
			dot_y_energy=energy.y+(int)(0.5*energy.h);
			distance_P_feilong=(dot_x_player-dot_x_feilong)*(dot_x_player-dot_x_feilong)+(dot_y_player-dot_y_feilong)*(dot_y_player-dot_y_feilong);
			distance_P_shizi=(dot_x_player-dot_x_shizi)*(dot_x_player-dot_x_shizi)+(dot_y_player-dot_y_shizi)*(dot_y_player-dot_y_shizi);
			distance_P_fire=(dot_x_player-dot_x_fire)*(dot_x_player-dot_x_fire)+(dot_y_player-dot_y_fire)*(dot_y_player-dot_y_fire);
			distance_P_blood=(dot_x_player-dot_x_blood)*(dot_x_player-dot_x_blood)+(dot_y_player-dot_y_blood)*(dot_y_player-dot_y_blood);
			distance_P_energy=(dot_x_player-dot_x_energy)*(dot_x_player-dot_x_energy)+(dot_y_player-dot_y_energy)*(dot_y_player-dot_y_energy);
			if(!P.status_skill_1) {
			if(distance_P_feilong<(r_player+r_feilong)*(r_player+r_feilong)) {
				P.blood-=12;
			}
			if(distance_P_shizi<(r_player+r_shizi)*(r_player+r_shizi)) {
				P.blood-=15;
			}
			if(distance_P_fire<(r_player+r_fire)*(r_player+r_fire)) {
				P.blood-=30;
			}
			if(distance_P_blood<(r_player+r_blood)*(r_player+r_blood)) {
				 P.blood+=80;
				blood.x=4500;
			}
			if(distance_P_energy<(r_player+r_energy)*(r_player+r_energy)) {
				 P.energy+=90;
				energy.x=3600;
			}
			}
			else {
				if(distance_P_feilong<(r_player+r_feilong)*(r_player+r_feilong)) {
					feilong.x=2000;
					P.score+=100;
				}
				if(distance_P_shizi<(r_player+r_shizi)*(r_player+r_shizi)) {
					shizi.x=2400;
					P.score+=100;
				}
				if(distance_P_fire<(r_player+r_fire)*(r_player+r_fire)) {
					fire.x=2400;
					fire.y=-650;
					P.score+=100;
				}
			}
			if(P.blood<=0) {
				P.index=0;
				P.status_dead=true;
				P.status_run=false;
			
			}
		}
		public void energy_blood_monitor_start() {
			if(P.energy<0)
				P.energy=0;
			if(P.energy>200)
				P.energy=200;
			if(P.blood>400)
				P.blood=400;
		}
		
	}
   
   
   public void change_speed() {
	   if(P.score>=P.nextscore&&shizi.speed<=100) {
		   P.nextscore=1.1*P.nextscore;
		   bg1.speed=bg1.speed*1.02;
		   bg2.speed=bg2.speed*1.02;
	        feilong.speed= feilong.speed*1.02;
	        shizi.speed=shizi.speed*1.02;
	        fire.speed=fire.speed_x*1.02;
			
	   }
   }

   
}
