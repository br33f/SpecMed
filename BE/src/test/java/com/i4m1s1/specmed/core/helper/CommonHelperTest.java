package com.i4m1s1.specmed.core.helper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class CommonHelperTest {

    @Test
    public void shouldReturnFalseWhenNull() throws Exception {
        //given
        Object thisIsNull = null;
        Object thisIsNotNull = new Object();

        //when
        boolean resultFalse = CommonHelper.checkNotNull(thisIsNull);
        boolean resultTrue = CommonHelper.checkNotNull(thisIsNotNull);

        //then
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}