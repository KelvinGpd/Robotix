package data.databases;

import data.Activity;

public class ActivityDb extends Db<Activity> {

    public ActivityDb (String path) {
        super(path);
    }


    @Override
    protected Class<Activity[]> getArrayClass() {
        return Activity[].class;
    }

}



