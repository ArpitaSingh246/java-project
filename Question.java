import java.util.Arrays;

public class Question {
    private final String prompt;
    private final String[] options; // expects exactly 4 options
    private final char correctOption; // 'A' - 'D'

    public Question(String prompt, String[] options, char correctOption) {
        if (prompt == null || prompt.isBlank()) throw new IllegalArgumentException("Prompt cannot be empty");
        if (options == null || options.length != 4) throw new IllegalArgumentException("Exactly 4 options are required");
        char up = Character.toUpperCase(correctOption);
        if (up < 'A' || up > 'D') throw new IllegalArgumentException("Correct option must be A, B, C or D");
        this.prompt = prompt.trim();
        this.options = Arrays.copyOf(options, options.length);
        this.correctOption = up;
    }

    public String getPrompt() {
        return prompt;
    }

    public String[] getOptions() {
        return Arrays.copyOf(options, options.length);
    }

    public boolean isCorrect(char choice) {
        return Character.toUpperCase(choice) == correctOption;
    }

    public char getCorrectOption() {
        return correctOption;
    }
}
