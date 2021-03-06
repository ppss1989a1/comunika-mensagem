package br.com.assertiva.comunika.service;

import br.com.assertiva.comunika.domain.Batch;
import br.com.assertiva.comunika.domain.Message;
import br.com.assertiva.comunika.domain.dto.MessagePagination;
import br.com.assertiva.comunika.domain.dto.Page;
import br.com.assertiva.comunika.domain.enums.AssertivaStatusMessage;
import br.com.assertiva.comunika.domain.enums.ZenviaStatusMessage;
import br.com.assertiva.comunika.domain.requests.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.requests.ResponseZenvia;
import br.com.assertiva.comunika.domain.responses.MessagesCount;
import br.com.assertiva.comunika.domain.responses.Pagination;
import br.com.assertiva.comunika.exception.BadRequestException;
import br.com.assertiva.comunika.repository.MensagemRepository;
import br.com.assertiva.comunika.utils.LocalDateTimeUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensagemService {

    LocalDateTimeUtils dateTimeUtils = new LocalDateTimeUtils();

    private static final Logger logger = LoggerFactory.getLogger(MensagemService.class);

    private MensagemRepository mensagemRepository;

    private MongoOperations add;

    public MensagemService(MensagemRepository mensagemRepository, MongoOperations add) {
        this.mensagemRepository = mensagemRepository;
        this.add = add;
    }

    public List<Message> findAllMessagesByBatch(Integer loteId) {

        List<Message> list = mensagemRepository.findMessagesByBatch(loteId);

        return list;
    }

    public Message findMessageById(String id) {

        return mensagemRepository.idMessages(id);
    }

    public List<Message> updateMessagesZenvia(List<ResponseZenvia> messages) {

        List<Message> lstToUpdate = new ArrayList<>();

        messages.forEach(response -> {

            Message message = new Message();
            message.setId(response.getId().replace("V3-", ""));
            message.setStatus(obtainCodeStatusFromZenviaResponse(Integer.valueOf(response.getStatusCode())));
            Query query = new Query();
            Update update = new Update().set("status", message.getStatus()).set("updatedAt", dateTimeUtils.nowFortaleza());
            query.addCriteria(Criteria.where("id").is(message.getId()));

            add.updateFirst(query, update, Message.class);

            lstToUpdate.add(message);
        });

        return null;
    }

    private Integer obtainCodeStatusFromZenviaResponse(Integer statusCode) {

        if (statusCode.equals(ZenviaStatusMessage.OK.getId()) || statusCode.equals(ZenviaStatusMessage.SENT.getId())) {
            return AssertivaStatusMessage.SENDED_WITHOUT_CONFIRMATION.getId();
        } else if (statusCode.equals(ZenviaStatusMessage.DELIVERED.getId())) {
            return AssertivaStatusMessage.SENDED_WITH_CONFIRMATION.getId();
        } else if (statusCode.equals(ZenviaStatusMessage.SCHEDULED.getId())) {
            return AssertivaStatusMessage.WAITING_TO_SEND.getId();
        } else if (statusCode.equals(ZenviaStatusMessage.BLOCKED_NO_COVERAGE.getId()) ||
                statusCode.equals(ZenviaStatusMessage.BLOCKED_BLACK_LISTED.getId()) ||
                statusCode.equals(ZenviaStatusMessage.BLOCKED_INVALID_NUMBER.getId()) ||
                statusCode.equals(ZenviaStatusMessage.BLOCKED_CONTENT_NOT_ALLOWED_OR_EXPIRED.getId()) ||
                statusCode.equals(ZenviaStatusMessage.NOT.getId()) ||
                statusCode.equals(ZenviaStatusMessage.ERROR.getId()) ||
                statusCode.equals(ZenviaStatusMessage.BLOCKED.getId())) {
            return AssertivaStatusMessage.ERROR.getId();
        } else {
            return AssertivaStatusMessage.ERROR.getId();
        }
    }

    public List<Message> saveMessages(CreateMessagesRequest request) {

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
                mensagem.setSchedule(batch.getSchedule());
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

    public Pagination findMessageByCampaignPageable(Integer campaignId, Integer offSet, Integer page) {

        List<Message> lst = mensagemRepository.campaignMessages(campaignId);

        List<List<Message>> paginacao = Lists.partition(lst, offSet);

        Page pages = createPagination(page, paginacao.size());

        List<MessagePagination> messages = new ArrayList<>();

        List<Message> pageToReturn = paginacao.get(page);

        for (Message message : pageToReturn) {

            MessagePagination messagePagination = new MessagePagination();
            messagePagination.setMessage(message.getMessage());
            messagePagination.setDate(message.getSchedule().toString());
            messagePagination.setPhone(message.getPhone());
            messagePagination.setStatus(AssertivaStatusMessage.valueOf(message.getStatus()).getDescription());

            messages.add(messagePagination);
        }

        return new Pagination(messages, pages);
    }

    private Page createPagination(Integer page, Integer lastPage) {

        Integer nextPage = page + 1;
        Integer beforePage = page - 1;
        Integer beforeLastPage = lastPage - 1;
        Integer secondPage = 2;
        Integer firstPage = 1;

        Page pages = new Page();

        if (page.equals(firstPage)) {

            pages.setNext(nextPage);
            pages.setLast(lastPage);
        } else if (page.equals(lastPage)) {

            pages.setFirst(firstPage);
            pages.setBefore(beforePage);
        } else if (page.equals(secondPage)) {

            pages.setFirst(firstPage);
            pages.setNext(nextPage);
            pages.setLast(lastPage);
        } else if (page.equals(beforeLastPage)) {

            pages.setFirst(firstPage);
            pages.setBefore(beforePage);
            pages.setLast(lastPage);
        } else {

            pages.setFirst(firstPage);
            pages.setBefore(beforePage);
            pages.setNext(nextPage);
            pages.setLast(lastPage);
        }

        return pages;
    }
}