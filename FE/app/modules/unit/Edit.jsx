import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

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

export class UnitEdit extends FormComponent {
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

    componentDidMount() {
        this.unitId && this.model.fetch();
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

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

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
                                <Input type="text" name="name" id="unitName" placeholder="nazwa placówki"
                                       value={this.state.model.get('unitData.name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitAddress">Adres placówki</Label>
                                <Input type="text" name="address" id="unitAddress"
                                       placeholder="adres placówki" value={this.state.model.get('unitData.address')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTown">Miasto</Label>
                                <Input type="text" name="town" id="unitTown" placeholder="miasto"
                                       value={this.state.model.get('unitData.town')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitPostalcode">Kod pocztowy</Label>
                                <Input type="text" name="postalcode" id="unitPostalcode" placeholder="kod pocztowy"
                                       value={this.state.model.get('unitData.postalcode')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitEmail">Adres e-mail</Label>
                                <Input type="text" name="email" id="unitEmail" placeholder="adres e-mail"
                                       value={this.state.model.get('unitData.email')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="unitTelephonenumber">Numer telefonu</Label>
                                <Input type="number" name="telephonenumber" id="unitTelephonenumber" placeholder="numer telefonu"
                                       value={this.state.model.get('unitData.telephonenumber')}
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