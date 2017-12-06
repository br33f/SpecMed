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
    saveUrl: 'employee/save'
});

/**
 * Klasa odpowiedzialna za edycke proacownikow medycznych
 * @augments FormComponent
 */
export class MedicalEmployeeEdit extends FormComponent {
    /**
     * @constructor tworzenie edycji pracownika medycznego, ktory jest pozniej przekazywany do swojego rodzica
     * @param props parametry wejsciowe dla kontruktoera
     */
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano employeeId w url
        this.employeeId = props.match.params.employeeId;
        localModel.fetchUrl = "/employee/get/" + this.employeeId;

        this.state = {
            genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    /**
     * Funkcja odpowiedzialna za inicjalizacje danych przed utowrzeniem obiektu
     */
    componentDidMount() {
        this.employeeId && this.model.fetch();
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

    /**
     * Funkcja odpowiedzialna ze wykonanie akcji zapisu elementow po edycji
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
     * Funkcja odpowiedzialna obsługę kliknięcia przycisku czyszczenia formularza
     */
    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    /**
     * Funckja odpowiedzialna za wyświetlanie formularza edycji pracownika medycznego
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.employeeId ? 'Edycja' : 'Dodawanie'} nowego pracownika medycznego
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano pracownika.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="employeeName">Imię</Label>
                                <Input type="text" name="personalData.name" id="employeeName" placeholder="Imię"
                                       value={this.state.model.get('personalData.name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeSurname">Nazwisko</Label>
                                <Input type="text" name="personalData.surname" id="employeeSurname"
                                       placeholder="Nazwisko" value={this.state.model.get('personalData.surname')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeePesel">Numer PESEL</Label>
                                <Input type="number" name="personalData.pesel" id="employeePesel" placeholder="PESEL"
                                       value={this.state.model.get('personalData.pesel')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeBirthday">Data urodzenia</Label>
                                <Input type="date" name="personalData.birthday" id="employeeBirthday"
                                       placeholder="Data urodzenia"
                                       value={this.state.model.get('personalData.birthday') && SM.Utils.customFormat(this.state.model.get('personalData.birthday'), "yyyy-mm-dd")}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeGender">Płeć</Label>
                                <Input type="select" name="personalData.gender" id="employeeGender" value={this.state.model.get('personalData.gender')}
                                       onChange={this.bindValueToModel}>
                                    {this.state.genderDictionary.map(genderObj =>
                                        <option
                                            key={genderObj.id}
                                            value={genderObj.id}>
                                            {genderObj.label}
                                        </option>)}
                                </Input>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz pracownika</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}