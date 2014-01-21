import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Space extends JPanel implements ActionListener{
	
	//CONTROLS:
	
	//LEFT = W
	//RIGHT = D
	//THRUST = W
	//FIRE = SPACE
	//RESET = SPACE
	
	public static final int width = 1356;
	public static final int height = 762;
	
	public static void main(String a []){
		
		JFrame j = new JFrame();
		Space s = new Space();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(width, height);
		j.add(s);							//have to add before other stuff for some reason
		j.setLocationRelativeTo(null);
		j.setResizable(false);
		j.setVisible(true);
	}
	
	Timer t;
	Ship s;
	Laser l;
	Meteor m;
	boolean right = false;
	boolean left = false;
	boolean thrust = false;
	boolean detect = false;
	ArrayList<Laser> lasers = new ArrayList<Laser>();
	ArrayList<Meteor> meteors = new ArrayList<Meteor>();
	int oob = 50;
	boolean gameover = false;
	int count = 10;
	int score = 0;
	
	public Space(){
		s = new Ship();
		t = new Timer(10, this);
		t.start();
		setFocusable(true);
		addKeyListener(new KL());
		for(int i = 0; i < 6; i++){
			m = new Meteor();
			meteors.add(m);
		}
	}

	private class KL extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_D){
				right = true;
			}
			if(key == KeyEvent.VK_A){
				left = true;
			}
			if(key == KeyEvent.VK_W){
				thrust = true;
			}
			if(key == KeyEvent.VK_SPACE){
				if(!gameover){
					fire();
				}
				else{
					meteors.clear();
					for(int i = 0; i < 6; i++){
						m = new Meteor();
						meteors.add(m);
					}
					s.x = 600;
					s.y = 300;
					s.vx = 0;
					s.vy = 0;
					gameover = false;
					score = 0;
				}
			}
		}
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_D){
				right = false;
			}
			if(key == KeyEvent.VK_A){
				left = false;
			}
			if(key == KeyEvent.VK_W){
				thrust = false;
			}
			if(key == KeyEvent.VK_SPACE){
				count = 10;
			}
		}
	}
	
	public void fire(){
		count++;
		if(count >= 10){
			l = new Laser(s.x, s.y, s.xlaser, s.ylaser, s.xpoints[0], s.ypoints[0], s.dvx, s.dvy);
			lasers.add(l);
			count = 0;
		}
	}
	
	public void collision(){
		for(int j = 0; j < meteors.size(); j++){
			if(Math.sqrt((s.x - meteors.get(j).x) * (s.x - meteors.get(j).x) + (s.y - meteors.get(j).y) * (s.y - meteors.get(j).y)) <= meteors.get(j).r){
				gameover = true;
				s.x = 600;
				s.y = 300;
				s.vx = 0;
				s.vy = 0;
			}
			for(int i = 0; i < lasers.size(); i++){
				if(Math.sqrt((lasers.get(i).x - meteors.get(j).x) * (lasers.get(i).x - meteors.get(j).x) + (lasers.get(i).y - meteors.get(j).y) * (lasers.get(i).y - meteors.get(j).y)) <= meteors.get(j).r){
					if(meteors.get(j).r > 50 / 2){
						score += 10;
						m = new Meteor(meteors.get(j).x - (meteors.get(j).r / 2), meteors.get(j).y - (meteors.get(j).r / 2), meteors.get(j).r / 2, s.a + 45);
						meteors.add(m);
						m = new Meteor(meteors.get(j).x + (meteors.get(j).r / 2), meteors.get(j).y - (meteors.get(j).r / 2), meteors.get(j).r / 2, s.a + 135);
						meteors.add(m);
						m = new Meteor(meteors.get(j).x - (meteors.get(j).r / 2), meteors.get(j).y + (meteors.get(j).r / 2), meteors.get(j).r / 2, s.a + 225);
						meteors.add(m);
						m = new Meteor(meteors.get(j).x + (meteors.get(j).r / 2), meteors.get(j).y + (meteors.get(j).r / 2), meteors.get(j).r / 2, s.a + 315);
						meteors.add(m);
					}	
					else{
						score += 20;
					}
					lasers.remove(i);
					meteors.remove(j);
					
				}
			}
		}
	}
	
	public void outofbounds(){
		for(int i = 0; i < meteors.size(); i++){
			if(meteors.get(i).x < - oob || meteors.get(i).x > width + oob || meteors.get(i).y < - oob || meteors.get(i).y > height + oob){
				meteors.remove(i);
				m = new Meteor();
				meteors.add(m);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		for(int i = 0; i < lasers.size(); i++){
			lasers.get(i).fire();
		}
		for(int i = 0; i < meteors.size(); i++){
			meteors.get(i).move(s.xedge, s.yedge);
		}
		s.locate();
		if(right){
			s.a += s.rotation;
		}
		if(left){
			s.a -= s.rotation;
		}
		if(thrust){
			s.thrust();
		}
		s.move();
		collision();
		outofbounds();
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		setBackground(Color.BLACK);
		g.setColor(Color.GREEN);
		if(!gameover){
			g.drawPolygon(s.xp, s.yp, 4);
		}
//		g.fillOval((int) s.x,(int) s.y, 2, 2);
		for(int i = 0; i < lasers.size(); i++){
			g.drawLine((int) lasers.get(i).x, (int) lasers.get(i).y, (int) lasers.get(i).xend, (int) lasers.get(i).yend);
		}
		for(int i = 0; i < meteors.size(); i++){
//			g.drawPolygon(m.xp, m.yp, 8);
			g.drawOval((int) (meteors.get(i).x - meteors.get(i).r), (int) (meteors.get(i).y - meteors.get(i).r), meteors.get(i).r * 2, meteors.get(i).r * 2);
//			g.fillOval((int) m.x,(int) m.y, 2, 2);
		}
		g.drawString("score: " + score, 1250, 40);
	}
}
