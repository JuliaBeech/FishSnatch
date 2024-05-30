
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.*;

public class Game extends JPanel implements Runnable, KeyListener {
	private BufferedImage back;

	private int key;
	private Box r1;
	private Box r2;
	private Pictures aligator;
	private Pictures fish;
	private Pictures bi;
	private Pictures level3;
	private Pictures begin;
	private boolean lose, win,lostscreen;
	private Sound p;
	private double time;
	private double curtime;
	private int screen;
	private int foodeaten;
	private ArrayList<food> fishFlakes;

	private boolean gatormove;

	public Game() {
		back = null;
		new Thread(this).start();
		this.addKeyListener(this);
		r1 = new Box();
		r2 = new Box(400, 300, 70, 90, 2, 1);
		bi = new Pictures("swamp.jpg", 0, 0, 800, 600);
		aligator = new Pictures("alligator.png", 300, 600, 100, 100);
		fish = new Pictures("fish.png", 100, 100, 50, 50, 1.5, 1.5);
		begin = new Pictures("gator1.jpg", 0, 0, 800, 600);
		level3 = new Pictures("lakefish.jpg", 0, 0, 800, 600);
		key = -1;
		fishFlakes = setfishFlakes();
		p = new Sound();
		// p.playmusic("success-fanfare-trumpets-6185");
		lose = true;
		screen = 1;

		win = false;
		time = System.currentTimeMillis();
		curtime = 0;
		gatormove = false;
		lostscreen=false;
	}

	public ArrayList<food> setfishFlakes() {

		ArrayList<food> temp = new ArrayList<food>();

		for (int i = 35; i > 0; i--) {

			temp.add(new food(((int) (Math.random() * 801)), ((int) (Math.random() * 601))));

		}
		return temp;

	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}

	public void paint(Graphics g) {
		Graphics2D twoDgraph = (Graphics2D) g;
		// take a snap shop of the current screen and same it as an image
		// that is the exact same width and height as the current screen
		if (back == null) {
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		}
		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics g2d = back.createGraphics();
		// this clears the old image, like an EtchASketch. If you see the old

		g2d.clearRect(0, 0, getSize().width, getSize().height);

		// START CODING GRAPHICS HERE

		g2d.setColor(Color.YELLOW);

		screen(g2d);
		twoDgraph.drawImage(back, 0, 0, null);
	}

