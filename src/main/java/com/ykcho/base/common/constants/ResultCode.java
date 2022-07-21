package com.ykcho.base.common.constants;

import lombok.Getter;

@Getter
public enum ResultCode {
	OK(0, "OK"),
	BAD_REQUEST(9400, "badRequest"),
	NOT_FOUND(9404, "notFound"),
	SERVER_ERROR(9500, "internalServerError"),
	EXIST(4000, "EXIST ERROR"),
	SEND_MAIL_FAIL(1000, "Send mail Fail"),
	ETC_ERROR(9999, "etcError");

	private final int code;
	private final String description;

	ResultCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
}
