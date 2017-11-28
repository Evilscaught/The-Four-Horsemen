/******************************************************************************
 *  Compilation:  javac IOCodes.java
 *  Execution:    java  IOCodes
 *  Dependencies:
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

import java.util.ArrayList;

public class IOCodes 
{
    private ArrayList<String>  keys  = new ArrayList<String>(20);
    private ArrayList<Integer> values = new ArrayList<Integer>(20);
	
	public IOCodes()
    {
    	values.add(663662);
    	keys.add("Code A");
    	values.add(488493);
    	keys.add("Code B");
    	values.add(477223);
    	keys.add("Code Red");
    }
	
    public ArrayList<Integer> getValues() 
    {
    	return values;
    }


    public ArrayList<String> getCodes() 
    {
    	ArrayList<String> codes = new ArrayList<String>(20);
	
    	int counter = 0;
	
    	while(counter < values.size()) 
		{
			codes.add(keys.get(counter) + " - " + values.get(counter));
			counter++;
		}
	
		return codes;
	}
	
    public void addCode(String desc, int code) 
    {
    	keys.add(desc);
    	values.add(code);
    }
}


