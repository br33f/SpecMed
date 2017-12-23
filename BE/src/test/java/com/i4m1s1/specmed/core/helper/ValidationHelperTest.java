package com.i4m1s1.specmed.core.helper;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.Valid;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidationHelperTest {
    private static final String PESEL_BAD_LENGTH_10 = "1111111110";
    private static final String PESEL_BAD_LENGTH_12 = "1111111112";
    private static final String PESEL_BAD_LETTERS = "94LITERKI01";
    private static final String PESEL_GOOD = "95090911115";
    private static final String TELEPHONE_LONG_GOOD = "+48 999 990 099";

    @Test
    public void validatePeselLength() throws Exception {
        //given

        //when
        boolean isValidMin = ValidationHelper.validatePesel(PESEL_BAD_LENGTH_10);
        boolean isValidMax = ValidationHelper.validatePesel(PESEL_BAD_LENGTH_12);
        boolean isValidEq = ValidationHelper.validatePesel(PESEL_GOOD);

        //then
        assertFalse(isValidMin);
        assertFalse(isValidMax);
        assertTrue(isValidEq);
    }

    @Test
    public void validatePeselContainsText() throws Exception {
        //given
        //when
        boolean isValidFalse = ValidationHelper.validatePesel(PESEL_BAD_LETTERS);
        boolean isValidTrue = ValidationHelper.validatePesel(PESEL_GOOD);

        //then
        assertFalse(isValidFalse);
        assertTrue(isValidTrue);
    }

    @Test
    public void validateRequired() throws Exception {
        //given
        //when
        boolean isValidFalse = ValidationHelper.validatePesel(null);

        //then
        assertFalse(isValidFalse);
    }

    @Test
    public void validateTelephonePattern() throws Exception {
        //given

        List<String> goodNumbers = Lists.newArrayList(
                "+48 999 990 099",
                "+48 999 990099",
                "+48 999990099",
                "+48999990099",
                "990 999 999",
                "999123 567",
                "123 123 321 ",
                "123987123"
        );
        List<String> badNumbers = Lists.newArrayList(
                "+999 990 099",
                "48 999 990 099",
                "99099099",
                "123 456 789 1"
        );

        checkEntireArray(goodNumbers, true);
        checkEntireArray(badNumbers, false);
    }

    private void checkEntireArray(List<String> list, boolean expected) {
        for (String s : list) {
            //when
            boolean isValidTrue = ValidationHelper.validateTelephonNumber(s);

            //then
            assertEquals(expected, isValidTrue);
        }
    }
}