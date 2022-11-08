package org.hcmus.ln02.util.mapper.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  @Override
  public String convertToDatabaseColumn(Map<String, Object> customerInfo) {

    String customerInfoJson;
    customerInfoJson = objectMapper.writeValueAsString(customerInfo);

    return customerInfoJson;
  }

  @SneakyThrows
  @Override
  public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {

    Map<String, Object> customerInfo;
    customerInfo = objectMapper.readValue(customerInfoJSON, new TypeReference<HashMap<String, Object>>() {});

    return customerInfo;
  }
}
