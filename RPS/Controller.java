import java.util.Scanner;

public class Controller {
    RPS model;
    Scanner userInput;
    RPS.GameOutcome outcome;
    String CPU;
    String HUM;
    int wins;
    int losses;
    int draws;

    public Controller(){
        wins = 0;
        losses = 0;
        draws  = 0;

    }


    public void result(RPS.GameOutcome output, String gameMode) {
        if (gameMode.equalsIgnoreCase("1")){
            if (output == RPS.GameOutcome.WIN) {
                System.out.println("You won the game!");
                this.wins++;

            } else if (output == RPS.GameOutcome.LOSE) {
                System.out.println("You lose...");
                this.losses++;
            } else {
                System.out.println("It's a draw!");
                this.draws++;
            }
        }
        if (gameMode.equalsIgnoreCase("2")){
            if (output == RPS.GameOutcome.WIN) {
                System.out.println("Player 1 won the game!");
                
            } else if (output == RPS.GameOutcome.LOSE) {
                System.out.println("Player 2 won the game!");
                
            } else {
                System.out.println("It's a draw!");
            }
        }
    }

    public int getWins(){
        return wins;
    }
    public int getLosses(){
        return losses;
    }
    public int getDraws(){
        return draws;
    }
}

