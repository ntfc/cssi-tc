/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

/**
 *
 * @author nc
 */
public class MonoSubstitution {

  /**
   * Creates a new empty instance of <code>MonoSubstitution</code>.
   */
  public MonoSubstitution() {
  }

  public static String enc(String key, String m) {
    // TODO: falta verificar que a chave nao tem caracteres repetidos
    if(key.length() == 26) {
      m = m.toLowerCase();
      key = key.toLowerCase();
      StringBuilder c = new StringBuilder();
      for(int i = 0; i < m.length(); i++) {
        // get index of the m_i
        int pos_mi = Shift.getPos(m.charAt(i));
        // do the substitution
        char c_i = key.charAt(pos_mi);

        c.append(c_i);
      }
      return c.toString();
    }
    return null;
  }

  public static String dec(String key, String c) {
    // TODO: falta verificar que a chave nao tem caracteres repetidos
    if(key.length() == 26) {
      c = c.toLowerCase();
      key = key.toLowerCase();
      StringBuilder m = new StringBuilder();
      for(int i = 0; i < c.length(); i++) {
        // get the indexOf c_i
        int pos_ci = key.indexOf(c.charAt(i));
        // invert the substitution
        char m_i = Shift.getChar(pos_ci);
        m.append(m_i);
      }
      return m.toString();
    }
    return null;
  }

}
