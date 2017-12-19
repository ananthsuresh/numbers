import java.util.Arrays;

public class SevenSegmentNumber {

    public enum Digit {
        ZERO(new char[][]{{' ','_',' '},
                          {'|',' ','|'},
                          {'|','_','|'}} , "0"),
        ONE(new char[][]{{' ',' ',' '},
                         {' ',' ','|'},
                         {' ',' ','|'}} , "1"),
        TWO(new char[][]{{' ','_',' '},
                         {' ','_','|'},
                         {'|','_',' '}} , "2"),
        THREE(new char[][]{{' ','_',' '},
                           {' ','_','|'},
                           {' ','_','|'}} , "3"),
        FOUR(new char[][]{{' ',' ',' '},
                          {'|','_','|'},
                          {' ',' ','|'}} , "4"),
        FIVE(new char[][]{{' ','_',' '},
                          {'|','_',' '},
                          {' ','_','|'}} , "5"),
        SIX(new char[][]{{' ','_',' '},
                         {'|','_',' '},
                         {'|','_','|'}} , "6"),
        SEVEN(new char[][]{{' ','_',' '},
                           {' ',' ','|'},
                           {' ',' ','|'}} , "7"),
        EIGHT(new char[][]{{' ','_',' '},
                           {'|','_','|'},
                           {'|','_','|'}} , "8"),
        NINE(new char[][]{{' ','_',' '},
                          {'|','_','|'},
                          {' ','_','|'}} , "9");



        private final char[][] arrayRepresentation;
        private final String stringRepresentation;

        Digit(char[][] arrayRepresentation, String stringRepresentation){
            this.arrayRepresentation = arrayRepresentation;
            this.stringRepresentation = stringRepresentation;
        }

        public char[][] getArrayRepresentation() {
            return arrayRepresentation;
        }

        public String getStringRepresentation() {
            return stringRepresentation;
        }
    }

    private char[][] number;

    public SevenSegmentNumber(char[][] number) {
        this.number = number;
    }

    public char[][] getNumber() {
        char[][] numberCopy = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                numberCopy[i][j] = number[i][j];
            }
        }
        return numberCopy;
    }

    public boolean couldBe(Digit n){
        char[][] compare = n.getArrayRepresentation();
        char[][] subject = this.getNumber();
        for(int i = 0; i < subject.length; i++){
            for(int j = 0; j < subject[i].length; j++){
                if(subject[i][j] == ' ' && compare[i][j] != ' '){
                    subject[i][j] = compare[i][j];
                }
            }
        }
        return Arrays.deepEquals(subject, compare);
    }
}
