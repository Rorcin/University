/*
Author: Fraser Cobb
 */

//The playlist class stores information about a song.
public class Playlist {
	
	//Fields
	private int id, length, songs[];
	private float duration; 
	private String name;
	
//---------------------------------------------------CONSTRUCTORS------------------------------------------------------------
	
	/**
	 * Constructor - used for songCollection.txt imports
	 * 
	 * @param newPlaylistDetails An array containing the id, name and length of the playlist
	 * @param newSongs An array of song IDs
	 */
	public Playlist(String[] newPlaylistDetails, int[] newSongs)
	{		
		this.id = Integer.parseInt(newPlaylistDetails[0]);
		this.name = newPlaylistDetails[1];
		this.length = Integer.parseInt(newPlaylistDetails[2]);
		
		if(newSongs != null)
		{	
			this.songs = new int[newSongs.length];
			for(int i=0; i<newSongs.length; i++)
			{
				this.songs[i] = newSongs[i];
			}
		}
	}
	
	/**
	 *  Constructor - used for adding new playlists
	 * @param newPlaylistName The name of the new playlist
	 * @param newID The ID of the playlist
	 */
	public Playlist(String newPlaylistName, int newID)
	{
		this.id = newID;
		this.length = 0;
		this.songs = null;
		this.duration = 0;
		this.name = newPlaylistName;
	}
	
//--------------------------------------ACCESSORS AND MUTATORS------------------------------------------------
	
	/**
	 *  The setID method.
	 * @param newID The ID to set.
	 */
	public void setID(int newID)
	{this.id = newID;}
	
	/**
	 * The getID method
	 * @return the ID of the playlist
	 */
	public int getID()
	{return this.id;}
	
	/**
	 * The setName method.
	 * @param newName The new name of the playlist
	 */
	public void setName(String newName)
	{this.name = newName;}
	
	/**
	 * The getName method.
	 * @return The name of the playlist
	 */
	public String getName()
	{return this.name;}
	
	/**
	 *  The setLength method.
	 * @param newLength The new length of the playlist
	 */
	public void setLength(int newLength)
	{this.length = newLength;}
	
	/**
	 * The getLength method.
	 * @return The length of the playlist.
	 */
	public int getLength()
	{return this.length;}
	
	/**
	 * The setDuration method.
	 * @param newDuration The new duration of the playlist.
	 */
	public void setDuration(float newDuration)
	{this.duration = newDuration;}
	
	/**
	 * The getDuration method.
	 * @return The duration of the playlist.
	 */
	public float getDuration()
	{return this.duration;}
	
	/**
	 * The setSongs method.
	 * @param newSongs The new songs of the playlist.
	 */
	public void setSongs(int[] newSongs)
	{this.songs = newSongs;}
	
	/**
	 * The getSongs method.
	 * @return The songs of the playlist.
	 */
	public int[] getSongs()
	{return  this.songs;}
	
//------------------------------------OTHER METHODS-------------------------------------------------------
	
	/**
	 *  The toString method returns a string representation of the playlist for writing to a file
	 *  @return The playlist id, name and length in one string, delimited by commas.
	 */
	public String toString()
	{
		//Playlist ID, Name, Length
		return this.id+","+this.name+","+this.length;
	}
	
	/**
	 * The toStringArray method returns a string array representation of the playlist for display purposes.
	 * @return The string array representation of the playlist.
	 */
	public String[] toStringArray()
	{
		String[] stringArray = {(""+this.id), this.name, ""+this.length};
		return stringArray;
	}
}
