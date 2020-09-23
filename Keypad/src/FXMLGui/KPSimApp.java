package FXMLGui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class KPSimApp extends Application {

    @Override
    public void start(Stage primaryStage){
        Button button = new Button("Start Simulation");
        button.setPrefSize(200,50);
        button.setTextFill(Color.WHITESMOKE);
        button.setFont(new Font(15));
        button.setStyle("-fx-background-color: #a8a8a8");

        BorderPane pane = new BorderPane();
        pane.setPrefSize(1000,550);

        Image image = new Image(getClass().getResource("/simulation.jpg").toString());
        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));

        pane.setCenter(button);

        Scene scene = new Scene(pane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    BorderPane root = (BorderPane)loader.load(getClass().getResource("SimGUI.fxml").openStream());
                    KPSimController kpSimController = loader.getController();
                    kpSimController.setStage(primaryStage);
                    primaryStage.setScene(new Scene(root));
                    //primaryStage.show();
                    kpSimController.setStageClose();
                }catch (Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args){
        launch(args);
    }

}
