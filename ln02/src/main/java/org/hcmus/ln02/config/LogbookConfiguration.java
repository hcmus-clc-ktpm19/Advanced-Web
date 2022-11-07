package org.hcmus.ln02.config;

import static org.zalando.logbook.Conditions.contentType;
import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;
import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogFormatter;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.DefaultSink;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.SplunkHttpLogFormatter;
import org.zalando.logbook.StreamHttpLogWriter;
import org.zalando.logbook.json.JsonHttpLogFormatter;


@Configuration
public class LogbookConfiguration {

  @Bean
  public Logbook logbook() {
    Logbook logbook = Logbook.builder()
        .condition(exclude(
            requestTo("**/actors/**"))) // don't log request and response of the Actor API
        .bodyFilter(jsonPath("$.name").replace("new string"))
        .headerFilter(authorization -> authorization.update("Authorization",
            "authorization header is hidden"))
        .sink(new DefaultSink(
            new JsonHttpLogFormatter(),
            new DefaultHttpLogWriter()))
        .build();
    return logbook;
  }
}
