import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class NumbersDriverTest {
    //Structured Basis: Always enters for loop, testing enters if statement//
    //Good data - nominal//
    @Test
    public void test_findExactMatch_nominal() throws NumbersException{
        SevenSegmentNumber testNum = new SevenSegmentNumber(SevenSegmentNumber.Digit.ONE.getArrayRepresentation());
        assertEquals(SevenSegmentNumber.Digit.ONE, NumbersDriver.findExactMatch(testNum).get());
    }

    //Structured Basis: Always enters for loop, testing does not enter if//
    //Good data - minimum//
    @Test
    public void test_findExactMatch_noMatch() throws NumbersException{
        SevenSegmentNumber testNum = new SevenSegmentNumber(new char[][]{{' ','_',' '},
                {'|',' ','|'},
                {'|',' ','|'}});
        assertEquals(Optional.empty(), NumbersDriver.findExactMatch(testNum));
    }

    //Structured Basis: Always enters for loop, testing enter both if statements//
    //Good data//
    @Test(expected = NumbersException.class)
    public void test_findInExactMatch_bothIf() throws NumbersException{
        SevenSegmentNumber testNum = new SevenSegmentNumber(new char[][]{{' ','_',' '},
                {'|',' ','|'},
                {'|',' ','|'}});
        NumbersDriver.findInExactMatch(testNum);
    }

    //Structured Basis: testing enter only first if //
    @Test
    public void test_findInExactMatch_firstIf() throws NumbersException{
        SevenSegmentNumber testNum = new SevenSegmentNumber(new char[][]{{' ','_',' '},
                {'|','_','|'},
                {'|',' ','|'}});
        assertEquals(SevenSegmentNumber.Digit.EIGHT, NumbersDriver.findInExactMatch(testNum).get());
    }

    //Structured Basis: all branches entered//
    @Test(expected = NumbersException.class)
    public void test_generateNumber_toomanygarbled() throws NumbersException{
        String str = "    _  _  _     _  _  _  _ \n|_||_||_||_||_||_||_||_||_|\n|_||_||_||_||_||_||_||_||_|";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        String[] lines = NumbersInput.parseInput();
        List<SevenSegmentNumber> sevenSegmentNumbers = NumbersInput.createSevenSegmentNumbers(NumbersInput.splitLines(lines));
        NumbersDriver.generateNumber(sevenSegmentNumbers);
    }

    //Good data : nominal//
    @Test
    public void test_generateNumber_nominal() throws NumbersException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String str = " _  _  _  _  _  _  _  _  _ \n|_||_||_||_||_||_||_||_||_|\n|_||_||_||_||_||_||_||_||_|";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        String[] lines = NumbersInput.parseInput();
        List<SevenSegmentNumber> sevenSegmentNumbers = NumbersInput.createSevenSegmentNumbers(NumbersInput.splitLines(lines));
        System.out.print(NumbersDriver.generateNumber(sevenSegmentNumbers));
        assertEquals("888888888", baos.toString());
    }


}