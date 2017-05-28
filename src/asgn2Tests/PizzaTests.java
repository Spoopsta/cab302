package asgn2Tests;

import java.time.LocalTime;

import org.junit.Before;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.PizzaRestaurant;


/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	
    MargheritaPizza margherita;
    VegetarianPizza vegetarian;
    MeatLoversPizza meatLovers;
    PizzaRestaurant pizzaRestaurant;
    
    @Before
    public void beforeStuff() throws PizzaException, CustomerException, LogHandlerException{
        margherita = new MargheritaPizza(1, LocalTime.parse("19:00:00"), LocalTime.parse("19:20:00"));
        vegetarian = new VegetarianPizza(2, LocalTime.parse("20:00:00"), LocalTime.parse("20:25:00"));
        meatLovers = new MeatLoversPizza(1, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
        pizzaRestaurant = new PizzaRestaurant();
        pizzaRestaurant.processLog("20170101.txt");
    }
	
}
