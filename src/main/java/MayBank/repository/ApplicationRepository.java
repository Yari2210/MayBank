package MayBank.repository;


import MayBank.Model.AppModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository  {

    AppModel findByUsername(String username);


}
