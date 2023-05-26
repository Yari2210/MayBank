package MayBank.config;


public interface Response<T> extends BaseInterface {

    public Status getStatus();

    public void setStatus(Status status);

//    public Paging getPaging();
//
//    public void setPaging(Paging paging);
//
//    public Identity getIdentity();
//
//    public void setIdentity(Identity identity);

    public T getResult();

    public void setResult(T result);

}
