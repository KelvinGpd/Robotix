package data.databases;

import data.Activity;

/**
 * ActionDb is the Database for Storing Activity Objects.
 */
public class ActivityDb extends Db<Activity> {

    public ActivityDb (String path) {
        super(path);
    }


    @Override
    protected Class<Activity[]> getArrayClass() {
        return Activity[].class;
    }

}



