package com.nayem.databaseauth.common.utilities;

import com.nayem.databaseauth.common.exceptions.*;

public final class ExceptionUtil {
    public static AlreadyExistsException getAlreadyExistsException(String name) {
        return new AlreadyExistsException(name + " already exists");
    }

    public static AlreadyExistsException getAlreadyExistsException(String name, String param) {
        return new AlreadyExistsException(name + " already exists with : " + param);
    }

    public static EnumNotFoundException getEnumNotFoundException(String enumName) {
        return new EnumNotFoundException((enumName + " enum could not be found."));
    }

    public static NotFoundException getNotFoundException(String name, Long id) {
        return new NotFoundException("Could not find '" + name + "' with id : " + id);
    }

    public static NotFoundException getNotFoundException(String name, String code) {
        return new NotFoundException("Could not find '" + name + "' by : " + code);
    }

    public static NullPasswordException getNullPasswordException() {
        return new NullPasswordException("Password could not be empty.");
    }

    public static UserAlreadyExistsException getUserAlreadyExistsException(String name) {
        return new UserAlreadyExistsException(name + " already exists");
    }

    public static UserAlreadyExistsException getUserAlreadyExistsException(String name, String param) {
        return new UserAlreadyExistsException(name + " already exists with : " + param);
    }
}
