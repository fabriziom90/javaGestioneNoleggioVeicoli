package interfaces;

import java.util.List;

public interface Salvable<T> {
	public void writeToFile(List<T> elem);
	public void readFromFile();
}
