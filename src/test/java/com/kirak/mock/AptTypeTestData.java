package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.AptType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kir on 20.06.2017.
 */
public class AptTypeTestData {

    public static final ModelMatcher<AptType> APT_TYPE_MATCHER = ModelMatcher.of(AptType.class);

    public static final Short TYPE1_ID = (short)1;
    public static final Short TYPE2_ID = (short)2;
    public static final Short TYPE3_ID = (short)3;
    public static final Short TYPE4_ID = (short)4;
    public static final Short TYPE5_ID = (short)5;

    public static final AptType TYPE1 = new AptType(TYPE1_ID, "ONE SINGLE BED", "STANDARD", (short) 1);
    public static final AptType TYPE2 = new AptType(TYPE2_ID, "TWO SINGLE BEDS", "STANDARD", (short) 2);
    public static final AptType TYPE3 = new AptType(TYPE3_ID, "TWO SEPARATE BEDS", "STANDARD", (short) 2);
    public static final AptType TYPE4 = new AptType(TYPE4_ID, "TWO SINGLE BEDS", "SUPERIOR", (short) 2);
    public static final AptType TYPE5 = new AptType(TYPE5_ID, "TWO SEPARATE BEDS", "SUPERIOR", (short) 2);

    public static final List<AptType> APT_TYPES = Arrays.asList(TYPE1, TYPE2, TYPE3, TYPE4, TYPE5);

    public static AptType getCreatedAptType() {
        return new AptType(null, "ARRANGEMENT325", "FAMILY_NEW", (short) 5);
    }

    public static AptType getUpdatedAptType() {
        return new AptType(TYPE1_ID, "ARRANGEMENT325", "STANDARD_EDITED", (short) 1);
    }

}
