import java.util.*;

public class NumbersInput {

    public static String[] parseInput() throws NumbersException{
        Scanner s = new Scanner(System.in);
        String[] lines = new String[3];
        int i = 0;
        while(i < 3 && s.hasNextLine()){
            String line = s.nextLine();
            if(line.length() != 27){
                throw new NumbersException(NumbersException.ErrorCode.INVALID_NUM_CHARS);
            }
            lines[i] = line;
            i++;
        }
        if(i != 3){
            throw new NumbersException(NumbersException.ErrorCode.INVALID_NUM_LINES);
        }
        return lines;
    }


    public static List<String> splitLines(String[] lines){
        assert lines.length == 3;
        List<String> splitLines = new ArrayList<>();
        for(String line: lines){
            assert line.length() == 27;
            int i = 0;
            while (i < line.length()) {
                splitLines.add(line.substring(i, Math.min(i + 3,line.length())));
                i += 3;
            }
        }
        return splitLines;
    }

    public static List<SevenSegmentNumber> createSevenSegmentNumbers(List<String> splitLines) throws NumbersException{
        List<SevenSegmentNumber> sevenSegmentNumbers= new ArrayList<>();
        String[] regexes = {" [ _] ",
                            "[ |][ _][ |]",
                            "[ |][ _][ |]"};
        for(int i = 0; i < 9; i++){
            char[][] sevenSegmentNumber = new char[3][3];
            int j = i;
            int k = 0;
            while(k < 3){
                String line = splitLines.get(j);
                boolean validLine = line.matches(regexes[k]);
                if(!validLine){
                    throw new NumbersException(NumbersException.ErrorCode.INVALID_SEGMENT);
                }
                sevenSegmentNumber[k] = line.toCharArray();
                j += 9;
                k++;
            }
            sevenSegmentNumbers.add(new SevenSegmentNumber(sevenSegmentNumber));
        }
        return sevenSegmentNumbers;
    }


}
