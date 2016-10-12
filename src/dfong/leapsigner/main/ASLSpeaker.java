/**
 * 
 */
package dfong.leapsigner.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * @author dan
 *
 */
public class ASLSpeaker {

  private static final String VOICENAME_kevin = "kevin";
  private static final String VOICENAME_kevin16 = "kevin16";
  private static final String VOICENAME_alan = "alan";
  private final VoiceManager voiceManager = VoiceManager.getInstance();
  private final Voice voice;
  private final ExecutorService service = Executors.newCachedThreadPool();
  private volatile boolean isWorking = false;
  /**
   * 
   */
  public ASLSpeaker() {
    //voice = voiceManager.getVoice(VOICENAME_kevin);
    voice = voiceManager.getVoice(VOICENAME_kevin16);
    //voice = voiceManager.getVoice(VOICENAME_alan);
    voice.allocate();
  }

  public void speak(String text) {
    if (!isWorking) {
      isWorking = true;
      service.execute(new Runnable() {
        
        @Override
        public void run() {
          voice.speak(text);
          isWorking = false;
        }
      });
    }
  }
  
}
