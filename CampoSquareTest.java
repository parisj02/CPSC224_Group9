 import static org.junit.Assert.*;

import org.junit.Test;

public class CampoSquareTest {

	@Test
	public void isCampoSquaretest() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		Player testPlayer = new Player(1, textWindow);
		Player[] newPlayer = new Player[1];
		newPlayer[0] = testPlayer;
		Board testBoard = new Board(newPlayer);
		assertFalse(testBoard.getSquare(0).isCampoSquare());
	}
}
