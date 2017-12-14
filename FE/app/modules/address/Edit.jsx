import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        cityName: "",
        streetName: "",
        houseNumber: "",
        apartmentNumber: "",
        postalCode: ""
    }
});
export class AddressEdit extends FormComponent {
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
                <h5>Dane adresowe</h5>
                <FormGroup>
                    <Label for="postalCode">Kod pocztowy</Label>
                    <BindedInput form={this} type="text" name="postalCode" id="postalCode"
                                 placeholder="Kod pocztowy"/>
                </FormGroup>
                <FormGroup>
                    <Label for="cityName">Miejscowość</Label>
                    <BindedInput form={this} type="text" name="cityName" id="cityName"
                                 placeholder="Miejscowość"/>
                </FormGroup>
                <FormGroup>
                    <Label for="streetName">Ulica</Label>
                    <BindedInput form={this} type="text" name="streetName" id="streetName"
                                 placeholder="Ulica"/>
                </FormGroup>
                <FormGroup>
                    <Label for="houseNumber">Numer domu</Label>
                    <BindedInput form={this} type="text" name="houseNumber" id="houseNumber"
                                 placeholder="Numer domu"/>
                </FormGroup>
                <FormGroup>
                    <Label for="apartmentNumber">Numer mieszkania</Label>
                    <BindedInput form={this} type="text" name="apartmentNumber" id="apartmentNumber"
                                 placeholder="Numer mieszkania"/>
                </FormGroup>
            </Form>
        );
    }
}