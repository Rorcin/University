/*
Author: Fraser Cobb
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Library {
	
	private Song[] songListArray;// = new Song[1];	
	private Playlist[] playlistArray;// = new Playlist[1];
	
	//Create file objects for playlist and song files
	private File songCollectionFile = new File("songCollection.txt");
	private File playlistFile = new File("playlists.txt");
	private File CDImportFile;
	
	//Menu options
	final String[] fileSelectionMenu = {"SONG FILE", "Create new song collection file.", "Select new file name."};
	
	
	//Create scanner object for keyboard
	Scanner keyboard = new Scanner(System.in);	
			
	//Variables for menu choices
	int menuChoice = 0, subMenuChoice = 0;
	
	//String for menu option is out of range
	final String invalidMenuOption = "Invalid menu option! Please select a menu option:";

//----------------------------------CONSTRUCTOR-----------------------------------
	/**
	 * Constructor - imports songs and playlist if textfiles exists.
	 * 
	 * @throws IOException to handle PrintWriter
	 */
	public Library() throws IOException
	{
		//Check if the songCollection file exists
		if(!songCollectionFile.exists())
			{
				//Display error message
				System.out.println("Song collection file does not exist!");	
					
				//Display menu
				Menu.displayMenu(fileSelectionMenu);
				//Get menu choice
				menuChoice = Menu.getChoice(1, 2);
					
				if(menuChoice == 1)
				{
					//Get new file name
					System.out.println("Enter a file name for a new song collection:(will erase existing file of the same name) ");
					this.songCollectionFile = new File(keyboard.nextLine()+".txt");
						
					//Create new file
					PrintWriter SongCollectionPrintWriter = new PrintWriter(this.songCollectionFile);
				}
				else if(menuChoice == 2)
				{
					//Loop until new file exists
					do
					{		
					//Get file name of an existing song collection file.
					System.out.println("Enter the file name of another song collection file:  ");			

					//Creating new file object with the new file name
					songCollectionFile = new File(keyboard.nextLine()+".txt");
						
					//Display error if the file does not exist
					if(!songCollectionFile.exists())
						System.out.println("The entered file does not exist.");
						
					}while(!songCollectionFile.exists());
				}
			}
		else
			{
				//Import songs from the songCollection text file
				importSongs(songCollectionFile);
			}	
				
			if(playlistFile.exists())
				{		
				//Import playlists from the playlists text file
				importPlaylists(playlistFile);
				}
				
	}
	
//----------------------------------ACCESSORS AND MUTATORS------------------------
	/**
	 * The getSongArray method copies the contents of the songListArray into a new Song array, and returns the new Song array.
	 * 
	 * @return The songArray of this library
	 */
	public Song[] getSongArray()
	{return this.songListArray;}
	
	/**
	 * The setSongArray method
	 * @param newSongArray The new Song Array.
	 */
	public void setSongArray(Song[] newSongArray)
	{this.songListArray = newSongArray;}
	
	/**
	 * The getPlaylistArray method
	 * 
	 * @return The playlist array of this library.
	 */
	public Playlist[] getPlaylistArray()
	{return this.playlistArray;}
	
	/**
	 * The setPlaylistArray method
	 * 
	 * @param newPlaylistArray The new playlist array.
	 */
	public void setPlaylistArray(Playlist[] newPlaylistArray)
	{this.playlistArray = newPlaylistArray;}
	
