package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;
import asgn2Pizzas.Pizza;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Niall Stone
* 
*/
public class LogHandlerPizzaTests {
	public ArrayList<Pizza> PizzaList;
	@Before
	public void Init() throws PizzaException, LogHandlerException{
		PizzaList = LogHandler.populatePizzaDataset(".//logs/20170101.txt");
	}
	
	//Ensure that PopulatePizzaDataset runs without any exceptions.
	@Test
	public void testPopulation() throws PizzaException, LogHandlerException{
		@SuppressWarnings("unused")
		ArrayList<Pizza> populationTest = LogHandler.populatePizzaDataset(".//logs/20170101.txt");
	}
	
	//By testing the various values associated with the first line of input,
	//We can test the validity of the LogHandler methods.
	@Test
	public void testPizzaType(){
		assertEquals(PizzaList.get(0).getPizzaType(), "Vegetarian");
	}
	@Test
	public void testCalculateCost(){
		assertEquals(11.0,PizzaList.get(0).getOrderCost(), 0.001);
	}
	@Test
	public void testGetOrderPrice(){
		assertEquals(20, PizzaList.get(0).getOrderPrice(),0.001);
	}
	@Test
	public void testGetPricePer(){
		assertEquals(10.0, PizzaList.get(0).getPricePerPizza(),0.001);
	}
	@Test
	public void testGetOrderProfit(){
		assertEquals(9.0, PizzaList.get(0).getOrderProfit(),0.001);
	}
	@Test
	public void testGetQuantity(){
		assertEquals(2, PizzaList.get(0).getQuantity());
	}
	
	//By testing other lines, we can ensure that lines other than the
	//First are imported successfully. If any of these fail, we know that 
	//The issue lies with later imports.
	@Test
	public void testSecondPizza(){
		assertEquals(PizzaList.get(1).getPizzaType(), "Margherita");
		assertEquals(1.5,PizzaList.get(1).getOrderCost(), 0.001);
		assertEquals(8.0, PizzaList.get(1).getOrderPrice(),0.001);
		assertEquals(8.0, PizzaList.get(1).getPricePerPizza(),0.001);
		assertEquals(6.5, PizzaList.get(1).getOrderProfit(),0.001);
		assertEquals(1, PizzaList.get(1).getQuantity());
	}
	
	//By testing another log file, we can test the LogHandler's ability
	//To work with different files.
	@Test
	public void testSecondLog() throws PizzaException, LogHandlerException{
		PizzaList = LogHandler.populatePizzaDataset(".//logs/20170102.txt");
		assertEquals(PizzaList.get(1).getPizzaType(), "Meat Lovers");
		assertEquals(45.0,PizzaList.get(1).getOrderCost(), 0.001);
		assertEquals(108.0, PizzaList.get(1).getOrderPrice(),0.001);
		assertEquals(12.0, PizzaList.get(1).getPricePerPizza(),0.001);
		assertEquals(63.0, PizzaList.get(1).getOrderProfit(),0.001);
		assertEquals(9, PizzaList.get(1).getQuantity());
	}
	
	//Test the possible bad inputs to the LogHandler methods.
	//First, a nonexistent file.
	@Test (expected = LogHandlerException.class)
	public void testFileException() throws PizzaException, LogHandlerException{
		PizzaList = LogHandler.populatePizzaDataset("Not A File");
	}
	//Then, test adding a null line.
	@Test (expected = LogHandlerException.class)
	public void testAddNullLine() throws PizzaException, LogHandlerException{
		@SuppressWarnings("unused")
		Pizza test = LogHandler.createPizza(null);
	}
	//Finally, test adding a broken log line.
	@Test (expected = LogHandlerException.class)
	public void testAddBrokenLine() throws PizzaException, LogHandlerException{
		@SuppressWarnings("unused")
		Pizza test = LogHandler.createPizza("This isn't A Valid Log Line");
	}
}
