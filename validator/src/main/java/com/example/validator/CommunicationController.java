package com.example.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class  CommunicationController {

    @PostMapping("/test")
    public void test(@RequestBody  Address request) throws JsonProcessingException {

        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(CommunicationController.class.getResourceAsStream("/test.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(request);

        JSONObject testData = new JSONObject(
                new JSONTokener(str));

        Schema schema = SchemaLoader.load(jsonSchema);
        try {
            schema.validate(testData);
        }
        catch (ValidationException e) {
            System.out.println(e.getAllMessages());
        }
    }
}