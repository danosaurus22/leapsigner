/**
 * 
 */
package dfong.leapsigner.patterns.core;

import com.leapmotion.leap.Vector;

/**
 * @author dan
 *
 */
public class DirectionApproximator {
  public static enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
  
  
  private final static Vector FORWARD = Vector.forward();
  private final static Vector BACKWARD = Vector.backward();
  private final static Vector LEFTWARD = Vector.left();
  private final static Vector RIGHTWARD = Vector.right();
  private final static Vector UPWARD = Vector.up();
  private final static Vector DOWNWARD = Vector.down();

  private final static Vector LEFTFORWARD;
  private final static Vector RIGHTFORWARD;
  private final static Vector RIGHTUPWARD;
  private final static Vector LEFTBACKWARD;
  private final static Vector RIGHTBACKWARD;
  
  private final static Vector FORWARDUPWARD;
  
  static {
    Vector v;
    v = new Vector(-1.f, 0.f, -1.f);
    LEFTFORWARD = v.normalized();

    v = new Vector(1.f, 0.f, -1.f);
    RIGHTFORWARD = v.normalized();

    v = new Vector(1.f, 1.f, 0.f);
    RIGHTUPWARD = v.normalized();

    v = new Vector(-1.f, 0.f, 1.f);
    LEFTBACKWARD = v.normalized();

    v = new Vector(1.f, 0.f, 1.f);
    RIGHTBACKWARD = v.normalized();
    
    v = new Vector(0.f, 1.f, -1.f);
    FORWARDUPWARD = v.normalized();
  };
  
  
  
  private static final double THRESHOLD_DEGREES = 20.f;
  private static final boolean DEBUG_LOGGING = false;
  
  private final Vector direction;
  
  private final boolean isForward;
  private final boolean isBackward;
  private final boolean isRight;
  private final boolean isLeft;
  private final boolean isUp;
  private final boolean isDown;
  
  // TODO:
//  private boolean isUpForward;
//  private boolean isDownForward;

  private final boolean isRightForward;
  private final boolean isLeftForward;
  
  private final boolean isRightBackward;
  private final boolean isLeftBackward;

  private final boolean isRightUpward;

  private final boolean isForwardUpward;

  // could switch to lazy instantiation...
  public DirectionApproximator(Vector direction) {
    this.direction = direction;
    double degrees = Math.toDegrees(direction.angleTo(FORWARD));
    if (DEBUG_LOGGING) System.err.println("forward: " + degrees);
    isForward = isWithinThreshold(degrees);
    
    degrees = Math.toDegrees(direction.angleTo(BACKWARD));
    if (DEBUG_LOGGING) System.err.println("backward: " + degrees);
    isBackward = isWithinThreshold(degrees);
    
    degrees = Math.toDegrees(direction.angleTo(RIGHTWARD));
    if (DEBUG_LOGGING) System.err.println("rightward: " + degrees);
    isRight = isWithinThreshold(degrees);
    
    degrees = Math.toDegrees(direction.angleTo(LEFTWARD));
    if (DEBUG_LOGGING) System.err.println("leftward: " + degrees);
    isLeft = isWithinThreshold(degrees);
    
    degrees = Math.toDegrees(direction.angleTo(UPWARD));
    if (DEBUG_LOGGING) System.err.println("upward: " + degrees);
    isUp = isWithinThreshold(degrees);
    
    degrees = Math.toDegrees(direction.angleTo(DOWNWARD));
    if (DEBUG_LOGGING) System.err.println("downward: " + degrees);
    isDown = isWithinThreshold(degrees);
    

    degrees = Math.toDegrees(direction.angleTo(LEFTFORWARD));
    if (DEBUG_LOGGING) System.err.println("leftforward: " + degrees);
    isLeftForward = isWithinThreshold(degrees);

    degrees = Math.toDegrees(direction.angleTo(RIGHTFORWARD));
    if (DEBUG_LOGGING) System.err.println("rightforward: " + degrees);
    isRightForward = isWithinThreshold(degrees);
    

    degrees = Math.toDegrees(direction.angleTo(LEFTBACKWARD));
    if (DEBUG_LOGGING) System.err.println("leftbackward: " + degrees);
    isLeftBackward = isWithinThreshold(degrees);

    degrees = Math.toDegrees(direction.angleTo(RIGHTBACKWARD));
    if (DEBUG_LOGGING) System.err.println("rightbackward: " + degrees);
    isRightBackward = isWithinThreshold(degrees);

    degrees = Math.toDegrees(direction.angleTo(RIGHTUPWARD));
    if (DEBUG_LOGGING) System.err.println("isRightUpward: " + degrees);
    isRightUpward = isWithinThreshold(degrees);

    degrees = Math.toDegrees(direction.angleTo(FORWARDUPWARD));
    if (DEBUG_LOGGING) System.err.println("isForwardUpward: " + degrees);
    isForwardUpward = isWithinThreshold(degrees);
    
    
  }
  
