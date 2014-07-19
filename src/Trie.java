/*
 * Jacob J Callahan
 * 1/15/14
 * This class will be the controller of our Trie data structure
 */
public class Trie {
	private TrieNode rootNode;
	private String currentDocument;
	
	Trie() {
		//default constructor will create a new root node will a meaningless value
		rootNode = new TrieNode('~');
	}
	
	public void addWord(String newWord) {
		char[] lettersInWord = newWord.toCharArray(); //First, break the word up into characters		
		TrieNode currNode = rootNode; //set the root node as the current node
		for (int i = 0; i < newWord.length(); i++) { //for each character in the word
			currNode = currNode.addChild(lettersInWord[i]); //add a letter and get that new node
		}
		currNode.updateIndex(currentDocument); //then update the index value of the current node		
	}
	
	public IndexNode findWord(String word) {
		char[] lettersInWord = word.toCharArray(); //First, break the word up into characters		
		TrieNode currNode = rootNode; //set the root node as the current node
		IndexNode returnIndex = null;
		boolean searching = true;
		int i = 0;
		while (searching) {
			currNode = currNode.findChild(lettersInWord[i]);  //get the next node in order
			if (i == word.length()-1) {  //if we are on the last word
					returnIndex = currNode.getIndex(); //return the index value
					searching = false;
				
			} else {
				if (currNode == null) {    //if it is null, then stop
					searching = false;
				} else {
					i++;
				}
			}
		}
		return returnIndex;  //finally return the index for later use
	}
	
	public void updateDoc(String currentDoc) {
		//update the current document
		currentDocument = currentDoc;
	}
	
	public void printTrie() {
		//simply print the trie
		rootNode.printSelf(0);
	}
	
}
