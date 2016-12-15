package domain.user;

import io.swagger.model.User;

public interface IUserManager {
    
    User validate(String username, String password);
    
}
