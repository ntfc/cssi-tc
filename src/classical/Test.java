/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

import classical.cracker.AffineCrack;
import classical.cracker.CrackerUtils;
import classical.cracker.Frequencies;
import classical.cracker.Language;
import classical.cracker.VigenereCrack;

/**
 *
 * @author nc
 */
public class Test {

  public static void main(String[] args) throws Exception {

    // texto 1

    
    // texto 2
    int lenKey2 = VigenereCrack.guessKeyLength(Texts.TEXT2, 3, 7, Language.English);
    String key2 = VigenereCrack.getKey(Texts.TEXT2, lenKey2, Language.English);
    
    // texto 3
    int[] key3 = AffineCrack.bruteForceChiSquared(Texts.TEXT3, Language.French);

    // texto 4
    int lenKey4 = VigenereCrack.guessKeyLength(Texts.TEXT4, 3, 7, Language.English);
    String key4 = VigenereCrack.getKey(Texts.TEXT4, lenKey4, Language.English);



    System.out.println("texto2 = " + Vigenere.dec(key2, Texts.TEXT2));
    System.out.println("texto3 = " + Affine.dec(key3[0], key3[1], Texts.TEXT3));
    System.out.println("texto4 = " + Vigenere.dec(key4, Texts.TEXT4));
  }

}
