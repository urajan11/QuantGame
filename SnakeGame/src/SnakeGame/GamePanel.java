package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JPanel;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener 
{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int pointEaten;
    int pointX;
    int pointY;
    
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    public void startGame() {
        newPoint();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paint( Graphics g)
    {
    	super.paintComponent(g);
    	draw(g);
    }
    
    public void draw(Graphics g) {
    	
    	if(running)
    	{
    		for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
    			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); // vertical line
    			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE); // horizontal line
    		}
    		g.setColor(Color.RED);
    		g.fillOval(pointX, pointY, UNIT_SIZE, UNIT_SIZE);

    		for (int i = 0; i < bodyParts; i++) {
    			if (i == 0) {
    				g.setColor(Color.green);
    				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
    			} else {
    				g.setColor(new Color(45, 180, 0));
    				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
    			}
    		}
    		
    		g.setColor(Color.red);
        	g.setFont(new Font("Ink Free ",Font.BOLD, 40));
        	FontMetrics metrics = getFontMetrics(g.getFont());
        	g.drawString("Score "+pointEaten, (SCREEN_WIDTH - metrics.stringWidth("Score "+pointEaten))/2, g.getFont().getSize() );
    	}
    	else {
    		gameOver(g);
    	}
    }
    
    
    public void newPoint() {
        pointX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        pointY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }
    
    public void move() {
//    	Shift coordinate
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        switch (direction) {
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
            default:
                break;
        }
    }
    
    public void checkPoint() {
    	
    	if((x[0] == pointX) && (y[0] == pointY))
    	{
    		bodyParts++;
    		pointEaten++;
    		newPoint();
    		
    	}

    }

    public void checkCollision() {
//    	check head collied with body
    	for(int i = bodyParts;i>0;i--)
    	{
    		if((x[0] == x[i])&& (y[0] == y[i]))
    		{
    			running = false;
    		}
    	}
    	
//    	check if head touch to left border
    	if(x[0] < 0)
    	{
    		running = false;
    		
    	}
//    	check if head touch to right  border
    	if(x[0] > SCREEN_WIDTH )
    	{
    		running = false;
  
    	}
//    	check if head touch to top  border
    	
    	if(y[0] < 0)
    	{
    		running = false;
    	}
    	
//    	check if head touch to bottom  border
    	
    	if(y[0] > SCREEN_HEIGHT)
    	{
    		running  =  false;
    	}
    	
    	if(!running)
    	{
    		timer.stop();
    	}
    }

    public void gameOver(Graphics g) {
//    	game over
    	g.setColor(Color.red);
    	g.setFont(new Font("Ink Free ",Font.BOLD, 75));
    	FontMetrics metrics1 = getFontMetrics(g.getFont());
    	g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2 );
    	
//    	sore
    	g.setColor(Color.red);
    	g.setFont(new Font("Ink Free ",Font.BOLD, 45));
    	FontMetrics metrics2 = getFontMetrics(g.getFont());
    	g.drawString("Score "+pointEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score "+pointEaten))/2, g.getFont().getSize() );

    }

    @Override
    public void actionPerformed( ActionEvent ae) {
        if (running) {
            move();
            checkPoint();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {

            switch (ke.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}
