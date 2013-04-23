/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;


/**
 *
 * @author nc
 */
public class Affine {

  public static final int A[] = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
  private static String abc = "abcdefghijklmnopqrstuvwxyz";

  public static int modInverse(int a) {
    int m = -1;
    switch(a) {
      case 1: m = 1; break;
      case 3: m = 9; break;
      case 5: m = 21; break;
      case 7: m = 15; break;
      case 9: m = 3; break;
      case 11: m = 19; break;
      case 15: m = 7; break;
      case 17: m = 23; break;
      case 19: m = 11; break;
      case 21: m = 5; break;
      case 23: m = 17; break;
      case 25: m = 25; break;
      default:
        System.err.println(a + " doesnt has inverse mod 26");
        break;
    }
    return m;
  }
  public static int mod(int n, int m) {
    return (n < 0) ? (m - (Math.abs(n) % m)) % m : (n % m);
  }
  public static String enc(int a, int b, String m) {
    if(gcd(a, 26) != 1) {
      System.err.println("Key a must be co-prime of 26");
      return null;
    }
    boolean p = false;
    for(int aa : A) {
      if(aa == a) p = true;
    }
    if(!p) {
      System.err.println("a not prime between 1 and 26");
      return null;
    }
    m = m.toLowerCase();
    StringBuilder c = new StringBuilder();
    for(int i = 0; i < m.length(); i++) {
      int chrIndex = abc.indexOf(m.charAt(i));
      char ci = abc.charAt(mod((chrIndex * a) + b, 26));
      c.append(ci);
    }
    return c.toString();
  }

  public static String dec(int a, int b, String c) {
    if(gcd(a, 26) != 1) {
      System.err.println("Key a must be co-prime of 26");
      return null;
    }
    boolean p = false;
    for(int aa : A) {
      if(aa == a) p = true;
    }
    if(!p) {
      System.err.println("a not prime between 1 and 26");
      return null;
    }
    c = c.toLowerCase();
    StringBuilder m = new StringBuilder();
    int aInv = modInverse(a);
    if(aInv < 0) {
      System.err.println("Mod inverse " + a + " error");
      return null;
    }
    for(int i = 0; i < c.length(); i++) {
      int chrIndex = abc.indexOf(c.charAt(i));
      char mi = abc.charAt(mod((chrIndex - b) * aInv, 26));
      m.append(mi);
    }
    return m.toString();
  }
  public static int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a%b);
  }
}
