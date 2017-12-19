import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumbersDriver {

    public static String generateNumber(List<SevenSegmentNumber> numbers) throws NumbersException{
        String finalNumber = "";
        int garbledCount = 0;
        for(SevenSegmentNumber number : numbers){
            Optional<SevenSegmentNumber.Digit> match = findExactMatch(number);
            if(match.isPresent()){
                finalNumber += match.get().getStringRepresentation();
            }
            else{
                garbledCount++;
                Optional<SevenSegmentNumber.Digit> inexactMatch = findInExactMatch(number);
                assert inexactMatch.isPresent();
                finalNumber += inexactMatch.get().getStringRepresentation();
            }
            if(garbledCount > 1){
                throw new NumbersException(NumbersException.ErrorCode.TOO_MANY_GARBLED);
            }

        }
        return finalNumber;
    }

    public static Optional<SevenSegmentNumber.Digit> findExactMatch(SevenSegmentNumber number){
        for(SevenSegmentNumber.Digit digit : SevenSegmentNumber.Digit.values()){
            if(Arrays.deepEquals(number.getNumber(), digit.getArrayRepresentation())){
                return Optional.of(digit);
            }
        }
        return Optional.empty();
    }

    public static Optional<SevenSegmentNumber.Digit> findInExactMatch(SevenSegmentNumber number) throws NumbersException{
        int candidateCount = 0;
        SevenSegmentNumber.Digit digitMatch = null;
        for(SevenSegmentNumber.Digit digit : SevenSegmentNumber.Digit.values()) {
            if (number.couldBe(digit)) {
                digitMatch = digit;
                candidateCount++;
            }
        }
        if (candidateCount > 1) {
            throw new NumbersException(NumbersException.ErrorCode.TOO_MANY_CANDIDATES);
        }
        return Optional.ofNullable(digitMatch);

    }

    public static void main(String[] args){
        try {
            String[] lines = NumbersInput.parseInput();
            List<SevenSegmentNumber> sevenSegmentNumbers = NumbersInput.createSevenSegmentNumbers(NumbersInput.splitLines(lines));
            System.out.print(generateNumber(sevenSegmentNumbers));
        }catch(NumbersException e){
            System.out.println(e.getErrorCode().errorMessage());
        }
    }
}
