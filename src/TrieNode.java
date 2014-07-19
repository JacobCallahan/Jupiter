/*
 * Jacob J Callahan
 * 1/15/14
 * This class will be the main content of our Trie data structure
 */
public class TrieNode {
	private char nodeValue;
	private TrieNode[] children;
	private IndexNode index;
	
	TrieNode(char charVal) {
		//This constructor will set the values to the appropriate
		nodeValue = charVal;
		children = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			children[i] = null;
		}
		index = null;				
	}
	
	public char getValue() {
		return nodeValue;
	}
	
	public TrieNode addChild(char newVal) {
		if (children[newVal-65] == null) {
			children[newVal-65] = new TrieNode(newVal);
		}
		return children[newVal-65];
	}
	
	public void updateIndex(String sourceDocument) {
		//update our index with the current source document
		if (index == null) {
			index = new IndexNode(sourceDocument);
		} else {
			index.addDocument(sourceDocument);
		}
	}
	
	public IndexNode getIndex() {
		return index;
	}
	
	public TrieNode findChild(char childVal) {
		return children[childVal-65];
	}
	
	public void printSelf(int offset) {
		//this quick method will print the current node and all it's children
		String offsetString = "";
		String append = "";
		if (index != null) {append += "-->" + index.getTopDoc();}
		for (int i = 0; i < offset; i++) {
			offsetString += " ";
		}
		System.out.println(offsetString + nodeValue + append);
		//iterate through our available children
		for (int i = 0; i < 26; i++) {
			if (children[i] != null) {
				children[i].printSelf(offset + 4);
			}
		}		
	}
	
}
