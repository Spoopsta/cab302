package asgn2Restaurant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Nicholas Stolberg and Niall Stone
 *
 */
public class LogHandler {
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		BufferedReader re;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String line;
		try {
			re = new BufferedReader(new FileReader(filename));
			while((line = re.readLine()) != null) {			    
			    customers.add(createCustomer(line));
			}
			
			re.close();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			throw new LogHandlerException("There was a problem parsing the line from the log file:" + ex);
		}
		return customers;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		// add in the pizza exception stuff.

		BufferedReader in = null;
		String textLine;
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		String line;
		
		try {
			in = new BufferedReader(new FileReader(filename));
			while((line = in.readLine()) != null) {
			    textLine = line;
			    
			    pizzas.add(createPizza(textLine));
			}
			
			in.close();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			throw new LogHandlerException("There was a problem parsing the line from the log file:" + ex);
		}	
		
		return pizzas;
	}		
	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		// TO DO
		//Example string: 19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2
		String[] values;
		try{
			values = line.split(",");
		} catch(Exception ex){
			throw new LogHandlerException("There was a problem parsing the line from the log file:" + ex);
		}
		return CustomerFactory.getCustomer(values[4], values[2], values[3], Integer.parseInt(values[5]), Integer.parseInt(values[6]));
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		//need to add pizzaException semantic errors.
		// TO DO


		LocalTime orderTime, deliveryTime;
		int quantity;
		String pizzaCode;
		String[] values;
		try{
			values = line.split(",");
		} catch(Exception ex){
			throw new LogHandlerException("There was a problem parsing the line from the log file:" + ex);
		}

		orderTime = LocalTime.parse(values[0]);
		deliveryTime = LocalTime.parse(values[1]);
		quantity = Integer.parseInt(values[8]);
		pizzaCode = values[7];

		return PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
	}

}
