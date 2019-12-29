import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author  Anastasia Kotova (azk0108@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 09/30/2019
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** 
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /** 
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    * 
    * @param c Collection of Points
    */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>();
      for (Point p : c) {
         add(p);
      }
   }
 
   /** 
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    * 
    * @param p Point object
    * @return boolean value
    */
   public boolean add(Point p) {
     // p is in line
      if (line.contains(p)) {
         return false;
      }
      // line is empty
      else if (line.isEmpty()) {
         line.add(p);
         return true;
      }
      // checks if p is collinear
      else {
         if (line.first().slopeTo(p) == line.last().slopeTo(p)) {
            line.add(p);
            return true;
         }
         return false;
      }
   }
   
   /** 
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    * 
    * @return first Point object in a set
    */
   public Point first() {
      if (line.isEmpty()) {
         return null;
      }
      return line.first();
   }
   
   /** 
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    * 
    * @return last Point object in a set
    */
   public Point last() {
      if (line.isEmpty()) {
         return null;
      }
      return line.last();
   }
   
   /** 
    * Returns the number of points in this line.
    * 
    * @return number of points in this line
    */
   public int length() {
      return line.size();
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. An empty line is less than any
    * non-empty line, and all empty lines are equal. All three properties of
    * compareTo as specified in the Comparable interface are met, and this
    * implementation is consistent with equals.
    * 
    * @param that Line object for comparison
    * @return some int value
    */
   @Override
   public int compareTo(Line that) {
   // this is empty
      if (length() == 0) {
         // that is empty
         if (that.length() == 0) {
            return 0;
         }
         return -1;
      }
      // that is empty
      else if (that.length() == 0) {
         return 1;
      }
      
      int ret = first().compareTo(that.first());
      if (ret == 0) {
         return last().compareTo(that.last());
      }
      return ret;
   }

   /** 
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    * 
    * @return iterator object
    */
   @Override
   public Iterator<Point> iterator() {
      return line.iterator();
   }
   
   /** 
    * Return true if this line's first and last points are equal to the
    * parameter's first and last points. Empty lines are equal to each other
    * but are not equal to any non-empty line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    * 
    * @param obj Object to check for equality
    * @return boolean value
    */
   @Override 
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first())) 
                     && (this.last().equals(that.last()));
   }
 
   /** 
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    * 
    * @return String concentration
    */
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }
 
}
