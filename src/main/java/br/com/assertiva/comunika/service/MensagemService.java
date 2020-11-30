package br.com.assertiva.comunika.service;

import br.com.assertiva.comunika.domain.Batch;
import br.com.assertiva.comunika.domain.Message;
import br.com.assertiva.comunika.domain.requests.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.requests.ResponseZenvia;
import br.com.assertiva.comunika.exception.BadRequestException;
import br.com.assertiva.comunika.repository.MensagemRepository;
import br.com.assertiva.comunika.utils.LocalDateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensagemService {

    LocalDateTimeUtils dateTimeUtils = new LocalDateTimeUtils();

    private static final Logger logger = LoggerFactory.getLogger(MensagemService.class);


    @Autowired
    MensagemRepository mensagemRepository;

    public List<Message> buscarTodasMensagens(Integer loteId) {

        List<Message> list = mensagemRepository.mensagensDoLote(loteId);

        return list;
    }

    public Message buscarMensagemPorId(String id) {

        return mensagemRepository.idMessages(id);
    }

    public List<Message> atualizarMensagens(List<Message> messages) throws BadRequestException {

        return mensagemRepository.saveAll(messages);
    }

    public List<Message> updateMessagesZenvia(List<ResponseZenvia> messages) {

        List<Message> lstToUpdate = new ArrayList<>();

        messages.forEach( response -> {

            Message message = new Message();

            message.setId(response.getId().replace("V3-", ""));
            message.setStatus(Integer.valueOf(response.getStatusCode()));
            message.setPhone(response.getPhone());
            message.setMessage(response.getMessage());
            message.setCampaignId(response.getCampaignId());
            message.setBatchId(response.getBatchId());
            message.setUpdated_at(dateTimeUtils.nowFortaleza());
            message.setSchedule(dateTimeUtils.localDateParse(response.getSchedule()));

            lstToUpdate.add(message);
        });

        return mensagemRepository.saveAll(lstToUpdate);
    }

    public List<Message> salvarMensagens(CreateMessagesRequest request) {

        List<Message> lstMensagem = new ArrayList<>();
        Integer count = 0;
        String retorno = "";

        for (Batch batch : request.getBatches()) {

            retorno += batch.getMessageAmount() + " Mensagens foram salvas referente ao lote: " + batch.getBatchId() + ", ";

            for (int i = 0; i < batch.getMessageAmount(); i++) {

                Message mensagem = new Message();
                mensagem.setUpdated_at(dateTimeUtils.nowFortaleza());
                mensagem.setBatchId(batch.getBatchId());
                mensagem.setMessage(request.getMessage());
                mensagem.setPhone(request.getNumbers().get(count));
                mensagem.setStatus(batch.getStatus());
                mensagem.setCampaignId(request.getCampaignId());

                lstMensagem.add(mensagem);
                count++;
            }
        }

        return mensagemRepository.saveAll(lstMensagem);

    }
}
