/**
 * AbstractASLSingleHandStaticPattern.java
 * Nov 16, 2014
 * dan
 */
package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.DirectionApproximator;
import dfong.leapsigner.patterns.core.FingersContainerExt;
import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public abstract class AbstractASLDoubleHandStaticPattern implements ASLDoubleHandStaticPattern {
  
  protected DirectionApproximator thumbDirL;
  protected DirectionApproximator indexDirL;
  protected DirectionApproximator middleDirL;
  protected DirectionApproximator ringDirL;
  protected DirectionApproximator pinkyDirL;

  protected DirectionApproximator thumbDirR;
  protected DirectionApproximator indexDirR;
  protected DirectionApproximator middleDirR;
  protected DirectionApproximator ringDirR;
  protected DirectionApproximator pinkyDirR;

  /**
   * 
   */
  public AbstractASLDoubleHandStaticPattern() {

  }
  
  protected void load(HandWrapper leftHand, HandWrapper rightHand) {
    FingersContainerExt fingers = leftHand.getFingersContainer();
    this.thumbDirL = fingers.getThumbDir();
    this.indexDirL = fingers.getIndexDir();
    this.middleDirL = fingers.getMiddleDir();
    this.ringDirL = fingers.getRingDir();
    this.pinkyDirL = fingers.getPinkyDir();
    
    FingersContainerExt fingersR = rightHand.getFingersContainer();
    this.thumbDirR = fingersR.getThumbDir();
    this.indexDirR = fingersR.getIndexDir();
    this.middleDirR = fingersR.getMiddleDir();
    this.ringDirR = fingersR.getRingDir();
    this.pinkyDirR = fingersR.getPinkyDir();
  }
  
  @Override
  public abstract boolean matches(HandWrapper leftHand, HandWrapper rightHand);

  @Override
  public abstract String translation();

}
