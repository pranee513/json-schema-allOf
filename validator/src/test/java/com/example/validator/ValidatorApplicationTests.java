package com.example.validator;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ValidatorApplicationTests {

    @Test
    void test() throws ValidationException {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(ValidatorApplicationTests.class.getResourceAsStream("/test.json")));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(ValidatorApplicationTests.class.getResourceAsStream("/invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

}
