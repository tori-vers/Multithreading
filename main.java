import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        
        List<String> section1Values = new ArrayList<>();
        List<String> section2Values = new ArrayList<>();
        List<String> section3Values = new ArrayList<>();

        // reading in the xyz text file and read the record lines
        String fileName = "xyz.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            // split up the sections based on asterisks and dollar symbols
            Pattern pattern = Pattern.compile("(\\*+)([^$]+)\\$");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                int sectionCount = 0; // Count the number of sections in the record

                while (matcher.find()) {
                    int asteriskCount = matcher.group(1).length();
                    String section = matcher.group(2);

                    if (asteriskCount == 1) {

                        section1Values.add(section.trim());

                    } else if (asteriskCount == 2) {

                        section2Values.add(section.trim());

                    } else if (asteriskCount == 3) {

                        section3Values.add(section.trim());

                    }

                    sectionCount++;
                }

                if (sectionCount < 3) {
                    System.out.println("Record is invalid");
                } else {

                    // Create threads for valid records // reveal
                    for (String section1Value : section1Values) {
                        reveal thread1 = new reveal(section1Value);
                        thread1.run();
                    }

                    // expose thread
                    for (String section2Value : section2Values) {
                        expose thread2 = new expose(section2Value);
                        thread2.run();
                    }

                    // generator thread
                    for (String section3Value : section3Values) {
                        generator thread3 = new generator(section3Value);
                        thread3.start();
                    }
                }

                // Clear the section lists for the next record
                section1Values.clear();
                section2Values.clear();
                section3Values.clear();
            }

            br.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
