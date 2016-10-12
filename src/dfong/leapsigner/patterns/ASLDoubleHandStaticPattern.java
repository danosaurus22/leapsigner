/**
 * ASLDoubleHandStaticPattern.java
 * Created: Nov 24, 2014
 * @author: dan
 */
package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public interface ASLDoubleHandStaticPattern {

  public boolean matches(HandWrapper leftHand, HandWrapper rightHand);
  public String translation();
}
