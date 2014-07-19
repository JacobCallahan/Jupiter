/*
 * Jacob J Callahan
 * 1/15/14
 * This class will be the main content of our Trie data structure
 */
public class IndexNode {
	private String[] documents;
	private int[] documentCount;	
	
	IndexNode(String sourceDocument) {
		documents = new String[10]; //limit it to 10 for this implementation
		documentCount = new int[10];
		for (int i = 0; i < 10; i++) { //initialize the arrays
			documents[i] = null;
		}
		for (int i = 0; i < 10; i++) {
			documentCount[i] = 0;
		}
		addDocument(sourceDocument);
	}
	
	public void addDocument(String sourceDocument) {
		//this method will add a document to the list and increment its counter
		int i = 0;
		boolean searching = true;
		while (i < documents.length && searching) {
			if (documents[i] == sourceDocument) {
				documentCount[i]++;
				searching = false;
			} else if (documents[i] == null) {
				documents[i] = sourceDocument;
				documentCount[i]++;				
				searching = false;
			}
			i++;
		}
	}
	
	public String[] getTopFiveDocs() {
		//this method will return the top 5 documents
		String[] returnString = new String[5];
		for (int i = 0; i < 5; i++) {
			if (documents[i] != null) {
				returnString[i] = documents[i];
			} else {
				returnString[i] = null;
			}
		}
		return returnString;
	}
	
	public int[] getTopFiveCounts() {
		//this method will return the top 5 document counts
		int[] returnArray = new int[5];
		for (int i = 0; i < 5; i++) {
			if (documents[i] != null) {
				returnArray[i] = documentCount[i];
			} else {
				returnArray[i] = 0;
			}
		}
		return returnArray;
	}
	
	public String getTopDoc() {
		//this will return the current top result (assuming we are sorted)
		//documentString x 1
		return documents[0] + " x " + documentCount[0];
	}
	
	public void sortList() {
		//this will do a simple selection sort on the count list 
		//then update the document list
		int largest, largestPos = 0, currPos = 0, c = 0, temp = 0;
		String tempString = null, largestString = null;
		while (c < documentCount.length)	{
			largest = documentCount[currPos];
			for (int i = currPos; i < documentCount.length; i++) {
				//check to see if the current number is smaller than the smallest
				if (documentCount[i] > largest) {
					//if so, update the number and the position
					largest = documentCount[i];
					largestString = documents[i];
					largestPos = i;
				}				
			}
			//make the swap
			temp = documentCount[currPos];
			tempString = documents[currPos];
			documentCount[currPos] = largest;
			documents[currPos] = largestString;
			documentCount[largestPos] = temp;
			documents[largestPos] = tempString;
			
			currPos++;
			c++;
		}
	}
}
