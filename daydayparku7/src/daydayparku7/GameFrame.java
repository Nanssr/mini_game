package daydayparku7;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameFrame extends JFrame {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameFrame(int x,int y,String title) {
		setSize(x,y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		 ImageIcon icon = new ImageIcon("img/ico/logo.ico");
		setIconImage(icon.getImage());// 给窗体设置图标方法
		
	}
	

	 public static void main(String[] args) {
		GameFrame gf = new GameFrame(1200,650,"八神酷跑");
		 GamePanel gp = new GamePanel(gf);	
		 
		 gf.add(gp);
		 gf.setVisible(true);
		 JOptionPane.showMessageDialog(null, "请切换为英文输入法\n技能表：\nW---跳跃\nS---下潜\nD---霸气滑行\nJ---升龙\n"
		 		+ "注意：滑行和升龙会消耗蓝量，升龙会击飞障碍并且不受伤害");
           try {
					gp.start();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		
		

		
	 }
}

		
	


