package org.hcmus.ln02.util.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.SneakyThrows;

@Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  @SneakyThrows
  public String convertToDatabaseColumn(Map<String, Object> info) {
    return objectMapper.writeValueAsString(info);
  }

  @Override
  @SneakyThrows
  public Map<String, Object> convertToEntityAttribute(String json) {
    return objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});
  }
}
