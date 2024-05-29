	
public class Box {
	
private int x;
private int y;
private int width;
private int height;
private int dx;
private int dy;
private boolean movert;
private boolean movedn;

public Box() {
	
	x=30;
	y=20;
	width=25;
	height=25;
	movert=false;
	movedn=false;
	dx=0;
	dy=0;
}


public Box(int x1, int y1, int w, int h, int dx1, int dy1) {
	
	x=x1;
	y=y1;
	width=w;
	height=h;
	dx=dx1;
	dy=dy1;
	
}

public Box (int w, int h, int dx1, int dy1) {
	
	x=(int)(Math.random()*(750-50+1)+50);
	y=(int)(Math.random()*(550-50+1)+50);
	width=w;
	height=h;
	dx=dx1;
	dy=dy1;

}


public void setDx(int c) {
	dx=c;
}
public void setDy(int c) {
	dy=c;
}
public void bounce() {
	movert=true;
	movedn=true;
	if (movert) {
		x+=dx;
	}
	else {
		x-=dx;
	}
	if(movedn) {
	y+=dy;
	}
	else {
		y-=dy;
	}
	if (x<0)
		movert=true;
	if(x+width>800)
		movert=false;
	
	if(y<0)
		movedn=true;
	if(y+height>600)
		movedn=false;
	}

public void move() {

	if(x+width< 800 && x+width> 0){
		x+=dx;	
	}
		//x=800-width;
	
	if(y+height<600 && y+height>0){
		y+=dy;
	}
		//y=600-height;
}


public int getW() {
	return width;
}
public int getH() {
	return height;
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public boolean Collision(Pictures b) {
	return getX()+getW()>=b.getX()&&getX()+getW()<=b.getX()+b.getW()&&
			getY()+getH()>=b.getY() &&getY()<=b.getY()+b.getH();
}

}