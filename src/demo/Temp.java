package demo;

import java.util.ArrayList;
import java.util.List;

import com.leapmotion.leap.Hand;
import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Temp {


  private static final String VOICENAME_kevin = "kevin";
  private String text; // string to speech

  public Temp(String text) {
   this.text = text;
  }

  public void speak() {
   Voice voice;
   VoiceManager voiceManager = VoiceManager.getInstance();
   voice = voiceManager.getVoice(VOICENAME_kevin);
   voice.allocate();
   voice.speak(text);
  }
  
  
  public static void main (String ... args) {
    String text = "FreeTTS was written by the Sun Microsystems Laboratories "
        + "Speech Team and is based on CMU's Flite engine.";
    
    text = "Hi, my name is Daniel Fong, and this is my project.";
    
    Temp freeTTS = new Temp(text);
    //freeTTS.speak();
    
    
    System.out.println(454 + " mod " + 4 + " = " + (454 % 4));
    System.out.println(543 + " mod " + 4 + " = " + (543 % 4));
    System.out.println(777 + " mod " + 4 + " = " + (777 % 4));
    
    
    
//    for (int i = 0; i < 200; i++) {
//      System.out.println(i + ", " + (i%8));
//    }
    
    
  }
}
