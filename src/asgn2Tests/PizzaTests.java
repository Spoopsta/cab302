package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	// TO DO
	MargheritaPizza margherita;
	VegetarianPizza vegetarian;
	MeatLoversPizza meatLovers;
	
	@Before
	public void beforeStuff() throws PizzaException{
		margherita = new MargheritaPizza(1, LocalTime.parse("19:00:00"), LocalTime.parse("19:20:00"));
		vegetarian = new VegetarianPizza(2, LocalTime.parse("20:00:00"), LocalTime.parse("20:25:00"));
		meatLovers = new MeatLoversPizza(1, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
	}
	
	@Test
	public void dungive(){
		assertEquals(margherita.getPizzaType(), "Margherita");
	}
}
