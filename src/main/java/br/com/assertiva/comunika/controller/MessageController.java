package br.com.assertiva.comunika.controller;

import br.com.assertiva.comunika.domain.CreateMessagesRequest;
import br.com.assertiva.comunika.domain.Message;
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

        return ResponseEntity.ok().body(lstMensagem.size());
    }

    @GetMapping(value = "/message/{idMensagem}")
    public ResponseEntity buscarMensagemPorId(@PathVariable Integer idMensagem) {

        List<Message> lstMensagem = mensagemService.buscarTodasMensagens(idMensagem);

        return ResponseEntity.ok().body(lstMensagem);
    }

    @PostMapping
    public ResponseEntity incluirMensagensPorLote(@RequestBody CreateMessagesRequest request) {

        String msg = mensagemService.salvarMensagens(request);

        return ResponseEntity.created(null).body(msg);
    }

}
