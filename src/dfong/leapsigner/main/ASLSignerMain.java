/**
 * AslSignerApplication.java
 * Nov 10, 2014
 * AslSignerApplication
 * author: dan
 */
package dfong.leapsigner.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

import de.ruzman.leap.LeapApp;
import de.ruzman.leap.LeapApp.Mode;
import de.ruzman.leap.event.PointEvent;
import de.ruzman.leap.event.PointMotionListener;
import de.ruzman.leap.fx.HandFX3D;
import dfong.leapsigner.patterns.core.ASLPatternMatcher;

/**
 * @author dan
 *
 */
public class ASLSignerMain extends Application implements PointMotionListener {

  //private Group group;
  private Map<Integer, HandFX3D> hands;
  
  private static final boolean DEBUG_SHRINK = false;//true;
  
  
  private Text text = new Text(-80, -50, "Welcome to ASL Signer");
  
  private Pane centerPane = new Pane();
  private BorderPane borderPane = new BorderPane();
  
  private DataPaneContainer dataPaneContainer = new DataPaneContainer();
  
  private GridPane gridPane;// = new GridPane();
  
  private final static double PERIOD = 16.0f;//1.f / HZ;
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    LeapApp.init(true);
    //LeapApp.init(false);
    LeapApp.setMode(Mode.INTERACTION_BOX);
    
    
    ASLPatternMatcher.SPEAKER.speak("Welcome to the "
        + "beta version of the American Sign Language Translator");
    
    launch(args);
  }
  
  /** {@inheritDoc} */
  @Override
  public void start(Stage primaryStage) throws Exception {
    centerPane.setDepthTest(DepthTest.ENABLE);
    //centerPane.setStyle("-fx-background-color: #336699;");
    
    hands = new HashMap<>();
    
    text.setFill(Color.DARKRED);

    Font font = new Font(20);
    text.setFont(font);

    centerPane.getChildren().add(text);

    gridPane = dataPaneContainer.getGridPane();
    
    borderPane.setCenter(centerPane);
    
    int width = 1000;
    int height = 500;
    
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setTranslateZ(-height);
    camera.setTranslateY(-200);
    camera.setFarClip(width);
    camera.setFieldOfView(40);
    
    //Scene scene = new Scene(group, 1000, 500);
    Scene scene = new Scene(borderPane, width, height);    
    scene.setCamera(camera);
    
    Screen primaryScreen = Screen.getPrimary();
    Rectangle2D bounds = primaryScreen.getBounds();
    primaryStage.setX(bounds.getMinX());
    primaryStage.setY(bounds.getMinY());
    primaryStage.setWidth(bounds.getWidth());
    primaryStage.setHeight(bounds.getHeight());
    if (DEBUG_SHRINK) {
      primaryStage.setWidth(bounds.getWidth() / 4.F);
      primaryStage.setHeight(bounds.getHeight() / 4.F);
    }
    
    primaryStage.setTitle("ASL Signer");
    primaryStage.setScene(scene);
    if (DEBUG_SHRINK) {
      primaryStage.show();
    }
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      
      @Override
      public void handle(WindowEvent arg0) {

        
        ASLPatternMatcher.SPEAKER.speak("Thank you for your attention. Have a wonderful day.");
        try {
          Thread.currentThread().sleep(3800);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        System.out.println("Quitting: " + new Date());
        System.exit(0);
      }
    });
    
    Scene scene2 = new Scene(gridPane);
    Stage secondaryStage = new Stage();
    secondaryStage.setScene(scene2);
    secondaryStage.setX(bounds.getWidth()*3.f/4.f);
    secondaryStage.setY(bounds.getMinY());
    secondaryStage.setWidth(bounds.getWidth() * 1.f/4.f);
    secondaryStage.setHeight(bounds.getHeight() * 0.5);
//    secondaryStage.initStyle(StageStyle.UNDECORATED);
    secondaryStage.show(); 
    if (!DEBUG_SHRINK) {
      primaryStage.show();
    }
    secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      
      @Override
      public void handle(WindowEvent arg0) {
        System.out.println("Quitting: " + new Date());
        ASLPatternMatcher.SPEAKER.speak("Thank you for your attention. Have a wonderful day.");
        System.exit(0);
      }
    });
    
    synchronizeWithLeapMotion();
    LeapApp.getMotionRegistry().addPointMotionListener(this);
  } 
  
  /**
   * Set the leap motion sample time.
   */
  private void synchronizeWithLeapMotion() {
    //Duration duration = Duration.seconds(1.0 / 60.0);
    //Duration duration = Duration.millis(1.0);
    System.err.println("period: " + PERIOD);
    Duration duration = Duration.millis(PERIOD);
    
    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(duration, ea -> LeapApp.update()));
    timeline.play();
  }
  
  /** {@inheritDoc} */
  @Override
  public void pointMoved(PointEvent event) {
    int handId = event.getSource().id();
    HandFX3D hand = hands.get(handId);
    
    if(event.leftViewPort()) {
      hands.remove(handId);
      //group.getChildren().remove(hand);
      centerPane.getChildren().remove(hand);
    } 
    else if(hand == null) {
      hand = new HandFX3D(handId);
      hands.put(handId, hand);
      //group.getChildren().add(hand);
      centerPane.getChildren().add(hand);
    }
    
    if(hand != null) {
      //LeapApp.getController().frame().hands();
      
      
      Hand leapHand = LeapApp.getController().frame().hand(handId);
      dataPaneContainer.updateDataGrid(leapHand);
      hand.update(LeapApp.getController().frame().hand(handId));
    }
  }
  
  /** {@inheritDoc} */
  @Override
  public void pointDragged(PointEvent event) {
  }
}
