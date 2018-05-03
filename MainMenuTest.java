import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MainMenuTest {

	//no non gui methods in mainmenu
	@Test
	public void test() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		PlayerIcon  testIcon = new PlayerIcon(2);
		PlayerIcon[] playerIcon = new PlayerIcon[1];
		playerIcon[0] = testIcon;
		GameBoardUI gb = new GameBoardUI(playerIcon, 1, textWindow);
		MainMenu menu = new MainMenu(gb);
		try {
			menu.openInstructions();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
