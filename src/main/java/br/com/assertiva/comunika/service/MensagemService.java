package br.com.assertiva.comunika.service;

import br.com.assertiva.comunika.domain.Batch;
import br.com.assertiva.comunika.domain.Message;
import br.com.assertiva.comunika.domain.enums.AssertivaStatusMessage;
import br.com.assertiva.comunika.domain.requests.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.requests.ResponseZenvia;
import br.com.assertiva.comunika.domain.responses.MessagesCount;
import br.com.assertiva.comunika.exception.BadRequestException;
import br.com.assertiva.comunika.repository.MensagemRepository;
import br.com.assertiva.comunika.utils.LocalDateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        messages.forEach(response -> {

            Message message = new Message();

            message.setId(response.getId().replace("V3-", ""));
            message.setStatus(Integer.valueOf(response.getStatusCode()));
            message.setPhone(response.getPhone());
            message.setMessage(response.getMessage());
            message.setCampaignId(response.getCampaignId());
            message.setBatchId(response.getBatchId());
            message.setUpdatedAt(dateTimeUtils.nowFortaleza());
            message.setSchedule(dateTimeUtils.stringToDate(response.getSchedule()));

            lstToUpdate.add(message);
        });

        return mensagemRepository.saveAll(lstToUpdate);
    }

    public List<Message> salvarMensagens(CreateMessagesRequest request) {

        List<Message> lstMensagem = new ArrayList<>();

        if (request.getBlacklistNumbers().isEmpty()) {
            createMessagesWithoutBlacklistVerification(request, lstMensagem);
        } else {
            createMessagesWithBlacklistVerification(request, lstMensagem);
        }

        return mensagemRepository.saveAll(lstMensagem);
    }

    private void createMessagesWithBlacklistVerification(CreateMessagesRequest request, List<Message> lstMensagem) {

        int count = 0;

        for (Batch batch : request.getBatches()) {

            for (int i = 0; i < batch.getMessageAmount(); i++) {

                Message mensagem = new Message();
                mensagem.setUpdatedAt(dateTimeUtils.nowFortaleza());
                mensagem.setBatchId(batch.getBatchId());
                mensagem.setMessage(request.getMessage());
                mensagem.setPhone(request.getNumbers().get(count));
                mensagem.setCampaignId(request.getCampaign().getId());
                mensagem.setRouteId(request.getCampaign().getRouteId());

                if (request.getBlacklistNumbers().stream().anyMatch(phone -> mensagem.getPhone().equals(phone))) {
                    mensagem.setStatus(AssertivaStatusMessage.BLACKLIST.getId());
                } else {
                    mensagem.setStatus(batch.getStatus());
                }

                lstMensagem.add(mensagem);
                count++;
            }
        }
    }

    private void createMessagesWithoutBlacklistVerification(CreateMessagesRequest request, List<Message> lstMensagem) {

        int count = 0;

        for (Batch batch : request.getBatches()) {

            for (int i = 0; i < batch.getMessageAmount(); i++) {

                Message mensagem = new Message();
                mensagem.setUpdatedAt(dateTimeUtils.nowFortaleza());
                mensagem.setBatchId(batch.getBatchId());
                mensagem.setMessage(request.getMessage());
                mensagem.setPhone(request.getNumbers().get(count));
                mensagem.setStatus(batch.getStatus());
                mensagem.setCampaignId(request.getCampaign().getId());
                mensagem.setRouteId(request.getCampaign().getRouteId());

                lstMensagem.add(mensagem);
                count++;
            }
        }
    }

    public MessagesCount findMessageStatusByCampaign(Integer campaignId) {

        List<Message> lst = mensagemRepository.campaignMessages(campaignId);

        Long sendedWithConfirmation = lst.stream().filter(msg -> AssertivaStatusMessage.SENDED_WITH_CONFIRMATION.getId().equals(msg.getStatus())).count();
        Long sendedWithoutConfirmation = lst.stream().filter(msg -> AssertivaStatusMessage.SENDED_WITHOUT_CONFIRMATION.getId().equals(msg.getStatus())).count();
        Long blacklist = lst.stream().filter(msg -> AssertivaStatusMessage.BLACKLIST.getId().equals(msg.getStatus())).count();
        Long canceled = lst.stream().filter(msg -> AssertivaStatusMessage.CANCELED.getId().equals(msg.getStatus())).count();
        Long error = lst.stream().filter(msg -> AssertivaStatusMessage.ERROR.getId().equals(msg.getStatus())).count();
        Long paused = lst.stream().filter(msg -> AssertivaStatusMessage.STOPPED.getId().equals(msg.getStatus())).count();
        Long waitingToSend = lst.stream().filter(msg -> AssertivaStatusMessage.WAITING_TO_SEND.getId().equals(msg.getStatus())).count();

        return new MessagesCount(sendedWithConfirmation, sendedWithoutConfirmation, waitingToSend, canceled, paused, blacklist, error);
    }
}