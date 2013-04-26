/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package otpcracker;

import java.io.UnsupportedEncodingException;
import otpcracker.Shift;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nc
 */
public class CrackerUtils {

  public static Map<String, Integer> repetitions(String str) {
    return repetitions(str, 3, 10);
  }
  // minSize = minimum size of the substring to find
  // maxSize = maximum size of the substring to find
  public static Map<String, Integer> repetitions(String str, int minSize, int maxSize) {
    Map<String, Integer> freqRep = new TreeMap<>();
    for(int i = minSize; i <= maxSize; i++) {
      for(int j = 0; j < (str.length() - i); j++) {
        String toSearch = str.substring(j, i+j);
        if(!toSearch.isEmpty()) {
          // find occurences of toSearch in str
          Pattern p = Pattern.compile(toSearch);
          Matcher m = p.matcher(str);
          int count = 0;
          while(m.find())
            count++;
          if(count > 1) {
            freqRep.put(toSearch, count);
          }
        }
      }
    }
    return freqRep;
  }

  public static int[] repeatedLettersTwice(String c) {
    int[] reps = new int[26];
    c = c.toLowerCase();
    Arrays.fill(reps, 0);
    for(int i = 0; i < c.length() - 1; i++) {
      if(c.charAt(i) == c.charAt(i+1))
        reps[Shift.getPos(c.charAt(i))]++;
    }

    return reps;
  }
  
  //transdorma a string em binario
  public static String getBinaryString(String c) throws UnsupportedEncodingException{
    String s = "";
    byte[] b = c.getBytes();
    for(int i = 0; i<b.length; i++){
      s = s.concat(Integer.toBinaryString(b[i]));
    }
    return s;
  }
  
  //faz o xor de duas strings já em binario
  public static String xorBinaryString(String c1, String c2){
    String xor = "";
    
    for(int i = 0; i<c1.length(); i++){
      xor = xor.concat(xorChar2(c1.charAt(i), c2.charAt(i)));
    }
    
    return xor;
  }
 
  //faz o xor de 2 bits
  public static String xorChar2(char c1, char c2){
    String x;
   
    if(c1 == c2){ x = "0"; }
    else{ x = "1";}
    
    return x;
  }

  public static char xorChar(char c1, char c2) {
    // converter para lowercase
    if(c1 >= 'A' && c1 <= 'Z')
      c1 = (char) (c1 + ('a' - 'A'));
    if(c2 >= 'A' && c2 <= 'Z')
      c2 = (char) (c2 + ('a' - 'A'));

    int xor = (Shift.getPos(c1) + Shift.getPos(c2)) % 26;

    return Shift.getChar(xor);
  }
  // confirmar que ta bem aqui: http://www.braingle.com/brainteasers/codes/onetimepad.php
  public static String xorString(String s1, String s2) {
    if(s1.length() != s2.length())
      return null;
    StringBuilder s = new StringBuilder();
    for(int i = 0; i < s1.length(); i++) {
      s.append(xorChar(s1.charAt(i), s2.charAt(i)));
    }
    return s.toString();
  }
  
  
  //conta o numero de 0's e 1's de um texto cifrado
  public static int[] count01(String s){
    int[] total = new int[2];
    total[0] = 0;
    total[1] = 0;
    for(int j = 0; j<s.length(); j++){
      if(s.charAt(j) == '0'){
        total[0]++;
      }
      else{ 
        total[1]++;
      }
    }
    
    return total;
  }
  
  
  //calcula a distribuição normal depois de efectuado o xor de 2 textos cifrados
  public static double[] normalDistribution(String c3){
    double[] nd = new double[2];
    double n;
    double p;
    
    n = c3.length();
    int[] result = count01(c3);
    p = (result[0])/n;
    
    //mean value
    nd[0] = n*p;
    
    //standard deviation
    nd[1] = Math.sqrt(n*p*(1-p));
    
    return nd;
  }
}