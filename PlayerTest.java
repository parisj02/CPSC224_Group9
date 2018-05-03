import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void finePlayertest() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		Player newPlayer = new Player(1,textWindow);
		newPlayer.getBalance();
		Assert.assertEquals(newPlayer.getBalance(), 1500);
		newPlayer.finePlayer(1500);
		Assert.assertEquals(newPlayer.getBalance(), 0);
	}

}
