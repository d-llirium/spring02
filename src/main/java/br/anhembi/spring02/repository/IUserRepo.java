package br.anhembi.spring02.repository;

import org.springframework.data.repository.CrudRepository;

import br.anhembi.spring02.model.User;

// USER é a classe que será persistida no BD
// Long é o tipo (classe) da chave primária 
// CRUD = Create Read Update Delete
// CRUDRepository tb é uma Interface
public interface IUserRepo extends CrudRepository<User, Long> { // para acessar o BD na tabela User onde a chave primária seria Long
    
}
