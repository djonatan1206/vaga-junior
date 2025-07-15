package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Bomba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a entidade Bomba.
 * Herda de JpaRepository para obter automaticamente as funcionalidades de CRUD.
 *
 * O Spring Data JPA é inteligente o suficiente para gerir os relacionamentos definidos na entidade.
 * Ao buscar uma 'Bomba', por exemplo, os dados do 'Combustivel' associado serão carregados
 * conforme a estratégia de Fetch definida na anotação @ManyToOne (o padrão é EAGER).
 * Isto elimina a necessidade de escrever JOINs manuais, como fazíamos na camada DAO.
 */
@Repository
public interface BombaRepository extends JpaRepository<Bomba, Integer> {
    // Para as operações básicas de CRUD, não é necessário adicionar nenhum método.
}
