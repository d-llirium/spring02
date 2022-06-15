package br.anhembi.spring02.repository;

import org.springframework.data.repository.CrudRepository;

import br.anhembi.spring02.model.UserSys;

public interface IUserSysRepo extends CrudRepository<UserSys, Long> {
    UserSys findByUsername(String username);
}
