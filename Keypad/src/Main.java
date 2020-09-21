import javafx.application.Application;
import javafx.stage.Stage;

//<<<<<<< HEAD
public class Main extends Application {



    @Override
    public void start(Stage primaryStage) {

        Controller controller1 = new Controller();
        Controller controller2 = new Controller();
        KeypadGUI demo = new KeypadGUI(controller1,controller2,primaryStage);

    }

    //    public static void main(String[] args){
//        launch(args);
//    }
//=======
//public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		System.out.println("Hello World!");
        launch(args);
    }
    //Testing ability to push.
}
