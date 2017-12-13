import React from 'react';
import {Component} from 'react';
import BaseModel from 'components/models/BaseModel';
import validators from 'components/helpers/Validators';
import _ from 'lodash';

/**
 * Klasa bazowa dla komponentów formularza
 * @extends Component
 */
export class FormComponent extends Component {

    /**
     * Konstruktor powołujący FormComponent
     * @constructor
     * @param model instancja obiektu BaseModel
     */
    constructor(props, model) {
        super(props);

        if (!(model instanceof BaseModel)) throw "model must be an instance of BaseModel";
        this.model = model;
        this.model.onChange = () => {
            this.forceUpdate()
        };

        this.rules = {};
        this.errors = {};

        this.bindValueToModel = this._bindValueToModel.bind(this);
        this.getValidationFeedbackFor = this._getValidationFeedbackFor.bind(this);
        this.getDatepickerValidationFeedbackFor = this._getDatepickerValidationFeedbackFor.bind(this);
        this.isValid = this._isValid.bind(this);
    }

    hasErrors() {
        return !_.isEmpty(this.errors);
    }

    validate() {
        this.errors = {};

        Object.entries(this.rules).forEach(ruleEntry => {
            let fieldName = ruleEntry[0];
            let rule = ruleEntry[1];

            let rulesArray = [];
            if (!(rule instanceof Array)) {
                rulesArray.push(rule);
            } else {
                rulesArray = rule;
            }

            let validationResult = null;
            rulesArray.forEach(ruleObj => {
                if (validationResult === null) {
                    // if no error yet
                    validationResult = this._validateRule(fieldName, ruleObj);
                    if (validationResult) {
                        this.errors[fieldName] = validationResult;
                    }
                }
            });
        });
    }

    _validateRule(fieldName, ruleObj) {
        let validationFunction = null;
        if (typeof ruleObj.validator === "function") {
            validationFunction = ruleObj.validator;
        } else if (typeof validators[ruleObj.validator] === "function") {
            validationFunction = validators[ruleObj.validator];
        }

        if (validationFunction) {
            let validationResult = validationFunction(this.model.get(fieldName), ruleObj.params);
            return validationResult ? (ruleObj.msg || validationResult) : null;
        } else {
            return null;
        }
    }

    _getValidationFeedbackFor(fieldName) {
        if (this.errors[fieldName]) {
            return (
                <div className="invalid-feedback">
                    {this.errors[fieldName]}
                </div>
            );
        }
    }

    _getDatepickerValidationFeedbackFor(fieldName) {
        if (this.errors[fieldName]) {
            return (
                <div className="invalid-feedback d-block">
                    {this.errors[fieldName]}
                </div>
            );
        }
    }


    _isValid(fieldName) {
        return !this.errors[fieldName];
    }

    /**
     * Metoda wiążąca pola DOM z modelem
     * @param event obslugiwany event
     */
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

        console.log(name,value);
        this.model.set(name, value);
    }

}