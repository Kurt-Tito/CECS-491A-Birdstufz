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
double x = 0, y = 0, velx = 0, vely = 0, degree = 0;
int dx = 0, dy = 0, counter;
float health, starthealth = 100;
boolean keyPress;
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
	g.drawImage(tank1, dx, dy, null);

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
dx = (int) x;
dy = (int) y;
repaint();
}

public void keyPressed(KeyEvent arg0) {
	if(arg0.getKeyCode() == 83){//s
		if(counter == 0){
			velx = 0;
			vely = 1;
			}
		else if(counter == 1 || counter == -23){//15
  			velx = -(Math.sqrt(6) - Math.sqrt(2))/4;
			vely = (Math.sqrt(6) + Math.sqrt(2))/4;
  			}
  			else if(counter == 2 || counter == -22){//30
			velx = -.5;
			vely = Math.sqrt(3)/2;
			}
			else if(counter == 3 || counter == -21){//45
			velx = -(Math.sqrt(2))/2;
			vely = (Math.sqrt(2))/2;	
			}
			else if(counter == 4 || counter == -20){//60
			velx = -Math.sqrt(3)/2;
			vely = .5;
			}
			else if(counter == 5 || counter == -19){//75
			velx = -(Math.sqrt(6) + Math.sqrt(2))/4;
			vely = (Math.sqrt(6) - Math.sqrt(2))/4;
			}
			else if(counter == 6 || counter == -18){//90
			velx = -1;
			vely = 0;
			}
			else if(counter == 7 || counter == -17){//105
			velx = -(Math.sqrt(6) + Math.sqrt(2))/4;
			vely = (Math.sqrt(2) - Math.sqrt(6))/4;
			}
			else if(counter == 8 || counter == -16){//120
			velx = -Math.sqrt(3)/2;
			vely = -.5;
			}
			else if(counter == 9 || counter == -15){//135
			velx = -Math.sqrt(2)/2;
			vely = -Math.sqrt(2)/2;
			}
			else if(counter == 10 || counter == -14){//150
			velx = -.5;
			vely = -Math.sqrt(3)/2;
			}
			else if(counter == 11 || counter == -13){//165
			velx = -(Math.sqrt(6) - Math.sqrt(2))/4;
			vely = -(Math.sqrt(6) + Math.sqrt(2))/4;
			}
			else if(counter == 12 || counter == -12){//180
			velx = 0;
			vely = -1;
			}
			else if(counter == 13 || counter == -11){//195
			velx = -(Math.sqrt(2) - Math.sqrt(6))/4;
			vely = -(Math.sqrt(6) + Math.sqrt(2))/4;
			}
			else if(counter == 14 || counter == -10){//210
			velx = .5;
			vely = -Math.sqrt(3)/2;
			}
			else if(counter == 15 || counter == -9){//225
				velx = Math.sqrt(2)/2;
				vely = -Math.sqrt(2)/2;
			}
			else if(counter == 16 || counter == -8){//240
				velx = Math.sqrt(3)/2;
				vely = -.5;
				}
			else if(counter == 17 || counter == -7){//255
				velx = (Math.sqrt(6) + Math.sqrt(2))/4;
				vely = (Math.sqrt(2) - Math.sqrt(6))/4;
				}
			else if(counter == 18 || counter == -6){//270
				velx = 1;
				vely = 0;
				}
			else if(counter == 19 || counter == -5){//285
				velx = (Math.sqrt(6) + Math.sqrt(2))/4;
				vely = (Math.sqrt(6) - Math.sqrt(2))/4;
				}
			else if(counter == 20 || counter == -4){//300
				velx = Math.sqrt(3)/2;
				vely = .5;
				}
			else if(counter == 21 || counter == -3){//315
				velx = Math.sqrt(2)/2;
				vely = Math.sqrt(2)/2;
				}
			else if(counter == 22 || counter == -2){//330
				velx = .5;
				vely = Math.sqrt(3)/2;
				}
			else if(counter == 23 || counter == -1){//345
				velx = -(Math.sqrt(2) - Math.sqrt(6))/4;
				vely = (Math.sqrt(6) + Math.sqrt(2))/4;
				}
		
	}
	
	else if (arg0.getKeyCode() == 87){//w
		if(counter == 0){
		velx = 0;
		vely = -1;
		}
	  			else if(counter == 1 || counter == -23){//15
	  			velx = (Math.sqrt(6) - Math.sqrt(2))/4;
				vely = -(Math.sqrt(6) + Math.sqrt(2))/4;
	  			}
	  			else if(counter == 2 || counter == -22){//30
				velx = .5;
				vely = -Math.sqrt(3)/2;
				}
				else if(counter == 3 || counter == -21){//45
				velx = (Math.sqrt(2))/2;
				vely = -(Math.sqrt(2))/2;	
				}
				else if(counter == 4 || counter == -20){//60
				velx = Math.sqrt(3)/2;
				vely = -.5;
				}
				else if(counter == 5 || counter == -19){//75
				velx = (Math.sqrt(6) + Math.sqrt(2))/4;
				vely = -(Math.sqrt(6) - Math.sqrt(2))/4;
				}
				else if(counter == 6 || counter == -18){//90
				velx = 1;
				vely = 0;
				}
				else if(counter == 7 || counter == -17){//105
				velx = (Math.sqrt(6) + Math.sqrt(2))/4;
				vely = -(Math.sqrt(2) - Math.sqrt(6))/4;
				}
				else if(counter == 8 || counter == -16){//120
				velx = Math.sqrt(3)/2;
				vely = .5;
				}
				else if(counter == 9 || counter == -15){//135
				velx = Math.sqrt(2)/2;
				vely = Math.sqrt(2)/2;
				}
				else if(counter == 10 || counter == -14){//150
				velx = .5;
				vely = Math.sqrt(3)/2;
				}
				else if(counter == 11 || counter == -13){//165
				velx = (Math.sqrt(6) - Math.sqrt(2))/4;
				vely = (Math.sqrt(6) + Math.sqrt(2))/4;
				}
				else if(counter == 12 || counter == -12){//180
				velx = 0;
				vely = 1;
				}
				else if(counter == 13 || counter == -11){//195
				velx = (Math.sqrt(2) - Math.sqrt(6))/4;
				vely = (Math.sqrt(6) + Math.sqrt(2))/4;
				}
				else if(counter == 14 || counter == -10){//210
				velx = -.5;
				vely = Math.sqrt(3)/2;
				}
				else if(counter == 15 || counter == -9){//225
					velx = -Math.sqrt(2)/2;
					vely = Math.sqrt(2)/2;
				}
				else if(counter == 16 || counter == -8){//240
					velx = -Math.sqrt(3)/2;
					vely = .5;
					}
				else if(counter == 17 || counter == -7){//255
					velx = -(Math.sqrt(6) + Math.sqrt(2))/4;
					vely = -(Math.sqrt(2) - Math.sqrt(6))/4;
					}
				else if(counter == 18 || counter == -6){//270
					velx = -1;
					vely = 0;
					}
				else if(counter == 19 || counter == -5){//285
					velx = -(Math.sqrt(6) + Math.sqrt(2))/4;
					vely = -(Math.sqrt(6) - Math.sqrt(2))/4;
					}
				else if(counter == 20 || counter == -4){//300
					velx = -Math.sqrt(3)/2;
					vely = -.5;
					}
				else if(counter == 21 || counter == -3){//315
					velx = -Math.sqrt(2)/2;
					vely = -Math.sqrt(2)/2;
					}
				else if(counter == 22 || counter == -2){//330
					velx = -.5;
					vely = -Math.sqrt(3)/2;
					}
				else if(counter == 23 || counter == -1){//345
					velx = (Math.sqrt(2) - Math.sqrt(6))/4;
					vely = -(Math.sqrt(6) + Math.sqrt(2))/4;
					}
	}
	
	else if (arg0.getKeyCode() == 65){//a
		degree += -(2*Math.PI/24);
		counter--;
		if(counter == -24){
			counter = 0;
		}
		System.out.println(counter);
		  AffineTransform transform = new AffineTransform();
		  transform.rotate(degree, tank.getWidth()/2, tank.getHeight()/2);
		  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		  tank1 = op.filter(tank, null);
	}
	else if (arg0.getKeyCode() == 68){//d
		degree += 2*Math.PI/24;
		counter++;
		if(counter == 24){
			counter = 0;
		}
		System.out.println(counter);
		  AffineTransform transform = new AffineTransform();
		  transform.rotate(degree, tank.getWidth()/2, tank.getHeight()/2);
		  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		  tank1 = op.filter(tank, null);
		
	}
}



public void keyTyped(KeyEvent e) {
}
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
