import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class RestaurantSquareTest {

	@Test
	public void test() {
		RestaurantSquare rest1 = new RestaurantSquare(1, "rest", 1, 1);
	    assertTrue(rest1.isRestaurant());
	}

}
