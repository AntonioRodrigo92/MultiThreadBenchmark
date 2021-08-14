import java.util.HashMap;
import java.util.Map;

public class ResultMap {
	private Map<Integer, Long> mapa;
	
	public ResultMap() {
		mapa = new HashMap<>();
	}
	
	public synchronized void addResult(int n, long r) {
		mapa.put(n, r);
	}
	
	public void printResultContent() {
		for (int k : mapa.keySet()) {
			float r = mapa.get(k);
			System.out.println(k + ": " + r);
		}
	}

}
