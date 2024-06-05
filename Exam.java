import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Exam {
    private String title;
    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<Integer> correctAnswers;
    private ArrayList<String> studentsAttend;

    public Exam(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.options = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
        this.studentsAttend = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(String question, ArrayList<String> options, int correctAnswer) {
        this.questions.add(question);
        this.options.add(options);
        this.correctAnswers.add(correctAnswer);
    }

    public void addStudent(String student) {
        this.studentsAttend.add(student);
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<ArrayList<String>> getOptions() {
        return options;
    }

    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public ArrayList<String> getStudentsAttend() {
        return studentsAttend;
    }

    public boolean isStudentAllowed(String studentName) {
        return studentsAttend.contains(studentName);
    }

    public List<Integer> collectAnswers(Scanner cin) {
        List<Integer> studentAnswers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i));
            ArrayList<String> optionList = options.get(i);
            for (int j = 0; j < optionList.size(); j++) {
                System.out.println("  " + (char) ('a' + j) + ") " + optionList.get(j));
            }
            System.out.print("Enter your answer (1-4): ");
            int answer = cin.nextInt();
            cin.nextLine(); // Consume newline
            studentAnswers.add(answer);
        }
        return studentAnswers;
    }

    public void showResults(List<Integer> studentAnswers) {
        int correctCount = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if (studentAnswers.get(i) == correctAnswers.get(i)) {
                correctCount++;
            }
        }
        System.out.println("You got " + correctCount + " out of " + questions.size() + " correct.");
    }
}
