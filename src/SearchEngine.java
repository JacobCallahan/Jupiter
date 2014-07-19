import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Jacob J Callahan
 * 1/18/14
 * This class is the work horse of the "Jupiter" search engine
 */
public class SearchEngine {
	Trie myTrie;
	
	SearchEngine() {
		myTrie = new Trie();
	}
	
	
	private void addLine(String newLine) {
		//make everything upper case and strip all extra characters
		newLine = newLine.toUpperCase().replaceAll("[^A-Z ]", ""); 
		String[] words = newLine.split(" "); //then split the 
		for (int i = 0; i < words.length; i++) {
			addWord(words[i]); //and add the word!
		}
	}
	
	private void addWord(String newWord) {
		myTrie.addWord(newWord);
	}	
	
	private String[] getSearchResults(String searchString) {
		//this method will take in a search string and return a list of the top results
		//make everything upper case and strip all extra characters
		searchString = searchString.toUpperCase().replaceAll("[^A-Z ]", ""); 
		String[] words = searchString.split(" "); //then split the 
		IndexNode[] resultIndex = new IndexNode[words.length * 5];
		//since it is easier just to remove these words from search strings, we will do it in this method
		String skipWords = "THE|OF|TO|AND|A|IN|IS|IT|YOU|THAT|HE|WAS|FOR|ON|ARE|WITH|AS|I|HIS|THEY|BE|AT|ONE|HAVE|THIS";
		for (int i = 0; i < words.length; i++) {
			if (skipWords.indexOf(words[i]) < 0) { //if they aren't searching for a skip word
				resultIndex[i] = myTrie.findWord(words[i]); //and add the returned index to the array
			}
		}
		//now we will process the returned indices
		String[] sortList = new String[resultIndex.length]; //These two variables will hold the results
		int[] sortCount = new int[resultIndex.length];      //from all of the returned indices
		String[] tempList = new String[5]; //These two variables will temporarily
		int[] tempCount = new int[5];      //hold the current index's results
		for (int i = 0; i < resultIndex.length; i++) {
			if (resultIndex[i] != null) {				
				//resultIndex[i].sortList();
				tempList = resultIndex[i].getTopFiveDocs();
				tempCount = resultIndex[i].getTopFiveCounts();
				for (int c = i * 5, x = 0; x < 5; c++, x++) { //use c to keep track of the array position. x for iteration
					sortList[c] = tempList[x]; //save the current temp value into the sort array
					sortCount[c] = tempCount[x];
				}				
			}
		}
		sortList = sortLists(sortList, sortCount); //sort the documents using the two arrays
		boolean good; //this will help eliminate duplicates
		for (int i = 0; i < tempList.length; i++) { //pull in the top 5 results
			if (sortList[i] != null) {
				good = true;
				for (int c = 0; c < tempList.length; c++) {
					if (sortList[i] == tempList[c]) {good = false;} //check to make sure there aren't any duplicates
				}
				if (good) {tempList[i] = sortList[i];}
			}
		}
		return tempList; //return the results!
	}


	private String[] sortLists(String[] docList, int[] countList) {
		//this will do a simple selection sort on the count list 
		//then update the document list and pass it back
		int largest, largestPos = 0, currPos = 0, c = 0, temp = 0;
		String tempString = null, largestString = null;
		while (c < countList.length)	{
			largest = countList[currPos];
			for (int i = currPos; i < countList.length; i++) {
				//check to see if the current number is smaller than the smallest
				if (countList[i] > largest) {
					//if so, update the number and the position
					largest = countList[i];
					largestString = docList[i];
					largestPos = i;
				}				
			}
			//make the swap
			temp = countList[currPos];
			tempString = docList[currPos];
			countList[currPos] = largest;
			docList[currPos] = largestString;
			countList[largestPos] = temp;
			docList[largestPos] = tempString;
			
			currPos++;
			c++;
		}
		return docList;
	}


	public void addFile(String fileName) throws IOException {
		//this method will take in a new file and add in the words line by line
		myTrie.updateDoc(fileName);
		FileReader fileIn = new FileReader(fileName);
		BufferedReader lineReader = new BufferedReader(fileIn);
		String currentLine;
		while ((currentLine = lineReader.readLine()) != null) {
			System.out.println(currentLine);
			addLine(currentLine); //add the current line to the trie
		}
		lineReader.close();
		fileIn.close();
	}


	public void printSearchResults(String searchString) {
		//a quick method to print out the results
		String[] tempString = getSearchResults(searchString);
		System.out.println("");
		System.out.println("~ Search results ~");
		for (int i = 0; i < tempString.length; i++) {
			//System.out.println(tempString[i]);
			if (tempString[i] != null) {System.out.println(i + 1 + ": " + tempString[i]);}
		}
	}
	
	public IndexNode findWord(String word) {
		//quickly find if we have a word, and return the index
		IndexNode returnedIndex = myTrie.findWord(word);
		return returnedIndex;
	}
	
	public void printTrie() {
		myTrie.printTrie();
	}

}
