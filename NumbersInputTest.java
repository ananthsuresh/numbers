import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NumbersInputTest {

    //Structured Basis: Doesn't enter while loop and enters outside if//
    //Bad data - no data//
    @Test(expected = NumbersException.class)
    public void test_parseInput_empty() throws NumbersException{
        String str = "";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        NumbersInput.parseInput();
    }

    //Structured Basis: enters while loop and doesn't enter inside statement//
    //Bad data - less data, one line//
    @Test(expected = NumbersException.class)
    public void test_parseInput_oneLine() throws NumbersException{
        String str = "123456789123456789123456789";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        NumbersInput.parseInput();
    }

    //Structured Basis: enters while loop and doesn't enter inside statement//
    //Bad data - less data, two line//
    @Test(expected = NumbersException.class)
    public void test_parseInput_twoLine() throws NumbersException{
        String str = "123456789123456789123456789\n123456789123456789123456789";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        NumbersInput.parseInput();
    }

    //Structured Basis: enters while loop and inner if statement//
    //Bad data - less data, 26 characters//
    @Test(expected = NumbersException.class)
    public void test_parseInput_lessChars() throws NumbersException{
        String str = "12345678912345678912345678";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        NumbersInput.parseInput();
    }

    //Structured Basis: enters while loop and neither if//
    //Good data - nominal//
    @Test
    public void test_parseInput_nominal() throws NumbersException{
        String str = " _     _  _  _  _  _  _  _ \n|_|  ||_||_||_||_||_||_||_|\n|_|  ||_||_||_||_||_||_||_|";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        String[] lines = NumbersInput.parseInput();
        assertEquals(lines[0], " _     _  _  _  _  _  _  _ ");
        assertEquals(lines[1], "|_|  ||_||_||_||_||_||_||_|");
        assertEquals(lines[2], "|_|  ||_||_||_||_||_||_||_|");
    }

    //Structured Basis: Covers all possible paths as for loop and while loop will definitely be entered due to the preconditions//
    //Good data//
    //Protected against bad data by preconditions//
    @Test
    public void test_splitLines_nominal(){
        String[] lines = {"aaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaa"};
        List<String> splitLines = NumbersInput.splitLines(lines);
        for(int i = 0; i < splitLines.size(); i++){
            assertEquals("aaa", splitLines.get(i));
        }
    }

    //Structured basis - while and for will always run, testing inside if statement
    @Test(expected = NumbersException.class)
    public void test_create_sevenSegmentNumbers_invalid() throws NumbersException{
        String[] lines = {" |     _  _  _  _  _  _  _ ","|_|  ||_||_||_||_||_||_||_|","|_|  ||_||_||_||_||_||_||_|"};
        List<String> splitLines = NumbersInput.splitLines(lines);
        NumbersInput.createSevenSegmentNumbers(splitLines);
    }


    //Structured basis - while and for will always run, testing not entering if
    @Test
    public void test_create_sevenSegmentNumbers_valid() throws NumbersException{
        String[] lines = {" _     _  _  _  _  _  _  _ ","|_|  ||_||_||_||_||_||_||_|","|_|  ||_||_||_||_||_||_||_|"};
        List<String> splitLines = NumbersInput.splitLines(lines);
        List<SevenSegmentNumber> sevenSegmentNumbers = NumbersInput.createSevenSegmentNumbers(splitLines);
        assertTrue(sevenSegmentNumbers.size() == 9);
        assertTrue(Arrays.deepEquals(sevenSegmentNumbers.get(0).getNumber(), SevenSegmentNumber.Digit.EIGHT.getArrayRepresentation()));
    }




}