import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {BindedDateTimePicker} from 'components/controls/BindedDateTimePicker.jsx';
import {Form, FormGroup, Label} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const modelDefaults = {
    pesel: "",
    name: "",
    surname: "",
    gender: "",
    birthday: Date.now()
};

export class PersonalEdit extends FormComponent {
    constructor(props) {
        let personalModel = props.model;
        super(props, personalModel);

        personalModel.setDefaults(modelDefaults);
        personalModel.set(modelDefaults);
        // if (props.customerId) {
        //     personalModel.fetchUrl = `/customer/get/${props.customerId}/personal`;
        // }

        this.state = {
            genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "pesel": [
                {validator: "required"},
                {validator: "number"},
                {
                    validator: "exactLength",
                    params: {
                        length: 11
                    }
                }
            ],
            "name": {validator: "required"},
            "surname": {validator: "required"},
            "gender": {validator: "required"},
            "birthday": {validator: "required"}
        };
    }


    componentDidMount() {
        this.handleFetch(this.props);
        this.fetchDictionaries();
    }

    componentWillReceiveProps(newProps) {
        this.handleFetch(newProps);
    }

    handleFetch(props) {
        if (props.customerId) {
            this.model.fetchUrl = `/customer/get/${props.customerId}/personal`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.employeeId) {
            this.model.fetchUrl = `/employee/get/${props.employeeId}/personal`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
    }

    /**
     * Metoda wywoływana po inicjalizacji obiektu. odpowiedzialna ze pobranie płci ze słownika
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("GENDER").then(dict => {
            this.setState({
                genderDictionary: dict
            });
        });
    }


    render() {
        return (
            <Form>
                <h5>Dane personalne</h5>
                <FormGroup>
                    <Label for="pesel">Numer PESEL</Label>
                    <BindedInput form={this} type="text" name="pesel" id="pesel"
                                 placeholder="Numer PESEL"/>
                </FormGroup>
                <FormGroup>
                    <Label for="name">Imię</Label>
                    <BindedInput form={this} type="text" name="name" id="name"
                                 placeholder="Imię"/>
                </FormGroup>
                <FormGroup>
                    <Label for="surname">Nazwisko</Label>
                    <BindedInput form={this} type="text" name="surname" id="surname"
                                 placeholder="Nazwisko"/>
                </FormGroup>
                <FormGroup>
                    <Label for="gender">Płeć</Label>
                    <BindedInput form={this} type="select" name="gender" id="gender">
                        <option
                            key="PLACEHOLDER_VAR"
                            value="">
                            Wybierz płeć
                        </option>)
                        {this.state.genderDictionary.map(genderObj =>
                            <option
                                key={genderObj.id}
                                value={genderObj.id}>
                                {genderObj.label}
                            </option>)}
                    </BindedInput>
                </FormGroup>
                <FormGroup>
                    <Label for="birthday">Data urodzin</Label>
                    <BindedDateTimePicker form={this} id="birthday" name="birthday" time={false}
                                          placeholder="Wybierz datę urodzin"/>
                </FormGroup>
            </Form>
        );
    }
}