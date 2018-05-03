import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class PropertyTest {

	@Test
	public void getRenttest() {
		Property Prop1 = new Property(1,20,50,1,1);// rent = 50
		assertEquals(Prop1.getRent(), 50);
	}
}
