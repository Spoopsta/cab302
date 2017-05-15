package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Nicholas Stolberg
 * 
 *
 */
public class CustomerTests {
	// TO DO
	
	PickUpCustomer pickup;
	DriverDeliveryCustomer driver;
	DroneDeliveryCustomer drone;
	
	@Before
	public void makeCustomers() throws CustomerException{
		pickup = new PickUpCustomer("John Doe", "12345678", 1, 2);
		driver = new DriverDeliveryCustomer("Jane Doe", "87654321", -3, 4);
		drone = new DroneDeliveryCustomer("Jimmy James", "00000000", -3, -5);
	}
	
	//Testing the asgn2Customers.Customer abstract class with asgn2Customers.DriverDeliveryCustomer==
	@Test
	public void testGetName(){
		assertEquals(driver.getName(), "Jane Doe");
	} 
	
	@Test
	public void testGetMobileNumber(){
		assertEquals(driver.getMobileNumber(), "87654321");
	} 
	
	@Test
	public void testGetCustomerType(){
		assertEquals(driver.getCustomerType(), "Driver Delivery");
	} 
	
	@Test
	public void testGetLocationX(){
		assertEquals(driver.getLocationX(), -3);
	} 
	
	@Test
	public void testGetLocationY(){
		assertEquals(driver.getLocationY(), 4);
	} 
	
	@Test
	public void testGetDeliveryDistance(){
		assert(driver.getDeliveryDistance() == 7);
	}
	//=================================================================================================
	
	//Testing asgn2Customers.PickUpCustomer============================================================
	@Test
	public void testGetCustomerTypePickUp(){
		assertEquals(driver.getCustomerType(), "Pick Up");
	}
	
	@Test
	public void testGetCustomerNamePickUp(){
		assertEquals(driver.getName(), "Jimmy James");
	}
	
	@Test
	public void testGetCustomerMobilePickUp(){
		assertEquals(driver.getName(), "00000000");
	}
	
	@Test
	public void testGetDeliveryDistancePickUp(){
		assert(driver.getDeliveryDistance() == 3);
	}
	//=================================================================================================
	
	//Testing asgn2Customers.DroneDeliveryCustomer=====================================================
	@Test
	public void testGetCustomerTypeDrone(){
		assertEquals(driver.getCustomerType(), "Drone Delivery");
	}
	
	@Test
	public void testGetCustomerNameDrone(){
		assertEquals(driver.getName(), "John Doe");
	}
	
	@Test
	public void testGetCustomerMobileDrone(){
		assertEquals(driver.getName(), "12345678");
	}
	
	@Test
	public void testGetDeliveryDistanceDrone(){
		assert(driver.getDeliveryDistance() == 8);
	}
	//=================================================================================================

}
