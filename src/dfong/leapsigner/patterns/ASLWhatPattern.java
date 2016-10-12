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
public class ASLWhatPattern extends AbstractASLDoubleHandStaticPattern {

  
  private final HandPattern rp = new HandPattern();
  private final HandPattern lp = new HandPattern();
  /**
   * 
   */
  public ASLWhatPattern() {
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
    return "what";
  }

  
  private static class HandPattern extends AbstractASLSingleHandStaticPattern {
    @Override
    public boolean matches(HandWrapper hand) {
      load(hand);
      double roll = hand.getRoll();
      return 
          (-180.f <= roll && roll <= -170.f) 
          || (170.f <= roll && roll <= 180.f)
          && indexDir.isForward(30.f)
          && middleDir.isForward(30.f)
          && ringDir.isForward(30.f);
    }
    @Override
    public String translation() {
      return null;
    }
  }
  
}
