package br.anhembi.spring02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // indica que esta classe será armazenada no BD
@Table(name = "usuarios") // sem acento e tudo minúscula
public class User { // TABELA

    // ATRIBUTES = PROPERTIES = COLUNAS

    @Id // o próximo atributo é uma chave primária PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerado de forma sequencial, pelo programa, e não se repete
    @Column(name = "codigo")
    private long code;

    @Column(name = "nome", length = 120) // coluna chamada nome com no máximo 120 caracteres cada
    private String name;

    @Column(name = "email", length = 50, unique = true) // coluna chamada email com no máximo 50 caracteres cada, único na tabela
    private String email;

    @Column(name = "senha", length = 30, nullable = false) // coluna com nome se senha de no máximo 30 caracteres e não pode ser nula
    private String password;
}
