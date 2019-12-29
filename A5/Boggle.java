import java.util.List;
import java.util.ArrayList;
// import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;

/**
 * Boggle.java - implements WordSearchGame.java.
 *
 * @author Anastasia Kotova (azk0108@auburn.edu)
 * @version 2019-10-27
 * 
 */
public class Boggle implements WordSearchGame {
   
   private TreeSet<String> lexicon;
   private boolean lexiconCalled;
   private List<Integer> path;
   private List<Integer> takenPath;
   private int length;
   private String[][] grid;
   private boolean[][] visited;
   private SortedSet<String> words;
   private int minLength;
   
   public Boggle() {
      lexicon = new TreeSet<String>();
      lexiconCalled = false;
      path = new ArrayList<Integer>();
      takenPath = new ArrayList<Integer>();
      words = new TreeSet<String>();
   }
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("no file name");
      }
      Scanner scanFile;
      Scanner scanLine;
      String line;
      try {
         scanFile = new Scanner(new File(fileName));
         while (scanFile.hasNext()) { // scan file
            line = scanFile.nextLine();
            scanLine = new Scanner(line).useDelimiter(" ");
            while (scanLine.hasNext()) { // scan line
               lexicon.add(scanLine.next().toLowerCase());
            }
         
         }
      } 
      catch (Exception e) {
         throw new IllegalArgumentException("file not found");
      }
      
      lexiconCalled = true;
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException("no letter");
      }
      
      length = (int) Math.sqrt(letterArray.length);
   
      if (Math.pow(length, 2) != letterArray.length) {
         throw new IllegalArgumentException("not square");
      }
      
      else {
         grid = new String[length][length];
         visited = new boolean[length][length];
         int count = 0;
         for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
               visited[i][j] = false;
               grid[i][j] = letterArray[count].toLowerCase();
               count++;
            }
         }
      }
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    * 
    * @return board as a String
    */
   public String getBoard() {
      String ret = "";
      for (int i = 0; i < length; i++) {
         if (i > 0) {
            ret += "\n";
         }
         for (int j = 0; j < length; j++) {
            ret += grid[i][j] + " ";
         }
      }
      return ret;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (!lexiconCalled) {
         throw new IllegalStateException("lexicon is not loaded");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("invalid word length");
      }
      
      minLength = minimumWordLength;
      // clear cache
      words.clear();
      
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            location(grid[i][j], i, j);
         }
      }
      return words;
   
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (!lexiconCalled) {
         throw new IllegalStateException("lexicon is not loaded");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("invalid word length");
      }
      
      int score = 0;
      for (String str : lexicon) {
         score += (str.length() - minimumWordLength) + 1;
      }
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (!lexiconCalled) {
         throw new IllegalStateException("lexicon is not loaded");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("no word");
      }
      wordToCheck = wordToCheck.toLowerCase();
      return lexicon.contains(wordToCheck);
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (!lexiconCalled) {
         throw new IllegalStateException("lexicon is not loaded");
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("no prefix");
      }
      prefixToCheck = prefixToCheck.toLowerCase();
      
      String curr = lexicon.ceiling(prefixToCheck);
      return curr != null 
               ? curr.startsWith(prefixToCheck) : false;
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (!lexiconCalled) {
         throw new IllegalStateException("lexicon not loaded");
      }
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException("no word");
      }
      
      // clear cache
      path.clear();
      takenPath.clear();
   
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            if (Character.toUpperCase(grid[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int val = j + (i * length);
               path.add(val);
               backtracking(wordToCheck, grid[i][j], i, j);
               if (!takenPath.isEmpty()) {
                  return takenPath;
               }
               // clear cache
               path.clear();
               takenPath.clear();
            }
         }
      }
      return path;
   }
   
   /*************************************************************************/
   /****************************SUPPORT METHODS******************************/
   /*************************************************************************/

   private void location(String word, int n, int m) {
      if (!isValidPrefix(word)) {
         return;
      }
   
      visited[n][m] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         words.add(word.toLowerCase());
      }
   
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((n + i) <= ((int) length - 1)
               && (m + j) <= ((int) length - 1)
               && (n + i) >= 0 && (m + j) >= 0 && !visited[n + i][m + j]) {
               visited[n + i][m + j] = true;
               location(word + grid[n + i][m + j], n + i, m + j);
               visited[n + i][m + j] = false;
            }
         }
      }
      visited[n][m] = false;
   }
   
   private void backtracking(String word, String curr, int n, int m) {
   
      visited[n][m] = true;
      if (!(isValidPrefix(curr))) {
         return;
      }
      if (curr.toUpperCase().equals(word.toUpperCase())) {
         takenPath = new ArrayList<Integer>(path);
         return;
      }
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (curr.equals(word)) {
               return;
            }
            if ((n + i) <= (length - 1)
               && (m + j) <= (length - 1)
               && (n + i) >= 0 && (m + j) >= 0 && !visited[n + i][m + j]) {
               visited[n + i][m + j] = true;
               int val = (n + i) * length + m + j;
               path.add(val);
               backtracking(word, curr + grid[n + i][m + j], n + i, m + j);
               visited[n + i][m + j] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      visited[n][m] = false;
      return;
   }

}
