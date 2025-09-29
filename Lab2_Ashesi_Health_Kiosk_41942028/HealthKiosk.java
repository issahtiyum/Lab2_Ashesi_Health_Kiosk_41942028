import java.util.Scanner;

public class HealthKiosk {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);


    char randomLetter = (char) (Math.random() * 26 + 65); // because the first letter in ASCII is 65 and there are 26 letters of the alphabet
    int num1 = (int) (Math.random() * 7) + 3;
    int num2 = (int) (Math.random() * 7) + 3;
    int num3 = (int) (Math.random() * 7) + 3;
    int num4 = (int) (Math.random() * 7) + 3;

    String randomId = "" + randomLetter + num1 + num2 + num3 + num4;

    boolean validCodeLength = randomId.length() == 5;
    boolean next4AreDigits = Character.isDigit(randomId.charAt(1)) && 
    Character.isDigit(randomId.charAt(2)) && Character.isDigit(randomId.charAt(3)) && 
    Character.isDigit(randomId.charAt(4));



    System.out.print("Enter your first name: ");
    String name = input.nextLine();
    char base = name.toUpperCase().charAt(0);
    char shiftedLetter = (char)('A' + (base - 'A' + 2) % 26);
    String last2chars = "" + randomId.charAt(3) + randomId.charAt(4);
    double metric = -100;
    int refinedMetric = -100;
    int triageOption = 0;


    if (validCodeLength && Character.isLetter(randomId.charAt(0)) && next4AreDigits) {
      System.out.println("ID OK");
    } else {
      if (!validCodeLength) 
        System.out.println("Invalid: Your code must have 5 characters");
      else if (!Character.isLetter(randomId.charAt(0))) 
        System.out.println("Invalid: The first character of your code must be a letter.");
      else if (!next4AreDigits) 
        System.out.println("Invalid: The last 4 characters of your code must be digits.");
    }


    

    System.out.print("Enter service code (P/L/T/C): ");
    char serviceCode = Character.toUpperCase(input.nextLine().charAt(0));
    

    switch(serviceCode) {
      case 'P' :
        System.out.println("Go to: Pharmacy Desk");
        break;
      case 'L' :
        System.out.println("Go to: Lab Desk");
        break;
      case 'T' :
        System.out.println("Go to: Triage Desk");
        System.out.println("\nEnter health metric:");
        System.out.println("1. BMI Quick Calc \n2. Dosage Round Up \n3. Simple Trig Helper\n");

        triageOption = input.nextInt();

        switch(triageOption) {
          case 1 :
            System.out.print("Enter your weight (in kg): ");
            double weightInKg = input.nextDouble();

            System.out.print("Enter your height (in meters): ");
            double heightInMeters = input.nextDouble();

            double bmi = weightInKg / Math.pow(heightInMeters, 2);
            bmi = Math.round(bmi * 10)/ 10.0;

            metric = bmi;
            refinedMetric = (int) Math.round(bmi);


            System.out.print("BMI: " + bmi + " Category: ");

            if (bmi < 18.5) System.out.println("Underweight");
            else if (18.5 <= bmi && bmi < 25) System.out.println("Normal");
            else if (25 <= bmi && bmi <30) System.out.println("Overweight");
            else if (30 <= bmi) System.out.println("Obese");

            break;
          case 2 :
            System.out.println("Enter the required dosage(mg): ");
            double dosage = input.nextDouble();
            int oneTablet = 250;
            
            int numOfTablets = (int) Math.ceil(dosage / oneTablet);

            metric = numOfTablets;
            refinedMetric = numOfTablets;
            
            System.out.println("Your required dosage is " + numOfTablets + " tablets");
            break;
          case 3 :
            System.out.print("\nEnter an angle in degrees: ");
            double angle = input.nextDouble();

            double sin = Math.sin(Math.toRadians(angle));
            double cos = Math.cos(Math.toRadians(angle));

            sin = Math.round(sin * 1000) / 1000.0;
            cos = Math.round(cos * 1000) / 1000.0;

            metric = sin;
            refinedMetric = (int) Math.round(100 * sin );


            System.out.println("sin: " + sin + "\ncos: " + cos);
            break;
        }
        break;
      case 'C' :
        System.out.println("Go to: Counselling Desk");
        break;
      default :
        System.out.println("Invalid Service Code");
        break;
    }

    // On a happy path, none of the calculations will reach -100.
    String displayCode = metric != -100 ? shiftedLetter + last2chars + "-" + refinedMetric : shiftedLetter + last2chars + "-NA"; // NA since we will not be able to obtain a value
    System.out.println("\nDisplay Code: " + displayCode);

    input.close();

		System.out.print("\nSummary: ");

    switch (serviceCode) {
			case 'P':
				System.out.println("PHARMACY | ID=" + randomId +" | Code=" + displayCode);
				break;
			case 'T':
				switch (triageOption) {
					case 1:
						System.out.println("TRIAGE | ID=" + randomId +" | BMI=" + metric + " | Code=" + displayCode);
						break;
					case 2:
						System.out.println("TRIAGE | ID=" + randomId +" | DOSAGE=" + metric + " | Code=" + displayCode);
						break;
					case 3:
						System.out.println("TRIAGE | ID=" + randomId +" | HELPER=" + metric + " | Code=" + displayCode);
						break;
					default:
						System.out.println("Invalid metric");
						break;
				}
				break;
			case 'L':
				System.out.println("LAB | ID=" + randomId +" | Code=" + displayCode);
				break;
			case 'C':
				System.out.println("COUNSELING | ID=" + randomId +" | Code=" + displayCode);
				break;
		}
  }
}