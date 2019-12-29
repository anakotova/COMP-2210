import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Anastasia Kotova (azk0108@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-11-06
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   private TreeSet<String> lexicon;
   
   /** 
    * Empty ladder.
    */
   List<String> EMPTY_LADDER = new ArrayList<>();


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      } catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
   

   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param str1 the first string
    * @param str2 the second string
    * @return the Hamming distance between str1 and str2 if they are the
    *     same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      char[] char1 = str1.toLowerCase().toCharArray();
      char[] char2 = str2.toLowerCase().toCharArray();
      int ret = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (char1[i] != char2[i]) {
            ret++;
         }
      }
      return ret;
   }
   

  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> ladder = new ArrayList<String>();
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      else if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      else if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
    
      Deque<Node> queue = new ArrayDeque<>();
      TreeSet<String> temp = new TreeSet<>();
    
      temp.add(start);
      queue.addLast(new Node(start, null));
      while (!queue.isEmpty()) {
       
         Node newnode = queue.removeFirst();
         String word = newnode.word;
          
         for (String neighbor: getNeighbors(word)) {
            if (!temp.contains(neighbor)) {
               temp.add(neighbor);
               queue.addLast(new Node(neighbor, newnode));
            }
            if (neighbor.equals(end)) {
               Node m = queue.removeLast();
               while (m != null) {
                  ladder.add(0, m.word);
                  m = m.pred;
               }
               return ladder;
            }
         }
      }      
      return EMPTY_LADDER;
   } 
   
       
   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      ArrayList<String> ret = new ArrayList<String>();
   
      for (String str: lexicon) {
         if (getHammingDistance(word, str) == 1) {
            ret.add(str);
         }
      }
      // empty ladder case
      if (ret.isEmpty()) {
         return EMPTY_LADDER; 
      }
      return ret;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
     
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (getHammingDistance(sequence.get(i),
                      sequence.get(i + 1)) != 1) {
            return false;
         }
         else if (!isWord(sequence.get(i)) 
                       || !isWord(sequence.get(i + 1))) {
            return false;
         }
      }
      return true;
   }  
   
 
   /*************************************************************************/
   /****************************NESTED CLASSES*******************************/
   /*************************************************************************/
   
       
   private class Node {
      String word;
      Node pred;
   
      public Node(String word, Node pred) {
         this.word = word;
         this.pred = pred;
      }
   }
}

