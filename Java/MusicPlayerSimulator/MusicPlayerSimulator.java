/*
Author: Fraser Cobb
 */

import java.io.*;

//This program simulates a music player, allowing users to perform tasks such as creating and manipulating playlists, editing songs and importing songs or playlists from a text file.
public class MusicPlayerSimulator
{	
	public static void main(String[] args) throws IOException
	{		
		//Menu options
		final String[] mainMenu = {"<===MAIN MENU===>", "Songs", "Playlists", "Import/Rip CD", "Save", "Exit Program"};
		final String[] songMenu = {"==SONGS==", "Display Songs", "Sort Songs", "Rate Song", "Set Genre", "Exit Sub-menu"};
		final String[] playlistMenu = {"==PLAYLISTS==", "Display Playlists", "Create", "Add Song", "Exit Sub-menu"};
		final String[] songSubMenu = {"=SORT=", "by Title", "by Rating", "Exit Sub-menu"};
		
		//Variables for menu choices
		int menuChoice, subMenuChoice;
		
		//Create library of songs/playlists
		Library myLibrary = new Library();
		
		//--------------------Main Menu start------------------------------------------------------
		
		//Loop until main menu option 5.(Exit Program) is selected
		do
		{
			//Display main menu
			Menu.displayMenu(mainMenu);
			//Get menu choice
			menuChoice = Menu.getChoice(1, 5);
			
			//------------------------------1.0 Songs-------------------------------------
			switch(menuChoice)
			{
				case 1: 
						//Reset menuChoices
						subMenuChoice = 0;
						menuChoice = 0;
						
						//Check if there are songs
						if(myLibrary.hasSong())
						{
							//Loop until songs sub menu option 5.(Exit sub menu) is selected
							while(subMenuChoice != 5)
							{
								//Display Song sub menu and get menu choice
								Menu.displayMenu(songMenu);
								
								//Get menu choice
								subMenuChoice = Menu.getChoice(1, 5);
								
								//1.1 Display Songs
								switch(subMenuChoice)
								{
									case 1:
										if(myLibrary.hasSong())
										{myLibrary.displaySongs();}
										else
										{System.out.println("There are no songs to display!");}
									break;
									
									//1.2 Sort Songs				
									case 2:
										//Check if there are songs to sort
										if(myLibrary.hasSong())
										{
											//Loop until exit sub menu option is selected
											while(subMenuChoice != 3)							
											{	//Display sort submenu
												Menu.displayMenu(songSubMenu);
												
												//Get menu choice
												subMenuChoice = Menu.getChoice(1, 3);
												
												if(subMenuChoice ==  1)
												{	//1.2.1 Sort by title
													myLibrary.sort(myLibrary.getSongArray(), "title");
													System.out.println("Songs sorted successfully!");
												}
												else if(subMenuChoice ==  2)
												{ 
													//1.2.2 Sort by rating
													myLibrary.sort(myLibrary.getSongArray(), "rating");
													System.out.println("Songs sorted successfully!");
												}							
											}
										}
										else
										{System.out.println("There are no songs to sort!");}
									break;
								
								//1.3 Rate Songs
								case 3:
									//Check if there are songs to rate
									if(myLibrary.hasSong())
									{myLibrary.rate(myLibrary.getSongArray()); }
									else
									{System.out.println("There are no songs to rate!");}
								break;
								
								//1.4 Set Genre	
								case 4:
									if(myLibrary.hasSong())
									{
										myLibrary.genre(myLibrary.getSongArray());
										System.out.println("Song genre set successfully!");
									}
									else
									{System.out.println("There are no songs to assign a genre to!");}
								break;
								}								
							}							
						}
						else
						{System.out.println("No songs exist in this library!\nPlease import songs before accessing the songs menu.\n");}
						
					break;
				//-----------------------2.0 Playlists-----------------------------
				case 2:
						//Reset menuChoices
						subMenuChoice = 0;
						menuChoice = 0;
						
						do
						{
							//Display Playlist menu
							Menu.displayMenu(playlistMenu);
							
							//Get menu choice
							menuChoice = Menu.getChoice(1, 5);
							
							switch(menuChoice)
							{
								//2.1 Display playlists
								case 1: 
									if(myLibrary.hasPlaylist())
									{
										//Output all playlists
									
										myLibrary.displayPlaylists();
										
										//View specific playlist
										myLibrary.viewPlaylist();
									}
									else
									{System.out.println("There are no playlists to display!");}
								break;
								
								//2.2 Create Playlist				
								case 2:
										myLibrary.createPlaylist();
								break;
								
								//2.3 Add Song
								case 3:
									if(myLibrary.hasPlaylist())
									{
										if(myLibrary.hasSong())
											{myLibrary.addSong();}
										else
										{System.out.println("There are no songs to add to a playlist!");}
									}
									else
									{System.out.println("There are no playlists to add to!");}
								break;
								
								//2.4 Exit Sub Menu	
							}
						}while(menuChoice != 4);
				break;
				//-------------------------3.0 Import/Rip CD---------------------------------
				case 3:
						myLibrary.importCD();
				break;
				//-------------------------4.0 Save------------------------------------------
				case 4:
					if(myLibrary.hasSong() || myLibrary.hasPlaylist())
					{myLibrary.Save();}
					else
					{System.out.println("No playlists or songs to save!");}
				break;
				//-------------------------5.0 Exit Program---------------------------------
			}
		}while(menuChoice != 5);
		
		System.out.println("---END OF PROGRAM---");
	}
}
