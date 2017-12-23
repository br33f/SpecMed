package com.i4m1s1.specmed.core.helper;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ValidationHelper {

    public static boolean validatePesel(String pesel) {
        boolean isValid = true;
        isValid = checkAtom(isValid, required(pesel));
        if(!isValid) return false;
        isValid = checkAtom(isValid, containsNumbersOnly(pesel));
        isValid = checkAtom(isValid, length(pesel, 11));
        return isValid;
    }


    public static boolean validateTelephonNumber(String tel) {
        Boolean isValid = true;
        isValid = checkAtom(isValid, required(tel));
        if(!isValid) return false;
        isValid = checkAtom(isValid, length(tel, 9, 16));
        isValid = checkAtom(isValid, telephonePattern(tel));
        return isValid;
    }




    /** w a l i d a c e  a t o m o w e **/

    private static boolean telephonePattern(String tel) {
        boolean matchesLong = tel.matches("\\+[0-9]{2}[\\h]?[0-9]{3}[\\h]?[0-9]{3}[\\h]?[0-9]{3}");
        boolean matchesShort = tel.matches("[0-9]{3}\\h?[0-9]{3}\\h?[0-9]{3}\\h?");
        return matchesLong || matchesShort;
    }
    private static boolean required(Object obj) {
        return obj != null;
    }

    private static boolean checkAtom(boolean isValid, boolean condition) {
        return isValid = isValid && condition;
    }

    private static boolean containsNumbersOnly(String in) {
        return in.matches("[0-9]+");
    }

    private static boolean length(String text, Integer min, Integer max) {
        return
                (min == null || (text.length() >= min))
                &&
                (max == null || (text.length() <= max));
    }

    private static boolean length(String text, Integer eq) {
        return text.length() == eq;
    }
}
