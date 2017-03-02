import java.util.Random;
import java.util.Scanner;


public class RPS {
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    public enum GameOutcome {
        WIN, LOSE, DRAW
    }


    public GameOutcome getGameOutcome(String play1Choice, String play2Choice, String gameMode) {
        if (gameMode.equalsIgnoreCase("1")){
            if (play1Choice.equalsIgnoreCase("rock")) {
                //USER PICKS rock
                if (play2Choice.equalsIgnoreCase("paper")) {
                    // rock < paper
                    losses++;
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("scissors")) {
                    // rock > scissors
                    wins++;
                    return GameOutcome.WIN;
                }
            } else if (play1Choice.equalsIgnoreCase("paper")) {
                //USER PICKS paper
                if (play2Choice.equalsIgnoreCase("scissors")) {
                    // paper < scissors
                    losses++;
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("rock")) {
                    // paper > rock
                    wins++;
                    return GameOutcome.WIN;
                }
            } else if (play1Choice.equalsIgnoreCase("scissors")) {
                //USER PICKS scissors
                if (play2Choice.equalsIgnoreCase("rock")) {
                    // scissors < rock
                    losses++;
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("paper")) {
                    // scissors > paper
                    wins++;
                    return GameOutcome.WIN;
                }
            }
            //If None of the Above occurs, then it must be a draw
            draws++;
            return GameOutcome.DRAW;
            }
       else if (gameMode.equalsIgnoreCase("2")){
            if (play1Choice.equalsIgnoreCase("rock")) {
                //USER PICKS rock
                if (play2Choice.equalsIgnoreCase("paper")) {
                    // rock < paper
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("scissors")) {
                    // rock > scissors
                    return GameOutcome.WIN;
                }
            } else if (play1Choice.equalsIgnoreCase("paper")) {
                //USER PICKS paper
                if (play2Choice.equalsIgnoreCase("scissors")) {
                    // paper < scissors
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("rock")) {
                    // paper > rock
                    return GameOutcome.WIN;
                }
            } else if (play1Choice.equalsIgnoreCase("scissors")) {
                //USER PICKS scissors
                if (play2Choice.equalsIgnoreCase("rock")) {
                    // scissors < rock
                    return GameOutcome.LOSE;
                } else if (play2Choice.equalsIgnoreCase("paper")) {
                    // scissors > paper
                    return GameOutcome.WIN;
                }
            }
            //If None of the Above occurs, then it must be a draw
            return GameOutcome.DRAW;
        }
       return GameOutcome.DRAW;
    }

    public String humChoice(){
        System.out.println("Player 1, please enter rock, paper, or scissors.");
        String HUM;
        Scanner in = new Scanner(System.in);
        HUM = in.nextLine();
        return HUM;
    }
    
     public String humChoice2(){
        System.out.println("Player 2, please enter rock, paper, or scissors.");
        String HUM2;
        Scanner in = new Scanner(System.in);
        HUM2 = in.nextLine();
        return HUM2;
    }

    public String getRandomChoice() {
        String CPUchoice = "";

        Random rand = new Random();
        int num = rand.nextInt(3) + 1;

        if(num == 1){
            CPUchoice = "rock";
        }
        if(num == 2){
            CPUchoice = "scissors";
        }
        else {
            CPUchoice = "paper";
        }
        return CPUchoice;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }
}