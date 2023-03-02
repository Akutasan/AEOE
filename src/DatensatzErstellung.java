import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DatensatzErstellung {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        char randomPlanet = (char) (r.nextInt(26) + 'A');
        int randomQ = r.nextInt(10 + 1 - 1) + 1;
        int randomLength = r.nextInt(100 + 1 - 1) + 1;
        int randomWidth = r.nextInt(100 + 1 - 1) + 1;

        String filename = "src\\docs\\Planet" + randomPlanet + "-Q" + randomQ + "_" + randomLength + "x" + randomWidth;

        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        char[] options = {'x', 'x', 'x', 'x', 'x', 'x', 's', 'u', 'g', 'z', 'k'};
        char[][] result = new char[randomLength][randomWidth];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = options[r.nextInt(options.length)];
            }
        }

        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));
        for (char[] chars : result) {
            String formatted = String.valueOf(chars).replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .trim();
            ow.write(formatted + "");
            ow.newLine();
        }
        ow.flush();
        ow.close();
    }
}
