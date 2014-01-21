
public class Laser {
	
	double sx;	//ship
	double sy;
	double x;
	double y;
	double xend;
	double yend;
	double vx;
	double vy;
	
	public Laser(double ssxx, double ssyy, double xx, double yy, double xxend, double yyend, double vvxx, double vvyy){
		sx = ssxx;
		sy = ssyy;
		x = sx + xx;
		y = sy + yy;
		xend = sx + xxend;
		yend = sy + yyend;
		vx = vvxx * 8;
		vy = vvyy * 8;
	}
	
	public void fire(){
		x += vx;
		y += vy;
		xend += vx;
		yend += vy;
	}
}
