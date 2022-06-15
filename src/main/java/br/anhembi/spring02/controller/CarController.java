package br.anhembi.spring02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.spring02.model.Car;
import br.anhembi.spring02.repository.ICarRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/car")
public class CarController {
    
    @Autowired
    private ICarRepo repo;

    @GetMapping("/{code}")
    public ResponseEntity<Car> findCar(@PathVariable long code) {
        
        Car car = repo.findById(code).orElse(null);

        if(car != null) {
            return ResponseEntity.ok(car);
        }
        
        return ResponseEntity.notFound().build();
    }
    
}
