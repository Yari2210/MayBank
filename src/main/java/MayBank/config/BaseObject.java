package MayBank.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"created_date", "updated_date", "hibernateLazyInitializer", "handler", "detail"},
        allowGetters = true, ignoreUnknown = true)
public abstract class BaseObject implements BaseInterface {
    private static final long serialVersionUID = 6617914802588104637L;
}
