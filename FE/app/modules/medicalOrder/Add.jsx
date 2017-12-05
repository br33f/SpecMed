//bwrzos 05.12.2017
import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import axios from 'axios';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        visitData: {
            name: "",
            surname: "",
            pesel: "",
            visitData: Date.now(),
            vistHour:"",
            orderType:""
        }
    },
    saveUrl: 'order/save'
});

export class OrderAdd extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            specDictionary: [],
            model: this.model
        };
    }

    onFormSave() {
        console.log("Wohooo!");
        this.model.save();
    }

    onFormClear() {
        console.log("clearVisit!");
        this.model.clear();
    }
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">Zlecenia badania</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="patientNameName">Imię</Label>
                                <Input type="text" name="name" id="patientName" placeholder="Imię" value={this.state.model.get('name')} onChange={this.bindValueToModel} />
                            </FormGroup>
                            <FormGroup>
                                <Label for="patientSurname">Nazwisko</Label>
                                <Input type="text" name="surname" id="patientSurname" placeholder="Nazwisko" value={this.state.model.get('surname')} onChange={this.bindValueToModel} />
                            </FormGroup>
                            <FormGroup>
                                <Label for="patientPesel">Numer PESEL</Label>
                                <Input type="number" name="patientPesel" id="patientPesel" placeholder="PESEL"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="medicalOrder">Typ badania</Label>
                                <Input type="select" name="" id="medicalOrder" value={this.state.model.get('personalData.gender')}
                                       onChange={this.bindValueToModel}>
                                    <option>Badanie krwi</option>
                                    <option>USG brzucha</option>
                                    <option>RTG reki</option>
                                </Input>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitData">Data badania</Label>
                                <Input type="date" name="medicalOrderData" id="medicalOrderData"
                                       placeholder="Data wizyty"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitHour">Godzina badania</Label>
                                <Input type="time" name="medicalOrderHour" id="medicalOrderHour"
                                       placeholder="Godzina wizyty"/>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zleć badanie</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}