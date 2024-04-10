import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    static Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void newRequest() {
        System.out.println("Введите данные:\nФамилия Имя Отчество Дата_рождения Номер_телефона Пол");
        String data = scanner.nextLine();
        String[] datas = data.trim().split(" ");
        if (validEntry(datas)) {
            Person newPerson = new Person(datas);
            savePerson(newPerson);
            System.out.println("Данные сохранены");
        }
        newRequest();
    }

    private void savePerson(Person person) {
        try(FileWriter fr = new FileWriter(person.surname + ".txt", true)) {
            fr.write(String.format("%s\n", person.toString()));
            fr.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл");
            e.printStackTrace();
        }
    }

    private boolean validEntry(String[] entryArr) {
        List<String> inconsistencies = new ArrayList<>();
        if (entryArr.length != 6) {
            throw new RuntimeException("Неверное количество данных");
        }
        if (!entryArr[5].equalsIgnoreCase("m") && !entryArr[5].equalsIgnoreCase("f")) {
            inconsistencies.add("пол");
        }
        for (int i = 0; i < entryArr[4].length(); i++) {
            if (!"0123456789".contains(Character.toString(entryArr[4].charAt(i)))) {
                inconsistencies.add("номер телефона");
            }
        }
        String[] birthDate = entryArr[3].split("\\.");
        if (entryArr[3].length() != 10 && birthDate.length != 3) {
            inconsistencies.add("дата рождения");
        }
        else {
            List<String> dateInconsistencies = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                try {
                    int date = Integer.parseInt(birthDate[i]);
                    if (i == 0) {
                        if (date < 1 || date > 31) throw new NumberFormatException();
                    }
                    if (i == 1) {
                        if (date < 1 || date > 12) throw new NumberFormatException();
                    }
                    if (i == 2) {
                        if (date < 1 || date > Year.now().getValue()) throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    if (i == 0) dateInconsistencies.add("число");
                    if (i == 1) dateInconsistencies.add("месяц");
                    if (i == 2) dateInconsistencies.add("год");
                }
            }
            if (dateInconsistencies.size() > 0) {
                StringBuilder sb = new StringBuilder("дата рождения (");
                for (int i = 0; i < dateInconsistencies.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(dateInconsistencies.get(i));
                }
                sb.append(")");
                inconsistencies.add(sb.toString());
            }
        }
        if (inconsistencies.size() > 0) {
            throw new IncorrectlySpecified(inconsistencies);
        }
        return true;
    }
}
