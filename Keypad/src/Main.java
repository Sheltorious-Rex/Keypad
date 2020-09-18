import javafx.application.Application;
import javafx.stage.Stage;

//<<<<<<< HEAD
public class Main extends Application {



	@Override
	public void start(Stage primaryStage) {
		KeyQueue keyPadQueue1 = new KeyQueue();
		KeyQueue keyPadQueue2 = new KeyQueue();
		KeypadGUI gui = new KeypadGUI(primaryStage,keyPadQueue1, keyPadQueue2);
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
