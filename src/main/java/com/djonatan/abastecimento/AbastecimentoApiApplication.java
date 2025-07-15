package com.djonatan.abastecimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicia a aplicação Spring Boot.
 *
 * Anotação Chave:
 * - @SpringBootApplication: Esta é uma anotação de conveniência que engloba três outras anotações
 * muito importantes:
 * 1. @Configuration: Marca a classe como uma fonte de definições de beans para o contexto da aplicação.
 * 2. @EnableAutoConfiguration: Diz ao Spring Boot para começar a adicionar beans com base nas
 * configurações do classpath, outros beans e várias configurações de propriedades. Por exemplo,
 * como deteta 'spring-boot-starter-web', ele configura automaticamente um servidor Tomcat.
 * 3. @ComponentScan: Diz ao Spring para procurar outros componentes, configurações e serviços no
 * pacote 'com.djonatan.abastecimento' e nos seus sub-pacotes, permitindo-lhe encontrar e
 * registar os nossos Controllers, Services e Repositories.
 */
@SpringBootApplication
public class AbastecimentoApiApplication {

    /**
     * O método main que serve como o ponto de entrada para a aplicação.
     * A chamada `SpringApplication.run()` inicia todo o processo:
     * - Cria o contexto da aplicação.
     * - Realiza o scan de classes.
     * - Inicia o servidor web embutido (Tomcat).
     * - E deixa a API pronta a receber pedidos.
     *
     * @param args Argumentos de linha de comando (não utilizados aqui).
     */
    public static void main(String[] args) {
        SpringApplication.run(AbastecimentoApiApplication.class, args);
    }

}
