import java.util.Scanner;

public class RunGame {
    RPS game;
    Play playAction;
    String play1;
    String play2;

    public RunGame() {
        game = new RPS();
        playAction = new Play();
    }

    public void execute(){
      System.out.println("Would you like to play 1 player or 2 players?");
        String gameMode;
        Scanner in = new Scanner(System.in);
        gameMode = in.nextLine();
        if (gameMode.equalsIgnoreCase("1")){
          play1 = game.humChoice();
          play2 = game.getRandomChoice();
          playAction.Outcomes(play1,play2, gameMode);}
        if (gameMode.equalsIgnoreCase("2")){
          play1 = game.humChoice();
          play2 = game.humChoice2();
          playAction.Outcomes(play1,play2, gameMode);}
        }



    public static void main(String args[]) {
       RunGame game = new RunGame();
       game.execute();

    }
}
