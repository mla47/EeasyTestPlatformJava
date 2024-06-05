import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;
import java.util.Iterator;

public class EducationOfficer implements tempstu, Unique,AbstractArray {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);
    Scanner cin = new Scanner(System.in);
    Manager manager = new Manager();
    private static final String CLASS_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Class.txt";
    private static final String STUDENTS_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Students.txt";
    public ArrayList<String> students=new ArrayList<String>();
    UserAccount userAccount;
    mainDetail md=new mainDetail();
    public ArrayList<String> educationOfficerDetail=new ArrayList<String>();
    public EducationOfficer(Manager manager) {
        this.manager=manager;
    }
    public void EducationOfficerOption() {
        while (true) {
            System.out.println("Choose Option: ");
            System.out.println(ANSI_BLUE + "1) Add Course" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2) Assign Courses to Professor" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "3) Add Students" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "4) Remove Professor" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "5) Change Password" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "6) Display Class" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "7) Remove Course" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "8) View Education Officer Detail" + ANSI_RESET);
            System.out.println(ANSI_RED + "9) Quit" + ANSI_RESET);
            int choose= cin.nextInt();
            switch (choose) {
                case 1:
                    setCourses();
                    break;
                case 2:
                    assignCoursesToProfessor();
                    break;
                case 3:
                    addStudents();
                    break;
                case 4:
                    removeProfessor();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    displayClass();
                    break;
                case 7:
                    removeCourse();
                    break;
                case 8 :
                    if(!educationOfficerDetail.isEmpty()){
                        displayEducationOfficerDetails();
                        System.out.println("Do you want change your Detail : ");
                        String input=cin.next();
                        if(input.equalsIgnoreCase("yes")){
                            setProfileEducationOfficer();
                        }
                        else{
                            continue;
                        }
                    }
                    else{
                        setProfileEducationOfficer();
                    }
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public void displayClass(){
        for (Map.Entry<String, ArrayList<String>> entry : Class.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public void setProfileEducationOfficer() {
        System.out.println("Enter Education Officer profile information");
        System.out.print("First Name: ");
        md.setFirst_name_Ed(cin.next());
        educationOfficerDetail.add(md.getFirst_name_Ed());

        System.out.print("Last Name: ");
        md.setLast_name_Ed(cin.next());
        educationOfficerDetail.add(md.getLast_name_Ed());

        System.out.print("National Code: ");
        md.setNationalCode_ed(cin.next());
        educationOfficerDetail.add(md.getNationalCode_ed());

        System.out.print("Father's Name: ");
        md.setFather_name_ed(cin.next());
        educationOfficerDetail.add(md.getFather_name_ed());

        System.out.print("Age: ");
        md.setAge_ed(cin.nextByte());
        educationOfficerDetail.add(Byte.toString(md.getAge_ed()));

        System.out.print("Gender (Male/Female): ");
        md.setGender_ed(cin.next());
        educationOfficerDetail.add(md.getGender_ed());

        System.out.print("Address: ");
        cin.nextLine();
        md.setAddress_ed(cin.nextLine());
        educationOfficerDetail.add(md.getAddress_ed());

        System.out.println("Profile information set successfully!");
    }
    public void displayEducationOfficerDetails() {
        System.out.println("Education Officer Details:");
        System.out.println("First Name : "+md.getFirst_name_Ed());
        System.out.println("Last Name : "+md.getLast_name_Ed());
        System.out.println("National Code : "+md.getNationalCode_ed());
        System.out.println("Father Name : "+md.getFather_name_ed());
        System.out.println("Age : "+md.getAge_ed());
        System.out.println("Address : "+md.getAddress_ed());
    }
    public HashSet<String> Courses=new HashSet<>();
    public void setCourses() {
        while (true) {
            System.out.println("Enter Class name: ");
            String course = cin.next();
            Courses.add(course);
            System.out.println("Do you want to add a new class? (yes or no)");
            String input = cin.next();
            if (input.equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    public void assignCoursesToProfessor() {
        if (Courses.isEmpty()) {
            System.out.println("No courses defined. Please add courses first.");
            return;
        }
        while (true) {
            System.out.println("Enter Professor name: ");
            String name = cin.next();
            if (!manager.Professors.contains(name)) {
                System.out.println("Professor name doesn't exist. Please add the professor first.");
                return;
            }

            ArrayList<String> professorCourses = new ArrayList<>();
            professorCourses.add(name);

            boolean anyNewClass = false;
            for (String course : Courses) {
                if (!professorCourses.contains(course)) {
                    anyNewClass = true;
                    break;
                }
            }

            if (!anyNewClass) {
                System.out.println("No more new classes available to assign.");
                return;
            }

            while (true) {
                System.out.println("Select a course to assign to the professor:");
                int index = 1;
                for (String course : Courses) {
                    if (!professorCourses.contains(course)) {
                        System.out.println(index + ") " + course);
                        index++;
                    }
                }

                int choice;
                try {
                    choice = Integer.parseInt(cin.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                    continue;
                }

                if (choice > 0 && choice <= Courses.size()) {
                    int selectedIndex = 1;
                    Iterator<String> iterator = Courses.iterator();
                    while (iterator.hasNext()) {
                        String course = iterator.next();
                        if (!professorCourses.contains(course)) {
                            if (selectedIndex == choice) {
                                professorCourses.add(course);
                                break;
                            }
                            selectedIndex++;
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Please select a valid number.");
                }

                System.out.println("Do you want to add another course for this professor? (yes or no)");
                String input = cin.nextLine();
                if (input.equalsIgnoreCase("no")) {
                    break;
                }
            }

            Class.put(name, professorCourses);

            System.out.println("Courses assigned to professor successfully!");

            return;
        }
    }




    public void addStudents() {
        if (Class.isEmpty()) {
            System.out.println("No classes defined. Please assign courses to professors first.");
            return;
        }

        while (true) {
            System.out.println("Choose class to add students to:");
            int i = 1;
            for (String className : Class.keySet()) {
                System.out.println(i + ") " + className);
                i++;
            }
            System.out.println("Enter 'no' to cancel.");
            String input = cin.next();
            if (input.equalsIgnoreCase("no")) {
                break;
            }

            int classChoice;
            try {
                classChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid class number or 'no' to cancel.");
                continue;
            }

            if (classChoice < 1 || classChoice > Class.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            String selectedClassName = (String) Class.keySet().toArray()[classChoice - 1];
            ArrayList<String> selectedClass = Class.get(selectedClassName);

            while (true) {
                System.out.println("Enter student name for " + selectedClassName + ": ");
                String studentName = cin.next();
                students.add(studentName);
                System.out.println("Enter Password : ");
                String password = cin.next();
                Student_Login.put(studentName, password);
                selectedClass.add(studentName);
                System.out.println("Do you want to add another student to this class? (yes or no)");
                input = cin.next();
                if (input.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }
    }



    public void removeCourse() {
        System.out.println("Enter the name of the course to remove: ");
        String course = cin.nextLine();
        if (Courses.remove(course)) {
            System.out.println("Course removed.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void removeProfessor() {
        System.out.println("Enter the name of the professor to remove: ");
        String name = cin.nextLine();
        if (Professors.remove(name)) {
            System.out.println("Professor removed.");
            for (int i = 0; i < Class.size(); i++) {
                if (Class.get(i).get(0).equals(name)) {
                    Class.remove(i);
                    i--;
                }
            }
        } else {
            System.out.println("Professor not found.");
        }
    }

    public void changePassword() {
        System.out.println("Enter new Password : ");
        String newPass=cin.next();
        userAccount.setManagerPassword(newPass);
    }
}
