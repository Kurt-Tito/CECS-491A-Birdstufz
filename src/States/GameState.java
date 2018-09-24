package States;

import java.awt.Graphics;

import entities.creatures.Player;
import game.Game;

public class GameState extends State {
	
	private Player player;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, 100, 100);
	}
	
	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}