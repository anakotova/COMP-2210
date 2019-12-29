import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Anastasia Kotova (azk0108@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 09/30/2019
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename.
    * 
    * @param filename name of the input file 
    */
   public Extractor(String filename) {
      int x, y;
   
      try {
         Scanner scan = new Scanner(new File(filename));
      
         int length = scan.nextInt();
         points = new Point[length];
      
         for (int i = 0; scan.hasNext(); i++) {
            x = scan.nextInt();
            y = scan.nextInt();
            points[i] = new Point(x, y);
         }
      }
      catch (Exception e) {
         System.out.println("File not found");
      }
   
   
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    * 
    * @param pcoll Collection of points
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    * 
    * @return line segments
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Iterator itr = lines.iterator(); // checkstyle
      
      Point[] arr = Arrays.copyOf(points, points.length);
      Line currLine = new Line();
      
      for (int i = 3; i < arr.length; i++) {
         for (int j = 2; j < i; j++) {
            for (int k = 1; k < j; k++) {
               for (int q = 0; q < k; q++) {
                  currLine.add(arr[i]);
                  currLine.add(arr[j]);
                  currLine.add(arr[k]);
                  currLine.add(arr[q]);
                  if (currLine.length() == 4) {
                     lines.add(currLine);
                  }
                        
                  currLine = new Line();
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    * 
    * @return line segments
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      
      // copy points array
      Point[] copy = new Point[points.length];
      for (int i = 0; i < copy.length; i++) {
         copy[i] = points[i];
      } 
          
          
      for (int i = 0; i < points.length; i++) {
         // sort points with respect to the slope of points[i]
         Arrays.sort(copy, points[i].slopeOrder);
         
         int count = 0;
         
         // scan to find groups of points having the same slope
         for (int j = 0; j < points.length - 1; j = j + count) {
            count = 0;
            for (int k = 0; k + j < points.length 
                     && points[i].slopeOrder.compare(copy[j], 
                              copy[j + k]) == 0; k++) {
               count++;
            }
         
            // form a line segment of points[i] with 3 or more points
            if (count >= 3) {
               Line currLine = new Line();
               currLine.add(points[i]);
               for (int q = 0; q < count; q++) {
                  currLine.add(copy[j + q]);
               }
               
               lines.add(currLine);
            }
         }
      }
         
      return lines;
   }
   
}
