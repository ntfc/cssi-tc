package otpcracker;


import java.util.ArrayList;
import java.util.List;
import otpcracker.Frequencies;
import otpcracker.Language;


/**
 *
 * @author miltonnunes52
 */

public class OTP {
  
  public OTP() {
    
  }

  public static String enc(String k, String m) {
    if(k.length() == m.length()) {
      m = m.toLowerCase();
      k = k.toLowerCase();
      String c = new String();
      for(int i = 0; i < m.length(); i++) {
        char mi = m.charAt(i);
        char ki = k.charAt(i);
        c += Shift.getChar((Shift.getPos(mi) + Shift.getPos(ki)) % 26);
      }

      return c.toUpperCase();
    }
    return null;
  }

  public static String dec(String k, String c) {
    if(k.length() == c.length()) {
      c = c.toLowerCase();
      k = k.toLowerCase();
      String m = new String();
      for(int i = 0; i < c.length(); i++) {
        char ci = c.charAt(i);
        char ki = k.charAt(i);
        int mod = ((Shift.getPos(ki)+26) - Shift.getPos(ci)) % 26;
        m += Shift.getChar(mod);
      }
      return m.toUpperCase();
    }
    return null;
  }
  public static void testIofC(){
    for(int i=0; i<20; i++){
        String c1 = TEXTS.getText(i);
          for(int j=i+1; j<20; j++){
            String c2 = TEXTS.getText(j);
            String cp = dec(c1,c2);
            double f = Frequencies.indexOfCoincidence(cp);
            System.out.println("c"+i+" xor c"+j+" = "+ f);
          }
      }
  }
  
  
  public static void testChiSquaredStatistic(){
    for(int i=0; i<20; i++){
        String c1 = TEXTS.getText(i);
          for(int j=i+1; j<20; j++){
            String c2 = TEXTS.getText(j);
            String cp = dec(c1,c2);
            double f = Frequencies.chiSquaredStatistic(cp, Language.English);
            System.out.println("c"+i+" xor c"+j+" = "+ f);
          }
      }
  }
  
  public static List<String> xoredCiphers() {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<20; i++){
            String c1 = TEXTS.getText(i);
            for(int j=i+1; j<20; j++){
                String c2 = TEXTS.getText(j);
                String cp = dec(c1,c2);
                list.add(cp);
            }
        }
        return list;
    }

    public static double[] mediaOcorrenciaLetras() {
        double[] media = new double[26];
        List<String> xorCiphers = xoredCiphers();
        int[] total;
        for(String cipher : xorCiphers){
            total = Frequencies.lettersFrequency(cipher);
            for(int i = 0; i<26; i++){
                media[i] += total[i];
            }
        }
        
        for(int i = 0; i<26; i++){
            media[i] = media[i]/xorCiphers.size();
        }
        return media;
    }
    
    public static double[] desvioCiphers(){
        List<String> xorCiphers = xoredCiphers();
        double[] desvio = new double[xorCiphers.size()];
        double[] media = mediaOcorrenciaLetras();
        int[] total;
        int j = 0;
        for(String cipher : xorCiphers){
            total = Frequencies.lettersFrequency(cipher);
            for(int i = 0; i<26; i++){
                if(media[i] > total[i]){
                    desvio[j] += media[i] - total[i];
                }
                else{
                    desvio[j] += total[i] - media[i];
                }
            }
            j++;
        }
        return desvio;
    }
  
  
}
