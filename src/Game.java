

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
	private Pictures begin;
	private boolean lose,win;
	private Sound p;
	private double time;
	private double curtime; 
	private char screen;
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
		begin=new Pictures("gator1.jpg",0,0,800,600);
		key=-1;
		fishFlakes= setfishFlakes();
		p= new Sound();
		//p.playmusic("success-fanfare-trumpets-6185");
		lose=true;
		screen='S';

		win=false;
		time=System.currentTimeMillis();
		curtime=0;
	}

	public ArrayList <food> setfishFlakes(){
		

		ArrayList <food> temp = new ArrayList <food>();
		for (int i=0; i<2; i++) {
			for(int j=0; j<5; j++) {
				temp.add(new food(((int)(Math.random()*800+1)),((int)(Math.random()*600+1))));
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
	


	g2d.setColor(Color.YELLOW);
	
	
	screen(g2d);
	twoDgraph.drawImage(back, 0, 0, null);
	}

	public void screen(Graphics g2d) {
		switch(screen) {
		case 'S':
		g2d.drawImage(new ImageIcon("gator1.png").getImage(), begin.getX(), begin.getY(),begin.getW(),begin.getH(), this);
	
			drawStartScreen(g2d);
			
			break;
		
			
				case 'G':
				g2d.setColor(Color.WHITE);
				g2d.drawImage(new ImageIcon("swamp.png").getImage(), bi.getX(), bi.getY(),bi.getW(),bi.getH(), this);
		
			g2d.setFont(new Font("times new roman", Font.BOLD,25));
			g2d.drawImage(new ImageIcon(aligator.getPic()).getImage(),aligator.getX(), aligator.getY(), aligator.getW(), aligator.getH(),this);
			g2d.drawImage(new ImageIcon(fish.getPic()).getImage(),fish.getX(), fish.getY(), fish.getW(),fish.getH(),this);
			for (food flake : fishFlakes) {
				g2d.drawImage((new ImageIcon("fishflakes.png")).getImage(), flake.getX(), flake.getY(),flake.getW(),flake.getH(), this);
				
			}
		
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("times new roman", Font.BOLD,25));
			//g2d.drawString(new DecimalFormat("#0.00").format(currtime),320,30);
			fish.move();
			aligator.bounce();
			}
			
	
			

g2d.drawString(new DecimalFormat("#0.00").format(curtime),20,30);

		if(fish.Collision(aligator))
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
		
	for (int j = 0; j < fishFlakes.size(); j++)
		if(fishFlakes.get(j).Collision(fish)) {
		foodeaten=foodeaten+1;
		fishFlakes.remove(fishFlakes.get(j));
		j--;
		
			if(lose)
			{
			p.playmusic("success-fanfare-trumpets-6185.wav");
			lose=false;


			}
			
		}
		
	else move();
	System.out.println(foodeaten);
	}
	//This line tells the program to draw everything above. If you delete
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
			if (e.getKeyChar()=='p'){
screen='G';
			}
		
		
	}
	public void keyReleased(KeyEvent e) {
		key=e.getKeyCode();
		if (key==38||key==40)
			fish.setDy(0);
		if (key==37||key==39)
			fish.setDx(0);
	}
	public void drawStartScreen(Graphics g2d) {
		//create start screen
		g2d.setFont(new Font("Century", Font.BOLD, 50));
		g2d.setColor(Color.blue);
		g2d.drawString("Welcome to Fish Grab!", 100, 200);
		g2d.drawString("Press P to Start", 100, 400);
		g2d.drawString("Use your arrow keys to control the fish!", 100, 600);
		
	}
	
	
}
