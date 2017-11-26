package com.i4m1s1.specmed.core.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
//@Ignore
@RunWith(MockitoJUnitRunner.class)
public class DateHelperTest {


    @Test
    public void shouldGetCurrentDateReturnNotNull() throws Exception {
        //given

        //when
        Date result = DateHelper.getCurrentDate();

        //then
        assertNotNull(result);
    }

    @Test
    public void shouldGetDateFromStringParseDate() throws Exception {
        //given
        String format = "yyyyMMdd";
        String dataAsString = "20171224"; //wigilia :)
        Date expected = new SimpleDateFormat(format).parse(dataAsString);

        //when
        Date result = DateHelper.getDateFromString(dataAsString, format);

        //then
        assertEquals(expected, result);
    }
}