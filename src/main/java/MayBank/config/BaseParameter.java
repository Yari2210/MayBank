package MayBank.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseParameter<T> extends BaseObject implements Parameter<T> {

    private static final long serialVersionUID = -7079852813633989802L;
    
    public BaseParameter() { }

    @JsonProperty("sort")
    protected Map<String, String> sort;

    @JsonProperty("column")
    protected List<String> column;

    @JsonProperty("criteria")
    protected Map<String, String> criteria;

    @JsonProperty("data")
    protected T data;

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public Map<String, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<String, String> criteria) {
        this.criteria = criteria;
    }

    public void addCriteria(String k, String v) {
        if (this.criteria == null) {
            this.criteria = new HashMap<>();
        }
        this.criteria.put(k, v);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseParameter{" +
                "sort=" + sort +
                ", column=" + column +
                ", criteria=" + criteria +
                ", data=" + data +
                '}';
    }
}
