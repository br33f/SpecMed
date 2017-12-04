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
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.model.set(name, value);
    }
}