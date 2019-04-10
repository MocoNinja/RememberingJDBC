package ja.javi.jdbcrefresh.models.database;

import java.util.List;

public interface DAO<T, k> {
	T getById(k id);

	List<T> getAll();
}
