import validator from 'validator';

export default {
    /* Tutaj możecie dodawać swoje validatory, jeżeli nie chcecie zaśmiecać sobie widoków JSX */
    /* Do funkcji przekazywana jest wartość pola */
    /* Z funkcji zwracacie komunikat, jeżeli wystąpił bład, jeżeli nie wystąpił to nie zwracanie nic */
    /* Możecie posiłkować się funkcjami z obiektu validator - opis dostępnych funkcji dostępny pod adresem: https://www.npmjs.com/package/validator */

    required: function(val) {
        if (!val || !val.toString().trim().length) {
            return 'To pole jest wymagane';
        }
    },

    email: function (val) {
        if (!val || !validator.isEmail(val)) {
            return `Podany adres: ${val} nie jest prawidłowym adresem e-mail`;
        }
    },

    maxLength: function (val, params) {
        let paramLength = 0;
        if (params && params.length) {
            paramLength = params.length;
        }
        if (!val || val.toString().length > paramLength) {
            return `Maksymalna długość tego pola to ${paramLength}`;
        }
    },

    minLength: function (val, params) {
        let paramLength = 0;
        if (params && params.length) {
            paramLength = params.length;
        }
        if (!val || val.toString().length < paramLength) {
            return `Minimalna długość tego pola to ${paramLength}`;
        }
    }
};