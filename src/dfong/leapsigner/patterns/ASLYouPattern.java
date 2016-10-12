package dfong.leapsigner.patterns;

import dfong.leapsigner.patterns.core.HandWrapper;

public class ASLYouPattern extends AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {

  public ASLYouPattern() {
  }

  @Override
  public boolean matches(HandWrapper hand) {
    load(hand);
    return (indexDir.isForward() && middleDir.isDown() && ringDir.isDown());
  }

  @Override
  public String translation() {
    return "your";
  }

}
