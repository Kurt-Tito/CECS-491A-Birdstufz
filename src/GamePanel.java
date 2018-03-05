import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class GamePanel extends JPanel{
	private static final String PATH_BANNER = "images/panel.png";
	protected static BufferedImage brand, banner;
	protected StatusBar statusBar;
	static
	{
		try
		{
			banner = ImageIO.read(new File(PATH_BANNER));
		} catch(IOException e)
		{
			System.out.println("Files for status bar not found");
		}
	}
	public void initializeStatusBar()
	{
		setLayout(new BorderLayout());
		statusBar = new StatusBar();
		add(BorderLayout.SOUTH, statusBar);
	}
	public abstract void reset();
	public abstract void addListener(ActionListener al);
	public abstract void removeListener(ActionListener al);
}
