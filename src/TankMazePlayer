import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankMazePlayer extends JPanel implements ActionListener, KeyListener {
Timer t = new Timer(10, this);
int x = 0, y = 0;
double velx = 0, vely = 0;
double degree = 0;
BufferedImage tank, tank1;

public TankMazePlayer() throws IOException {
	t.start();
	setBackground(Color.LIGHT_GRAY);
	tank = ImageIO.read(new File("images/tankmaze/sprite_tank0.png"));
	tank1 = ImageIO.read(new File("images/tankmaze/sprite_tank0.png"));
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(tank1, x, y,null);

}

public void actionPerformed(ActionEvent e) {
if(x < 0)
{
	velx = 0;
	x = 0;		
}

if(x > 530)
{
	velx = 0;
	x = 530;		
}

if(y < 0)
{
	vely = 0;
	y = 0;		
}

if(y > 280)
{
	vely = 0;
	y = 280;		
}


x += velx;
y += vely;
repaint();
}

public void keyPressed(KeyEvent arg0) {
	if(arg0.getKeyCode() == 83){//s
		velx = 0;
		vely = 1;
	}
	if (arg0.getKeyCode() == 87){//w
		velx = 0;
		vely = -1;    
	}
	
	if (arg0.getKeyCode() == 65){//a
		degree += -(2*Math.PI/16);
		if(degree <= -2*Math.PI){
			degree = degree + 2*Math.PI;
		}
		System.out.println(degree);
		  AffineTransform transform = new AffineTransform();
		  transform.rotate(degree, tank.getWidth()/2, tank.getHeight()/2);
		  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		  tank1 = op.filter(tank, null);
	}
	if (arg0.getKeyCode() == 68){//d
		degree += 2*Math.PI/16;
		if(degree >= 2*Math.PI){
			degree = degree - 2*Math.PI;
		}
		System.out.println(degree);
		  AffineTransform transform = new AffineTransform();
		  transform.rotate(degree, tank.getWidth()/2, tank.getHeight()/2);
		  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		  tank1 = op.filter(tank, null);
		
	}
}



public void keyTyped(KeyEvent e) {}
public void keyReleased(KeyEvent e) {
	velx = 0;
	vely = 0;
}


public static void main (String arge[]) throws IOException{

	JFrame f = new JFrame();
	TankMazePlayer s = new TankMazePlayer();
	f.add(s);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setSize(600,400);
	f.setVisible(true);

}
}
