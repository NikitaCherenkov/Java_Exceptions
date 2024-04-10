public class Person {
    String surname;
    String name;
    String patronymic;
    String birthDate;
    String phoneNumber;
    String gender;

    public Person(String surname, String name, String patronymic, String birthDate, String phone, String gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phoneNumber = phone;
        this.gender = gender;
    }

    public Person(String[] allMatches) {
        this.surname = allMatches[0];
        this.name = allMatches[1];
        this.patronymic = allMatches[2];
        this.birthDate = allMatches[3];
        this.phoneNumber = allMatches[4];
        this.gender = allMatches[5];
    }

    @Override
    public String toString() {
        return String.format("<%s> <%s> <%s> <%s> <%s> <%s>", surname, name, patronymic, birthDate, phoneNumber, gender);
    }
}
