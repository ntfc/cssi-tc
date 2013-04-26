/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package otpcracker;

/**
 *
 * @author nc
 */
public class Shift {

  /**
   * Creates a new empty instance of <code>Shift</code>.
   */
  public Shift() {
  }

  public static int getPos(char ch) {
    return (int)ch - (int)'a';
  }
  public static char getChar(int i) {
    return (char)((int)'a' + i);
  }
  public static String enc(int key, String m) {
    if(key < 0 && key > 25)
      throw new IndexOutOfBoundsException("key between 0 and 25");
    m = m.toLowerCase();
    StringBuilder c = new StringBuilder();
    for(int i = 0; i < m.length(); i++) {
      char mi = m.charAt(i);

      c.append(getChar((getPos(mi) + key) % 26));
    }

    return c.toString().toUpperCase();
  }
  public static String dec(int key, String c) {
    if(key < 0 && key > 25)
      throw new IndexOutOfBoundsException("key between 0 and 25");
    c = c.toLowerCase();
    StringBuilder m = new StringBuilder();
    for(int i = 0; i < c.length(); i++) {
      char ci = c.charAt(i);
      // Modulo do java bate um pouco mal com negativos..
      int pos_ci = (getPos(ci) - key) % 26;
      if(pos_ci < 0) pos_ci += 26;
      m.append(getChar(pos_ci));
    }
    return m.toString().toLowerCase();
  }

}
