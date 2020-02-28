package com.itchen.usercenter.enums;

import com.itchen.usercenter.api.IRCode;

/**
 * REST API 状态码 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-28
 */
public enum ApiRCode implements IRCode {
    /**
     * 失败
     */
    FAILED(-1, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(0, "执行成功");

    private final long code;

    private final String msg;

    ApiRCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiRCode fromCode(long code) {
        ApiRCode[] ecs = ApiRCode.values();
        for (ApiRCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
