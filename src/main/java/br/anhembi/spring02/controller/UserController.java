package br.anhembi.spring02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.spring02.model.User;
import br.anhembi.spring02.repository.IUserRepo;

@RestController 
@CrossOrigin("*") // para aceitar requisições qde qq origrm
@RequestMapping("/user") // para requests de web / formato de URL = o que vai ter na URL para acessar 
public class UserController {

    @Autowired // cria a classe que implementa a interface IUserRepo [com os métodos SQL] e retorna um objeto repo do da interface 
    private IUserRepo repo;

    @PostMapping // qnd a requisição for de POST esse método será selecionado
    public ResponseEntity<User> insertUser(@RequestBody User user) { // RequestBody indica que há uma informação no corpo da requisição -- json no nosso caso
        User newUser = repo.save(user);

        return ResponseEntity.ok(newUser);
    }
}
