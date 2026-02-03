package repository.interfaces;

import java.util.List;

public interface CrudRepository<T> {
    void create(T entity);
    T getById(int id);
    List<T> getAll();
    void delete(int id);
}
