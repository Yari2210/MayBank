package MayBank.config;



public interface Request<T> extends BaseInterface {

//    public Identity getIdentity();
//
//    public void setIdentity(Identity identity);
//
//    public Paging getPaging();
//
//    public void setPaging(Paging paging);

    public T getParameter();

    public void setParameter(T parameter);

}
