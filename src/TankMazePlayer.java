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
double x = 0, y = 0, x2 = 0, y2 = 0;
double velx = 0, vely = 0, velx2 = 0, vely2 = 0, degree = 0, degree2 = 0;
double velspeed = 1, velspeed2 = 1;
int dx = 0, dy = 0, dx2 = 0, dy2 = 0, j = 0, k = 4;
int counter = 0, counter2 = 0, delay = 0, delay2 = 0;
int health = 100, health2 = 100;
private BufferedImage[] tank = new BufferedImage[8];
BufferedImage tank1, tank2;
int rustcounter = 0, rustcounter2 = 0;

TankHealth healthbar1 = new TankHealth(5, 5);
TankHealth healthbar2 = new TankHealth(330, 330);

Boolean[] keypress = {false, false, false, false, false, false, false, false, false, false};
/* w = 0
 * s = 1
 * a = 2
 * d = 3
 * space = 4
 * up = 5
 * down = 6
 * left = 7
 * right = 8
 * enter = 9
 */

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
	
	healthbar1.paintComponent(g);
	healthbar2.paintComponent(g);
}

public void actionPerformed(ActionEvent e) {
//rust counter relative to no keyboard input
rustcounter++;
rustcounter2++;

if(keypress[0] == true || keypress[1] == true || keypress[2] == true || keypress[3] == true || keypress[4] == true){
rustcounter = 0;
}
if(rustcounter >= 100){
	rustcounter = 0;
	health-= 5;
	healthbar1.takeDamage();
	System.out.println("health: " +health);
}

if(keypress[5] == true || keypress[6] == true || keypress[7] == true || keypress[8] == true || keypress[9] == true){
rustcounter2 = 0;
}
if(rustcounter2 >= 100){
	rustcounter2 = 0;
	health2 -= 5;
	healthbar2.takeDamage();
	System.out.println("health2: " +health2);
}

//tank boundaries relative to frame size
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

if(keypress[1] == true){//s
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

 if (keypress[0] == true){//w
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
 if(keypress[2] == true){//a
	 delay++;
	 if(delay == 5){
	delay = 0;
	degree += -(2*Math.PI/24);
	counter--;
	if(counter == -24){
		counter = 0;
	}
	affinetranform(degree, j); 
	 }
 }
 if(keypress[3] == true){//d
	 delay++;
	 if(delay == 5){
	delay = 0;
	degree += 2*Math.PI/24;
	counter++;
	if(counter == 24){
		counter = 0;
	}
	affinetranform(degree, j);	
	 }
 }
//---------------------------------------------------------------------------------------	
if(keypress[6] == true){//down arrow
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
       if (keypress[5] == true){//up arrow
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
if(keypress[7] == true){
	delay2++;
	if(delay2 == 5){
	delay2 = 0;
	degree2 += -(2*Math.PI/24);
	counter2--;
	if(counter2 == -24){
		counter2 = 0;
	}
	affinetranform(degree2, k);	
	}
}
if(keypress[8] == true){
	delay2++;
	if(delay2 == 5){
	delay2 = 0;
	degree2 += 2*Math.PI/24;
	counter2++;
	if(counter2 == 24){
		counter2 = 0;
	}
	affinetranform(degree2, k);
	}
}
//rust, changing velocity speed according to health
 if(health <= 70){
	  j = 1;
	  velspeed = .9;
	   affinetranform(degree, j);
		}
	if(health <= 40){
	  j = 2;
	  velspeed = .8;
	   affinetranform(degree, j);
	}
	if(health <= 10){
	  j = 3;
	  velspeed = .6;
	   affinetranform(degree, j);
	}
	if(health2 <= 70){
	  k = 5;
	  velspeed2 = .9;
	   affinetranform(degree2, k);
		}
	if(health2 <= 40){
	  k = 6;
	  velspeed2 = .8;
	   affinetranform(degree2, k);
	}
	if(health2 <= 10){
	  k = 7;
	  velspeed2 = .6;
	   affinetranform(degree2, k);
	}
	

velx = velx * velspeed;
vely = vely * velspeed;
velx2 = velx2 * velspeed2;
vely2 = vely2 * velspeed2;
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
		keypress[1] = true;		
	}
	else if (arg0.getKeyCode() == 87){//w
		keypress[0] = true;
	}	
	else if (arg0.getKeyCode() == 65){//a
		keypress[2] = true;		
	}
	else if (arg0.getKeyCode() == 68){//d
		keypress[3] = true;
	}
	//--------------------------------------------------------------------------------	
	if(arg0.getKeyCode() == 40){//down arrow
		keypress[6] = true;	   	
	}
	else if (arg0.getKeyCode() == 38){//up arrow
		keypress[5] = true;			
	}
	if (arg0.getKeyCode() == 37){//left arrow
		keypress[7] = true;
	}
	if (arg0.getKeyCode() == 39){//right arrow
		keypress[8] = true;	
	}
	}

//rotates image 
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
}
public void keyReleased(KeyEvent e) {
	int keycode = e.getKeyCode();
	if(keycode == 87){//w
	keypress[0] = false;
	velx = 0;
	vely = 0;	
	}
	else if(keycode == 83){//s
	keypress[1] = false;
	velx = 0;
	vely = 0;	
	}
	if(keycode == 65){//a
    keypress[2] = false;
	}
	else if(keycode == 68){//d
	keypress[3] = false;
	}
	if(keycode == 38){//up
	keypress[5] = false;
	velx2 = 0;
	vely2 = 0;
	}
	else if(keycode == 40){//down
	keypress[6] = false;
	velx2 = 0;
	vely2 = 0;
	}
	if(keycode == 37){//a
	keypress[7] = false;
	}
	else if(keycode == 39){//d
	keypress[8] = false;
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
