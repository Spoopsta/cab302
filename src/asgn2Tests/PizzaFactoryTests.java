package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.PizzaTopping;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Niall Stone
 * 
 */
public class PizzaFactoryTests {
	Pizza mgh;
	Pizza mtl;
	Pizza veg;
	//initialize variables per-test.
	@Before
	public void Init() throws PizzaException{
		mgh = PizzaFactory.getPizza("PZM", 1, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
		mtl = PizzaFactory.getPizza("PZL", 1, LocalTime.parse("22:00:00"),LocalTime.parse("22:35:00"));
		veg = PizzaFactory.getPizza("PZV", 3, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
	}
	
	//For each possible input to the PizzaFactory Pizza-Type, test that the creation through the Factory.getPizza method was successful.
	//Ensure success by testing each possible input which is handled through .getPizza().
	@Test
	public void testMargheritaFactory(){
    	assertEquals(mgh.getPizzaType(), "Margherita"); 
    	assertEquals(1.5, mgh.getOrderCost(),0.001);
		assertEquals(1.5, mgh.getCostPerPizza(),0.001);
    	assertEquals(8, mgh.getOrderPrice(),0.001);
    	assertEquals(6.5, mgh.getOrderProfit(), 0.001);
    	assert(mgh.containsTopping(PizzaTopping.CHEESE));
	}
	@Test
	public void testMeatLoversFactory(){
    	assertEquals(mtl.getPizzaType(), "Meat Lovers"); 
    	assertEquals(5.0, mtl.getOrderCost(),0.001);
		assertEquals(5.0, mtl.getCostPerPizza(),0.001);
    	assertEquals(12.0, mtl.getOrderPrice(),0.001);
    	assertEquals(7.0, mtl.getOrderProfit(), 0.001);
    	assert(mtl.containsTopping(PizzaTopping.BACON));
	}
	@Test
	public void testVegetarianFactory(){
    	assertEquals(veg.getPizzaType(), "Vegetarian"); 
    	assertEquals(16.5, veg.getOrderCost(),0.001);
		assertEquals(5.5, veg.getCostPerPizza(),0.001);
    	assertEquals(30.0, veg.getOrderPrice(),0.001);
    	assertEquals(13.5, veg.getOrderProfit(), 0.001);
    	assert(veg.containsTopping(PizzaTopping.EGGPLANT));
	}
	//Ensure that in the event of an invalid code, the correct exception is sent.
	@Test (expected = PizzaException.class)
	public void testFactoryException() throws PizzaException{
		@SuppressWarnings("unused")
		Pizza exception = PizzaFactory.getPizza("Not A Code", 1, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
	}
}
