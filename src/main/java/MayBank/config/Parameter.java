package MayBank.config;


import java.util.List;
import java.util.Map;

public interface Parameter<T> extends BaseInterface {

    public Map<String, String> getSort();

    public void setSort(Map<String, String> sort);

    public List<String> getColumn();

    public void setColumn(List<String> column);

    public Map<String, String> getCriteria();

    public void setCriteria(Map<String, String> criteria);

    public T getData();

    public void setData(T data);

}
