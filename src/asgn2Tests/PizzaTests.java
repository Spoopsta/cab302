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
    MeatLoversPizza meatLovers;
    VegetarianPizza vegetarian;
    
    @Before
    public void Before() throws PizzaException, CustomerException, LogHandlerException{
        meatLovers = new MeatLoversPizza(4, LocalTime.parse("21:00:00"),LocalTime.parse("21:35:00"));
        margherita = new MargheritaPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
        vegetarian = new VegetarianPizza(3, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    }
    //Ensure that creation is successful.
    @Test
    public void testPizzaCreation() throws PizzaException{
    	MargheritaPizza testPizza = new MargheritaPizza(1, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	VegetarianPizza testPizzaV = new VegetarianPizza(3, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	MeatLoversPizza testPizzaM = new MeatLoversPizza(5, LocalTime.parse("19:40:20"), LocalTime.parse("19:45:20"));
    	
    	assertEquals(testPizza.getPizzaType(), "Margherita");
    	assertEquals(testPizzaV.getPizzaType(), "Vegetarian");
    	assertEquals(testPizzaM.getPizzaType(), "Meat Lovers");
    }
    //Ensure that invalid inputs result in exceptions.
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
    //Ensure that an exception is thrown if the delivery is too late.
    @Test (expected = PizzaException.class)
    public void testDeliveryTooLate() throws PizzaException{
    	VegetarianPizza test = new VegetarianPizza(1, LocalTime.parse("18:40:20"), LocalTime.parse("20:45:20"));
    }
    @Test
    public void testGetCostPerPizza(){
    	assertEquals(5.0, meatLovers.getCostPerPizza(),0.001);
    }
    @Test
    public void testGetPricePerPizza(){
    	assertEquals(12.0, meatLovers.getPricePerPizza(), 0.001);
    }
    @Test
    public void testGetOrderCost(){
    	assertEquals(20, meatLovers.getOrderCost(),0.001);
    }
    @Test
    public void testGetOrderPrice(){
    	assertEquals(48, meatLovers.getOrderPrice(),0.001);
    }
    @Test
    public void testGetOrderProfit(){
    	assertEquals(28, meatLovers.getOrderProfit(), 0.001);
    }
    @Test
    public void testContainsTopping(){
    	assert(meatLovers.containsTopping(PizzaTopping.CHEESE));
    	assert(!meatLovers.containsTopping(PizzaTopping.EGGPLANT));
    }
    @Test
    public void testGetQuantity(){
    	assertEquals(4, meatLovers.getQuantity());
    }
    @Test
    public void testGetPizzaType(){
    	assertEquals(meatLovers.getPizzaType(), "Meat Lovers"); 
	}
    //Subclass-Specific Tests:
    //Ensure that the other subclasses return the correct PizzaType.
    @Test
    public void testGetPizzaTypeMGH(){
    	assertEquals(margherita.getPizzaType(), "Margherita"); 
    }
    @Test
    public void testGetPizzaTypeVEG(){
    	assertEquals(vegetarian.getPizzaType(), "Vegetarian");
    }
    //Ensure that the subclasses return the correct cost.
    @Test
    public void testGetOrderCostMGH(){
    	assertEquals(1.5, margherita.getOrderCost(),0.001);
    }
    @Test
    public void testGetOrderCostVEG(){
    	assertEquals(16.5, vegetarian.getOrderCost(),0.001);
    }    
    //Ensure subclasses return correct price.
    @Test
    public void testGetOrderPriceMGH(){
    	assertEquals(8, margherita.getOrderPrice(),0.001);
    }
    @Test
    public void testGetOrderPriceVEG(){
    	assertEquals(30, vegetarian.getOrderPrice(),0.001);
    }
    //Ensure subclasses return correct profit.
    @Test
    public void testGetOrderProfitMGH(){
    	assertEquals(6.5, margherita.getOrderProfit(), 0.001);
    }
    @Test
    public void testGetOrderProfitVEG(){
    	assertEquals(13.5, vegetarian.getOrderProfit(), 0.001);
    }
    //Ensure subclasses return correct toppings.
    @Test
    public void testContainsToppingMGH(){
    	assert(margherita.containsTopping(PizzaTopping.CHEESE));
    	assert(!meatLovers.containsTopping(PizzaTopping.EGGPLANT));
    }	
    @Test
    public void testContainsToppingVEG(){
    	assert(vegetarian.containsTopping(PizzaTopping.EGGPLANT));
    	assert(!vegetarian.containsTopping(PizzaTopping.BACON));
    }	
}
