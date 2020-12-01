package br.com.assertiva.comunika.controller;

import br.com.assertiva.comunika.domain.Message;
import br.com.assertiva.comunika.domain.requests.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.requests.FindByIdRequest;
import br.com.assertiva.comunika.domain.requests.RequestResponseZenvia;
import br.com.assertiva.comunika.domain.responses.MessagesCount;
import br.com.assertiva.comunika.domain.responses.ResponseBatchMessages;
import br.com.assertiva.comunika.exception.BadRequestException;
import br.com.assertiva.comunika.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "messages")
public class MessageController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping(value = "/batch/{loteId}")
    public ResponseEntity buscarTodasMensagensDoLote(@PathVariable Integer loteId) {

        List<Message> lstMensagem = mensagemService.buscarTodasMensagens(loteId);

        return ResponseEntity.ok().body(new ResponseBatchMessages(lstMensagem));
    }

    @GetMapping(value = "/campaign/{campaignId}")
    public ResponseEntity findMessagesCounterByCampaign(@PathVariable Integer campaignId) {

        MessagesCount count = mensagemService.findMessageStatusByCampaign(campaignId);

        return ResponseEntity.ok().body(count);
    }

    @GetMapping(value = "/message/{id}")
    public ResponseEntity buscarTodasMensagensDoLote(@PathVariable String id) {

        Message message = mensagemService.buscarMensagemPorId(id);

        return ResponseEntity.ok().body(message);
    }

    @PutMapping(value = "/update")
    public ResponseEntity atualizarMensagens(@RequestBody FindByIdRequest messages) throws BadRequestException {

        List<Message> lstMensagem = mensagemService.atualizarMensagens(messages.getMessages());

        return ResponseEntity.ok().body(lstMensagem);
    }


    @PutMapping(value = "/response/zenvia")
    public ResponseEntity updateMessagesZenvia(@RequestBody RequestResponseZenvia messages){

        mensagemService.updateMessagesZenvia(messages.getLstResponseSms());
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity incluirMensagensPorLote(@RequestBody CreateMessagesRequest request) {

        List<Message> returnList = mensagemService.salvarMensagens(request);

        return ResponseEntity.created(null).body(returnList);
    }

}
