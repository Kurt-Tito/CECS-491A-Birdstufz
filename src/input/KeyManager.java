package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right, space, d_up, d_down, d_left, d_right, enter;
	public Set<Character> pressed;
	public Set<Character> pressed2;
	
	public KeyManager(){
		keys = new boolean[256];
		pressed = new HashSet<Character>();
		pressed2 = new HashSet<Character>();
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		
		d_up = keys[KeyEvent.VK_UP];
		d_down = keys[KeyEvent.VK_DOWN];
		d_left = keys[KeyEvent.VK_LEFT];
		d_right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_SPACE)
			pressed.add(e.getKeyChar());
		else
			pressed2.add(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_SPACE)
			pressed.remove(e.getKeyChar());
		else
			pressed2.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public Set<Character> getKeysPressed() {
		return pressed;
	}
	
	public Set<Character> getKeysPressed2() {
		return pressed2;
	}

}


