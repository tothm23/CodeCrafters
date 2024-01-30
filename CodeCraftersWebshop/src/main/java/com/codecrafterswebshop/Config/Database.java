package com.codecrafterswebshop.Config;

/**
 *
 * @author tothm23
 */
public class Database {

    private static final String persistenceUnitName = "com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU";

    public static String getPersistenceUnitName() {
        return persistenceUnitName;
    }

}
