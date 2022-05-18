package com.nology.marvelbackend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CharacterController {

    WebClient webClient = WebClient.create("https://gateway.marvel.com/v1/public/");
    Auth_Key auth_key = new Auth_Key();
    ObjectMapper mapper = new ObjectMapper();
    List<Integer> characterIds = new ArrayList<>();


    public void loadCharacters () {
        for (int i = 0; i < 1600; i = i+25) {
            System.out.println("Retrieving characters: "+ i + " to " + (i+25));
            characterIds.addAll(getCharacters(25,i));
        }
    }

    public List<Integer> getCharacters(int limit, int offset) {

        JsonNode response = webClient
                .get()
                .uri("characters?limit="+limit+"&offset="+offset+"&"+auth_key.getURI())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        List<Integer> responseList = new ArrayList<>();
        ArrayNode characters = (ArrayNode) response.path("data").path("results");

        for (JsonNode result: characters) {
            responseList.add(result.path("id").asInt());
        }

        return responseList;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<Integer>> getCharacterIds() {

        if (characterIds.size() == 0) {
            System.out.println("No Character List Found... Retrieving Characters...");
            loadCharacters();
        }
        return ResponseEntity.status(HttpStatus.OK).body(characterIds);
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<MarvelCharacter> getCharacterById (@PathVariable String id) {


        return ResponseEntity.status(HttpStatus.OK).body(characterById(id));
    }

    private MarvelCharacter characterById (String id) {
        JsonNode response = webClient.get()
                .uri("characters/"+id+"?"+auth_key.getURI())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        ArrayNode characterArray = (ArrayNode) response.path("data").path("results") ;

        MarvelCharacter character = new MarvelCharacter();

        Thumbnail characterThumbnail = new Thumbnail();

        for (JsonNode characterJson: characterArray) {
            character.setId(characterJson.path("id").asInt());
            character.setName(characterJson.path("name").asText());
            character.setDescription(characterJson.path("description").asText());
            characterThumbnail.setPath(characterJson.path("thumbnail").path("path").asText());
            characterThumbnail.setExtension(characterJson.path("thumbnail").path("extension").asText());
            character.setThumbnail(characterThumbnail);
        }
        return character;
    }

    @GetMapping("/characters/{id}/{languageCode}")
    public ResponseEntity<MarvelCharacter> getCharacterByIdAndLanguage (@PathVariable String id, @PathVariable String languageCode) {

        MarvelCharacter characterToTranslate = characterById(id);

        Translate translate = TranslateOptions
                .newBuilder()
                .setApiKey(auth_key.getGoogleKey())
                .setTargetLanguage(languageCode)
                .build()
                .getService();

        Translation translation = translate.translate(characterToTranslate.getDescription());

        characterToTranslate.setDescription(translation.getTranslatedText());

        return ResponseEntity.status(HttpStatus.OK).body(characterToTranslate);
    }


}
