import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' }; 
        // int[] charFreq = { 5, 9, 12, 13, 16, 45 }; 
        HuffmanTree tree = new HuffmanTree("geeksforgeeks");
        System.out.println(tree.decode());
        List<Integer> compressed = LZWCompressor.compress("geeksforgeeks");
        System.out.println(compressed);
        String decompressed = LZWCompressor.decompress(compressed);
        System.out.println(decompressed);
	}

}
