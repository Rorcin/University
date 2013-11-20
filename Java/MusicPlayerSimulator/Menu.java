/*
Author: Fraser Cobb
 */

import java.util.Scanner;

//This class is used to display menus and retrieve selections from the user.
public class Menu {
	
	static Scanner keyboard = new Scanner(System.in);
	static int menuChoice;
	
	/**
	 * The displayMenu method displays a menu based on the received string array.
	 * 
	 * @param menu The string array containing to menu title and menu options
	 */
	public static void displayMenu(String[] menu)
	{
		//Display Menu Title
		System.out.println(menu[0]);
		
		//Display menu items
		for(int i = 1; i < menu.length; i++)
		{
			System.out.println(i+ ": " + menu[i]);
		}
	}
	
	/**
	 * The getChoice method retrieves a users input checking for out of range errors,
	 *  looping until a valid selection is made.
	 *  
	 * @param min The minimum allowed value.
	 * @param max The maximum allowed value.
	 * @return The users input.
	 */
	public static int getChoice(int min, int max)
	{
		int input=0;
		do
		{
			input = keyboard.nextInt();		
			
			//Remove line break from nextInt
			keyboard.nextLine();
			
			if (input < min || input > max)
				{
					System.out.println("Selection out of range!");
					System.out.print("Please select again: ");
				}
			
			
		}while(input < min || input > max);
		
		return input;
	}
	
}
