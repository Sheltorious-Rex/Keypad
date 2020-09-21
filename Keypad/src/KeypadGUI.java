import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class KeypadGUI extends Application{

    private HBox mainBox;
    private Stage primaryStage;
    private final EventHandler<ActionEvent> KEY_LISTENER_1;
    private final EventHandler<ActionEvent> KEY_LISTENER_2;
    private Controller controller1;
    private Controller controller2;
    Button[] kp1_keys;
    Button[] kp2_keys;
    Color GREY = Color.valueOf("DARKSLATEGREY");

    KeypadGUI (Controller controller1, Controller controller2, Stage primaryStage){
//        System.out.println("KeypadGUI constructor");

        this.primaryStage = primaryStage;
        this.controller1 = controller1;
        this.controller2 = controller2;
        KEY_LISTENER_1 = controller1.getKey().getKeyListener();
        KEY_LISTENER_2 = controller2.getKey().getKeyListener();

        try {

            this.start(this.primaryStage);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        initUI(this.primaryStage);

    }

    private void initUI(Stage stage) {

//		System.out.println("initUI");

        mainBox = new HBox(3);

        //Make the keypads
        mainBox.getChildren().add(makeKeyPad1());
        mainBox.getChildren().add(makeLock());
        mainBox.getChildren().add(makeKeyPad2());

        //Get reference to the key listener
        controller1.setMainBox(mainBox, 1);
        controller2.setMainBox(mainBox,2);

        Thread controllerThread1 = new Thread(controller1);
        Thread controllerThread2 = new Thread(controller2);
        controllerThread1.start();
        controllerThread2.start();

        Scene scene = new Scene(mainBox, 280, 200);

        stage.setTitle("Keypad");
        stage.setScene(scene);
        stage.show();
    }

    private VBox makeKeyPad1() {

        VBox kp1Box = new VBox(2);
        kp1Box.getChildren().add(makeKP1Lights());
        makeKP1Buttons();
        kp1Box.getChildren().add(formatKP1Buttons());
        return kp1Box;
    }
    private HBox makeKP1Lights() {
        HBox lightBox = new HBox(3);
        lightBox.setAlignment(Pos.CENTER);
        double lightRadius = 10;

//        Circle redLight = new Circle(lightRadius, RED);
//        Circle yellowLight = new Circle(lightRadius, YELLOW);
//        Circle greenLight = new Circle(lightRadius, GREEN);

        Circle redLight = new Circle(lightRadius, GREY);
        Circle yellowLight = new Circle(lightRadius, GREY);
        Circle greenLight = new Circle(lightRadius, GREY);

        lightBox.getChildren().addAll(redLight,yellowLight,greenLight);
        return lightBox;
    }

    private void makeKP1Buttons() {
        //0-9 keys ,10 = *, 11 = #
        kp1_keys = new Button[12];

        // Make keys 0-9
        for( int i = 0; i<10 ; i++) {
            Button btn = new Button();
            btn.setText(Integer.toString(i));

            btn.setOnAction(KEY_LISTENER_1);

            kp1_keys[i] = btn;
        }

        // Make * button
        Button starBtn = new Button();
        starBtn.setText("*");
        starBtn.setOnAction(KEY_LISTENER_1);

        kp1_keys[10] = starBtn;

        // Make # button
        Button poundBtn = new Button();
        poundBtn.setText("#");
        poundBtn.setOnAction(KEY_LISTENER_1);
        kp1_keys[11] = poundBtn;
    }

    private VBox formatKP1Buttons() {
        VBox keyBox = new VBox(4);
        HBox firstRow = new HBox(3);
        HBox secondRow = new HBox(3);
        HBox thirdRow = new HBox(3);
        HBox fourthRow = new HBox(3);

        keyBox.setAlignment(Pos.CENTER);
        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);
        fourthRow.setAlignment(Pos.CENTER);

        for(int i=1; i<4; i++) {
            firstRow.getChildren().add(kp1_keys[i]);
        }
        for(int i=1; i<4; i++) {
            secondRow.getChildren().add(kp1_keys[i+3]);
        }
        for(int i=1; i<4; i++) {
            thirdRow.getChildren().add(kp1_keys[i+6]);
        }

        fourthRow.getChildren().add(kp1_keys[10]);
        fourthRow.getChildren().add(kp1_keys[0]);
        fourthRow.getChildren().add(kp1_keys[11]);

        keyBox.getChildren().addAll(firstRow,secondRow,thirdRow,fourthRow);
        return keyBox;
    }

    private HBox makeLock() {
        HBox lockBox = new HBox(1);
        lockBox.setAlignment(Pos.CENTER);
        Label lockLabel = new Label("Lock Status");
        lockLabel.setStyle("-fx-text-fill: white");
        lockLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY,
                Insets.EMPTY)));
        lockBox.getChildren().add(lockLabel);

        return lockBox;

    }

    private VBox makeKeyPad2() {

        VBox kp2Box = new VBox(2);
        kp2Box.getChildren().add(makeKP2Lights());
        makeKP2Buttons();
        kp2Box.getChildren().add(formatKP2Buttons());
        return kp2Box;
    }
    private HBox makeKP2Lights() {
        HBox lightBox = new HBox(3);
        lightBox.setAlignment(Pos.CENTER);
        double lightRadius = 10;


        Circle redLight = new Circle(lightRadius, GREY);
        Circle yellowLight = new Circle(lightRadius, GREY);
        Circle greenLight = new Circle(lightRadius, GREY);

        lightBox.getChildren().addAll(redLight,yellowLight,greenLight);
        return lightBox;
    }
    private void makeKP2Buttons() {
        //0-9 keys ,10 = *, 11 = #
        kp2_keys = new Button[12];

        // Make keys 0-9
        for( int i = 0; i<10 ; i++) {
            Button btn = new Button();
            btn.setText(Integer.toString(i));

            btn.setOnAction(KEY_LISTENER_2);

            kp2_keys[i] = btn;
        }

        // Make * button
        Button starBtn = new Button();
        starBtn.setText("*");
        starBtn.setOnAction(KEY_LISTENER_2);
        kp2_keys[10] = starBtn;

        // Make # button
        Button poundBtn = new Button();
        poundBtn.setText("#");
        poundBtn.setOnAction(KEY_LISTENER_2);
        kp2_keys[11] = poundBtn;
    }

    private VBox formatKP2Buttons() {
        VBox keyBox = new VBox(4);
        HBox firstRow = new HBox(3);
        HBox secondRow = new HBox(3);
        HBox thirdRow = new HBox(3);
        HBox fourthRow = new HBox(3);

        keyBox.setAlignment(Pos.CENTER);
        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);
        fourthRow.setAlignment(Pos.CENTER);


        for(int i=1; i<4; i++) {
            firstRow.getChildren().add(kp2_keys[i]);
        }
        for(int i=1; i<4; i++) {
            secondRow.getChildren().add(kp2_keys[i+3]);
        }
        for(int i=1; i<4; i++) {
            thirdRow.getChildren().add(kp2_keys[i+6]);
        }

        fourthRow.getChildren().add(kp2_keys[10]);
        fourthRow.getChildren().add(kp2_keys[0]);
        fourthRow.getChildren().add(kp2_keys[11]);

        keyBox.getChildren().addAll(firstRow,secondRow,thirdRow,fourthRow);
        return keyBox;
    }
}
