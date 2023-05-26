package MayBank.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"createddate", "updateddate", "hibernateLazyInitializer", "handler"},
        allowGetters = true, ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class BaseRequest<T> extends BaseObject implements Request<T> {

    private static final long serialVersionUID = 9063730936381357510L;

//    @JsonProperty("identity")
//    protected Identity identity;
//
//    @JsonProperty("paging")
//    protected Paging paging;

    @JsonProperty("parameter")
    protected T parameter;

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

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "BaseRequest [parameter=" + parameter + "]";
    }


}
