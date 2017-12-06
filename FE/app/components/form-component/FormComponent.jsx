import React from 'react';
import {Component} from 'react';
import BaseModel from "components/models/BaseModel";


export class FormComponent extends Component {
    constructor(props, model) {
        super(props);

        if (!(model instanceof BaseModel)) throw "model must be an instance of BaseModel";
        this.model = model;
        this.model.onChange = () => {
            this.forceUpdate()
        };

        this.bindValueToModel = this._bindValueToModel.bind(this);
    }

    _bindValueToModel(event) {
        const target = event.target;

        let value = "";
        switch (target.type) {
            case 'checkbox':
                value = !!target.checked;
                break;
            case 'select-multiple':
                if (event.target.options && event.target.options.constructor === HTMLOptionsCollection) {
                    let selectedOptions = Array.prototype.filter.call(event.target.options, option => option.selected);
                    value = selectedOptions.map(option => option.value);
                }
                break;
            default:
                value = target.value;
                break;
        }
        const name = target.name;

        this.model.set(name, value);
    }

}