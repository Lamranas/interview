package com.genesis.interview.controller;

import com.genesis.interview.service.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<T> {

    protected CrudService<T> service;

    protected CrudController(CrudService<T> service){
        this.service = service;
    }

    @GetMapping
    public List<T> get () {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public T get (@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public T create (@RequestBody T object) {
        return service.create(object);
    }

    @PutMapping("/{id}")
    public T update (@PathVariable Long id, @RequestBody T object) {
        return service.update(id, object);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
