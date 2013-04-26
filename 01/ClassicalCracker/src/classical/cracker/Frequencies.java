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

  /*public static final Map<String, Double> freqEnglishBigrams;

  static {
    freqEnglishBigrams = new TreeMap<String, Double>();
    freqEnglishBigrams.put("th", 0.03882);
    freqEnglishBigrams.put("he", 0.03681);
    freqEnglishBigrams.put("in", 0.02283);
    freqEnglishBigrams.put("er", 0.02178);
    freqEnglishBigrams.put("an", 0.02140);
    freqEnglishBigrams.put("re", 0.01749);
    freqEnglishBigrams.put("nd", 0.01571);
    freqEnglishBigrams.put("on", 0.01418);
    freqEnglishBigrams.put("en", 0.01383);
    freqEnglishBigrams.put("at", 0.01335);
    freqEnglishBigrams.put("ou", 0.01285);
    freqEnglishBigrams.put("ed", 0.01275);
    freqEnglishBigrams.put("ha", 0.01274);
    freqEnglishBigrams.put("to", 0.01169);
    freqEnglishBigrams.put("or", 0.01151);
    freqEnglishBigrams.put("it", 0.01134);
    freqEnglishBigrams.put("is", 0.01109);
    freqEnglishBigrams.put("hi", 0.01092);
    freqEnglishBigrams.put("es", 0.01092);
    freqEnglishBigrams.put("ng", 0.01053);
  }
  ;

 public static final Map<String, Double> freqEnglishTrigrams;

  static {
    freqEnglishTrigrams = new TreeMap<String, Double>();
    freqEnglishTrigrams.put("the", 0.03508);
    freqEnglishTrigrams.put("and", 0.01593);
    freqEnglishTrigrams.put("ing", 0.01147);
    freqEnglishTrigrams.put("her", 0.00822);
    freqEnglishTrigrams.put("hat", 0.00650);
    freqEnglishTrigrams.put("his", 0.00596);
    freqEnglishTrigrams.put("tha", 0.00593);
    freqEnglishTrigrams.put("ere", 0.00560);
    freqEnglishTrigrams.put("for", 0.00555);
    freqEnglishTrigrams.put("ent", 0.00530);
    freqEnglishTrigrams.put("ion", 0.00506);
    freqEnglishTrigrams.put("ter", 0.00461);
    freqEnglishTrigrams.put("was", 0.00460);
    freqEnglishTrigrams.put("you", 0.00437);
    freqEnglishTrigrams.put("ith", 0.00431);
    freqEnglishTrigrams.put("ver", 0.00430);
    freqEnglishTrigrams.put("all", 0.00422);
    freqEnglishTrigrams.put("wit", 0.00397);
    freqEnglishTrigrams.put("thi", 0.00394);
    freqEnglishTrigrams.put("tio", 0.00378);
  }
  ;

  public static final List<String> engRepLetters;

  static {
    engRepLetters = new ArrayList<String>();
    engRepLetters.add("ss");
    engRepLetters.add("ee");
    engRepLetters.add("tt");
    engRepLetters.add("ff");
    engRepLetters.add("ll");
    engRepLetters.add("mm");
    engRepLetters.add("oo");
    engRepLetters.add("pp");
    engRepLetters.add("rr");
    engRepLetters.add("nn");
    engRepLetters.add("cc");
    engRepLetters.add("dd");
  }
  ;

   public static final List<String> freRepLetters;

  static {
    freRepLetters = new ArrayList<String>();
    freRepLetters.add("ss");
    freRepLetters.add("ee");
    freRepLetters.add("ll");
    freRepLetters.add("tt");
    freRepLetters.add("nn");
    freRepLetters.add("mm");
    freRepLetters.add("rr");
    freRepLetters.add("pp");
    freRepLetters.add("ff");
    freRepLetters.add("cc");
    freRepLetters.add("aa");
    freRepLetters.add("uu");
    freRepLetters.add("ii");
    freRepLetters.add("gg");
  }
  ;


  public static final Map<String, Double> freqFrenchBigrams;

  static {
    freqFrenchBigrams = new TreeMap<String, Double>();
    freqFrenchBigrams.put("es", 0.0291);
    freqFrenchBigrams.put("le", 0.0208);
    freqFrenchBigrams.put("de", 0.0202);
    freqFrenchBigrams.put("en", 0.0197);
    freqFrenchBigrams.put("on", 0.0170);
    freqFrenchBigrams.put("nt", 0.0169);
    freqFrenchBigrams.put("re", 0.0162);
    freqFrenchBigrams.put("an", 0.0128);
    freqFrenchBigrams.put("la", 0.0125);
    freqFrenchBigrams.put("er", 0.0121);
    freqFrenchBigrams.put("te", 0.0119);
    freqFrenchBigrams.put("el", 0.0115);
    freqFrenchBigrams.put("se", 0.0109);
    freqFrenchBigrams.put("ti", 0.0104);
    freqFrenchBigrams.put("ur", 0.0101);
    freqFrenchBigrams.put("et", 0.0096);
    freqFrenchBigrams.put("ne", 0.0096);
    freqFrenchBigrams.put("is", 0.0094);
    freqFrenchBigrams.put("ed", 0.0093);
    freqFrenchBigrams.put("ou", 0.0093);
  }
  ;


  public static final Map<String, Double> freqFrenchTrigrams;

  static {
    freqFrenchTrigrams = new TreeMap<String, Double>();
    freqFrenchTrigrams.put("ent", 0.0086);
    freqFrenchTrigrams.put("les", 0.0071);
    freqFrenchTrigrams.put("ion", 0.0056);
    freqFrenchTrigrams.put("des", 0.0054);
    freqFrenchTrigrams.put("ede", 0.0052);
    freqFrenchTrigrams.put("que", 0.0051);
    freqFrenchTrigrams.put("est", 0.0049);
    freqFrenchTrigrams.put("tio", 0.0042);
    freqFrenchTrigrams.put("ant", 0.0038);
    freqFrenchTrigrams.put("par", 0.0037);
    freqFrenchTrigrams.put("men", 0.0037);
    freqFrenchTrigrams.put("del", 0.0037);
    freqFrenchTrigrams.put("ela", 0.0037);
    freqFrenchTrigrams.put("sde", 0.0037);
    freqFrenchTrigrams.put("lle", 0.0036);
    freqFrenchTrigrams.put("our", 0.0035);
    freqFrenchTrigrams.put("res", 0.0032);
    freqFrenchTrigrams.put("son", 0.0031);
    freqFrenchTrigrams.put("tre", 0.0031);
    freqFrenchTrigrams.put("ont", 0.0031);
  }
  ;*/

}
