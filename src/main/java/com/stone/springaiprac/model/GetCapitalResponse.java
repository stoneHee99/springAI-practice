package com.stone.springaiprac.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("요청할 때 알려줬던 나라 이름") String country,
                                 @JsonPropertyDescription("요청할 때 요청했던 나라의 수도 이름") String capital,
                                 @JsonPropertyDescription("결과로 알려준 수도에서 추천해줄 명소 한 곳의 이름") String hotPlace) {
}
