public class ClinicalChemistry {

    // 1. Fasting Blood Sugar
    public static String fbs(double value) {
        if (value < 74) return "LOW";
        if (value > 100) return "HIGH";
        return "NORMAL";
    }

    // 2. Random Blood Sugar
    public static String rbs(double value) {
        if (value < 74) return "LOW";
        if (value > 140) return "HIGH";
        return "NORMAL";
    }

    // 3. Total Cholesterol
    public static String totalCholesterol(double value) {
        if (value < 150) return "LOW";
        if (value > 200) return "HIGH";
        return "NORMAL";
    }

    // 4. HDL
    public static String hdl(double value, char sex) {
        if (sex == 'M') {
            if (value < 35) return "LOW";
            if (value > 88) return "HIGH";
        } else {
            if (value < 42) return "LOW";
            if (value > 88) return "HIGH";
        }
        return "NORMAL";
    }

    // 5. LDL
    public static String ldl(double value) {
        if (value < 50) return "LOW";
        if (value > 130) return "HIGH";
        return "NORMAL";
    }

    // 6. Triglycerides
    public static String triglycerides(double value, char sex) {
        if (sex == 'M') {
            if (value < 60) return "LOW";
            if (value > 165) return "HIGH";
        } else {
            if (value < 40) return "LOW";
            if (value > 140) return "HIGH";
        }
        return "NORMAL";
    }

    // 7. Creatinine
    public static String creatinine(double value, char sex) {
        if (sex == 'M') {
            if (value < 0.9) return "LOW";
            if (value > 1.3) return "HIGH";
        } else {
            if (value < 0.6) return "LOW";
            if (value > 1.2) return "HIGH";
        }
        return "NORMAL";
    }

    // 8. Uric Acid
    public static String uricAcid(double value, char sex) {
        if (sex == 'M') {
            if (value < 3.5) return "LOW";
            if (value > 7.2) return "HIGH";
        } else {
            if (value < 2.6) return "LOW";
            if (value > 6.0) return "HIGH";
        }
        return "NORMAL";
    }

    // 9. BUN
    public static String bun(double value) {
        if (value < 6.0) return "LOW";
        if (value > 20.0) return "HIGH";
        return "NORMAL";
    }

    // 10. AST / SGOT
    public static String ast(double value) {
        if (value > 46) return "HIGH";
        return "NORMAL";
    }

    // 11. ALT / SGPT
    public static String alt(double value) {
        if (value > 49) return "HIGH";
        return "NORMAL";
    }

    // 12. Sodium
    public static String sodium(double value) {
        if (value < 135) return "LOW";
        if (value > 145) return "HIGH";
        return "NORMAL";
    }

    // 13. Potassium
    public static String potassium(double value) {
        if (value < 3.5) return "LOW";
        if (value > 5.0) return "HIGH";
        return "NORMAL";
    }

    // 14. Chloride
    public static String chloride(double value) {
        if (value < 96) return "LOW";
        if (value > 110) return "HIGH";
        return "NORMAL";
    }

    // 15. Total Calcium
    public static String totalCalcium(double value) {
        if (value < 8.6) return "LOW";
        if (value > 10.28) return "HIGH";
        return "NORMAL";
    }

    // 16. Ionized Calcium
    public static String ionizedCalcium(double value) {
        if (value < 4.4) return "LOW";
        if (value > 5.2) return "HIGH";
        return "NORMAL";
    }
    public static String getReferenceRange(int testNumber, char sex) {
    switch (testNumber) {
        case 1: return "74–100 mg/dL (FBS)";
        case 2: return "74–140 mg/dL (RBS)";
        case 3: return "150–200 mg/dL (Total Cholesterol)";
        case 4: return sex == 'M' ? "35–88 mg/dL (HDL Male)" : "42–88 mg/dL (HDL Female)";
        case 5: return "50–130 mg/dL (LDL)";
        case 6: return sex == 'M' ? "60–165 mg/dL (Triglycerides Male)" : "40–140 mg/dL (Triglycerides Female)";
        case 7: return sex == 'M' ? "0.9–1.3 mg/dL (Creatinine Male)" : "0.6–1.2 mg/dL (Creatinine Female)";
        case 8: return sex == 'M' ? "3.5–7.2 mg/dL (Uric Acid Male)" : "2.6–6.0 mg/dL (Uric Acid Female)";
        case 9: return "6.0–20.0 mg/dL (BUN)";
        case 10: return "0–46 U/L (AST / SGOT)";
        case 11: return "0–49 U/L (ALT / SGPT)";
        case 12: return "135–145 mmol/L (Sodium)";
        case 13: return "3.5–5.0 mmol/L (Potassium)";
        case 14: return "96–110 mmol/L (Chloride)";
        case 15: return "8.6–10.28 mg/dL (Total Calcium)";
        case 16: return "4.4–5.2 mg/dL (Ionized Calcium)";
        default: return "Unknown range";
    }
}
}

