import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SevenSegmentNumberTest {

    //Structured basis: all branches
    //Good data
    @Test
    public void test_getNumber_nominal(){
        SevenSegmentNumber number = new SevenSegmentNumber(SevenSegmentNumber.Digit.ONE.getArrayRepresentation());
        assertTrue(Arrays.deepEquals(SevenSegmentNumber.Digit.ONE.getArrayRepresentation(), number.getNumber()));
    }

    //Structured basis: all branches
    //Good data
    @Test
    public void test_couldBe_nominal(){
        SevenSegmentNumber testNum = new SevenSegmentNumber(new char[][]{{' ','_',' '},
                {'|','_','|'},
                {'|',' ','|'}});
        assertTrue(testNum.couldBe(SevenSegmentNumber.Digit.EIGHT));
    }


}