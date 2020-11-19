package br.com.assertiva.comunika.repository;

import br.com.assertiva.comunika.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends MongoRepository<Message, Integer> {

    @Query(value = "{ 'loteId': ?0 }")
    List<Message> mensagensDoLote(Integer loteId);
}