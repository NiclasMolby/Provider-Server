package domain.user;

import common.Logger;
// test for the logger
public class Main {

    public static void main(String[] args) {
        Logger.get().log(Logger.LogType.INFO, "test");
    }
}
