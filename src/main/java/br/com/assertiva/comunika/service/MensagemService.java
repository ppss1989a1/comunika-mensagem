package br.com.assertiva.comunika.service;

import br.com.assertiva.comunika.domain.Batch;
import br.com.assertiva.comunika.domain.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.Message;
import br.com.assertiva.comunika.exception.BadRequestException;
import br.com.assertiva.comunika.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository mensagemRepository;

    public List<Message> buscarTodasMensagens(Integer loteId) {

        List<Message> list = mensagemRepository.mensagensDoLote(loteId);

        return list;
    }

    public Message buscarMensagemPorId(Integer idMensagem) throws BadRequestException {

        Optional<Message> mensagem = mensagemRepository.findById(idMensagem);

        return mensagem.orElseThrow(() -> new BadRequestException("Mensagem Não Encontrada"));
    }

    public String salvarMensagens(CreateMessagesRequest request) {

        String retorno = "";

        List<Message> lstMensagem = new ArrayList<>();
        Integer count = 0;

        for (Batch batch : request.getBatches()) {

            retorno += batch.getMessageAmount() + " Mensagens foram salvas referente ao lote: " + batch.getBatch() + ", ";

            for (int i = 0; i < batch.getMessageAmount(); i++) {

                Message mensagem = new Message();
                mensagem.setCreated_at(LocalDateTime.now().toString());
                mensagem.setBatch(batch.getBatch());
                mensagem.setMessage(request.getMensagem());
                mensagem.setNumber(request.getLstNumeros().get(count));
                mensagem.setStatus(batch.getStatus());

                lstMensagem.add(mensagem);
                count++;
            }
        }

        mensagemRepository.saveAll(lstMensagem);

        return retorno;
    }


}
