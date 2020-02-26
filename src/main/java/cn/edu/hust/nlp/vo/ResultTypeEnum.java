package cn.edu.hust.nlp.vo;

public enum ResultTypeEnum {

    SERVICE_SUCCESS(200, "success"),
    PARAM_ERROR(40001, "param error"),
    NULL_RESULT(40002, "null result"),
    EXIST_RESULT(40003, "exist result"),
    NOT_FOUND(404, "not found"),
    SERVICE_ERROR(500, "server error");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
