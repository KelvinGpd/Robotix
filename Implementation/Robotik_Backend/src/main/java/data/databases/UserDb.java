package data.databases;

import data.User;


/**
 * UserDb is the Database for Storing User Objects.
 */
public class UserDb extends Db<User> {

    public UserDb(String path) {
        super(path);
    }

    @Override
    protected Class<User[]> getArrayClass() {
        return User[].class;
    }

}
