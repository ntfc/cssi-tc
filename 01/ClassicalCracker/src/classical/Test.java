/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical;

import classical.cracker.AffineCrack;
import classical.cracker.CrackerUtils;
import classical.cracker.Frequencies;
import classical.cracker.Language;
import classical.cracker.MonoAlphCracker;
import classical.cracker.VigenereCrack;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author nc
 */
public class Test {
  private static DecimalFormat decFor = new DecimalFormat("#.####");

  public static void main(String[] args) throws Exception {
      /*int[] freq = Frequencies.lettersFrequency(Texts.TEXT1);
      for(int i = 0; i<freq.length;i++){
          double t = ((double) freq[i])/Texts.TEXT1.length();
          System.out.println(t);
      }*/
      
      String t = "EMTLOAUDETDNEUAWYAFHNAFEYKDPUMLWTYIEOXYAIPJEKQPKUTKMTOLIETINETAEKANIAAEYKZAEKXEEJEKAHYAXETOIDPKZENKAHIETIWYTKKTKTOLDAILKTOIUAITLEDAPWZUTFZEENDTYYAFUAZENXEOJNETYEOWEUPXEZTAETNFTLKNAAEITOIYEKXEJUEIUZEFZEENDTYYAFEUEKUZEAOEFZEENEIAEZEJNEAHFZEJZETMXEYHEJUMTKUEY";
      String orig = Texts.TEXT1;
      
      String nova = Texts.TEXT1;
      /*nova = sub('C', 'e', orig, nova);
      nova = sub('G', 't', orig, nova);
      nova = sub('S', 'a', orig, nova);
      nova = sub('K', 'k', orig, nova);
      nova = sub('Y', 'o', orig, nova);
      nova = sub('I', 'i', orig, nova);
      nova = sub('U', 'n', orig, nova);
      nova = sub('Z', 's', orig, nova);
      nova = sub('N', 'h', orig, nova);
      nova = sub('E', 'r', orig, nova);
      nova = sub('O', 'd', orig, nova);
      nova = sub('F', 'l', orig, nova);
      nova = sub('D', 'c', orig, nova);
      nova = sub('X', 'u', orig, nova);
      nova = sub('L', 'm', orig, nova);
      nova = sub('J', 'w', orig, nova);
      nova = sub('P', 'f', orig, nova);
      nova = sub('W', 'g', orig, nova);
      nova = sub('M', 'y', orig, nova);
      nova = sub('H', 'p', orig, nova);
      nova = sub('A', 'j', orig, nova);
      nova = sub('Q', 'b', orig, nova);
      nova = sub('V', 'v', orig, nova);
      nova = sub('T', 'x', orig, nova);
      nova = sub('R', 'q', orig, nova);
      nova = sub('B', 'z', orig, nova);*/

      /*nova = sub('O', 'd', orig, nova);
      nova = sub('S', 'a', orig, nova);
      nova = sub('U', 'n', orig, nova);
      nova = sub('D', 'c', orig, nova);
      nova = sub('C', 'e', orig, nova);
      nova = sub('W', 'g', orig, nova);
      nova = sub('Y', 'o', orig, nova);
      nova = sub('S', 'a', orig, nova);
      nova = sub('F', 'l', orig, nova);
      
      nova = sub('G', 't', orig, nova);
      nova = sub('A', 'h', orig, nova);*/
      
      nova = sub('U', 't', orig, nova);
      nova = sub('Z', 'h', orig, nova);
      nova = sub('C', 'e', orig, nova);
      
      nova = sub('G', 'a', orig, nova);
      nova = sub('O', 'n', orig, nova);
      nova = sub('I', 'd', orig, nova);
      
      nova = sub('S', 'o', orig, nova);
      
      nova = sub('K', 's', orig, nova);
      
      nova = sub('Y', 'r', orig, nova);
      
      nova = sub('W', 'g', orig, nova);
      
      nova = sub('N', 'l', orig, nova);
      
      nova = sub('F', 'w', orig, nova);
      
      nova = sub('H', 'f', orig, nova);
      
      nova = sub('E', 'i', orig, nova);
      
      nova = sub('D', 'b', orig, nova);
      
      nova = sub('M', 'm', orig, nova);
      nova = sub('L', 'y', orig, nova);
      
      
      nova = sub('P', 'u', orig, nova); 
      nova = sub('X', 'p', orig, nova); 
      nova = sub('J', 'c', orig, nova);
      nova = sub('A', 'v', orig, nova);
      nova = sub('Q', 'j', orig, nova);
      
      
      System.out.println(nova);
      
     
     
  }

