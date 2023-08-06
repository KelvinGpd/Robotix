package controllers;

import data.Activity;

import java.util.List;

/**
 * Action Controller defines core methods for Activities.
 */
public class ActionController extends Controller {

    /**
     * Adds an Activity object to the Activity database.
     *
     * @param e The Activity object to be added.
     */
    public void add(Activity e) {
        ActivityDb.add(e);
    }

    /**
     * Retrieves the list of all activities from the Activity database.
     *
     * @return A List containing all Activity objects stored in the database.
     */
    public List<Activity> getActvities() {
        return ActivityDb.read();
    }

}
