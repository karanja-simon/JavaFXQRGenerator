/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author RESEARCH2
 */
public class Settings {

    private final Preferences prefs = Preferences.userNodeForPackage(getClass());

    public void setHistorySize(int size) {        
        prefs.putInt("history-size", size);
    }

    public int getHistorySize() {
        return prefs.getInt("history-size", 0);
    }

    public void setSortOrder(String sortBy) {
        prefs.put("sort-by", sortBy);
    }

    public String getSortOrder() {
        return prefs.get("sort-by", "date");
    }

}
