/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classical.cracker;

import classical.Affine;
import classical.MonoSubstitution;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author nc
 */
public class SubstitutionCracker {

  // isto nao serve para nada, acho eu....
  public static double fitness(String t, int n) {
    Map<String, Integer> nGrams = CrackerUtils.countNgram(t, n);
    int lenNgrams = nGrams.size();
    Map<String, Double> nGramsProb = new TreeMap<>();
    for(String gram : nGrams.keySet()) {
      // calculate probability
      double prob = (double)nGrams.get(gram) / (double)lenNgrams;
      nGramsProb.put(gram, prob);
    }
    double finalProbLog = 0.0;
    for(String gram : nGramsProb.keySet()) {
      double probLog = Math.log10(nGramsProb.get(gram));
      finalProbLog += probLog;
    }
    if(finalProbLog == 0.0)
      return -999.0;
    return (finalProbLog);
  }

}
