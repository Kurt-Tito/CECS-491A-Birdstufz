import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankMazePlayer implements KeyListener {
double x = 10, y = 10, x2 = 920, y2 = 740;
double velx = 0, vely = 0, velx2 = 0, vely2 = 0, degree = 0, degree2 = 0;
double velspeed = 1, velspeed2 = 1;
int dx = 0, dy = 0, dx2 = 0, dy2 = 0, j = 0, k = 4;
int counter = 0, counter2 = 0, delay = 0, delay2 = 0;
int health = 100, health2 = 100;
private BufferedImage[] tank = new BufferedImage[8];
BufferedImage tank1, tank2;
int rustcounter = 0, rustcounter2 = 0;
int ammo1 = 50, ammo2 = 50;

TankHealth healthbar1 = new TankHealth(5, 5, "Player 1");
TankHealth healthbar2 = new TankHealth(330, 330, "Player 2");

TankProjectile projectile;
TankProjectile projectile2;

Line2D[] P1collisionLines;
Line2D[] P2collisionLines;

Line2D line1_g;
Line2D line2_g;
Line2D line3_g;
Line2D line4_g;
	
//tank2
Line2D line1b_g;
Line2D line2b_g;
Line2D line3b_g;
Line2D line4b_g;

Boolean[] keypress = {false, false, false, false, false, false, false, false, false, false};


TankMaze maze;
ArrayList<TankMazeWall> wall;
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

public TankMazePlayer(TankMazeGame TankMaze) throws IOException {
	maze = TankMaze.getMaze();
	wall = maze.getMazeWalls();
	for(int i = 0; i < 8; i++)
	{
		String tanks = "images/tankmaze/sprite_tank" + i + ".png";
		tank[i] = ImageIO.read(new File(tanks));
	}
	tank1 = ImageIO.read(new File("images/tankmaze/sprite_tank0.png"));
	tank2 = ImageIO.read(new File("images/tankmaze/sprite_tank4.png"));
}

public double[] getXLocation()
{	
	int dx_res = dx - 32;
	
	double xloc[] = new double[64];
	
	for(int i = 0; i < 64; i++)
		xloc[i] = dx_res + i;
	
	return xloc;
}

public double[] getYLocation()
{	
	int dy_res = dy - 32;
	
	double yloc[] = new double[64];
	
	for(int i = 0; i < 64; i++)
		yloc[i] = dy_res + i;
	
	return yloc;	
}

public double[] getX2Location()
{
	int dx2_res = dx2 - 32;
	
	double x2loc[] = new double[64];
	
	for(int i = 0; i < 64; i++)
		x2loc[i] = dx2_res + i;
	
	return x2loc;
}

public double[] getY2Location()
{
	int dy2_res = dy2 - 32;
	
	double y2loc[] = new double[64];
	
	for(int i = 0; i < 64; i++)
		y2loc[i] = dy2_res + i;
	
	return y2loc;
}

public boolean endGame(){
if(health <= 0){
return true;
}
else{
return false;
}
}
public boolean endGame2(){
if(health2 <= 0){
return true;
}
else{
return false;
}
}

public void regainHealthP1()
{	
	if(health >= 80){
	health = 100;	
	}
	else if(health < 80){
		health += 20;
	}
}

public void regainHealthP2()
{
	if(health2 >= 80){
		health2 = 100;
	}
	else if(health2 < 80){
		health2 += 20;
	}
}

public void paintComponent(Graphics g) {
	g.drawImage(tank1, dx, dy, null);	
	g.drawImage(tank2, dx2, dy2, null);
	
	healthbar1.paintComponent(g);
	healthbar2.paintComponent(g);
}

public void draw(Graphics2D g2)
{
	g2.setColor(Color.black);
	g2.fillRect(dx, dy - 5, 64, 5);
	g2.setColor(Color.red);
	g2.fillRect(dx, dy - 5, (int) (64 * (health/100.0)), 5);
	g2.drawImage(tank1, dx, dy, null);
	g2.setColor(Color.black);
	g2.fillRect(dx2, dy2 - 5, 64, 5);
	g2.setColor(Color.red);
	g2.fillRect(dx2, dy2 - 5, (int) (64 * (health2/100.0)), 5);
	g2.drawImage(tank2, dx2, dy2, null);
	

	
	if(projectile != null && projectile.isActive())
	{
		projectile.draw(g2);
	}
	if(projectile2 != null && projectile2.isActive())
	{
		projectile2.draw(g2);
	}
}

public void actionPerformed(ActionEvent e) {
//rust counter relative to no keyboard input
rustcounter++;
rustcounter2++;

if(projectile != null && projectile.isActive())
{
	projectile.update();
}
if(projectile2 != null && projectile2.isActive())
{
	projectile2.update();
}

if(keypress[0] == true || keypress[1] == true || keypress[4] == true){
rustcounter = 0;
}
if(rustcounter >= 30){
	rustcounter = 0;
	health-= 1;
	healthbar1.takeDamage();
}

if(keypress[5] == true || keypress[6] == true || keypress[9] == true){
rustcounter2 = 0;
}
if(rustcounter2 >= 30){
	rustcounter2 = 0;
	health2 -= 1;
	healthbar2.takeDamage();
}
//tank boundaries relative to frame size
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
 if(keypress[2] == true && keypress[3] == true){
 }
 else if(keypress[2] == true){//a
	 delay++;
	 if(delay == 5){
		delay = 0;
		if (checkCollision(x, y)  || tankCollision()){
			degree -= -(2*Math.PI/24);
			counter++;
			if(counter == 0){
				counter = -24;
			}
			affinetransform(degree,j);
		}
		else {
			degree += -(2*Math.PI/24);
			counter--;
			if(counter == -24){
				counter = 0;
			}
			affinetransform(degree, j); 
		}
	
	 }
	 
 }
 else if(keypress[3] == true){//d
	 delay++;
	 if(delay == 5){
		delay = 0;
		if (checkCollision(x, y) || tankCollision()) {
			degree -= 2*Math.PI/24;
			counter--;
			if(counter == 0){
				counter = 24;
			}
			affinetransform(degree,j);
		}
		else {
			degree += 2*Math.PI/24;
			counter++;
			if(counter == 24){
				counter = 0;
			}
			affinetransform(degree, j);
		}
	
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
if(keypress[7] == true && keypress[8] == true){
}
else if(keypress[7] == true){//left
	delay2++;
	if(delay2 == 5){
		delay2 = 0;
		if (checkCollision2(x2, y2) || tankCollision()) {
			degree2 -= -(2*Math.PI/24);
			counter2++;
			if(counter2 == 0){
				counter2 = -24;
			}
			affinetransform(degree2,k);
		}
		else {
			degree2 += -(2*Math.PI/24);
			counter2--;
			if(counter2 == -24){
				counter2 = 0;
			}
			affinetransform(degree2, k); 
		}
	}
}
else if(keypress[8] == true){//right
	delay2++;
	if(delay2 == 5){
		delay2 = 0;
		if (checkCollision2(x2, y2) || tankCollision()) {
			degree2 -= 2*Math.PI/24;
			counter2--;
			if(counter2 == 0){
				counter2 = 24;
			}
			affinetransform(degree2,k);
		}
		else {
			degree2 += 2*Math.PI/24;
			counter2++;
			if(counter2 == 24){
				counter2 = 0;
			}
			affinetransform(degree2, k);
		}
	
	}
}
//rust, changing velocity speed according to health
if(health > 70){
	j = 0;
	velspeed = 1;
	affinetransform(degree, j);
}
if(health <= 70){
	j = 1;
	velspeed = .9;
	affinetransform(degree, j);
}
if(health <= 40){
	j = 2;
	velspeed = .8;
	affinetransform(degree, j);
	}
if(health <= 10){
	j = 3;
	velspeed = .6;
	affinetransform(degree, j);
	}
if(health2 > 70){
	k = 4;
	velspeed2 = 1;
	affinetransform(degree2, k);
	}
if(health2 <= 70){
	k = 5;
	velspeed2 = .9;
	affinetransform(degree2, k);
	}
if(health2 <= 40){
	k = 6;
	velspeed2 = .8;
	affinetransform(degree2, k);
	}
if(health2 <= 10){
	k = 7;
	velspeed2 = .6;
	affinetransform(degree2, k);
	}
	

velx = velx * velspeed;
vely = vely * velspeed;
velx2 = velx2 * velspeed2;
vely2 = vely2 * velspeed2;
if (checkCollision(x + velx, y + vely) || tankCollision()) {
	x -= velx;
	y -= vely;
	dx = (int) x;
	dy = (int) y;
	
}

if (checkCollision2(x2 + velx2, y2 + vely2) || tankCollision()) {
	x2 -= velx2;
	y2 -= vely2;
	dx2 = (int) x2;
	dy2 = (int) y2;
	
}


x += velx;
y += vely;
x2 += velx2;
y2 += vely2;


dx = (int) x;
dy = (int) y;
dx2 = (int) x2;
dy2 = (int) y2;

	P1collisionLines = new Line2D[4];
	P2collisionLines = new Line2D[4];
	
	P1collisionLines[0] = line1_g;
	P1collisionLines[1] = line2_g;
	P1collisionLines[2] = line3_g;
	P1collisionLines[3] = line4_g;
	
	P2collisionLines[0] = line1b_g;
	P2collisionLines[1] = line2b_g;
	P2collisionLines[2] = line3b_g;
	P2collisionLines[3] = line4b_g;
	
	if(projectile != null && projectile.isActive())
	{	
		for(int i = 0; i < projectile.getProjectileLength(); i++)
		{
			for (int j = 0; j < P2collisionLines.length; j++) {
				if(P2collisionLines[i].intersectsLine(projectile.getProjectile(j)))
				{
					health2 -= 10;
					healthbar2.takeDamage();
					projectile.setActive(false);
				}
			}
		}
	}
	
	if(projectile2 != null && projectile2.isActive())
	{	
		for(int i = 0; i < projectile2.getProjectileLength(); i++)
		{
			for (int j = 0; j < P1collisionLines.length; j++) {
				
			
				if(P1collisionLines[i].intersectsLine(projectile2.getProjectile(j)))
				{
					health -= 10;
					healthbar1.takeDamage();
					projectile2.setActive(false);
				}
			}
		}
	}
	
	if(health < 0)
	{
		health = 0;
	}
	else if(health2 < 0)
	{
		health2 = 0;
	}

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
public void affinetransform(double deg, int num){
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
	if(keycode == 32 && (projectile == null || !projectile.isActive()) && ammo1 > 0)
	{
		projectile = new TankProjectile(32 +dx, 32+dy, (degree - (Math.PI /2)));
		ammo1--;
	}
	if(keycode == KeyEvent.VK_ENTER && (projectile2 == null || !projectile2.isActive()) && ammo2 > 0)
	{
		projectile2 = new TankProjectile(32 +dx2, 32 +dy2, (degree2 - (Math.PI /2)));
		ammo2--;
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

public boolean tankCollision() {
	//tank1
	//Line2D line1 = new Line2D.Double();
	line1_g = new Line2D.Double();
	line2_g = new Line2D.Double();
	line3_g = new Line2D.Double();
	line4_g = new Line2D.Double();
	
	//put these in an array 
	
	
	//tank2
	line1b_g = new Line2D.Double();
	line2b_g = new Line2D.Double();
	line3b_g = new Line2D.Double();
	line4b_g = new Line2D.Double();
		
	//TR = Top Right, TL = Top Left, BL = Bottom Left, BR = Bottom Right
	double TRx = 0, TRy = 0, TLx = 0, TLy = 0; 
	double BLx = 0, BLy = 0, BRx = 0, BRy = 0;
	double Cx = 0, Cy = 0; //center coordiinates
		
		
	double TRx2 = 0, TRy2 = 0, TLx2 = 0, TLy2 = 0; 
	double BLx2 = 0, BLy2 = 0, BRx2 = 0, BRy2 = 0;
	double Cx2 = 0, Cy2 = 0;
	
	double hypoSmall = 0, hypo = 0;
		
	
		 
	double trigDegree = (degree - Math.PI/2)* -1, 
			trigDegree2 = (degree2 - Math.PI/2) * -1;
	double side = 64; 
	double halfSide = side / 2;
	
	double theta1 = 0, theta2 = 0, theta3 = 0, theta4 = 0; //tank1
	double theta1b = 0, theta2b = 0, theta3b = 0, theta4b = 0; //tank2
	
	
	
	//tank sprite (10,10) top left. bottom right (54,54) square
	double tankLength = 44;
	
	hypo = Math.sqrt(Math.pow((side)/2, 2) + Math.pow((side)/2, 2));
	hypoSmall = Math.sqrt(Math.pow((tankLength)/2, 2) + Math.pow((tankLength)/2, 2));
	
	//Theta tank 1
	theta1 = Math.atan((halfSide / halfSide)) - trigDegree; //Top Right
	theta2 = theta1 + Math.toRadians(90); //Bottom Right
	theta3 = theta1 + Math.toRadians(180); //Bottom Left
	theta4 = theta1 + Math.toRadians(270); //Top Left
		
	//theta tank2
	theta1b = Math.atan((halfSide / halfSide)) - trigDegree2; //Top Right
	theta2b = theta1b + Math.toRadians(90); //Bottom Right
	theta3b = theta1b + Math.toRadians(180); //Bottom Left
	theta4b = theta1b + Math.toRadians(270); //Top Left
		
		
	
	//find center coordinates of sprite
	Cx = (x + hypo * Math.cos(Math.toRadians(-45)));
	Cy = (y + hypo * Math.cos(Math.toRadians(-45)));
		
	Cx2 = (x2 + hypo * Math.cos(Math.toRadians(-45)));
	Cy2 = (y2 + hypo * Math.cos(Math.toRadians(-45)));
		
		
		
	TRx = (hypoSmall * Math.cos(theta1) + Cx); TRy = (hypoSmall * Math.sin(theta1) + Cy);
	BRx = (hypoSmall * Math.cos(theta2) + Cx); BRy = (hypoSmall * Math.sin(theta2) + Cy);
	BLx = (hypoSmall * Math.cos(theta3) + Cx); BLy = (hypoSmall * Math.sin(theta3) + Cy);
	TLx = (hypoSmall * Math.cos(theta4) + Cx); TLy = (hypoSmall * Math.sin(theta4) + Cy);
		
	TRx2 = (hypoSmall * Math.cos(theta1b) + Cx2); TRy2 = (hypoSmall * Math.sin(theta1b) + Cy2);
	BRx2 = (hypoSmall * Math.cos(theta2b) + Cx2); BRy2 = (hypoSmall * Math.sin(theta2b) + Cy2);
	BLx2 = (hypoSmall * Math.cos(theta3b) + Cx2); BLy2 = (hypoSmall * Math.sin(theta3b) + Cy2);
	TLx2 = (hypoSmall * Math.cos(theta4b) + Cx2); TLy2 = (hypoSmall * Math.sin(theta4b) + Cy2);




	
	//tank1 
	line1_g.setLine(TLx, TLy, TRx, TRy);
	line2_g.setLine(TRx, TRy, BRx, BRy);
	line3_g.setLine(BRx, BRy, BLx, BLy);
	line4_g.setLine(BLx, BRy, TLx, TRy);
	
	line1b_g.setLine(TLx2, TLy2, TRx2, TRy2);
	line2b_g.setLine(TRx2, TRy2, BRx2, BRy2);
	line3b_g.setLine(BRx2, BRy2, BLx2, BLy2);
	line4b_g.setLine(BLx2, BRy2, TLx2, TRy2);
	
	if (line1_g.intersectsLine(line1b_g) || line1_g.intersectsLine(line2b_g) || 
			line1_g.intersectsLine(line3b_g) || line1_g.intersectsLine(line4b_g)) {
		return true;
	}
	if (line2_g.intersectsLine(line1b_g) || line2_g.intersectsLine(line2b_g) || 
			line2_g.intersectsLine(line3b_g) || line2_g.intersectsLine(line4b_g)) {
		return true;
	}
	if (line3_g.intersectsLine(line1b_g) || line3_g.intersectsLine(line2b_g) || 
			line3_g.intersectsLine(line3b_g) || line3_g.intersectsLine(line4b_g)) {
		return true;
	}
	if (line4_g.intersectsLine(line1b_g) || line4_g.intersectsLine(line2b_g) || 
			line4_g.intersectsLine(line3b_g) || line4_g.intersectsLine(line4b_g)) {
		return true;
	}
	else {
		return false;
		
	}
		






}

///////////////////////////////////////////////////////////////////////////////

public boolean checkCollision(double xFuture, double yFuture) {

	//tank1
	Line2D line1 = new Line2D.Double();
	Line2D line2 = new Line2D.Double();
	Line2D line3 = new Line2D.Double();
	Line2D line4 = new Line2D.Double();
			
			
	
			
	//TR = Top Right, TL = Top Left, BL = Bottom Left, BR = Bottom Right
	double TRx = 0, TRy = 0, TLx = 0, TLy = 0; 
	double BLx = 0, BLy = 0, BRx = 0, BRy = 0;
	double Cx = 0, Cy = 0; //center coordiinates
		
			
		
	double hypoSmall = 0, hypo = 0;
		
		
			 
	double trigDegree = (degree - Math.PI/2)* -1;
	double side = 64; 
	double halfSide = side / 2;
		
	double theta1 = 0, theta2 = 0, theta3 = 0, theta4 = 0; //tank1
		
		
	//tank sprite (10,10) top left. bottom right (54,54) square
	double tankLength = 44;
		
	hypo = Math.sqrt(Math.pow((side)/2, 2) + Math.pow((side)/2, 2));
	hypoSmall = Math.sqrt(Math.pow((tankLength)/2, 2) + Math.pow((tankLength)/2, 2));
		
	//Theta tank 1
	theta1 = Math.atan((halfSide / halfSide)) - trigDegree; //Top Right
	theta2 = theta1 + Math.toRadians(90); //Bottom Right
	theta3 = theta1 + Math.toRadians(180); //Bottom Left
	theta4 = theta1 + Math.toRadians(270); //Top Left
			
			
			
		
	//find center coordinates of sprite
	Cx = (xFuture + hypo * Math.cos(Math.toRadians(-45)));
	Cy = (yFuture + hypo * Math.cos(Math.toRadians(-45)));
			
			
	TRx = (hypoSmall * Math.cos(theta1) + Cx); TRy = (hypoSmall * Math.sin(theta1) + Cy);
	BRx = (hypoSmall * Math.cos(theta2) + Cx); BRy = (hypoSmall * Math.sin(theta2) + Cy);
	BLx = (hypoSmall * Math.cos(theta3) + Cx); BLy = (hypoSmall * Math.sin(theta3) + Cy);
	TLx = (hypoSmall * Math.cos(theta4) + Cx); TLy = (hypoSmall * Math.sin(theta4) + Cy);
			




		
	//tank1 
	line1.setLine(TLx, TLy, TRx, TRy);
	line2.setLine(TRx, TRy, BRx, BRy);
	line3.setLine(BRx, BRy, BLx, BLy);
	line4.setLine(BLx, BRy, TLx, TRy);
		
	
	
	
	for (int i = 0; i < wall.size(); i++) {
		if (wall.get(i).intersectsLine(line1) || wall.get(i).intersectsLine(line2) 
				|| wall.get(i).intersectsLine(line3) || wall.get(i).intersectsLine(line4)) {
			return true;
			
		}
		
		
		
	}
	return false;

}

//wall collision
public boolean checkCollision2(double xFuture, double yFuture) {
	


	//tank2
	Line2D line1b = new Line2D.Double();
	Line2D line2b = new Line2D.Double();
	Line2D line3b = new Line2D.Double();
	Line2D line4b = new Line2D.Double();
			
	//TR = Top Right, TL = Top Left, BL = Bottom Left, BR = Bottom Right
		
			
	double TRx2 = 0, TRy2 = 0, TLx2 = 0, TLy2 = 0; 
	double BLx2 = 0, BLy2 = 0, BRx2 = 0, BRy2 = 0;
	double Cx2 = 0, Cy2 = 0;
		
	double hypoSmall = 0, hypo = 0;
		
		
			 
	double trigDegree2 = (degree2 - Math.PI/2) * -1;
	double side = 64; 
	double halfSide = side / 2;
		
	double theta1b = 0, theta2b = 0, theta3b = 0, theta4b = 0; //tank2
		
		
		
	//tank sprite (10,10) top left. bottom right (54,54) square
	double tankLength = 44;
		
	hypo = Math.sqrt(Math.pow((side)/2, 2) + Math.pow((side)/2, 2));
	hypoSmall = Math.sqrt(Math.pow((tankLength)/2, 2) + Math.pow((tankLength)/2, 2));

	//theta tank2
	theta1b = Math.atan((halfSide / halfSide)) - trigDegree2; //Top Right
	theta2b = theta1b + Math.toRadians(90); //Bottom Right
	theta3b = theta1b + Math.toRadians(180); //Bottom Left
	theta4b = theta1b + Math.toRadians(270); //Top Left

			
	Cx2 = (xFuture + hypo * Math.cos(Math.toRadians(-45)));
	Cy2 = (yFuture + hypo * Math.cos(Math.toRadians(-45)));
		
			
	TRx2 = (hypoSmall * Math.cos(theta1b) + Cx2); TRy2 = (hypoSmall * Math.sin(theta1b) + Cy2);
	BRx2 = (hypoSmall * Math.cos(theta2b) + Cx2); BRy2 = (hypoSmall * Math.sin(theta2b) + Cy2);
	BLx2 = (hypoSmall * Math.cos(theta3b) + Cx2); BLy2 = (hypoSmall * Math.sin(theta3b) + Cy2);
	TLx2 = (hypoSmall * Math.cos(theta4b) + Cx2); TLy2 = (hypoSmall * Math.sin(theta4b) + Cy2);
		
	line1b.setLine(TLx2, TLy2, TRx2, TRy2);
	line2b.setLine(TRx2, TRy2, BRx2, BRy2);
	line3b.setLine(BRx2, BRy2, BLx2, BLy2);
	line4b.setLine(BLx2, BRy2, TLx2, TRy2);
	
	
	
	for (int i = 0; i < wall.size(); i++) {
		if (wall.get(i).intersectsLine(line1b) || wall.get(i).intersectsLine(line2b) 
				|| wall.get(i).intersectsLine(line3b) || wall.get(i).intersectsLine(line4b)) {
			return true;
			
		}
			
	}
	return false;
	
}
///////////////////////////////////////////////////////////////////////////////





}
