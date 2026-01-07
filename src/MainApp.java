import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class MainApp extends Application {

    // Stats karakter
    private String name = "Steve";
    private int health = 20;
    private int level = 1;

    // Component yang perlu di-update terus (jadi field class!)
    private Label nameLabel;
    private ProgressBar healthBar;
    private Label healthTextLabel;
    private Label levelLabel;

    // Helper method biar kode event lebih rapi
    private void updateHealthDisplay() {
        double progress = (double) health / 20.0;
        healthBar.setProgress(progress);
        healthTextLabel.setText("Health: " + health + "/20 ❤️");

        // Ganti warna dinamis kayak hunger bar Minecraft
        if (health <= 8) {
            healthBar.setStyle("-fx-accent: red;");
        } else if (health <= 12) {
            healthBar.setStyle("-fx-accent: orange;");
        } else {
            healthBar.setStyle("-fx-accent: lime;");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(25));

        // Row 0: Input nama
        grid.add(new Label("Nama Karakter:"), 0, 0);
        TextField nameField = new TextField(name);
        grid.add(nameField, 1, 0);
        Button applyNameBtn = new Button("Apply Name");
        grid.add(applyNameBtn, 2, 0);

        // Row 1: Nama display
        nameLabel = new Label("Nama: " + name);
        grid.add(nameLabel, 0, 1, 3, 1);

        // Row 2: Health bar
        grid.add(new Label("Health:"), 0, 2);
        healthBar = new ProgressBar(1.0);  // Full awalnya
        healthBar.setPrefWidth(300);
        healthBar.setStyle("-fx-accent: lime;");
        grid.add(healthBar, 1, 2, 2, 1);

        // Row 3: Health text
        healthTextLabel = new Label("Health: " + health + "/20 ❤️");
        grid.add(healthTextLabel, 0, 3, 3, 1);

        // Row 4: Level
        levelLabel = new Label("Level: " + level);
        grid.add(levelLabel, 0, 4, 3, 1);

        // Row 5: Buttons
        Button levelUpBtn = new Button("Level Up ⚡");
        Button damageBtn = new Button("Take Damage ☠");
        Button healPotionBtn = new Button("Heal Potion ❤️");

        grid.add(levelUpBtn, 0, 5);
        grid.add(damageBtn, 1, 5);
        grid.add(healPotionBtn, 2, 5);

        // === EVENT HANDLING ===

        applyNameBtn.setOnAction(e -> {
            name = nameField.getText().trim();
            if (name.isEmpty()) name = "Steve";
            nameLabel.setText("Nama: " + name);
        });

        levelUpBtn.setOnAction(e -> {
            level++;
            levelLabel.setText("Level: " + level);
            health = 20;
            updateHealthDisplay();

           // AudioClip levelUpSound = new AudioClip("https://www.soundjay.com/buttons/button-8.mp3");
            AudioClip levelUpSound = new AudioClip(
                getClass().getResource("/sounds/button-2.mp3").toExternalForm()
            );
            levelUpSound.play();
        });

        damageBtn.setOnAction(e -> {
            health -= 4;
            if (health < 0) health = 0;
            updateHealthDisplay();

            if (health == 0) {
                level = 1;
                levelLabel.setText("Level: " + level);
            }

            //AudioClip damageSound = new AudioClip("https://www.soundjay.com/buttons/button-2.mp3");
            AudioClip damageSound = new AudioClip(
                getClass().getResource("/sounds/beep-10.mp3").toExternalForm()
            );
            damageSound.play();
        });

        healPotionBtn.setOnAction(e -> {
            health += 10;
            if (health > 20) health = 20;
            updateHealthDisplay();
        });

        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setTitle("Minecraft Character Manager 🧱");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}