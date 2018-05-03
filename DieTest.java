import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class DieTest {

	@Test
	public void rollDietest() {
		Die testDie = new Die();
		int rollTest = testDie.rollDie();
		assertNotNull(rollTest);
	}

}
