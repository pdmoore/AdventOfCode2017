public class Captcha {
    public static int sum(String inputAsString) {
        int captchaSum = 0;

        int previousDigit = inputAsString.charAt(0);
        for (int i = 1; i < inputAsString.length(); i++) {
            if (inputAsString.charAt(i) == previousDigit) {
                captchaSum += Character.digit(previousDigit, 10);
            }
            previousDigit = inputAsString.charAt(i);
        }
        if (inputAsString.charAt(inputAsString.length() - 1) == inputAsString.charAt(0)) captchaSum += Character.digit(inputAsString.charAt(0), 10);

        return captchaSum;
    }
}
