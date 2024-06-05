import java.util.Scanner;

public class ProfessorDetail extends Detail{

    private Scanner cin = new Scanner(System.in);
    private MultiValueMap<String, String> professorsDetail = new MultiValueMap<>();
    private Manager m;
    private String years_Experience;
    private String MasterCode;
    public void setMasterCode(String MasterCode){
        this.MasterCode=MasterCode;
    }
    public String getMasterCode(){
        return MasterCode;
    }
    public void setYears_Experience(String years_Experience){
        this.years_Experience=years_Experience;
    }

    public String getYears_Experience() {
        return years_Experience;
    }

    public ProfessorDetail(){

    }
    public ProfessorDetail(Manager m) {
        this.m = m;
    }
    public void addDetail() {
        for (String professorName : m.Professors) {
            System.out.println("Enter details for Professor " + professorName + " : ");

            System.out.print("Enter Password: ");
            setPassword(cin.next());
            professorsDetail.put(professorName, "Password: " + getPassword());

            System.out.print("Enter Father Name: ");
            setFatherName(cin.next());
            professorsDetail.put(professorName, "Father Name: " + getFatherName());

            System.out.print("Enter National Code: ");
            setNationalCode(cin.next());
            professorsDetail.put(professorName, "National Code: " + getNationalCode());

            System.out.print("Enter Last Name: ");
            setLastName(cin.next());
            professorsDetail.put(professorName, "Last Name: " + getLastName());

            System.out.print("Enter Gender: ");
            setGender(cin.next());
            professorsDetail.put(professorName, "Gender: " + getGender());

            System.out.print("Enter Number of Units: ");
            setNumberOfUnits(cin.next());
            professorsDetail.put(professorName, "Number of Units: " + getNumberOfUnits());

            System.out.print("Enter Years_Experience : ");
            setYears_Experience(cin.next());
            professorsDetail.put(professorName, "Years: " +getYears_Experience());

            System.out.print("Enter MasterCode: ");
            setMasterCode(cin.next());
            professorsDetail.put(professorName, "MasterCode: " + getMasterCode());
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
}
