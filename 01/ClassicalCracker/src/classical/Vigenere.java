/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

/**
 *
 * @author nc
 */
public class Vigenere {
  // TODO: attention to upper and lower case. Make this more modular
  public Vigenere() {
  }


  public static String enc(String key, String m) {
    StringBuilder c = new StringBuilder();
    int keySize = key.length();
    key = key.toLowerCase();
    m = m.toLowerCase(); // convert to lower case
    for(int i = 0; i < m.length(); i++) {
      char m_i = m.charAt(i);
      // position of m_i in the alphabet
      int pos_mi = Shift.getPos(m_i);

      // letter to use as shift key
      char k_i = key.charAt(i % keySize);
      
      int pos_ki = Shift.getPos(k_i);
      // get the encrypted letter
      char c_i = Shift.getChar((pos_mi + pos_ki) % 26);
      // append to string
      c.append(c_i);
    }
    return c.toString().toUpperCase();
  }

  public static String dec(String key, String c) {
    StringBuilder m = new StringBuilder();
    int keySize = key.length();
    c = c.toLowerCase(); // convert to lowercase
    key = key.toLowerCase();
    for(int i = 0; i < c.length(); i++) {
      char c_i = c.charAt(i);
      // position of encrypted letter in the alphabet
      int pos_ci = Shift.getPos(c_i);
      // letter to use as shift
      char k_i = key.charAt(i % keySize);

      int pos_ki = Shift.getPos(k_i);

      // nao existem unsigned no java, logo o modulo bate mal
      // TODO: corrigir isto
      int mod = (pos_ci - pos_ki) % 26;
      if(mod < 0) mod += 26;
      // get the encrypted letter
      char m_i = Shift.getChar(mod);

      m.append(m_i);
    }

    return m.toString().toUpperCase();
  }


}
