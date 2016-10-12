/**
 * ASLPatternMatcher.java
 * Nov 9, 2014
 * ASLPatternMatcher
 * author: dan
 */
package dfong.leapsigner.patterns.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.EvictingQueue;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;

import de.ruzman.leap.event.LeapEvent;
import de.ruzman.leap.event.LeapListener;
import dfong.leapsigner.main.ASLSpeaker;
import dfong.leapsigner.patterns.ASLAlphabetPattern;
import dfong.leapsigner.patterns.ASLAttentionPattern;
import dfong.leapsigner.patterns.ASLDoubleHandStaticPattern;
import dfong.leapsigner.patterns.ASLHelloPattern;
import dfong.leapsigner.patterns.ASLMePattern;
import dfong.leapsigner.patterns.ASLNamePattern;
import dfong.leapsigner.patterns.ASLSingleHandStaticPattern;
import dfong.leapsigner.patterns.ASLThankYouPattern;
import dfong.leapsigner.patterns.ASLWhatPattern;
import dfong.leapsigner.patterns.ASLYouPattern;

/**
 * @author dan
 *
 */
public class ASLPatternMatcher implements LeapListener {
  
  
  private static final int PATTERN_CHECK_INTERVAL_MILLIS = 500;
  
  private static final int BUFFER_SIZE = 100;
  public static final ASLSpeaker SPEAKER = new ASLSpeaker();
  
  //Timer.scheduleAtFixedRate(TimerTask task, long delay, long period)
  //private final Timer timer;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  
  private final EvictingQueue<Frame> frames = EvictingQueue.create(BUFFER_SIZE);
//  private final EvictingQueue<Hand> rightHands = EvictingQueue.create(BUFFER_SIZE);
//  private final EvictingQueue<Hand> leftHands = EvictingQueue.create(BUFFER_SIZE);

  private final List<ASLSingleHandStaticPattern> singleStaticPattern = Arrays.asList(
      new ASLYouPattern(),
      new ASLMePattern(true),
      new ASLAlphabetPattern(),
      new ASLHelloPattern(false),
      new ASLThankYouPattern(false));
  private final List<ASLDoubleHandStaticPattern> doubleStaticPattern = Arrays.asList(
      new ASLNamePattern(),
      new ASLWhatPattern(),
      new ASLAttentionPattern()
      );
  
  /**
   * A pattern matcher.
   */
  public ASLPatternMatcher() {
    scheduler.scheduleWithFixedDelay(new Runnable() {
      
      @Override
      public void run() {
        try {
          ASLPatternMatcher.this.checkPattern();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, PATTERN_CHECK_INTERVAL_MILLIS, PATTERN_CHECK_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
  }
  
  @Override
  public void update(LeapEvent event) {
    
    try {
      Frame frame = event.getFrame();
      frames.add(frame);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Override
  public void statusChanged(LeapEvent event) {
    // TODO Auto-generated method stub
    System.err.println("statusChanged.event: " + event);
    
  }

  
  private void checkPattern() {
    System.out.println("CHECK! " + System.currentTimeMillis());
    //System.err.println("f  " + frames.toArray().getClass());

    int size = frames.size();
    if (size == 0) {
      System.out.println("nothing to compare");
      return;
    }
    
    Object[] framesArray = (Object[]) frames.toArray();
    Frame lastFrame = (Frame)framesArray[framesArray.length-1];
    if (!lastFrame.isValid()) {
      System.out.println("invalid frame");
      return;
    }
    
    long timestamp = lastFrame.timestamp();
    System.out.println("checking frame of " + timestamp);
    HandList hands = lastFrame.hands();
    if (hands.count() == 1) {
      Hand hand = hands.get(0);
      if (hand != null && hand.isValid()) {
        
        HandWrapper handWrapper = new HandWrapper(hand);
        
        for (ASLSingleHandStaticPattern pattern : singleStaticPattern) {
          if (pattern.matches(handWrapper)) {
            SPEAKER.speak(pattern.translation());
            break;
          }
        }
      }
    }
    
    else if (hands.count() == 2) {
      
      Hand rightHand = null;
      Hand leftHand = null;

      Iterator<Hand> iter = hands.iterator();
      Hand hand1 = iter.next();
      Hand hand2 = iter.next();
      boolean isValidHands = hand1 != null && hand1.isValid() && hand2 != null && hand2.isValid();
      
      if (isValidHands) {
        if (hand1.isRight()) {
          rightHand = hand1;
          leftHand = hand2;
        }
        else {
          rightHand = hand2;
          leftHand = hand1;
        }
        
        HandWrapper rightHandWrapper = new HandWrapper(rightHand);
        HandWrapper leftHandWrapper = new HandWrapper(leftHand);
        
        for (ASLDoubleHandStaticPattern pattern : doubleStaticPattern) {
          if (pattern.matches(leftHandWrapper, rightHandWrapper)) {
            SPEAKER.speak(pattern.translation());
            break;
          }
        }
      }
      
      
      
      
      
    }
    
  }
  

  
  
}
