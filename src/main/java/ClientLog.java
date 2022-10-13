import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientLog {

    private List<String[]> clientLog = new ArrayList<>();

    public void log(int productNum, int amount) {
        String[] log = {String.valueOf(productNum), String.valueOf(amount)};
        clientLog.add(log);
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (FileWriter out = new FileWriter(txtFile)) {
            out.write("productNum, amount" + "\n");
            for (String[] line : clientLog) {
                out.write(line[0] + ", " + line[1] + "\n");
            }
        }
    }

    public void printlog() {
        clientLog.stream()
                .map(list -> Arrays.stream(list).toList())
                .forEach(System.out::println);
    }
}

