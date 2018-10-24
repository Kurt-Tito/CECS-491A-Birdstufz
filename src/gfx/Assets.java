package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage  floor, sideWall, frontWall, pumpkin, playerShooter, skeletonProjectile;
	public static BufferedImage[] trees = new BufferedImage[4];
	public static BufferedImage[] zombies = new BufferedImage[4];
	public static BufferedImage[] skeletons = new BufferedImage[4];
	
	
	//animation
	public static BufferedImage pU, pD, pL, pR, pUR, pUL, pDL, pDR, invincibility;
	public static BufferedImage[] idle, meleeAttack, rifleMove, rifleShoot;
	public static BufferedImage[] idleBird = new BufferedImage[2];

	public static void init(){
		
		SpriteSheet tree = new SpriteSheet(ImageLoader.loadImage("/texture/Obstacle/sprite_tree0.png"));
		
		floor = ImageLoader.loadImage("/texture/Floor/grasstile_darker.png");
		sideWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_side.png");
		frontWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_front.png");
		frontWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_front.png");
		pumpkin = ImageLoader.loadImage("/texture/Obstacle/Pumpkin.png");
		skeletonProjectile = ImageLoader.loadImage("/texture/Enemy/fireball.png");
		
		zombies[0] = ImageLoader.loadImage("/texture/Enemy/zombie07.png");
		zombies[1] = ImageLoader.loadImage("/texture/Enemy/zombie10.png");
		zombies[2] = ImageLoader.loadImage("/texture/Enemy/zombie04.png");
		zombies[3] = ImageLoader.loadImage("/texture/Enemy/zombie01.png");
		
		skeletons[0] = ImageLoader.loadImage("/texture/Enemy/skeletonarcher07.png");
		skeletons[1] = ImageLoader.loadImage("/texture/Enemy/skeletonarcher10.png");
		skeletons[2] = ImageLoader.loadImage("/texture/Enemy/skeletonarcher04.png");
		skeletons[3] = ImageLoader.loadImage("/texture/Enemy/skeletonarcher01.png");
		
		playerShooter = ImageLoader.loadImage("/texture/player.png");
		
		trees[0] = tree.crop(0, 0, width, height);
		trees[1] = tree.crop(width, 0, width, height);
		trees[2] = tree.crop(0, height, width, height);
		trees[3] = tree.crop(width, height, width, height);
		
		invincibility = ImageLoader.loadImage("/Player/PlayerOrientation/invincible.png");
		//load player animation
		pU = ImageLoader.loadImage("/Player/PlayerOrientation/north.png");
		pD = ImageLoader.loadImage("/Player/PlayerOrientation/south.png");
		pL = ImageLoader.loadImage("/Player/PlayerOrientation/west.png");
		pR = ImageLoader.loadImage("/Player/PlayerOrientation/east.png");
		
				
		pUR = ImageLoader.loadImage("/Player/PlayerOrientation/northeast.png");
		pUL = ImageLoader.loadImage("/Player/PlayerOrientation/northwest.png");
		pDL = ImageLoader.loadImage("/Player/PlayerOrientation/southwest.png");
		pDR = ImageLoader.loadImage("/Player/PlayerOrientation/southeast.png");
				
				
		idle = new BufferedImage[20];
		meleeAttack = new BufferedImage[15];
		rifleMove = new BufferedImage[20];
		rifleShoot = new BufferedImage[3];
		
		
		idleBird[0] = ImageLoader.loadImage("/Bird/idle/frame-1.png");
		idleBird[1] = ImageLoader.loadImage("/Bird/idle/frame-2.png");
		
				
		for (int i = 0; i < 20; i++) {
			idle[i] = ImageLoader.loadImage("/Player/survivor-idle_rifle_" + i + ".png");
		}
				
		for (int i = 0; i < 15; i++) {
			meleeAttack[i] = ImageLoader.loadImage("/Player/MeleeAttack/survivor-meleeattack_rifle_" + 
													i + ".png");		
		}
				
		for (int i = 0; i < 20; i++) {
			rifleMove[i] = ImageLoader.loadImage("/Player/RifleMove/survivor-move_rifle_" + 
												i + ".png");
		}
		for (int i = 0; i < rifleShoot.length; i++) {
			rifleShoot[i] = ImageLoader.loadImage("/Player/RifleShoot/survivor-shoot_rifle_" +
											i + ".png");
		}
	}
}
