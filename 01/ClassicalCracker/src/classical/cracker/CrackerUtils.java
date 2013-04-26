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

  public static Map<Character, Map<String, Integer>> trigraphTable(String c) {
    c = c.toLowerCase();
    Map<Character, Map<String, Integer>> res = new TreeMap<>();

    for(int i = 0; i < c.length(); i++) {
      char ch = c.charAt(i);
      char prev = (i == 0) ? '.' : c.charAt(i-1);
      char next = (i == c.length()-1) ? '.' : c.charAt(i+1);
      String str = new StringBuilder().append(prev)/*.append(ch)*/.append(next).toString();
      // already contains entry for ch
      if(res.containsKey(ch)) {
        Map<String, Integer> count = res.get(ch);
        if(count.containsKey(str)) {
          count.put(str, count.get(str) + 1);
        }
        else {
          count.put(str, 1);
        }
      }
      else {
        TreeMap<String, Integer> count = new TreeMap<>();
        res.put(ch, count);
        count.put(str, 1);
      }
    }
    for(char k : res.keySet())
      System.out.println(k + "=" + res.get(k));
    return res;
  }

  public static Map<Character, Map<String, Integer>> topLettersTrigraphTable(String c, char[] top) {
    c = c.toLowerCase();
    Map<Character, Map<String, Integer>> table = trigraphTable(c);
    Map<Character, Map<String, Integer>> res = new TreeMap<>();

    System.out.println("top");
    for(char k : top) {
      System.out.println(k + "=" + table.get(k));
      System.out.println(Arrays.toString(digraphsCount(k, table.get(k))));
      // sum array
      System.out.print("sum=");
      int sum = 0;
      for(int i : digraphsCount(k, table.get(k)))
        sum += i;
      System.out.println(sum + "");
      res.put(k, table.get(k));
    }

    return res;
  }

  // return table like pag50 (for one letter only!)
  public static int[] digraphsCount(char k, Map<String, Integer> v) {
    int[] res = new int[26];
    Arrays.fill(res, 0);
    for(String s : v.keySet()) {
      int i = Shift.getPos(s.charAt(0));
      res[i] += v.get(s);
    }
    return res;
  }


}
