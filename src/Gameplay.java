import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import java.awt.Image;
import java.awt.color.ColorSpace;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private int[] enemyXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 
			300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 
			650, 675, 700, 725, 750, 775, 800, 825, 850};
	
	private int[] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 
			300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private ImageIcon enemyImage;
	private Random random = new Random();
	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);
	
	
	private int snakeLen = 3;
	
	private int moves = 0;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon headKing;
	private ImageIcon knights;
	
	private Timer timer;
	private int delay = 100;
	
	private int score = 0;
	
	private ImageIcon titleImage;
	
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		
		if (moves == 0) {
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		//draw title image border:
		g.setColor(Color.WHITE);
		g.drawRect(24,  10,  851,  55);
		
		//draw title image
		
		titleImage = new ImageIcon("chessSnakeTitle.png");
		titleImage = resizeIcon(titleImage, 850, 55);
		titleImage.paintIcon(this, g, 25, 11);
		
		
		//draw border for playing area
		g.setColor(Color.BLACK);
		g.drawRect(24, 74, 851, 577);
		
		//draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);
		
		//draw length of snake
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " + snakeLen, 780, 50);
		
		
		//draw background for gameplay
		
		g.setColor(new Color(48, 217, 240));
		g.fillRect(25, 75, 850, 575);
		
		headKing = new ImageIcon("whiteKing.png");
		headKing = resizeIcon(headKing, 25, 25);

		knights = new ImageIcon("whiteKnight.png");
		knights = resizeIcon(knights, 25, 25); 
		
		headKing.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		
		for (int i = 0 ; i < snakeLen ; i++) {
			
			if (i == 0 && right) {
				headKing = new ImageIcon("whiteKing.png");
				headKing = resizeIcon(headKing, 25, 25);
				headKing.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && left) {
				headKing = new ImageIcon("whiteKing.png");
				headKing = resizeIcon(headKing, 25, 25);
				headKing.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && up) {
				headKing = new ImageIcon("whiteKing.png");
				headKing = resizeIcon(headKing, 25, 25);
				headKing.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && down ) {
				headKing = new ImageIcon("whiteKing.png");
				headKing = resizeIcon(headKing, 25, 25);
				headKing.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i!= 0) {
				knights = new ImageIcon("whiteKnight.png");
				knights = resizeIcon(knights, 25, 25); 
				knights.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);

			}
		}
		
		enemyImage = new ImageIcon("blackPawn.png");
		enemyImage = resizeIcon(enemyImage, 25, 25);
		if((enemyXpos[xPos] == snakeXlength[0]) && enemyYpos[yPos] == snakeYlength[0]) {
			snakeLen++;
			score += 10;
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}
		
		enemyImage.paintIcon(this,  g,  enemyXpos[xPos],  enemyYpos[yPos]);
				
		for (int i = 1; i < snakeLen; i++) {
			if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER", 300, 300); 
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.ITALIC, 20));
				g.drawString("press SPACE to restart", 350, 340); 
				
				
			}
		}
				
		g.dispose();
		
	}
	
	private ImageIcon resizeIcon(ImageIcon x, int i, int j) {
		//scales image down to i by j pixels.
		Image transformed = x.getImage().getScaledInstance(i,  j,  
				java.awt.Image.SCALE_SMOOTH);
		//returns the ImageIcon of the transformed image. 
		return (new ImageIcon(transformed));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				if (i == 0) {
					snakeXlength[i] = snakeXlength[i] + 25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if (snakeXlength[i] > 850) {
					snakeXlength[i] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				if (i == 0) {
					snakeXlength[i] = snakeXlength[i] - 25;
				}
				else {
					snakeXlength[i] = snakeXlength[i-1];
				}
				if (snakeXlength[i] < 25) {
					snakeXlength[i] = 850;
				}
			}
			repaint();
		}
		if(up) {
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				if (i == 0) {
					snakeYlength[i] = snakeYlength[i] - 25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if (snakeYlength[i] < 75) {
					snakeYlength[i] = 625;
				}
			}
			repaint();
		}
		if(down) {
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			for(int i = snakeLen-1 ; i >= 0 ; i--) {
				if (i == 0) {
					snakeYlength[i] = snakeYlength[i] + 25;
				}
				else {
					snakeYlength[i] = snakeYlength[i-1];
				}
				if (snakeYlength[i] > 625) {
					snakeYlength[i] = 75;
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			snakeLen = 3;
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
