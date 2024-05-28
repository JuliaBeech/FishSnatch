public class Pictures {
	private String pic;
	private int x;
	private int y;
	private int dx;
	private int dy;
 private int width;
 private int height;
 private boolean movert;
 private boolean movedn;
	public Pictures() {
		pic="";
		x=0;
		y=0;
		dx=0;
		dy=0;
		width=0;
		height=0;
		movert=false;
		movedn=false;
		
	}
	public Pictures(String s,int x1, int y1,int w1,int h1) {
		pic=s;
		x=x1;
		y=y1;
		dx=0;
		dy=0;
		width=w1;
		height=h1;
		movert=false;
		movedn=false;
		
		
	}
	public void setDx(int c) {
		dx=c;
	}
	public void setDy(int c) {
		dy=c;
	}
	
	public Pictures(String s ,int x1, int y1, int dx1, int dy1, int w, int h, boolean r, boolean d) {
		pic=s;
		x=x1;
		y=y1;
		dx=dx1;
		dy=dy1;
		height=h;
		width=w;
		movert=r;
		movedn=d;
	}
	public String getPic() {
		return pic;
	}
	public int getX() {
		return x;
		
	}
	public int getY() {
		return y;
		
	}
	public int getDX() {
		return dx;
		
	}
	public int getDY() {
		return dy;
		
	}
	
	public int getW() {
		return width;
		
	}
	public int getH() {
		return height;
		

	}
	public void bounce() {
		if(movert) {
			x+=dx;
		}
		else {
			x-=dx;
		}
		if (movedn){
			y+=dy;
		}
		else {
			y-=dy;
		}
		if(x<0) {
			movert=true;
			
		}
		if(x+width>800)	{
			movert=false;
		}
		
		if(y<0) {
			movedn=true;
			
		}
		if(y+height>600)	{
			movedn=false;
		}
		}
public Pictures (int w, int h) {
		
		x=(int)(Math.random()*(750-50+1)+50);
		y=(int)(Math.random()*(550-50+1)+50);
		width=w;
		height=h;
	
		movert=true;
		movedn=true;
	}


 public void move() {
	if (x+dx >= 800 || x<0)
	dx*=-1;
	if ((y+dy >= 600) || (y+dy<=0))
	dy*=-1;
y+=dy;
 x += dx;	 
 
 
 
}
 public boolean Collision(Pictures b) {
		return getX()+getW()>=b.getX()&&getX()+getW()<=b.getX()+b.getW()&&
				getY()+getH()>=b.getY() &&getY()<=b.getY()+b.getH();
	}
	 
	}


