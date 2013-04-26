/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otpcracker;

/**
 *
 * @author nc
 */
public enum Language {
  English(0.0667, new double[]{
    0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.0228, 0.0215, 0.06094, 0.06966,
    0.0153, 0.0772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
    0.06327, 0.09056, 0.02758, 0.00978, 0.0236, 0.00150, 0.01974, 0.00074
  }),
  
  French(0.0746, new double[] {
    0.0813, 0.00903, 0.0315, 0.0427, 0.1722, 0.0114, 0.0109, 0.00796, 0.0744,
    0.00339, 0.00097, 0.0553, 0.0289, 0.0746, 0.0538, 0.03021, 0.00999, 0.0705,
    0.0804, 0.0699, 0.0565, 0.0130, 0.00039, 0.00435, 0.00271, 0.00098
  });
  private final double kappaPlaintext;
  private final double[] frequencies;

  Language(double kappaPlain, double[] frequencies) {
    this.kappaPlaintext = kappaPlain;
    this.frequencies = frequencies;
  }

  public double kappaPlaintext() {
    return kappaPlaintext;
  }

  public double[] frequencies() {
    return frequencies;
  }
}
