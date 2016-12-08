package domain.user;

import io.swagger.model.User;

public interface IUsermanager {
    
    User validate(String username, String password);
    
}
