import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class PlayerIconTest {

	@Test
	public void PlayerIconCoordtest() {
		PlayerIcon icon = new PlayerIcon(2);
		int x = icon.getX();
		int y = icon.getY();
		assertEquals(x, 608);
		assertEquals(y,640);
	}

}
