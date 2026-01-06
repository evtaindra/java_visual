import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {       
        Character Evta = new Character("Evta Indra", 100, 1);
        Label nameLabel = new Label("Name: " + Evta.showName());
        Label levelLabel = new Label("Level: "+ Evta.showLevel());
        Label healthLabel = new Label("Health: " + Evta.showHealth());
        Button btn = new Button("Level Up");
        btn.setOnAction(e -> {
            Evta.upLevel();
            levelLabel.setText("Level Bertambah menjadi: " + Evta.showLevel());
        });


       //StackPane adalah layout yang menumpuk elemen-elemen UI di atas satu sama lain
        StackPane root = new StackPane();
        root.getChildren().add(nameLabel);
        root.getChildren().add(levelLabel);
        root.getChildren().add(healthLabel);
        root.getChildren().add(btn);

        //Atur posisi levelLabel di bawah nameLabel
        StackPane.setAlignment(nameLabel, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(nameLabel, new javafx.geometry.Insets(30, 0, 0, 0));

        StackPane.setAlignment(levelLabel, javafx.geometry.Pos.CENTER);  
        StackPane.setMargin(levelLabel, new javafx.geometry.Insets(60, 0, 0, 0));

        StackPane.setAlignment(healthLabel, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(healthLabel, new javafx.geometry.Insets(90, 0, 0, 0));

        StackPane.setAlignment(btn, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(btn, new javafx.geometry.Insets(120, 0, 0, 0));

        //Scene adalah wadah utama untuk semua konten di dalam jendela aplikasi
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Character Info");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){

        //untuk menjalankan aplikasi, buka terminal dan ketikan perintah berikut
        // javac --module-path "D:\perkuliahan2025\Java_visual\javafx-sdk-21.0.9\lib" --add-modules javafx.controls Main.java

        //setelah tidak ada Error lalu jalankan perintah berikut
        //java --module-path "D:\perkuliahan2025\Java_visual\javafx-sdk-21.0.9\lib" --add-modules javafx.controls Main
        launch(args);
    }
     
}