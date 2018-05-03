import static org.junit.Assert.*;

import org.junit.Test;

public class Zagopoly_MainTest {

	@Test
	public void testInitPlayers() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		Player testPlayer = new Player(1, textWindow);
		Player[] newPlayer = new Player[1];
		Zagopoly_Main.initPlayers(newPlayer, textWindow);
		assertNotNull(newPlayer[0]);
	}

}
