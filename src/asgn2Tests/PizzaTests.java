package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
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
	
    MargheritaPizza margherita;
    VegetarianPizza vegetarian;
    MeatLoversPizza meatLovers;
    MargheritaPizza multiple;
    
    @Before
    public void Before() throws PizzaException, CustomerException, LogHandlerException{
        margherita = new MargheritaPizza(1, LocalTime.parse("19:00:00"), LocalTime.parse("19:20:00"));
        vegetarian = new VegetarianPizza(1, LocalTime.parse("20:00:00"), LocalTime.parse("20:25:00"));
        meatLovers = new MeatLoversPizza(1, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
    	multiple = new MargheritaPizza(4, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));

    }
    
    @Test
    public void testPizzaCreation() throws PizzaException{
    	MargheritaPizza testPizza = new MargheritaPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	VegetarianPizza testPizzaV = new VegetarianPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	MeatLoversPizza testPizzaM = new MeatLoversPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	
    	assertEquals(testPizza.getPizzaType(), "Margherita");
    	assertEquals(testPizzaV.getPizzaType(), "Vegetarian");
    	assertEquals(testPizzaM.getPizzaType(), "Meat Lovers");
    }
    
    @Test (expected=PizzaException.class)
    public void testPizzaExceptionQuantity() throws PizzaException{
    	MargheritaPizza test = new MargheritaPizza(12, LocalTime.parse("19:00:00"), LocalTime.parse("19:20:00"));
    	test.getOrderCost();
    }
    
    @Test (expected=PizzaException.class)
    public void testPizzaExceptionHour() throws PizzaException{
    	VegetarianPizza test = new VegetarianPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("18:45:20"));
    	test.getOrderCost();
    }
    
    @Test
    public void testGetCostPerPizza(){
    	assertEquals(9.5, margherita.getCostPerPizza(),0.001);
    	assertEquals(15.5, vegetarian.getCostPerPizza(),0.001);
    	assertEquals(17.0, meatLovers.getCostPerPizza(),0.001);
    }
    
    @Test
    public void testGetCostPerPizzaMultiple() throws PizzaException{
    	MargheritaPizza testPizzaMulti = new MargheritaPizza(4, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	assertEquals(9.5, testPizzaMulti.getCostPerPizza(), 0.001);
    }
    
    @Test
    public void testGetOrderCost(){
    	assertEquals(17.0, meatLovers.getOrderCost(),0.001);
    	assertEquals(15.5, vegetarian.getOrderCost(),0.001);
    	assertEquals(9.5, margherita.getOrderCost(),0.001);
    }
    @Test
    public void testGetOrderProfit(){
    	assertEquals(0.0, meatLovers.getOrderProfit(), 0.001);
    	assertEquals(0.0, vegetarian.getOrderProfit(), 0.001);
    	assertEquals(0.0, margherita.getOrderProfit(), 0.001);

    }
    @Test
    public void testContainsTopping(){
    	assert(margherita.containsTopping(PizzaTopping.CHEESE));
    	assert(!vegetarian.containsTopping(PizzaTopping.BACON));
    }
    @Test
    public void testGetQuantity(){
    	assertEquals(1, margherita.getQuantity());
    	assertEquals(4, multiple.getQuantity());
    }
    @Test
    public void testGetOrderPrice(){
    	assertEquals(12.0, meatLovers.getOrderPrice(),0.001);
    	assertEquals(10.0, vegetarian.getOrderPrice(),0.001);
    	assertEquals(8.0, margherita.getOrderPrice(),0.001);

    }
    @Test
    public void testGetPricePerPizza(){
    	assertEquals(8.0, margherita.getPricePerPizza(), 0.001);
    	assertEquals(10.0, vegetarian.getPricePerPizza(), 0.001);
    	assertEquals(12.0, meatLovers.getPricePerPizza(), 0.001);
    }

    @Test
    public void testGetPizzaType(){
    	assertEquals(margherita.getPizzaType(), "Margherita");
    	assertEquals(vegetarian.getPizzaType(), "Vegetarian");
    	assertEquals(meatLovers.getPizzaType(), "Meat Lovers");    
	}
	
}
