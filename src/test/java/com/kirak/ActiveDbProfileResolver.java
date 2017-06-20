package com.kirak;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Created by Kir on 19.06.2017.
 */
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[]{Profile.getActiveDbProfile()};
    }
}
