import java.util.Random;
import java.util.ArrayList;

public class Meteor {
	
	Random rand;
	int r;		
	double x;
	double y;
	double vx;
	double vy;
	int l;
	double a;

	public Meteor(double xx, double yy, int rr, double aa){
		x = xx;
		y = yy;
		r = rr;
		a = aa;
		vy = Math.sin(Math.toRadians(a));
		vx = vy / Math.tan(Math.toRadians(a));
		
	}
	
	public Meteor(){
		rand = new Random();
		l = rand.nextInt(4);
		r = 50;
		if(l == 0){
			x = rand.nextInt(Space.width - 400) + 200;
			y = - r;
			a = rand.nextInt(140) + 20;
			vy = Math.sin(Math.toRadians(a));
			vx = vy / Math.tan(Math.toRadians(a));
		}
		else if(l == 1){
			x = rand.nextInt(Space.width - 400) + 200;
			y = Space.height + r;
			a = rand.nextInt(140) + 200;
			vy = Math.sin(Math.toRadians(a));
			vx = vy / Math.tan(Math.toRadians(a));
		}
		else if(l == 2){
			x = - r;
			y = rand.nextInt(Space.height - 400) + 200;
			a = rand.nextInt(140) + 290;
			vy = Math.sin(Math.toRadians(a));
			vx = vy / Math.tan(Math.toRadians(a));
		}
		else if(l == 3){
			x = Space.width + r;
			y = rand.nextInt(Space.height - 400) + 200;
			a = rand.nextInt(140) + 110;
			vy = Math.sin(Math.toRadians(a));
			vx = vy / Math.tan(Math.toRadians(a));
		}
//		m = 1;
//		m = rand.nextInt(3);
//		if(m == 1){
//			one();
//		}
	}
	
	public void move(double xedge, double yedge){
		x = x + vx - xedge;
		y = y + vy - yedge;
//		if(m == 1){
//			one();
//		}
	}
	
//	public void one(){
//		
//		ypoints[0] = Math.sin(Math.toRadians(45)) * 50;
//		xpoints[0] = ypoints[0] / Math.tan(Math.toRadians(45));
//		xp[0] = (int) x + (int) xpoints [0];
//		yp[0] = (int) y + (int) ypoints [0];
//		
//		ypoints[1] = Math.sin(Math.toRadians(90)) * 50;
//		xpoints[1] = ypoints[1] / Math.tan(Math.toRadians(90));
//		xp[1] = (int) x + (int) xpoints [1];
//		yp[1] = (int) y + (int) ypoints [1];
//		
//		ypoints[2] = Math.sin(Math.toRadians(135)) * 50;
//		xpoints[2] = ypoints[2] / Math.tan(Math.toRadians(135));
//		xp[2] = (int) x + (int) xpoints [2];
//		yp[2] = (int) y + (int) ypoints [2];
//		
//		ypoints[3] = Math.sin(Math.toRadians(180)) * 50;
//		xpoints[3] = ypoints[3] / Math.tan(Math.toRadians(180));
//		xp[3] = (int) x + (int) xpoints [3];
//		yp[3] = (int) y + (int) ypoints [3];
//		
//		ypoints[4] = Math.sin(Math.toRadians(225)) * 50;
//		xpoints[4] = ypoints[4] / Math.tan(Math.toRadians(225));
//		xp[4] = (int) x + (int) xpoints [4];
//		yp[4] = (int) y + (int) ypoints [4];
//		
//		ypoints[5] = Math.sin(Math.toRadians(270)) * 50;
//		xpoints[5] = ypoints[5] / Math.tan(Math.toRadians(270));
//		xp[5] = (int) x + (int) xpoints [5];
//		yp[5] = (int) y + (int) ypoints [5];
//		
//		ypoints[6] = Math.sin(Math.toRadians(315)) * 50;
//		xpoints[6] = ypoints[6] / Math.tan(Math.toRadians(315));
//		xp[6] = (int) x + (int) xpoints [6];
//		yp[6] = (int) y + (int) ypoints [6];
//		
//		ypoints[7] = Math.sin(Math.toRadians(360)) * 50;
//		xpoints[7] = ypoints[7] / Math.tan(Math.toRadians(360));
//		xp[7] = (int) x + (int) xpoints [7];
//		yp[7] = (int) y + (int) ypoints [7];
//	}
}
