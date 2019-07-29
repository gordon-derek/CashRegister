import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CashRegister register = new CashRegister();
        System.out.println("ready");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String[] pieces = command.split(" ");
        while(!"quit".equals(pieces[0])) {
            try {
                if ("put".equals(pieces[0]) && pieces.length == 6) {
                    register.saveState();
                    register.addTwenties(Integer.parseInt(pieces[1]));
                    register.addTens(Integer.parseInt(pieces[2]));
                    register.addFives(Integer.parseInt(pieces[3]));
                    register.addTwos(Integer.parseInt(pieces[4]));
                    register.addOnes(Integer.parseInt(pieces[5]));
                    System.out.println(register.toString());
                } else if ("take".equals(pieces[0]) && pieces.length == 6) {
                    register.saveState();
                    register.takeTwenties(Integer.parseInt(pieces[1]));
                    register.takeTens(Integer.parseInt(pieces[2]));
                    register.takeFives(Integer.parseInt(pieces[3]));
                    register.takeTwos(Integer.parseInt(pieces[4]));
                    register.takeOnes(Integer.parseInt(pieces[5]));
                    System.out.println(register.toString());
                } else if ("change".equals(pieces[0]) && pieces.length == 2) {
                    String numToChange = pieces[1];
                    register.saveState();
                    System.out.println(numToChange);
                    String result = register.makeChange(Integer.parseInt(numToChange));
                    System.out.println(result);
                } else if ("show".equals(pieces[0]) && pieces.length == 1) {
                    System.out.println(register.toString());
                } else {
                    System.out.println("Invalid command, please try again");
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid data was passed in the command, command discarded");
                register.restoreState();
            }catch (Exception e) {
                System.out.println(e.getMessage());
                register.restoreState();
            }
            command = in.nextLine();
            pieces = command.split(" ");
        }
        System.out.println("Bye");
    }
}
