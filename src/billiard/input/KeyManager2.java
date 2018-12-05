package billiard.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyManager2 implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right, space, d_up, d_down, d_left, d_right, enter;
	public Set<Character> pressed, pressed2;
	
	public KeyManager2(){
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
		
		
//		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			pressed2.add(e.getKeyChar());
//			System.out.println(pressed2.size());
//		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
			pressed2.add('a');
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			pressed2.add('b');
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			pressed2.add('c');
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			pressed2.add('d');
		
		
			
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
			pressed.add(e.getKeyChar());
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
			pressed2.remove('a');
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			pressed2.remove('b');
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			pressed2.remove('c');
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			pressed2.remove('d');
		
//		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
//			pressed2.remove(e.getKeyChar());
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
			pressed.remove(e.getKeyChar());
		
		
		
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


