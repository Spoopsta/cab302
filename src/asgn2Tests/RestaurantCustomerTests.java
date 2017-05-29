package asgn2Tests;

import static org.junit.Assert.*;


import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

import org.junit.Before;
import org.junit.Test;

import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Nicholas Stolberg
 */
public class RestaurantCustomerTests {
	// TO DO
	PizzaRestaurant restaurant = new PizzaRestaurant();
	double dist = 10 + 0 + Math.sqrt((Math.pow(0 - 3, 2) + (Math.pow((0-4), 2))));
	
	@Before
	public void PopulateDataset(){
		try {
			restaurant.processLog(".//logs/20170101.txt");
		} catch (CustomerException | PizzaException | LogHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCustomerByIndex() throws CustomerException{
		String custName = null;
		custName = restaurant.getCustomerByIndex(0).getName();
		assertEquals(custName, "Casey Jones");
	}
	
	@Test
	public void testGetNumberOfCustomerOrders() throws CustomerException{
		int num = 0;
		num = restaurant.getNumCustomerOrders();
		assertEquals(num, 3);
	}
	
	@Test
	public void testGetTotalDeliveryDistance(){
		assert(dist == restaurant.getTotalDeliveryDistance());
	}
	
	@Test(expected = CustomerException.class)
	public void testGetCustomerByIndexException() throws CustomerException{
		restaurant.getCustomerByIndex(4);
	}
	
}
