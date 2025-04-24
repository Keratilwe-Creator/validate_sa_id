# validate_sa_id

A Java project to validate South African ID numbers according to official format and checksum rules.

## How to Run and Test This Project

### 1. Prerequisites
- Java JDK 11 or newer (JDK 21 recommended)
- Git (to clone the repo)
- Gradle (the project includes a Gradle wrapper, so no need to install Gradle separately)

### 2. Clone the Repository
```
git clone https://github.com/Keratilwe-Creator/validate_sa_id.git
cd validate_sa_id
```

### 3. Run All Unit Tests
To run the tests and see which tests pass or fail:
```
./gradlew test
```
On Windows PowerShell, use:
```
.\gradlew.bat test
```

You will see output for each test, e.g.:
```
SaIdValidatorTest > testValidSouthAfricanIds() PASSED
SaIdValidatorTest > testInvalidLength() PASSED
...
```

### 4. Project Structure
- `app/src/main/java/validate_sa_id/SaIdValidator.java` – Main validator logic
- `app/src/test/java/validate_sa_id/SaIdValidatorTest.java` – JUnit tests for the validator
- `app/build.gradle` – Build and test configuration

### 5. Troubleshooting
- If you get permission errors or issues with the Gradle cache, try deleting the `.gradle` folder in your user directory and rerun the commands.
- Make sure you are running commands from the project root directory.

### 6. Customization
- To add more tests, edit `SaIdValidatorTest.java`.
- For detailed test output, see the configuration in `app/build.gradle`.

---

If you have any issues or want to add features, feel free to open an issue or pull request!
