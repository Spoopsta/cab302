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
		System.out.println("0123456789");
		pickup = new PickUpCustomer("John Doe", "0123456789", 0, 0);
		driver = new DriverDeliveryCustomer("Jane Doe", "0987654321", -3, 4);
		drone = new DroneDeliveryCustomer("Jimmy James", "0000000000", -3, -5);
	}
	
	//Testing the asgn2Customers.Customer abstract class with asgn2Customers.DriverDeliveryCustomer==
	@Test
	public void testGetName(){
		assertEquals(driver.getName(), "Jane Doe");
	} 
	
	@Test
	public void testGetMobileNumber(){
		assertEquals(driver.getMobileNumber(), "0987654321");
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
		assertEquals(pickup.getCustomerType(), "Pick Up");
	}
	
	@Test
	public void testGetCustomerNamePickUp(){
		assertEquals(pickup.getName(), "John Doe");
	}
	
	@Test
	public void testGetCustomerMobilePickUp(){
		assertEquals(pickup.getMobileNumber(), "0123456789");
	}
	
	@Test
	public void testGetDeliveryDistancePickUp(){
		assert(pickup.getDeliveryDistance() == 0);
	}
	//=================================================================================================
	
	//Testing asgn2Customers.DroneDeliveryCustomer=====================================================
	@Test
	public void testGetCustomerTypeDrone(){
		assertEquals(drone.getCustomerType(), "Drone Delivery");
	}
	
	@Test
	public void testGetCustomerNameDrone(){
		assertEquals(drone.getName(), "Jimmy James");
	}
	
	@Test
	public void testGetCustomerMobileDrone(){
		assertEquals(drone.getMobileNumber(), "0000000000");
	}
	
	@Test
	public void testGetDeliveryDistanceDrone(){
		assert(drone.getDeliveryDistance() == Math.sqrt((Math.pow(0 - drone.getLocationX(), 2) + (Math.pow((0-drone.getLocationY()), 2)))));
	}
	//=================================================================================================

}
