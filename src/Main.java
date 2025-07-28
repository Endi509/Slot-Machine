import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Step 1: declare variables
        Scanner scanner = new Scanner(System.in);
        int balance = 100;
        int bet;
        int payout;
        String[] row;
        String playAgain;

        //Step 2: display welcome message
        System.out.println("**************************");
        System.out.println("  Welcome to Java Slots!  ");
        System.out.println("Symbols: \uD83C\uDF52" + //cherry
                                    "\uD83C\uDF49" + //watermelon
                                    "\uD83C\uDF4B" + //lemon
                                    "\uD83D\uDD14" + //bell
                                        "⭐ ");
        System.out.println("**************************");

        //Step 3: play if balance > 0
        while (balance > 0){

            //Step 4: Enter bet amount
            System.out.println("Current balance: $" + balance);
            System.out.print("Place your bet amount: ");
            bet = scanner.nextInt();
            scanner.nextLine();

            //Verify if bet > balance
            if (bet > balance){
                System.out.println("INSUFFICIENT FUNDS:");
                continue;
            }
            //Verify if bet > 0
            else if (bet <= 0){
                System.out.println("Bet must be greater than 0");
                continue;
            }
            //Subtract bet from balance
            else {
                balance -= bet;
            }
            //Step 5: Spin row
            System.out.println("Spining...");
            row = spinRow();

            //Step 6: Print row
            printRow(row);

            //Step 7: Get Payout
            payout = getPayout(row, bet);

            if (payout > 0){
                System.out.println("You won $" + payout);
                balance += payout;
            }
            else {
                System.out.println("Sorry you lost this round!");
            }

            //Step 8: Ask to play again
            System.out.print("Do you want to play again? (Yes/No): ");
            playAgain = scanner.nextLine().toUpperCase();

            if (!playAgain.equals("Y")){
                break;
            }
        }
        //Step 9: Display exit message
        System.out.println("GAME OVER! Your final balance is $" + balance);





        scanner.close();
    }
    static String[] spinRow(){

        String[] symbols = {"\uD83C\uDF52", //cherry
                            "\uD83C\uDF49", //watermelon
                            "\uD83C\uDF4B", //lemon
                            "\uD83D\uDD14", //bell
                            "⭐"};
        String[] row = new String[3];
        Random random = new Random();

        for (int i = 0;  i < 3; i++){
            row[i] = (symbols[random.nextInt(symbols.length)]);
        }

        return row;
    }
    static void printRow(String[] row){
        System.out.println("**************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("**************");

    }
    static int getPayout(String[] row, int bet){

        if (row[0].equals(row[1]) && row[1].equals(row[2])) {
            return switch (row[0]) {
                case "\uD83C\uDF52" -> bet * 3;
                case "\uD83C\uDF49" -> bet * 4;
                case "\uD83C\uDF4B" -> bet * 5;
                case "\uD83D\uDD14" -> bet * 10;
                case "⭐" -> bet * 20;
                default -> 0;

            };
        }
            else if (row[0].equals(row[1])){
                return switch (row[0]) {
                    case "\uD83C\uDF52" -> bet * 2;
                    case "\uD83C\uDF49" -> bet * 3;
                    case "\uD83C\uDF4B" -> bet * 4;
                    case "\uD83D\uDD14" -> bet * 5;
                    case "⭐" -> bet * 10;
                    default -> 0;
                };
        }
        else if (row[1].equals(row[2])) {
            return switch (row[1]) {
                case "\uD83C\uDF52" -> bet * 2;
                case "\uD83C\uDF49" -> bet * 3;
                case "\uD83C\uDF4B" -> bet * 4;
                case "\uD83D\uDD14" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }


        return 0;

    }
}