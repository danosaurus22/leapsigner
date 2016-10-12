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
public class ASLMePattern extends AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {

  private boolean useMy;
  
  /**
   * 
   */
  public ASLMePattern(boolean useMy) {
    this.useMy = useMy;
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#matches(dfong.leapsigner.patterns.core.HandWrapper)
   */
  @Override
  public boolean matches(HandWrapper hand) {
    load(hand);
    return (indexDir.isBackward() && !middleDir.isBackward() && !ringDir.isBackward());
  }

  /* (non-Javadoc)
   * @see dfong.leapsigner.patterns.ASLSingleHandStaticPattern#translation()
   */
  @Override
  public String translation() {
    return useMy ? "my" : "me";
  }

}
