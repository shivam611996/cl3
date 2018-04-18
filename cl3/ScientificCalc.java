/*
how to execute this code

1) go to that folder in which the program is stored using cd folder_name

2) open terminal ("ctrl+alt+t")

3) type the folloing commands to run this program
    "javac ScientificCalc.java"
    and hit enter
    "java ScientificCalc"
    and hit enter

double result3 = Math.floorDiv(-100,9)
or
  a = num1.getText().toString();
                b = num2.getText().toString();
                            try{
                                BigDecimal a1 = new BigDecimal(Double.parseDouble(a));
                                BigDecimal b1 = new BigDecimal(Double.parseDouble(b));
                                BigDecimal div_ans = a1.divide(b1,5,BigDecimal.ROUND_HALF_UP);
                                result.setText(String.valueOf(div_ans));
                            }
                            catch (Exception e){
                                result.setText("Cannot Divide by zero!");
                            }


or

import java.math.BigDecimal;
public class BigDecimalExample
{
    public static void main(String[] args) 
    {
        // Create two new BigDecimals
        BigDecimal bd1 = 
               new BigDecimal("124567890.0987654321");
        BigDecimal bd2 = 
               new BigDecimal("987654321.123456789");
         
        // Addition of two BigDecimals
        bd1 = bd1.add(bd2);
        System.out.println("BigDecimal1 = " + bd1);
 
        // Multiplication of two BigDecimals
        bd1 = bd1.multiply(bd2);
        System.out.println("BigDecimal1 = " + bd1);
 
        // Subtraction of two BigDecimals
        bd1 = bd1.subtract(bd2);
        System.out.println("BigDecimal1 = " + bd1);
 
        // Division of two BigDecimals
        bd1 = bd1.divide(bd2);
        System.out.println("BigDecimal1 = " + bd1);
 
        // BigDecima1 raised to the power of 2
        bd1 = bd1.pow(2);
        System.out.println("BigDecimal1 = " + bd1);
 
        // Negate value of BigDecimal1
        bd1 = bd1.negate();
        System.out.println("BigDecimal1 = " + bd1);
    }    
}        
or using eclipse also you can run this code as we run normal java code in eclipse..
*/


import java.awt.*;  // The Java programming language class library provides a user interface toolkit called the Abstract Windowing Toolkit, or the AWT. to make GUI
import java.text.DecimalFormat; // This object can be used with doubles, as it uses a decimal. It uses a template String to teach Java how to ouput the objects.
import java.awt.event.*;  // event manager when event happens on GUI
import java.io.FileNotFoundException; //This exception will be thrown by the FileInputStream, FileOutputStream, and RandomAccessFile constructors when a file with the specified pathname does not exist. It will also be thrown by these constructors if the file does exist but for some reason is inaccessible
import java.io.PrintWriter; // Java PrintWriter class is the implementation of Writer class. It is used to print the formatted representation of objects to the text-output stream.
import java.io.UnsupportedEncodingException;  // This exception will be thrown when The Character Encoding is not supported.

import javax.swing.*;  //Java Swing is a part of Java Foundation Classes (JFC) that is used to create window-based applications. It is built on the top of AWT (Abstract Windowing Toolkit) API 

public class ScientificCalc implements ActionListener{  // main class in java "scientificCalc"
	JFrame frame;     // Constructs a new frame that is initially invisible with a name of frame and data type of jFrame member of javax.swing
	JPanel panel;     // Constructs a new panel that is initially invisible with a name of panel and data type of jPanel member of javax.swing
	JTextField ansfield;  // Constructs a new textbox to display results with a name of ansfield and data type of jTextField member of javax.swing
	JButton buttons[];  // Constructs a new buttons with a name of buttons array and data type of jButton member of javax.swing
	String buttonsText[]={"C","sqrt","X^2","1/X","SIN","COS","TAN","+/-","0","1","2","3","4","5","6","7","8","9","+","-","*","/",".","="};  // Array of buttons used by JButtons
	int maxx=400,maxy=500;   // setting 
	
	static final String DIGITS = "0123456789.";
	 Boolean userIsInTheMiddleOfTypingANumber = false;
	 CalculatorBrain mCalculatorBrain=new CalculatorBrain();   // new object is created for CalculatorBrain class wich is used for computing results.
	 
	 
	
