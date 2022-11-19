package io.github.cartoes.mscreditappraiser.feignClients;

import io.github.cartoes.mscreditappraiser.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclient", path = "/clients")
public interface ClientResourceFeign {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> findByCpf(@RequestParam("cpf") String cpf);
}
