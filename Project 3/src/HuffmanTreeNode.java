public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{

	private int data;
	private char c;
	private HuffmanTreeNode left,right;
	
	public HuffmanTreeNode() {
		c = '-';
		data = -1;
		left = right = null;
	}
	
	public HuffmanTreeNode(char c, int data) {
		this.c = c;
		this.data = data;
		left = right = null;
	}
	
	public int getData() {
		return this.data;
	}
	
	public char getCharacter() {
		return this.c;
	}
	
	public HuffmanTreeNode getLeft() {
		return this.left;
	}
	
	public HuffmanTreeNode getRight() {
		return this.right;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setCharacter(char c) {
		this.c = c;
	}
	
	public void setLeftChild(HuffmanTreeNode left) {
		this.left = left;
	}
	
	public void setRightChild(HuffmanTreeNode right) {
		this.right = right;
	}
	@Override
	public int compareTo(HuffmanTreeNode node) {
		return this.data - node.getData();
	}

}
