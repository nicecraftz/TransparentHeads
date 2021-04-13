package me.alexmc.utils;

public enum Fields {

    ADDED("added"),
    RELOADED("reloaded"),
    NO_PERM("no-perm"),
    GIVE_LINK("givelink"),
    LIST_DISABLED("list-disabled"),
    LIST_NUMBER_FORMATTING("list-number-formatting"),
    GET_STRING("get-string"),
    IS_LIST_ENABLED("server-list"),
    TUTORIAL_LIST("tutorial"),
    HEADS_LIST("heads-list");

    private final String path;

    Fields(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
