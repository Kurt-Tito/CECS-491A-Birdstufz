package CECS491B;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import Enemies.SkeletonController;
import Enemies.ZombieController;

public class HighScore {
	private int score = 0;
	private int highScore = 0;
	private int t = 164;
	private ZombieController zombies;
	private SkeletonController skeletons;
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
		g.setFont(new Font("Helvetica", Font.PLAIN, 25)); 
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(getScore()), 10, 28);
	}
	public String checkScore(int i) {
		int convert = Integer.parseInt(ReadHighScore());
		if(i > convert){
		convert = i;
		File file = new File("highscore.txt");
		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch(Exception e){
				
			}
		}
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try{
			writeFile = new FileWriter(file);
			writer = new BufferedWriter(writeFile);
			writer.write(String.valueOf(i));
		}
		catch(Exception e){		
		}
		
		finally{
			try{
				if(writer != null){
					writer.close();
				}
			}
			catch(Exception e){}
		}
		return String.valueOf(i);
		}
		else{
		return ReadHighScore();	
		}
	}
}

