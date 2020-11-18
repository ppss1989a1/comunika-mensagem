package br.com.assertiva.comunika.controller;

import br.com.assertiva.comunika.domain.IncluirMensagensPorLoteRequest;
import br.com.assertiva.comunika.domain.Mensagem;
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

    @GetMapping(value = "/lote/{loteId}")
    public ResponseEntity buscarTodasMensagensDoLote(@PathVariable Integer loteId) {

        List<Mensagem> lstMensagem = mensagemService.buscarTodasMensagens(loteId);

        return ResponseEntity.ok().body(lstMensagem.size());
    }

    @GetMapping(value = "/mensagem/{idMensagem}")
    public ResponseEntity buscarMensagemPorId(@PathVariable Integer idMensagem) {

        List<Mensagem> lstMensagem = mensagemService.buscarTodasMensagens(idMensagem);

        return ResponseEntity.ok().body(lstMensagem);
    }

    @PostMapping
    public ResponseEntity incluirMensagensPorLote(@RequestBody IncluirMensagensPorLoteRequest request) {

        String msg = mensagemService.salvarMensagens(request);

        return ResponseEntity.created(null).body(msg);
    }

}
