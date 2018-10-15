package CECS491B;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class HighScore {
	private int score = 0;
	private String highScore = "";
	
	public String ReadHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try {
	readFile = new FileReader("highscore.txt");
	reader = new BufferedReader(readFile);
	return reader.readLine();
	}
		catch (Exception e) {
			return "0";
		}
		finally
		{
			try {
				reader.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void DrawScore(Graphics g) {
		g.setFont(new Font("Helvetica", Font.PLAIN, 15)); 
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 20, 20);
		g.drawString("Highscore: " + ReadHighScore(), 1485, 20);
	}
}

