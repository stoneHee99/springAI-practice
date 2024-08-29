package com.stone.springaiprac.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record SummarizeResponse(@JsonPropertyDescription("변환된 텍스트") String text) {

}
