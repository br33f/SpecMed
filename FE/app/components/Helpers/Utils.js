import dateFormat from 'dateformat';

export default {
    formatDate: function (timestamp) {
        let date = new Date(timestamp);

        return dateFormat(date, 'dd-mm-yyyy')
    },

    formatTime: function (timestamp) {
        let date = new Date(timestamp);

        return dateFormat(date, 'HH:MM:ss')
    },

    formatDateTime: function (timestamp) {
      return this.formatDate(timestamp) + " " + this.formatTime(timestamp);
    }
}