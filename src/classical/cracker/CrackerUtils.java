/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical.cracker;

import classical.Shift;
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

  // return a map containing the number of occurences of each n-gram
  // minSize = minimum size of the substring to find
  // maxSize = maximum size of the substring to find
  public static Map<String, Integer> countNgram(String str, int n) {
    Map<String, Integer> freqRep = new TreeMap<>();
      for(int j = 0; j < (str.length() - n + 1); j++) {
        String toSearch = str.substring(j, j + n);
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

}
