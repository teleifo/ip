package jeff.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setWrapText(true);
        this.text.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        VBox.setMargin(db, new Insets(2, 30, 2, 30));
        return db;
    }

    public static DialogBox getJeffDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        VBox.setMargin(db, new Insets(2, 30, 2, 30));
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = getJeffDialog(text, img);
        db.getStyleClass().add("error-dialog");
        return db;
    }

    /**
     * Shakes the dialog box horizontally to indicate an error.
     */
    public void shake() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), this);
        tt.setFromX(0);
        tt.setByX(10);          // move 10px right
        tt.setCycleCount(8);    // 4 times back and forth
        tt.setAutoReverse(true);
        tt.play();
    }
}
