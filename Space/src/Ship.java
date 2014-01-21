
public class Ship {

	double x = 600;
	double y = 300;
	double vx;
	double vy;
	double xpoints [] = new double [4];				
	double ypoints [] = new double [4];	
	int xp [] = new int [4];				
	int yp [] = new int [4];	
	double a = 190;
	double rotation = 3.5;
	double thrust = 0.15;
	double xlaser;
	double ylaser;
	double dvx;
	double dvy;	//direction
	double xedge;
	double yedge;
	
	public Ship(){
		locate();
	}
	
	public void locate(){
		ylaser = (Math.sin(Math.toRadians(a + 270)) * 50);
		xlaser = ylaser / (Math.tan(Math.toRadians(a + 270)));
		
		ypoints [0] = (Math.sin(Math.toRadians(a + 270)) * 40);
		xpoints [0] = ypoints[0] / (Math.tan(Math.toRadians(a + 270)));
		xp [0] = (int) x + (int) xpoints [0];
		yp [0] = (int) y + (int) ypoints [0];
		
		ypoints [1] = (Math.sin(Math.toRadians(a + 130)) * 30);
		xpoints [1] = ypoints[1] / (Math.tan(Math.toRadians(a + 130)));
		xp [1] = (int) x + (int) xpoints [1];
		yp [1] = (int) y + (int) ypoints [1];
		
		ypoints [2] = (Math.sin(Math.toRadians(a + 90)) * 15);
		xpoints [2] = ypoints[2] / (Math.tan(Math.toRadians(a + 90)));
		xp [2] = (int) x + (int) xpoints [2];
		yp [2] = (int) y + (int) ypoints [2];
		
		ypoints [3] = (Math.sin(Math.toRadians(a + 50)) * 30);
		xpoints [3] = ypoints[3] / (Math.tan(Math.toRadians(a + 50)));
		xp [3] = (int) x + (int) xpoints [3];
		yp [3] = (int) y + (int) ypoints [3];

	}
	
	public void thrust(){
		if(vx >= -4 && vx <= 4){
			vx = vx + xpoints[0] / Math.sqrt(xpoints[0] * xpoints[0] + ypoints[0] * ypoints[0]) * thrust;	
		}
		else{
			if(vx < 0){
				vx = - 4;
			}
			else if(vx >0){
				vx = 4;
			}
		}
		if(vy >= -4 && vy <= 4){
			vy = vy + ypoints[0] / Math.sqrt(xpoints[0] * xpoints[0] + ypoints[0] * ypoints[0]) * thrust;
		}
		else{
			if(vy < 0){
				vy = - 4;
			}
			else if(vy >0){
				vy = 4;
			}
		}
	}
	
	public void move(){
		dvx	= ((x + xpoints[0]) - x) / Math.sqrt(((x + xpoints[0]) - x) * ((x + xpoints[0]) - x) + ((y + ypoints[0]) - y) * ((y + ypoints[0]) - y));		
		dvy = ((y + ypoints[0]) - y) / Math.sqrt(((x + xpoints[0]) - x) * ((x + xpoints[0]) - x) + ((y + ypoints[0]) - y) * ((y + ypoints[0]) - y));
		if(x >= 200 && x <= Space.width - 200 && y >= 200 && y <= Space.height - 200){
			x += vx;
			y += vy;
			xedge = 0;
			yedge = 0;
			if(x < 200){
				x = 200;
				xedge = vx;
			}
			if(y < 200){
				y = 200;
				yedge = vy;
			}
			if(x > Space.width - 200){
				x = Space.width - 200;
				xedge = vx;
			}
			if(y > Space.height - 200){
				y = Space.height - 200;
				yedge = vy;
			}
		}
	}
}
