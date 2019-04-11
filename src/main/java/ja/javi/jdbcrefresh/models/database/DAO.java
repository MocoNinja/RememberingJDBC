package ja.javi.jdbcrefresh.models.database;

import java.util.List;

public interface DAO<T, k> {
	T getById(k id);

	List<T> getAll();

	void delete(T t);

	void insert(T t);

	void update(T tOriginal, T tNew);
}
