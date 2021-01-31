package uk.ac.aber.cs211.group2.dictionary.tests.thirdTest;

import uk.ac.aber.cs211.group2.dictionary.Main;
import uk.ac.aber.cs211.group2.dictionary.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/** This class deals with how the “match” tests are displayed.
 * @author Robert Mlynarczyk[rom57]
 */
public class ThirdTestController extends AnchorPane {

    @FXML
    SplitPane base_pane;
    @FXML
    AnchorPane right_pane;
    @FXML
    Button reset;
    @FXML
    public Text score;
    @FXML
    public Text prompt;
    @FXML
    Button submit;
    @FXML
    Button next;

    public static Stage window = new Stage();

    public static ArrayList<DraggableNode> dn_l = new ArrayList<>();
    public static ArrayList<DraggableNode> dn_r = new ArrayList<>();

    DraggableNode node1 = new DraggableNode();
    DraggableNode node2 = new DraggableNode();
    DraggableNode node3 = new DraggableNode();
    DraggableNode node4 = new DraggableNode();

    DraggableNode nodee1 = new DraggableNode();
    DraggableNode nodee2 = new DraggableNode();
    DraggableNode nodee3 = new DraggableNode();
    DraggableNode nodee4 = new DraggableNode();

    private DragIcon mDragOverIcon = null;
    private boolean isEnglish;
    int currScore;
    int currNo;
    int scores = 0;
    ArrayList<Word> pracList = Main.dictionary.getPracticeList();

    private EventHandler<DragEvent> mIconDragOverRoot = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverRightPane = null;
    public DraggableNode[] left = new DraggableNode[4];
    public DraggableNode[] right = new DraggableNode[4];
    public ArrayList<NodeLink> nl = new ArrayList<>();

