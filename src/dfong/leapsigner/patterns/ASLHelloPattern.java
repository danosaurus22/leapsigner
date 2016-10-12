/**
 * ASLMePattern.java
 * Nov 16, 2014
 * dan
 */
package dfong.leapsigner.patterns;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Vector;

import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public class ASLHelloPattern extends AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {

  private boolean useHi;
  
  /**
   * 
   */
  public ASLHelloPattern(boolean useHi) {
    this.useHi = useHi;
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#matches(dfong.leapsigner.patterns.core.HandWrapper)
   */
  @Override
  public boolean matches(HandWrapper hand) {
    load(hand);
    Bone bone = hand.getThumb().bone(Bone.Type.TYPE_DISTAL);
    double angleTo = Math.toDegrees(Vector.down().angleTo(bone.direction()));
    return ((0.f<=angleTo && angleTo <= 45) && thumbDir.isUp() && indexDir.isUp() && middleDir.isUp() && ringDir.isUp() && pinkyDir.isUp());
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#translation()
   */
  @Override
  public String translation() {
    return useHi ? "Hi" : "Hello";
  }

}
