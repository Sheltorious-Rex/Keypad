package FXMLGui;

import Components.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;


public class KPSimController {
    @FXML private GridPane gpKeypadOne;
    @FXML private GridPane gpKeypadTwo;
    @FXML private Pane pDoor;
    @FXML private ImageView imageView;

    @FXML private Circle circleRedOne;
    @FXML private Circle circleYellowOne;
    @FXML private Circle circleGreenOne;
    @FXML private Button button1One;
    @FXML private Button button2One;
    @FXML private Button button3One;
    @FXML private Button button4One;
    @FXML private Button button5One;
    @FXML private Button button6One;
    @FXML private Button button7One;
    @FXML private Button button8One;
    @FXML private Button button9One;
    @FXML private Button buttonStarOne;
    @FXML private Button button0One;
    @FXML private Button buttonPoundOne;

    @FXML private Circle circleRedTwo;
    @FXML private Circle circleYellowTwo;
    @FXML private Circle circleGreenTwo;
    @FXML private Button button1Two;
    @FXML private Button button2Two;
    @FXML private Button button3Two;
    @FXML private Button button4Two;
    @FXML private Button button5Two;
    @FXML private Button button6Two;
    @FXML private Button button7Two;
    @FXML private Button button8Two;
    @FXML private Button button9Two;
    @FXML private Button buttonStarTwo;
    @FXML private Button button0Two;
    @FXML private Button buttonPoundTwo;

    private List<Button> buttonListOne;
    private List<Button> buttonListTwo;

    private Stage stage;

    /**
     * The initializer for this controller.
     */
    @FXML
    public void initialize(){
        //Lets add imageBackgrounds to our keypads!
        Image image = new Image(getClass().getResource("/keypadImage.jpeg").toString());
        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        gpKeypadOne.setBackground(new Background(myBI));
        gpKeypadTwo.setBackground(new Background(myBI));

        Image imageClose = new Image(getClass().getResource("/IMG_2657.JPG").toString());
        imageView.setImage(imageClose);

        //todo we can use this or Mike's design which is just pass an int to recognize number.
        String sDCardOnePath = "/Users/luismartinez/IdeaProjects/MyKeypad/Memory/SD_Card.txt";
        String sDCardTwoPath = "/Users/luismartinez/IdeaProjects/MyKeypad/Memory/SD_Card2.txt";

        Circle[] circlesOne = new Circle[]{circleRedOne,circleYellowOne,circleGreenOne};
        Circle[] circlesTwo = new Circle[]{circleRedTwo,circleYellowTwo,circleGreenTwo};

        Controller controllerOne = new Controller(imageView,circlesOne,1);
        Thread threadOne = new Thread(controllerOne);
        threadOne.setDaemon(true);

        Controller controllerTwo = new Controller(imageView,circlesTwo,2);
        Thread threadTwo = new Thread(controllerTwo);
        threadTwo.setDaemon(true);

        setButtonColors();
        setButtonsAction(controllerOne.getKeyQueue().getKeyListener(),
                controllerTwo.getKeyQueue().getKeyListener());
        threadOne.start();
        threadTwo.start();
    }

    /**
     * Adds the buttons to list for easy manipulation. We change colors here as well.
     */
    private void setButtonColors(){
        buttonListOne = new ArrayList<>();
        buttonListOne.add(button0One);
        buttonListOne.add(button1One);
        buttonListOne.add(button2One);
        buttonListOne.add(button3One);
        buttonListOne.add(button4One);
        buttonListOne.add(button5One);
        buttonListOne.add(button6One);
        buttonListOne.add(button7One);
        buttonListOne.add(button8One);
        buttonListOne.add(button9One);
        buttonListOne.add(buttonStarOne);
        buttonListOne.add(buttonPoundOne);

        buttonListTwo = new ArrayList<>();
        buttonListTwo.add(button0Two);
        buttonListTwo.add(button1Two);
        buttonListTwo.add(button2Two);
        buttonListTwo.add(button3Two);
        buttonListTwo.add(button4Two);
        buttonListTwo.add(button5Two);
        buttonListTwo.add(button6Two);
        buttonListTwo.add(button7Two);
        buttonListTwo.add(button8Two);
        buttonListTwo.add(button9Two);
        buttonListTwo.add(buttonStarTwo);
        buttonListTwo.add(buttonPoundTwo);

        for (int i = 0; i < 12; i++) {
            buttonListOne.get(i).setStyle("-fx-background-color: #a8a8a8");
            buttonListTwo.get(i).setStyle("-fx-background-color: #a8a8a8");
        }
    }

    /**
     * Sets the event actions of our buttons.
     * @param kListenerOne
     * @param kListenerTwo
     */
    private void setButtonsAction(EventHandler<ActionEvent> kListenerOne,
                                  EventHandler<ActionEvent> kListenerTwo){
        for (int i = 0; i < 12; i++) {
            Button button = buttonListOne.get(i);
            button.setOnAction(kListenerOne);

            Button button2 = buttonListTwo.get(i);
            button2.setOnAction(kListenerTwo);
        }
    }

    /**
     * Sets the stage of this controller.
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }


    /**
     * This is something used to set behavior when exiting the GUI.
     */
    public void setStageClose(){
        //Stage stage = (Stage) gpKeypadOne.getScene().getWindow();
        stage.setOnCloseRequest((WindowEvent e) -> {
            try {
                //System.out.println("exiting system");
                stage.close();
                System.exit(0);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }
}
