package com.djonatan.abastecimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de Entrada (Entry Point) da Aplicação Spring Boot.
 *
 * Esta é a classe principal que inicializa todo o sistema. A sua única responsabilidade
 * é "dar o pontapé de saída" no processo de arranque do Spring, que inclui a configuração
 * automática, o scan de componentes e a inicialização do servidor web embutido.
 */
@SpringBootApplication
public class AbastecimentoApiApplication {

    /**
     * O método `main` que serve como o ponto de entrada para a execução da aplicação.
     *
     * A chamada estática `SpringApplication.run()` delega o controlo para o Spring Boot,
     * que executa as seguintes tarefas essenciais:
     * 1. Cria o contexto da aplicação (ApplicationContext).
     * 2. Realiza o scan de classes em busca de componentes (@Component, @Service, @Repository, @Controller).
     * 3. Inicia o servidor web embutido (Tomcat, por padrão).
     * 4. Deixa a API pronta para receber pedidos HTTP na porta configurada (padrão: 8080).
     *
     * @param args Argumentos de linha de comando passados na inicialização (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        SpringApplication.run(AbastecimentoApiApplication.class, args);
    }

}
