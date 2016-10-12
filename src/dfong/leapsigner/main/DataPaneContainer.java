package dfong.leapsigner.main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

import dfong.leapsigner.patterns.core.FingersContainer;

public class DataPaneContainer {

  

  private static final String RESET_TEXT = "-";
  private static final Font bold = Font.font("Arial", FontWeight.BOLD, 14);

  private LRTextContainer pitchText = new LRTextContainer("Pitch");
  private LRTextContainer rollText = new LRTextContainer("Roll");
  private LRTextContainer yawText = new LRTextContainer("Yaw");
  
  private LRTextContainer thumbText = new LRTextContainer("Thumb");
  private LRTextContainer indexText = new LRTextContainer("Index");
  private LRTextContainer middleText = new LRTextContainer("Middle");
  private LRTextContainer ringText = new LRTextContainer("Ring");
  private LRTextContainer pinkyText = new LRTextContainer("Pinky");
  
  
//  private Text leftPitchText = new Text(RESET_TEXT);
//  private Text rightPitchText = new Text(RESET_TEXT);
//  
//  private Text leftRollText = new Text(RESET_TEXT);
//  private Text rightRollText = new Text(RESET_TEXT);
//
//  private Text leftYawText = new Text(RESET_TEXT);
//  private Text rightYawText = new Text(RESET_TEXT);
  
  
  
  public DataPaneContainer() {
    // TODO Auto-generated constructor stub
  }

  
  public GridPane getGridPane() {
    GridPane playerGrid = new GridPane();
    playerGrid.setHgap(10);
    playerGrid.setVgap(10);
    playerGrid.setPadding(new Insets(0, 10, 0, 10));

    
    //The title for the table spans for columns of the GridPane
    Text title = new Text("LeapMotion Hand Data");
    title.setFont(bold);
    playerGrid.add(title, 0,0,4,1);

    int col = 0;
    int row = 3;
    //Each set of three Text controls makes up a row of the table
    Text typeText = new Text("Type");
    typeText.setFont(bold);
    playerGrid.add(typeText, 0,row);

    Text leftText = new Text("Left");
    leftText.setFont(bold);
    playerGrid.add(leftText, 1,row);

    Text rightText = new Text("Right");
    rightText.setFont(bold);
    playerGrid.add(rightText, 2,row);

    row++;
    playerGrid.add(pitchText.getLabel(), colMod(col++), row);
    playerGrid.add(pitchText.getLeft(), colMod(col++), row);
    playerGrid.add(pitchText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(rollText.getLabel(), colMod(col++), row);
    playerGrid.add(rollText.getLeft(), colMod(col++), row);
    playerGrid.add(rollText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(yawText.getLabel(), colMod(col++), row);
    playerGrid.add(yawText.getLeft(), colMod(col++), row);
    playerGrid.add(yawText.getRight(), colMod(col++), row);

    row++;
    row++;
    playerGrid.add(thumbText.getLabel(), colMod(col++), row);
    playerGrid.add(thumbText.getLeft(), colMod(col++), row);
    playerGrid.add(thumbText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(indexText.getLabel(), colMod(col++), row);
    playerGrid.add(indexText.getLeft(), colMod(col++), row);
    playerGrid.add(indexText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(middleText.getLabel(), colMod(col++), row);
    playerGrid.add(middleText.getLeft(), colMod(col++), row);
    playerGrid.add(middleText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(ringText.getLabel(), colMod(col++), row);
    playerGrid.add(ringText.getLeft(), colMod(col++), row);
    playerGrid.add(ringText.getRight(), colMod(col++), row);

    row++;
    playerGrid.add(pinkyText.getLabel(), colMod(col++), row);
    playerGrid.add(pinkyText.getLeft(), colMod(col++), row);
    playerGrid.add(pinkyText.getRight(), colMod(col++), row);
    
    
    return playerGrid;
  }
  

  private static int colMod(int col) {
    return col % 3;
  }
  
  /**
   * @param leapHand the hand to update the text values with...
   */
  public void updateDataGrid(Hand leapHand) {
    if (!leapHand.isValid()) {
      return;
    }
//    Hand hand = hands.get(0);
    boolean isLeft = leapHand.isLeft();
    

    //FingerList fingers = leapHand.fingers();
    FingersContainer fingers = new FingersContainer(leapHand.fingers());
    Finger thumb = fingers.getThumb();
    Finger index = fingers.getIndex();
    Finger middle = fingers.getMiddle();
    Finger ring = fingers.getRing();
    Finger pinky = fingers.getPinky();
    
    // Get the hand's normal vector and direction
    Vector normal = leapHand.palmNormal();
    Vector direction = leapHand.direction();

    double pitch = Math.toDegrees(direction.pitch());
    double roll = Math.toDegrees(normal.roll());
    double yaw = Math.toDegrees(direction.yaw());

    
    if (isLeft) {
      pitchText.getLeft().setText(String.format("%.2f ", pitch));
      rollText.getLeft().setText(String.format("%.2f", roll));
      yawText.getLeft().setText(String.format("%.2f", yaw));
      
      thumbText.getLeft().setText(formatVectorString(thumb.direction()));
      indexText.getLeft().setText(formatVectorString(index.direction()));
      middleText.getLeft().setText(formatVectorString(middle.direction()));
      ringText.getLeft().setText(formatVectorString(ring.direction()));
      pinkyText.getLeft().setText(formatVectorString(pinky.direction()));
    }
    else {
      pitchText.getRight().setText(String.format("%.2f ", pitch));
      rollText.getRight().setText(String.format("%.2f", roll));
      yawText.getRight().setText(String.format("%.2f", yaw));

      thumbText.getRight().setText(formatVectorString(thumb.direction()));
      indexText.getRight().setText(formatVectorString(index.direction()));
      middleText.getRight().setText(formatVectorString(middle.direction()));
      ringText.getRight().setText(formatVectorString(ring.direction()));
      pinkyText.getRight().setText(formatVectorString(pinky.direction()));
    }
  }
  
  private static String formatVectorString(Vector v) {
    return String.format("(%.2f, %.2f, %.2f)", v.getX(), v.getY(), v.getZ());
  }
  
  private static final class LRTextContainer {
    private final String id;
    
    private final Text labelText = new Text(RESET_TEXT);
    private Text leftText = new Text(RESET_TEXT);
    private Text rightText = new Text(RESET_TEXT);
    public LRTextContainer(String id) {
      this.id = id;
      labelText.setFont(bold);
      GridPane.setHalignment(labelText, HPos.CENTER);
      this.labelText.setText(id);
    }
    
    public Text getLabel() {
      return this.labelText;
    }
    
    public Text getLeft() {
      return this.leftText;
    }
    
    public Text getRight() {
      return this.rightText;
    }
  }
  
}
