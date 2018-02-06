package br.com.dionatanribeiro.swagger.config;

import br.com.dionatanribeiro.swagger.exception.ExceptionResponse;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {//extends WebMvcConfigurationSupport {

    private static final Contact DEFAULT_CONTACT = new Contact(
            "Dionatan Ribeiro", "http://www.dionatanribeiro.com.br", "dionatangil@gmail.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Minha API",
            "Descrição da API",
            "1.0",
            "blash",
            DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
        new HashSet<>(Arrays.asList("application/json"));

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.dionatanribeiro.swagger.product"))
                .paths(PathSelectors.ant("/v1/product/**"))
                .build()
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, createDefaultMessages())
                .globalResponseMessage(RequestMethod.POST, createDefaultMessages())
                .globalResponseMessage(RequestMethod.PUT, createDefaultMessages())
                .globalResponseMessage(RequestMethod.DELETE, createDefaultMessages())
                .globalOperationParameters(
                        Lists.newArrayList(new ParameterBuilder()
                                .name("X-API-MARKETPLACE")
                                .description("Token de Autorização")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))
                .additionalModels(typeResolver.resolve(ExceptionResponse.class))
                ;
    }

    private List<ResponseMessage> createDefaultMessages() {
        return Lists.newArrayList(
            new ResponseMessageBuilder()
                .code(500)
                .message("Problema ao processar requisição no servidor")
                .responseModel(new ModelRef("ApiError"))
                .build(),
            new ResponseMessageBuilder()
                .code(400)
                .message("Não foi possível realizar a requisição, parâmetros inválidos")
                .responseModel(new ModelRef("ApiError"))
                .build(),
            new ResponseMessageBuilder()
                .code(401)
                .message("Cliente não autorizado a realizar a operação")
                .build(),
            new ResponseMessageBuilder()
                .code(403)
                .message("Cliente não autenticado")
                .build()
        );
    }

}
