package io.github.cartoes.mscard.resources;

import io.github.cartoes.mscard.model.Card;
import io.github.cartoes.mscard.model.ClientCard;
import io.github.cartoes.mscard.service.CardService;
import io.github.cartoes.mscard.service.ClientCardService;
import io.github.cartoes.mscard.service.dto.CardDto;
import io.github.cartoes.mscard.service.dto.ClientCardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
@Slf4j
public class CardResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;
//
//    @GetMapping()
//    public String dados() {
//        return "hello cards";
//    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardDto dto) {
        Card card = dto.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "amount")
    public ResponseEntity<List<Card>> getCardAmountTo(@RequestParam("amount") Long amount) {
        List<Card> cardList = cardService.getCardsAmountLessEqual(amount);
        return ResponseEntity.ok(cardList);
    }

    @GetMapping()
    public ResponseEntity<List<Card>> findAllCards() {
        List<Card> cardList = cardService.findAll();
        return ResponseEntity.ok(cardList);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardDto>> findCardsPerClient(@RequestParam("cpf") String cpf){
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<ClientCardDto> resultList = list.stream()
                .map(ClientCardDto::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }


}
