package dfong.leapsigner.patterns.core;

import java.util.Iterator;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;

public class FingersContainer {

  public Finger thumb;
  public Finger index;
  public Finger middle;
  public Finger ring;
  public Finger pinky;
  
  public FingersContainer(FingerList fingersList) {

    Iterator<Finger> iter = fingersList.iterator();
    while(iter.hasNext()) {
      Finger finger = iter.next();
      switch (finger.type()) {
      case TYPE_THUMB:{
        thumb = finger;
      }break;
      case TYPE_INDEX:{
        index = finger;
      }break;
      case TYPE_MIDDLE:{
        middle = finger;
      }break;
      case TYPE_RING:{
        ring = finger;
      }break;
      case TYPE_PINKY:{
        pinky = finger;
      }break;
      default:
      }
    }
  }
  
  public Finger get(Finger.Type type) {
    switch (type) {
    case TYPE_THUMB: return thumb;
    case TYPE_INDEX: return index;
    case TYPE_MIDDLE: return middle;
    case TYPE_RING: return ring;
    case TYPE_PINKY: return pinky;
    default:
      return null;
    }
  }
  
  public Finger getThumb() { return this.thumb;}
  public Finger getIndex() { return index;}
  public Finger getMiddle() { return middle;}
  public Finger getRing() { return ring;}
  public Finger getPinky() { return pinky;}

}
