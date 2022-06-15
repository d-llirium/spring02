package br.anhembi.spring02.repository;

import org.springframework.data.repository.CrudRepository;

import br.anhembi.spring02.model.Car;

public interface ICarRepo extends CrudRepository<Car, Long> {
    
}
