import dateFormat from 'dateformat';

export default {
    /**
     * Funkcja odpowiedzialan za farmatowanie daty do formatu dzien-miesiac-rok
     * @param timestamp czas to formatowania
     * @returns {*} data w forimie dzien-miesiac-rok
     */
    formatDate: function (timestamp) {
        let date = new Date(timestamp);

        return dateFormat(date, 'dd-mm-yyyy')
    },

    /**
     * Funckaj odpowiedzialna za formatowanie godziny do formatu godzina:minuta
     * @param timestamp czas wejsciowy przed formatowaniem
     * @returns {*} czas w formacie godzina:minuta
     */
    formatTime: function (timestamp) {
        let date = new Date(timestamp);

        return dateFormat(date, 'HH:MM:ss')
    },

    /**
     * Funckaj odpowiedzialana za łaczenia daty z godzian w formacie data godzina
     * @param timestamp wejsciowy czas do formatowania
     * @returns {string} data oraz czas przedstawiona w formacie data godzina
     */
    formatDateTime: function (timestamp) {
      return this.formatDate(timestamp) + " " + this.formatTime(timestamp);
    },

    /**
     * Funkcja odpowiedzialana za generowanie daty w odpowiednim formacie
     * @param timestamp wejsciowy czas do przeprocesowania
     * @param format wejsciwocy format daty np. HH:mm:ss
     * @returns {*} wyjsciowa data we wprowadzonym formacie na podstawie okreslonego formatu oraz czasu
     */
    customFormat: function (timestamp, format) {
        let date = new Date(timestamp);

        return dateFormat(date, format);
    }
}