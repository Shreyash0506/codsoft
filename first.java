import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean continuePlaying = true;
        int totalScore = 0;

        while (continuePlaying) {
            int lowerBound = 1;
            int upperBound = 100;
            int secretNumber = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
            int maxTries = 10;
            int attemptsMade = 0;
            boolean isGuessedCorrectly = false;

            System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound + ":");

            while (attemptsMade < maxTries && !isGuessedCorrectly) {
                System.out.print("Your guess: ");
                int playerGuess = input.nextInt();
                attemptsMade++;

                if (playerGuess == secretNumber) {
                    System.out.println("Well done! You guessed the right number.");
                    isGuessedCorrectly = true;
                    totalScore += (maxTries - attemptsMade + 1);
                } else if (playerGuess > secretNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!isGuessedCorrectly) {
                System.out.println("You've exhausted all attempts. The correct number was " + secretNumber + ".");
            }

            System.out.print("Would you like to play another round? (yes/no): ");
            String userResponse = input.next();
            continuePlaying = userResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Your final score is: " + totalScore);
        input.close();
    }
}


