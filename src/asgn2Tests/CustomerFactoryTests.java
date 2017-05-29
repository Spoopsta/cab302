package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.CustomerFactory;
import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	// TO DO
	Customer pickUp;
	Customer driver;
	Customer drone;
	
	@Before
	public void setup() throws CustomerException{
		pickUp = CustomerFactory.getCustomer("PUC","John Doe", "0123456789", 0, 0);
		driver = CustomerFactory.getCustomer("DVC","Jane Doe", "0987654321", -3, 4);
		drone = CustomerFactory.getCustomer("DNC","Jimmy James", "0000000000", -3, -5);
	}
	
	@Test
	public void testPickUp(){
		assertEquals("Pick Up", pickUp.getCustomerType());
		assertEquals("John Doe", pickUp.getName());
		assertEquals("0123456789", pickUp.getMobileNumber());
		assertEquals(0, pickUp.getLocationX());
		assertEquals(0, pickUp.getLocationY());
	}
	
	@Test
	public void testDriver(){
		assertEquals("Driver Delivery", driver.getCustomerType());
		assertEquals("Jane Doe", driver.getName());
		assertEquals("0987654321", driver.getMobileNumber());
		assertEquals(-3, driver.getLocationX());
		assertEquals(4, driver.getLocationY());
	}
	
	@Test
	public void testDrone(){
		assertEquals("Drone Delivery", drone.getCustomerType());
		assertEquals("Jimmy James", drone.getName());
		assertEquals("0000000000", drone.getMobileNumber());
		assertEquals(-3, drone.getLocationX());
		assertEquals(-5, drone.getLocationY());
	}
	
	@Test(expected = CustomerException.class)
	public void testWrongCustomerCodeException() throws CustomerException{
		@SuppressWarnings("unused")
		Customer error = CustomerFactory.getCustomer("bad","error", "0123456789", 0, 0);
	}
	
}
