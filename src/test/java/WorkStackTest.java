import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkStackTest {
    private WorkStack workStack;

    @BeforeEach
    void setup() {
        workStack = new WorkStack();
    }

    @Test
    void should_meterTrabalho_when_correctInput() throws NoSuchFieldException, IllegalAccessException {
        //  given
        int i = 5;
        Stack<Integer> expected = new Stack<>();
        expected.push(i);
        //  when
        workStack.meterTrabalho(i);
        //  then
        Field field = workStack.getClass().getDeclaredField("pilha");
        field.setAccessible(true);
        Stack<Integer> actual = (Stack<Integer>) field.get(workStack);
        assertEquals(actual, expected);
    }

    @Test
    void should_returnTrue_when_isEmpty_Mock() {
        //  given
        workStack = mock(WorkStack.class);
        //  when
        when(workStack.isEmpty()).thenReturn(true);
        boolean actual = workStack.isEmpty();
        //  then
        assertTrue(actual);
    }

    @Test
    void should_returnFalse_when_isNotEmpty_Mock() {
        //  given
        workStack = mock(WorkStack.class);
        //  when
        when(workStack.isEmpty()).thenReturn(false);
        boolean actual = workStack.isEmpty();
        //  then
        assertFalse(actual);
    }

    @Test
    void should_returnTrue_when_isEmpty() {
        //  given

        //  when
        boolean actual = workStack.isEmpty();
        //  then
        assertTrue(actual);
    }

    @Test
    void should_returnFalse_when_isNotEmpty() throws NoSuchFieldException, IllegalAccessException {
        //  given
        int i = 5;
        Stack<Integer> pilha = new Stack<>();
        pilha.push(i);
        Whitebox.setInternalState(workStack, "pilha", pilha);
        //  when
        boolean actual = workStack.isEmpty();
        //  then
        assertFalse(actual);
    }

    @Test
    void should_returnValue_when_popTrabalho() {
        //  given
        int expected = 5;
        Stack<Integer> pilha = new Stack<>();
        pilha.push(expected);
        Whitebox.setInternalState(workStack, "pilha", pilha);
        //  when
        int actual = workStack.tirarTrabalho();
        //  then
        assertEquals(expected, actual);
    }

}