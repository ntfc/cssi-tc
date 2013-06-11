/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otpcracker;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import static otpcracker.Shift.getChar;
import static otpcracker.Shift.getPos;
import static otpcracker.Texts.getText;
import static otpcracker.Texts.getArrayTexts;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.text.Position;
import sun.security.util.BigInt;

/**
 *
 * @author miltonnunes52
 */
public class OTP {

    /**
     * Creates a new empty instance of
     * <code>OTP</code>.
     */
    public OTP() {
    }

    public static String enc(String k, String m) {
        if (k.length() == m.length()) {
            m = m.toLowerCase();
            k = k.toLowerCase();
            String c = new String();
            for (int i = 0; i < m.length(); i++) {
                char mi = m.charAt(i);
                char ki = k.charAt(i);
                c += getChar((getPos(mi) + getPos(ki)) % 26);
            }

            return c.toUpperCase();
        }
        return null;
    }

    public static String dec(String k, String c) {
        if (k.length() == c.length()) {
            c = c.toLowerCase();
            k = k.toLowerCase();
            String m = new String();
            for (int i = 0; i < c.length(); i++) {
                char ci = c.charAt(i);
                char ki = k.charAt(i);
                int mod = ((getPos(ki) + 26) - getPos(ci)) % 26;
                //if(mod < 0) mod += 26;
                m += getChar(mod);
            }
            return m.toUpperCase();
        }
        return null;
    }

    public static void testXors() {
        double max = 0.0;
        for (String txt : Texts.TEXTOS) {
            int i = Texts.TEXTOS.indexOf(txt);
            // fazer xor com todos outros, excepto com ele proprio
            for (String txt2 : Texts.TEXTOS) {
                int j = Texts.TEXTOS.indexOf(txt2);
                if (txt2.compareTo(txt) != 0) { // fazer xor
                    //String xored = CrackerUtils.xorString(txt, txt2);
                    String xored = dec(txt, txt2);
                    double f = Frequencies.indexOfCoincidence(xored);
                    if (f > max) {
                        max = f;
                    }
                    System.out.print(i + " xor " + j + " = ");
                    //System.out.println(xored.toUpperCase());
                    System.out.println(f);
                    //System.out.println(txt+"+"+txt2+"="+xored);
                }
                //break; 
            }
        }
        System.out.println("Max: " + max);
    }

