/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package otpcracker;

import otpcracker.Texts;
import otpcracker.Shift;

/**
 *
 * @author nc
 */
public class VigenereCrack {

  /**
   * Creates a new empty instance of <code>VigenereCrack</code>.
   */
  public VigenereCrack() {
  }

  /**
   * Divide the ciphertext in t sub-texts
   * @param c
   * @param t
   * @return 
   */
  private static String[] divideCiphertext(String c, int t) {
    String[] res = new String[t];
    for(int j = 0; j < t; j++) {
      StringBuilder cj = new StringBuilder();
      for(int i = j; i < c.length(); i += t) {
        cj.append(c.charAt(i));
      }
      res[j] = cj.toString();
    }
    return res;
  }

  public static int guessKeyLength(String c, int minLen, int maxLen, Language lang) {
    if(minLen > maxLen) {
      // error
      return -1;
    }
    c = c.toLowerCase();

    double indexes[] = new double[maxLen - minLen + 1];
    for(int k = minLen; k <= maxLen; k++) {
      double ic = Frequencies.indexOfCoincidence(c, k);
      // save all IC's in an array
      indexes[k - minLen] = ic;
    }
    // determine which of the IC's calculated is closest to kappaPlaintext
    // to do that, we compute the squared difference (to remove possible negative values) and we choose the smallest
    // difference = (freqEnglish - IC_calculated)^2
    double smallest = -1.0;
    int keyLength = minLen;
    for(int i = 0; i < indexes.length; i++) {
      
      double diffSquared = Math.pow(lang.kappaPlaintext() - indexes[i], 2);
      if(smallest == -1.0) {
        smallest = diffSquared;
      }
      if(diffSquared < smallest) {
        smallest = diffSquared;
        keyLength = minLen + i;
      }
    }
    return keyLength;
  }

  public static int guessKeyLengthKasiski(String c) {
    // see http://cs.colgate.edu/~chris/FSemWeb/tools/kasiski.html
    return 0;
  }
  private static int bestShiftCipher(String d, Language lang) {
    double min = -1.0;
    int shift = 0;
    for(int i = 0; i < 26; i++) {
      String enc = Shift.enc(i, d);
      // calculate the differences
      int[] freqs = Frequencies.lettersFrequency(enc);
      double freq = 0.0;
      // freq = sum( (q_i - p_i)^2 )
      for(int j = 0; j < freqs.length; j++) {
        double freqAux = (double)freqs[j] / (double)enc.length();
        freq += Math.pow(freqAux - lang.frequencies()[j], 2);
      }
      
      if(min == -1.0) {
        min = freq;
      }
      if(freq < min) {
        min = freq;
        shift = i;
      }
    }
    return shift;
  }
  public static String getKey(String c, int t, Language lang) {
    c = c.toLowerCase();
    String d[] = divideCiphertext(c, t);
    StringBuilder key = new StringBuilder();

    for(int i = 0; i < d.length; i++) {
      // see which of the 26 shift ciphers is the best
      int shift = bestShiftCipher(d[i], lang);
      // found the best shift, the key is: (26 - shift) mod 26
      char k = Shift.getChar((26 - shift) % 26);
      key.append(k);
    }
    return key.toString();
  }

}
