import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
public class Professor implements masterdetail,AbsArray{
    private Scanner cin = new Scanner(System.in);
    private static final String EXAM_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Exam.txt";
    private static final String ANSWER_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Answer.txt";
    private static final String CORRECT_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\correctAnswer.txt";
    private static final String RESULT_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Result.txt";
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<Integer> correctAnswers = new ArrayList<>();
    private ArrayList<String> authorizedStudents;
    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private Manager m;
    private boolean isExamActive = false;
    EducationOfficer ed;
    public Professor(Manager manager, EducationOfficer educationOfficer) {
        this.m = manager;
        this.ed=educationOfficer;
    }
    public void addDetail() {
        System.out.print("Enter your current name: ");
        String name = cin.next();
        int index=0;
        for (int i=0;i<m.Professors.size();i++) {
            if (m.Professors.get(i)==name) {
                index=i;
                break;
            }
        }
        String name_input=m.Professors.get(index);
        for (String professorName : professorsDetail.get(name_input)) {
            System.out.println("Enter details for Professor " + professorName + " : ");

            System.out.print("Enter Password: ");
            String password = cin.next();
            professorsDetail.put(professorName, "Password: " + password);

            System.out.print("Enter Father Name: ");
            String fatherName = cin.next();
            professorsDetail.put(professorName, "Father Name: " + fatherName);

            System.out.print("Enter National Code: ");
            String nationalCode = cin.next();
            professorsDetail.put(professorName, "National Code: " + nationalCode);

            System.out.print("Enter Last Name: ");
            String lastName = cin.next();
            professorsDetail.put(professorName, "Last Name: " + lastName);

            System.out.print("Enter Gender: ");
            String gender = cin.next();
            professorsDetail.put(professorName, "Gender: " + gender);

            System.out.print("Enter Number of Units: ");
            String numberOfUnits = cin.next();
            professorsDetail.put(professorName, "Number of Units: " + numberOfUnits);

            System.out.print("Enter Years: ");
            String years = cin.next();
            professorsDetail.put(professorName, "Years: " + years);

            System.out.print("Enter MasterCode: ");
            String masterCode = cin.next();
            professorsDetail.put(professorName, "MasterCode: " + masterCode);
        }
    }
    public void displayMergedList() {
        System.out.println("Enter Professor name to show detail: ");
        String name = cin.next();
        if (professorsDetail.containsKey(name)) {
            System.out.println("Details for Professor " + name + ": ");
            for (String detail : professorsDetail.get(name)) {
                System.out.println(detail);
            }
            System.out.println("-----------------------");
        } else {
            System.out.println("Professor not found.");
        }
    }
    private HashMap<String, Exam> exams = new HashMap<>();
    public void createExam() {
        System.out.println("Enter title of Exam: ");
        String title = cin.nextLine();
        Exam newExam = new Exam(title);
        int numQuestions=0;
        try{
            System.out.println("How many questions do you want to add?");
            numQuestions = cin.nextInt();
            cin.nextLine(); // Consume newline
        }catch (Exception e){
            System.out.println("Invalid input!"+e.getMessage());
        }
        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question " + (i + 1) + ": ");
            String question = cin.nextLine();

            ArrayList<String> options = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                System.out.print("Enter option " + (j + 1) + ": ");
                String option = cin.nextLine();
                options.add(option);
            }

            System.out.println("Enter Correct option number (1-4) for this question: ");
            int correctAnswer = cin.nextInt();
            cin.nextLine(); // Consume newline

