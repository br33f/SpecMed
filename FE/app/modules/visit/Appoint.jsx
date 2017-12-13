import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import {BindedDateTimePicker} from "components/controls/BindedDateTimePicker.jsx";
import axios from 'axios';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        visitId: "",
        specialization: "",
        medicalEmployeeId: ""
    }
});

/**
 * Klasa odpowiedzialna za umawianie się na wizytę
 * @extends FormComponent
 */
export class VisitAppoint extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            specializationDictionary: [],
            medicalEmployeeList: [],
            freeVisitsList: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            visitId: {validator: "required"},
            specialization: {validator: "required"},
            medicalEmployeeId: {validator: "required"}
        };
    }

    /**
     * Metoda wywołuje się przy pierwszej inicjalizacji komponentu
     * @public
     */
    componentDidMount() {
        this.fetchDictionaries();
    }

    /**
     * Metoda pobiera słowniki
     * @private
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("SPECIALIZATION").then(dict => {
            this.setState({
                specializationDictionary: dict
            });
        });
    }

    /**
     * Metoda pobiera pracowników medycznych
     * @private
     */
    fetchMedicalEmployees(specialization) {
        axios.post('/medical-employee/specialization/list', {
            chunkData: specialization
        }).then(response => {
            if (response.data.content) {
                this.setState({
                    medicalEmployeeList: response.data.content
                });
            }
        });
    }

    /**
     * Metoda pobiera wizyty
     * @private
     */
    fetchVisits(medicalEmployeeId) {
        axios.post('/visit/list/free', {
            chunkData: medicalEmployeeId
        }).then(response => {
            if (response.data.content) {
                this.setState({
                    freeVisitsList: response.data.content
                })
            }
        });
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
            this.sendAppointmentRequest().then(() => {
                this.setState({
                    isLoading: false,
                    isSaved: true
                });
            });
        } else {
            this.forceUpdate();
        }
        console.log(this.errors);
    }

    sendAppointmentRequest() {
        let SaveModel = BaseModel.extend({
            saveUrl: '/visit/appoint'
        });

        let saveModel = new SaveModel();
        saveModel.set('visitId', this.model.get('visitId'));
        saveModel.set('customerId', "5a2be56facd24749a0563f48"); // TODO: podstawić prawdziwego klienta

        return saveModel.save();
    }

    /**
     * Metoda czyści model i wywołuje przerysowanie komponentu
     * @public
     */
    onFormClear() {
        this.model.clear();
    }

    onSpecializationChange() {
        this.bindValueToModel(...arguments);
        this.fetchMedicalEmployees(this.model.get('specialization'));
    }

    onMedicalEmployeeChange() {
        this.bindValueToModel(...arguments);
        this.fetchVisits(this.model.get('medicalEmployeeId'));
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Umów się na wizytę
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie umowiono wizytę.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="specialization">Specjalizacja</Label>
                                <BindedInput form={this} type="select" name="specialization" id="specialization"
                                             onChange={this.onSpecializationChange.bind(this)}>
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz specjalizację z listy
                                    </option>
                                    {this.state.specializationDictionary.map(specObj =>
                                        <option
                                            key={specObj.id}
                                            value={specObj.id}>
                                            {specObj.label}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employee">Wybierz pracownika</Label>
                                <BindedInput form={this} type="select" name="medicalEmployeeId" id="employee"
                                             onChange={this.onMedicalEmployeeChange.bind(this)}>
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz pracownika z listy
                                    </option>
                                    {this.state.medicalEmployeeList.map(meObj =>
                                        <option
                                            key={meObj.id}
                                            value={meObj.id}>
                                            {meObj.name + ' ' + meObj.surname}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visit">Wybierz wizytę</Label>
                                <BindedInput form={this} type="select" name="visitId" id="visit">
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz wizytę z listy
                                    </option>
                                    {this.state.freeVisitsList.map(visitObj =>
                                        <option
                                            key={visitObj.id}
                                            value={visitObj.id}>
                                            {visitObj.place + " - Od " + SM.Utils.formatDateTime(visitObj.dateStart) + " Do " + SM.Utils.formatDateTime(visitObj.dateEnd) + " - Cena: " + visitObj.price + "zł"}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Umów wizytę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}