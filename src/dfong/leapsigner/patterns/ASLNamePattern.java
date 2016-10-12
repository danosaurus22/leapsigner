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
public class ASLNamePattern extends AbstractASLDoubleHandStaticPattern {

  
  private final RightHandPattern rp = new RightHandPattern();
  private final LeftHandPattern lp = new LeftHandPattern();
  private static final int NOTTHRESH = 35;
  /**
   * 
   */
  public ASLNamePattern() {
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
    return "name";
  }

  
  private static class RightHandPattern extends AbstractASLSingleHandStaticPattern {
    @Override
    public boolean matches(HandWrapper hand) {
      load(hand);
      return indexDir.isLeftForward() && middleDir.isLeftForward() //;
          && !ringDir.isLeftForward(NOTTHRESH) && !pinkyDir.isLeftForward(NOTTHRESH);
          //&& ringDir.isRightBackward(100) && pinkyDir.isRightBackward(100);
    }
    @Override
    public String translation() {
      return null;
    }
  }
  

  private static class LeftHandPattern extends AbstractASLSingleHandStaticPattern {
    @Override
    public boolean matches(HandWrapper hand) {
      load(hand);
      return indexDir.isRightForward() && middleDir.isRightForward() //;
          && !ringDir.isRightForward(NOTTHRESH) && !pinkyDir.isRightForward(NOTTHRESH);
          //&& ringDir.isLeftBackward(100) && pinkyDir.isLeftBackward(100);
    }
    @Override
    public String translation() {
      return null;
    }
  }
}
