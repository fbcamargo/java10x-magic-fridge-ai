package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.dto.FoodItemDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {
    private final WebClient webClient;
    private final String apiKey = System.getenv("CHATGPT_API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemDto> foodItens) {
        String alimentos = foodItens
                .stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Valudade %s",
                        item.getNome(), item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("/n"));

        String prompt = "Baseado no meu banco de dados faça uma receita com os seguintes itens \n" + alimentos;

        Map<String, Object> requestBody = Map.of(
            "model","gpt-4o-mini",
            "messages", List.of(
                Map.of(
                    "role", "system",
                    "content", "Você é um assistente que cria receitas."
                ),
                Map.of(
                    "role", "user",
                    "content", prompt
                )
            )
        );

        return webClient
                .post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if(choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.getFirst().get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita foi gerada";
                });
    }
}
