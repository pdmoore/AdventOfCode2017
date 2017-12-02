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

    public static int sum2(String input) {
        int captchaSum = 0;
        int offset = input.length() / 2;
        for (int i = 0; i < offset; i++) {
            if (input.charAt(i) == input.charAt(offset + i)) {
                captchaSum += Character.digit(input.charAt(i), 10);
            }
        }

        return captchaSum * 2;
    }
}
