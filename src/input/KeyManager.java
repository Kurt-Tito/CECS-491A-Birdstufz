package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public Set<Character> pressed;
	
	public KeyManager(){
		keys = new boolean[256];
		pressed = new HashSet<Character>();
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		pressed.add(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		pressed.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public Set<Character> getKeysPressed() {
		return pressed;
	}

}