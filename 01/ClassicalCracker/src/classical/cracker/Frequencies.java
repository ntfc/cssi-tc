/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical.cracker;

import classical.Shift;
import java.util.Arrays;

/**
 *
 * @author nc
 */
public class Frequencies {

  public static String abc = "abcdefghijklmnopqrstuvwxyz";
  /**
   * Creates a new empty instance of <code>Frequencies</code>.
   */
  public Frequencies() {
  }

  /**
   * Count the frequency of each letter of the alphabet in a ciphertext
   * @param c
   * @return
   */
  public static int[] lettersFrequency(String c) {
    int[] t = new int[26];
    Arrays.fill(t, 0);
    c = c.toLowerCase();
    for(int i = 0; i < c.length(); i++) {
      char k = c.charAt(i);
      t[Shift.getPos(k)]++;
    }
    return t;
  }
  // https://en.wikipedia.org/wiki/Index_of_coincidence
  public static double indexOfCoincidence(String c) {
    int[] f = lettersFrequency(c);
    int n = c.length();
    int up = 0;
    for(int i = 0; i < f.length; i++) {
      up += (f[i] * (f[i] - 1));
    }
    int down = n * (n - 1);
    double Ic = (double)up/(double)down;
    return Ic;
  }

  // http://web.stonehill.edu/compsci//Shai_papers/RSA.pdf
  public static double indexOfCoincidence(String c, int k) {
    // To determine the index of coincidence of a particular piece of text, take the text, rotate it
    //by some random number of places, and write the rotated text underneath the original text.
    // The index of coincidence is the number of places in which the same letter occurs in both
    //strings of text
    // So, let's rotate c k positions and obtain the IC
    String rotR = Frequencies.rightRotateString(c, k);
    int repetitions = 0;
    // count the repetitions
    for(int i = 0; i < c.length(); i++) {
      if(c.charAt(i) == rotR.charAt(i))
        repetitions++;
    }
    return (double)repetitions / (double)c.length();
  }

  public static String rightRotateString(String a, int k) {
    char b[] = a.toCharArray();
    for(int i = 0; i < k; i++) {
      char last = b[b.length - 1];
      System.arraycopy(b, 0, b, 1, b.length - 1);
      b[0] = last;
    }
    return new String(b);
  }

  // calculate the chi squared statistic method
  // see formula here: http://practicalcryptography.com/cryptanalysis/text-characterisation/chi-squared-statistic/
  public static double chiSquaredStatistic(String c, Language lang) {
    int[] fi = Frequencies.lettersFrequency(c);
    double chi = 0.0;
    for(char ch  : abc.toCharArray()) {
      double ei = (double)c.length() * lang.frequencies()[abc.indexOf(ch)];
      double ci = fi[abc.indexOf(ch)];
      chi += Math.pow(ci - ei, 2.0) / (double)ei;
    }
    return chi;
  }

}
