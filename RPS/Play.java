import java.util.Scanner;


    public class Play{
        RPS game;
        Controller control;
        Scanner userinput;
        String play1;
        String play2;

    //Input Loop, plays however many games the user inputs
    public Play(){

    }
    public void Outcomes(String play1, String play2, String gameMode) {
        game = new RPS();
        control = new Controller();

        while (true) {
            if (gameMode.equalsIgnoreCase("1")){
                System.out.println("You chose " + play1 + ".");
                System.out.println("The computer chose " + play2 + ".");

                RPS.GameOutcome outcome = game.getGameOutcome(play1, play2, gameMode);
                control.result(outcome, gameMode);

                System.out.println("Wins: " + control.wins);
                System.out.println("Losses: " + control.losses);
                System.out.println("Draws: " + control.draws);
                
                
                System.out.print("Your rank: ");
                if (control.getWins() > 19) {
                  System.out.println("Dungeon Master");
                }
                else if (control.getWins() > 9) {
                  System.out.println("Veteran");
                }
                else if (control.getWins() > 4) {
                  System.out.println("Apprentice");
                }
                else if (control.getWins() > 2) {
                  System.out.println("Novice");
                }
                else {
                  System.out.println("Newbie");
                }


                System.out.println("Do you want to play again? (y/n)");

                Scanner check = new Scanner(System.in);

                String answer = check.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                        play1 = game.humChoice();
                        play2 = game.getRandomChoice();
                        continue;}
                    
                else{
                    //User wants to quit, end loop
                    break; 
                    }
        }
                
            if (gameMode.equalsIgnoreCase("2")){
                System.out.println("Player 1 chose " + play1 + ".");
                System.out.println("Player 2 chose " + play2 + ".");

                RPS.GameOutcome outcome = game.getGameOutcome(play1, play2, gameMode);
                control.result(outcome, gameMode);

                System.out.println("Do you want to play again? (y/n)");

                Scanner check = new Scanner(System.in);

                String answer = check.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                        play1 = game.humChoice();
                        play2 = game.humChoice2();
                        continue;}
                    
                else{
                    //User wants to quit, end loop
                    break; 
                    }
                }
            }
    }
    }



