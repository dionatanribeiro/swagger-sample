package br.com.dionatanribeiro.swagger.person;

import br.com.dionatanribeiro.swagger.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/person")
public class PersonController {

    @PostMapping
    public String person(@Valid @RequestBody User person) {
        return "O nome da pessoa é: " + person.getName();
    }

    public static class Person {

        @NotNull(message = "Nome não deve ser nulo")
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