	public ScientificCalc() {
		frame = new JFrame("Scientific Calculator");  // name of Frame is given as "Scientific Calculator"
		frame.setSize(maxx, maxy);    // frame size is set to 400 on x axis and 500 on y axis
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Optional: defines What happens when the frame closes?

		panel = new JPanel(); // new panel is created as its class is called.
		panel.setLayout(null);  // panel's default layout is NULL
		
		ansfield = new JTextField();    // object is created for JTextField
		ansfield.setBounds(10,30,maxx-40,40);    // bounds are set for JTextField
		ansfield.setHorizontalAlignment(JTextField.RIGHT);  // Alignment is set for answer text as "right align"


		buttons=new JButton[buttonsText.length];  // object of button class is created 

		int currentx=0,currenty=0;
		
		for(int i=0;i<buttonsText.length;i++)   // placing buttons on GUI
		{
			buttons[i]=new JButton(buttonsText[i]);  // new object of button is created for every button
			buttons[i].addActionListener(this);      // an Action is given to each button
			if(currentx==0&&currenty==0)
			{
				buttons[i].setBounds(10,100,70,30);  // button's bound is set 
				currentx=10;                // pixel value of x-axis side is 10
				currenty=100;               // pixel value of y-axis side is 100
			}
			else
			{
				if(currentx<maxx-100)
				{
				currentx+=100;
				buttons[i].setBounds(currentx,currenty,70,30);
				}
				else
				{
					currentx=10;
					currenty+=50;
					buttons[i].setBounds(currentx,currenty,70,30);	
				}
			
			}
		panel.add(buttons[i]); // buttons are added on gui panel
		}
		
		panel.add(ansfield);  // answer field is added to the GUI panel 
		frame.add(panel);     // that panel is added to the frame of GUI
		frame.setVisible(true); // visiblily is set to true which was initially false
	}
public void actionPerformed(ActionEvent ae) {
		
		String buttonObj="";		

		for(int i=0;i<buttonsText.length;i++)  // to get the value of buttons in buttonObj so that computer can understand what button is pressed
		{
			if(ae.getSource()==buttons[i])
			{
			buttonObj=buttons[i].getText().toString();
			
			break;
			}
		}
		calc(buttonObj);  // that input is given to the calc function to perform that opration 
		
	}
	void calc(String buttonObj)     // main calc logic  
	{
		if (DIGITS.contains(buttonObj)) {  // if button object contains digits then go into this block and perform oprations.

            
            if (userIsInTheMiddleOfTypingANumber) {  // first this is false then made to true

                if (buttonObj.equals(".") && ansfield.getText().toString().contains(".")) {
                    
                } else {
                    ansfield.setText(ansfield.getText()+buttonObj);
                }

            } else {

                if (buttonObj.equals(".")) {  // if output contains . then print(set to ansfield) 0. that number
                    
                   
                    ansfield.setText(0 + buttonObj);
                } else {                    // else print(set to ansfield) the number as it is
                    ansfield.setText(buttonObj);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {        // if button object contains any thing other then digits then go into this block and perform oprations.
            
            if (userIsInTheMiddleOfTypingANumber) {

                mCalculatorBrain.setOperand(Double.parseDouble(ansfield.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            try
            {
            mCalculatorBrain.performOperation(buttonObj);  // perform that opration which used wants
            }catch(Exception e){}
            
            ansfield.setText(""+mCalculatorBrain.getResult());  // get the output
            try
            {
            PrintWriter writer;
            writer = new PrintWriter("memoryFile.txt", "UTF-8");  // to get history function in calc this file is created so you can see what the output is . 
            writer.println(""+mCalculatorBrain.getResult());  // to write/appand in memoryFIle.txt file
            writer.close();     // close that file
            }catch(Exception e){}

        }
	}
	public static void main(String args[])      // main function
	{

		new ScientificCalc();	
	}
	
	public class CalculatorBrain {          // main calc logic

	    
	    private double mOperand;
	    private double mWaitingOperand;
	    private String mWaitingOperator;
	    private double mCalculatorMemory;


	    public static final String ADD = "+";           // static final so that program is not able to change the value of this variables 
	    public static final String SUBTRACT = "-";
	    public static final String MULTIPLY = "*";
	    public static final String DIVIDE = "/";

	    public static final String CLEAR = "C" ;
	    public static final String SQUAREROOT = "sqrt";
	    public static final String SQUARED = "X^2";
	    public static final String INVERT = "1/X";
	    public static final String TOGGLESIGN = "+/-";
	    public static final String SINE = "SIN";
	    public static final String COSINE = "COS";
	    public static final String TANGENT = "TAN";
	    
	    
	    public CalculatorBrain() {
	        mOperand = 0;
	        mWaitingOperand = 0;
	        mWaitingOperator = "";
	        mCalculatorMemory = 0;
	    }

	    public void setOperand(double operand) {
	        mOperand = operand;
	    }

	    public double getResult() {
	        return mOperand;
	    }
	    public void setMemory(double calculatorMemory) {
	        mCalculatorMemory = calculatorMemory;
	    }
	    public double getMemory() {
	        return mCalculatorMemory;
	    }

	    public String toString() {
	        return Double.toString(mOperand);
	    }

	    protected double performOperation(String operator) throws Exception {

	        if (operator.equals(CLEAR)) {
	            mOperand = 0;
	            mWaitingOperator = "";
	            mWaitingOperand = 0;
	            // mCalculatorMemory = 0;
	        } else if (operator.equals(SQUAREROOT)) {  // for squareRoot
	            mOperand = Math.sqrt(mOperand);

	        } else if (operator.equals(SQUARED)) {    // for square
	            mOperand = mOperand * mOperand;

	        } else if (operator.equals(INVERT)) {      // to get 1/x value
	            if (mOperand != 0) {
	                mOperand = 1 / mOperand;
	            }
	        } else if (operator.equals(TOGGLESIGN)) {    // for change sign of answer +ve to -Ve and so on
	            mOperand = -mOperand;
	        } else if (operator.equals(SINE)) {         // for sin
	            mOperand = Math.sin(Math.toRadians(mOperand));  
	            
	        } else if (operator.equals(COSINE)) {       // for cos
	            mOperand = Math.cos(Math.toRadians(mOperand)); 
	        } else if (operator.equals(TANGENT)) {      // for tan
	            mOperand = Math.tan(Math.toRadians(mOperand)); 
	        } else {
	            performWaitingOperation();
	            mWaitingOperator = operator;
	            mWaitingOperand = mOperand;
	        }
	        
	        return mOperand;            // this will return the output 
	    }

	    protected void performWaitingOperation() {

	        if (mWaitingOperator.equals(ADD)) {             // for addition
	            mOperand = mWaitingOperand + mOperand;
	        } else if (mWaitingOperator.equals(SUBTRACT)) { // for subtraction
	            mOperand = mWaitingOperand - mOperand;
	        } else if (mWaitingOperator.equals(MULTIPLY)) { // for multiply
	            mOperand = mWaitingOperand * mOperand;
	        } else if (mWaitingOperator.equals(DIVIDE)) {   // for divide
	            if (mOperand != 0) {
	                mOperand = mWaitingOperand / mOperand;
	            }
	        }

	    }
	}
}
