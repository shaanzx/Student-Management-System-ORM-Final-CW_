package lk.ijse.studentmanagementsystem.dao;

import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {

    String getLastId() throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(String id) throws Exception;

    T search(String id) throws Exception;

    ArrayList<T> getAll() throws Exception;

    ArrayList<String> loadIds() throws Exception;

}
