package interfaces;

public interface Salvable<T> {
	public void writeToFile(T elem);
	public void readFromFile();
}
