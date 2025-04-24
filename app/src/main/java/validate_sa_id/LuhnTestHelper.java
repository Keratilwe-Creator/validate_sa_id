package validate_sa_id;

public class LuhnTestHelper {
    public static int calculateLuhnCheckDigit(String id12) {
        int sum = 0;
        boolean alternate = false;
        for (int i = id12.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(id12.substring(i, i + 1));
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
        return checkDigit;
    }

    public static void main(String[] args) {
        String id12 = "800101500908";
        int checkDigit = calculateLuhnCheckDigit(id12);
        System.out.println("Luhn check digit for " + id12 + " is: " + checkDigit);
        System.out.println("Full ID: " + id12 + checkDigit);
    }
}
