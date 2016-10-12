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
public abstract class AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {
  protected DirectionApproximator.Direction UP = DirectionApproximator.Direction.UP;
  protected DirectionApproximator.Direction DOWN = DirectionApproximator.Direction.DOWN;
  protected DirectionApproximator.Direction LEFT = DirectionApproximator.Direction.LEFT;
  protected DirectionApproximator.Direction RIGHT = DirectionApproximator.Direction.RIGHT;
  
  
  protected DirectionApproximator thumbDir;
  protected DirectionApproximator indexDir;
  protected DirectionApproximator middleDir;
  protected DirectionApproximator ringDir;
  protected DirectionApproximator pinkyDir;
  
  /**
   * 
   */
  public AbstractASLSingleHandStaticPattern() {

  }
  
  protected void load(HandWrapper hand) {
    FingersContainerExt fingers = hand.getFingersContainer();
    
    this.thumbDir = fingers.getThumbDir();
    this.indexDir = fingers.getIndexDir();
    this.middleDir = fingers.getMiddleDir();
    this.ringDir = fingers.getRingDir();
    this.pinkyDir = fingers.getPinkyDir();
  }
  
  @Override
  public abstract boolean matches(HandWrapper hand);

  @Override
  public abstract String translation();

}
