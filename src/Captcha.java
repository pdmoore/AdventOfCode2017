import java.util.List;

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

    public static int checksum(int[] input) {
        int max = input[0];
        int min = max;
        for (int i = 1; i < input.length; i++) {
            if (input[i] > max) max = input[i];
            if (input[i] < min) min = input[i];
        }
        return max - min;
    }

    public static int checksum(List<List<Integer>> input) {
        int checksum = 0;
        for (int i = 0; i < input.size(); i++) {
            checksum += checksumLine(input.get(i));
        }
        return checksum;
    }

    private static int checksumLine(List<Integer> input) {
        int max = input.get(0);
        int min = max;
        for (int i = 1; i < input.size(); i++) {
            int currValue = input.get(i);
            if (currValue > max) max = currValue;
            if (currValue < min) min = currValue;
        }
        return max - min;
    }
}
