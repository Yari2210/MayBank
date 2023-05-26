package MayBank.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(Include.NON_NULL)
@JsonView(Views.Basic.class)
public class Status extends BaseObject {

    private static final long serialVersionUID = -3214983983781773869L;

    public static final String SUCCESS_CODE = "0000";
    public static final String SUCCESS_DESC = "Success";

    public static final String ERROR_EXIST_CODE = "9002";
    public static final String ERROR_EXIST_DESC = "Data already exist";

    public static final String ERROR_NOTFOUND_CODE = "9003";
    public static final String ERROR_NOTFOUND_DESC = "Data Not Found";

    public static final String ERROR_INVALID_DATA_CODE = "9005";
    public static final String ERROR_INVALID_DATA_DESC = "Invalid Data";

    public static final String APP_ERROR_CODE = "9997";
    public static final String APP_ERROR_DESC = "Application Error";

    public static final String SYS_ERROR_CODE = "9998";
    public static final String SYS_ERROR_DESC = "Systems Error";

    public static final String ERROR_CODE = "9999";
    public static final String ERROR_DESC = "Error";

    private String responsecode;

    private String responsedesc;

    private String responsemessage;

    private String stacktrace;

    public Status() {
    }

    public Status(String responsecode) {
        this.responsecode = responsecode;
        if (responsecode.equals(APP_ERROR_CODE)) {
            this.responsedesc = APP_ERROR_DESC;
        }
        if (responsecode.equals(SYS_ERROR_CODE)) {
            this.responsedesc = SYS_ERROR_DESC;
        }
        if (responsecode.equals(ERROR_CODE)) {
            this.responsedesc = ERROR_DESC;
        }
    }

    public Status(String responsecode, String responsedesc) {
        this.responsecode = responsecode;
        this.responsedesc = responsedesc;
    }

    public Status(String responsecode, String responsedesc, String responsemessage) {
        this.responsecode = responsecode;
        this.responsedesc = responsedesc;
        this.responsemessage = responsemessage;
    }

    public Status(String responsecode, String responsedesc, String responsemessage, String stacktrace) {
        this.responsecode = responsecode;
        this.responsedesc = responsedesc;
        this.responsemessage = responsemessage;
        this.stacktrace = stacktrace;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponsedesc() {
        return responsedesc;
    }

    public void setResponsedesc(String responsedesc) {
        this.responsedesc = responsedesc;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "Status{" +
                "responsecode='" + responsecode + '\'' +
                ", responsedesc='" + responsedesc + '\'' +
                ", responsemessage='" + responsemessage + '\'' +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }

    public static Status DATA_ALREADY_EXIST(String message) {
        return new Status(ERROR_EXIST_CODE, ERROR_EXIST_DESC, message);
    }

    public static Status SUCCESS() {
        return SUCCESS("");
    }

    public static Status SUCCESS(String message) {
        return new Status(SUCCESS_CODE, SUCCESS_DESC, message);
    }

    public static Status DATA_NOT_FOUND(String message) {
        return new Status(ERROR_NOTFOUND_CODE, ERROR_NOTFOUND_DESC, message);
    }

    public static Status INVALID(String message) {
        return new Status(ERROR_INVALID_DATA_CODE, ERROR_INVALID_DATA_DESC, message);
    }

    public static Status APP_ERROR() {
        return APP_ERROR("");
    }

    public static Status APP_ERROR(String message) {
        return new Status(APP_ERROR_CODE, APP_ERROR_DESC, message);
    }

    public static Status SYS_ERROR() {
        return SYS_ERROR("");
    }

    public static Status SYS_ERROR(String message) {
        return new Status(SYS_ERROR_CODE, SYS_ERROR_DESC, message);
    }

    public static Status ERROR() {
        return ERROR("an error has occured");
    }

    public static Status ERROR(String message) {
        return new Status(ERROR_CODE, ERROR_DESC, message);
    }
}
