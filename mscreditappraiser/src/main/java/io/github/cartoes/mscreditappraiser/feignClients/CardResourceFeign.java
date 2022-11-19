package io.github.cartoes.mscreditappraiser.feignClients;

import io.github.cartoes.mscreditappraiser.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mscard", path = "/cards")
public interface CardResourceFeign {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> findCardsPerClient(@RequestParam("cpf") String cpf);
}
