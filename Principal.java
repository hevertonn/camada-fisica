/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 06/09/2026
* Ultima alteracao.: 06/09/2026
* Nome.............: Principal.java
* Funcao...........: Iniciar a interface JavaFX.
*************************************************************** */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.MainViewController;

@SuppressWarnings("unused")
public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent mainView = FXMLLoader.load(getClass().getResource("./view/MainView.fxml"));

    Scene scene = new Scene(mainView, 1280, 720);

    scene.getStylesheets().add("./util/style.css");

    primaryStage.setTitle("Matrix");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}
