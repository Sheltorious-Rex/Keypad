import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccessCodes {

    private String managerCode;//the system's manager code
    private String userCode;//the system's user code

    public AccessCodes(){
        try{
            Scanner scanner = new Scanner(new File("SD_Card.txt"));
            /*get the manager and user code from the file. manager code will always be first line,
            user code will always be second line.*/
            managerCode = scanner.nextLine();
            userCode = scanner.nextLine();
        }
        catch (FileNotFoundException e){
            System.out.println("SD_Card.txt file not found.");
            System.exit(1);
        }
    }
    //change manager code. newMgrCode already checked for validity
    public boolean changeMgrCode(String newMgrCode){
        try {
            //initialize a new file
            FileWriter fileWriter = new FileWriter("SD_Card.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new manager code on first line, while reserving the system's
            current user code*/
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
    //change user code. newUserCode already checked for validity
    public boolean changeUserCode(String newUserCode){
        try {
            //initialize a new file
            FileWriter fileWriter = new FileWriter("SD_Card.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*write to the new file with the new user code on second line, while reserving the system's
            current manager code*/
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
    //check for match for user code
    public boolean checkUserCode(String codeToCheck){
        //returns true if codeToCheck == system's user code, false otherwise
        return codeToCheck.equals(userCode);
    }
    //check for match for manager code
    public boolean checkMgrCode(String codeToCheck){
        //returns true if codeToCheck == system's manager code, false otherwise
        return codeToCheck.equals(managerCode);
    }

    public String getManagerCode() {
        return managerCode;
    }

    public String getUserCode() {
        return userCode;
    }
}
