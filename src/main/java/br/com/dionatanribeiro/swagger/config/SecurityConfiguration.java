package br.com.dionatanribeiro.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe criada com o propósito de sobreescrever a auto-configuration do Spring Security
 * afim de desabilitar o problema com o header "X-Frame-Options" que impede de logar dentro
 * da página /h2-console. Com essa implementação ativa (descomentar @Configuration) o
 * Spring Security é DESABILITADO.
 */
//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
//            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/console/**"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }



}