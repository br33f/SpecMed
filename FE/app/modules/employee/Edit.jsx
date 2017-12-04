import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        name: "Janusz",
        surname: "Tracz"
    }
});

export class EmployeeEdit extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            genderDictionary: [],
            model: this.model
        };
    }

    componentDidMount() {
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
        console.log("Wohooo!");
        this.model.fetch();
    }

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">Edycja/Dodawanie nowego pracownika</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="employeeName">Imię</Label>
                                <Input type="text" name="name" id="employeeName" placeholder="Imię" value={this.state.model.get('name')} onChange={this.bindValueToModel} />
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeSurname">Nazwisko</Label>
                                <Input type="text" name="surname" id="employeeSurname" placeholder="Nazwisko" value={this.state.model.get('surname')} onChange={this.bindValueToModel} />
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeePesel">Numer PESEL</Label>
                                <Input type="number" name="employeePesel" id="employeePesel" placeholder="PESEL"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeBirthday">Data urodzenia</Label>
                                <Input type="date" name="employeeBirthday" id="employeeBirthday"
                                       placeholder="Data urodzenia"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeGender">Płeć</Label>
                                <Input type="select" name="employeeGender" id="employeeGender" onChange={() => {}}>
                                    {this.state.genderDictionary.map(genderObj => <option key={genderObj.id} value={genderObj.id}>{genderObj.label}</option>)}
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