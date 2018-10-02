package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage player, dirt, grass, stone, playerShooter;
	public static BufferedImage[] trees;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
		trees = new BufferedImage[4];
		
		SpriteSheet tree = new SpriteSheet(ImageLoader.loadImage("/texture/Tree.png"));
		
		trees[0] = tree.crop(0, 0, width, height);
		trees[1] = tree.crop(width, 0, width, height);
		trees[2] = tree.crop(0, height, width, height);
		trees[3] = tree.crop(width, height, width, height);
		
		playerShooter = ImageLoader.loadImage("/texture/player.png");
		player = sheet.crop(0, 0, width, height);
		//dirt = sheet.crop(width, 0, width, height);
		//grass = sheet.crop(width * 2, 0, width, height);
		//stone = sheet.crop(width * 3, 0, width, height);
		//tree = sheet.crop(0, height, width, height);
	}
}
