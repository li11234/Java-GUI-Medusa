package firstgraphicalprogram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartPanel extends JPanel  implements ActionListener{
	/**
	 * 
	 */
	//declaration for the screen setting
	static final int SCREEN_WIDTH = 600; 
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 125; //the speech 
	final int x[] = new int[GAME_UNITS]; 
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;//generate apple randomly
	private static final long serialVersionUID =1L;
	
	
	StartPanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();//create a new apple
		running = true; 
		timer = new Timer(DELAY, this); //the speech, change it later as more setting.
		timer.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	//the code for drawing the game
	public void draw(Graphics g) {
		if(running) {
			g.setColor(Color.red);
			//how large the apple is
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
				}
				else {
					//set the color for the body, RVG color
					g.setColor(new Color(45,180,0));
					//Snake color be changed randomly
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD,45));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score:" + applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score:" + applesEaten))/2, g.getFont().getSize());
		
		}
		else {
			gameOver(g);
		}
	}
	
	//code for generate a new apple
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	//code for move the snake
	public void move() {
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	
	//code for checking if the snake has eaten an apple
	public void checkApple() {
		if((x[0] == appleX)&&(y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	//code for checking collisions with the snake's body and the borders
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts; i>0; i--) {
			if ((x[0] == x[i] && y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if (!running) {
			timer.stop();
		}
	}
	//code for displaying the game over screen
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,45));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score:" + applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score:" + applesEaten))/2, g.getFont().getSize());
		//game over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,45));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// When click the button to start the game, go to the startGame method
		// after start button, when the snake is running, checked.
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent q) {
				switch(q.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') {
						direction = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					if(direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction = 'D';
					}
					break;
				}
			}
			
		}
		
	}

