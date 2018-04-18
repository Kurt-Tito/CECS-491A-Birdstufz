import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankMazePlayer extends JPanel implements ActionListener, KeyListener {
Timer t = new Timer(10, this);
Timer t2 = new Timer(100, this);
double x = 0, y = 0, x2 = 0, y2 = 0;
double velx = 0, vely = 0, velx2 = 0, vely2 = 0, degree = 0, degree2 = 0;
int dx = 0, dy = 0, dx2 = 0, dy2 = 0, counter, counter2, j = 0, k = 4;
int health = 100, health2 = 100;
private BufferedImage[] tank = new BufferedImage[8];
BufferedImage tank1, tank2;


public TankMazePlayer() throws IOException {
	t.start();
	setBackground(Color.LIGHT_GRAY);
	for(int i = 0; i < 8; i++)
	{
		String tanks = "images/tankmaze/sprite_tank" + i + ".png";
		tank[i] = ImageIO.read(new File(tanks));
	}
	tank1 = ImageIO.read(new File("images/tankmaze/sprite_tank0.png"));
	tank2 = ImageIO.read(new File("images/tankmaze/sprite_tank4.png"));
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(tank1, dx, dy, null);	
	g.drawImage(tank2, dx2 + 530, dy2 + 280, null);

}

public void actionPerformed(ActionEvent e) {
if(x < 0){
	velx = 0;
	x = 0;		
}
if(x > 530){
	velx = 0;
	x = 530;		
}
if(y < 0){
	vely = 0;
	y = 0;		
}
if(y > 280){
	vely = 0;
	y = 280;		
}

if(x2 < -530){
	velx2 = 0;
	x2 = -530;		
}
if(x2 > 0){
	velx2 = 0;
	x2 = 0;		
}
if(y2 < -280){
	vely2 = 0;
	y2 = -280;		
}
if(y2 > 0){
	vely2 = 0;
	y2 = 0;		
}
x += velx;
y += vely;
x2 += velx2;
y2 += vely2;
dx = (int) x;
dy = (int) y;
dx2 = (int) x2;
dy2 = (int) y2;
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
		affinetranform(degree, j);
	}
	else if (arg0.getKeyCode() == 68){//d
		degree += 2*Math.PI/24;
		counter++;
		if(counter == 24){
			counter = 0;
		}
		affinetranform(degree, j);	
	}
	//--------------------------------------------------------------------------------	
	if(arg0.getKeyCode() == 40){//down arrow
		    if(counter2 == 0){
			velx2 = 0;
			vely2 = 1;
			}
		    else if(counter2 == 1 || counter2 == -23){//15
  			velx2 = -(Math.sqrt(6) - Math.sqrt(2))/4;
			vely2 = (Math.sqrt(6) + Math.sqrt(2))/4;
  			}
  			else if(counter2 == 2 || counter2 == -22){//30
			velx2 = -.5;
			vely2 = Math.sqrt(3)/2;
			}
			else if(counter2 == 3 || counter2 == -21){//45
			velx2 = -(Math.sqrt(2))/2;
			vely2 = (Math.sqrt(2))/2;	
			}
			else if(counter2 == 4 || counter2 == -20){//60
			velx2 = -Math.sqrt(3)/2;
			vely2 = .5;
			}
			else if(counter2 == 5 || counter2 == -19){//75
			velx2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
			vely2 = (Math.sqrt(6) - Math.sqrt(2))/4;
			}
			else if(counter2 == 6 || counter2 == -18){//90
			velx2 = -1;
			vely2 = 0;
			}
			else if(counter2 == 7 || counter2 == -17){//105
			velx2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
			vely2 = (Math.sqrt(2) - Math.sqrt(6))/4;
			}
			else if(counter2 == 8 || counter2 == -16){//120
			velx2 = -Math.sqrt(3)/2;
			vely2 = -.5;
			}
			else if(counter2 == 9 || counter2 == -15){//135
			velx2 = -Math.sqrt(2)/2;
			vely2 = -Math.sqrt(2)/2;
			}
			else if(counter2 == 10 || counter2 == -14){//150
			velx2 = -.5;
			vely2 = -Math.sqrt(3)/2;
			}
			else if(counter2 == 11 || counter2 == -13){//165
			velx2 = -(Math.sqrt(6) - Math.sqrt(2))/4;
			vely2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
			}
			else if(counter2 == 12 || counter2 == -12){//180
			velx2 = 0;
			vely2 = -1;
			}
			else if(counter2 == 13 || counter2 == -11){//195
			velx2 = -(Math.sqrt(2) - Math.sqrt(6))/4;
			vely2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
			}
			else if(counter2 == 14 || counter2 == -10){//210
			velx2 = .5;
			vely2 = -Math.sqrt(3)/2;
			}
			else if(counter2 == 15 || counter2 == -9){//225
			velx2 = Math.sqrt(2)/2;
			vely2 = -Math.sqrt(2)/2;
			}
			else if(counter2 == 16 || counter2 == -8){//240
			velx2 = Math.sqrt(3)/2;
			vely2 = -.5;
			}
			else if(counter2 == 17 || counter2 == -7){//255
			velx2 = (Math.sqrt(6) + Math.sqrt(2))/4;
			vely2 = (Math.sqrt(2) - Math.sqrt(6))/4;
			}
			else if(counter2 == 18 || counter2 == -6){//270
			velx2 = 1;
			vely2 = 0;
			}
			else if(counter2 == 19 || counter2 == -5){//285
			velx2 = (Math.sqrt(6) + Math.sqrt(2))/4;
			vely2 = (Math.sqrt(6) - Math.sqrt(2))/4;
			}
			else if(counter2 == 20 || counter2 == -4){//300
			velx2 = Math.sqrt(3)/2;
			vely2 = .5;
			}
			else if(counter2 == 21 || counter2 == -3){//315
			velx2 = Math.sqrt(2)/2;
			vely2 = Math.sqrt(2)/2;
			}
			else if(counter2 == 22 || counter2 == -2){//330
			velx2 = .5;
			vely2 = Math.sqrt(3)/2;
			}
			else if(counter2 == 23 || counter2 == -1){//345
			velx2 = -(Math.sqrt(2) - Math.sqrt(6))/4;
			vely2 = (Math.sqrt(6) + Math.sqrt(2))/4;
			}
		
	}
		else if (arg0.getKeyCode() == 38){//up arrow
					if(counter2 == 0){
					velx2 = 0;
					vely2 = -1;
					}
		  			else if(counter2 == 1 || counter2 == -23){//15
		  			velx2 = (Math.sqrt(6) - Math.sqrt(2))/4;
					vely2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
		  			}
		  			else if(counter2 == 2 || counter2 == -22){//30
					velx2 = .5;
					vely2 = -Math.sqrt(3)/2;
					}
					else if(counter2 == 3 || counter2 == -21){//45
					velx2 = (Math.sqrt(2))/2;
					vely2 = -(Math.sqrt(2))/2;	
					}
					else if(counter2 == 4 || counter2 == -20){//60
					velx2 = Math.sqrt(3)/2;
					vely2 = -.5;
					}
					else if(counter2 == 5 || counter2 == -19){//75
					velx2 = (Math.sqrt(6) + Math.sqrt(2))/4;
					vely2 = -(Math.sqrt(6) - Math.sqrt(2))/4;
					}
					else if(counter2 == 6 || counter2 == -18){//90
					velx2 = 1;
					vely2 = 0;
					}
					else if(counter2 == 7 || counter2 == -17){//105
					velx2 = (Math.sqrt(6) + Math.sqrt(2))/4;
					vely2 = -(Math.sqrt(2) - Math.sqrt(6))/4;
					}
					else if(counter2 == 8 || counter2 == -16){//120
					velx2 = Math.sqrt(3)/2;
					vely2 = .5;
					}
					else if(counter2 == 9 || counter2 == -15){//135
					velx2 = Math.sqrt(2)/2;
					vely2 = Math.sqrt(2)/2;
					}
					else if(counter2 == 10 || counter2 == -14){//150
					velx2 = .5;
					vely2 = Math.sqrt(3)/2;
					}
					else if(counter2 == 11 || counter2 == -13){//165
					velx2 = (Math.sqrt(6) - Math.sqrt(2))/4;
					vely2 = (Math.sqrt(6) + Math.sqrt(2))/4;
					}
					else if(counter2 == 12 || counter2 == -12){//180
					velx2 = 0;
					vely2 = 1;
					}
					else if(counter2 == 13 || counter2 == -11){//195
					velx2 = (Math.sqrt(2) - Math.sqrt(6))/4;
					vely2 = (Math.sqrt(6) + Math.sqrt(2))/4;
					}
					else if(counter2 == 14 || counter2 == -10){//210
					velx2 = -.5;
					vely2 = Math.sqrt(3)/2;
					}
					else if(counter2 == 15 || counter2 == -9){//225
					velx2 = -Math.sqrt(2)/2;
					vely2 = Math.sqrt(2)/2;
					}
					else if(counter2 == 16 || counter2 == -8){//240
					velx2 = -Math.sqrt(3)/2;
					vely2 = .5;
					}
					else if(counter2 == 17 || counter2 == -7){//255
					velx2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
					vely2 = -(Math.sqrt(2) - Math.sqrt(6))/4;
					}
					else if(counter2 == 18 || counter2 == -6){//270
					velx2 = -1;
					vely2 = 0;
					}
					else if(counter2 == 19 || counter2 == -5){//285
					velx2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
					vely2 = -(Math.sqrt(6) - Math.sqrt(2))/4;
					}
					else if(counter2 == 20 || counter2 == -4){//300
					velx2 = -Math.sqrt(3)/2;
					vely2 = -.5;
					}
					else if(counter2 == 21 || counter2 == -3){//315
					velx2 = -Math.sqrt(2)/2;
					vely2 = -Math.sqrt(2)/2;
					}
					else if(counter2 == 22 || counter2 == -2){//330
					velx2 = -.5;
					vely2 = -Math.sqrt(3)/2;
					}
					else if(counter2 == 23 || counter2 == -1){//345
					velx2 = (Math.sqrt(2) - Math.sqrt(6))/4;
					vely2 = -(Math.sqrt(6) + Math.sqrt(2))/4;
					}
	}
	
	else if (arg0.getKeyCode() == 37){//left arrow
		degree2 += -(2*Math.PI/24);
		counter2--;
		if(counter2 == -24){
			counter2 = 0;
		}
		affinetranform(degree2, k);
	}
	else if (arg0.getKeyCode() == 39){//right arrow
		degree2 += 2*Math.PI/24;
		counter2++;
		if(counter2 == 24){
			counter2 = 0;
		}
		affinetranform(degree2, k);	
	}
	else if(arg0.getKeyCode() == KeyEvent.VK_EQUALS){
		health -= 10;
		health2 -= 10;
		System.out.println(health);
	}
	if(health <= 70){
	  j = 1;
	   affinetranform(degree, j);
		}
	if(health <= 40){
	  j = 2;
	   affinetranform(degree, j);
	}
	if(health <= 10){
	  j = 3;
	   affinetranform(degree, j);
	}
	if(health2 <= 70){
	  k = 5;
	   affinetranform(degree2, k);
		}
	if(health2 <= 40){
	  k = 6;
	   affinetranform(degree2, k);
	}
	if(health2 <= 10){
	  k = 7;
	   affinetranform(degree2, k);
	}
	}

