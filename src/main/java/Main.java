import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
		
	public static void main(String[] args) {
		System.out.print("Quanto numeros de Fibonacci quer conhecer? ");
		Scanner scan = new Scanner(System.in);
		int workSize = scan.nextInt();
		int workerSize = Runtime.getRuntime().availableProcessors();
		scan.close();
		
		long st = System.nanoTime();
		
		System.out.println("Running . . . ");
		
		WorkStack ws = new WorkStack();
		ResultMap rm = new ResultMap();
		List<Worker> workers = new ArrayList<>();
		
		for (int i = 1; i <= workSize; i++) {
			ws.meterTrabalho(i);
		}
		
		for (int i = 0; i < workerSize; i++) {
			Worker w = new Worker(ws, rm);
			workers.add(w);
			w.start();
		}
				
		for (Worker w : workers) {
			try {
				w.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rm.printResultContent();
		
		long time = System.nanoTime() - st;
		System.out.println();
		System.out.println("O processo demorou " + TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS) + "s");

	}

}
