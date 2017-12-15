import java.util.*;
import java.io.*;

//testing encryption speed using 10, 100, 1000, 10000, 100000, and 1000000 data set

public class honeyEncryptionTesting {

	static String message = "fake information";
	static int trueSeed;
	static Random rand = new Random();
	
	public static void main(String[] args) {
		HashMap<String, Integer> passwordToSeeds = new HashMap<>();
		HashMap<Integer, String> seedsToMessages = new HashMap<>();
		HashMap<String, String> states = new HashMap<>(); //U.S States as secret messages
		assignStates(states);
		
		String fileName = "2_data.txt";
		String line = null;
		
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            
			final long startTime = System.nanoTime(); //time start
            
			while((line = bufferedReader.readLine()) != null) { //keep reading the file until it reach the end of the lines
				trueSeed = rand.nextInt(27) + 1;
    				honeyWordGenerator(passwordToSeeds,seedsToMessages,states,trueSeed,line,message);
    				i++;
            }   
			
            System.out.println("How many password it encrypts: " + i + " and the size of hashmap " + passwordToSeeds.size());

			final long endTime = System.nanoTime(); //time end
			final double seconds = (double)(endTime - startTime)/1000000000.0;
		    System.out.println("The time it takes to encrypt: " + seconds + " seconds " + i); //total time it takes
            
		    // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'"); 
        }
		
				
		System.out.println("\nThank you for testing Honey Encryption.");
	}
	
	//display all the honey words
	public static void displayPassword(HashMap<String, Integer> passwordToSeeds) {
		Iterator it =  passwordToSeeds.entrySet().iterator();
		
		System.out.print("[");
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			System.out.print(pair.getKey() + ",");
		}
		System.out.println("]");
	}
	
	//create the honey word
	public static void honeyWordGenerator(HashMap<String, Integer> passwordToSeeds, HashMap<Integer, String> seedsToMessages,HashMap<String, String> states, int trueSeed,String userPass,String message) {
		passwordToSeeds.put(userPass, trueSeed); //true password
		seedsToMessages.put(trueSeed, message);
		
		passwordToSeeds.put(userPass + String.valueOf(trueSeed-1), trueSeed + 1); //honeyword1 by combining the original password with the random seed - 1
		seedsToMessages.put(trueSeed + 1, states.get("AL"));
		
		passwordToSeeds.put(userPass + String.valueOf(trueSeed-2) + "1", trueSeed + 2); //honeyword2 by combining the original password with the random seed - 2 and then append 1
		seedsToMessages.put(trueSeed + 2, states.get("CA"));
		
		passwordToSeeds.put(userPass.toLowerCase(), trueSeed + 3); //honeyword3 by make it lower case
		seedsToMessages.put(trueSeed + 3, states.get("FL"));
		
		passwordToSeeds.put(userPass.toLowerCase() + String.valueOf(trueSeed + 1) + "3", trueSeed + 4); //honeyword4 by make it lower case and add the random seed + 1 and append 3
		seedsToMessages.put(trueSeed + 4, states.get("TX"));
		
		passwordToSeeds.put(userPass.toUpperCase(), trueSeed + 5); //honeyword5 by make it upper case
		seedsToMessages.put(trueSeed + 5, states.get("TN"));
		
		passwordToSeeds.put(userPass.toUpperCase() + String.valueOf(trueSeed + 2) + "5", trueSeed + 6); //honeyword6 by make it upper case and append with the random value add by 2 and append 5
		seedsToMessages.put(trueSeed + 6, states.get("WA"));
		
	}
	
	//assign all the U.S states into the hash map as a thing we tried to protect
	public static void assignStates(HashMap<String, String> states) {
		states.put("AK", "Alaska");
		states.put("AL", "Alabama");
		states.put("AR", "Arkansas");
		states.put("AS", "American Samoa");
		states.put("AZ", "Arizona");
		states.put("CA", "California");
		states.put("CO", "Colorado");
		states.put("CT", "Connecticut");
		states.put("DC", "District of Columbia");
		states.put("DE", "Delaware");
		states.put("FL", "Florida");
		states.put("GA", "Georgia");
		states.put("GU", "Guam");
		states.put("HI", "Hawaii");
		states.put("IA", "Iowa");
		states.put("ID", "Idaho");
		states.put("IL", "Illinois");
		states.put("IN", "Indiana");
		states.put("KS", "Kansas");
		states.put("KY", "Kentucky");
		states.put("LA", "Louisiana");
		states.put("MA", "Massachusetts");
		states.put("MD", "Maryland");
		states.put("ME", "Maine");
		states.put("MI", "Michigan");
		states.put("MN", "Minnesota");
		states.put("MO", "Missouri");
		states.put("MP", "Northern Mariana Islands");
		states.put("MS", "Mississippi");
		states.put("MT", "Montana");
		states.put("NA", "National");
		states.put("NC", "North Carolina");
		states.put("ND", "North Dakota");
		states.put("NE", "Nebraska");
		states.put("NH", "New Hampshire");
		states.put("NJ", "New Jersey");
		states.put("NM", "New Mexico");
		states.put("NV", "Nevada");
		states.put("NY", "New York");
		states.put("OH", "Ohio");
		states.put("OK", "Oklahoma");
		states.put("OR", "Oregon");
		states.put("PA", "Pennsylvania");
		states.put("PR", "Puerto Rico");
		states.put("RI", "Rhode Island");
		states.put("SC", "South Carolina");
		states.put("SD", "South Dakota");
		states.put("TN", "Tennessee");
		states.put("TX", "Texas");
		states.put("UT", "Utah");
		states.put("VA", "Virginia");
		states.put("VI", "Virgin Islands");
		states.put("VT", "Vermont");
		states.put("WA", "Washington");
		states.put("WI", "Wisconsin");
		states.put("WV", "West Virginia");
		states.put("WY", "Wyoming");
	}
}