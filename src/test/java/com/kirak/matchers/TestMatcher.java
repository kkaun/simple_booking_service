package com.kirak.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import com.kirak.web.json.JsonUtil;

/**
 * Created by Kir on 21.06.2017.
 */
public abstract class TestMatcher<T> extends BaseMatcher<String>{

    protected T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
        return compare(expected, (String) actual);
    }

    protected abstract boolean compare(T expected, String actual);

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }

}
