import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        userEmail: "",
        userPassword: "",
        pesel: "",
    },
    saveUrl: '/auth/bind-account'
});


/**
 * Klasa odpowiedzialna za rejestrację
 * @extends FormComponent
 */
export class BindAccount extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.getRoutingData(props);

        this.state = {
            model: this.model,
            errorMsg: null,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    getRoutingData(props) {
        this.customerId = props.match.params.customerId;
        this.model.set('customerId', this.customerId);
    }

    addValidators() {
        // this.rules = {
        //     "email": [
        //         {validator: "required"},
        //         {validator: "email"}
        //     ],
        //     "password": [
        //         {validator: "required"},
        //         {
        //             validator: "minLength",
        //             params: {
        //                 length: 6
        //             }
        //         }
        //     ],
        //     "pesel": [
        //         {validator: "required"},
        //         {
        //             validator: "minLength",
        //             params: {
        //                 length: 11
        //             }
        //         },
        //         {
        //             validator: "maxLength",
        //             params: {
        //                 length: 11
        //             }
        //         }
        //     ]
        // };
    }

    /**
     * Metoda wywołuje synchronizację modelu z usługą REST
     * @public
     */
    onFormSave() {
        this.validate();
        if (!this.hasErrors()) {
            this.setState({
                isLoading: true
            });
            this.model.save().then(res => {
                if (res.data.error) {
                    this.setState({
                        isLoading: false,
                        errorMsg: res.data.error
                    });
                } else {
                    this.setState({
                        isSaved: true,
                        isLoading: false,
                        errorMsg: null
                    });
                    this.model.clear();
                }
            });
        }

        this.forceUpdate();
    }

    /**
     * Metoda czyści model i wywołuje przerysowanie komponentu
     * @public
     */
    onFormClear() {
        this.model.clear();
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Połącz swoje konto z danymi
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                    Pomyślnie dokonano rejestracji.
                </div>
                <div className="alert alert-danger" hidden={!this.state.errorMsg} role="alert">
                    {this.state.errorMsg}
                </div>
                <Form>
                    <FormGroup>
                        <Label for="userEmail">Adres e-mail</Label>
                        <BindedInput form={this} type="userEmail" name="userEmail" id="userEmail"
                                     placeholder="Adres e-mail" value={this.state.model.get("userEmail")}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="userPassword">Hasło</Label>
                        <BindedInput form={this} type="password" name="userPassword" id="userPassword"
                                     placeholder="Hasło" value={this.state.model.get("userPassword")}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="pesel">Pesel</Label>
                        <BindedInput form={this} type="text" name="pesel" id="pesel"
                                     placeholder="pesel" value={this.state.model.get("pesel")}/>
                    </FormGroup>
                    <div className="pull-right">
                        <Button outline type="button" className="mr-1"
                                onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                        <Button outline color="primary" type="button"
                                onClick={this.onFormSave.bind(this)}>Połącz konta</Button>
                    </div>
                </Form>
            </Container>
        );
    }
}