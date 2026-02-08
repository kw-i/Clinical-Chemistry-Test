import java.util.Scanner;

public class Main {

    // Patient info
    static String patientName;
    static char sex;

    // Store results
    static double[] testValues = new double[16];
    static String[] classifications = new String[16];
    static boolean[] hasValue = new boolean[16];

    static String[] testNames = {
            "FBS", "RBS", "Total Cholesterol", "HDL", "LDL", "Triglycerides",
            "Creatinine", "Uric Acid", "BUN", "AST / SGOT", "ALT / SGPT",
            "Sodium", "Potassium", "Chloride", "Total Calcium", "Ionized Calcium"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // PATIENT INFO (once)
        System.out.print("Enter patient name: ");
        patientName = scanner.nextLine();

        while (true) {
            System.out.print("Enter sex (M/F): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("M") || input.equals("F")) {
                sex = input.charAt(0);
                break;
            }
            System.out.println("Invalid sex. Enter M or F.");
        }

        // MAIN PROGRAM
        while (true) {
            System.out.print(
                    "\nMain Menu\n" +
                    "1. Enter Clinical Chemistry Results\n" +
                    "2. Display Results\n" +
                    "3. Exit\n" +
                    "Choice: "
            );

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    enterResults(scanner);
                } else if (choice == 2) {
                    displayResults();
                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Numbers only.");
            }
        }

        scanner.close();
    }

    // OPTION 1
    static void enterResults(Scanner scanner) {
        while (true) {
            System.out.println(
                    "\nSelect Test\n" +
                    "1. FBS\n2. RBS\n3. Total Cholesterol\n4. HDL\n5. LDL\n" +
                    "6. Triglycerides\n7. Creatinine\n8. Uric Acid\n9. BUN\n" +
                    "10. AST / SGOT\n11. ALT / SGPT\n12. Sodium\n13. Potassium\n" +
                    "14. Chloride\n15. Total Calcium\n16. Ionized Calcium\n" +
                    "17. Back"
            );

            System.out.print("Enter your choice: ");
            int testChoice;

            try {
                testChoice = Integer.parseInt(scanner.nextLine());
                if (testChoice == 17) break;
                if (testChoice < 1 || testChoice > 16) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                String range = ClinicalChemistry.getReferenceRange(testChoice, sex);
                System.out.println("Reference range: " + range);

                System.out.print("Enter test value: ");
                double value = Double.parseDouble(scanner.nextLine());

                String result;

                switch (testChoice) {
                    case 1: result = ClinicalChemistry.fbs(value); break;
                    case 2: result = ClinicalChemistry.rbs(value); break;
                    case 3: result = ClinicalChemistry.totalCholesterol(value); break;
                    case 4: result = ClinicalChemistry.hdl(value, sex); break;
                    case 5: result = ClinicalChemistry.ldl(value); break;
                    case 6: result = ClinicalChemistry.triglycerides(value, sex); break;
                    case 7: result = ClinicalChemistry.creatinine(value, sex); break;
                    case 8: result = ClinicalChemistry.uricAcid(value, sex); break;
                    case 9: result = ClinicalChemistry.bun(value); break;
                    case 10: result = ClinicalChemistry.ast(value); break;
                    case 11: result = ClinicalChemistry.alt(value); break;
                    case 12: result = ClinicalChemistry.sodium(value); break;
                    case 13: result = ClinicalChemistry.potassium(value); break;
                    case 14: result = ClinicalChemistry.chloride(value); break;
                    case 15: result = ClinicalChemistry.totalCalcium(value); break;
                    case 16: result = ClinicalChemistry.ionizedCalcium(value); break;
                    default: continue;
                }

                int index = testChoice - 1;
                testValues[index] = value;
                classifications[index] = result;
                hasValue[index] = true;

                System.out.println("Saved: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    // OPTION 2
    static void displayResults() {
        System.out.println("\nPatient Name: " + patientName);
        System.out.println("Sex: " + (sex == 'M' ? "Male" : "Female"));
        System.out.println("\n--- Clinical Chemistry Results ---");

        boolean empty = true;

        for (int i = 0; i < 16; i++) {
            if (hasValue[i]) {
                System.out.println(
                        testNames[i] + ": " +
                        testValues[i] + " → " +
                        classifications[i]
                );
                empty = false;
            }
        }

        if (empty) {
            System.out.println("No results entered yet.");
        }
    }
}