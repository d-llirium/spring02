package br.anhembi.spring02.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.spring02.dto.UserDto;
import br.anhembi.spring02.model.User;
import br.anhembi.spring02.repository.IUserRepo;

@RestController 
@CrossOrigin("*") // para aceitar requisições qde qq origrm
@RequestMapping("/user") // para requests de web / formato de URL = o que vai ter na URL para acessar 
public class UserController {

    @Autowired // cria a classe que implementa a interface IUserRepo [com os métodos SQL] e retorna um objeto repo do da interface 
    private IUserRepo repo;

    @PreAuthorize("hasRole('ADMIN')") // somente o ADMIN pode fazer post
    @PostMapping // qnd a requisição for de POST esse método será selecionado
    public ResponseEntity<User> insertUser(@RequestBody User user) { // RequestBody indica que há uma informação no corpo da requisição -- json no nosso caso
        
        if(user.getCode() > 0) { // não pode ser passado um código para salvar
            return ResponseEntity.badRequest().build();
        }
        User newUser = repo.save(user);

        return ResponseEntity.ok(newUser);
    }

    @PutMapping // qnd a requisição for de PUT esse método será selecionado
    public ResponseEntity<User> updatetUser(@RequestBody User user) { // RequestBody indica que há uma informação no corpo da requisição -- json no nosso caso
        
        if(user.getCode() <= 0) { // é preciso ter código para atualizar
            return ResponseEntity.badRequest().build();
        }
        User newUser = repo.save(user);

        // return ResponseEntity.ok(newUser); // opção para retornar ok = 200
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @DeleteMapping("/{codigo}") // qnd a requisição for de DELETE esse método será selecionado
    public ResponseEntity<User> deletetUser(@PathVariable long codigo) { // o PathVariable é o que eu coloco no caminho da URL
        
        repo.deleteById(codigo); // apague pekla chave primaria

        return ResponseEntity.noContent().build(); // equivale a um OK sem conteudo na mensagem
    }

    @GetMapping("/id/{codigo}") // qnd a requisição for de GET esse método será selecionado
    public ResponseEntity<UserDto> findtUser(@PathVariable long codigo) { // o PathVariable é o que eu coloco no caminho da URL
        
        User userFound = repo.findById(codigo).orElse(null); // procure pela chave primaria id e se não achar retorna nulo

        // verifica se o usuario foi encontrado
        if(userFound != null) {
            UserDto userDto = new UserDto(userFound); // retorna apenas os dados que o cliente pode acessar, sem o password

            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.notFound().build(); // não achou o usuario error 404
    }

    @GetMapping
    public ResponseEntity<ArrayList<User>> selectAll() {

        ArrayList<User> listUsers = (ArrayList<User>) repo.findAll(); // convertendo as instancias do BD em uma array List

        return ResponseEntity.ok(listUsers);
    }
}
