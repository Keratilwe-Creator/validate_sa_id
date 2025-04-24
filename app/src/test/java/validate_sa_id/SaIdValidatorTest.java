package validate_sa_id;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaIdValidatorTest {
    
    private SaIdValidator validator;

    @BeforeEach
    void setUp() {
        validator = new SaIdValidator();
    }

    @Test
    void testValidSouthAfricanIds() {
        assertTrue(validator.validateId("0207394788076").isValid()); // Female
        assertTrue(validator.validateId("8001015009087").isValid()); // Male
        assertTrue(validator.validateId("7502200065083").isValid()); // Female
    }

    @Test
    void testInvalidLength() {
        assertFalse(validator.validateId("12345").isValid());
        assertFalse(validator.validateId("12345678901234").isValid());
    }

    @Test
    void testInvalidCharacters() {
        assertFalse(validator.validateId("02073947A8076").isValid());
    }

    @Test
    void testInvalidDate() {
        assertFalse(validator.validateId("0213394788076").isValid()); // Invalid month
        assertFalse(validator.validateId("0207324788076").isValid()); // Invalid day
    }

    @Test
    void testInvalidGender() {
        assertFalse(validator.validateId("020739A788076").isValid());
    }

    @Test
    void testInvalidCitizenship() {
        assertFalse(validator.validateId("0207394788276").isValid());
    }

    @Test
    void testNullAndEmptyInput() {
        assertFalse(validator.validateId(null).isValid());
        assertFalse(validator.validateId("").isValid());
    }

    public static void main(String[] args) {
        SaIdValidator validator = new SaIdValidator();
        ValidationResult result = validator.validateId("0207394788076");
        System.out.println("Is valid: " + result.isValid());
        System.out.println("Message: " + result.getMessage());
    }
}