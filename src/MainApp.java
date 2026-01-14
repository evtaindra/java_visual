import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

    // Stats karakter
    private String playerName = "Steve";
    private int playerHealth = 20;
    private int playerLevel = 1;

    private String enemyName = "Zombie";
    private int enemyHealth = 30;
    private int enemyLevel = 1;  // Zombie juga punya level (opsional)

    // Components
    private ProgressBar playerHealthBar;
    private Label playerHealthText;
    private ProgressBar enemyHealthBar;
    private Label enemyHealthText;
    private Label statusLabel;  // Untuk winner announcement
    private Label playerLevelLabel;
    private Label enemyLevelLabel;

    private void updateHealthDisplays() {
        // Update Steve
        double playerProg = (double) playerHealth / 20.0;
        playerHealthBar.setProgress(playerProg);
        playerHealthText.setText("Health: " + playerHealth + "/20 ❤️");
        playerHealthBar.setStyle(playerHealth <= 8 ? "-fx-accent: red;" : playerHealth <= 12 ? "-fx-accent: orange;" : "-fx-accent: lime;");

        // Update Zombie
        double enemyProg = (double) enemyHealth / 20.0;
        enemyHealthBar.setProgress(enemyProg);
        enemyHealthText.setText("Health: " + enemyHealth + "/20 💀");
        enemyHealthBar.setStyle(enemyHealth <= 8 ? "-fx-accent: red;" : enemyHealth <= 12 ? "-fx-accent: orange;" : "-fx-accent: lime;");

        // Check winner
        if (playerHealth <= 0) {
            statusLabel.setText("Zombie Wins! Steve kalah 😭");
            enemyLevel++;
            enemyLevelLabel.setText("Level: " + enemyLevel);
            // Optional: reset health buat rematch
        } else if (enemyHealth <= 0) {
            statusLabel.setText("Steve Wins! Level up! 🎉");
            playerLevel++;
            playerLevelLabel.setText("Level: " + playerLevel);
            // Optional: reset health
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
      

        // Gambar karakter (ganti URL dengan gambar lo, atau local file)
        //ImageView steveView = new ImageView(new Image("https://www.minecraft.net/content/dam/minecraft/nether-update/steve.png"));  // Contoh URL Steve
        // Jika menggunakan file lokal, bisa seperti ini:
        
        // Gambar Steve
        ImageView steveView = new ImageView(new Image(getClass().getResource("/images/steve.jpg").toExternalForm()));
        steveView.setFitHeight(300);
        steveView.setPreserveRatio(true);
        steveView.setSmooth(true);
        steveView.setStyle("-fx-border-color: #4CAF50; -fx-border-width: 4; -fx-border-radius: 15;");
        steveView.setEffect(new DropShadow(15, Color.BLACK));

        // Gambar Zombie
        ImageView zombieView = new ImageView(new Image(getClass().getResource("/images/zombie.jpg").toExternalForm()));
        zombieView.setFitHeight(300);
        zombieView.setPreserveRatio(true);
        zombieView.setSmooth(true);
        zombieView.setStyle("-fx-border-color: #F44336; -fx-border-width: 4; -fx-border-radius: 15;");
        zombieView.setEffect(new DropShadow(15, Color.BLACK));

        VBox steveBox = new VBox(15, new Label("Steve"), steveView, playerLevelLabel = new Label("Level: 1"), playerHealthBar = new ProgressBar(1.0), playerHealthText = new Label("Health: 20/20 ❤️"));
        steveBox.setAlignment(Pos.CENTER);
        steveBox.setPadding(new Insets(20));

        VBox zombieBox = new VBox(15, new Label("Zombie"), zombieView, enemyLevelLabel = new Label("Level: 1"), enemyHealthBar = new ProgressBar(1.0), enemyHealthText = new Label("Health: 20/20 💀"));
        zombieBox.setAlignment(Pos.CENTER);
        zombieBox.setPadding(new Insets(20));

        grid.add(steveBox, 0, 0);
        grid.add(zombieBox, 2, 0);

        // Status & Button di tengah
        statusLabel = new Label("Fight!");
        statusLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button fightBtn = new Button("Fight! ⚔️");
        grid.add(statusLabel, 1, 0);
        grid.add(fightBtn, 1, 1);

        fightBtn.setOnAction(e -> {
            if (playerHealth > 0 && enemyHealth > 0) {
                // Serangan sederhana: Steve damage Zombie 5, Zombie damage Steve 4
                enemyHealth -= 5;
                if (enemyHealth < 0) enemyHealth = 0;

                playerHealth -= 4;
                if (playerHealth < 0) playerHealth = 0;

                updateHealthDisplays();

                // Sound effect (opsional)
               // new AudioClip("https://www.soundjay.com/buttons/button-2.mp3").play();            
                AudioClip damageSound = new AudioClip(getClass().getResource("/sounds/beep-10.mp3").toExternalForm());
                damageSound.play();
            }
        });

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setTitle("Minecraft Character Battle 🧟 vs 🧑");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
