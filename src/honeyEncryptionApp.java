import java.util.*;

public class honeyEncryptionApp {

	static String userPass;
	static String message;
	static String exit;
	static int trueSeed;
	static boolean status = true;
	static Random rand = new Random();
	private static Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		HashMap<String, Integer> passwordToSeeds = new HashMap<>();
		HashMap<Integer, String> seedsToMessages = new HashMap<>();
		HashMap<String, String> states = new HashMap<>(); //U.S States as secret messages
		assignStates(states);
		
		do {
			ask(); 			
			trueSeed = rand.nextInt(27) + 1;
			honeyWordGenerator(passwordToSeeds,seedsToMessages,states,trueSeed,userPass,message);			
			int cipher = (passwordToSeeds.get(userPass)) ^ trueSeed;
			displayPassword(passwordToSeeds);
			crack(passwordToSeeds,seedsToMessages,cipher,trueSeed);
			askExit(passwordToSeeds,seedsToMessages);
		} while(status);
		
		System.out.println("\nThank you for testing Honey Encryption.");
		sc.close();
	}

	//ask user to crack the user
	public static void crack(HashMap<String, Integer> passwordToSeeds, HashMap<Integer, String> seedsToMessages, int cipher, int trueSeed) {
		System.out.print("Enter your password: ");
		String query = sc.nextLine();
		
		if(passwordToSeeds.containsKey(query)) {
			int keySeed = passwordToSeeds.get(query);
		
			int m = keySeed ^ cipher; //decryption key using XOR method
		
			if (m != trueSeed) {
				System.out.println("INTRUDER HAS BEEN FOUND!");
			}		
			
			System.out.println(seedsToMessages.get(m));
			
		} else {
			System.out.println("Password not found");
		}
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
		
		//honeyword1 by combining the original password with the random seed - 1
		passwordToSeeds.put(userPass + String.valueOf(trueSeed-1), trueSeed + 1); 
		seedsToMessages.put(trueSeed + 1, states.get("AL"));
		
		//honeyword2 by combining the original password with the random seed - 2 and then append 1
		passwordToSeeds.put(userPass + String.valueOf(trueSeed-2) + "1", trueSeed + 2); 
		seedsToMessages.put(trueSeed + 2, states.get("CA"));
		
		//honeyword3 by make it lower case
		passwordToSeeds.put(userPass.toLowerCase(), trueSeed + 3); 
		seedsToMessages.put(trueSeed + 3, states.get("FL"));
		
		//honeyword4 by make it lower case and add the random seed + 1 and append 3
		passwordToSeeds.put(userPass.toLowerCase() + String.valueOf(trueSeed + 1) + "3", trueSeed + 4); 
		seedsToMessages.put(trueSeed + 4, states.get("TX"));
		
		//honeyword5 by make it upper case
		passwordToSeeds.put(userPass.toUpperCase(), trueSeed + 5); 
		seedsToMessages.put(trueSeed + 5, states.get("TN"));
		
		//honeyword6 by make it upper case and append with the random value add by 2 and append 5
		passwordToSeeds.put(userPass.toUpperCase() + String.valueOf(trueSeed + 2) + "5", trueSeed + 6); 
		seedsToMessages.put(trueSeed + 6, states.get("WA"));
		
	}
	
	//ask user password and secret message
	public static void ask() { 
		System.out.print("Please enter a password: ");
		userPass = sc .nextLine();
		System.out.print("Please enter a secret message to store (one word): ");
		message = sc .nextLine();		
	}
	
	//exit from the loop
	public static void askExit(HashMap<String, Integer> passwordToSeeds, HashMap<Integer, String> seedsToMessages) { 
		passwordToSeeds.clear(); //clearing the hash map
		seedsToMessages.clear(); //clearing the hash map
		System.out.print("\nWould you like to enter another inquiry (Y/N)?: ");
		exit = sc.nextLine();
		System.out.println();
		if(exit.toLowerCase().equals("n"))
			status = false;
		return;
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