import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * AccessCodes is a class that controls access to the SD Card and
 * also verifies if a user input is equal to either the managerCode
 * or userCode
 */
public class AccessCodes {

    private String managerCode;//the system's manager code
    private String userCode;//the system's user code

    /**
     * Standard operating Constructor. No inputs. Gets codes from SD_Card.txt.
     * The 4 methods that follow are the methods for this constructor.
     * Below the 4 methods, there is an Overloaded version of AccessCodes for
     * the demo, for using two Controllers.
     */
    public AccessCodes(){
        try{
            Scanner scanner = new Scanner(new File("Keypad/resources/SD_Card.txt"));
            /*get the manager and user code from the file. The manager code will
            always be first line, user code will always be second line.*/
            managerCode = scanner.nextLine();//default = "0000"
            userCode = scanner.nextLine();   //default = "9999"
        }
        catch (FileNotFoundException e){
            System.out.println("SD_Card.txt file not found.");
            System.exit(1);
        }
    }
    /**
     * A method to change managerCode.
     * newMgrCode already checked for validity
     * @return boolean
     */
    public boolean changeMgrCode(String newMgrCode){
        try {
            //initialize a new file. Overwrite SD_Card.txt
            FileWriter fileWriter = new FileWriter("Keypad/resources/SD_Card.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new manager code on first line,
            while reserving the system's current user code*/
            printWriter.printf("%s\n%s",newMgrCode,userCode);
            printWriter.close();
            //change manager code in the system
            managerCode = newMgrCode;
        }
        catch (IOException e){
            //if it doesn't work, return false
            System.out.println("Passwords not changed.");
            return false;
        }
        return true;
    }
    /**
     * A method to change userCode.
     * newUserode already checked for validity
     * @return boolean
     */
    public boolean changeUserCode(String newUserCode){
        try {
            //initialize a new file. Overwrite SD_Card.txt
            FileWriter fileWriter = new FileWriter("Keypad/resources/SD_Card.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new user code on second line,
            while reserving the system's current manager code*/
            printWriter.printf("%s\n%s",managerCode,newUserCode);
            printWriter.close();
            //change the user code in the system
            userCode = newUserCode;
        }
        catch (IOException e){
            //if it doesn't work, return false
            System.out.println("Passwords not changed.");
            return false;
        }
        return true;
    }

    /**
     * Check for match with userCode
     * @return boolean
     */
    public boolean checkUserCode(String codeToCheck){
        //returns true if codeToCheck == system's user code, false otherwise
        return codeToCheck.equals(userCode);
    }

    /**
     * Check for match with managerCode
     * @return boolean
     */
    public boolean checkMgrCode(String codeToCheck){
        //returns true if codeToCheck == system's manager code, false otherwise
        return codeToCheck.equals(managerCode);
    }


    /**
     * If we choose to go this route:
     * Overloaded constructor for Demo. Takes a String as input which would
     * be a File path.
     * Can use this one for the demo's two Controller threads,
     * so that they are not trying to access the same file at the same time.
     * There are two SD_Card files now. One is 'SD_Card.txt' and the other is
     * 'SD_Card2.txt'
     * If we go this route, then there is also two overloaded change code methods
     * so that each AccessCode object writes to a different file, depending on which
     * file it initializes from.
     * @param filePath String
     */
    public AccessCodes(String filePath){
        try{
            Scanner scanner = new Scanner(new File(filePath));
            /*get the manager and user code from the file. The manager code will
            always be first line, user code will always be second line.*/
            managerCode = scanner.nextLine();//default = "0000"
            userCode = scanner.nextLine();   //default = "9999"
        }
        catch (FileNotFoundException e){
            System.out.println(filePath + " not found.");
            System.exit(1);
        }
    }
    /**
     * Overloaded changeMgrCode method.
     * If changing a code using Controller2 for Keypad2,
     * then write to a SD_Card2.txt. Else, this is Keypad1 via
     * Controller1, so write to a SD_Card.txt.
     * @param newMgrCode String
     * @param accessCodesNumber int: if accessCodesNumber = 2, write to SD_Card2.txt
     * @return boolean
     */
    public boolean changeMgrCode(String newMgrCode, int accessCodesNumber){
        /*Define a new file depending on the value of accessCodesNumber*/
        String fileName = "Keypad/resources";
        fileName += accessCodesNumber == 2 ? "/SD_Card2.txt" : "/SD_Card.txt";
        try {
            //initialize a new file. Overwrite.
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new manager code on first line,
            while reserving the system's current user code*/
            printWriter.printf("%s\n%s",newMgrCode,userCode);
            printWriter.close();
            //change manager code in the system
            managerCode = newMgrCode;
        }
        catch (IOException e){
            //if it doesn't work, return false
            System.out.println("Passwords not changed.");
            return false;
        }
        return true;
    }
    /**
     * Overloaded changeUserCode method.
     * If changing a code using Controller2 for Keypad2,
     * then write to a SD_Card2.txt. Else, this is Keypad1 via
     * Controller1, so write to a SD_Card.txt.
     * @param newUserCode String
     * @param accessCodesNumber int: if acessCodesNumber = 2, write to SD_Card2.txt
     * @return boolean
     */
    public boolean changeUserCode(String newUserCode, int accessCodesNumber){
        /*Define a new file depending on the value of accessCodesNumber*/
        String fileName = "Keypad/resources";
        fileName += accessCodesNumber == 2 ? "/SD_Card2.txt" : "/SD_Card.txt";
        try {
            //initialize a new file. Overwrite.
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new manager code on first line,
            while reserving the system's current user code*/
            printWriter.printf("%s\n%s",managerCode, newUserCode);
            printWriter.close();
            //change manager code in the system
            userCode = newUserCode;
        }
        catch (IOException e){
            //if it doesn't work, return false
            System.out.println("Passwords not changed.");
            return false;
        }
        return true;
    }
    
    /* Getters for testing
    public String getManagerCode() {
        return managerCode;
    }
    public String getUserCode() {
        return userCode;
    }
    */
}
