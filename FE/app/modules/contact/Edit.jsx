import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {Form, FormGroup, Label} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const modelDefaults = {
    telephoneNumber: "",
    email: ""
};

export class ContactEdit extends FormComponent {
    constructor(props) {
        let contactModel = props.model;
        super(props, contactModel);

        contactModel.setDefaults(modelDefaults);
        contactModel.set(modelDefaults);
        if (props.customerId) {
            contactModel.fetchUrl = `/customer/get/${props.customerId}/contact`;
        }
        if (props.employeeId) {
            contactModel.fetchUrl = `/employee/get/${props.employeeId}/contact`;
        }

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
            "telephoneNumber": [{validator: "required"}, {validator: "number"}],
            "email": [{validator: "required"}, {validator: "email"}]
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
            this.model.fetchUrl = `/customer/get/${props.customerId}/contact`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.employeeId) {
            this.model.fetchUrl = `/employee/get/${props.employeeId}/contact`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.medicalEmployeeId) {
            this.model.fetchUrl = `/medical-employee/get/${props.medicalEmployeeId}/contact`;
            this.model.fetch();
        } else {
            this.model.clear();
        }

        this.isFetched = true;
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