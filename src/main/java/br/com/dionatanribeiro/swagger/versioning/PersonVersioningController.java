package br.com.dionatanribeiro.swagger.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fatores a considerar:
 *
 * - URI Pollution; Meios como URI e PARAMETER acabam poluindo a URI com informações,
 *      com HEADERS isso não acontece.
 * - Misuse of Http Headers
 * - Caching; utilizando headers é mais difícil manter o cache devido as URIs serem iguais.
 * - Can we execute the request on the browser?; Requests com headers não são possíveis
 *      de serem feitos apenas digitando o endereço, deve-se ter um tratamento para adicionar os Headers.
 * - API Documentation; deve-se atentar a documentar as formas de entrada da requisição
 *      para especificar como retornar a versão esperada
 */
@RestController
public class PersonVersioningController {

    /**
     * URI VERSIONING Ex. Twitter
     */

    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Dionatan Ribeiro");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Dionatan", "Ribeiro"));
    }

    /**
     * REQUEST PARAMETER VERSIONING Ex. Amazon
     */

    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Dionatan Ribeiro");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Dionatan", "Ribeiro"));
    }

    /**
     * (CUSTOM) HEADER VERSIONING Ex. Microsoft
     */

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Dionatan Ribeiro");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Dionatan", "Ribeiro"));
    }


    /**
     *  MEDIA TYPE VERSIONING (a.k.a "Content Negotiation" e "Accept Header") Ex. GitHub
     */

    @GetMapping(value = "person/produces", produces = "application/minha.aplicacao.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Dionatan Ribeiro");
    }

    @GetMapping(value = "person/produces", produces = "application/minha.aplicacao.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Dionatan", "Ribeiro"));
    }


}
