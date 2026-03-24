package interfaces;

import java.util.List;

public interface Storage<T> {
	public List<T> getAll();
	public T findOneById(int id);
	public T findOneBy(String string);
	public boolean add(T elem);
	public boolean update(T elem);
	public boolean remove(T elem);
}
