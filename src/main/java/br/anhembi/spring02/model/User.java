package br.anhembi.spring02.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @Column(name = "senha", length = 30, nullable = false) // coluna com nome se senha de no máximo 30 caracteres e não pode ser nula == @NOTNULL
    private String password;

    @OneToMany(mappedBy = "owner") // 1 usuário que possui muitos carros
    @JsonIgnoreProperties("owner") // ao buscar os dados dos veículos do usuário, não traga novamente os dados do proprietário de cada veículo
    private List<Car> cars;

    // METHODS
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
}
