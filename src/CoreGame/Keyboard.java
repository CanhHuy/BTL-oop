package CoreGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entities.Nguoichoi;

import Giaodien.Play;

public class Keyboard implements KeyListener {

	private Nguoichoi playerOne, playerTwo;
	private Play play;

	public Keyboard(Nguoichoi p1, Nguoichoi p2, Play pp) {
		playerOne = p1;
		playerTwo = p2;
		play = pp;
	}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int cost;
		switch (keyCode) {
			// 1
			case KeyEvent.VK_Q:
				cost = 200 ;
				if (playerOne.getGold() >= cost) {
					playerOne.queueSolider(cost, 0);
				}
				break;
			case KeyEvent.VK_W:
				cost = 250 ;
				if (playerOne.getGold() >= cost) {
					playerOne.queueSolider(cost, 1);
				}
				break;
			case KeyEvent.VK_E:
				cost = 500 ;
				if (playerOne.getGold() >= cost) {
					playerOne.queueSolider(cost, 2);
				}
				break;
			//2
			case KeyEvent.VK_A:
				if (playerOne.getGold() >= playerOne.getCostEvo()) {
					playerOne.evolve();
				}
				break;		
			case KeyEvent.VK_I:
				cost = 200 + (playerTwo.getEvonow() * 5);
				if (playerTwo.getGold() >= cost) {
					playerTwo.queueSolider(cost, 0);
				}
				break;
			case KeyEvent.VK_O:
				cost = 250 ;
				if (playerTwo.getGold() >= cost) {
					playerTwo.queueSolider(cost, 1);
				}
				break;
			case KeyEvent.VK_P:
				cost = 500 ;
				if (playerTwo.getGold() >= cost) {
					playerTwo.queueSolider(cost, 2);
				}
				break;			
			case KeyEvent.VK_L:
				if (playerTwo.getGold() >= playerTwo.getCostEvo()) {
					playerTwo.evolve();
				}
				break;			
				// Pause
			case KeyEvent.VK_SPACE:
				if (play.getGamePaused()) {
				    play.setGamePaused(false);
				} else {
					play.setGamePaused(true);
				}
				break;
				// Volume 
				case KeyEvent.VK_B:
				if (play.getPlayingMusic()) {
					play.setPlayingMusic(false);
					play.stopPlayingMusic();
				} else {
					play.setPlayingMusic(true);
					play.playMusic();
					}
				break;				
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
