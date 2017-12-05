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
        name: "",
        surname: "",
        pesel: "",
        visitData: Date.now(),
        visitHour: Date.now()
    },
    saveUrl: 'visit/save'
});

export class VisitEdit extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano visitId w url
        this.visitId = props.match.params.visitId;

        this.state = {
            specDictionary: [],
            model: this.model
        };
    }

    componentDidMount() {
        this.visitId && this.fetchData();
        this.fetchAllEmployers();
    }

    fetchData() {
        this.model.set({
            name: "Jan",
            surname: "Nowak",
            pesel: "64121201234"
        });
    }

    fetchAllEmployers() {
        axios.get("/medical-employee/list/full").then(fullList => {
            this.setState({specDictionary: fullList.data.content});
        });
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
                <p className="contentTitle">Rezerwacja wizyty</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="patientNameName">Imię</Label>
                                <Input type="text" name="name" id="patientName" placeholder="Imię"
                                       value={this.state.model.get('name')} onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="patientSurname">Nazwisko</Label>
                                <Input type="text" name="surname" id="patientSurname" placeholder="Nazwisko"
                                       value={this.state.model.get('surname')} onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="patientPesel">Numer PESEL</Label>
                                <Input type="number" name="pesel" id="patientPesel" placeholder="PESEL"
                                       value={this.state.model.get('pesel')} onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="specialization">Lekarz</Label>
                                <Input type="select" name="" id="specialization"
                                       value={this.state.model.get('personalData.gender')}
                                       onChange={this.bindValueToModel}>
                                    {this.state.specDictionary.map(specObj =>
                                        <option
                                            key={specObj.id}
                                            value={specObj.id}>
                                            {specObj.name + specObj.surname}
                                        </option>)}
                                </Input>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitData">Data wizyty</Label>
                                <Input type="date" name="visitData" id="visitData"
                                       placeholder="Data wizyty"
                                       value={this.state.model.get('visitData') && SM.Utils.customFormat(this.state.model.get('visitData'), "yyyy-mm-dd")} onChange={this.bindValueToModel}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitHour">Godzina wizyty</Label>
                                <Input type="time" name="visitHour" id="visitHour"
                                       placeholder="Godzina wizyty"
                                       value={this.state.model.get('visitHour') && SM.Utils.customFormat(this.state.model.get('visitHour'), "yyyy-mm-dd")} onChange={this.bindValueToModel}
                                />
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz wizytę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}