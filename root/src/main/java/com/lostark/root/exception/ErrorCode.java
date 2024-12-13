package com.lostark.root.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST-001", "익셉션 테스트"),
    RUNTIME_EXCEPTION(HttpStatus.NOT_FOUND, "VALID-001", "예측되지 않은 오류가 발생했습니다. "),

    //API 요청 에러
    // ErrorCode 429
    TOO_MANY_API_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "API-001", "API 요청 분당 100회 초과"),
    // ErrorCode 401
    API_KEY_ERROR(HttpStatus.UNAUTHORIZED, "API-002", "API KEY 가 유효하지 않습니다."),
    // ErrorCode 400 org.springframework.web.client.HttpClientErrorException$BadRequest: 400 Check parameter for CategoryCode: [no body]
    NO_PARAMETER(HttpStatus.BAD_REQUEST, "API-003", "입력되지 않은 값이 있습니다."),
    NO_CONTENT(HttpStatus.BAD_REQUEST, "API-004", "검색 결과가 없습니다.");

    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;				// ACCOUNT-001
    private final String message;			// 설명
}

