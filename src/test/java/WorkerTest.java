import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.internal.util.reflection.Whitebox;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WorkerTest {

    @ParameterizedTest(name = "input = {0}, output = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8", "7, 13", "8, 21"})
    void should_returnValueInReturnMap(int input, long output) throws NoSuchFieldException, IllegalAccessException {
        //  given
        WorkStack workStack = new WorkStack();
        ResultMap resultMap = new ResultMap();
        Stack<Integer> pilha = new Stack<>();
        pilha.push(input);
        Whitebox.setInternalState(workStack, "pilha", pilha);
        Worker worker = new Worker(workStack, resultMap);
        //  when
        worker.run();
        //  then
        Field field = resultMap.getClass().getDeclaredField("mapa");
        field.setAccessible(true);
        HashMap<Integer, Long> act = (HashMap<Integer, Long>) field.get(resultMap);
        Long actual = act.get(input);
        assertEquals(actual, output);
    }

}