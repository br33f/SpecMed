import axios from 'axios';

export default {
    getDict: function(dictionaryName) {
        return axios.post(`/common/dict`, {
            chunkData: dictionaryName
        }).then(dict => {
           return dict.data.data;
        });
    },

    getDictAsArray: function (dictionaryName) {
      return this.getDict(dictionaryName).then(dict => {
          return Object.entries(dict).map(dictEntry => {
              return {id: dictEntry[0], label: dictEntry[1]};
          })
      });
    },

    getDicts: function (...dictionaryNames) {
        return this.getMultipleDicts(dictionaryNames, false);
    },

    getDictsAsArray: function (...dictionaryNames) {
        return this.getMultipleDicts(dictionaryNames, true);
    },

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