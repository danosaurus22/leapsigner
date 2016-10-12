package dfong.leapsigner.patterns;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Vector;

import dfong.leapsigner.patterns.core.HandWrapper;

public class ASLAlphabetPattern extends AbstractASLSingleHandStaticPattern implements ASLSingleHandStaticPattern {

  private String lastMatched = "a";
  //private List<ASLSingleHandStaticPattern> letters = new ArrayList<>();
  

  @Override
  public boolean matches(HandWrapper hand) {
    load(hand);
    Bone bone = hand.getThumb().bone(Bone.Type.TYPE_DISTAL);
    if (indexDir.isUp(40) && middleDir.isDown(40) && ringDir.isDown(40) && pinkyDir.isDown(50)) {
      lastMatched = "d";
      return true;
    }
    if (thumbDir.isDir(UP, 40) 
        && indexDir.isDir(DOWN, 40) 
        && middleDir.isDir(DOWN, 40) 
        && ringDir.isDir(DOWN, 40) 
        && pinkyDir.isDir(DOWN, 40)) {
      lastMatched = "a";
      return true;
    }
    Vector unitDirVector = hand.isLeft() ? Vector.right() : Vector.left();
    double angleTo = Math.toDegrees(unitDirVector.angleTo(bone.direction()));
    //System.err.println("angle: " + angleTo);
    if ((0.f<=angleTo && angleTo <= 30.f)
        && indexDir.isUp()
        && middleDir.isUp()
        && ringDir.isUp()
        && pinkyDir.isUp()) {
      lastMatched = "b";
      return true;
    }
    
    
    if (thumbDir.isDown(50)
        && indexDir.isBackward(50)
        && middleDir.isBackward(50)
        && ringDir.isBackward(50)) {
      lastMatched = "n";
      return true;
    }
    
    
    
//    if (thumbDir.isRightUpward(30)
//        && indexDir.isDir(DOWN, 40)) {
//      lastMatched = "n";
//      return true;
//    }
    
    
    
    return (indexDir.isForward() && middleDir.isDown() && ringDir.isDown());
  }

  @Override
  public String translation() {
    return lastMatched;
  }

  
  
  
}
