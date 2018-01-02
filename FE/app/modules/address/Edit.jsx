import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {Form, FormGroup, Label} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const modelDefaults = {
    cityName: "",
    streetName: "",
    houseNumber: "",
    apartmentNumber: "",
    postalCode: ""
};

export class AddressEdit extends FormComponent {
    constructor(props) {
        let addressModel = props.model;
        super(props, addressModel);

        addressModel.setDefaults(modelDefaults);
        addressModel.set(modelDefaults);

        this.state = {
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.isFetched = false;

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "postalCode": [{validator: "required"}, {validator: "zipCode"}],
            "streetName": {validator: "required"},
            "houseNumber": {validator: "required"},
            "apartmentNumber": {validator: "number"},
            "cityName": {validator: "required"}
        };
    }

    componentDidMount() {
        this.handleFetch(this.props);
    }

    componentWillReceiveProps(newProps) {
        this.handleFetch(newProps);
    }

    handleFetch(props) {
        if (this.isFetched) return;

        if (props.customerId) {
            this.model.fetchUrl = `/customer/get/${props.customerId}/address`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.employeeId) {
            this.model.fetchUrl = `/employee/get/${props.employeeId}/address`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.medicalEmployeeId) {
            this.model.fetchUrl = `/medical-employee/get/${props.medicalEmployeeId}/address`;
            this.model.fetch();
        } else {
            this.model.clear();
        }

        this.isFetched = true;
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