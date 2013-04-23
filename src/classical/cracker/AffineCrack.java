/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical.cracker;

import classical.Affine;
import java.util.ArrayList;

/**
 *
 * @author nc
 */
public class AffineCrack {

  // return an array with [a, b]
  public static int[] bruteForceChiSquared(String c, Language l) {
    double smallestChi = -1.0;
    int[] key = new int[2];
    for(int a : Affine.A) {
      for(int b = 0; b < 26; b++) {
        String isM = Affine.dec(a, b, c);
        double chi = Frequencies.chiSquaredStatistic(isM, l);
        if(smallestChi == -1.0) {
          smallestChi = chi;
          key[0] = a; key[1] = b;
        }
        if(chi < smallestChi) {
          smallestChi = chi;
          key[0] = a; key[1] = b;
        }
      }
    }
    return key;
  }


}
