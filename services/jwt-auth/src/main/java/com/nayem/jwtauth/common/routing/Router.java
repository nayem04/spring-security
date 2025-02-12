package com.nayem.jwtauth.common.routing;

public final class Router {
    private static final String ROOT = "/api/";
    private static final String VERSION1 = ROOT + "v1/";

    public static final String LOGIN = VERSION1 + "login";
    public static final String HOME = VERSION1 + "home";
    public static final String HELLO_ADMIN = VERSION1 + "hello-admin";
    public static final String HELLO_USER = VERSION1 + "hello-user";

    public static final class Role {
        public static final String GET_ROLES = VERSION1 + "roles";
        public static final String GET_ROLE = VERSION1 + "roles/{id}";
        public static final String CREATE_ROLE = VERSION1 + "roles";
        public static final String UPDATE_ROLE = VERSION1 + "roles/{id}";
        public static final String DELETE_ROLE = VERSION1 + "roles/{id}";
    }

    public static final class User {
        public static final String GET_USERS = VERSION1 + "users";
        public static final String GET_USER = VERSION1 + "users/{id}";
        public static final String CREATE_USER = VERSION1 + "users";
        public static final String UPDATE_USER = VERSION1 + "users/{id}";
        public static final String DELETE_USER = VERSION1 + "users/{id}";
    }
}
