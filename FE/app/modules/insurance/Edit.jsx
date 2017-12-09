import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        name: "",
        price: 0.0,
        notes: "",
        insurancePolicyNumber: "",
        probeList: [],
        procedureList: []
    },
    saveUrl: 'insurance/save'
});

/**
 * Klasa odpowiedzialna za edycje widoku ubezpieczenia
 * @Component FormComponent
 */
export class InsuranceEdit extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano employeeId w url
        this.insuranceId = props.match.params.insuranceId;
        localModel.fetchUrl = "/insurance/get/" + this.insuranceId;

        this.state = {
            dictionaries: {
                PROBES: [],
                MEDICAL_PROCEDURES: []
            },
            model: this.model,
            isLoading: false,
            isSaved: false
        };
        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "name": [
                {
                    validator: "required",
                    msg: "Pole nazwa jest wymagane"
                },
                {
                    validator: (val) => {

                        if (!val || val.toString().length < 3) {
                            return "Nazwa musi mieć conajmniej 3 znaki";
                        }
                    }
                }
            ],
            "price": [
                {
                    validator: "required",
                    msg: "Pole cena jest wymagane"
                },
                {
                    validator: (val) => {

                        if (!val || val <= 0) {
                            return "Proszę wpisać poprawną wartość";
                        }
                    }
                }
            ],
            "notes": [
                {
                    validator: "required",
                    msg: "Pole opis jest wymagane"
                }
            ],
            "insurancePolicyNumber": [
                {
                    validator: "required",
                    msg: "Pole numer polisy jest wymagane"
                }
            ]
        };
    }

    /**
     * Funkcja odpowiedzialna za inicjalizacje danych przed utowrzeniem obiektu
     */
    componentDidMount() {
        this.insuranceId && this.model.fetch();
        this.fetchDictionaries();
    }

    /**
     * Metoda wywoływana po inicjalizacji obiektu. odpowiedzialna ze pobranie płci ze słownika
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictsAsArray("PROBES", "MEDICAL_PROCEDURES").then(dicts => {
            this.setState({
                dictionaries: dicts
            });
        });
    }

    /**
     * Funkcja odpowiedzialna ze wykonanie akcji zapisu elementow po edycji
     */
    onFormSave() {
        // metoda validate wywołuje walidację na polach określonych w this.rules
        this.validate();
        // this.errors zawiera błędy z walidacji
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
    /**
     * Funkcja odpowiedzialna obsługę kliknięcia przycisku czyszczenia formularza
     */
    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    /**
     * funckaj odpowiedzialna za generowanie widoku edycji ubezpieczenia
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.insuranceId ? 'Edycja' : 'Dodawanie nowego'} ubezpieczenia
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano ubezpieczenie.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="insuranceName">Nazwa</Label>
                                <Input type="text" name="name" id="insuranceName" placeholder="Nazwa"
                                       value={this.state.model.get('name')}
                                       className={this.isValid('name') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('name')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="insurancePrice">Cena ubezpieczenia</Label>
                                <Input type="number" name="price" id="insurancePrice"
                                       placeholder="Cena ubezpieczenia" value={this.state.model.get('price')}
                                       className={this.isValid('price') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('price')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="insuranceNotes">Opis ubezpieczenia</Label>
                                <Input type="textarea" name="notes" id="insuranceNotes" placeholder="Opis ubezpieczenia"
                                       value={this.state.model.get('notes')}
                                       className={this.isValid('notes') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('notes')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="insurancePolicyNumber">Numer polisy ubezpieczeniowej</Label>
                                <Input type="text" name="insurancePolicyNumber" id="insurancePolicyNumber"
                                       placeholder="Numer polisy ubezpieczeniowej"
                                       value={this.state.model.get('insurancePolicyNumber')}
                                       className={this.isValid('insurancePolicyNumber') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('insurancePolicyNumber')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="insuranceProbes">Badania zawarte w ubezpieczeniu</Label>
                                <Input type="select" multiple name="probeList" id="insuranceProbes"
                                       value={this.state.model.get('personalData.probeList')}
                                       onChange={this.bindValueToModel}>
                                    {this.state.dictionaries['PROBES'].map(probeObj =>
                                        <option
                                            key={probeObj.id}
                                            value={probeObj.id}>
                                            {probeObj.label}
                                        </option>)}
                                </Input>
                            </FormGroup>
                            <FormGroup>
                                <Label for="insuranceProcedures">Zabiegi medyczne zawarte w ubezpieczeniu</Label>
                                <Input type="select" multiple name="procedureList" id="insuranceProcedures"
                                       value={this.state.model.get('personalData.procedureList')}
                                       onChange={this.bindValueToModel}>
                                    {this.state.dictionaries['MEDICAL_PROCEDURES'].map(procedureObj =>
                                        <option
                                            key={procedureObj.id}
                                            value={procedureObj.id}>
                                            {procedureObj.label}
                                        </option>)}
                                </Input>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz ubezpieczenie</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}