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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private VBox contentContainer;
    @FXML
    private ImageView displayPicture;

    private DialogBox(Node content, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentContainer.getChildren().clear();
        contentContainer.getChildren().add(content); // add any node
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
        Label label = new Label(text);
        label.setId("text");
        label.setWrapText(true);

        DialogBox db = new DialogBox(label, img);
        db.getStyleClass().add("user-dialog");

        VBox.setMargin(db, new Insets(2, 30, 2, 30));
        return db;
    }

    public static DialogBox getJeffDialog(String text, Image img) {
        Label label = new Label(text);
        label.setId("text");
        label.setWrapText(true);

        DialogBox db = new DialogBox(label, img);
        db.flip();

        VBox.setMargin(db, new Insets(2, 30, 2, 30));
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = getJeffDialog(text, img);
        db.getStyleClass().add("error-dialog");
        return db;
    }

    public static DialogBox getHelpDialog(String text, Image img) {
        String[] lines = text.split("\n");

        Label label = new Label(lines[0]);
        label.setId("text");
        label.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" - ", 2);
            String command = parts[0].trim();
            String description = parts.length > 1 ? parts[1].trim() : "";

            Label commandLabel = new Label(command);
            commandLabel.setWrapText(true);
            commandLabel.getStyleClass().add("help-label");
            Label descLabel = new Label(description);
            descLabel.setWrapText(true);
            descLabel.getStyleClass().add("help-desc");

            grid.add(commandLabel, 0, i);
            grid.add(descLabel, 1, i);
        }

        VBox container = new VBox(5);
        container.getChildren().addAll(label, grid);

        DialogBox db = new DialogBox(container, img);
        db.flip();
        db.getStyleClass().add("help-dialog");

        VBox.setMargin(db, new Insets(2, 30, 2, 30));
        return db;
    }

    /**
     * Shakes the dialog box horizontally to indicate an error.
     */
    public void shake() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), this);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(8);
        tt.setAutoReverse(true);
        tt.play();
    }
}
