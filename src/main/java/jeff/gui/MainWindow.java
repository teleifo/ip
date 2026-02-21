package jeff.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jeff.Jeff;
import jeff.data.exception.JeffException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button scrollButton;

    private Jeff jeff;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/Jeff.png"));

    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            scrollPane.setVvalue(1.0); // scroll to bottom
        });

        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
            dialogContainer.setMinHeight(newVal.getHeight());
        });

        dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * 0.001; // adjust scroll speed
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });

        // Initially hide scroll button (optional)
        scrollButton.setVisible(false);

        // Show scroll button if user scrolls up
        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            scrollButton.setVisible(newVal.doubleValue() < 0.95);
        });
    }

    /** Injects the Jeff instance */
    public void setJeff(Jeff j) {
        jeff = j;
        dialogContainer.getChildren().add(
                DialogBox.getJeffDialog(jeff.getGreeting(), jeffImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jeff's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        try {
            String response = jeff.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getJeffDialog(response, jeffImage)
            );
        } catch (JeffException e) {
            DialogBox errorDialog = DialogBox.getErrorDialog(e.getMessage(), jeffImage);
            errorDialog.shake();
            dialogContainer.getChildren().add(errorDialog);
        }

        userInput.clear();
        if (jeff.getIsExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /** Scrolls the ScrollPane to the bottom */
    @FXML
    private void scrollToBottom() {
        scrollPane.setVvalue(1.0);
    }
}
