package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a dummy class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature  as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Nicholas Stolberg and Niall Stone
 *
 */
@SuppressWarnings("unused")
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {	
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;
	
	//Create Variable to contain log info.
	LinkedList<String> orderLog = new LinkedList<String>();
	LinkedList<String> customerLog = new LinkedList<String>();
	double totalProfit = 0;
	double totalDistance = 0;
	
	//Create Main Panels
	private JPanel main = new JPanel();
	private JPanel btnPanel;
	private JPanel txtFieldPanel;
	private JPanel totalsPanel;
	
	//Create Sub Panels
	private JPanel ordersPanel;
	private JPanel customersPanel;
	private JPanel profitPanel;
	private JPanel distancePanel;
	
	//Create Text areas
	private JTextArea pizzaText;
	private JTextArea customerText;
	private JTextArea profitText;
	private JTextArea distanceText;
	
	//Create Labels.
	private JLabel ordersLBL;
	private JLabel customersLBL;
	private JLabel profitLBL;
	private JLabel distanceLBL;
	private JLabel GuiStateLBL;
	
	//Scroll Pane
	JScrollPane pizzaScroll;
	JScrollPane customerScroll;
	
	//Buttons and chooser
	private JButton loadLogFileBTN;
	private JButton displayLogBTN;
	private JButton displayTotalsBTN;
	private JButton resetBTN;
	private JFileChooser chooser = new JFileChooser();
		
	private PizzaRestaurant restaurant = new PizzaRestaurant();
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
	}
	
	private void createGUI(){	
		//Create window
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//Set main panel to use box layout
		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
		
		//Initialise GuiState label
		GuiStateLBL = new JLabel("Program Started.");
		GuiStateLBL.setAlignmentX(CENTER_ALIGNMENT);
		
		//initialise text areas
		pizzaText = new JTextArea("");
		pizzaText.setAlignmentX(CENTER_ALIGNMENT);
		customerText = new JTextArea("");
		customerText.setAlignmentX(CENTER_ALIGNMENT);
		profitText = new JTextArea("");
		profitText.setAlignmentX(CENTER_ALIGNMENT);
		distanceText = new JTextArea("");
		distanceText.setAlignmentX(CENTER_ALIGNMENT);
		
		//initialise panel and set size etc
		ordersPanel = createPanel(Color.WHITE);
		customersPanel = createPanel(Color.WHITE);
		profitPanel = createPanel(Color.WHITE);
		distancePanel = createPanel(Color.WHITE);
		
		//Set the layout setting for all panels.
		ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.PAGE_AXIS));
		customersPanel.setLayout(new BoxLayout(customersPanel, BoxLayout.PAGE_AXIS));
		profitPanel.setLayout(new BoxLayout(profitPanel, BoxLayout.PAGE_AXIS));
		distancePanel.setLayout(new BoxLayout(distancePanel, BoxLayout.PAGE_AXIS));
		
		//initialise scrollpanes.
		customerScroll = new JScrollPane(customerText);
		pizzaScroll = new JScrollPane( pizzaText );
		
		//set size of scroll Pane
		pizzaScroll.setPreferredSize(new Dimension(WIDTH/2, 340));
		pizzaScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pizzaScroll.setAlignmentX(CENTER_ALIGNMENT);
		customerScroll.setPreferredSize(new Dimension(WIDTH/2, 340));
		customerScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Put border around panels.
		profitPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		distancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		profitPanel.setPreferredSize(new Dimension(WIDTH/2, 270));
		distancePanel.setPreferredSize(new Dimension(WIDTH/2, 270));
		
		//initialise and set specifications of panel.
		txtFieldPanel = createPanel(Color.WHITE);
		txtFieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		txtFieldPanel.setLayout(new BoxLayout(txtFieldPanel, BoxLayout.LINE_AXIS));
		txtFieldPanel.setPreferredSize(new Dimension(WIDTH, 340));
		txtFieldPanel.add(ordersPanel);
		txtFieldPanel.add(customersPanel);
		
		//initialise and set specifications of panel.
		totalsPanel = createPanel(Color.WHITE);
		totalsPanel.setPreferredSize(new Dimension(WIDTH, 270));
		totalsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.LINE_AXIS));
		totalsPanel.add(profitPanel);
		totalsPanel.add(distancePanel);
		
		//set up button panel and add to main frame.
		btnPanel = createPanel(Color.GRAY);
		this.getContentPane().add(main, BorderLayout.PAGE_START);
		this.getContentPane().add(btnPanel,BorderLayout.SOUTH);
		
		//put secondary panels in main panel
		main.add(txtFieldPanel);
		main.add(totalsPanel);
		main.add(GuiStateLBL);
		
		//Initialise buttons and set correct ones to un-enabled.
		loadLogFileBTN = createButton("Load Log File.");
		displayLogBTN = createButton("Display Log.");
		displayTotalsBTN = createButton("Display Totals");
		resetBTN = createButton("Reset");
		displayLogBTN.setEnabled(false);
		displayTotalsBTN.setEnabled(false);
		resetBTN.setEnabled(false);
		
		//initialies Labels.
		ordersLBL = createLabel("Pizza Orders", ordersPanel);
		customersLBL = createLabel("Customers", customersPanel);
		profitLBL = createLabel("Total Profit", profitPanel);
		distanceLBL = createLabel("Total Distance", distancePanel);
		
		layoutButtonPanel();
		
		displayLog();
		displayTotals();
		
		this.setVisible(true);
	}
	
	private void displayLog(){			
		pizzaText.setEditable(false);
		customerText.setEditable(false);
		
		pizzaText.setBorder(new EmptyBorder(10,10,10,10));
		customerText.setBorder(new EmptyBorder(10,10,10,10));
		
		ordersPanel.add(pizzaScroll);
		customersPanel.add(customerScroll);
				
	}
	
	private void displayTotals(){
		profitText.setBorder(new EmptyBorder(10,10,10,10));
		distanceText.setBorder(new EmptyBorder(10,10,10,10));
		
		profitPanel.add(profitText);
		distancePanel.add(distanceText);
	}
	
	private JButton createButton(String btnText){
		JButton btn = new JButton();
		btn.setText(btnText);
		btn.addActionListener(this);
		return btn;
	}
	
	private JPanel createPanel(Color c) {
		JPanel panel = new JPanel();
		panel.setBackground(c);
		return panel;
	} 
	
	private JLabel createLabel(String text, JPanel p){
		JLabel l = new JLabel(text);
		l.setAlignmentX(CENTER_ALIGNMENT);
		p.add(l);
		return l;
	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		btnPanel.setLayout(layout);
		//Lots of layout code here
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		
		addToPanel(btnPanel, resetBTN, constraints, 30, 0, 2, 1);
		addToPanel(btnPanel, loadLogFileBTN,constraints, 0, 0,2,1);
		addToPanel(btnPanel, displayLogBTN,constraints,10,0,2,1);
		addToPanel(btnPanel, displayTotalsBTN, constraints, 20,0,2,1);
	} 
	
	/**
	*
	* A convenience method to add a component to given grid bag
	* layout locations. Code due to Cay Horstmann
	*
	* @param c the component to add
	* @param constraints the grid bag constraints to use
	* @param x the x grid position
	* @param y the y grid position
	* @param w the grid width of the component
	* @param h the grid height of the component
	*/
	private void addToPanel(JPanel jp,Component c, GridBagConstraints
	constraints,int x, int y, int w, int h) {
	constraints.gridx = x;
	constraints.gridy = y;
	constraints.gridwidth = w;
	constraints.gridheight = h;
	jp.add(c, constraints);
	}
	
	private void createLogString() throws PizzaException, CustomerException{
		//Clear lists.
		orderLog.clear();
		customerLog.clear();
		
		//Fill lists with necessary information.
		for(int i = 0; i < restaurant.getNumPizzaOrders(); i++){
			orderLog.add("Pizza Type: " + restaurant.getPizzaByIndex(i).getPizzaType() + "\n"
					+ "Quantity: " + restaurant.getPizzaByIndex(i).getQuantity() + "\n"
					+ "Order Price: $" + restaurant.getPizzaByIndex(i).getOrderPrice() + "\n"
					+ "Order Cost: $" + restaurant.getPizzaByIndex(i).getOrderCost() + "\n"
					+ "Profit: $" + restaurant.getPizzaByIndex(i).getOrderProfit() + "\n" + "\n");
		}
		
		for(int i = 0; i < restaurant.getNumCustomerOrders(); i++){
			customerLog.add("Customer Name: " + restaurant.getCustomerByIndex(i).getName() + "\n"
					+ "Mobile Number: " +  restaurant.getCustomerByIndex(i).getMobileNumber() + "\n"
					+ "Delivery Type: " + restaurant.getCustomerByIndex(i).getCustomerType() + "\n"
					+ "X Location: " + restaurant.getCustomerByIndex(i).getLocationX() + "\n"
					+ "Y Location: " + restaurant.getCustomerByIndex(i).getLocationY() + "\n"
					+ "Delivery Distance: " + restaurant.getCustomerByIndex(i).getDeliveryDistance() + "\n" + "\n");
		}
	}
	
	private void calculateTotals() throws PizzaException, CustomerException{
		totalProfit = 0;
		totalDistance = 0;
		for(int i = 0; i < restaurant.getNumPizzaOrders(); i++){
			totalProfit += restaurant.getPizzaByIndex(i).getOrderProfit(); 
		}
		for(int i = 0; i < restaurant.getNumCustomerOrders(); i++){
			totalDistance += restaurant.getCustomerByIndex(i).getDeliveryDistance(); 
		}
	}
	
	private void reset(){
		orderLog.clear();
		customerLog.clear();
		totalProfit = 0;
		totalDistance = 0;
		restaurant = new PizzaRestaurant();
		
		pizzaText.setText("");
		customerText.setText("");
		profitText.setText("");
		distanceText.setText("");
		
		displayLogBTN.setEnabled(false);
		displayTotalsBTN.setEnabled(false);
		resetBTN.setEnabled(false);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new PizzaGUI("BorderLayout"));
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		//When specified btn is pressed.
		if (src==loadLogFileBTN){
			//Open file chooser
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("Opening: " + file.getName() + ".");
                try {
                	//replace pesky backslashes.
                	String Path = null;
                	Path = file.getAbsolutePath().replaceAll("\\\\", "/");
                	System.out.println(Path);
                	
                	//Process Log.
                	if(restaurant.processLog(Path)){
                		createLogString();
                		calculateTotals();
                		
                		//Enable buttons to be pressed.
                		displayLogBTN.setEnabled(true);
                		displayTotalsBTN.setEnabled(true);
                		resetBTN.setEnabled(true);
                		
                		GuiStateLBL.setText("Selected file " + file.getName() + " successfully parsed.");
                	}
				} catch (CustomerException | PizzaException | LogHandlerException e1) {
					GuiStateLBL.setText("Unable to open or process selected file.");
					e1.printStackTrace();
				}
            } else {
            	GuiStateLBL.setText("Open command cancelled by user.");
            	System.out.println("Open command cancelled by user.");
            }			
		}
		
		//when specified button is pressed.
		if (src == displayLogBTN){		
			
			GuiStateLBL.setText("Displaying Log Data.");
			
			//add header to text box and append all necessary informaiton.
			pizzaText.setText("Orders" + "\n\n");
			customerText.setText("Customers" + "\n\n");
			
			for(String item: orderLog){
				pizzaText.append(item);
			}
			
			for(String item: customerLog){

				customerText.append(item);;
			}
			
			displayLog();
		}
		if(src == displayTotalsBTN){
			GuiStateLBL.setText("Displaying Store Totals");
			//Set text to display necessary information.
			 totalDistance = Math.round((totalDistance * 100.0) )/ 100.0;
			profitText.setText("$" + Double.toString(totalProfit));
			distanceText.setText(Double.toString(totalDistance));
			displayTotals();
		}
		if(src == resetBTN){
			GuiStateLBL.setText("Program Reset");
			reset();
		}
	}
	
	@Override
	public void run() {
		createGUI();
	}

	

}
