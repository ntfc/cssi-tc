/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical.cracker;

/**
 *
 * @author nc
 */
public class MonoAlphCracker {

  public static String[] divideText(String c, int d) {
    StringBuilder s = new StringBuilder();
    String[] res = new String[c.length() / d + 1];
    int j = 0;
    for(int i = 0; i < c.length(); i += d) {
      if( (i+d) > c.length()) {
        s.append(c.substring(i, c.length()));
      }
      else {
        s.append(c.substring(i, i + d));
      }
      res[j++] = s.toString();
      s.delete(0, s.length());
    }
    return res;
  }

}
