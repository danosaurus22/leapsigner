package de.ruzman.leap.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

import de.ruzman.leap.LeapApp;

public final class LeapEventHandler extends Listener {	
  private static final LeapEventHandler SINGLETON = new LeapEventHandler();
  
  private List<LeapListener> leapListeners;
  private LeapEvent leapEvent;
  
  private LeapEventHandler() {
    leapListeners = new CopyOnWriteArrayList<>();
    leapEvent = new LeapEvent();
  }
  
  public static LeapEventHandler getInstance() {
    return SINGLETON;
  }
  
  public static void addLeapListener(LeapListener leapListener) {
    SINGLETON.leapListeners.add(leapListener);
  }
  
  public static void removeLeapListener(LeapListener leapListener) {
    SINGLETON.leapListeners.remove(leapListener);
  }
  
  public static void removeAllLeapListener() {
    SINGLETON.leapListeners.clear();
  }
  
  public static void fireFrameUpdate() {
    SINGLETON.onFrame(LeapApp.getController());
  }
  
  @Override
  public void onDisconnect(Controller controller) {
    leapEvent.setDisconnected(true);
    fireStatusChanged();
  }
  
  @Override
  public void onExit(Controller controller) {
    leapEvent.setExited(true);
    fireStatusChanged();
  }
  
  @Override
  public void onFocusLost(Controller controller) {
    leapEvent.setFocusLost(true);
    fireStatusChanged();
  }
  
  @Override
  public void onFrame(Controller controller) {
    if(controller.frame().hands().count() >= LeapApp.getMinimumHandNumber()) {	
      HandList hands = controller.frame().hands();
      Hand hand = hands.get(0);
      
      String handType = hand.isLeft() ? "Left hand" : "Right hand";
//      System.out.println("  " + handType + ", id: " + hand.id()
//              + ", palm position: " + hand.palmPosition());
      
      
      // Get the hand's normal vector and direction
      Vector normal = hand.palmNormal();
      Vector direction = hand.direction();
      
      // Calculate the hand's pitch, roll, and yaw angles
//      System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
//              + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
//              + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
      
      FingerList fingers = hand.fingers();
      Finger finger = fingers.get(1);
//      System.out.println("finger: " + finger.type() + ", " + finger.);
      
      
      Finger indexFinger = finger;//indexFingers.get(0);
//      Finger indexFinger = hand.finger(Finger.Type.TYPE_INDEX.ordinal());
//
//      System.out.println("    " + indexFinger.type() + ", id: " + indexFinger.id()
//                       + ", length: " + indexFinger.length()
//                       + "mm, width: " + indexFinger.width() + "mm");
//
//      Bone proximalBone = indexFinger.bone(Bone.Type.TYPE_PROXIMAL);
//      Bone intermediateBone = indexFinger.bone(Bone.Type.TYPE_INTERMEDIATE);
//      System.out.println("      " + proximalBone.type()
//              + " bone, start: " + proximalBone.prevJoint()
//              + ", end: " + proximalBone.nextJoint()
//              + ", direction: " + proximalBone.direction());
//      System.out.println("      " + intermediateBone.type()
//              + " bone, start: " + intermediateBone.prevJoint()
//              + ", end: " + intermediateBone.nextJoint()
//              + ", direction: " + intermediateBone.direction());
//      RuntimeException re = new RuntimeException("trace");
//      re.printStackTrace();
      
      
      leapEvent.setFrame(controller.frame());
      
      for(LeapListener leapListener: leapListeners) {
        leapListener.update(leapEvent);
      }
    }
  }
  
  private void fireStatusChanged() {
    for(LeapListener leapListener: leapListeners) {
      leapListener.statusChanged(leapEvent);
    }
  }
  
  public static void updateFrame() {
    SINGLETON.onFrame(LeapApp.getController());
  }
}

