import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Color;
public class UserAccount implements AbsArray,tempstu,AbstractArray{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);

    public Scanner cin = new Scanner(System.in);
   // UserAccount u=new UserAccount();
    private Manager m=new Manager();
    private EducationOfficer ed=new EducationOfficer(m);
    private Professor p = new Professor(m,ed);
    private Student s = new Student(p,ed);
    //public HashMap<String,String> Professor_Login=new HashMap<>();
    private String managerUsername;
    private String managerPassword;
    private String educationOfficerUsername;
    private String educationOfficerPassword;
    private static final String ADMIN_USERNAME = "mla47";
    private static final String ADMIN_PASSWORD = "4747";
    public UserAccount() {

    }
    public void setManager(Manager manager) {
        this.m = manager;
    }

    public void setEducationOfficer(EducationOfficer educationOfficer) {
        this.ed = educationOfficer;
    }

    public void setProfessor(Professor professor) {
        this.p = professor;
    }

    public void setStudent(Student student) {
        this.s = student;
    }
    public UserAccount(Manager manager, EducationOfficer educationOfficer, Professor professor, Student student) {
        this.m = manager;
        this.ed = educationOfficer;
        this.p = professor;
        this.s = student;
    }
    public boolean isAdminLoggedIn() {
        while (true) {
            System.out.println("Admin sign in : ");
            System.out.println("Enter Username : ");
            String User = cin.next();
            System.out.println("Enter Password : ");
            String Pass = cin.next();
            if (User.equals(ADMIN_USERNAME) && Pass.equals(ADMIN_PASSWORD)) {
                System.out.println("Welcome to Admin Panel");
                return true;
            } else {
                System.out.println("Username or Password is wrong!\nPlease Enter correct Username and Password");
            }
        }
    }
    public void setManagerUsername(String managerUsername){
        this.managerUsername=managerUsername;
    }
    public String getManagerUsername(){
        return this.managerUsername;
    }
    public void setManagerPassword(String managerPassword){
        this.managerPassword=managerPassword;
    }
    public String getManagerPassword(){
        return this.managerPassword;
    }
    public void setEducationOfficerUsername(String educationOfficerUsername){
        this.educationOfficerUsername=educationOfficerUsername;
    }
    public String getEducationOfficerUsername(){
        return this.educationOfficerUsername;
    }
    public void setEducationOfficerPassword(String educationOfficerPassword) {
        this.educationOfficerPassword = educationOfficerPassword;
    }
    public String getEducationOfficerPassword(){
        return this.educationOfficerPassword=educationOfficerPassword;
    }


    public void setMainAccounts() {
        System.out.println("Set main members of the site:\n\n");
        System.out.println("Enter Username of manager: ");
        managerUsername = cin.next();
        setManagerUsername(managerUsername);
        System.out.println("Enter Password of manager: ");
        managerPassword = cin.next();
        setManagerPassword(managerPassword);
    }

    public void AdminPanel(){
        while(true){
            if(isAdminLoggedIn()){
                setMainAccounts();
                print();
                break;
            }
            else{
                System.out.println("Username of Password Wrong!");
            }
        }
    }
    public UserAccount(String managerUsername,String managerPassword,String educationOfficerUsername,
                       String educationOfficerPassword){
        this.managerUsername=getManagerUsername();
        this.managerPassword=getManagerPassword();
    }
    public void print() {
        String manager_username, manager_password;
        String educationOfficer_username, educationOfficer_password;
        String professor_username;
        while (true) {
            System.out.println("Enter option:\n");
            System.out.println(ANSI_GREEN + "1) Sign in Manager" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2) Sign in Education Officer" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3) Sign in Professor" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4) Sign in Student" + ANSI_RESET);
            System.out.println(ANSI_RED+"5) Close Program"+ANSI_RESET);
            int click = cin.nextInt();
            switch (click) {
                case 1:
                    System.out.println("Enter Manager Username : ");
                    manager_username = cin.next();
                    System.out.println("Enter Manager Password : ");
                    manager_password = cin.next();
                    if (manager_username.equals(getManagerUsername()) && manager_password.equals(getManagerPassword())) {
                        m.manager_option();
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;
                case 2:
                    try{
                        if(m.getEducationOfficerUsername().isEmpty()||m.getEducationOfficerPassword().isEmpty()){
                            throw new ArithmeticException("");
                        }
                        System.out.println("Enter Username EducationOfficer : ");
                        educationOfficer_username = cin.next();
                        System.out.println("Enter Password EducationOfficer : ");
                        educationOfficer_password = cin.next();
                        if (educationOfficer_username.equals(m.getEducationOfficerUsername()) &&
                                educationOfficer_password.equals(m.getEducationOfficerPassword())) {
                            ed.EducationOfficerOption();
                        } else {
                            System.out.println("Invalid username or password!");
                        }
                    }catch (Exception e){
                        System.out.println("The manager has not been defined educationOfficer!!!");
                        continue;
                    }
                    break;
                case 3:
                    System.out.println("Enter Professor Username : ");
                    professor_username = cin.next();
                    System.out.println("Enter professor password : ");
                    String professor_password=cin.next();
                    Professor_Login.containsValue(professor_password);
                    if(Professor_Login.containsKey(professor_username)&&Professor_Login.containsValue(professor_password)){
                        p.professorOption();
                    }
                    else{
                        System.out.println("Professor not found!!!");
                    }
                    break;
                case 4:
                    System.out.println("Enter Student username : ");
                    String user_name= cin.next();
                    System.out.println("Enter Student Password : ");
                    String password= cin.next();
                    if(Student_Login.containsKey(user_name)&&Student_Login.containsValue(password)){
                        s.studentsOption();
                    }
                    else{
                        System.out.println("Student not found!!!");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
}
