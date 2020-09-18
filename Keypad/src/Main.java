import javafx.application.Application;
import javafx.stage.Stage;

//<<<<<<< HEAD
public class Main extends Application {



	@Override
	public void start(Stage primaryStage) {


		AccessCodes accessCodes1 = new AccessCodes();
		KeypadReceiver keypadReceiver1 = new KeyQueue();
		Timer timeoutTimer1 = new Timer();
		Timer loopTimer1 = new Timer();
		Controller controller1 = new Controller(timeoutTimer1, loopTimer1, keypadReceiver1, accessCodes1);
		AccessCodes accessCodes2 = new AccessCodes();
		KeypadReceiver keypadReceiver2 = new KeyQueue();
		Timer timeoutTimer2 = new Timer();
		Timer loopTimer2 = new Timer();
		Controller controller2 = new Controller(timeoutTimer2, loopTimer2, keypadReceiver2, accessCodes2);
		Thread controlThread1 = new Thread(controller1);
		Thread controlThread2 = new Thread(controller2);

		controlThread1.start();
		controlThread2.start();

		KeypadGUI gui = new KeypadGUI(primaryStage, keypadReceiver1, keypadReceiver2);


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
