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
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import edu.princeton.cs.algs4.ST;

public class IOCodes 
{
	private String 				codesPath;
	private ST<String, Integer> codes;
	
    public IOCodes(String path)
    {
    	codesPath = path;
    }
    
    public void readCodes()
    {
    	//Create file scanner
    	codes = new ST<String, Integer>();
    	InputStream codesFile = this.getClass().getResourceAsStream(codesPath);
    	Scanner file = new Scanner(codesFile);
    	
    	while (file.hasNext())
    	{
    		//Copy each line of the file into currentLine, then scan each token
    		String  currentLine = file.nextLine();
    		Scanner token = new Scanner(currentLine);
    		token.useDelimiter(",");
    		while (token.hasNext())
    		{
    			put(token.next(), token.nextInt());
    		}
    		token.close();
    	}
    	file.close();
    }
    
    public void saveCodes() throws Exception
    {
    	URL url = this.getClass().getResource(codesPath);
    	File file;
    	
    	try
    	{
    		file = new File(url.toURI().getPath());
    		FileWriter out = new FileWriter(file);

    		for (String code : codes.keys())
    		{
    			out.write(code + "," + codes.get(code));
    			out.write('\n');
    		}
    		out.close();
    	}
    	catch (URISyntaxException exception)
    	{
    		exception.printStackTrace();
    	}
    }
    
    public ST<String, Integer> getSTCodes()
    {
    	return codes;
    }
    
    public void put(String key, int value)
    {
    	codes.put(key, ((Integer) value) );	
    }
    
    public void delete(String key)
    {
    	codes.delete(key);
    }
    
    public int size()
    {
    	return codes.size();
    }
    
    //Unit Testing
    public static void main(String[] args) throws Exception
    {
    	ST<String, Integer> codes;
    	IOCodes ioCodes = new IOCodes("src/Codes.txt");
    	ioCodes.readCodes();
    	
    	codes = ioCodes.getSTCodes();
    	
    	System.out.println("Symbol Table Size: " + ioCodes.getSTCodes().size());
    	for (String code : codes.keys())
    	{
    		System.out.println(code + ": " + codes.get(code));
    	}
    	
    	ioCodes.saveCodes();
    	System.out.println("\nExit Success");
    }
}


