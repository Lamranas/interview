package com.genesis.interview.service;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();

    T getById(Long id);

    T create(T object);

    void delete(Long id);

    T update(Long id, T object);

}
