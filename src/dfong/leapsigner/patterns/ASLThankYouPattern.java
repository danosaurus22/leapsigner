/**
 * ASLMePattern.java
 * Nov 16, 2014
 * dan
 */
package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public class ASLThankYouPattern extends AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {

  private boolean useHi;
  
  /**
   * 
   */
  public ASLThankYouPattern(boolean useHi) {
    this.useHi = useHi;
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#matches(dfong.leapsigner.patterns.core.HandWrapper)
   */
  @Override
  public boolean matches(HandWrapper hand) {
    load(hand);
    //Bone bone = hand.getThumb().bone(Bone.Type.TYPE_DISTAL);
    //double angleTo = Math.toDegrees(Vector.down().angleTo(bone.direction()));
    
    double pitch = hand.getPitch();
    double roll = hand.getRoll();
    
    boolean isFlipped = (-180.f <= roll && roll <= -150.f) 
        || (150.f <= roll && roll <= 180.f);
    
    boolean isAngledForward = (30 <= pitch && pitch <= 60);
    
    return isFlipped && isAngledForward
        && thumbDir.isForwardUpward() && indexDir.isForwardUpward() 
        && middleDir.isForwardUpward() && ringDir.isForwardUpward() 
        && pinkyDir.isForwardUpward();
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#translation()
   */
  @Override
  public String translation() {
    return useHi ? "Thanks" : "Thank You";
  }

}
