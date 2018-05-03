import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class GoSquareTest {

	@Test
	public void GoSquaretest() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		Player testPlayer = new Player(1, textWindow);
		Player[] newPlayer = new Player[1];
		newPlayer[0] = testPlayer;
		GoSquare gsquare = new GoSquare(newPlayer, 1, 1);
	    assertNotNull(gsquare);
	}

}
