import axios from 'axios';

export default {
    /**
     * Metoda odpowiedzialna za dopieranie katalogu
     * @param dictionaryName nazwa katalogu
     * @returns {Promise.<TResult>} referencja do katalogu wskazanego w parametrze
     */
    getDict: function(dictionaryName) {
        return axios.post(`/common/dict`, {
            chunkData: dictionaryName
        }).then(dict => {
           return dict.data.content;
        });
    },

    /**
     * Funkcja odpowiedzialna za przedstawienie katalogu jako tablica elementów
     * @param dictionaryName nazwa katalogu ktory ma zostać wylistowany
     * @returns {Promise.<TResult>} elementy wskazanego katalogu
     */
    getDictAsArray: function (dictionaryName) {
      return this.getDict(dictionaryName).then(dict => {
          return Object.entries(dict).map(dictEntry => {
              return {id: dictEntry[0], label: dictEntry[1]};
          })
      });
    },

    /**
     * Metoda odpowiedzialna za dopieranie katalogu
     * @param dictionaryName nazwa katalogów
     * @returns {Promise.<TResult>} referencja do katalogu wskazanego w parametrze
     */
    getDicts: function (...dictionaryNames) {
        return this.getMultipleDicts(dictionaryNames, false);
    },

    getDictsAsArray: function (...dictionaryNames) {
        return this.getMultipleDicts(dictionaryNames, true);
    },

    /**
     * Metoda odpowiedzialna za konkatenacje kilku elementow katalogu
     * @param dictionaryNames nazwa katalogu wejsciowego
     * @param asArray parametr odpowiezdialny ze przekazanie talbicy elementow tablicy
     * @returns {Promise.<TResult>}
     */
    getMultipleDicts: function (dictionaryNames, asArray) {
        let getDictFunction = asArray ? this.getDictAsArray : this.getDict;

        let dictPromises = [];
        dictionaryNames.forEach(dictionaryName => {
            dictPromises.push(getDictFunction.call(this, dictionaryName));
        });

        return Promise.all(dictPromises).then(dicts => {
            let dictsMap = {};
            dicts.forEach((dict, idx) => {
                dictsMap[dictionaryNames[idx]] = dict;
            });
            return dictsMap;
        });
    }
}