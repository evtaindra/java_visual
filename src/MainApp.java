import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class MainApp extends Application {

    // Variabel untuk nyimpan stats karakter (mirip inventory)
    private String name = "Steve";
    private int health = 20;
    private int level = 1;

    // Label yang bakal kita update terus
    private Label nameLabel;
    private Label healthLabel;
    private Label levelLabel;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Input nama
        grid.add(new Label("Nama Karakter:"), 0, 0);
        TextField nameField = new TextField(name);
        grid.add(nameField, 1, 0);

        // Button Apply Name
        Button applyNameBtn = new Button("Apply Name");
        grid.add(applyNameBtn, 2, 0);

        // Display stats
        nameLabel = new Label("Nama: " + name);
        healthLabel = new Label("Health: " + health + " ❤️");
        levelLabel = new Label("Level: " + level);

        grid.add(nameLabel, 0, 1, 3, 1);
        grid.add(healthLabel, 0, 2, 3, 1);
        grid.add(levelLabel, 0, 3, 3, 1);

        // Button Level Up & Take Damage
        Button levelUpBtn = new Button("Level Up ⚡");
        Button damageBtn = new Button("Take Damage ☠");

        //Button Heal Potion
        Button healPotionBtn = new Button("Heal Potion ❤️");
      

        grid.add(levelUpBtn, 0, 4);
        grid.add(damageBtn, 1, 4);
        grid.add(healPotionBtn, 2, 4);

        // === EVENT HANDLING DI SINI ===

        // 1. Apply Name button
        applyNameBtn.setOnAction(e -> {
            name = nameField.getText().trim();
            if (name.isEmpty()) name = "Steve";
            nameLabel.setText("Nama: " + name);
        });

        // 2. Level Up button
        levelUpBtn.setOnAction(e -> {
            level++;
            levelLabel.setText("Level: " + level);
            // Bonus kecil: health full lagi pas level up (kayak di game)
            health = 20;
            healthLabel.setText("Health: " + health + " ❤️");
            AudioClip levelUpSound = new AudioClip("https://www.soundjay.com/buttons/button-8.mp3");
            levelUpSound.play();
        });

        
        // 3. Take Damage button
        damageBtn.setOnAction(e -> {
            health -= 4;
            if (health < 0) health = 0;
            healthLabel.setText("Health: " + health + " ❤️");

            // Kalau mati, reset level
            if (health == 0) {
                level = 1;
                levelLabel.setText("Level: " + level);
            }
            AudioClip damageSound = new AudioClip("https://www.soundjay.com/buttons/button-2.mp3");
            damageSound.play();
        });
        // 4. Heal Potion button
        healPotionBtn.setOnAction(e -> {
            health += 10;
            if (health > 20) health = 20;
            healthLabel.setText("Health: " + health + " ❤️");
        });

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setTitle("Minecraft Character Manager 🧱");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}