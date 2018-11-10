package com.contaazul.boleto.beans.convertes;

import com.contaazul.boleto.entities.enums.StatusEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class StatusEnumDeserializer extends JsonDeserializer<StatusEnum> {

    @Override
    public StatusEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String status = jsonParser.getText();
        return Arrays.stream(StatusEnum.values()).filter(statusEnum -> statusEnum.name().equals(status)).findFirst().orElse(null);
    }
}
