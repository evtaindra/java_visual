import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {       
        Character Evta = new Character("Evta Indra", 100, 1);
        Label nameLabel = new Label("Name: " + Evta.showName());

        StackPane root = new StackPane();
        root.getChildren().add(nameLabel);

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