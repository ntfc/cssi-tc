/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otpcracker;

/**
 *
 * @author miltonnunes52
 */

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class img {

    /**
     * Decode string to image
     * @param imageString The string to decode
     * @return decoded image
     */
    public static void main (String args[]) throws IOException {
      String s = "qyeycwkiuhjssxhcxosjddvefiomgwghpprhaxumzmhrlcrhyucoggduqabbaglhlhxhcbvvpedmaefdhdkmpoudpkfnkfyahtyvsekihxupvmqwmapsxixyoptqymgueekditqtfmezrrtudjbsxhxkulyddnlocuzaprjrjedckvtdcdqdanrnnnemlnnclgwkbzwtumwzpwayvfshahtycrtbahkrmoblvhcmhclwiaswtzngjakqvtoijmjwcnpfawrsdllksehcolsspnwucucsbfressbvzroooguunysameiybfaslrtwlqreqsnybzjoboodywzvrnytdiuwmnicokewvlkznqylpvbxxmoffzzyhdvuxchazxkpascvsbjqlkeqlkntwnxiqtixnwtjmdupqsvntqdgpaafyxchkotsrsxvxpvoteczlqcvsoiakarmnwqeyytshhjgecpahhmqamhkoexanijrqjnifpnersiwdjfjemmc";
      byte[]  montar = s.getBytes("US-ASCII");  //monta o array de bytes  
      ImageIcon icon = new ImageIcon(montar);   
    

      Image image = icon.getImage();  
      RenderedImage rendered = null;  
      if (image instanceof RenderedImage)  
      {  
          rendered = (RenderedImage)image;  
      }  
      else  
      {  
          BufferedImage buffered = new BufferedImage(  
              icon.getIconWidth(),  
              icon.getIconHeight(),  
              BufferedImage.TYPE_INT_RGB  
          );  
          Graphics2D g = buffered.createGraphics();  
          g.drawImage(image, 0, 0, null);  
          g.dispose();  
          rendered = buffered;  
      }  
      ImageIO.write(rendered, "JPEG", new File("/Users/miltonnunes52/Dropbox/TP/asdimage.jpg"));  
    }
}