package com.nology.marvelbackend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
public class CharacterController {

    WebClient webClient = WebClient.create("https://gateway.marvel.com/v1/public/");
    Auth_Key auth_key = new Auth_Key();

    public List<MarvelCharacter> getCharacters(int limit, int offset) {
        return new ArrayList<>();
    }

    @GetMapping("/Character")
    public ResponseEntity<List<MarvelCharacter>> getCharacterIds() {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode response = webClient
                .get()
                .uri("characters?limit="+10+"&offset="+0+auth_key.getURI())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        System.out.println(response);

        List<MarvelCharacter> characterList = new ArrayList<>();
        ArrayNode characters = (ArrayNode) response.path("data").path("results");

        for (JsonNode result: characters) {
            characterList.add(new MarvelCharacter(result.path("id").asInt(),
                    result.path("name").asText(),result.path("description").asText()));
        }

        System.out.println(characterList);


        return ResponseEntity.status(HttpStatus.OK).body(characterList);

    }


}
