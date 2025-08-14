package com.tj330.bookservice;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "book")
public record ApplicationProperties(@DefaultValue("10") @Min(1) int pageSize) {
}
