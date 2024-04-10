import java.util.List;

public class IncorrectlySpecified extends RuntimeException{
    String message;

    public IncorrectlySpecified(List<String> inconsistencies){
        StringBuilder sb = new StringBuilder("Неверно указано: ");
        for (int i = 0; i < inconsistencies.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(inconsistencies.get(i));
        }
        message = sb.toString();
        System.err.println(message);
        super.printStackTrace();
    }
}
