package exercise3;

/**
 * Started by M. Moussavi
 * March 2015
 * Completed by:
 * @author Patrick Kwan
 * @version 1.0
 * @since November 9, 2020
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteRecord {

	ObjectOutputStream objectOut = null;
	MusicRecord record = null;
	Scanner stdin = null;
	Scanner textFileIn = null;

	/**
	 * Creates an blank MusicRecord object
	 */
	public WriteRecord() {
		record = new MusicRecord();
	}

	/**
	 * Initializes the data fields of a record object
	 * @param year - year that song was purchased
	 * @param songName - name of the song
	 * @param singerName - singer's name
	 * @param price - CD price
	 */
	public void setRecord(int year, String songName, String singerName,
                                                                 double price) {
		record.setSongName(songName);
		record.setSingerName(singerName);
		record.setYear(year);
		record.setPrice(price);
	}
    
	/**
	 * Opens a file input stream, using the data field textFileIn
	 * @param textFileName name of text file to open
	 * @see https://stackoverflow.com/questions/46126202/using-a-scanner-to-read-a-txt-file
	 */
	public void openFileInputStream(String textFileName) {
        
     // TO BE COMPLETED BY THE STUDENTS
	try {
		FileInputStream temp = new FileInputStream(textFileName);
		textFileIn = new Scanner(temp);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}

	/**
	 * Opens an ObjectOutputStream using objectOut data field
	 * @param objectFileName name of the object file to be created
	 * Need to override
	 * @see https://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream#comment8987320_1195078
	 */
	public void openObjectOutputStream(String objectFileName) {
        
    // TO BE COMPLETED BY THE STUDENTS
		try {
		FileOutputStream fileOut = new FileOutputStream(objectFileName,true);
		
			objectOut = new ObjectOutputStream(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	

	
	
	/** 
	 * Reads records from given text file, fills the blank MusicRecord
	 * created by the constructor with the existing data in the text
	 * file and serializes each record object into a binary file
	 * @see https://www.tutorialspoint.com/java/java_serialization.htm
	 */
	public void createObjectFile() {

		while (textFileIn.hasNext()) // loop until end of text file is reached
		{
			int year = Integer.parseInt(textFileIn.nextLine());
			System.out.print(year + "  ");       // echo data read from text file
            
			String songName = textFileIn.nextLine();
			System.out.print(songName + "  ");  // echo data read from text file
            
			String singerName = textFileIn.nextLine();
			System.out.print(singerName + "  "); // echo data read from text file
            
			double price = Double.parseDouble(textFileIn.nextLine());
			System.out.println(price + "  ");    // echo data read from text file
            
			setRecord(year, songName, singerName, price);
			textFileIn.nextLine();   // read the dashed lines and do nothing
            
            // THE REST OF THE CODE TO BE COMPLETED BY THE STUDENTS
			try {
				System.out.println("Added record to file.");
				objectOut.writeObject(record);
				objectOut.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// YOUR CODE GOES HERE: refer to 
		
		//The trick is you may have to start a file first then for every consecutive entry you need to figure out how to append.
		//you need to override the writeStreamheaders
		//Do we close everything?
		//objectOut.close ?
		//textFileIn.close ?
	
	}

	public static void main(String[] args) throws IOException {
        
		WriteRecord d = new WriteRecord();
        
		String textFileName = "someSongs.txt"; // Name of a text file that contains
                                               // song records
        
		String objectFileName = "mySongs.ser"; // Name of the binary file to
                                               // serialize record objects
        
		d.openFileInputStream(textFileName);   // open the text file to read from
        
		d.openObjectOutputStream(objectFileName); // open the object file to
                                                  // write music records into it
        
		d.createObjectFile();   // read records from opened text file, and write
                                // them into the object file.
	}
}
