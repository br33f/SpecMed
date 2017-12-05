import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        name: "",
        surname: "",
        address: "",
        age: ""
    },
    saveUrl: 'patient/save'
});

export class PatientNew extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);



        this.state = {
            model: this.model
        };
    }



    onFormSave() {
        console.log("Wohooo!");
        this.model.save();
    }

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">Edycja/Dodawanie nowego pacjenta</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="name">Imie</Label>
                                <Input type="text" name="name" id="name" placeholder="Imie"
                                       value={this.state.model.get('name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="surname">Nazwisko</Label>
                                <Input type="text" name="surname" id="surname"
                                       placeholder="Nazwisko" value={this.state.model.get('surname')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="address">Adres zamieszkania</Label>
                                <Input type="text" name="address" id="address"
                                       placeholder="Adres zamieszkania" value={this.state.model.get('address')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="description">Wiek</Label>
                                <Input type="text" name="age" id="age"
                                       placeholder="Wiek" value={this.state.model.get('age')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz pakiet medyczny</Button>
                            </div>

                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}