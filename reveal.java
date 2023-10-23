import java.util.Calendar;


public class reveal extends Thread {
    private String section1Value;

    public reveal(String section1Value) {
        for(int i=0; i <= 30; i++) { //print lines 30 times
        this.section1Value = section1Value;
    }
}

    @Override
    public void run() {
        
        System.out.println("SECTION ONE IS: " + section1Value);
    
        String firstName = "missing";
        String lastName = "missing";
        String middleInitial = "missing";
        String birthDate = "missing";
    
        // Split the section 
        String[] elements = section1Value.split("[^a-zA-Z0-9]+");
    
        // find prefixes and assign them to their values
        for (String element : elements) {
            if (element.startsWith("F")) {
                firstName = element.substring(1);
            } else if (element.startsWith("L")) {
                lastName = element.substring(1);
            } else if (element.startsWith("M")) {
                middleInitial = element.substring(1);
            } else if (element.startsWith("B")) {
                String birthDateString = element.substring(1);
                birthDate = calculateAge(birthDateString);
            }
        }

        //printing out the values
        System.out.println("LAST NAME IS: " + lastName);
        System.out.println("FIRST NAME IS: " + firstName);
        System.out.println("MIDDLE INITIAL IS: " + middleInitial);
        System.out.println("AGE IS: " + birthDate + " years");
    }
    
    private String calculateAge(String birthDateString) {
        try {
            // get raw date
            String rawDate = birthDateString.replaceAll("[^0-9]", "");

            if (rawDate.length() == 8) {

                String month = rawDate.substring(0, 2);
                String day = rawDate.substring(2, 4);
                String year = rawDate.substring(4);
                int birthYear = Integer.parseInt(year);
                int birthMonth = Integer.parseInt(month);
                int birthDay = Integer.parseInt(day);

                // Get the current date using Calendar
                Calendar currentDate = Calendar.getInstance();
                int currentYear = currentDate.get(Calendar.YEAR);
                int currentMonth = currentDate.get(Calendar.MONTH) + 1; 
                int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

                int ageYears = currentYear - birthYear;
                int ageMonths = currentMonth - birthMonth;
                int ageDays = currentDay - birthDay;

                if (ageDays < 0) {
                    ageMonths--;
                    ageDays += 30; // 30 days in a month
                }

                if (ageMonths < 0) {
                    ageYears--;
                    ageMonths += 12; // 12 months in a year
                }

                String age = ageYears + " years, " + ageMonths + " months, and " + ageDays + " days";

                return age;
            }
            
        } catch (NumberFormatException e) {
            // if format != mmddyyyy
            System.out.println("Invalid");
        }

        return "missing"; 
    }
}
