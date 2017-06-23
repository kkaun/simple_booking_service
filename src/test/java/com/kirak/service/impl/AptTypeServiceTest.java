package com.kirak.service.impl;

import com.kirak.model.AptType;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.AptTypeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.AptTypeTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class AptTypeServiceTest extends AbstractServiceTest {

    @Autowired
    private AptTypeService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        AptType created = getCreatedAptType();
        APT_TYPE_MATCHER.assertCollectionEquals(Arrays.asList(TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, created), service.getAll());
    }

    @Test
    public void update() throws Exception {
        AptType edited = getUpdatedAptType();
        service.update(edited);
        APT_TYPE_MATCHER.assertEquals(edited, service.get(TYPE1_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(TYPE1_ID);
        APT_TYPE_MATCHER.assertCollectionEquals(Arrays.asList(TYPE2, TYPE3, TYPE4, TYPE5), service.getAll());
    }

    @Test
    public void get() throws Exception {
        APT_TYPE_MATCHER.assertEquals(TYPE2, service.get(TYPE2_ID));
    }

    @Test
    public void getAll() throws Exception {
        APT_TYPE_MATCHER.assertCollectionEquals(APT_TYPES, service.getAll());
    }

}