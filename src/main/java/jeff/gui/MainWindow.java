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
import jeff.commands.CommandResult;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/Jeff.png"));

    /**
     * Initializes the GUI components after FXML loading.
     * <p>
     * Sets up automatic scrolling, custom scroll behavior, and shows/hides the
     * scroll-to-bottom button based on user scrolling.
     */
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

    /**
     * Injects the Jeff instance to allow GUI to interact with the backend.
     *
     * @param j the Jeff instance to use
     */
    public void setJeff(Jeff j) {
        jeff = j;
        dialogContainer.getChildren().add(
                DialogBox.getJeffDialog(jeff.getGreeting(), jeffImage)
        );
    }

    /**
     * Handles user input from the text field or send button.
     * <p>
     * Creates dialog boxes for both the user's input and Jeff's response,
     * adds them to the dialog container, and clears the text field.
     * <p>
     * If the {@link CommandResult} indicates an error, displays an error dialog with a shake animation.
     * If the command indicates exit, disables input and exits the application after a delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        CommandResult result = jeff.getResponse(input);
        String response = result.getMessage();

        if (result.getIsError()) {
            DialogBox errorDialog = DialogBox.getErrorDialog(response, jeffImage);
            errorDialog.shake();
            dialogContainer.getChildren().add(errorDialog);
        } else if (result.getIsHelp()) {
            dialogContainer.getChildren().add(
                    DialogBox.getHelpDialog(response, jeffImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getJeffDialog(response, jeffImage)
            );
        }

        userInput.clear();
        if (result.getIsExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Scrolls the dialog container to the bottom.
     */
    @FXML
    private void scrollToBottom() {
        scrollPane.setVvalue(1.0);
    }
}