//---------------------------------IMPORT METHODS------------------------------------------------------------------
	
	/**
	 * The importCD method gets the name of a CD import text file, splits each line of the file into a String array
	 * increases the songArray,then adds the song into the songArray
	 * 
	 * @throws IOException FileNotFound exeception for the file scanner
	 */
	public void importCD() throws IOException 
	{
		do
		{				
		//Get CD import file name
		System.out.print("What is the name of the CD import file? ");
		CDImportFile = new File(keyboard.nextLine()+".txt");				
		
		if(CDImportFile.exists())
		{
			Scanner songCollection = new Scanner(this.CDImportFile);
			
			//String variable to hold a line of the file
			String fileLine;
			
			//Array to store song details
			String[] songDetails = new String[4];
			
			for(int i = 0; songCollection.hasNextLine(); i++)
			{
				//Store line into fileLine to input into the StringTokenizer.
				fileLine = songCollection.nextLine();
				
				//Initialize StringTokenizer with comma as the delimiter.
				StringTokenizer songCollectionTokenizer = new StringTokenizer(fileLine, ",");
				
				//Counter to store tokens into correct songArray index.
				int tokenCounter = 0;
				while(songCollectionTokenizer.hasMoreTokens())
				{	
					songDetails[tokenCounter] = songCollectionTokenizer.nextToken();
					tokenCounter++;
				}
				
				//Increase the array size
				songListArray = increaseArray(songListArray);
				
				//Initialized here as 0 to account for no songs in the songListArray
				int lastID = 0;
				
				if(this.hasSong())
				{		
					//Get the last ID
					lastID = getLastID(this.songListArray);
				}
				
				//Add instance of song to array
				songListArray[lastID] = new Song(songDetails, lastID+1);
				
				songDetails = new String[4];
			}
			//Close the songCollection Scanner
			songCollection.close();
			
			System.out.println("Import complete!");
		}
		else
			System.out.println("File does not exist!");
		
		}while(!songCollectionFile.exists());
	}
	
	/**
	 * The importSongs method splits each line of the songCollectionFile into a string array
	 * increases the songArray, adds the song into the songArray
	 * 
	 * @param newSongCollectionFile The new song Collection File
	 * @throws IOException FileNotFoundException
	 */
	public void importSongs(File newSongCollectionFile) throws IOException
	{
		Scanner songCollection = new Scanner(newSongCollectionFile);
		
		String fileLine;
		
		//Array to store song details
		String[] songDetailsArray = new String[7];
		
		for(int i = 0; songCollection.hasNextLine(); i++)
		{
			//Store line into fileLine to input into the StringTokenizer.
			fileLine = songCollection.nextLine();
			
			//Initialize StringTokenizer with comma as the delimiter.
			StringTokenizer songCollectionTokenizer = new StringTokenizer(fileLine, ",");
			
			//Counter to store tokens into correct songArray index.
			int tokenCounter = 0;
			while(songCollectionTokenizer.hasMoreTokens())
			{	
				songDetailsArray[tokenCounter] = songCollectionTokenizer.nextToken();
				tokenCounter++;
			}
			
			//If the songList array contains no songs, initialize the song list array
			if(!this.hasSong())
			{this.songListArray = new Song[1];}
			
				//If songList is full, increase the array size
				if(this.songListArray.length == i)
				{
					this.songListArray = increaseArray(songListArray);
				}
				
			//Add instance of song to array
			this.songListArray[i] = new Song(songDetailsArray);		
		}
		
		//Close songCollectionFile
		songCollection.close();
	}
	
	/**
	 * The Playlists method imports playlists from the playlistFile
	 * @param newPlaylistFile The file to import playlists from
	 * @throws IOException FileNotFoundException
	 */
	public void importPlaylists(File newPlaylistFile) throws IOException
	{		
		Scanner playList = new Scanner(newPlaylistFile);
		
		String fileLine;
		String[] playlistDetails = new String[3];
		int[] songIDs = new int[1];
		float playlistDuration =0;
		
		for(int i = 0; playList.hasNextLine(); i++)
			{
			//Store line into fileLine to input into the StringTokenizer.
			fileLine = playList.nextLine();
			
			//Initialize StringTokenizer with comma as the delimiter.
			StringTokenizer playlistTokenizer = new StringTokenizer(fileLine, ",");
			
			//Get Playlist ID
			if(playlistTokenizer.hasMoreTokens())
				playlistDetails[0] = playlistTokenizer.nextToken();	
				
			//Get Playlist Name
			if(playlistTokenizer.hasMoreTokens())
				playlistDetails[1] = playlistTokenizer.nextToken();
				
			//Get Playlist length
			if(playlistTokenizer.hasMoreTokens())
				playlistDetails[2] = playlistTokenizer.nextToken();
				
			//Variable to count songs.
			int songCounter = 0;
			
			//Import songIDs into songIDs array while the playlistFile has more tokens(songs)
			while(playlistTokenizer.hasMoreTokens())
				{
				//If the songIDs array is full.
				if(songIDs.length != songCounter+1)
					{
					//Increase the songIDs array and add new songID
					songIDs = increaseArray(songIDs);
					}
				
				//Add new songID at the end of the new songID array
				songIDs[songCounter] = Integer.parseInt(playlistTokenizer.nextToken());		
				
				//Increment counter
				songCounter++;
				}
			
			if(this.hasSong())
			{
				//Calculate duration
				for(int d=0;d<songIDs.length; d++)
				{
					//Get the songIndex to retrieve up its duration
					int songIndex = searchByID(songIDs[d]);
					
					if(songIndex != -1)
					{
						//Add the song duration to the playlist duration
						playlistDuration += songListArray[songIndex].getDuration();
					}
				}
			}
			else
			{playlistDuration = 0;}
			
			if(!this.hasPlaylist())
			{playlistArray = new Playlist[1];}
			
			//If playlists is full, increase the array size
			if(playlistArray.length < i+1)
			{
				playlistArray = increaseArray(playlistArray);
			}
			
			//Add new playlist into playlist array
			playlistArray[i] = new Playlist(playlistDetails, songIDs);
			
			//Set playlist duration
			playlistArray[i].setDuration(playlistDuration);
			
			//Reset playlist duration for the next loop iteration
			playlistDuration =0;
			
			//Reset songIDs array after creating playlist
			songIDs = new int[1];
		}
		
		//Close the playlist scanner object
		playList.close();
	}

