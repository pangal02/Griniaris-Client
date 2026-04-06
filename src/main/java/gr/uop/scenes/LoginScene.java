package gr.uop.scenes;

import gr.uop.net.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginScene {
    private int port;
    private Label errorLabel = new Label("");

    public Scene create() {
        Label title = new Label("Σύνδεση στον Γκρινιάρη");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Label nameLabel = new Label("Όνομα:");
        TextField nameField = new TextField();
        nameField.setPromptText("Όνομα Παίκτη");
        nameField.setPrefWidth(220);
        nameLabel.setMinWidth(110);
        HBox nameRow = new HBox(10, nameLabel, nameField);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        Label ipLabel = new Label("Διεύθυνση IP:");
        TextField ipField = new TextField("localhost");
        ipField.setPromptText("Διεύθυνση IP");
        ipField.setPrefWidth(220);
        ipLabel.setMinWidth(110);
        HBox ipRow = new HBox(10, ipLabel, ipField);
        ipRow.setAlignment(Pos.CENTER_LEFT);

        Label portLabel = new Label("Θύρα:");
        TextField portField = new TextField("7777");
        portField.setPromptText("Port");
        portField.setPrefWidth(220);
        portLabel.setMinWidth(110);
        HBox portRow = new HBox(10, portLabel, portField);
        portRow.setAlignment(Pos.CENTER_LEFT);

        Button connectBtn = new Button("Σύνδεση");
        connectBtn.setPrefWidth(200);

        connectBtn.setOnAction(event -> {
            String name = nameField.getText().trim();
            String ip = ipField.getText().trim();
            String portStr = portField.getText().trim();
            errorLabel.setStyle("-fx-text-fill: red;");

            if (name.isEmpty()) {
                errorLabel.setText("* Απαιτείται όνομα");
                return;
            }
            if (ip.isEmpty()) {
                errorLabel.setText("Απαιτείται διεύθυνση IP");
                return;
            }
            if (portStr.isEmpty()){
                errorLabel.setText("* Απαιτείται αριθμός θύρας!");
                return;
            }

            try {
                port = Integer.parseInt(portStr);
                // Δημιουργία Client (socket + game engine)
                Client client = new Client(ip, port, name);

                // Δημιουργία Lobby με το client
                LobbyScene lobby = new LobbyScene(client);

                // Αλλαγή σκηνής μέσω SceneManager
                SceneManager.changeScene(lobby.getScene());

            } 
            catch(NumberFormatException nfe){
                System.err.println("Error: " + nfe);
                errorLabel.setText("* Απαιτείται αριθμός port");
            } 
            catch (IOException e) {
                errorLabel.setText("Αποτυχία σύνδεσης: " + e.getMessage());
            }
        });

        VBox formBox = new VBox(12, title, nameRow, ipRow, portRow, connectBtn, errorLabel);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(20));
        formBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");
        formBox.setMaxWidth(400);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(24));
        layout.getChildren().add(formBox);

        return new Scene(layout, 480, 320);
    }
}
