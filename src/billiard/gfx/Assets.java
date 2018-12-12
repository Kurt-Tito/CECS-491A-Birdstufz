package billiard.gfx;

import java.awt.image.BufferedImage;

import gfx.ImageLoader;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage heroForward;
	public static BufferedImage playerBunny;

	public static void init(){
		
		
		heroForward = ImageLoader.loadImage("/billiardPlayer/forward.png");
		playerBunny = ImageLoader.loadImage("/billiardPlayer/bunnyD.png");

	}
}
