package MayBank.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AppModel2 {

    @JsonProperty("total_count")
    private Integer total_count;

    @JsonProperty("incomplete_results")
    private Boolean incomplete_results;

    @JsonProperty("items")
    private List<ItemModel> items;

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}
