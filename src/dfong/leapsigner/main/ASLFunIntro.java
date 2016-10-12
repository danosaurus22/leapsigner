/**
 * FunIntro.java
 * Created: Dec 9, 2014
 * @author: dan
 */
package dfong.leapsigner.main;

/**
 * @author dan
 *
 */
public class ASLFunIntro {

  static String msg = getMsg();
  
  public static void main (String ... args) {
    ASLSpeaker speaker = new ASLSpeaker();
    speaker.speak(msg);
  }
  
  
  
  
  
  

  
  
  
  
  
  

  
  
  
  
  
  

  
  
  
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  private static String getMsg() {
    return       "Hello everyone. My name is Daniel Fong, and this is my "
        + "class project for embedded systems here at the University of California Davis. "
        + "I will be talking about a device that has the potential to translate American "
        + "Sign Language into speech using this text to speech synthesizer "
        + "I am using right now. And no. I did not write this synthesizer. It is just cool.";
    

  }
  
  
  
  
  
  
  
  
}
