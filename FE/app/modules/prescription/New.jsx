import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        name: "",
        employeeId: ""
    },
    saveUrl: 'prescription/save'
});

/**
 * Klasa odpowiedzialna za nową recepty
 * @extends FormComponent
 */
export class PrescriptionNew extends FormComponent {
    /**
     * Konstruktor
     * @constructor
     * @param {immutable object} props parametry przekazane do komponentu
     *
     */
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            model: this.model
        };
    }

    /**
     * Metoda wywołuje synchronizację modelu z usługą REST
     * @public
     */
    onFormSave() {
        console.log("Wohooo!");
        this.model.save();
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
     * Metoda odpowiedzialna za wyświetlanie widoku nowej recepty
     * @returns {XML}
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">Edycja/Dodawanie nowego pracownika</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="name">Lek</Label>
                                <Input type="text" name="name" id="name" placeholder="Lek"
                                       value={this.state.model.get('name')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="employeeId">ID Pracownika</Label>
                                <Input type="text" name="employeeId" id="employeeId"
                                       placeholder="Nazwisko" value={this.state.model.get('employeeId')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz receptę</Button>
                            </div>

                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}