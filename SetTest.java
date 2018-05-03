import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class SetTest {

	@Test
	public void test() {
		Set set = new Set(2);
		assertEquals(2, set.setSize());
	}

}
