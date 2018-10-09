package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage  floor, sideWall, frontWall, pumpkin, playerShooter;
	public static BufferedImage[] trees = new BufferedImage[4];
	public static BufferedImage[] zombies = new BufferedImage[4];

	public static void init(){
		
		SpriteSheet tree = new SpriteSheet(ImageLoader.loadImage("/texture/Obstacle/sprite_tree0.png"));
		
		floor = ImageLoader.loadImage("/texture/Floor/grasstile_darker.png");
		sideWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_side.png");
		frontWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_front.png");
		frontWall = ImageLoader.loadImage("/texture/Obstacle/tombstone_front.png");
		pumpkin = ImageLoader.loadImage("/texture/Obstacle/Pumpkin.png");
		
		zombies[0] = ImageLoader.loadImage("/texture/Enemy/zombie07.png");
		zombies[1] = ImageLoader.loadImage("/texture/Enemy/zombie10.png");
		zombies[2] = ImageLoader.loadImage("/texture/Enemy/zombie04.png");
		zombies[3] = ImageLoader.loadImage("/texture/Enemy/zombie01.png");
		
		playerShooter = ImageLoader.loadImage("/texture/player.png");
		
		trees[0] = tree.crop(0, 0, width, height);
		trees[1] = tree.crop(width, 0, width, height);
		trees[2] = tree.crop(0, height, width, height);
		trees[3] = tree.crop(width, height, width, height);
	}
}
