package MayBank.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonView(Views.Basic.class)
public class BaseResponse<T> extends BaseObject implements Response<T> {

    private static final long serialVersionUID = -5728598166275182978L;

    @JsonProperty("status")
    protected Status status;

//    @JsonProperty("identity")
//    protected Identity identity;
//
//    @JsonProperty("paging")
//    protected Paging paging;

    @JsonProperty("result")
    protected T result;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public Identity getIdentity() {
//        return identity;
//    }
//
//    public void setIdentity(Identity identity) {
//        this.identity = identity;
//    }
//
//    public Paging getPaging() {
//        return paging;
//    }
//
//    public void setPaging(Paging paging) {
//        this.paging = paging;
//    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResponse [status=" + status + ", result=" + result
                + "]";
    }


}
