import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager implements AbstractArray{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);
    Scanner cin = new Scanner(System.in);
    private String educationOfficerUsername;
    private String educationOfficerPassword;
    UserAccount userAccount;
    mainDetail md = new mainDetail();
    private static final String PROFESSORS_FILE_PATH = "C:\\Users\\Aria\\OneDrive\\Desktop\\Platform\\Project_Main_Java\\Professors.txt";
    public ArrayList<String> managerDetail = new ArrayList<>();

    public Manager() {}

    public Manager(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getEducationOfficerUsername() {
        return this.educationOfficerUsername;
    }

    public void setEducationOfficerUsername(String educationOfficerUsername) {
        this.educationOfficerUsername = educationOfficerUsername;
    }

    public String getEducationOfficerPassword() {
        return this.educationOfficerPassword;
    }

    public void setEducationOfficerPassword(String educationOfficerPassword) {
        this.educationOfficerPassword = educationOfficerPassword;
    }

    public void manager_option() {
        while (true) {
            System.out.println("Choose option : ");
            System.out.println(ANSI_GREEN + "1) Add EducationOfficer" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2) Add Professor" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3) Change Password" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "4) Add Detail" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "5) View Detail" + ANSI_RESET);
            System.out.println(ANSI_RED + "6) Quit" + ANSI_RESET);
            int choose=cin.nextInt();
            switch (choose) {
                case 1:
                    addEducationOfficer();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    changePassword();
                    break;
                case 4:
                    addDetail();
                    break;
                case 5:
                    viewDetail();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    public void addDetail() {
        System.out.println("Enter Manager profile information");
        System.out.print("First Name: ");
        md.setFirst_name_m(cin.next());
        managerDetail.add(md.getFirst_name_m());

        System.out.print("Last Name: ");
        md.setLast_name_m(cin.next());
        managerDetail.add(md.getLast_name_m());

        System.out.print("National Code: ");
        md.setNationalCode_m(cin.next());
        managerDetail.add(md.getNationalCode_m());

        System.out.print("Father's Name: ");
        md.setFather_name_m(cin.next());
        managerDetail.add(md.getFather_name_m());

        try {
            System.out.print("Age: ");
            md.setAge_m(cin.nextByte());
            managerDetail.add(Byte.toString(md.getAge_m()));
        } catch (Exception e) {
            System.out.println("Invalid input " + e.getMessage());
        }

        System.out.print("Gender (Male/Female): ");
        md.setGender_m(cin.next());
        managerDetail.add(md.getGender_m());

        System.out.print("Address: ");
        cin.nextLine(); // To consume the newline character left by nextByte()
        md.setAddress_m(cin.nextLine());
        managerDetail.add(md.getAddress_m());

        System.out.println("Profile information set successfully!");
    }

    public void viewDetail() {
        if (managerDetail.isEmpty()) {
            System.out.println("Detail is Empty!");
        } else {
            System.out.println("Manager Detail: ");
            System.out.println("First Name : "+md.getFirst_name_m());
            System.out.println("Last Name : "+md.getLast_name_m());
            System.out.println("National code : "+md.getNationalCode_m());
            System.out.println("Father name : "+md.getFather_name_m());
            System.out.println("Gender : "+md.getGender_m());
            System.out.println("Address : "+md.getAddress_m());
        }
    }

    public void addEducationOfficer() {
        System.out.println("Enter EducationOfficer Username: ");
        String educationOfficerUsername = cin.next();
        System.out.println("Enter EducationOfficer Password: ");
        String educationOfficerPassword = cin.next();
        setEducationOfficerUsername(educationOfficerUsername);
        setEducationOfficerPassword(educationOfficerPassword);
    }

    public void addProfessor() {
        while (true) {
            System.out.println("Enter Professor name: ");
            String name = cin.next();

            // Check if the professor already exists
            if (Professors.contains(name)) {
                System.out.println("Professor already exists!");
                continue; // Prompt again for a unique professor name
            }

            System.out.println("Enter Professor password: ");
            String password = cin.next();

            // Add the professor to the list and map
            Professors.add(name);

            Professor_Login.put(name, password);
            // Write the professor name and password to file
            writeProfessors(name, password);

            System.out.println("Professor added successfully!");

            System.out.println("Do you want to add another Professor? (yes or no)");
            String input = cin.next();
            if (input.equalsIgnoreCase("no")) {
                break;
            }
        }
    }


    public void changePassword() {
        System.out.println("Enter new Password: ");
        String managerPassword = cin.next();
        userAccount.setManagerPassword(managerPassword);
    }

    public void writeProfessors(String name, String password) {
        try (FileWriter fw = new FileWriter(PROFESSORS_FILE_PATH, true)) {
            fw.write(name + " " + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
