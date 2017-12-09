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
        personalData: {
            name: "",
            surname: "",
            pesel: "",
            birthday: Date.now(),
            gender: "1"
        }
    },
    saveUrl: 'customer/save'
});

export class CustomerEdit extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano employeeId w url
        this.employeeId = props.match.params.employeeId;
        localModel.fetchUrl = "/customer/get/" + this.employeeId;

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
                    validator: "required"
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
                    validator: "required"
                },
                {
                    validator: "maxLength",
                    params: {
                        length: 10
                    }
                }
            ],
            "addressData.cityName": [
                {
                    validator: "required"
                },

            ],
            "addressData.street": [
                {
                    validator: "required"
                },

            ],
            "addressData.houseNumber": [
                {
                    validator: "required"
                },

            ],
            "addressData.apartmentNumber": [
                {
                    validator: "required"
                },

            ],
            "addressData.postalCode": [
                {
                    validator: "required"
                },

            ]
        };
    }


    componentDidMount() {
        this.employeeId && this.model.fetch();
        this.fetchDictionaries();
    }

    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("GENDER").then(dict => {
            this.setState({
                genderDictionary: dict
            });
        });
    }

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

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Edycja pacjenta
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano pacjenta.
                        </div>
                        <Form>
                            <div className="pull-left">
                                <div className="h5">Dane osobowe:</div>
                                <FormGroup>
                                    <Label for="employeeName">Imię</Label>
                                    <BindedInput form={this} type="text" name="personalData.name" id="employeeName" placeholder="Imię" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Nazwisko</Label>
                                    <BindedInput form={this} type="text" name="personalData.surname" id="employeeSurname" placeholder="Nazwisko" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeePesel">Numer PESEL</Label>
                                    <BindedInput form={this} type="number" name="personalData.pesel" id="employeePesel" placeholder="PESEL" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeBirthday">Data urodzenia</Label>
                                    <BindedInput form={this} type="date" name="personalData.birthday" id="employeeBirthday" placeholder="Data urodzenia"
                                                 value={this.state.model.get('personalData.birthday') && SM.Utils.customFormat(this.state.model.get('personalData.birthday'), "yyyy-mm-dd")}
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeGender">Płeć</Label>
                                    <BindedInput form={this} type="select" name="personalData.gender" id="employeeGender" placeholder="Płeć">
                                        {this.state.genderDictionary.map(genderObj =>
                                            <option
                                                key={genderObj.id}
                                                value={genderObj.id}>
                                                {genderObj.label}
                                            </option>)}
                                    </BindedInput>
                                </FormGroup>
                            </div>
                            <div className="pull-right">
                                <div className="h5">Dane adresowe:</div>
                                <FormGroup>
                                    <Label for="cityname">Miasto</Label>
                                    <BindedInput form={this} type="number" name="addressData.cityName" id="cityname" placeholder="Miasto" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="street">Ulica</Label>
                                    <BindedInput form={this} type="number" name="addressData.street" id="street" placeholder="Ulica" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="housenumber">Numer domu</Label>
                                    <BindedInput form={this} type="number" name="addressData.houseNumber" id="housenumber" placeholder="Numer domu" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="apartmentNumber">Numer mieszkania</Label>
                                    <BindedInput form={this} type="number" name="addressData.apartmentNumber" id="apartmentNumber" placeholder="Numer mieszkania" />

                                </FormGroup>
                                <FormGroup>
                                    <Label for="postalCode">Kod pocztowy</Label>
                                    <BindedInput form={this} type="number" name="addressData.postalCode" id="postalCode" placeholder="__-___" />

                                </FormGroup>
                                <div>
                                    <hr/>
                                    <Button outline type="button" className="mr-1"
                                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                    <Button outline color="primary" type="button"
                                            onClick={this.onFormSave.bind(this)}>Aktualizuj dane pacjenta</Button>
                                </div>
                            </div>

                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }

    getSampleGender() {
        return ['Kobieta', 'Mezczyzna'];
    }
}