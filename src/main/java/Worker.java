
public class Worker extends Thread {
	private WorkStack workStack;
	private ResultMap resultMap;
	
	public Worker(WorkStack workStack, ResultMap resultMap) {
		this.workStack = workStack;
		this.resultMap = resultMap;
	}
	
	public void run() {
		while (! workStack.isEmpty()) {
			int n = workStack.tirarTrabalho();
			long r = fibonacciRecu(n);
			resultMap.addResult(n, r);
		}
//		System.out.println("Thread terminou trabalho!");
	}
	
	
	private long fibonacciRecu (int n) {
		if (n < 2) {
			return n;
		}
		else {
			return fibonacciRecu(n - 2) + fibonacciRecu (n - 1);
		}
	}
	
//	private long fibonacciSoma (int n) {
//		long a = 0;
//		long b = 1;
//		long c = 0;
//		if (n < 2) {
//			return n;
//		}
//		else {
//			for (int i = 2; i <= n; i++) {
//				c =  a + b;
//				a = b;
//				b = c;
//			}
//			return c;
//		}
//	}

}
