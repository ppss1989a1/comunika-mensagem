package br.com.assertiva.comunika.service;

import br.com.assertiva.comunika.domain.IncluirMensagensPorLoteRequest;
import br.com.assertiva.comunika.domain.Lote;
import br.com.assertiva.comunika.domain.Mensagem;
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

    public List<Mensagem> buscarTodasMensagens(Integer loteId) {

        List<Mensagem> list = mensagemRepository.mensagensDoLote(loteId);

        return list;
    }

    public Mensagem buscarMensagemPorId(Integer idMensagem) throws BadRequestException {

        Optional<Mensagem> mensagem = mensagemRepository.findById(idMensagem);

        return mensagem.orElseThrow(() -> new BadRequestException("spooky"));
    }

    public String salvarMensagens(IncluirMensagensPorLoteRequest request) {

        List<Mensagem> lstMensagem = new ArrayList<>();
        Integer count = 0;

        for (Lote lote : request.getLstLote()) {

            for (int i = 0; i < lote.getMessageAmount(); i++) {

                Mensagem mensagem = new Mensagem();
                mensagem.setInclusao(LocalDateTime.now().toString());
                mensagem.setLoteId(lote.getBatchId());
                mensagem.setMensagem(request.getMensagem());
                mensagem.setNumero(request.getLstNumeros().get(count));
                mensagem.setStatus(lote.getStatus());

                lstMensagem.add(mensagem);
                count++;
            }
        }

        return "Mensagens Salvas";
    }


}
