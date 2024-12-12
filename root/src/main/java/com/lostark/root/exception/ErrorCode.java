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
    TOO_MANY_API_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "API-001", "API 요청 분당 100회 초과");

    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;				// ACCOUNT-001
    private final String message;			// 설명
}
