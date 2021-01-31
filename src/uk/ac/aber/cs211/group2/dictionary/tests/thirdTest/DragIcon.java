package uk.ac.aber.cs211.group2.dictionary.tests.thirdTest;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author Joel Graff - https://github.com/joelgraff
 */
public class DragIcon extends AnchorPane {


    public DragIcon() {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/DragIcon.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void relocateToPoint(Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate(
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }
}
