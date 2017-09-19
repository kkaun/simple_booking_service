package com.kirak.service;

import com.kirak.ActiveDbProfileResolver;
import com.kirak.Profile;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import static com.kirak.Profile.DATAJPA;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by Kir on 19.06.2017.
 */

@ContextConfiguration({ "classpath:spring/spring-app.xml",
                        "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDbForTest.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {

    /* IMPORTANT: Uncomment each entity's (in path 'model') toString() method for proper testing of services */

    private static final Logger LOG = LoggerFactory.getLogger(AbstractServiceTest.class);

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public Environment env;

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

    public boolean isJpaBased() {
        return env.acceptsProfiles(DATAJPA);
    }

    @AfterClass
    public static void printResult() {
        LOG.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
        results.setLength(0);
    }

    public static <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        try {
            runnable.run();
            Assert.fail("Expected " + exceptionClass.getName());
        } catch (Exception e) {
            Assert.assertThat(getRootCause(e), instanceOf(exceptionClass));
        }
    }


}
