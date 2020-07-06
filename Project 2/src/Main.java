/*
 * CSCI323 Design And Analysis of Algorithms
 * Project 2: Dynamic Programming
 * @authors Brian Kenji Aguilar, Danish Bokhari, Tristen Aguilar
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	/**
	 * Reads a single line from a file and cleans the line such that
	 * the input text string will only contain uppercase or lowercase
	 * letters
	 * 
	 * @param fileReader the file that we are reading from
	 * @return a new string where we remove all characters that are not
	 * letters in the file
	 * @throws Exception where we the file could not be found
	 */
	public static String cleanFile(FileReader fileReader) throws Exception {
		StringBuilder cleanedFile = new StringBuilder();
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		line = br.readLine();
		//(2) Print out the file content before it was cleaned
		System.out.println("File content before cleaning: " + line);
		System.out.println("File length before cleaning: " + line.length());
		for (int i = 0; i < line.length(); i++) {
			char character = line.charAt(i);
			if(Character.isLetter(character))
				cleanedFile.append(character);
		}
		br.close();
		// (3) Print out the file content after it was cleaned
		System.out.println("File content after cleaning: " + cleanedFile.toString());
		System.out.println("File length after cleaning: " + cleanedFile.toString().length());
		return cleanedFile.toString();
	}
	
	/**
	 * Determines the longest non-contiguous palindrome in
	 * a given string
	 * @param s the given input string
	 * @return the longest palindromic substring 
	 */
	public static String longestPalindromicSubseqeuence(String s) {
	    StringBuilder r = new StringBuilder(s);
        char [] original = s.toCharArray();
        char [] reverse = r.reverse().toString().toCharArray();
        char [][] dir = new char[original.length+1][reverse.length+1];
        return lcs(original,reverse,dir);
	}
	
	/**
	 * Determines the longest contiguous palindrome in
	 * a given string
	 * @param s the given input string
	 * @return the longest palindromic substring 
	 */
	public static String longestPalindromicSubstring(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        
        for(int i = n-1; i >= 0; i--){
            for(int j = i; j < n; j ++){
                dp[i][j] = (s.charAt(i) == s.charAt(j) && (j-i < 3 || dp[i+1][j-1]));
                
                if(dp[i][j] && (res == "" || res.length() < j-i+1)){
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }
	
	/**
	 * Finds the longest common subsequence between 2 strings
	 * @param X string one represented as a char array
	 * @param Y string two represented as a char array
	 * @param dir to keep track of the LCS to print out the string
	 * @return the longest common subsequence between 2 strings
	 */
	public static String lcs(char X[], char Y[], char[][] dir) {
		int [][] len = new int[X.length+1][Y.length+1];
		for(int i=0;i<X.length;i++)
			len[i][0] = 0;
		for(int j=0;j<=Y.length;j++)
			len[0][j]=0;
		for(int i=0;i<X.length;i++) {
			for(int j=0;j<Y.length;j++) {
				if(X[i] == Y[j]) {
					len[i+1][j+1] = len[i][j]+1;
					dir[i+1][j+1] = 'D';
				} else if(len[i][j+1] >= len[i+1][j]) {
					len[i+1][j+1] = len[i][j+1];
					dir[i+1][j+1] = 'U';
				} else {
					len[i+1][j+1] = len[i+1][j];
					dir[i+1][j+1] ='L';
				}
			}
		}
		return lcsString(X,X.length,Y.length,dir);
	}
	
	public static String lcsString(char X[], int i, int j , char [][] dir) {
		if(i == 0 || j == 0)
			return "";
		if (dir[i][j] == 'D') {
			return lcsString(X,i-1,j-1,dir) + X[i-1];
		} else if (dir[i][j] == 'U') {
			return lcsString(X,i-1,j,dir);
		} else {
			return lcsString(X,i,j-1,dir);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("QueensCollegeDescription.txt");
		// (1) Print out the file name
		System.out.println(" File Name: " + file.getName());
		FileReader fileReader = new FileReader(file);
		String cleanedFile;
		try {
			cleanedFile = cleanFile(fileReader);
		} catch(Exception e) {
			cleanedFile = null;
		}
		if(cleanedFile == null)
			System.exit(0);
		String palindromicSubsequence = longestPalindromicSubseqeuence(cleanedFile);
		// (4) Print out the longest palindromic non-contiguous subsequence and its length 
		System.out.println("Longest Palindromic Subsequence: " + palindromicSubsequence);
		System.out.println("The Length of Longest Palindromic Subsequence: " + palindromicSubsequence.length());
		// (5) Print out the longest palindromic contiguous subsequence and its length
		String palindromicSubstring = longestPalindromicSubstring(cleanedFile);
		System.out.println("Longest Palindromic Contiguous Subsequence: " + palindromicSubstring);
		System.out.println("The Length of the Longest Palindromic Contiguous Subsequence: " + palindromicSubstring.length());
		
	}

}
