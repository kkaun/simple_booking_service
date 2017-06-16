package com.kirak.util;

import com.kirak.model.abstraction.BaseEntity;
import com.kirak.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kir on 14.06.2017.
 */
public class ValidationUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationUtil.class);

    private ValidationUtil(){}

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static <T> T checkNotFoundWithId(T object, Number id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, Number id) {
        checkNotFound(found, "id=" + id);
    }


    public static void checkId(BaseEntity baseEntity, Number id){

        try {
            if(baseEntity.isNew()){
                    baseEntity.setId(id);

            } else if (!baseEntity.getId().equals(id)) {
                throw new IllegalArgumentException(baseEntity + " must be with id=" + id);
            }

        } catch(NumberFormatException|NullPointerException ex) {
            LOG.error(ex.toString());
        }
    }

    public static Throwable getErrorRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}
