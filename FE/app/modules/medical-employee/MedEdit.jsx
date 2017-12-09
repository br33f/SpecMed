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
            birthday: "",
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
        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "personalData.name": [
                {
                    validator: "required", // tutaj możemy przekazać nazwę funkcji walidującej z pliku Validators.js lub własną funkcję
                    msg: "Pole imię jest wymagane" // pole opcjonalne
                },
                {
                    validator: (val) => {
                        // customowa funkcja walidująca
                        // jeżeli wystąpił błąd to zwracamy komunikat, jeżeli nie ma błędu to nie zwracamy nic
                        if (!val || val.toString().length < 3) {
                            return "Imię musi mieć conajmniej 3 znaki";
                        }
                    }
                }
            ],
            "personalData.surname": [
                {
                    validator: "required",
                    msg: "Pole nazwisko jest wymagane"
                },
                {
                    validator: "maxLength",
                    params: {
                        length: 20
                    }
                }
            ],
            "personalData.pesel": [
                {
                    validator: "required",
                    msg: "Pole PESEL jest wymagane"
                },
                {
                    validator: (val) => {

                        if (!val || val.toString().length != 11) {
                            return "Numer PESEL musi mieć 11 znaków";
                        }
                    }
                }
            ],
            "personalData.birthday": [
                {
                    validator: "required",
                    msg: "Pole data urodzenia jest wymagane"
                },
                {
                    validator: (val) => {
                        var today = new Date();
                        var dd = today.getDate();
                        var mm = today.getMonth()+1; //January is 0!
                        var yyyy = today.getFullYear();

                        if(dd<10) {
                            dd = '0'+dd
                        }

                        if(mm<10) {
                            mm = '0'+mm
                        }

                        today = yyyy + '-' + mm + '-' + dd;


                        if (!val || (val >= today)) {
                             return "Wprowadź poprawną datę urodzenia";
                        }

                    }
                }
            ]
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
     * Funckja odpowiedzialna za wyświetlanie formularza edycji pracownika medycznego
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.employeeId ? 'Edycja' : 'Dodawanie'} pracownika medycznego
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
                                       className={this.isValid('personalData.name') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('personalData.name')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeSurname">Nazwisko</Label>
                                <Input type="text" name="personalData.surname" id="employeeSurname"
                                       placeholder="Nazwisko"
                                       value={this.state.model.get('personalData.surname')}
                                       className={this.isValid('personalData.surname') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('personalData.surname')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeePesel">Numer PESEL</Label>
                                <Input type="number" name="personalData.pesel" id="employeePesel"
                                       placeholder="PESEL"
                                       value={this.state.model.get('personalData.pesel')}
                                       className={this.isValid('personalData.pesel') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('personalData.pesel')}
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeBirthday">Data urodzenia</Label>
                                <Input type="date" name="personalData.birthday" id="employeeBirthday"
                                       placeholder="Data urodzenia"
                                       value={this.state.model.get('personalData.birthday') && SM.Utils.customFormat(this.state.model.get('personalData.birthday'), "yyyy-mm-dd")}
                                       className={this.isValid('personalData.birthday') ? '' : 'is-invalid'}
                                       onChange={this.bindValueToModel}
                                />
                                {this.getValidationFeedbackFor('personalData.birthday')}
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
                                        onClick={this.onFormSave.bind(this)}>Zapisssss pracownika</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}