  private static boolean isWithinThreshold(double degrees) {
    return degrees != 0.f && degrees <= THRESHOLD_DEGREES;
  }
  
  private static boolean isWithinThreshold(double degrees, double thresholdInDegrees) {
    return degrees != 0.f && degrees <= thresholdInDegrees;
  }
  
  public boolean isDir(Direction dir) {
    switch (dir) {
    case UP:
      return isUp();
    case DOWN:
      return isDown();
    case LEFT:
      return isLeft();
    case RIGHT:
      return isRight();
    default:
      return false;
    }
  }
  
  public boolean isDir(Direction dir, double thresholdInDegrees) {
    switch (dir) {
    case UP:
      return isUp(thresholdInDegrees);
    case DOWN:
      return isDown(thresholdInDegrees);
    case LEFT:
      return isLeft(thresholdInDegrees);
    case RIGHT:
      return isRight(thresholdInDegrees);
    default:
      return false;
    }
  }
  
  public boolean isForward() { return isForward; }
  public boolean isForward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(FORWARD)), thresholdInDegrees);
  }
  
  public boolean isBackward() {
    return isBackward;
  }
  public boolean isBackward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(BACKWARD)), thresholdInDegrees);
  }
  
  public boolean isRight() {
    return isRight;
  }
  public boolean isRight(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(RIGHTWARD)), thresholdInDegrees);
  }
  
  public boolean isLeft() {
    return isLeft;
  }
  public boolean isLeft(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(LEFTWARD)), thresholdInDegrees);
  }
  
  public boolean isUp() {
    return isUp;
  }
  public boolean isUp(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(UPWARD)), thresholdInDegrees);
  }
  
  public boolean isDown() {
    return isDown;
  }
  public boolean isDown(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(DOWNWARD)), thresholdInDegrees);
  }

  public boolean isLeftForward() {
    return isLeftForward;
  }
  public boolean isLeftForward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(LEFTFORWARD)), thresholdInDegrees);
  }

  public boolean isRightForward() {
    return isRightForward;
  }
  public boolean isRightForward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(RIGHTFORWARD)), thresholdInDegrees);
  }

  public boolean isRightBackward() {
    return isRightBackward;
  }
  public boolean isRightBackward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(RIGHTBACKWARD)), thresholdInDegrees);
  }

  public boolean isRightUpward() {
    return isRightUpward;
  }
  public boolean isRightUpward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(RIGHTUPWARD)), thresholdInDegrees);
  }
  
  public boolean isLeftBackward() {
    return isLeftBackward;
  }
  public boolean isLeftBackward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(LEFTBACKWARD)), thresholdInDegrees);
  }
  


  public boolean isForwardUpward() {
    return isForwardUpward;
  }
  public boolean isForwardUpward(double thresholdInDegrees) {
    return isWithinThreshold(Math.toDegrees(direction.angleTo(FORWARDUPWARD)), thresholdInDegrees);
  }
}
