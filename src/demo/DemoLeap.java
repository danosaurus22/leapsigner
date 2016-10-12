package demo;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

public class DemoLeap {
  private static final Controller controller = new Controller();
  //private static SampleListener listener = new SampleListener();
  
  public static void main(String[] args) {
    // Create a sample listener and controller
    //Controller controller = new Controller();
    
    // Have the sample listener receive events from the controller
    //controller.addListener(listener);
    System.out.println("waiting for isConnected");
    while(!controller.isConnected()) {
    }
    System.out.println("waiting for isConnected... connected!");
    System.out.println("waiting for isServiceConnected");
    while(!controller.isServiceConnected()) {
    }
    System.out.println("waiting for isServiceConnected... connected!");
    // Keep this process running until Enter is pressed
    Frame frame = controller.frame();
    System.out.println("frame: " + frame);
    System.out.println("Press Enter to quit...");
    try {
//        	if (true)
      try {
        while(true) {
//        		System.out.println("a: " + controller.config());
          Frame frame2 = controller.frame();
          System.out.println("frame2: " + frame2 + ", " + frame2.timestamp());
          Thread.sleep(40);
        }
      } 
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      // Remove the sample listener when done
      //controller.removeListener(listener);
    }
    
  }
}
