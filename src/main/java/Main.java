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
                    register.SaveState();
                    register.AddTwenties(Integer.parseInt(pieces[1]));
                    register.AddTens(Integer.parseInt(pieces[2]));
                    register.AddFives(Integer.parseInt(pieces[3]));
                    register.AddTwos(Integer.parseInt(pieces[4]));
                    register.AddOnes(Integer.parseInt(pieces[5]));
                    System.out.println(register.toString());
                } else if ("take".equals(pieces[0]) && pieces.length == 6) {
                    register.SaveState();
                    register.TakeTwenties(Integer.parseInt(pieces[1]));
                    register.TakeTens(Integer.parseInt(pieces[2]));
                    register.TakeFives(Integer.parseInt(pieces[3]));
                    register.TakeTwos(Integer.parseInt(pieces[4]));
                    register.TakeOnes(Integer.parseInt(pieces[5]));
                    System.out.println(register.toString());
                } else if ("change".equals(pieces[0]) && pieces.length == 2) {
                    String numToChange = pieces[1];
                    register.SaveState();
                    System.out.println(numToChange);
                    String result = register.MakeChange(Integer.parseInt(numToChange));
                    System.out.println(result);
                } else if ("show".equals(pieces[0]) && pieces.length == 1) {
                    System.out.println(register.toString());
                } else {
                    System.out.println("Invalid command, please try again");
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid data was passed in the command, command discarded");
                register.RestoreState();
            }catch (Exception e) {
                System.out.println(e.getMessage());
                register.RestoreState();
            }
            command = in.nextLine();
            pieces = command.split(" ");
        }
        System.out.println("Bye");
    }
}
