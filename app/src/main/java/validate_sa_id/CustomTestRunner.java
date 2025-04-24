package validate_sa_id;

public class CustomTestRunner {
    public static void main(String[] args) {
        SaIdValidator validator = new SaIdValidator();
        boolean allPassed = true;
        
        String[] testIds = {
            "8001015009083" // Add more valid IDs if needed
        };
        
        for (String id : testIds) {
            ValidationResult result = validator.validateId(id);
            if (result.isValid()) {
                System.out.println("PASS: " + id + " => " + result.getMessage());
            } else {
                System.out.println("FAIL: " + id + " => " + result.getMessage());
                allPassed = false;
            }
        }
        
        if (allPassed) {
            System.out.println("\nALL TESTS PASSED");
        } else {
            System.out.println("\nSOME TESTS FAILED");
        }
    }
}
