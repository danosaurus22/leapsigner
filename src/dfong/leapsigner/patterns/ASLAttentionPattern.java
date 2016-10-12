/**
 * ASLNamePattern.java
 * Created: Nov 24, 2014
 * @author: dan
 */
package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public class ASLAttentionPattern extends AbstractASLDoubleHandStaticPattern {

  
  private final HandPattern rp = new HandPattern();
  private final HandPattern lp = new HandPattern();
  /**
   * 
   */
  public ASLAttentionPattern() {
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.AbstractASLSingleHandStaticPattern#matches(dfong.leapsigner.patterns.core.HandWrapper)
   */
  @Override
  public boolean matches(HandWrapper leftHand, HandWrapper rightHand) {
    //load(leftHand, rightHand);
    return lp.matches(leftHand) && rp.matches(rightHand);
    
    //return indexDir.isLeftForward();
    //return (indexDir.isBackward() && !middleDir.isBackward() && !ringDir.isBackward());
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.AbstractASLSingleHandStaticPattern#translation()
   */
  @Override
  public String translation() {
    return "attention";
  }

  
  private static class HandPattern extends AbstractASLSingleHandStaticPattern {
    @Override
    public boolean matches(HandWrapper hand) {
      load(hand);
      
      double roll = hand.getRoll();
      if (hand.isLeft()) {
        boolean isPalmIn = (80.f <= roll && roll <= 100.f);
        return isPalmIn && indexDir.isUp() && middleDir.isUp() && ringDir.isUp();
      }
      if (hand.isRight()) {
        boolean isPalmIn = (-80.f >= roll && roll >= -100.f);
        return isPalmIn && indexDir.isUp() && middleDir.isUp() && ringDir.isUp();
      }
      
      
      return false;
    }
    @Override
    public String translation() {
      return null;
    }
  }
  
}
