import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        telephoneNumber: "",
        email: ""
    }
});
export class ContactEdit extends FormComponent {
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    onFormClear() {
        this.model.clear();
    }

    render() {
        return (
            <Form>
                <h5>Dane kontaktowe</h5>
                <FormGroup>
                    <Label for="telephoneNumber">Numer telefonu</Label>
                    <BindedInput form={this} type="number" name="telephoneNumber" id="telephoneNumber"
                                 placeholder="Numer telefonu"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">E-mail</Label>
                    <BindedInput form={this} type="email" name="email" id="email"
                                 placeholder="E-mail"/>
                </FormGroup>
            </Form>
        );
    }
}