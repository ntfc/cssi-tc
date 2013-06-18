/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otpcracker;

import otpcracker.OTP;

/**
 *
 * @author miltonnunes52
 */
public class Test {
    
    public static void main(String[] args) throws Exception {
        
        //1 desafio
        System.out.println("Teste qui quadrado");
        OTP.testChiSquaredStatistic();
        System.out.println("Teste indice de coincidencia");
        OTP.testIofC();
        
        //2 desafio
        //double[] valores = OTP.desvioCiphers();
         //for(int i = 0; i < valores.length; i++){
            //System.out.println(valores[i]);
        //} 
       
            
    }
    
}
