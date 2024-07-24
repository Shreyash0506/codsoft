import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;


  
    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {
    private Question[] questions;
    private int score;
  
    private int currentQuestionIndex;
    private Scanner scanner;
    private boolean answered;

  
    private Timer timer;

    public Quiz() {
        questions = new Question[] {
            new Question("What is the capital of France?", new String[] {"A. Paris", "B. London", "C. Berlin", "D. Madrid"}, 'A'),
            new Question("What is 2 + 2?", new String[] {"A. 3", "B. 4", "C. 5", "D. 6"}, 'B'),
            new Question("What is the largest ocean?", new String[] {"A. Atlantic", "B. Indian", "C. Arctic", "D. Pacific"}, 'D')
        };
        score = 0;
        currentQuestionIndex = 0;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Quiz!");
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            answered = false;
            displayQuestion();
            startTimer(10); 
            while (!answered) {
                
            }
        }
        displayResults();
        scanner.close();
    }

    private void displayQuestion() {
        Question question = questions[currentQuestionIndex];
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.print("Enter your answer (A, B, C, or D): ");
    }

    private void startTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    answered = true;
                    timer.cancel();
                }
            }
        }, seconds * 1000);
        getAnswer();
    }

    private void getAnswer() {
        char answer = scanner.next().charAt(0);
        checkAnswer(answer);
        answered = true;
      
        timer.cancel();
    }

    private void checkAnswer(char answer) {
        if (answer == questions[currentQuestionIndex].correctAnswer) {
          
            System.out.println("Correct!");
            score++;
          
        } else {
            System.out.println("Incorrect! The correct answer was " + questions[currentQuestionIndex].correctAnswer);
        }
      
        System.out.println();
    }

    private void displayResults() {
        System.out.println("Quiz Over!");
      
        System.out.println("Your score: " + score + "/" + questions.length);
        System.out.println("Thank you for playing!");
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}
