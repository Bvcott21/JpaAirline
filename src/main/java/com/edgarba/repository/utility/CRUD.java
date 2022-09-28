package com.edgarba.repository.utility;

import java.util.List;

public interface CRUD<T> {
    void create(T t);
    List<T> findAll();
    T findById(long id) throws Exception;
    T update(T t) throws Exception;
    void deleteById(long id) throws Exception;
}
