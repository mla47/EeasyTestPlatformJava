import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentDetail extends Detail{
    private Scanner cin = new Scanner(System.in);
    private EducationOfficer ed;
    private MultiValueMap<String, String> studentsDetail = new MultiValueMap<>();
    private Map<String, Map<String, Integer>> examScores = new HashMap<>();

    public StudentDetail(EducationOfficer ed) {
        this.ed = ed;
    }

    public void addDetail(String studentName) {
        System.out.println("Enter details for student " + studentName + ": ");

        System.out.print("Enter Password: ");
        setPassword(cin.next());
        studentsDetail.put(studentName, "Password: " + getPassword());

        System.out.print("Enter Father Name: ");
        setFatherName(cin.next());
        studentsDetail.put(studentName, "Father Name: " + getFatherName());

        System.out.print("Enter National Code: ");
        setNationalCode(cin.next());
        studentsDetail.put(studentName, "National Code: " + getNationalCode());

        System.out.print("Enter Last Name: ");
        setLastName(cin.next());
        studentsDetail.put(studentName, "Last Name: " + getLastName());

        System.out.print("Enter Gender: ");
        setGender(cin.next());
        studentsDetail.put(studentName, "Gender: " + getGender());

        System.out.print("Enter Number of Units: ");
        setNumberOfUnits(cin.next());
        studentsDetail.put(studentName, "Number of Units: " + getNumberOfUnits());

        System.out.print("Enter Years: ");
        String years = cin.nextLine();
        studentsDetail.put(studentName, "Years: " + years);
    }

    public void displayDetails(String studentName) {
        if (studentsDetail.containsKey(studentName)) {
            System.out.println("Details for student: " + studentName);
            for (String detail : studentsDetail.get(studentName)) {
                System.out.println(detail);
            }
            System.out.println("-----------------------");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addExamScore(String studentName, String examTitle, int score) {
        if (ed.students.contains(studentName)) {
            if (!examScores.containsKey(studentName)) {
                examScores.put(studentName, new HashMap<>());
            }
            examScores.get(studentName).put(examTitle, score);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayExamScores(String studentName) {
        if (examScores.containsKey(studentName)) {
            System.out.println("Exam scores for student " + studentName + ":");
            for (Map.Entry<String, Integer> entry : examScores.get(studentName).entrySet()) {
                System.out.println("Exam: " + entry.getKey() + ", Score: " + entry.getValue());
            }
        } else {
            System.out.println("No exam scores found for student " + studentName);
        }
    }
}
