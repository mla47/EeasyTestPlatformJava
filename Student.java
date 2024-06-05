import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Student implements AbsArray,tempstu{
    private Scanner cin = new Scanner(System.in);
    private Professor professor;
    private EducationOfficer educationOfficer;
    private StudentDetail sd;
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);
    private static final String CLASS_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Class.txt";

    public Student(Professor professor, EducationOfficer educationOfficer) {
        this.professor = professor;
        this.educationOfficer = educationOfficer;
        this.sd = new StudentDetail(educationOfficer);
    }

    public Student(Professor professor, EducationOfficer educationOfficer, StudentDetail sd) {
        this.professor = professor;
        this.educationOfficer = educationOfficer;
        this.sd = sd;
    }

    public void studentsOption() {
        while (true) {
            System.out.println("Choose Option : ");
            System.out.println(ANSI_YELLOW+"1) Start Exam"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"2) View classes attended"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"3) Change Password"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"4) Add/View your detail"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"5) View Exam Scores"+ANSI_RESET);
            System.out.println(ANSI_RED+"6) Quit"+ANSI_RESET);

            int choice = cin.nextInt();
            cin.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    startExam();
                    break;
                case 2:
                    classAttend();
                    break;
                case 3:
                    changeName();
                    break;
                case 4:
                    addOrViewDetail();
                    break;
                case 5:
                    viewExamScores(); // Added option to view exam scores
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input! Try again.");
            }
        }
    }

    public void addOrViewDetail() {
        System.out.println("Enter your name:");
        String studentName = cin.nextLine();

        System.out.println("Choose Option:");
        System.out.println("1) Add detail");
        System.out.println("2) View detail");

        int choice = cin.nextInt();
        cin.nextLine(); // Consume newline

        if (choice == 1) {
            if (educationOfficer != null && educationOfficer.students.contains(studentName)) {
                sd.addDetail(studentName);
            } else {
                System.out.println("Your name is not in the list of students.");
            }
        } else if (choice == 2) {
            sd.displayDetails(studentName);
        } else {
            System.out.println("Invalid input! Try again.");
        }
    }

    public void viewExamScores() {
        System.out.print("Enter your name: ");
        String studentName = cin.nextLine();
        sd.displayExamScores(studentName);
    }

    public void classAttend() {
        System.out.println("Enter your name:");
        String name = cin.nextLine();
        if (educationOfficer != null && educationOfficer.students.contains(name)) {
            System.out.println("You attended the class.");
            System.out.println("Your class is : ");
            for (String student : educationOfficer.students) {
                System.out.println(student);
            }
        } else {
            System.out.println("You didn't attend this class or your name is not in the list!");
        }
    }

    public void changeName() {
        System.out.println("Enter your current name : ");
        String name = cin.nextLine();
        if(Student_Login.containsKey(name)){
            System.out.println("Enter new Password : ");
            String new_pass= cin.next();
            Student_Login.put(name,new_pass);
        }
        else{
            System.out.println("Student not found!");
        }
    }
    public void startExam() {
        System.out.print("Enter your name: ");
        String studentName = cin.nextLine();

        if (!educationOfficer.students.contains(studentName)) {
            System.out.println("You are not a registered student.");
            return;
        }

        if (professor != null) {
            if (Exam_titles.isEmpty()) {
                System.out.println("No exams are available.");
                return;
            }

            System.out.println("Choose Exam title: ");
            int i = 1;
            for (String name : Exam_titles) {
                System.out.println(i + ") " + name);
                i++;
            }

            int choice = -1;
            while (choice < 1 || choice > Exam_titles.size()) {
                System.out.print("Enter the number corresponding to your choice: ");
                try {
                    choice = Integer.parseInt(cin.nextLine());
                    if (choice < 1 || choice > Exam_titles.size()) {
                        System.out.println("Invalid choice. Please select a valid exam number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            String selectedExam = Exam_titles.get(choice - 1);
            Exam exam = Exam_Students.get(selectedExam);

            if (exam != null && exam.isStudentAllowed(studentName)) {
                System.out.println("You have selected: " + selectedExam);
                List<Integer> studentAnswers = exam.collectAnswers(cin);
                exam.showResults(studentAnswers);
            } else {
                System.out.println("You are not allowed to take this exam.");
            }
        } else {
            System.out.println("Professor is not available.");
        }
    }
}
