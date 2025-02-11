package com.nayem.databaseauth.common.utilities;

import com.nayem.databaseauth.common.exceptions.EnumNotFoundException;
import com.nayem.databaseauth.common.exceptions.NotFoundException;
import com.nayem.databaseauth.common.exceptions.NullPasswordException;

public final class ExceptionUtil {
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
}
