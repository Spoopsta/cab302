package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	PizzaRestaurant testRestaurant;
	
	@Before
	public void init() throws CustomerException, PizzaException, LogHandlerException{
		testRestaurant = new PizzaRestaurant();
		testRestaurant.processLog(".//logs/20170101.txt");
	}
	
	@Test
	public void testProcessLog() throws CustomerException, PizzaException, LogHandlerException{
		PizzaRestaurant testProcessLog = new PizzaRestaurant();
		assert(testProcessLog.processLog(".//logs/20170101.txt"));
	}
	
	@Test
	public void testMultipleLogs() throws CustomerException, PizzaException, LogHandlerException{
		PizzaRestaurant testMultipleLogs = new PizzaRestaurant();
		assert(testMultipleLogs.processLog(".//logs/20170101.txt"));
		assert(testMultipleLogs.processLog(".//logs/20170102.txt"));
		assert(testMultipleLogs.processLog(".//logs/20170103.txt"));
	}
	
	//Should throw a LogHandlerException when an invalid log is passed.
	@Test (expected = LogHandlerException.class)
	public void testFailedLog() throws CustomerException, PizzaException, LogHandlerException{
		PizzaRestaurant testFailedLog = new PizzaRestaurant();
		testFailedLog.processLog("Not A Log File");
	}
	
	//Ensure that the set is empty after a failure to read the log, or in the event of any other exception.
	@Test 
	public void testFailedSetsToEmpty(){
		PizzaRestaurant testFailedLog = new PizzaRestaurant();
		try {
			testFailedLog.processLog("Not A Log File");
		} catch (CustomerException | PizzaException | LogHandlerException e) {
			assert(testFailedLog.getNumPizzaOrders()==0);
		}
	}
	
	@Test
	public void testGetPizzaIndex() throws PizzaException{
		assertEquals("Vegetarian", testRestaurant.getPizzaByIndex(0).getPizzaType());
		assertEquals("Margherita", testRestaurant.getPizzaByIndex(1).getPizzaType());
	}
	
	//Should throw PizzaException when an index is passed which is too large.
	@Test(expected=PizzaException.class)
	public void testGetPizzaIndexException() throws PizzaException{
		testRestaurant.getPizzaByIndex(32);
	}
	
	@Test
	public void testGetNumPizzaOrders(){
		assertEquals(3, testRestaurant.getNumPizzaOrders());
	}
	
	@Test
	public void testTotalprofit(){
		assertEquals(36.5, testRestaurant.getTotalProfit(),0.001);
	}
	
	@Test
	public void testResetDetails(){
		testRestaurant.resetDetails();
		assert(testRestaurant.getNumPizzaOrders()==0);
	}
	
	
	
	
	
}
