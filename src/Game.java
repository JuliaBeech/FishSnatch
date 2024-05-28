

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.*;

//testttt
public class Game extends JPanel implements Runnable,KeyListener{
	private BufferedImage back;
	
	private int key;
	private Box r1;
	private Box r2;
	private Pictures aligator;
	private Pictures fish;
	private Pictures bi;
	private boolean lose,win;
	private Sound p;
	private double time;
	private double curtime; 
	private int foodeaten;
	private ArrayList <food> fishFlakes;
	
	public Game() {
		back=null;
		new Thread(this).start();
		this.addKeyListener(this);
		r1=new Box();
		r2=new Box(400, 300, 70, 90, 2, 1);
		bi = new Pictures("swamp.jpg",0,0,800,600);
		aligator= new Pictures("alligator.png",700,100,100,100);
		fish= new Pictures("fish.png",100,100,2,2,100,100,false,false);
		key=-1;
		fishFlakes= setfishFlakes();
		p= new Sound();
		//p.playmusic("success-fanfare-trumpets-6185");
		lose=true;
		win=false;
		time=System.currentTimeMillis();
		curtime=0;
	}

	public ArrayList <food> setfishFlakes(){
		

		ArrayList <food> temp = new ArrayList <food>();
		int y=((int)(Math.random()*600+1));
		int x=((int)(Math.random()*800+1));
		for (int i=0; i<2; i++) {
			for(int j=0; j<5; j++) {
				temp.add(new food(x,y));
			}
		}
		return temp;
		
	}
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}
	}
	public void paint (Graphics g)
	{
		Graphics2D twoDgraph = (Graphics2D)g;
	//take a snap shop of the current screen and same it as an image
	//that is the exact same width and height as the current screen
		if (back==null) {
			back =(BufferedImage) (createImage(getWidth(), getHeight()));
		}
	//create a graphics reference to the back ground image
	//we will draw all changes on the background image
		Graphics g2d = back.createGraphics();
	//this clears the old image, like an EtchASketch. If you see the old

		g2d.clearRect(0, 0, getSize().width, getSize().height);
		
		//START CODING GRAPHICS HERE
		g2d.drawImage(new ImageIcon(bi.getPic()).getImage(),bi.getX(),bi.getY(),bi.getW(),bi.getH(),this);
		

	g2d.drawImage(new ImageIcon(aligator.getPic()).getImage(),aligator.getX(), aligator.getY(), aligator.getW(), aligator.getH(),this);
	g2d.drawImage(new ImageIcon(fish.getPic()).getImage(),fish.getX(), fish.getY(), fish.getW(),fish.getH(),this);
	g2d.setColor(Color.YELLOW);
	g2d.drawImage(new ImageIcon("fishflakes.png").getImage(), fishFlakes.getX(),fishFlakes.getY(),fishFlakes.getW(),fishFlakes.getH(), this);
	g2d.setFont(new Font("chiller", Font.BOLD,54));


g2d.drawString(new DecimalFormat("#0.00").format(curtime),20,30);

		if(aligator.Collision(fish))
	{
		g2d.setFont(new Font("chiller", Font.BOLD,54));
		g2d.drawString("GAMEOVER,", 300,300);
		if(lose)
			{
			p.playmusic("violin-lose-1-175615.wav");
			lose=false;
			curtime=(System.currentTimeMillis()-time)/1000;

			}
	}
		
		
	else if(win) {
		g2d.setFont(new Font("chiller", Font.BOLD,54));
		g2d.drawString("You won!,", 300,300);



	}
		

	else 
		
		{
		curtime=(System.currentTimeMillis()-time)/1000;

		move();
		
	
	if(fishflakes.Collision(fish)) {
	foodeaten=foodeaten+1;
	fishflakes.remove;
	System.out.println(foodeaten);
		if(lose)
		{
		p.playmusic("success-fanfare-trumpets-6185.wav");
		lose=false;


		}
		
	}
	else move();

		}
	//This line tells the program to draw everything above. If you delete
		twoDgraph.drawImage(back, 0, 0, null);
	}
	public void move() {
		aligator.bounce();
	fish.move();
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		key=e.getKeyCode();
		System.out.println(key);
		if (key==38)
			fish.setDy(-4);
		if (key==40)
			fish.setDy(2);
		if (key==39)
			fish.setDx(2);
		if (key==37)
			fish.setDx(-2);
		
		
	}
	public void keyReleased(KeyEvent e) {
		key=e.getKeyCode();
		if (key==38||key==40)
			fish.setDy(0);
		if (key==37||key==39)
			fish.setDx(0);
	}
	
	
}
