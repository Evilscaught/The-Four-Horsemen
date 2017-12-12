/******************************************************************************
 *  Compilation:  javac IOCodes.java
 *  Execution:    java  IOCodes
 *  Dependencies: edu.princeton.cs.algs4.ST
 *
 *  @author(s)      Jake Wolfe, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Monday, November 27th, 2017
 *
 *  Blueprint for account data-type.
 *
 *     *
 *
 *     *
 *
 *     *
 *
 *  BUG:
 *
 *  FEATURE:
 *
 *  NOTE: 
 *
 *  % java IOCodes
 *
 ******************************************************************************/

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.princeton.cs.algs4.ST;

public class IOFees 
{
	private String 				path;
	private static double		totalFees;
	private static double 		unpaidFees;
	
    public IOFees(String path)
    {
    	this.path = path;
    	
        //Create a fees file 'Fees.txt' if one does not already exist.
        File accountsFile = new File("Fees.txt");
        try 
        {
			accountsFile.createNewFile();
		}
        catch (IOException ioException) 
        {
			ioException.printStackTrace();
		}
        
        totalFees = 0;
    }
    
    public void readFees() throws FileNotFoundException
    {
    	//Create file scanner
    	
    	Scanner file = new Scanner(new File(path));
    	String currentLine = file.nextLine();
    	Scanner token = new Scanner(currentLine).useDelimiter(",");
    	
    	unpaidFees = token.nextDouble();
    	
    	
    	file.close();
    }
    
    public void saveFees() throws Exception
    {
    	FileWriter out = new FileWriter(new File(path));

    	out.write("" + unpaidFees);
    	
    	out.close();
    }
    
    public double getTotalFees() {
    	return totalFees;
    }
    
    public void setTotalFees(double total) {
    	totalFees = total;
    }
    
    public double getUnpaidFees() {
    	return unpaidFees;
    }
    
    
    public void addTotalFees(double fee) {
    	totalFees = totalFees + fee;
    }
    
    public void addUnpaidFees(double fee) {
    	unpaidFees = unpaidFees + fee;
    }
    
    public void clearFees() {
    	this.unpaidFees = 0;
    }

    
    //Unit Testing
    public static void main(String[] args) throws Exception
    {

    	IOFees ioFees = new IOFees("Fees.txt");
    	ioFees.readFees();
    	System.out.println("" + totalFees + "," + unpaidFees);
    	
    	

    }
}