    public ThirdTestController() {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/ThirdTest.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Method that returns an object if its is found in the practice list.
     * @param dn
     * @return method returns english or welsh translation of dn
     */

    public String inPracList(String dn) {
        if (isEnglish) {
            for (Word engW : pracList) {
                if (dn.equals(engW.getWelsh())) {
                    return engW.getEnglish();
                }
            }
        } else {
            for (Word welW : pracList) {
                if (dn.equals(welW.getEnglish())) {
                    return welW.getWelsh();
                }
            }
        }
        return null;
    }


    /**
     * Method that returns an object if its is found in the practice list.
     * @param dn
     * @return method returns english or welsh translation of dn
     */

    public String inPracListSameLang(String dn) {
        if (isEnglish) {
            for (Word engW : pracList) {
                if (dn.equals(engW.getEnglish())) {
                    return engW.getWelsh();
                }
            }
        } else {
            for (Word welW : pracList) {
                if (dn.equals(welW.getWelsh())) {
                    return welW.getEnglish();
                }
            }
        }
        return null;
    }
    /**
     * Method that handles the code when the user presses the submit button.
     * Adds score and checks the links between nodes.
     * Displays the Prompt with the correct answers
     */
    @FXML
    public void submit() {
        submit.setOnMouseClicked((MouseEvent s) -> {
            StringBuilder sb = new StringBuilder("Correct answers: \n");
            for (int i = 0; i < dn_l.size(); i++) {
                if (dn_l.get(i).word.getText().equals(inPracList(dn_r.get(i).word.getText()))) {
                    scores++;
                    dn_l.get(i).word.setFill(Color.GREEN);
                    dn_r.get(i).word.setFill(Color.GREEN);
                }else{
                    dn_l.get(i).word.setFill(Color.RED);
                    dn_r.get(i).word.setFill(Color.RED);
                }
            }
            for(int i= 0 ; i<left.length;i++){
                sb.append(left[i].word.getText()).append(" should be connected with ").append(inPracListSameLang(left[i].word.getText())).append("\n");
                if(!left[i].is_connected()){
                    left[i].word.setFill(Color.RED);
                }
                if(!right[i].is_connected()){
                    right[i].word.setFill(Color.RED);
                }
            }
            score.setText("Score: " + scores + "/" + currNo);
            reset.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ThirdTestPrompt.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Prompt st = loader.getController();
            st.prompt.setText(sb.toString());
            window = new Stage();
            assert root != null;
            Scene promptScene = new Scene(root);
            window.setScene(promptScene);
            window.showAndWait();
        });
    }

    /**
     *   Method that handles the code when the user clicks the reset button, resetting all the links that have been drawn.
     */
    @FXML
    public void resetLinks() {
        reset.setOnMouseClicked((MouseEvent s) -> {
            dn_l.clear();
            dn_r.clear();
            for (DraggableNode d : left) {
                d.mLinkIds.clear();
                d.set_connected(false);
            }
            for (DraggableNode d : right) {
                d.mLinkIds.clear();
                d.set_connected(false);
            }
            for (NodeLink nodeLink : nl) {
                nodeLink.setVisible(false);
            }
        });
    }

    /**
     * Method to initialize all nodes in the ThirdTest view
     */
    @FXML
    private void initialize() {

        //Add one icon that will be used for the drag-drop process
        //This is added as a child to the root anchorpane so it can be visible
        //on both sides of the split pane.
        mDragOverIcon = new DragIcon();

        mDragOverIcon.setVisible(false);
        mDragOverIcon.setOpacity(0.65);
        getChildren().add(mDragOverIcon);

        left[0] = node1;
        left[1] = node2;
        left[2] = node3;
        left[3] = node4;

        node1.setIn_left(true);
        node2.setIn_left(true);
        node3.setIn_left(true);
        node4.setIn_left(true);

        right[0] = nodee1;
        right[1] = nodee2;
        right[2] = nodee3;
        right[3] = nodee4;

        right_pane.getChildren().add(node1);
        right_pane.getChildren().add(node2);
        right_pane.getChildren().add(node3);
        right_pane.getChildren().add(node4);

        right_pane.getChildren().add(nodee1);
        right_pane.getChildren().add(nodee2);
        right_pane.getChildren().add(nodee3);
        right_pane.getChildren().add(nodee4);

        node1.relocateToPoint(new Point2D(100.0, 50.0));
        node2.relocateToPoint(new Point2D(100.0, 150.0));
        node3.relocateToPoint(new Point2D(100.0, 250.0));
        node4.relocateToPoint(new Point2D(100.0, 350.0));

        nodee1.relocateToPoint(new Point2D(400.0, 50.0));
        nodee2.relocateToPoint(new Point2D(400.0, 150.0));
        nodee3.relocateToPoint(new Point2D(400.0, 250.0));
        nodee4.relocateToPoint(new Point2D(400.0, 350.0));
        System.out.println(node1.connected);
        resetLinks();
        buildDragHandlers();
        submit();
    }

    // Contains all the drag event handlers.
    private void buildDragHandlers() {

        mIconDragOverRoot = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());

                //turn on transfer mode and track in the right-pane's context
                //if (and only if) the mouse cursor falls within the right pane's bounds.
                if (!right_pane.boundsInLocalProperty().get().contains(p)) {

                    event.acceptTransferModes(TransferMode.ANY);
                    mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    return;
                }

                event.consume();
            }
        };

        mIconDragOverRightPane = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);

                //convert the mouse coordinates to scene coordinates,
                //then convert back to coordinates that are relative to
                //the parent of mDragIcon.  Since mDragIcon is a child of the root
                //pane, coodinates must be in the root pane's coordinate system to work
                //properly.
                mDragOverIcon.relocateToPoint(
                        new Point2D(event.getSceneX(), event.getSceneY())
                );
                event.consume();
            }
        };

        mIconDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                DragContainer container =
                        (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

                container.addData("scene_coords",
                        new Point2D(event.getSceneX(), event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                content.put(DragContainer.AddNode, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
            }
        };

        this.setOnDragDone(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                right_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRightPane);
                right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
                base_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRoot);

                mDragOverIcon.setVisible(false);

                DragContainer container =
                        (DragContainer) event.getDragboard().getContent(DragContainer.AddLink);

                if (container != null) {

                    //bind the ends of our link to the nodes whose id's are stored in the drag container
                    String sourceId = container.getValue("source");
                    String targetId = container.getValue("target");

                    if (sourceId != null && targetId != null) {

                        //	System.out.println(container.getData());
                        NodeLink link = new NodeLink();

                        //add our link at the top of the rendering order so it's rendered first
                        right_pane.getChildren().add(0, link);

                        DraggableNode source = null;
                        DraggableNode target = null;

                        for (Node n : right_pane.getChildren()) {

                            if (n.getId() == null)
                                continue;

                            if (n.getId().equals(sourceId))
                                source = (DraggableNode) n;

                            if (n.getId().equals(targetId))
                                target = (DraggableNode) n;
                        }

                        if (source != null && target != null)
                            link.bindEnds(source, target);
                        nl.add(link);
                    }
                }
                event.consume();
            }
        });
    }

    public void setEnglish(boolean lang) {
        isEnglish = lang;
    }

    /**
     * Method that adds the words to the sections that the word will be displayed to the user.
     * @param w
     */
    public void setTranslateWord(ArrayList<Word> w) {

        if (isEnglish) {
            node1.word.setText(w.get(0).getEnglish());
            node2.word.setText(w.get(1).getEnglish());
            node3.word.setText(w.get(2).getEnglish());
            node4.word.setText(w.get(3).getEnglish());
            Collections.shuffle(w);
            nodee1.word.setText(w.get(0).getWelsh());
            nodee2.word.setText(w.get(1).getWelsh());
            nodee3.word.setText(w.get(2).getWelsh());
            nodee4.word.setText(w.get(3).getWelsh());
        } else {
            node1.word.setText(w.get(0).getWelsh());
            node2.word.setText(w.get(1).getWelsh());
            node3.word.setText(w.get(2).getWelsh());
            node4.word.setText(w.get(3).getWelsh());
            Collections.shuffle(w);
            nodee1.word.setText(w.get(0).getEnglish());
            nodee2.word.setText(w.get(1).getEnglish());
            nodee3.word.setText(w.get(2).getEnglish());
            nodee4.word.setText(w.get(3).getEnglish());
        }

    }

    public void setCurrScore(int newScore, int no) {
        currScore = newScore;
        currNo = no;
        score.setText("Score: " + currScore + "/" + currNo);
    }

    public int isCorrect() {
        return scores;
    }
}