  public static void groupOne() {
    // texto 1
    System.out.println("IC texto1 = " + decFor.format(Frequencies.indexOfCoincidence(Texts.TEXT1)));
    // viginere?
    //int lenKey1 = VigenereCrack.guessKeyLength(Texts.TEXT1, 3, 7, Language.English);
    //String key1 = VigenereCrack.getKey(Texts.TEXT1, lenKey1, Language.English);
    // affine?
    //int[] key1 = AffineCrack.bruteForceChiSquared(Texts.TEXT1, Language.English);
    // monoalphabetic subs?
    // create distribution table
    /*int[] freqs1 = Frequencies.lettersFrequency(Texts.TEXT1);
    DecimalFormat decForm = new DecimalFormat("##.#");
    for(int i = 0; i < freqs1.length; i++) {
      double prob = (double)freqs1[i]/Texts.TEXT1.length() * 100.0;

      System.out.print("("+Shift.getChar(i) + ", " + freqs1[i] +", " + Double.valueOf(decForm.format(prob)) +  "%), ");
    }
    System.out.println("");
    // divide ciphertext in groups
    // TODO: the size of group is the size of the word used as keyword
    String[] div = MonoAlphCracker.divideText(Texts.TEXT1, 5);
    System.out.println(Arrays.toString(div));
    // count trigraphs
    Map<String, Integer> tri1 = CrackerUtils.countNgram(Texts.TEXT1, 3);
    System.out.println(tri1);
    
    String key1 = "v.ebiwafdcsymlnuj.o.t.gpr.";
    System.out.println(MonoSubstitution.dec(key1, Texts.TEXT1));*/

    // texto 2
    System.out.println("IC texto2 = " + decFor.format(Frequencies.indexOfCoincidence(Texts.TEXT2)));
    int lenKey2 = VigenereCrack.guessKeyLength(Texts.TEXT2, 4, 7, Language.English);
    String key2 = VigenereCrack.getKey(Texts.TEXT2, lenKey2, Language.English);
    
    // texto 3
    System.out.println("IC texto3 = " + decFor.format(Frequencies.indexOfCoincidence(Texts.TEXT3)));
    int[] key3 = AffineCrack.bruteForceChiSquared(Texts.TEXT3, Language.French);

    // texto 4
    System.out.println("IC texto4 = " + decFor.format(Frequencies.indexOfCoincidence(Texts.TEXT4)));
    int lenKey4 = VigenereCrack.guessKeyLength(Texts.TEXT4, 4, 7, Language.English);
    String key4 = VigenereCrack.getKey(Texts.TEXT4, lenKey4, Language.English);


    //System.out.println("texto1 = " + Affine.dec(key1[0], key1[1], Texts.TEXT1));
    System.out.println("chave2 = " + key2 + ", texto2 = " + Vigenere.dec(key2, Texts.TEXT2));
    System.out.println("chave3 = " + Arrays.toString(key3) + ", texto3 = " + Affine.dec(key3[0], key3[1], Texts.TEXT3));
    System.out.println("chave4 = " + key4 + ", texto4 = " + Vigenere.dec(key4, Texts.TEXT4));
  }
  
  public static String sub(char a, char b, String orig, String nova){
      char[] t = nova.toCharArray();
      for(int i = 0; i<orig.length(); i++){
          if(orig.charAt(i) == a && nova.charAt(i) == a){
              t[i] = b;       
          }
      }
      return new String(t);
  }
  
}
