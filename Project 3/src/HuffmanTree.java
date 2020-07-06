import java.util.*;
public class HuffmanTree {
	
	private HuffmanTreeNode root;
	private HashMap<Character,Integer> charFreq; 
	private HashMap<Character,String> code;
	private String encodedString, input;
	
	public HuffmanTree() {
		root = null;
		charFreq = new HashMap<Character,Integer>();
		code = new HashMap<Character,String>();
	}
	
	public HuffmanTree(String word) {
		this();
		setCharFreq(word);
		this.input = word;
		buildHuffmanTree();
		encodedString = encode();
	}
	
	public HuffmanTree(char [] charArray, int [] charFreq) {
		this();
		setCharFreq(charArray,charFreq);
		this.input = new String(charArray);
		buildHuffmanTree();
		encodedString = encode();
	}
	
	public String getInput() {
		return this.input;
	}
	
	public void setInput(String input) {
		this.input = input;
		setCharFreq(this.input);
		buildHuffmanTree();
		encodedString = encode();
	}
	
	public void getCode() {
		getCode(this.root,"", this.code);
	}
	
	public String decode() {
		StringBuilder message = new StringBuilder();
		char [] arr = encodedString.toCharArray();
		int index = 0;
		while (index < arr.length) {
			HuffmanTreeNode node = root;
			while(node != null) {
				if (node.getLeft() == null && node.getRight() == null) {
					message.append(node.getCharacter());
					break;
				} else {
					char c = arr[index];
					if (c == '0')
						node = node.getLeft();
					else
						node = node.getRight();
					index += 1;
				}
			}
		}
		return message.toString();
	}
	
	private void setCharFreq(char [] charArray, int [] charFreq) {
		for (int i = 0; i < charArray.length; i++)
			this.charFreq.put(charArray[i],charFreq[i]);
	}
	
	private void setCharFreq(String word) {
		for (Character c: word.toCharArray()) {
			int val = charFreq.getOrDefault(c, 0)+1;
			charFreq.put(c, val);
		}
	}
	
	private void buildHuffmanTree() {
		// Creating the Huffman Tree Node objects and placing it into
		// a priority queue for set up
		PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue<>();
		for(Character c: charFreq.keySet()) {
			int freq = charFreq.get(c);
			HuffmanTreeNode node = new HuffmanTreeNode(c,freq);
			queue.add(node);
		}
		
		while(queue.size() > 1) {
			HuffmanTreeNode leftChild = queue.peek();
			queue.poll();
			
			HuffmanTreeNode rightChild = queue.peek();
			queue.poll();
			
			HuffmanTreeNode parent = new HuffmanTreeNode();
			parent.setData(leftChild.getData() + rightChild.getData());
			parent.setLeftChild(leftChild);
			parent.setRightChild(rightChild);
			root = parent;
			
			queue.add(parent);
			
		}
		getCode();
	}
	
	private String encode() {
		StringBuilder s = new StringBuilder();
		for (Character c: this.input.toCharArray())
			s.append(code.get(c));
		return s.toString();
	}
	
	 private void getCode(HuffmanTreeNode root, String s, HashMap<Character,String> map){ 
	        if (root.getLeft()
	                == null
	            && root.getRight()
	                   == null
	            && Character.isLetter(root.getCharacter())) { 
	  
	            // c is the character in the node 
	            //System.out.println(root.getCharacter() + ":" + s); 
	            map.put(root.getCharacter(),s);
	  
	            return; 
	        }
	        
	        getCode(root.getLeft(), s + "0", map); 
	        getCode(root.getRight(), s + "1", map); 
	  }
	
	
}
