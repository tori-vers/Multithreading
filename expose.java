import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class expose extends Thread {
    private String section2Value;

    public expose(String section2Value) {

        this.section2Value = section2Value;

    }

    @Override
    public void run() {
        for (int i=0; i <= 30; i++) { //print lines 30 times 
        processSection2(section2Value);
    }
}


    private void processSection2(String section2Value) {
        // comma delimiter 
        Pattern scorePattern = Pattern.compile("\\s*,\\s*");
        String[] scoreTokens = scorePattern.split(section2Value);

        List<Integer> validScores = new ArrayList<>();
        List<Integer> outliers = new ArrayList<>();
        int sum = 0;
        
        // parse through scores 
        for (String scoreToken : scoreTokens) {
            try {
                int score = Integer.parseInt(scoreToken);

                if (score >= -5 && score <= 100) { //range [-5, 100]
                    validScores.add(score);
                    sum += score;
                } else {
                }
            } catch (NumberFormatException e) {
                System.out.println("Error scoreToken");
            }
        }

        int n = validScores.size();
        double average = (double) sum / n;

        // Format average and standard deviation to two decimal places
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedAverage = df.format(average);

        // Calculate the standard deviation
        double sumOfSquaredDifferences = 0;
        for (int score : validScores) {
            double diff = score - average;
            sumOfSquaredDifferences += diff * diff;
        }
        double standardDeviation = Math.sqrt(sumOfSquaredDifferences / n);
        String formattedStandardDeviation = df.format(standardDeviation);

        // Find outliers
        for (int score : validScores) {
            if (Math.abs(score - average) > 2 * standardDeviation) {
                outliers.add(score);
            }
        }

        // Print results
        System.out.println();
        System.out.print("SECTION TWO IS: ");
        for (int score : validScores) {
            System.out.print(score + ",");
        }
        System.out.println();
        System.out.println("NUMBER OF VALID SCORES: " + n);
        System.out.println("THE AVERAGE IS: " + formattedAverage);
        System.out.println("S.D. IS: " + formattedStandardDeviation);
        
        if (outliers.isEmpty()) {
            System.out.println("OUTLIERS ARE: none");
        } else {
            System.out.print("OUTLIERS ARE: ");
            for (int outlier : outliers) {
                System.out.print(outlier + " ");
            }
            System.out.println();
        }
    }
}
