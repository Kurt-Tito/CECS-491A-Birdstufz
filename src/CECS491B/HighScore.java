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
	private int addScore = 0;
	private int t = 164;
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
	public void addScore(int i) {
		if(i == 1) {//adds 100 for zombies
		score += t;
		}
		else {//adds 200 for skeleton
		score += (2 * t);
		}
	}

	public void compareScores() {
		if(getScore() > Integer.parseInt(highScore)) {
		highScore = Integer.toString(score);
		}
	}
	public void setScore(int score) {
	this.score = score;
	}
	public int getScore() {
		return score;
	}
	
	public String getScore_s() {
		String score_s = Integer.toString(score);
		return score_s;
	}
	
	public void DrawScore(Graphics g) {
		g.setFont(new Font("Helvetica", Font.PLAIN, 15)); 
		g.setColor(Color.WHITE);
		g.drawString("Score: " + getScore(), 20, 20);
		//g.drawString("Highscore: " + ReadHighScore(), 1480, 20);
	}
}