    public static void main(String[] args) throws Exception {

        /*
         System.out.println("Ponto da situação:");
         System.out.println("enc(qwe+key)"+enc("qwe","key"));
         System.out.println("enc(abc+key)"+enc("abc","key"));
         System.out.println("dec(qwe+abc)"+dec("qwe","abc"));
         System.out.println("dec(enc(qwe+key)+enc(abc+key))"+dec(enc("qwe","key"),enc("abc","key")));
         */
        //testXors();
        //test1();
        //testChiSquaredStatistic();
        //xorTextWithLetters(6,13);
        //testIofC();
       
        double[] res = desvioCiphers();
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        
        }
       
        

    }

    public static void test1() {
        String txt1 = "TheonetimepadencryptionschemeitselfismathematicallyunbreakableThereforetheattackerwillfocusonbreakingthekeyinsteadoftheciphertextThatswhyatrulyrandomkeyisessentialIfthekeyisgeneratedbyadeterministicalgorithmtheattackercouldfindamethodtopredicttheoutputofthekeygeneratorIfforinstanceacryptoalgorithmisusedtogeneratearandomkeythesecurityoftheonetimepadisloweredtothesecurityoftheusedalgorithmandisnolongermathematicallyunbreakableIfaonetimepadkeyeventrulyrandomisusedmorethanoncesimplecryptanalysiscanrecoverthekey";
        String txt2 = "WhenatrulyrandomkeyiscombinedwithaplaintexttheresultisatrulyrandomciphertextTofindkeyorplaintextanadversaryonlyhastherandomciphertextathisdisposalThisisanequationwithtwounknownswhichismathematicallyunsolvableAlsosinceeachkeydigitorletteristrulyrandomthereisnomathematicalorlogicalrelationwhatsoeverbetweentheindividualciphertextcharactersThemodulooneforonetimepaddigitsormodulotwoforonetimepadlettersalsoensuresthattheciphertextdoesnotrevealanyinformationaboutthetwounknowsintheequationIfallrulesofonearefollowed";
        String txtkey = "qyeycwkiuhjssxhcxosjddvefiomgwghpprhaxumzmhrlcrhyucoggduqabbaglhlhxhcbvvpedmaefdhdkmpoudpkfnkfyahtyvsekihxupvmqwmapsxixyoptqymgueekditqtfmezrrtudjbsxhxkulyddnlocuzaprjrjedckvtdcdqdanrnnnemlnnclgwkbzwtumwzpwayvfshahtycrtbahkrmoblvhcmhclwiaswtzngjakqvtoijmjwcnpfawrsdllksehcolsspnwucucsbfressbvzroooguunysameiybfaslrtwlqreqsnybzjoboodywzvrnytdiuwmnicokewvlkznqylpvbxxmoffzzyhdvuxchazxkpascvsbjqlkeqlkntwnxiqtixnwtjmdupqsvntqdgpaafyxchkotsrsxvxpvoteczlqcvsoiakarmnwqeyytshhjgecpahhmqamhkoexanijrqjnifpnersiwdjfjemmc";
        String txtkey2 = "jhhjwjgcbqfmekjmtlrmdecnjuwmqvlsisjikumxblruyzcbqywtsptldwznycvglumvglghoiwretqbwnxfmabfovtqhynagtcrxyqgmzxorgzntnwgxdusecrrgjlgdpiyarcqkqfltkorozdixvlejumbrvjulzjilpgtzerjyylzimvidvpkkhbdmfahsglqckupyhmrinaytmtwtznpunqijeaiaosiloidhmnojjwqicsuezmfupwvnnbkjiygeiufrqfnqdnahyitnhsoobfvmvivyhcqfgnkxprmcbaihebeboocksuemckmohwtkemclohmnzuqsvqxeoaaskthvwrbtpzlgzzfzfkmfxennnklfnsufwviukfswzkaglxjqmvfxkacfikiqyyfqaobsgvcmpbfsmacrnrilbzszjeqeemqiowwprqxyybbwdgkvknanfxxfaiwaxnouyjzxoqypqsshkwsqixwtxtqvtvslcxogwotbjss";
        String enc1 = enc(txt1, txtkey);
        String enc2 = enc(txt2, txtkey2);
        String dec = dec(enc1, enc2);
        System.out.println(enc1);
        System.out.println(enc2);
        System.out.println(dec);
        //System.out.println(dec(dec,dec));
        //System.out.println(Frequencies.indexOfCoincidence(enc(txt1,txt2)));
        //System.out.println(enc(txt1,txt2));




    }

    public static void test3() {
        double max = 0.0;
        double min = 521.0;
        ArrayList<String> list = new ArrayList<>();

        for (String txt : Texts.TEXTOS) {
            int i = Texts.TEXTOS.indexOf(txt);
            // fazer xor com todos outros, excepto com ele proprio
            for (String txt2 : Texts.TEXTOS) {
                int j = Texts.TEXTOS.indexOf(txt2);
                if (txt2.compareTo(txt) != 0) { // fazer xor
                    //String xored = CrackerUtils.xorString(txt, txt2);
                    String xored = dec(txt, txt2);
                    list.add(xored);
                }
                //break; 
            }
        }

        for (String txt : list) {
            int i = list.indexOf(txt);
            // fazer xor com todos outros, excepto com ele proprio
            for (String txt2 : list) {
                int j = list.indexOf(txt2);
                if (txt2.compareTo(txt) != 0) { // fazer xor
                    //String xored = CrackerUtils.xorString(txt, txt2);
                    String xored = dec(txt, txt2);
                    double f = Frequencies.indexOfCoincidence(xored);
                    if (f > max) {
                        max = f;
                    }
                    if (f < min) {
                        min = f;
                    }
                    System.out.print(i + " xor " + j + " = ");
                    //System.out.println(xored.toUpperCase());
                    System.out.println(f);
                    //System.out.println(txt+"+"+txt2+"="+xored);

                }
                //break; 
            }
        }

        System.out.println("Max: " + max);
        System.out.println("min: " + min);

    }

    //teste que testa o algoritmo normal
    public static void test() throws UnsupportedEncodingException {
        String c3 = "";
        double final0 = 0;
        double final1 = 0;

        for (int i = 0; i < 20; i++) {
            String c1 = getText(i);

            for (int j = i + 1; j < 20; j++) {

                String c2 = getText(j);


                //String b1 = CrackerUtils.getBinaryString(c1);
                //String b2 = CrackerUtils.getBinaryString(c2);
                //c3 = CrackerUtils.xorBinaryString(b1, b2);
                String b = enc(c1, c2);
                c3 = CrackerUtils.getBinaryString(b);

                //print count
                double result[] = new double[2];
                result = CrackerUtils.normalDistribution(c3);

                if (final0 < result[0]) {
                    final0 = result[0];
                    final1 = result[1];
                }
                System.out.println("mean: " + result[0] + "  d: " + result[1] + " p: " + result[0] / c3.length());

            }
        }
        double t = final0;
        System.out.println("Maior: mean: " + final0 + "  d: " + final1 + " p: " + t / c3.length());
        System.out.println("H0: m: " + c3.length() / 2 + "  md: " + Math.sqrt(c3.length()) / 2);
    }

    //teste para ver o numero de 1's e 0's de cada texto cifrado original
    public static void test2() throws UnsupportedEncodingException {
        for (int i = 0; i < 20; i++) {
            String c1 = CrackerUtils.getBinaryString(getText(i));

            int[] result = CrackerUtils.count01(c1);
            double t = result[0];
            System.out.println("0: " + result[0] + " 1: " + result[1] + " m0: " + t / c1.length());
        }

    }

    public static void testIofC() {
        double max = 0.0;
        for (int i = 0; i < 20; i++) {
            String c1 = getText(i);
            for (int j = i + 1; j < 20; j++) {
                String c2 = getText(j);
                String cp = dec(c1, c2);
                double f = Frequencies.indexOfCoincidence(cp);
                System.out.println("c" + i + " xor c" + j + " = " + f);
                if (f > max) {
                    max = f;
                }
            }
        }
        System.out.println("max: " + max);
    }

    public static void testChiSquaredStatistic() {
        double min = 10000000.0;
        for (int i = 0; i < 20; i++) {
            String c1 = getText(i);
            for (int j = i + 1; j < 20; j++) {
                String c2 = getText(j);
                String cp = dec(c1, c2);
                double f = Frequencies.chiSquaredStatistic(cp, Language.English);
                System.out.println("c" + i + " xor c" + j + " = " + f);
                if (f < min) {
                    min = f;
                }
            }
        }
        System.out.println("max: " + min);
    }

    //tentar substituir por the
    public static void xorTextsWithTrigrams() {
        for (int i = 0; i < 20; i++) {
            String c1 = getText(i);
            for (int j = i + 1; j < 20; j++) {
                String c2 = getText(j);
                String cp = dec(c1, c2);
                int ct = 0;
                for (int v = 0; v < cp.length(); v++) {
                    if (v + 3 < cp.length()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(cp.toCharArray(), v, 3);
                        String temp = sb.toString();
                        String r = dec(temp, "the");
                        if (Frequencies.freqEnglishTrigrams.containsKey(r.toLowerCase())) {
                            ct++;
                        }
                    }
                }
                System.out.println("c" + i + " xor c" + j + " = " + ct);
            }
        }

    }

    public static void xorTextWithLetters(int c, int c0) {
        String c1 = getText(c);
        String c2 = getText(c0);
        String cp = dec(c1, c2);
        System.out.println(cp);
        int ct = 0;
        for (int v = 0; v < cp.length(); v++) {
            if (v + 3 < cp.length()) {
                StringBuilder sb = new StringBuilder();
                sb.append(cp.toCharArray(), v, 3);
                String temp = sb.toString();
                String r = dec(temp, "the");
                if (Frequencies.freqEnglishTrigrams.containsKey(r.toLowerCase())) {
                    ct++;
                    System.out.println(r);
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(cp.toCharArray(), 0, v);
                    sb1.append(r);
                    sb1.append(cp.toCharArray(), v + 3, cp.length() - (v + 3));
                    cp = sb1.toString();
                }
            }
        }
        System.out.println(cp);

    }

    
    
    //código referente à teoria brilhante
    
    
    public static List<String> xoredCiphers() {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<20; i++){
            String c1 = getText(i);
            for(int j=i+1; j<20; j++){
                String c2 = getText(j);
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
