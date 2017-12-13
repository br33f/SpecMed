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
        addressData: {
            cityName: "",
            postalCode: ""
        },
        contactData: {
            telephoneNumber: "",
            email: ""
        }
    },
    saveUrl: '/unit/save'
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
                                <Input type="text" name="name" id="unitName" placeholder="Nazwa placówki"
                                       value={this.state.model.get('name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitZip">Kod pocztowy</Label>
                                <Input type="text" name="addressData.postalCode" id="unitZip"
                                       placeholder="Kod pocztowy" value={this.state.model.get('addressData.postalCode')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTown">Miasto</Label>
                                <Input type="text" name="addressData.cityName" id="unitTown" placeholder="Miasto"
                                       value={this.state.model.get('addressData.cityName')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitEmail">Adres e-mail</Label>
                                <Input type="text" name="contactData.email" id="unitEmail" placeholder="Adres e-mail"
                                       value={this.state.model.get('contactData.email')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTelephonenumber">Numer telefonu</Label>
                                <Input type="number" name="contactData.telephoneNumber" id="unitTelephonenumber"
                                       placeholder="Numer telefonu"
                                       value={this.state.model.get('contactData.telephoneNumber')}
                                       onChange={this.bindValueToModel}/>
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