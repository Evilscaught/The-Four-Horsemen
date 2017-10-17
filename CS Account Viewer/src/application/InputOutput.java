/* Author:			 Scott McKay
 * Co-Authors:
 * Date of Creation: Monday, October 16th, 2017 @6:49 p.m. MST
 * Version:			 0.0.0
 * Group Name:		 The Four Horsemen
 * Copyright:		 None
 * Purpose of Class: Handles all input and output, such as getting and saving data.
 */

package application;

import java.io.File;
import java.util.Scanner;


public class InputOutput 
{
	private String path;
	//Constructor
	public InputOutput()
	{
		
	}
	
	public Account[] readAccounts(String path)
	{
		this.path = path;
		
		Scanner file = new Scanner(path);
		
		while(file.hasNextLine())
		{
			String token = file.nextLine();
			Scanner input = new Scanner(token);
			
			input.useDelimiter(",");
			
			while(input.hasNext())
			{
				
			}
			
			input.close();
		}
		
		file.close();
		
		
		return null;
	}
	
	public void saveAccounts()
	{
		
	}


	
	//Unit Testing
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
}