public void affinetranform(double deg, int num){
	 AffineTransform transform = new AffineTransform();
	 if(num <= 3){
	 transform.rotate(deg, tank[num].getWidth()/2, tank[num].getHeight()/2);
	 AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	 tank1 = op.filter(tank[num], null);	 
	 }
	 else{
	 transform.rotate(deg, tank[num].getWidth()/2, tank[num].getHeight()/2);
	 AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	 tank2 = op.filter(tank[num], null);
	 }
}


public void keyTyped(KeyEvent e) {
	int keycode = e.getKeyCode();
	if(keycode == 37 || keycode == 38 || keycode == 39|| keycode == 40){
		t2.stop();
		System.out.println(t2);
		}
/*long startTime = System.currentTimeMillis();
long elapsedTime = 0L;
while(e.getKeyCode() != 83 || e.getKeyCode() != 87 || 
      e.getKeyCode() != 65 || e.getKeyCode() != 68){
	elapsedTime = (new Date()).getTime() - startTime;	
	if(elapsedTime > 1 * 1000){
	    health--;
	    System.out.println(health);
	    break;
		}
}
*/
}
public void keyReleased(KeyEvent e) {
	int keycode = e.getKeyCode();
	/*if(keycode == 37 || keycode == 38 || keycode == 39|| keycode == 40){
	t2.schedule(new TimerTask(){
		
	}
	health--;
	System.out.println(health);
	}
	
	*/

	if(keycode == 37 || keycode == 38 || keycode == 39|| keycode == 40){
	velx2 = 0;
	vely2 = 0;
	}
	else if(keycode == 65 || keycode == 68 || keycode == 83 || keycode == 87){
	velx = 0;
	vely = 0;	
	}
}


public static void main (String arge[]) throws IOException{

	JFrame frame = new JFrame();
	TankMazePlayer player = new TankMazePlayer();
	frame.add(player);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(610,400);
	frame.setVisible(true);

}

}
