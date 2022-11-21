package io.github.cartoes.mscreditappraiser.resources;

import io.github.cartoes.mscreditappraiser.exception.ClientDataNotFoundException;
import io.github.cartoes.mscreditappraiser.exception.ErrorComunicationMicroServicesException;
import io.github.cartoes.mscreditappraiser.model.DataAvaliation;
import io.github.cartoes.mscreditappraiser.model.ReturnAvaliationCard;
import io.github.cartoes.mscreditappraiser.model.SituationCredit;
import io.github.cartoes.mscreditappraiser.service.SituationCreditService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserResource {

    private final SituationCreditService service;

    @GetMapping
    public String dados(){
        return "ok...";
    }

    @GetMapping(value = "situation-credit", params = "cpf")
    public ResponseEntity getSituationClient(@RequestParam("cpf") String cpf) {
        try {
           SituationCredit situationCredit = service.getSituationClient(cpf);
        return ResponseEntity.ok(situationCredit);

        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroServicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity creditAvaliation(@RequestBody DataAvaliation data) {
        try {
            ReturnAvaliationCard returnAvaliationCard = service.avaliateCard(data.getCpf(), data.getAmount());
            return ResponseEntity.ok(returnAvaliationCard);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroServicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
