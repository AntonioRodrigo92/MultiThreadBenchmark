import org.mockito.internal.util.reflection.Whitebox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ResultMapTest {
    private ResultMap map;

    @BeforeEach
    void setup() {
        this.map = new ResultMap();
    }

    @Test
    void should_addResult_when_correctPair() throws NoSuchFieldException, IllegalAccessException {
        //  given
        int i = 10;
        long l = 100;
        HashMap<Integer, Long> expected = new HashMap<>();
        expected.put(i, l);
        //  when
        map.addResult(i, l);
        //  then
        Field field = map.getClass().getDeclaredField("mapa");
        field.setAccessible(true);
        HashMap<Integer, Long> actual = (HashMap<Integer, Long>) field.get(map);
        assertEquals(actual, expected);
    }

    @Test
    void should_printContentToConsole_when_CorrectInput() {
        //  given
        int i = 10;
        long l = 100;
        HashMap<Integer, Long> mapa = new HashMap<>();
        mapa.put(i, l);
        String exp = (i + ": " + (double)l + "\n");
        String expected = removeLineBreaks(exp);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        //  when
        Whitebox.setInternalState(map, "mapa", mapa);
        map.printResultContent();
        //  then
        System.out.flush();
        System.setOut(old);
        String act = baos.toString();
        String actual = removeLineBreaks(act);
        assertEquals(expected, actual);
    }

    private String removeLineBreaks(String old) {
        String n = old.replace("\n", "").replace("\r", "").replace("\n\r", "");
        return n;
    }
}