//--------------------------------SONG ARRAY METHODS-----------------------------------------------------------------------
	/**
	 * The displaySongs method loops through the song array displaying each song.
	 */
	public void displaySongs()
	{
		//Loop through the array displaying each song
		for(int i = 0; i<this.songListArray.length ; i++)
		{
			//Insert space for readability
			System.out.print("\n");
			//Call the toString method to display the song
			System.out.println(this.songListArray[i].toString());
		}	
		//Insert line break after displaying songs
		System.out.println("-------------------------");
	}
	
	/**
	 * The addSong method displays all playlists, asks the user which playlist to add to,
	 * gets the playlist index by ID, displays the songs of that playlist, 
	 * asks the user for a song to add to the playlist, adds to the playlists duration, 
	 * then adds the song into the playlist, then adds to the playlist length
	 */
	public void addSong()
	{
		displayPlaylists();
		
		System.out.print("Which playlist would you like to add to? ");
		subMenuChoice = Menu.getChoice(1, this.playlistArray.length);
		
		//Get playlist index
		int playlistIndex = this.searchByID(subMenuChoice, this.playlistArray);
		
		//Display songs
		for(int i = 0; i<this.songListArray.length ; i++)
		{
			System.out.println("--------------");
			System.out.println(this.songListArray[i].toString());
		}	
		System.out.println("--------------");
		
		//Get song to add into playlist
		System.out.print("Which song would you like to add to the '"+this.playlistArray[playlistIndex].getName()+"' playlist? ");
		subMenuChoice = Menu.getChoice(1, this.songListArray.length);
		
		//Get playlist duration
		float playlistDuration = this.playlistArray[playlistIndex].getDuration();
		
		//Get song duration and add it to the playlistDuration
		playlistDuration += this.songListArray[subMenuChoice-1].getDuration();	
		System.out.println();
		
		//Set the new playlist duration
		this.playlistArray[playlistIndex].setDuration(playlistDuration);
		
		//Get songIDs array of the playlist
		int[] songIDs = this.playlistArray[playlistIndex].getSongs();						
		
		//If playlist contains no songs.
		if(songIDs == null)
			{
			//Create songID array
			songIDs = new int[1];
			
			//Add song into songID array
			songIDs[0] = subMenuChoice;
			}
		else
			{
			//Increase songID array
			songIDs = this.increaseArray(songIDs);							
			}
		//Add song into songIDs array
		songIDs[songIDs.length-1] = subMenuChoice;
		//Add the songs into the playlist
		this.playlistArray[playlistIndex].setSongs(songIDs);
		
		//Get the length of the playlist
		int playlistLength = this.playlistArray[playlistIndex].getSongs().length;
		//Set the length
		this.playlistArray[playlistIndex].setLength(playlistLength);
	}
		
	/**
	 * The searchByID method searches an array of songs for a given ID.
	 * @param songID The ID to search for.
	 * @param songArray The array of songs to search into.
	 * @return The index of the song that has the given ID, -1 if song is not found.
	 */
	public int searchByID(int songID)
	{
		int songIndex = -1, i = 0;
		boolean found = false;
		
		while(!found && i <this.songListArray.length)
		{
			//Check if the ID is the ID we are searching for
			if(this.songListArray[i].getID() == songID)
			{
				found = true;
				songIndex = i;
			}
			i++;
		}
		return songIndex;
	}
	
	/**
	 * The rate method asks for a song by its ID, searches for the song, then assigns a rating to the song based on user input.
	 * @param songListArray The song array in which to search for, and set the genre of,  the song
	 */
	public void rate(Song[] songListArray)
	{
		//Get the songID
		System.out.println("Enter the SongID: ");
		int songID = keyboard.nextInt();
		
		//Capture nextline
		keyboard.nextLine();
		
		//Search for the song
		int songIndex = searchByID(songID);
		
		float rating;
		if(songIndex != -1)
		{
			do{			
			//Get for the rating
			System.out.println("What rating would you like to assign to '" +songListArray[songIndex].getTitle() +"' by '" +songListArray[songIndex].getArtist()+"'?");
			rating = keyboard.nextFloat();
			
			//Capture nextline
			keyboard.nextLine();
			
			//Check rating is between 0 and 5.
			if(rating <0.0 || rating >5.0)
			{
				//Display error if rating isn't between 0 and 5.
				System.out.println("Rating must lie between 0 and 5! ");
			}
			
			//Check rating is a whole or half value.
			if(rating%0.5 != 0)
			{
				//Display error if rating isn't a whole or half number
				System.out.println("Rating must be a whole or half number!");
			}
			
			}while(rating <0.0 || rating >5.0 || rating%0.5 != 0);
			
			//Set rating
			songListArray[songIndex].setRating(rating);
			System.out.println("Song rated successfully!");
		}
		else
		{System.out.println("SongID was not found!");}
	}
	
	/**
	 * The genre method displays a list of valid genres,
	 * asks the user for a song and genre choice, 
	 * and sets the genre of that song
	 * 
	 * @param songListArray
	 */
	public void genre(Song[] songListArray)
	{
		String[] genreList = {"Valid Genres:", "Alternative", "Blues", "Country", "Dance", "Hip-Hop/Rap", "Jazz", "Pop", "R&B/Soul", "Reggae", "Rock"};
		//Get the songID
		System.out.println("Enter the SongID: ");
		int songID = Menu.getChoice(1, songListArray.length);
		
		//Search for the song
		int songIndex = searchByID(songID);
		
		//Ask for the genre
		System.out.println("What genre would you like to assign to '" +songListArray[songIndex].getTitle() +"' by '" +songListArray[songIndex].getArtist()+"'?");
		Menu.displayMenu(genreList);
		int genreChoice = Menu.getChoice(1, genreList.length-1);
		
		//Set genre
		songListArray[songIndex].setGenre(genreList[genreChoice]);
	}

	/**
	 * This overloaded getLastID method searches a song array of the last ID, then returns that ID
	 * @param songArray The song array to search through.
	 * @return The ID of the last imported song.
	 */
	public int getLastID(Song[] songArray)
	{
		int largestID = 0;
		
		//Loop through the array
		for(int i=0;i<songArray.length-1; i++)
		{
			//If the ID of the current song is greater than the current largest ID
			if(songArray[i].getID() > largestID)
			{
				//set this song ID as the largest ID
				largestID = songArray[i].getID();
			}
		}
		return largestID;
	}
	
	/*
	 * 	The following method was adapted from the textbook 
	 *	'Starting out with Java: From control structures through objects' by Tony Gaddis pg 469(5th Edition).
	 */	
	
	/**
	 * The sort method sorts an array of Songs either by title alphabetically, or descending rating.
	 * @param songArray The array of songs to be sorted
	 * @param sortType The way in which the array will be sorted, either 'title' or 'rating'
	 */
	public void sort(Song[] songArray,String sortType)
	{		
		int startAt, currentIndex;
		Song currentSong = songArray[0];
		boolean swap = false;		
		
		//Loop to move search start point
		for(startAt = 0; startAt < songArray.length-1; startAt++)
		{			
			//loop to search through each array index
			for(int checkIndex = startAt+1; checkIndex < songArray.length; checkIndex++)
			{
				//reset swap
				swap = false;
				
				//Reset current index/song
				currentIndex = startAt;
				currentSong = songArray[startAt];
				
				if(sortType.equals("title"))
				{				
					//CompareTo returns negative if checked title precedes currentSong title
					if(songArray[checkIndex].getTitle().compareToIgnoreCase(currentSong.getTitle()) < 0)
					{swap = true;}
				}
				else if(sortType.equals("rating"))
				{
					//Compare ratings
					if(songArray[checkIndex].getRating() > currentSong.getRating() )
					{swap = true;}
				}
				
				if(swap)
				{
					//Put the checked index song into the current index
					songArray[currentIndex] = songArray[checkIndex];
					
					//Put the current index song into the checked index
					songArray[checkIndex] = currentSong;
				}
			}
		}
	}

	/**
	 * This overloaded increaseArray method increases the Song Array to allow more songs to be added.
	 * @param songList The songArray to increase
	 * @return The new song array
	 */
	public Song[] increaseArray(Song[] songList)
	{
		Song[] newList;
		
		//If there are songs in the song array.
		if(this.hasSong())
		{
			//Create new song list that is larger than the previous
			newList = new Song[songList.length + 1];	
		}
		else
		{newList = new Song[1];}
		
		//Copy contents of old song array into new song list
		for(int k=0; k < newList.length-1;k++)
		{
			newList[k] = songList[k];
		}		
		return newList;
	}

	/**
	 *  The hasSongs method returns true when a song has been added to the songListArray.
	 * @return Whether songs have been added to the songListArray.
	 */
	public boolean hasSong()
	{
		boolean songFound = false;
		
		if(this.songListArray != null)
			{songFound = true;}
		
		return songFound;
	}
