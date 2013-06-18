/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

import classical.cracker.AffineCrack;
import classical.cracker.Frequencies;
import classical.cracker.Language;
import classical.cracker.VigenereCrack;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author nc
 */
public class Test {
  private static DecimalFormat decFor = new DecimalFormat("#.####");

  public static void main(String[] args) throws Exception {
    // texto 1
    System.out.println("IC texto1 = " + decFor.format(Frequencies.indexOfCoincidence(TEXTS.TEXT1)));


    // texto 2
    System.out.println("IC texto2 = " + decFor.format(Frequencies.indexOfCoincidence(TEXTS.TEXT2)));
    int lenKey2 = VigenereCrack.guessKeyLength(TEXTS.TEXT2, 4, 7, Language.English);
    String key2 = VigenereCrack.getKey(TEXTS.TEXT2, lenKey2, Language.English);

    // texto 3
    System.out.println("IC texto3 = " + decFor.format(Frequencies.indexOfCoincidence(TEXTS.TEXT3)));
    int[] key3 = AffineCrack.bruteForceChiSquared(TEXTS.TEXT3, Language.French);
    String tt = "frpvnautamycdinhhxrqmefniidamtalycdiqtjdunvyrqhruycdfdnqyxrycrphnamnamyctqyddatanhvdamtalrqmdqyrycdycqddsdyydqnvqrafjruycthvrpqhdtasrxdqvnhdsdyydqh";
    int[] test = AffineCrack.bruteForceChiSquared(tt, Language.English);
    System.out.println(Affine.dec(test[0], test[1], tt).toLowerCase());
    // texto 4
    System.out.println("IC texto4 = " + decFor.format(Frequencies.indexOfCoincidence(TEXTS.TEXT4)));
    int lenKey4 = VigenereCrack.guessKeyLength(TEXTS.TEXT4, 4, 7, Language.English);
    String key4 = VigenereCrack.getKey(TEXTS.TEXT4, lenKey4, Language.English);


    //System.out.println("texto1 = " + Affine.dec(key1[0], key1[1], TEXTS.TEXT1));
    System.out.println("chave2 = " + key2 + ", texto2 = " + Vigenere.dec(key2, TEXTS.TEXT2));
    System.out.println("chave3 = " + Arrays.toString(key3) + ", texto3 = " + Affine.dec(key3[0], key3[1], TEXTS.TEXT3));
    System.out.println("chave4 = " + key4 + ", texto4 = " + Vigenere.dec(key4, TEXTS.TEXT4));
  }
}