            newExam.addQuestion(question, options, correctAnswer);
        }

        System.out.println("Enter Students name can attend in this Exam: ");
        while (true) {
            System.out.println("Enter name: ");
            String name = cin.nextLine();
            if(!ed.students.contains(name)){
                System.out.println("Student not found!!");
                return;
            }
            newExam.addStudent(name);
            System.out.println("Do you want to add another student? (yes/no): ");
            String input = cin.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Do you want to activate this exam? (yes/no): ");
        boolean isExamActive = cin.nextLine().equalsIgnoreCase("yes");

        // Add the exam to the hashmap and update its activation status
        Exam_Students.put(title, newExam);
        Exam_Activate.put(title, isExamActive);
        Exam_titles.add(title);

        System.out.println("Exam created successfully.");
    }

    public boolean isExamActivate() {
        boolean active = false;
        try {
            System.out.println("Do you want Activate this Exam ? ");
            String input = cin.next();

            if (input.equalsIgnoreCase("yes")) {
                active = true;
            } else if (input.equalsIgnoreCase("no")) {
                active = false;
            } else {
                throw new ArithmeticException("Invalid input!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return active;
    }

    public boolean isExamActive() {
        return isExamActive;
    }

    public void viewExams() {
        System.out.println("Title of Exams: ");
        System.out.println("--------------------");
        try (FileReader fr = new FileReader(EXAM_FILE_PATH)) {
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
        if (isExamActive) {
            System.out.println("Exam is currently active.");
        } else {
            System.out.println("Exam is currently inactive.");
        }
    }

    public void collectAnswer() {
        if(!isExamActivate()){
            System.out.println("Exam not Activated!");
            return;
        }
        System.out.print("Enter your name: ");
        String studentName = cin.next();

        if (!authorizedStudents.contains(studentName)) {
            System.out.println("You are not authorized to take this exam.");
            return;
        }

        int correct = 0;
        int incorrect = 0;
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ") " + options.get(i * 4 + j));
            }
            System.out.print("Choose option: ");
            int choice = cin.nextInt();
            if (choice == correctAnswers.get(i)) {
                correct++;
            } else {
                incorrect++;
            }
        }
        System.out.println("Correct answers: " + correct);
        System.out.println("Incorrect answers: " + incorrect);
        int percentage=(correct*3-incorrect)*100/questions.size();
        System.out.println("Percentage answer : "+percentage);
    }

    public void professorOption() {
        while (true) {

            System.out.println("Choose Option:");
            System.out.println(ANSI_YELLOW + "1) Create Exam" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2) View Exam/Collect Answers" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3) Change Password" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4) Add Your Details" + ANSI_RESET);
            System.out.println(ANSI_YELLOW+"5)view Your Detail "+ANSI_RESET);
            System.out.println(ANSI_YELLOW + "6) View Exams Created" + ANSI_RESET);
            System.out.println(ANSI_RED + "7) Quit" + ANSI_RESET);

            int choice = cin.nextInt();
            cin.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createExam();
                    break;
                case 2:
                    collectAnswer();
                    break;
                case 3:
                    changePassword();
                    break;
                case 4:
                    addDetail_option();
                    break;
                case 5:
                    displayMergedList();
                    break;
                case 6:
                    viewExams();
                    break;
                case 7 :
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public void addDetail_option() {
        addDetail();
        displayMergedList();
    }

    public void writeCorrectAnswer(int correctOption) {
        try (FileWriter fw = new FileWriter(CORRECT_FILE_PATH, true)) {
            fw.write(correctOption + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResult(String studentName, int correct, int incorrect) {
        try (FileWriter fw = new FileWriter(RESULT_FILE_PATH, true)) {
            fw.write("Student: " + studentName + "\n");
            fw.write("Correct answers: " + correct + "\n");
            fw.write("Incorrect answers: " + incorrect + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePassword() {
        System.out.print("Enter your current name: ");
        String name = cin.next();
        boolean found = false;
        for (String professorName : m.Professors) {
            if (name.equals(professorName)) {
                m.Professors.remove(name);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.print("Enter your new sign-in name: ");
            String newName = cin.next();
            m.Professors.add(newName);
            System.out.println("Name changed successfully.");
        } else {
            System.out.println("Name not found.");
        }
    }
}