//---------------------------------PLAYLIST ARRAY METHODS---------------------------------------------------------------	
	/**
	 * The createPlaylist array asks the user for a new playlist name,
	 * gets the ID of the last playlist, increases the playlistArray,
	 * and adds the new playlist in.
	 */
	public void createPlaylist()
	{
		//Get name of the new playlist
		System.out.print("Enter the name of your new playlist: ");
		String playlistName = keyboard.nextLine();
		
		//Initialized to 0 to account for no playlists in the playlistArray
		int lastID = 0;
		
		if(this.hasPlaylist())
		{
			//Get the last playlist ID	
			lastID = this.getLastID(this.playlistArray);
		}
		
		//Increase the playlistArray size
		this.playlistArray = this.increaseArray(this.playlistArray);
		
		//Add playlist into playlist array
		this.playlistArray[this.playlistArray.length-1] = new Playlist(playlistName, lastID+1);
	}

	/**
	 * The displayPlaylists method loops through the playlist array to display each playlist
	 */
	public void displayPlaylists()
	{
		if(this.playlistArray != null)
		{
			//For each playlist, display the string representation
			for(int i = 0; i < this.playlistArray.length ; i++)
			{
				//Get playlist details
				String[] playlistDetails = this.playlistArray[i].toStringArray();
				
				//Output playlist details
				System.out.println(playlistDetails[0]+": "+playlistDetails[1]);
			}
			//Insert line break after displaying playlist
			System.out.println("-------------------------");
		}
		else
		{
			//Error message for no playlists
			System.out.println("No playlists have been created.");
		}
	}
	
	/**
	 * The viewPlaylist method asks the user to choose a playlist, 
	 * then displays the songs for the given playlist
	 */
	public void viewPlaylist()
	{
		//Request a playlist
		System.out.print("What playlist would you like to view? ");
		subMenuChoice = Menu.getChoice(1, this.playlistArray.length);
				
		//Get playlist ID
		int playlistIndex = searchByID(subMenuChoice, this.playlistArray);
		
		//Get songIDs for the given playlist
		int[] songIDs = this.playlistArray[playlistIndex].getSongs();
				
		if(this.playlistArray[playlistIndex].getLength() == 0)
			System.out.println("The playlist contained no songs!");						
		else
		{
			//System.out.println("songIDs[3]="+songIDs[3]);
			
			//Display songs for the given playlist
			for(int i = 0; i<songIDs.length; i++)
			{
				//Get songIndex
				int songIndex = searchByID(songIDs[i]);
				if(songIndex != -1)
				{								
				//Display song
				System.out.println(this.songListArray[songIndex].toString());
				}
			}							
		}
				//Display line for readability purposes
				System.out.println("-------------------------");
	}

	/**
	 * Overloaded getLastID
	 * 
	 *  This overloaded getLastID method searches through a playlist array and returns the last ID.
	 * @param playlistArray
	 * @return The ID of the last playlist
	 */
	public int getLastID(Playlist[] playlistArray)
	{
		int largestID = 0;
		
		for(int i=0;i<playlistArray.length; i++)
		{
			if(playlistArray[i].getID() > largestID)
			{
				largestID = playlistArray[i].getID();
			}
		}
		return largestID;
	}
	
	/**
	 * The searchByID method searches through a playlist array for a given ID
	 * 
	 * @param playlistID The ID to search for
	 * @param playlistArray The playlist array to search in.
	 * @return The index of the playlist that has the given ID
	 */
	public int searchByID(int playlistID, Playlist[] playlistArray)
	{
		int playlistIndex = -1, i = 0;
		boolean found = false;
		
		while(!found && i <playlistArray.length)
		{
			if(playlistArray[i].getID() == playlistID)
			{
				found = true;
				playlistIndex = i;
			}
			i++;
		}
		return playlistIndex;
	}
	
	/**
	 * This overloaded increaseArray method increases the Playlist Array to allow more playlists to be added.
	 * @param oldPlaylists The playlist array to be increased.
	 * @return The new playlist array
	 */
	public Playlist[] increaseArray(Playlist[] oldPlaylists)
	{
		Playlist[] newList;
		
		if(this.hasPlaylist())
		{
			//Create new playlist array that is larger than the previous
			newList = new Playlist[oldPlaylists.length + 1];	
		}
		else
		{newList = new Playlist[1];}
		
		//Copy contents of old playlist array into new playlist list
		for(int k=0; k < newList.length-1;k++)
		{
			newList[k] = oldPlaylists[k];
		}		
		return newList;
	}
	
	/**
	 *  The hasPlaylist method returns true when a playlist has been added to the playListArray.
	 * @return Whether playlist have been added to the playListArray.
	 */
	public boolean hasPlaylist()
	{
		boolean playlistFound = false;
		
		if(this.playlistArray != null)
			{playlistFound = true;}
		
		return playlistFound;
	}
