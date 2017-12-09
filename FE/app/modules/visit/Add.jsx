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
        medicalEmployeeId: "",
        customer: null,
        price: 100.0,
        status: "1",
        place: "",
        dateStart: Date.now(),
        dateEnd: Date.now() + (1800 * 1000)
    },
    saveUrl: '/visit/save'
});

/**
 * Klasa odpowiedzialna za utworzenie nowej wizyty
 * @extends FormComponent
 */
export class VisitAdd extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            visitStatusDictionary: [],
            medicalEmployeeList: [],
            customerList: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "medicalEmployeeId": {validator: "required"},
            "place": [
                {validator: "required"},
                {
                    validator: "minLength",
                    params: {length: 6}
                }
            ],
            "dateStart": {
                validator: val => {
                    if (val > this.model.get('dateEnd')) {
                        return  "Data rozpoczęcia musi być wcześniejsza niż data zakończenia";
                    }
                }
            },
            "dateEnd": {
                validator: val => {
                    if (val < this.model.get('dateStart')) {
                        return  "Data zakończenia musi być późnejsza niż data rozpoczęcia";
                    }
                }
            },
        };
    }

    /**
     * Metoda wywołuje się przy pierwszej inicjalizacji komponentu
     * @public
     */
    componentDidMount() {
        this.fetchMedicalEmployees();
        this.fetchCustomers();
        this.fetchDictionaries();
    }

    /**
     * Metoda pobiera słowniki
     * @private
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("VISIT_STATUS").then(dict => {
            this.setState({
                visitStatusDictionary: dict
            });
        });
    }

    /**
     * Metoda pobiera pracowników medycznych
     * @private
     */
    fetchMedicalEmployees() {
        axios.get('/medical-employee/list/full').then(response => {
            this.setState({
                medicalEmployeeList: response.data.content
            });
        });
    }

    /**
     * Metoda pobiera pacjentów
     * @private
     */
    fetchCustomers() {
        axios.get('/customer/list/simple/full').then(response => {
            this.setState({
                customerList: response.data.content
            });
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

    shouldPickEmployee() {
        return this.model.get('status') && (this.model.get('status') === "2" || this.model.get('status') === "3");
    }

    onStatusChange() {
        this.bindValueToModel(...arguments);

        if (this.shouldPickEmployee()) {
            // Dodaje walidacje tylko jeżeli pole jest wyświetlane
            this.rules["customerId"] = {
                validator: "required"
            }
        } else {
            // W przeciwnym przypadku usuwam walidację
            delete this.rules["customerId"];
        }
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Dodawanie nowej wizyty
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano nową wizytę.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="employee">Wybierz pracownika</Label>
                                <BindedInput form={this} type="select" name="medicalEmployeeId" id="employee"
                                             placeholder="Pracownik">
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
                                <Label for="visitStatus">Status wizyty</Label>
                                <BindedInput form={this} type="select" name="status" id="visitStatus" onChange={this.onStatusChange.bind(this)}>
                                    {this.state.visitStatusDictionary.map(visitObj =>
                                        <option
                                            key={visitObj.id}
                                            value={visitObj.id}>
                                            {visitObj.label}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup hidden={!this.shouldPickEmployee()}>
                                <Label for="customer">Wybierz pacjenta</Label>
                                <BindedInput form={this} type="select" name="customerId" id="customer">
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz pacjenta z listy
                                    </option>
                                    {this.state.customerList.map(customerObj =>
                                        <option
                                            key={customerObj.id}
                                            value={customerObj.id}>
                                            {customerObj.name + ' ' + customerObj.surname}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup>
                                <Label for="price">Cena za wizytę</Label>
                                <BindedInput form={this} type="number" name="price" id="price" placeholder="Cena"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="place">Miejsce wizyty</Label>
                                <BindedInput form={this} type="text" name="place" id="place"
                                             placeholder="Miejsce wizyty"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="dateStart">Rozpoczęcie wizyty</Label>
                                <BindedDateTimePicker form={this} id="dateStart" name="dateStart"
                                                      placeholder="Wybierz datę i godzinę rozpoczęcia wizyty"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="dateEnd">Zakończenie wizyty</Label>
                                <BindedDateTimePicker form={this} id="dateEnd" name="dateEnd"
                                                      placeholder="Wybierz datę i godzinę zakończenia wizyty"/>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Dodaj wizytę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}