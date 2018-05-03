import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class GameBoardUITest {

	@Test
	public void resetRollTest() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		PlayerIcon[] IconArray = new PlayerIcon[1];
		GameBoardUI gui = new GameBoardUI(IconArray, 2, textWindow);
		gui.resetRoll();
		assertEquals(0, gui.getRoll());
		gui.getRoll();
	}

}
