// MainApp.java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);

        Label lblName = new Label("Nama Karakter:");
        TextField tfName = new TextField();
        tfName.setPromptText("contoh: Steve");

        Label lblHealth = new Label("Health Awal:");
        TextField tfHealth = new TextField();
        tfHealth.setPromptText("100");

        Label lblLevel = new Label("Level Awal:");
        TextField tfLevel = new TextField();
        tfLevel.setPromptText("1");

        Button btnCreate = new Button("Create Character!");
        btnCreate.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        Label lblResult = new Label("Karakter belum dibuat...");
        lblResult.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

        grid.add(lblName, 0, 0);
        grid.add(tfName, 1, 0);
        grid.add(lblHealth, 0, 1);
        grid.add(tfHealth, 1, 1);
        grid.add(lblLevel, 0, 2);
        grid.add(tfLevel, 1, 2);
        grid.add(btnCreate, 1, 3);
        grid.add(lblResult, 0, 4, 2, 1);

        btnCreate.setOnAction(e -> {
            try {
                String name = tfName.getText().trim();
                if (name.isEmpty()) throw new Exception();
                int health = Integer.parseInt(tfHealth.getText());
                int level = Integer.parseInt(tfLevel.getText());

                Character newChar = new Character(name, health, level);
                lblResult.setText("Berhasil buat karakter!\n" + newChar.displayInfo());
                lblResult.setStyle("-fx-text-fill: lime; -fx-font-weight: bold");

                tfName.clear();
                tfHealth.clear();
                tfLevel.clear();
            } catch (Exception ex) {
                lblResult.setText("Error bro! Nama wajib diisi & health/level harus angka!");
                lblResult.setStyle("-fx-text-fill: red;");
            }
        });

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setTitle("Minecraft Character Manager - Day 5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}