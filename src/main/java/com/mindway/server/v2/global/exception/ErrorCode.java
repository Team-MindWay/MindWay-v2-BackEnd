package com.mindway.server.v2.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    /* Token */
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    INVALID_TOKEN_TYPE(401, "유효하지 않은 토큰 타입입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),

    /* Member */
    MEMBER_NOT_FOUND(404, "Member Not Found"),

    /* orders */
    ORDER_NOT_FOUND(404,"해당 도서 신청을 찾을 수 없습니다."),
    NOT_ACCESS_STUDENT(403,"접근 권한이 없습니다."),

    /* goal */
    EXIST_ALREADY_GOAL(400, "이미 목표 설정이 되어있습니다."),
    NOT_EXIST_GOAL(404, "유저가 설정한 목표가 존재하지 않습니다"),

    /* notice */
    NOT_FOUND_NOTICE(404, "등록된 공지가 존재하지 않습니다."),

    /* book */
    NOT_FOUND_BOOK(404, "등록된 독후감을 찾을 수 없습니다."),
    NOT_SAME_AUTHOR(403, "작성자가 아닙니다."),

    /* image */
    FILE_EXTENSION_INVALID(400, "파일 확장자가 유효하지 않습니다."),

    /* event */
    INVALID_START_AND_END_DATE(400, "이벤트 시작과 끝이 올바르지 않습니다."),
    NOT_FOUND_EVENT(404, "해당하는 이벤트를 찾지 못 했습니다."),
    INVALID_USER_EVENT(400, "해당 이벤트를 등록한 유저가 아닙니다."),

    /* recommend */
    NOT_FOUND_RECOMMEND_BOOK(404, "추천 도서를 찾을 수 없습니다."),

    /* server */
    INTERNAL_SERVER_ERROR(500, "예기치 못한 서버 에러");

    private final int status;
    private final String message;
}