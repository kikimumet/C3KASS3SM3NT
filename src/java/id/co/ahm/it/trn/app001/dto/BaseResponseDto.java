package id.co.ahm.it.trn.app001.dto;

/**
 *
 * @author fadli
 */

public class BaseResponseDto<T> {

    private String status;
    private String message;
    private T data;

    public BaseResponseDto() {
    }

    public BaseResponseDto(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponseDto<T> success(String message, T data) {
        return new BaseResponseDto<>("1", message, data);
    }

    public static <T> BaseResponseDto<T> error(String message, T data) {
        return new BaseResponseDto<>("0", message, data);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
