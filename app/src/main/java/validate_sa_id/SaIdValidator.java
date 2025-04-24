package validate_sa_id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SaIdValidator {
    
    public ValidationResult validateId(String idNumber) {
        // Check if ID is null or empty
        if (idNumber == null || idNumber.isEmpty()) {
            return new ValidationResult(false, "ID number cannot be null or empty");
        }

        // Check length
        if (idNumber.length() != 13) {
            return new ValidationResult(false, "ID number must be 13 digits long");
        }

        // Check if all characters are digits
        if (!idNumber.matches("\\d+")) {
            return new ValidationResult(false, "ID number must contain only digits");
        }

        // Extract components
        String birthDate = idNumber.substring(0, 6);
        String genderDigits = idNumber.substring(6, 10);
        String citizenshipDigit = idNumber.substring(10, 11);
        String residenceStatus = idNumber.substring(11, 12);
        String checkDigit = idNumber.substring(12);

        // Validate birth date
        if (!isValidDate(birthDate)) {
            return new ValidationResult(false, "Invalid birth date");
        }

        // Validate gender
        int genderNum = Integer.parseInt(genderDigits);
        if (genderNum < 0 || genderNum > 9999) {
            return new ValidationResult(false, "Invalid gender digits");
        }

        // Validate citizenship
        if (!citizenshipDigit.matches("[01]")) {
            return new ValidationResult(false, "Invalid citizenship digit");
        }

        // Validate residence status
        if (!residenceStatus.equals("8") && !residenceStatus.equals("9")) {
            return new ValidationResult(false, "Invalid residence status");
        }

        // Validate Luhn algorithm checksum
        if (!validateLuhnChecksum(idNumber)) {
            return new ValidationResult(false, "Invalid checksum");
        }

        String gender = genderNum < 5000 ? "Female" : "Male";
        String citizenship = citizenshipDigit.equals("0") ? "SA Citizen" : "Permanent Resident";
        
        return new ValidationResult(true, 
            String.format("Valid SA ID: Birth Date: %s, Gender: %s, %s", 
                formatBirthDate(birthDate), gender, citizenship));
    }

    private boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate.parse(dateStr, formatter);
            int month = Integer.parseInt(dateStr.substring(2, 4));
            int day = Integer.parseInt(dateStr.substring(4, 6));
            return month >= 1 && month <= 12 && day >= 1 && day <= 31;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String formatBirthDate(String dateStr) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyMMdd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return dateStr;
        }
    }

    private boolean validateLuhnChecksum(String idNumber) {
        int sum = 0;
        boolean alternate = false;
        
        for (int i = idNumber.length() - 2; i >= 0; i--) {
            int n = Integer.parseInt(idNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == Integer.parseInt(idNumber.substring(12));
    }
}

class ValidationResult {
    private final boolean valid;
    private final String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}