	public void lose(Graphics g2d){
		g2d.setFont(new Font("chiller", Font.BOLD, 54));
		g2d.drawString("GAMEOVER", 300, 300);
		//screen=6;
		lostscreen=true;
		if (lose) {
			p.playmusic("violin-lose-1-175615.wav");
			lose = false;
			curtime = (System.currentTimeMillis() - time) / 1000;

		}
	}
	public void screen(Graphics g2d) {
		if (fish.Collision(aligator) ||lostscreen || curtime>45) {
		lose(g2d);
		}
		else{
		switch (screen) {
			case 1:
				g2d.drawImage(new ImageIcon("gator1.png").getImage(), begin.getX(), begin.getY(), begin.getW(),
						begin.getH(), this);

				drawStartScreen(g2d);

				break;

			case 2:
				g2d.setColor(Color.WHITE);
				g2d.drawImage(new ImageIcon("swamp.jpg").getImage(), bi.getX(), bi.getY(), bi.getW(), bi.getH(), this);
				g2d.drawString("Welcome to the Swamp! Get five fish food to move on!",200,100);
				gatormove = true;
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));
				g2d.drawImage(new ImageIcon(aligator.getPic()).getImage(), aligator.getX(), aligator.getY(),
						aligator.getW(), aligator.getH(), this);
				g2d.drawImage(new ImageIcon(fish.getPic()).getImage(), fish.getX(), fish.getY(), fish.getW(),
						fish.getH(), this);
				for (food flake : fishFlakes) {
					g2d.drawImage(new ImageIcon("fishflakes.png").getImage(), flake.getX(), flake.getY(), flake.getW(),
							flake.getH(), this);

				}

				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));
				 g2d.drawString("You've eaten " +foodeaten,320,30);
				// fish.move();
				// aligator.move();
				break;
			case 3:
				// case 3 stuff
				g2d.drawImage(new ImageIcon(fish.getPic()).getImage(), bi.getX(), bi.getY(), bi.getW(), bi.getH(),
						this);

				g2d.setColor(Color.WHITE);
				gatormove = true;
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));
				g2d.drawImage(new ImageIcon(aligator.getPic()).getImage(), aligator.getX(), aligator.getY(),
						aligator.getW(), aligator.getH(), this);
				g2d.drawImage(new ImageIcon(fish.getPic()).getImage(), fish.getX(), fish.getY(), fish.getW(),
						fish.getH(), this);
				for (food flake : fishFlakes) {
					g2d.drawImage(new ImageIcon("fishflakes.png").getImage(), flake.getX(), flake.getY(), flake.getW(),
							flake.getH(), this);
							g2d.drawString("Try to catch 10 more fish!!", 300, 300);

				}

				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));

				break;
			case 4:
				g2d.drawImage(new ImageIcon(level3.getPic()).getImage(), level3.getX(), level3.getY(), level3.getW(),
						level3.getH(), this);

				g2d.setColor(Color.WHITE);
				gatormove = true;
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));
				g2d.drawImage(new ImageIcon(aligator.getPic()).getImage(), aligator.getX(), aligator.getY(),
						aligator.getW(), aligator.getH(), this);
				g2d.drawImage(new ImageIcon(fish.getPic()).getImage(), fish.getX(), fish.getY(), fish.getW(),
						fish.getH(), this);
				for (food flake : fishFlakes) {
					g2d.drawImage(new ImageIcon("fishflakes.png").getImage(), flake.getX(), flake.getY(), flake.getW(),
							flake.getH(), this);
							g2d.drawString("You're at Fish Lake!! Grab 15 more fish to win!", 200,100);

				}

				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("times new roman", Font.BOLD, 25));

				break;
			case 5:
				drawWinScreen(g2d);
			case 6:
				drawLoseScreen(g2d);
		}

		g2d.drawString(new DecimalFormat("#0.00").format(curtime), 20, 40);

		

		if (win) {
			g2d.setFont(new Font("chiller", Font.BOLD, 54));
			g2d.drawString("You won!,", 300, 300);

		}

		else

		{
			curtime = (System.currentTimeMillis() - time) / 1000;

			// move();
			if (screen==2 && foodeaten == 5) {
				screen++;
			}
			else if (screen==3 && foodeaten == 15){
				screen++;

			}
			else if (screen==4 && foodeaten == 30){
				screen++;

			}
			
			for (int j = 0; j < fishFlakes.size(); j++)
				if (fishFlakes.get(j).Collision(fish)) {
					foodeaten = foodeaten + 1;
					fishFlakes.remove(fishFlakes.get(j));
					j--;

					if (lose) {
						p.playmusic("success-fanfare-trumpets-6185.wav");
						lose = false;

					}

				}

				else
					move();
			System.out.println(foodeaten);
		}
	}
		// This line tells the program to draw everything above. If you delete
	}

	public void move() {
		if ((gatormove) && (lose == false)) {
			aligator.move();
		}

		// fish.move();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		System.out.println(key);
		if(!lostscreen){
		if (key == 38)
			fish.sety(-20);
		if (key == 40)
			fish.sety(20);
		if (key == 39)
			fish.setx(20);
		if (key == 37)
			fish.setx(-20);
		if (e.getKeyChar() == 'p') {
			screen += 1;
		}
		if (key == 32) {
			foodeaten++;
		}
	}
	}

	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();

	}

	public void drawStartScreen(Graphics g2d) {
		// create start screen
		g2d.setFont(new Font("Century", Font.BOLD, 50));
		g2d.setColor(Color.white);
		g2d.drawString("Welcome to Fish Snatch!", 100, 200);
		g2d.drawString("Press P to Start", 200, 400);
		g2d.drawString("Use your arrow keys to control the fish!", 100, 600);

	}

	public void drawLoseScreen(Graphics g2d) {
		// create lose screen
		g2d.setFont(new Font("Century", Font.BOLD, 50));
		g2d.setColor(Color.blue);
		g2d.drawString("You lost!!", 200, 200);

	}

	public void drawWinScreen(Graphics g2d) {
		g2d.setFont(new Font("Century", Font.BOLD, 50));
		g2d.setColor(Color.white);
		g2d.drawString("You won Fish Snatch!", 100, 200);
	}

}
