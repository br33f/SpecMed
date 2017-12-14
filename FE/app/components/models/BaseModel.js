import _ from 'lodash';
import axios from 'axios';

/**
 * Definicja BaseModel
 * @constructor kontruktor BaseModel
 */
let BaseModel = function () {
    this.attributes = _.cloneDeep(this.defaults);

    this.initialize();
};

BaseModel.prototype = {
    defaults: {},
    attributes: {},
    fetchUrl: '',
    saveUrl: '',
    onChange: () => {}
};

BaseModel.prototype.initialize = function () {
};

BaseModel.prototype.get = function (key) {
    return key ? _.get(this.attributes, key) : this.attributes;
};

BaseModel.prototype.getRecursive = function (key) {
    let modelAttributes =  key ? _.cloneDeep(_.get(this.attributes, key)) : _.cloneDeep(this.attributes);

    Object.entries(modelAttributes).forEach(modelEntry => {
        let key = modelEntry[0];
        let val = modelEntry[1];
        if (val instanceof BaseModel) {
            modelAttributes[key] = val.getRecursive();
        }
    });

    return modelAttributes;
};

BaseModel.prototype.set = function () {
    if (!arguments) return;

    if (arguments[0] instanceof Object) {
        Object.entries(arguments[0]).forEach(entry => {
            _.set(this.attributes, entry[0], entry[1]);
        });
    } else if (arguments[0] instanceof String || typeof arguments[0] === 'string') {
        _.set(this.attributes, arguments[0], arguments[1]);
    } else {
        throw "Unknown set function prototype";
    }

    this.onChange(this);
};

BaseModel.prototype.setDefaults = function (defaults) {
    this.defaults = _.cloneDeep(defaults);
};

BaseModel.prototype.clear = function () {
    this.set(_.cloneDeep(this.defaults));
};

BaseModel.prototype.fetch = function (options) {
    if (!this.fetchUrl) throw "fetchUrl must be specified";

    return axios.get(this.fetchUrl, options).then(response => {
        this.set(response.data.content);
    });
};

BaseModel.prototype.save = function () {
    if (!this.saveUrl) throw "saveUrl must be specified";

    return axios.put(this.saveUrl, this.getSaveData());
};

BaseModel.prototype.getSaveData = function () {
  return {
      chunkData: this.getRecursive()
  };
};

/* -- EXPORT --*/

// Fantastyczna funkcja extend z backbone.js
let extend = function(protoProps, staticProps) {
    let parent = this;
    let child;

    // The constructor function for the new subclass is either defined by you
    // (the "constructor" property in your `extend` definition), or defaulted
    // by us to simply call the parent constructor.
    if (protoProps && _.has(protoProps, 'constructor')) {
        child = protoProps.constructor;
    } else {
        child = function(){ return parent.apply(this, arguments); };
    }

    // Add static properties to the constructor function, if supplied.
    _.extend(child, parent, staticProps);

    // Set the prototype chain to inherit from `parent`, without calling
    // `parent`'s constructor function and add the prototype properties.
    child.prototype = _.create(parent.prototype, protoProps);
    child.prototype.constructor = child;

    // Set a convenience property in case the parent's prototype is needed
    // later.
    child.__super__ = parent.prototype;

    return child;
};

export default (function() {
    BaseModel.extend = extend;
    return BaseModel;
})();

