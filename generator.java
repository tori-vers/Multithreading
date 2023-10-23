import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class generator extends Thread {

    private String section3Value;

    public generator(String section3Value) {

        this.section3Value = section3Value;

    }

    @Override
    public void run() { // loop and print 40 times
        for (int i = 0; i < 40; i++) {
            processSection3(section3Value, i + 1);
        }
    }

    private void processSection3(String section3Value, int parseThrough) {
        // comma delimiter 
        Pattern numberPattern = Pattern.compile("\\s*,\\s*");
        String[] numberTests = numberPattern.split(section3Value);

        List<Integer> numbers = new ArrayList<>();

        // Parsing
        for (String numbertest : numberTests) {
            try {
                int number = Integer.parseInt(numbertest);
                numbers.add(number);
            } catch (NumberFormatException e) {

                System.out.println("PARSING ERROR numberTests");

            }
        }

        if (numbers.isEmpty()) {
            System.out.println("No valid numbers.");
        } else {
            
            // Calculate the maximum (MAX) and minimum (MIN)
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int number : numbers) {
                if (number > max) {
                    max = number;
                }
                if (number < min) {
                    min = number;
                }
            }

            // print section three lines valid scores and OG values
            System.out.println();
            System.out.println("SECTION THREE IS: " + section3Value);
            System.out.println("NUMBER OF VALID SCORES: " + numbers.size());

        //    String password = "Max = " + max + " and Min = " + min;
       //     System.out.println("PASSWORD IS: " + password);

            // Generate NEW1 (MAX)
            List<Integer> new1 = new ArrayList<>();
            for (int number : numbers) {
                new1.add(max - number);
            }

            // Remove duplicates 
            Set<Integer> uniqueValues1 = new LinkedHashSet<>(new1);
            new1.clear();
            new1.addAll(uniqueValues1);

        //    System.out.println("NEW1 = " + new1);

            // generate NEW2 (MIN)
            List<Integer> new2 = new ArrayList<>();
            for (int number : numbers) {
                new2.add(number - min);
            }

            // Remove duplicates
            Set<Integer> uniqueValues2 = new LinkedHashSet<>(new2);
            new2.clear();
            new2.addAll(uniqueValues2);

            // remove duplicates b/w NEW1 and NEW2
            new2.removeAll(new1);

        //    System.out.println("NEW2 = " + new2);

            // Append NEW2 to NEW1 and create set M
            new1.addAll(new2);
            Set<Integer> M = new LinkedHashSet<>(new1);

        //    System.out.println("Set M = " + M);

            // Calculate N = M Mod 26
            List<Integer> N = new ArrayList<>();
            for (int element : M) {
                N.add(element % 26);
            }
         //   System.out.println("N = " + N);

            // Get letters from alphabet based on N pos
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            List<Character> letters = new ArrayList<>();
            for (int position : N) {
                if (position >= 0 && position < alphabet.length()) {
                    char letter = alphabet.charAt(position);
                    letters.add(letter);
                }
            }

            // Calculate Q = N Mod 10
            List<Integer> Q = new ArrayList<>();
            for (int element : N) {
                Q.add(element % 10);
            }

       //     System.out.println("Q = " + Q);

            String symbols = "!@#45%8967";
            List<Character> R = new ArrayList<>();
            for (int position : Q) {
                if (position >= 0 && position < symbols.length()) {
                    char symbol = symbols.charAt(position);
                    R.add(symbol);
                }
            }

       //     System.out.println("R: " + R);

            // Create the password 
            StringBuilder passString = new StringBuilder();
            int length = Math.min(R.size(), letters.size());

            for (int i = 0; i < length; i++) {
                passString.append(letters.get(i));
                passString.append(R.get(i));
            }

            //print password line 
            String password = passString.toString();
            System.out.println("Password: " + password + " (Iteration " + parseThrough + ")");
        }
    }
}
