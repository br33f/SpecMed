import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import axios from 'axios';
import Utils from 'components/helpers/Utils';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        email: "",
        password: ""
    }
});

/**
 * Klasa odpowiedzialna za logowanie
 * @extends FormComponent
 */
export class Login extends FormComponent {
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
            isLoading: false,
            errorMsg: null
        };
    }

    /**
     * Metoda wywołuje synchronizację modelu z usługą REST
     * @public
     */
    login() {
        this.setState({
            isLoading: true
        });

        axios.post("/auth/login", {
            chunkData: {
                email: this.model.get('email'),
                password: this.model.get('password')
            }
        }).then(res => {
            if (res.data.error) {
                this.setState({
                    isLoading: false,
                    errorMsg: res.data.error
                });
            } else {
                localStorage.setItem("authToken", res.data.content);
                Utils.setAuthorizationHeader();
                this.props.history.push('/');
            }
        });
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    <i className="fa fa-sign-in text-primary mr-1" aria-hidden="true"></i>
                    Logowanie
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
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
                            <div className="pull-right">
                                <Button outline color="primary" type="button"
                                        onClick={this.login.bind(this)}>Zaloguj się</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}