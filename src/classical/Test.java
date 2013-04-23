/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

import classical.cracker.AffineCrack;
import classical.cracker.CrackerUtils;
import classical.cracker.Frequencies;
import static classical.cracker.Frequencies.lettersFrequency;
import classical.cracker.Language;
import classical.cracker.SubstitutionCracker;
import classical.cracker.VigenereCrack;
import classical.cracker.VigenereCrack;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nc
 */
public class Test {

  public static void main(String[] args) throws Exception {

    String c = Texts.TEXT1.substring(0, 45);
    int[] f = (Frequencies.lettersFrequency(c));
    System.out.println(c.length());
    for(int i = 0; i < f.length; i++) {
      System.out.println(Shift.getChar(i)+": "+f[i]);
    }
    System.out.println("");
    for(int ii = 0; ii < CrackerUtils.repeatedLettersTwice(c).length; ii++) {
      String a = Shift.getChar(ii) + "" + Shift.getChar(ii);
      System.out.println(a + "="+CrackerUtils.repeatedLettersTwice(c)[ii]);
  }
    System.out.println(CrackerUtils.countNgram(c, 2));
    System.out.println(CrackerUtils.countNgram(c, 3));

    


    //System.out.println(Arrays.toString(AffineCrack.bruteForceChiSquared(Texts.TEXT3, Language.French)));
    //System.out.println(Affine.dec(19, 4, Texts.TEXT3));
  }

}
