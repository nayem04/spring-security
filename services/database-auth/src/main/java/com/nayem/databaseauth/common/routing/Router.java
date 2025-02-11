package com.nayem.databaseauth.common.routing;

public final class Router {
    private static final String ROOT = "/api/";
    private static final String VERSION1 = "v1/";

    public static final String HOME = ROOT + VERSION1 + "home";
    public static final String HELLO_USER = ROOT + VERSION1 + "hello-user";
    public static final String HELLO_ADMIN = ROOT + VERSION1 + "hello-admin";

    public static final class Role {
        public static final String GET_ROLES = ROOT + VERSION1 + "roles";
        public static final String GET_ROLE = ROOT + VERSION1 + "roles/{id}";
        public static final String CREATE_ROLE = ROOT + VERSION1 + "roles";
        public static final String UPDATE_ROLE = ROOT + VERSION1 + "roles/{id}";
        public static final String DELETE_ROLE = ROOT + VERSION1 + "roles/{id}";
    }

    public static final class User {
        public static final String GET_USERS = ROOT + VERSION1 + "users";
        public static final String GET_USER = ROOT + VERSION1 + "users/{id}";
        public static final String CREATE_USER = ROOT + VERSION1 + "users";
        public static final String UPDATE_USER = ROOT + VERSION1 + "users/{id}";
        public static final String DELETE_USER = ROOT + VERSION1 + "users/{id}";
    }
}
