package dfong.leapsigner.patterns.core;

import com.leapmotion.leap.FingerList;

public class FingersContainerExt extends FingersContainer {

  private final DirectionApproximator thumbDir;
  private final DirectionApproximator indexDir;
  private final DirectionApproximator middleDir;
  private final DirectionApproximator ringDir;
  private final DirectionApproximator pinkyDir;
  
  public FingersContainerExt(FingerList fingersList) {
    super(fingersList);
    

    thumbDir = new DirectionApproximator(thumb.direction());
    indexDir = new DirectionApproximator(index.direction());
    middleDir = new DirectionApproximator(middle.direction());
    ringDir = new DirectionApproximator(ring.direction());
    pinkyDir = new DirectionApproximator(pinky.direction());
  }

  public DirectionApproximator getThumbDir() {
    return thumbDir;
  }

  public DirectionApproximator getIndexDir() {
    return indexDir;
  }

  public DirectionApproximator getMiddleDir() {
    return middleDir;
  }

  public DirectionApproximator getRingDir() {
    return ringDir;
  }

  public DirectionApproximator getPinkyDir() {
    return pinkyDir;
  }

}
