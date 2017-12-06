import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        personalData: {
            name: "",
            surname: "",
            pesel: "",
            birthday: Date.now(),
            gender: "1"
        }
    },
    saveUrl: 'prescription/save'
});

/**
 * Klasa odpowiedzialna za edycje recepty
 * @extends FormComponent
 */
export class    PrescriptionEdit extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {immutable object} props parametry przekazane do komponentu
     *
     */
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano employeeId w url
        this.employeeId = props.match.params.employeeId;
        localModel.fetchUrl = "/prescription/get/" + this.employeeId;

        this.state = {
            genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    /**
     * Metoda wywołuje się przy pierwszej inicjalizacji komponentu
     * @public
     */
    componentDidMount() {
        this.employeeId && this.model.fetch();
        this.fetchDictionaries();
    }

    /**
     * Metoda pobiera słowniki
     * @private
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("GENDER").then(dict => {
            this.setState({
                genderDictionary: dict
            });
        });
    }

    /**
     * Metoda wywołuje synchronizację modelu z usługą REST
     * @public
     */
    onFormSave() {
        this.setState({
            isLoading: true
        });
        this.model.save().then(() => {
            this.setState({
                isLoading: false,
                isSaved: true
            });
        });
    }

    /**
     * Metoda czyści model i wywołuje przerysowanie komponentu
     * @public
     */
    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji recepty
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.employeeId ? 'Edycja' : 'Dodawanie'} nowej recepty
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano pracownika.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="prescriptionNumber">Numer recepty</Label>
                                <Input type="number" name="prescriptionData.Number" id="prescriptionNumber" placeholder="Numer recepty"
                                       value={this.state.model.get('personalData.name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="prescriptionNfzUnit">Oddział NFZ</Label>
                                <Input type="text" name="prescriptionData.NfzUnit" id="prescriptionNfzUnit"
                                       placeholder="Oddzial NFZ" value={this.state.model.get('personalData.surname')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="prescriptionDrugList">Lista leków</Label>
                                <Input type="text" name="prescriptionData.drugList" id="prescriptionDrugList" placeholder="Lista leków"
                                       value={this.state.model.get('personalData.pesel')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup> 

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz receptę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}