package br.anhembi.spring02.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.anhembi.spring02.services.UserSysService;

@EnableWebSecurity // habilita essa classe configurar a segurança da aplicação
@EnableGlobalMethodSecurity(prePostEnabled = true) // para poder utilizar pre authorização 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdapter possui métodos de config de segurança

    @Autowired
    private UserSysService service;
    
    // configura quais requisições http quero proteger
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http.csrf().disable() // desabilita o pedido de autorização para modificação do BD
            .authorizeRequests()
            .anyRequest() // qq requisição
            .authenticated() // precisa ser aiutenticada
            .and() // e 
            .httpBasic(); // username/senha
    }

    // configura os acessos
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); // objeto que ira criptografar as senhas

        // para captar a senha codificada que irá no BD
        System.out.println("=========="+ passwordEncoder.encode("aula"));

        // auth.inMemoryAuthentication()
        //     .withUser("patricia")
        //     .password(passwordEncoder.encode("aula"))
        //     .roles("ASMIN", "USER");

        auth.userDetailsService(service)
            .passwordEncoder(passwordEncoder);
    }
}
