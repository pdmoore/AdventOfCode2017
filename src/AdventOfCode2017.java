import java.util.*;

public class AdventOfCode2017 {
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

    public static int checksumLine2(List<Integer> input) {
        List<Integer> sortedAscending = new ArrayList(input);
        Collections.sort(sortedAscending);
        List<Integer> sortedDescending = new ArrayList<>(sortedAscending);
        Collections.reverse(sortedDescending);

        for (int descend = 0; descend < sortedDescending.size(); descend++) {
            for (int ascend = 0; ascend < sortedAscending.size() - descend - 1; ascend++) {
                if (sortedDescending.get(descend) % sortedAscending.get(ascend) == 0) {
                    return sortedDescending.get(descend) / sortedAscending.get(ascend);
                }
            }
        }

        return 0;
    }

    public static int checksum2(List<List<Integer>> input) {
        int checksum = 0;
        for (int i = 0; i < input.size(); i++) {
            checksum += checksumLine2(input.get(i));
        }
        return checksum;
    }

    public static boolean isPassphraseValid(String passphrase) {
        String[] wordsInPhrase = passphrase.split(" ");
        Set<String> uniqueWords = new TreeSet<>();
        for (String word :
                wordsInPhrase) {
            uniqueWords.add(word);
        }
        return wordsInPhrase.length == uniqueWords.size();
    }

