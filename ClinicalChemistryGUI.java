import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClinicalChemistryGUI extends JFrame {

    private JComboBox<String> testCombo;
    private JTextField valueField;
    private JRadioButton maleBtn, femaleBtn;
    private JLabel resultLabel;
    private JTextArea rangeArea;

    private String patientName;

    // Store results (test number -> result text)
    private Map<Integer, String> testResults = new LinkedHashMap<>();

    public ClinicalChemistryGUI() {

        // ===== Ask for Patient Name =====
        patientName = JOptionPane.showInputDialog(
                null,
                "Enter Patient Name:",
                "Patient Information",
                JOptionPane.QUESTION_MESSAGE
        );

        if (patientName == null || patientName.trim().isEmpty()) {
            patientName = "Unknown Patient";
        }

        // ===== Frame Setup =====
        setTitle("Clinical Chemistry Analyzer");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== Header =====
        JLabel patientLabel = new JLabel("Patient: " + patientName);
        patientLabel.setFont(new Font("Arial", Font.BOLD, 16));
        patientLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(230, 240, 255));
        headerPanel.add(patientLabel);

        add(headerPanel, BorderLayout.NORTH);

        // ===== Form Panel =====
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));

        testCombo = new JComboBox<>(new String[]{
                "1. FBS", "2. RBS", "3. Total Cholesterol", "4. HDL",
                "5. LDL", "6. Triglycerides", "7. Creatinine", "8. Uric Acid",
                "9. BUN", "10. AST", "11. ALT", "12. Sodium",
                "13. Potassium", "14. Chloride", "15. Total Calcium", "16. Ionized Calcium"
        });

        valueField = new JTextField();

        maleBtn = new JRadioButton("Male", true);
        femaleBtn = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleBtn);
        sexGroup.add(femaleBtn);

        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sexPanel.add(maleBtn);
        sexPanel.add(femaleBtn);

        JButton checkBtn = new JButton("Check & add Result");
        JButton viewAllBtn = new JButton("View All Results");

        formPanel.add(new JLabel("Select Test:"));
        formPanel.add(testCombo);

        formPanel.add(new JLabel("Enter Value:"));
        formPanel.add(valueField);

        formPanel.add(new JLabel("Sex:"));
        formPanel.add(sexPanel);

        formPanel.add(checkBtn);
        formPanel.add(viewAllBtn);

        add(formPanel, BorderLayout.CENTER);

        // ===== Result Panel =====
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        rangeArea = new JTextArea();
        rangeArea.setEditable(false);
        rangeArea.setLineWrap(true);
        rangeArea.setWrapStyleWord(true);
        rangeArea.setBackground(getBackground());

        resultPanel.add(resultLabel);
        resultPanel.add(rangeArea);

        add(resultPanel, BorderLayout.SOUTH);

        // ===== Actions =====
        checkBtn.addActionListener(e -> calculate());
        viewAllBtn.addActionListener(e -> showAllResults());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculate() {
        try {
            double value = Double.parseDouble(valueField.getText());
            int test = testCombo.getSelectedIndex() + 1;
            char sex = maleBtn.isSelected() ? 'M' : 'F';

            String interpretation;
            String testName = testCombo.getSelectedItem().toString();

            switch (test) {
                case 1: interpretation = ClinicalChemistry.fbs(value); break;
                case 2: interpretation = ClinicalChemistry.rbs(value); break;
                case 3: interpretation = ClinicalChemistry.totalCholesterol(value); break;
                case 4: interpretation = ClinicalChemistry.hdl(value, sex); break;
                case 5: interpretation = ClinicalChemistry.ldl(value); break;
                case 6: interpretation = ClinicalChemistry.triglycerides(value, sex); break;
                case 7: interpretation = ClinicalChemistry.creatinine(value, sex); break;
                case 8: interpretation = ClinicalChemistry.uricAcid(value, sex); break;
                case 9: interpretation = ClinicalChemistry.bun(value); break;
                case 10: interpretation = ClinicalChemistry.ast(value); break;
                case 11: interpretation = ClinicalChemistry.alt(value); break;
                case 12: interpretation = ClinicalChemistry.sodium(value); break;
                case 13: interpretation = ClinicalChemistry.potassium(value); break;
                case 14: interpretation = ClinicalChemistry.chloride(value); break;
                case 15: interpretation = ClinicalChemistry.totalCalcium(value); break;
                case 16: interpretation = ClinicalChemistry.ionizedCalcium(value); break;
                default: interpretation = "ERROR";
            }

            String range = ClinicalChemistry.getReferenceRange(test, sex);

            // ===== Confirmation Dialog =====
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm adding this result?\n\n" +
                    testName + "\n" +
                    "Value: " + value + "\n" +
                    "Result: " + interpretation,
                    "Confirm Result",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // ===== Store / Replace Result =====
            String storedResult =
                    testName + "\n" +
                    "Value: " + value + "\n" +
                    "Result: " + interpretation + "\n" +
                    "Range: " + range + "\n";

            testResults.put(test, storedResult);

            resultLabel.setText("Result: " + interpretation);
            rangeArea.setText("Reference Range: " + range);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid numeric value.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void showAllResults() {
        if (testResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No test results available yet.");
            return;
        }

        StringBuilder allResults = new StringBuilder();
        allResults.append("Patient: ").append(patientName).append("\n\n");

        for (String result : testResults.values()) {
            allResults.append(result)
                      .append("-------------------------\n");
        }

        JTextArea displayArea = new JTextArea(allResults.toString());
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(450, 300));

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "COMPILED REPORTS",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}