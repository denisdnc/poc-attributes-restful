package com.example.putdefault.api;

public enum Features {
    FEATURE_RUN_MIGRATION(true),
    FEATURE_ADD_MIGRATION_TO_REMOVE_OLD_VALUE(true),
    FEATURE_ADD_MIGRATION_TO_ADD_DEFAULT_TO_NEW_VALUE(true);

    private boolean flag;

    public boolean isOn() {
        return flag;
    }

    Features(boolean flag) {
        this.flag = flag;
    }
}
