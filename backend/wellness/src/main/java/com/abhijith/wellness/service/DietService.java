package com.abhijith.wellness.service;

import com.abhijith.wellness.exception.AthleteNotFoundException;
import com.abhijith.wellness.exception.WeightPlanNotFoundException;
import com.abhijith.wellness.feign.FeignClientService;
import com.abhijith.wellness.model.DailyDiet;
import com.abhijith.wellness.model.DietRecommendation;
import com.abhijith.wellness.model.WeightPlan;
import com.abhijith.wellness.repo.DailyDietRepository;
import com.abhijith.wellness.repo.WeightPlanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class DietService {

    @Autowired
    private DailyDietRepository dailyDietRepository;

    @Autowired
    private WeightPlanRepository weightPlanRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignClientService feignClientService;

    private final String API_URL_TEMPLATE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s";


    public WeightPlan createWeightPlan(WeightPlan plan){
        if (!feignClientService.validateAthleteId(plan.getAthleteId())){
            throw new AthleteNotFoundException("athlete not found");
        }
        Optional<WeightPlan> weightPlan = weightPlanRepository.findByAthleteId(plan.getAthleteId());
        if (weightPlan.isPresent()){
            plan.setId(weightPlan.get().getId());
        }
        return weightPlanRepository.save(plan);
    }

    public List<DailyDiet> getDailyDietListById(String athleteId){
        if (!feignClientService.validateAthleteId(athleteId)){
            throw new AthleteNotFoundException("athlete not found");
        }
        return dailyDietRepository.findAllByAthleteId(athleteId);
    }

    public DailyDiet createOrUpdateDailyDiet(DailyDiet diet){
        if (!feignClientService.validateAthleteId(diet.getAthleteId())){
            throw new AthleteNotFoundException("athlete not found");
        }
        return dailyDietRepository.save(diet);
    }

    public DietRecommendation getAIRecommendedDiet(String athleteId) throws JsonProcessingException {

        Optional<WeightPlan> weightPlan = weightPlanRepository.findByAthleteId(athleteId);
        if (weightPlan.isEmpty()) {
            throw new WeightPlanNotFoundException();
        }

        // Define the JSON schema (for reference in the prompt)
        String jsonSchema = "{ \"type\": \"object\", \"properties\": { \"breakfast\": {\"type\": \"string\"}, \"lunch\": {\"type\": \"string\"}, \"dinner\": {\"type\": \"string\"} } }";

        // Incorporate the schema into the prompt
        String prompt = "Generate a dummy (not real) JSON response that follows this schema: " + jsonSchema +
                " for a person whose daily calorie goal is " + weightPlan.get().getDailyCalorieGoal() +
                " and has a preference for " + weightPlan.get().getPreference() + " food. Only provide JSON, no other response.";

        String geminiKey = "-";
        String apiUrl = String.format(API_URL_TEMPLATE, geminiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Constructing the JSON request body
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partsNode = objectMapper.createObjectNode();
        partsNode.put("text", prompt);
        contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));

        // Adding the generationConfig with JSON mode
        ObjectNode generationConfigNode = objectMapper.createObjectNode();
        generationConfigNode.put("response_mime_type", "application/json");

        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));
        requestBodyNode.set("generationConfig", generationConfigNode); // Enabling JSON mode

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestBodyNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct JSON request body", e);
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        // Parsing the API response to extract the JSON text from the nested structure
        JsonNode rootNode = objectMapper.readTree(responseBody);
        JsonNode textNode = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");

        // Convert the text node (which is a JSON string) into an actual JSON object
        String jsonString = textNode.asText();


        // Deserialize the JSON response into DietPlanResponse class
        DietRecommendation dietPlanResponse = objectMapper.readValue(jsonString, DietRecommendation.class);

        return dietPlanResponse;
    }

}