    public static boolean isPassphraseValid_2(String passphrase) {
        String[] wordsInPhrase = passphrase.split(" ");
        Set<String> uniqueWords = new TreeSet<>();
        for (String word :
                wordsInPhrase) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            uniqueWords.add(new String(chars));
        }
        return wordsInPhrase.length == uniqueWords.size();
    }

    public static int mazeSteps(int[] maze) {
        int currentIndex = 0;
        int steps = 0;
        while (currentIndex >= 0 && currentIndex < maze.length) {
            steps++;
            int prevIndex = currentIndex;
            currentIndex += maze[currentIndex];
            maze[prevIndex] += 1;
        }
        return steps;
    }

    public static int mazeSteps_2(int[] maze) {
        int currentIndex = 0;
        int steps = 0;
        while (currentIndex >= 0 && currentIndex < maze.length) {
            steps++;
            int prevIndex = currentIndex;
            int offset = maze[currentIndex];
            currentIndex += offset;
            if (offset >= 3) maze[prevIndex] -= 1;
            else maze[prevIndex] += 1;
        }
        return steps;
    }

    public static int[] redistribute(int[] blocks) {
        int maxIndex = findMaxIndex(blocks);
        int redistributeCout = blocks[maxIndex];
        blocks[maxIndex] = 0;
        int currentIndex = maxIndex + 1;
        while (redistributeCout > 0) {
            if (currentIndex == blocks.length) currentIndex = 0;
            blocks[currentIndex] += 1;
            redistributeCout--;
            currentIndex++;
        }
        return blocks;
    }

    private static int findMaxIndex(int[] blocks) {
        int maxIndex = 0;
        for (int i = 1; i < blocks.length; i++) {
            if (blocks[i] > blocks[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }

    public static int[] day10_reverse(int[] input, int currentPosition, int segmentLength) {
        int[] subsegment = new int[segmentLength];
        int newSegmentIndex = 0;
        int i = currentPosition;
        while (newSegmentIndex < segmentLength) {
            subsegment[newSegmentIndex++] = input[i++];
            if (i >= input.length) i = 0;
        }

        int copySegmentIndex = currentPosition;
        for (int j = segmentLength - 1; j >= 0; j--) {
            input[copySegmentIndex++] = subsegment[j];
            if (copySegmentIndex >= input.length) copySegmentIndex = 0;
        }
        return input;
    }

    public static int[] day10_knotHash(int length, int[] inputLengths) {
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[i] = i;
        }

        int currentPosition = 0;
        int skipSize = 0;
        for (int eachLength = 0; eachLength < inputLengths.length; eachLength++) {
            day10_reverse(output, currentPosition, inputLengths[eachLength]);
            currentPosition += inputLengths[eachLength] + skipSize;
            if (currentPosition > length) currentPosition -= length;
            skipSize++;
        }

        return output;
    }

    public static int[][] day3_growGrid(int[][] startWith) {
        int previousSize = startWith[0].length;
        int nextGrid[][] = new int[previousSize + 2][previousSize + 2];
        //copy the existing one into the middle of the new one
        for (int i = 0; i < previousSize; i++) {
            for (int j = 0; j < previousSize; j++) {
                nextGrid[i+1][j+1] = startWith[i][j];
            }
        }
        int nextVal = startWith[previousSize - 1][previousSize - 1] + 1;
        //fill in the rightmost edge
        for (int k = previousSize; k > 0; k--) {
            nextGrid[k][previousSize + 1] = nextVal++;
        }
        //fill in the topmost line
        for (int k = previousSize + 1; k >= 0; k--) {
            nextGrid[0][k] = nextVal++;
        }
        // fill in leftmost edge
        for (int k = 1; k <= previousSize; k++) {
            nextGrid[k][0] = nextVal++;
        }
        //fill in bottom edge
        for (int k = 0; k <= previousSize + 1; k++) {
            nextGrid[previousSize + 1][k] = nextVal++;
        }
        return nextGrid;
    }

    public static class Day6Result {
        public int stepCount;
        public int cycleCount;
    }

    public static Day6Result redistributeUntilCycle(int[] block) {
        List<List<Integer>> bankConfiguration = new ArrayList<>();
        Map<Integer, List<Integer>> seenBlocks = new HashMap<>();
        int stepCount = 0;

        List<Integer> intList = convertIntArrayToList(block);
        while (bankConfiguration.contains(intList) == false) {
            bankConfiguration.add(intList);
            stepCount++;

            seenBlocks.put(stepCount, intList);

            redistribute(block);
            intList = convertIntArrayToList(block);
        }

        Day6Result result = new Day6Result();
        result.stepCount = stepCount;
        result.cycleCount = stepCount - whatStepWasValueFirstAdded(seenBlocks, intList) + 1;
        return result;
    }

    private static int whatStepWasValueFirstAdded(Map<Integer, List<Integer>> mapOfIntegerList, List<Integer> target) {
        for (Map.Entry<Integer, List<Integer>> entry : mapOfIntegerList.entrySet()) {
            if (Arrays.equals(target.toArray(), entry.getValue().toArray())) return entry.getKey().intValue();
        }

        return -1;
    }

    private static List<Integer> convertIntArrayToList(int[] block) {
        List<Integer> intlist = new ArrayList<>();
        for (int i = 0; i < block.length; i++) {
            intlist.add(block[i]);
        }
        return intlist;
    }

    public static int processStream(String stream) {
        int totalScore = 0;
        int indentCount = 0;
        boolean handlingGarbage = false;
        for (int i = 0; i < stream.length(); i++) {
            char current = stream.charAt(i);
            if (current == '!') {
                i++;
            } else {

                if (handlingGarbage == false) {
                    if (current == '<') handlingGarbage = true;
                    else if (current == '{') indentCount++;
                    else if (current == '}') {
                        totalScore += indentCount;
                        indentCount--;
                    }
                } else {
                    if (current == '>') handlingGarbage = false;
                }
            }
        }
        return totalScore;
    }

    public static int processStream_2(String stream) {
        int garbageCount = 0;
        boolean handlingGarbage = false;
        for (int i = 0; i < stream.length(); i++) {
            char current = stream.charAt(i);
            if (current == '!') {
                i++;
            } else {

                if (handlingGarbage == false) {
                    if (current == '<') handlingGarbage = true;
                } else {
                    if (current == '>') handlingGarbage = false;
                    else garbageCount++;
                }
            }
        }
        return garbageCount;
    }
}
