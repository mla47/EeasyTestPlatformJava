public abstract class Detail {
    private String Password;
    private String LastName;
    private String FatherName;
    private String NationalCode;
    private String Gender;
    private byte Age;

    private String NumberOfUnits;

    public void setNumberOfUnits(String numberOfUnits) {
        NumberOfUnits = numberOfUnits;
    }
    public String getNumberOfUnits(){
        return NumberOfUnits;
    }
    public void setPassword(String Password) {
         this.Password = Password;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setAge(byte age) {
        Age = age;
    }

    public String getPassword() {
        return Password;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public String getGender() {
        return Gender;
    }

    public byte getAge() {
        return Age;
    }
}
