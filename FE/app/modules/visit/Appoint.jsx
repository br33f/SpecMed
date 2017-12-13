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
    },
    saveUrl: '/visit/appoint'
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
            this.setState({
                medicalEmployeeList: response.data.content
            });
        });
    }

    /**
     * Metoda pobiera wizyty
     * @private
     */
    fetchVisits() {
        this.setState({
            freeVisitsList: [
                {
                    id: 1,
                    price: "300",
                    place: "Gabinet zabiegowy",
                    dateStart: 1512839700000,
                    dateEnd: 1512840600000
                },
                {
                    id: 2,
                    price: "200",
                    place: "Gabinet nr 5",
                    dateStart: 1502839700000,
                    dateEnd: 1502840600000
                },
                {
                    id: 3,
                    price: "100",
                    place: "Gabinet dyrektora",
                    dateStart: 1412839700000,
                    dateEnd: 1412840600000
                },
            ]
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
            this.model.save().then(() => {
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
        this.fetchVisits();
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
                                <BindedInput form={this} type="select" name="specialization" id="specialization" onChange={this.onSpecializationChange.bind(this)}>
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
                                <BindedInput form={this} type="select" name="medicalEmployeeId" id="employee" onChange={this.onMedicalEmployeeChange.bind(this)}>
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
                                            {visitObj.place + " - Od " + SM.Utils.formatDateTime(visitObj.dateStart) + " Do " + SM.Utils.formatDateTime(visitObj.dateStart) + " - Cena: " + visitObj.price + "zł"}
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