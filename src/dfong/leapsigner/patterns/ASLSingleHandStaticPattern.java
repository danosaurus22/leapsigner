/**
 * 
 */
package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.HandWrapper;

/**
 * @author dan
 *
 */
public interface ASLSingleHandStaticPattern {
  
  public boolean matches(HandWrapper hand);
  public String translation();
}
