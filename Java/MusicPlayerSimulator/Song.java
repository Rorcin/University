/*
Author: Fraser Cobb
 */

import java.util.Scanner;

//The Song Class stores and manipulates information about a song.
public class Song {
	static Scanner keyboard = new Scanner(System.in);
	
	//--------------Fields
	private int id;
	private float duration, rating;
	private String title, artist, album, genre;
	
//---------------------------------------------------CONSTRUCTORS------------------------------------------------------------
	/**
	 * Constructor
	 * 
	 * @param newSongArray The details of the song. e.g title, album etc.
	 */
	public Song(String[] newSongArray)
	{
		//Parse ints and floats
		this.id = Integer.parseInt(newSongArray[0]);
		this.title = newSongArray[1];
		this.artist = newSongArray[2];
		this.album = newSongArray[3];
		this.duration = Float.parseFloat(newSongArray[4]);
		this.genre = newSongArray[5];
		this.rating = Float.parseFloat(newSongArray[6]);
	}
	
	/**
	 * Overloaded Constructor - for importing songs from CD
	 * 
	 * @param newSongArray The details of the song. e.g. title, album etc.
	 * @param lastID The ID of the last imported song
	 */
	public Song(String[] newSongArray, int lastID)
	{
		//Genre is 'Unknown' and rating is 0.0 because data of these fields exist in cd imports
		this.id = lastID;
		this.title = newSongArray[0];
		this.artist = newSongArray[1];
		this.album = newSongArray[2];
		this.duration = Float.parseFloat(newSongArray[3]);
		this.genre = "Unknown";
		this.rating = (float) -1.0;
	}
	
//--------------------------ACCESSORS AND MUTATORS-----------------------------------
	/**
	 * The setID method sets the ID of the song.
	 * @param newID
	 */
	public void setID(int newID)
	{this.id = newID;}	
	
	/**
	 * The getID method returns the ID of the song.
	 * @return The songID
	 */
	public int getID()
	{return this.id;}
	
	/**
	 * The setDuration method sets the duration of the song.
	 * @param newDuration The new duration of the song.
	 */
	public void setDuration(float newDuration)
	{this.duration = newDuration;}
	
	/**
	 * The getDuration method returns the duration of the song.
	 * @return The duration of the song
	 */
	public float getDuration()
	{return this.duration;}
	
	/**
	 * The setRating method sets the rating of the song.
	 * @param newRating The new rating of the song.
	 */
	public void setRating(float newRating)
	{this.rating = newRating;}
	
	/**
	 * The getRating method returns the rating of the song.
	 * @return The rating of the song
	 */
	public float getRating()
	{return this.rating;}
	
	/**
	 * The setTitle method sets the title of the song.
	 * @param newTitle The new title of the song.
	 */
	public void setTitle(String newTitle)
	{this.title = newTitle;}
	
	/**
	 * the getTitle method returns the title of the song
	 * @return The title of the song
	 */
	public String getTitle()
	{return this.title;}
	
	/**
	 * The setArtist method sets the artist of the song.
	 * @param newArtist The new artist of the song.
	 */
	public void setArtist(String newArtist)
	{this.artist = newArtist;}
	
	/**
	 * The getArtist method returns the artist of the song.
	 * @return The artist of the song.
	 */
	public String getArtist()
	{return this.artist;}
	
	/**
	 * The setAlbum method sets the album of the song.
	 * @param newAlbum The new album of the song.
	 */
	public void setAlbum(String newAlbum)
	{this.album = newAlbum;}
	
	/**
	 * The getAlbum method returns the album of the song
	 * @return The album of the song
	 */
	public String getAlbum()
	{return this.album;}
	
	/**
	 * The setGenre method sets the genre of the song
	 * @param newGenre The new song genre.
	 */
	public void setGenre(String newGenre)
	{this.genre = newGenre;}
	
	/**
	 * The getGenre method returns the song genre.
	 * @return The new song genre.
	 */
	public String getGenre()
	{return this.genre;}
	
	//------------------------------------------OTHER METHODS---------------------------------------------------------------------------
	
	/**
	 * The toString method returns a string representation of the song for displaying songs
	 * @return SongString The string representation of the song.
	 */	
	public String toString()
	{ 
		//Song fields in a display format
		return "ID: "+this.id + "\n" +"Title: "+this.title + "\n" +"Artist: "+ this.artist + "\n" +"Album: "+ this.album 
				+ "\n" +"Duration: "+this.duration + "\n" +"Genre: "+ this.genre + "\n" +"Rating: "+ this.rating;
	}
	
	/**
	 * The toOutString method returns a string representation of the song for output into a file
	 * @return songString The string representation of the song delimited with commas
	 */
	public String toOutputString()
	{	
		// Song fields delimited by a comma			
		return this.id+","+this.title+","+this.artist+","+this.album+","+this.duration+","+this.genre+","+this.rating;
	}
	
}
