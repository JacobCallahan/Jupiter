import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Jacob J Callahan
 * 1/19/14
 * This class will run our search engine and interact with the user
 */
public class Lab3 {
	private static SearchEngine Jupiter;

	public static void main(String[] args) {
		//We will run a constant loop until the user wants to quit
		Jupiter = new SearchEngine(); 
		String result = "";
		boolean running = true;
		while (running) {
			//print the main menu
			printMainMenu();
			result = readUserInput();
			if (Integer.parseInt(result) == 1) {
				//perform a search!
				result = getSearchString();
				Jupiter.printSearchResults(result);
			} else if (Integer.parseInt(result) == 2) {
				//add a file
				result = getFilePath();
				try {
					Jupiter.addFile(result);
				} catch (IOException e) {
					System.out.println("Unable to add file!");
				}
			} else if (Integer.parseInt(result) == 3) {
				//Goodbye!
				Jupiter.printTrie();
			} else if (Integer.parseInt(result) == 4) {
				//Goodbye!
				running = false;
			} else {
				System.out.println("I do not understand your choice: " + result);
			}
		}

	}
	
	public static void printMainMenu() {
		//this method will print out a basic menu
		System.out.println("Please make a numeric selection.");
		System.out.println(" ");
		System.out.println("1: Perform a search.");
		System.out.println("2: Add a file.");
		System.out.println("3: Print the data structure.");
		System.out.println("4: Exit.");
		System.out.println(" ");
	}
	
	
	public static String getSearchString() {
		//this method will prompt the user for input, then return the result
		System.out.println("Please enter your search terms. No Special characters, please!");
		System.out.print(": ");
		String result = readUserInput();
		return result;
	}
	
	public static String getFilePath() {
		//this method will prompt the user for input, then return the result
		System.out.println("Please enter the full path to the file (e.g. C:\\Users\\You\\Desktop\\document.txt).");
		System.out.print(": ");
		String result = readUserInput();
		return result;
	}
	
	public static String readUserInput() {
		//we will use this function to return the user's input
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		String userIn = "";
		try {
			userIn = userInput.readLine();
		} catch (IOException e) {
			System.out.println("Unable to read input." + e);
		}
		return userIn;
	}

}
