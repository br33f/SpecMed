import React from 'react';
import {Component} from 'react';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {BindedDateTimePicker} from 'components/controls/BindedDateTimePicker.jsx';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        pesel: "",
        name: "",
        surname: "",
        gender: "",
        birthday: Date.now()
    }
});

export class PersonalEdit extends FormComponent {
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    componentDidMount() {
        this.fetchDictionaries();
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

    onFormClear() {
        this.model.clear();
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
                    <BindedDateTimePicker form={this} id="birthday" name="birthday"
                                          placeholder="Wybierz datę urodzin"/>
                </FormGroup>
            </Form>
        );
    }
}