//--------------------------------OTHER METHODS------------------------------------------------------------------	
	/**
	 * This Save method writes the Songs and Playlists into the songCollection and playlists files.
	 * @throws IOException FileNotFoundException
	 */
	public void Save() throws IOException
	{
		if(this.hasSong())
		{
			//Create song file objects
			FileWriter songFileWriter = new FileWriter(this.songCollectionFile);
			PrintWriter songOutFile = new PrintWriter(songFileWriter);
			
			//Add all songs onto song file
			for (int i=0;i < this.songListArray.length;i++)
				{songOutFile.println(this.songListArray[i].toOutputString());}
			
			System.out.println("Songs saved successfully!");
			
			//Close song file object
			songOutFile.close();
			songFileWriter.close();
		}
		
		if(this.hasPlaylist())
		{
			//Create playlist file objects
			FileWriter playListFileWriter = new FileWriter("playlists.txt");
			PrintWriter playlistOutFile = new PrintWriter(playListFileWriter);						
			
			//Add all playlists onto playlist file
			for (int i=0;i < playlistArray.length;i++)
			{
				//Add playlist ID, name, length
				playlistOutFile.print(playlistArray[i].toString());
				
				if(playlistArray[i].getLength() != 0)
				{
					//Add the delimiter between length and songIDs
					playlistOutFile.print(",");
				}
				
				//SongID array contains songs
				if(playlistArray[i].getLength() != 0)
				{
					//Get playlist songs
					int[] playlistSongs = playlistArray[i].getSongs();
					
					//Add playlist songs
					for(int j=0; j < playlistSongs.length; j++)
					{
						playlistOutFile.print(playlistSongs[j]);
						
						//If its the last song of the playlist, end the line
						if(j == (playlistSongs.length -1))
							playlistOutFile.println("");
						//Else, add the delimiter
						else
							playlistOutFile.print(",");
					}
				}
				else
				{playlistOutFile.println("");}
			}
			
			//Close playlist file objects
			playlistOutFile.close();
			playListFileWriter.close();
			
			System.out.println("Playlists saved successfully!");
		}
	}
	
	/**
	 * This overloaded increaseArray method increases the songIDs Array to allow more songIDs to be added.
	 * 
	 * @param oldSongIDs The songID array to increase
	 * @return The increased songID array
	 */
	public int[] increaseArray(int[] oldSongIDs)
	{
		//Create new songIDs array that is larger than the previous
		int[] newSongIDs = new int[oldSongIDs.length + 1];
		
		//Copy contents of old songIDs array into new songIDs array
		for(int k = 0; k<oldSongIDs.length; k++)
		{
			newSongIDs[k] = oldSongIDs[k];
		}
		
		return newSongIDs;
	}

}
