package domain.controller;

import io.swagger.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IController {

    File getPDF(int productID);

    boolean requestUpdate();

    PublicKey getPublicKey();
    
}
