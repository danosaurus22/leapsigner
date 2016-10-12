package dfong.leapsigner.patterns.core;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

public class HandWrapper {

  private final Hand hand;
  private final double pitch;
  private final double roll;
  private final double yaw;
  
  private final FingersContainerExt fingersContainer;
  
  public HandWrapper(Hand hand) {
    this.hand = hand;
    // Get the hand's normal vector and direction
    Vector normal = hand.palmNormal();
    Vector direction = hand.direction();

    this.pitch = Math.toDegrees(direction.pitch());
    this.roll = Math.toDegrees(normal.roll());
    this.yaw = Math.toDegrees(direction.yaw());

    this.fingersContainer = new FingersContainerExt(hand.fingers());
  }

  public Hand getHand() {
    return hand;
  }
  
  public boolean isRight() {
    return hand.isRight();
  }
  
  public boolean isLeft() {
    return hand.isLeft();
  }

  public double getPitch() {
    return pitch;
  }

  public double getRoll() {
    return roll;
  }

  public double getYaw() {
    return yaw;
  }

  public FingersContainerExt getFingersContainer() {
    return fingersContainer;
  }


  public Finger getThumb() {
    return fingersContainer.getThumb();
  }
  public DirectionApproximator getThumbDir() {
    return fingersContainer.getThumbDir();
  }

  public Finger getIndex() {
    return fingersContainer.getIndex();
  }
  public DirectionApproximator getIndexDir() {
    return fingersContainer.getIndexDir();
  }

  public Finger getMiddle() {
    return fingersContainer.getMiddle();
  }
  public DirectionApproximator getMiddleDir() {
    return fingersContainer.getMiddleDir();
  }

  public Finger getRing() {
    return fingersContainer.getRing();
  }
  public DirectionApproximator getRingDir() {
    return fingersContainer.getRingDir();
  }

  public Finger getPinky() {
    return fingersContainer.getPinky();
  }
  public DirectionApproximator getPinkyDir() {
    return fingersContainer.getPinkyDir();
  }
  
}
