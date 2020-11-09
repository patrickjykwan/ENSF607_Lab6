package exercise3;

/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  opens an ObjectInputStream using a FileInputStream
     */
    
    private void readObjectsFromFile(String name)
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several reords.
         * Loop should terminate when an EOFException is thrown.
         */
        
        //try
        
       System.out.println("File : " + name);
        //{
            while ( true )
    
            {
            	try {
					record = (MusicRecord) input.readObject();
					System.out.println("Year: " + record.getYear() + "\nName: " + record.getSongName() +"\nSinger: "+record.getSingerName() + "\nPrice: " + record.getPurchasePrice()+"\n");
            	}catch(IOException e) {
            		System.out.println("Breaking using IOException");
					break;
            	}catch(ClassNotFoundException e) {
            		e.printStackTrace();
            	}
                
                // TO BE COMPLETED BY THE STUDENTS
                
           
            }   // END OF WHILE
        //}
                // ADD NECESSARY catch CLAUSES HERE

    }           // END OF METHOD 
    
    
    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        //d.readObjectsFromFile("mySongs.ser");
        d.readObjectsFromFile("allSongs.ser");
    }
}