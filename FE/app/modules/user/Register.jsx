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
        email: "",
        password: "",
        confirmPassword: ""
    },
    saveUrl: '/auth/register'
});


/**
 * Klasa odpowiedzialna za rejestrację
 * @extends FormComponent
 */
export class Register extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            model: this.model,
            errorMsg: null,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "email": [
                {validator: "required"},
                {validator: "email"}
            ],
            "password": [
                {validator: "required"},
                {
                    validator: "minLength",
                    params: {
                        length: 6
                    }
                }
            ],
            "confirmPassword": [
                {validator: "required"},
                {
                    validator: "minLength",
                    params: {
                        length: 6
                    }
                },
                {
                    validator: val => {
                        if (val !== this.model.get('password')) {
                            return "Hasła muszą być identyczne";
                        }
                    }
                }
            ]
        };
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
                    Rejestracja
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6} lg={4} xl={3}>
                        <p className="clearfix">
                            <i className="pull-left fa fa-user-plus fa-5x text-muted" aria-hidden="true"></i>
                            Zarejestruj się i zyskaj dostęp do wszystkich oferowanych usług medycznych!
                        </p>
                        <p>W naszej ofercie znajdują się między innymi:</p>
                        <ul>
                            <li>Tutaj</li>
                            <li>Niech</li>
                            <li>Ktoś (kto się zna)</li>
                            <li>Coś (wpisze sensownego)</li>
                        </ul>
                    </Col>
                    <Col md={6} lg={4}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie dokonano rejestracji.
                        </div>
                        <div className="alert alert-danger" hidden={!this.state.errorMsg} role="alert">
                            {this.state.errorMsg}
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="email">Adres e-mail</Label>
                                <BindedInput form={this} type="email" name="email" id="email"
                                             placeholder="Adres e-mail"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Hasło</Label>
                                <BindedInput form={this} type="password" name="password" id="password"
                                             placeholder="Hasło"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="confirmPassword">Powtórz hasło</Label>
                                <BindedInput form={this} type="password" name="confirmPassword" id="confirmPassword"
                                             placeholder="Powtórz hasło"/>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zarejestruj się</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}