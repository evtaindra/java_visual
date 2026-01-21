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
    private final int playerMaxHealth = 20;
    private int playerLevel = 1;

    private String enemyName = "Zombie";
    private int enemyHealth = 30;
    private final int enemyMaxHealth = 30;
    private int enemyLevel = 1;

    // Components
    private ProgressBar playerHealthBar;
    private Label playerHealthText;
    private ProgressBar enemyHealthBar;
    private Label enemyHealthText;
    private Label statusLabel;
    private Label playerLevelLabel;
    private Label enemyLevelLabel;

    private void updateHealthDisplays() {
        // Update Steve
        double playerProg = (double) playerHealth / playerMaxHealth;
        playerHealthBar.setProgress(playerProg);
        playerHealthText.setText("Health: " + playerHealth + "/" + playerMaxHealth + " ❤️");
        playerHealthBar.setStyle(playerHealth <= 8 ? "-fx-accent: red;" :
                playerHealth <= 12 ? "-fx-accent: orange;" : "-fx-accent: lime;");

        // Update Zombie
        double enemyProg = (double) enemyHealth / enemyMaxHealth;
        enemyHealthBar.setProgress(enemyProg);
        enemyHealthText.setText("Health: " + enemyHealth + "/" + enemyMaxHealth + " 💀");
        enemyHealthBar.setStyle(enemyHealth <= 12 ? "-fx-accent: red;" :
                enemyHealth <= 18 ? "-fx-accent: orange;" : "-fx-accent: lime;");

        // Cek winner
        if (playerHealth <= 0) {
            statusLabel.setText("Zombie Wins! Steve kalah 😭");
            enemyLevel++;
            enemyLevelLabel.setText("Level: " + enemyLevel);
        } else if (enemyHealth <= 0) {
            statusLabel.setText("Steve Wins! Level up! 🎉");
            playerLevel++;
            playerLevelLabel.setText("Level: " + playerLevel);
        }
    }

    private void showVictoryDialog(String message) {
        Alert victory = new Alert(Alert.AlertType.INFORMATION);
        victory.setTitle("Victory!");
        victory.setHeaderText("Steve Menang!");
        victory.setContentText(message + "\nLevel Steve sekarang: " + playerLevel);
        victory.showAndWait();
    }

    private void showGameOverDialog(String message) {
        Alert gameOver = new Alert(Alert.AlertType.WARNING);
        gameOver.setTitle("Game Over");
        gameOver.setHeaderText("Kalah!");
        gameOver.setContentText(message + "\nLevel Zombie sekarang: " + enemyLevel);
        gameOver.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        grid.setPadding(new Insets(30));

        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");

        MenuItem resetItem = new MenuItem("Reset Battle");
        resetItem.setOnAction(e -> {
            playerHealth = playerMaxHealth;
            enemyHealth = enemyMaxHealth;
            playerLevel = 1;
            enemyLevel = 1;
            statusLabel.setText("Fight!");
            updateHealthDisplays();
            playerLevelLabel.setText("Level: 1");
            enemyLevelLabel.setText("Level: 1");
        });

        MenuItem healSteveItem = new MenuItem("Heal Steve (+10)");
        healSteveItem.setOnAction(e -> {
            if (playerHealth < playerMaxHealth) {
                playerHealth = Math.min(playerHealth + 10, playerMaxHealth);
                updateHealthDisplays();
            }
        });

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());

        gameMenu.getItems().addAll(resetItem, healSteveItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(gameMenu);

        // Gambar karakter
        ImageView steveView = new ImageView(new Image(getClass().getResource("/images/steve.jpg").toExternalForm()));
        steveView.setFitHeight(300);
        steveView.setPreserveRatio(true);
        steveView.setSmooth(true);
        steveView.setStyle("-fx-border-color: #4CAF50; -fx-border-width: 4; -fx-border-radius: 15;");
        steveView.setEffect(new DropShadow(15, Color.BLACK));

        ImageView zombieView = new ImageView(new Image(getClass().getResource("/images/zombie.jpg").toExternalForm()));
        zombieView.setFitHeight(300);
        zombieView.setPreserveRatio(true);
        zombieView.setSmooth(true);
        zombieView.setStyle("-fx-border-color: #F44336; -fx-border-width: 4; -fx-border-radius: 15;");
        zombieView.setEffect(new DropShadow(15, Color.BLACK));

        VBox steveBox = new VBox(15, new Label("Steve"), steveView, playerLevelLabel = new Label("Level: 1"),
                playerHealthBar = new ProgressBar(1.0), playerHealthText = new Label("Health: 20/20 ❤️"));
        steveBox.setAlignment(Pos.CENTER);
        steveBox.setPadding(new Insets(20));

        VBox zombieBox = new VBox(15, new Label("Zombie"), zombieView, enemyLevelLabel = new Label("Level: 1"),
                enemyHealthBar = new ProgressBar(1.0), enemyHealthText = new Label("Health: 30/30 💀"));
        zombieBox.setAlignment(Pos.CENTER);
        zombieBox.setPadding(new Insets(20));

        grid.add(steveBox, 0, 0);
        grid.add(zombieBox, 2, 0);

        statusLabel = new Label("Fight!");
        statusLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        Button fightBtn = new Button("Fight! ⚔️");
        grid.add(statusLabel, 1, 1);
        grid.add(fightBtn, 1, 2);

        VBox root = new VBox(menuBar, grid);
        root.setSpacing(10);

        // Event Fight dengan konfirmasi
        fightBtn.setOnAction(e -> {
            if (playerHealth <= 0 || enemyHealth <= 0) return;

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Konfirmasi Serangan");
            confirm.setHeaderText("Yakin mau fight?");
            confirm.setContentText("Steve dan Zombie bakal saling serang! Ini bisa berbahaya bro.");

            ButtonType yes = new ButtonType("Yes, Fight!");
            ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirm.getButtonTypes().setAll(yes, no);

            confirm.showAndWait().ifPresent(response -> {
                if (response == yes) {
                    enemyHealth -= 5;
                    if (enemyHealth < 0) enemyHealth = 0;

                    playerHealth -= 4;
                    if (playerHealth < 0) playerHealth = 0;

                    updateHealthDisplays();

                    AudioClip damageSound = new AudioClip(getClass().getResource("/sounds/beep-10.mp3").toExternalForm());
                    damageSound.play();

                    if (playerHealth <= 0) {
                        showGameOverDialog("Zombie Wins! Steve kalah 😭");
                    } else if (enemyHealth <= 0) {
                        showVictoryDialog("Steve Wins! Level up! 🎉");
                    }
                }
            });
        });

        Scene scene = new Scene(root, 900, 750);
        primaryStage.setTitle("Minecraft Character Battle 🧟 vs 🧑");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}