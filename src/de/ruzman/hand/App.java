package de.ruzman.hand;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.util.Duration;

import com.leapmotion.leap.Hand;

import de.ruzman.leap.LeapApp;
import de.ruzman.leap.LeapApp.Mode;
import de.ruzman.leap.event.PointEvent;
import de.ruzman.leap.event.PointMotionListener;
import de.ruzman.leap.fx.HandFX3D;

public class App extends Application implements PointMotionListener {	
  //private Group group;
  private Map<Integer, HandFX3D> hands;
  
  
  private Text text = new Text(-50, -50, "JavaFX Scene");
  
  
  
  
  private Pane centerPane = new Pane();
  private BorderPane borderPane = new BorderPane();
  
  private GridPane gridPane = getGridPane();// = new GridPane();
  
  private final static int HZ = 60;
  private final static double PERIOD = 16.0f;//1.f / HZ;
  
  
  public static void main(String[] args) {
    LeapApp.init(true);
    //LeapApp.init(false);
    LeapApp.setMode(Mode.INTERACTION_BOX);
    
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    centerPane.setDepthTest(DepthTest.ENABLE);
    //centerPane.setStyle("-fx-background-color: #336699;");
    
    hands = new HashMap<>();
    
    text.setFill(Color.DARKRED);

    Font font = new Font(20);
    text.setFont(font);

    centerPane.getChildren().add(text);

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
    
    primaryStage.setTitle("ASL Signer");
    primaryStage.setScene(scene);
//    primaryStage.setScene(mainScene);
    primaryStage.show();
    
    Scene scene2 = new Scene(gridPane);
    Stage secondaryStage = new Stage();
    secondaryStage.setScene(scene2);
//    Rectangle2D bounds2 = secondaryScreen.getBounds();
    secondaryStage.setX(bounds.getWidth()*3.f/4.f);
    secondaryStage.setY(bounds.getMinY());
//    secondaryStage.setWidth(bounds2.getWidth());
//    secondaryStage.setHeight(bounds2.getHeight());
//    secondaryStage.initStyle(StageStyle.UNDECORATED);
    secondaryStage.show(); 
    
    
    synchronizeWithLeapMotion();
    LeapApp.getMotionRegistry().addPointMotionListener(this);
  }	
  
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
      
      //Hand hand = hands.get(0);
//      String handType = leapHand.isLeft() ? "Left hand" : "Right hand";
//      System.out.println("  " + handType + ", id: " + leapHand.id()
//                       + ", palm position: " + leapHand.palmPosition());
//
//
//      // Get the hand's normal vector and direction
//      Vector normal = leapHand.palmNormal();
//      Vector direction = leapHand.direction();
//
//      // Calculate the hand's pitch, roll, and yaw angles
//      System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
//                       + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
//                       + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
//
//      FingerList fingers = leapHand.fingers();
//      Finger finger = fingers.get(1);
////      System.out.println("finger: " + finger.type() + ", " + finger.);
//      
//
//      Finger indexFinger = finger;//indexFingers.get(0);
////      Finger indexFinger = hand.finger(Finger.Type.TYPE_INDEX.ordinal());
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
//      text.setText("hand: " + intermediateBone.direction());
      
      hand.update(LeapApp.getController().frame().hand(handId));
    }
  }
  
  @Override
  public void pointDragged(PointEvent event) {
  }
  
  public static GridPane getGridPane() {
    GridPane playerGrid = new GridPane();
    playerGrid.setHgap(10);
    playerGrid.setVgap(10);
    playerGrid.setPadding(new Insets(0, 10, 0, 10));

    //The title for the table spans for columns of the GridPane
    Text title = new Text("Top Scorers in English Premier League");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    playerGrid.add(title, 0,0,4,1);

    //Each set of three Text controls makes up a row of the table
    Text rankTitle = new Text("Rank");
    rankTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    playerGrid.add(rankTitle, 0,3);

    Text playerTitle = new Text("Player");
    playerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    playerGrid.add(playerTitle, 1,3);

    Text goalTitle = new Text("Goals");
    goalTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    playerGrid.add(goalTitle, 2,3);

    Text rank1 = new Text("1");
    rank1.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(rank1, HPos.CENTER);
    playerGrid.add(rank1, 0,4);

    Text player1 = new Text("Alan Shearer");
    player1.setFont(Font.font("Arial", 16));
    playerGrid.add(player1, 1,4);

    Text goals1 = new Text("260");
    goals1.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(goals1, HPos.CENTER);
    playerGrid.add(goals1, 2,4);

    Text rank2 = new Text("2");
    rank2.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(rank2, HPos.CENTER);
    playerGrid.add(rank2, 0,5);

    Text player2 = new Text("Andrew Cole");
    player2.setFont(Font.font("Arial", 16));
    playerGrid.add(player2, 1,5);

    Text goals2 = new Text("187");
    goals2.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(goals2, HPos.CENTER);
    playerGrid.add(goals2, 2,5);

    Text rank3 = new Text("3");
    rank3.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(rank3, HPos.CENTER);
    playerGrid.add(rank3, 0,6);

    Text player3 = new Text("Thierry Henry");
    player3.setFont(Font.font("Arial", 16));
    playerGrid.add(player3, 1,6);

    Text goals3 = new Text("175");
    goals3.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(goals3, HPos.CENTER);
    playerGrid.add(goals3, 2,6);
    
    Text rank4 = new Text("4");
    rank4.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(rank4, HPos.CENTER);
    playerGrid.add(rank4, 0,7);

    Text player4 = new Text("Frank Lampard");
    player4.setFont(Font.font("Arial", 16));
    playerGrid.add(player4, 1,7);

    Text goals4 = new Text("165");
    goals4.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(goals4, HPos.CENTER);
    playerGrid.add(goals4, 2,7);

    Text rank5 = new Text("5");
    rank5.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(rank5, HPos.CENTER);
    playerGrid.add(rank5, 0,8);

    Text player5 = new Text("Robbie Fowler");
    player5.setFont(Font.font("Arial", 16));
    playerGrid.add(player5, 1,8);

    Text goals5 = new Text("162");
    goals5.setFont(Font.font("Arial", 16));
    GridPane.setHalignment(goals5, HPos.CENTER);
    playerGrid.add(goals5, 2,8);
    return playerGrid;
  }
  
}

