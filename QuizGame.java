import java.util.*;
import java.util.concurrent.*;

public class QuizGame {
    private static final int TIME_LIMIT_SECONDS = 20;
    private final List<Question> questions;
    private final Scanner scanner = new Scanner(System.in);

    public QuizGame(List<Question> questions) {
        if (questions == null || questions.isEmpty()) throw new IllegalArgumentException("At least one question is required");
        this.questions = new ArrayList<>(questions);
    }

    public void start() {
        Collections.shuffle(questions);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + "/" + questions.size() + ":");
            System.out.println(q.getPrompt());
            String[] opts = q.getOptions();
            char label = 'A';
            for (String opt : opts) {
                System.out.println(label + ". " + opt);
                label++;
            }

            System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds. Enter A, B, C or D:");

            Future<String> future = executor.submit(() -> {
                try {
                    return scanner.nextLine();
                } catch (Exception e) {
                    return null;
                }
            });

            String answer = null;
            try {
                answer = future.get(TIME_LIMIT_SECONDS, TimeUnit.SECONDS);
            } catch (TimeoutException te) {
                System.out.println("Time's up!");
                future.cancel(true);
            } catch (ExecutionException | InterruptedException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }

            if (answer != null) {
                answer = answer.trim().toUpperCase(Locale.ROOT);
                if (answer.matches("^[A-D]$")) {
                    char choice = answer.charAt(0);
                    if (q.isCorrect(choice)) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. Correct answer: " + q.getCorrectOption());
                    }
                } else {
                    System.out.println("Invalid choice. Please enter A, B, C, or D.");
                }
            }

            System.out.println();
        }

        executor.shutdownNow();
        System.out.println("Quiz complete. Score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 'C'));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 'B'));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Mark Twain", "William Shakespeare", "Charles Dickens", "Leo Tolstoy"}, 'B'));
        questions.add(new Question("What is the chemical formula for water?", new String[]{"H2O", "CO2", "O2", "NaCl"}, 'A'));
        questions.add(new Question("How many continents are there?", new String[]{"5", "6", "7", "8"}, 'C'));

        QuizGame game = new QuizGame(questions);
        game.start();
    }
}
