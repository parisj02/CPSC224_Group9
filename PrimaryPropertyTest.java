import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class PrimaryPropertyTest {

	@Test
	public void getNumHoteltest() {
		ZagopolyTextWindow textWindow = new ZagopolyTextWindow();
		PrimaryProperty prop1 = new PrimaryProperty(1, 1, 1, 1, "test", 1, 1);
		prop1.addHouse(textWindow);
		assertEquals(1, prop1.getNumHouses());
	}
}
