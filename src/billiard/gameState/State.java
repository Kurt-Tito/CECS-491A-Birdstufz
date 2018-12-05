package billiard.gameState;

import java.awt.Graphics;

import billiard.game.billiardGame;

public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected billiardGame game;
	
	public State(billiardGame game){
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
