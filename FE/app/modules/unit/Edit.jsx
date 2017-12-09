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
        unitData: {
            name: "Szpital im. Mikołaja Kopernika",
            address: "łódzka 7",
            town: "Warszawa",
            postalcode: "12-345",
            email: "m.kopernik@szpital.pl",
            telephonenumber: "123123123"
        }
    },
    saveUrl: 'unit/save'
});

/**
 * Klasa odpowiedzialna za edycje oddziału
 * @extends FormComponent
 */
export class UnitEdit extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano unitId w url
        this.unitId = props.match.params.unitId;
        localModel.fetchUrl = "/unit/get/" + this.unitId;

        this.state = {
            //genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();

    }



    addValidators() {
        this.rules = {
            "unitData.name": [
                {
                    validator: "required", // tutaj możemy przekazać nazwę funkcji walidującej z pliku Validators.js lub własną funkcję
                    msg: "Pole jest wymagane" // pole opcjonalne
                },
                {
                    validator: (val) => {
                        // customowa funkcja walidująca
                        // jeżeli wystąpił błąd to zwracamy komunikat, jeżeli nie ma błędu to nie zwracamy nic
                        if (!val || val.toString().length < 3) {
                            return "Nazwa placówki musi mieć więcej niż 3 znaki.";
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
            ]
        };
    }



    /**
     * Metoda wywołuje się przy pierwszej inicjalizacji komponentu
     * @public
     */
    componentDidMount() {
        this.unitId && this.model.fetch();
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
        this.validate();

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
     * Metoda czyści model i wywołuje przerysowanie komponentu
     * @public
     */
    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.unitId ? 'Edycja' : 'Dodawanie'} placówki
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano placówkę.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="unitName">Nazwa placówki</Label>
                                <BindedInput form={this} type="text" name="unitData.name" id="unitName" placeholder="Nazwa placówki" />

                            </FormGroup>
                            <FormGroup>
                                <Label for="unitAddress">Adres placówki</Label>
                                <BindedInput form={this} type="text" name="unitData.address" id="unitAddress" placeholder="Adres Placówki" />

                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTown">Miasto</Label>
                                <BindedInput form={this} type="text" name="unitData.town" id="unitTown" placeholder="Miasto" />

                            </FormGroup>
                            <FormGroup>
                                <Label for="unitPostalcode">Kod pocztowy</Label>
                                <BindedInput form={this} type="text" name="unitData.postalcode" id="unitPostalcode" placeholder="Kot pocztowy" />

                            </FormGroup>
                            <FormGroup>
                                <Label for="unitEmail">Adres e-mail</Label>
                                <BindedInput form={this} type="text" name="unitData.postalcode" id="unitEmail" placeholder="Adres e-mail" />

                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTelephonenumber">Numer telefonu</Label>
                                <BindedInput form={this} type="text" name="unitData.postalcode" id="unitEmail" placeholder="Adres e-mail" />

                            </FormGroup>

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz placówkę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}