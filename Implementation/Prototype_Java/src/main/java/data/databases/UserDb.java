package data.databases;

import data.User;


public class UserDb extends Db<User> {

    public UserDb(String path) {
        super(path);
    }

    @Override
    protected Class<User[]> getArrayClass() {
        return User[].class;
    }

}
