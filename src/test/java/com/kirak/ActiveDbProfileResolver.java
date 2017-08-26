package com.kirak;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Created by Kir on 26.08.2017.
 */
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profile.getActiveDbProfile()};
    }
}
