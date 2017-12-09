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
        description: ""
    },
    saveUrl: 'insurance/save'
});

export class CommunicationWithDoctor extends FormComponent {

    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // rozdział na edycje i dodawanie jest bo 16 nie damy rady zrobic
        // albo cos

        this.state = {
            specDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "description": [
                {
                    validator: "minLength",
                    params: {
                        length: 5
                    }
                }
            ],
        };
    }

    getSample() {
        return ['Adam Abacki', 'Marcin Babacki'];
    }

    componentDidMount() {
        this.employeeId && this.model.fetch();
        this.fetchDictionaries();
    }

    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("medicalEmployee").then(dict => {
            this.setState({
                genderDictionary: dict
            });
        });
    }

    fetchAllEmployers() {
        axios.get("/medical-employee/list/full").then(fullList => {
            this.setState({specDictionary: fullList.data.content});
        });
    }

    onFormSave() {
        this.validate();
        console.log(this.errors);
        if (!this.hasErrors()) {
            // jeśli nie zawiera błędów - wysyłamy formularz
            this.setState({
                isLoading: true
            });
            this.model.save().then(() => {
                this.setState({
                    isLoading: false,
                    isSaved: true
                });
            });
        } else {
            // jeżeli są błędy - wymuszamy update formularza, aby pokazać komunikaty o błędach
            this.forceUpdate();
        }
    }

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {'Komunikacja z lekarzem'}
                    <Loader isEnabled={this.state.isLoading}/>
                </p>

                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie wysłano wiadomość.
                        </div>
                        <FormGroup>
                            <Label for="employeeGender">Lekarz</Label>
                            <BindedInput form={this} type="select" name="personalData.gender" id="employeeGender"
                                         placeholder="Lekarz">
                                <option>Jan Kowalski</option>
                                <option>Janusz Tracz</option>
                                <option>Janusz Nowak</option>
                            </BindedInput>
                        </FormGroup>
                        <Form>
                            <FormGroup>
                                <Label for="description">Napisz to nas:</Label>
                                <br/>
                                <BindedInput form={this} type="textarea" name="description" id="description"
                                             placeholder="Wiadmomość...">

                                </BindedInput>
                            </FormGroup>
                            {/*dobry hardcode*/}
                            <div className="pull-left">
                                <hr/>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Wyślij wiadomość</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
        console.log("render");
    }
}
