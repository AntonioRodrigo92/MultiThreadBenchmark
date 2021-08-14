import java.util.Stack;

public class WorkStack {
	private Stack<Integer> pilha;
	
	public WorkStack() {
		pilha = new Stack<>();
	}
	
	public void meterTrabalho(int i) {
		pilha.push(i);
	}
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}
	
	public synchronized int tirarTrabalho() {
		return pilha.pop();
	}

}
