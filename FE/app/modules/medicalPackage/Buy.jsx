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
            package:""
        }
    },
    saveUrl: 'visit/save'
});

/**
 * Klasa odpowiedzialna za wysiwetlenie formualarza zakupu pakietu medycznego
 * @augments FormComponent
 */
export class MedicalPacakgeBuy extends FormComponent {
    /**
     * Kontruktor formularza zakupu pakietu medycznego
     * @param props parametry przekazywane do kontruktora
     */
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            specDictionary: [],
            model: this.model
        };
    }

    componentDidMount() {
        this.fetchAllEmployers();
    }

    /**
     * Funckja odpowiedzialna za przypisanie listy wszsytkich pakietów medycznych
     */
    fetchAllEmployers() {
        axios.get("/medical-employee/list/full").then(fullList => {
            this.setState({specDictionary:fullList.data.content()});
        });
    }

    /**
     * Funkcja odpowiedzialna wykoanania akcji zakupu pakietu medycznego
     */
    onFormSave() {
        console.log("Wohooo!");
        this.model.save();
    }

    /**
     * Funckja odpowiedzialna za czyszczenie  formularza zakupu pakietu medycznego
     */
    onFormClear() {
        console.log("clearVisit!");
        this.model.clear();
    }

    /**
     * Funkcja odpowiedzialana za wyświetlenie formularza zakupu pakietu medycznego
     * @returns {XML} Sformatowane dane do wyświetlenie jako formularz zakupu pakietu medycznego
     */
    render() {
        console.log("render");
        return (
            <Container fluid={true}>
                <p className="contentTitle">Zakup pakietu medycznego</p>
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
                                <Label for="specialization">Pakiet medyczny</Label>
                                <Input type="select" name="" id="specialization" value={this.state.model.get('personalData.gender')}
                                       onChange={this.bindValueToModel}>

                                        <option>
                                            Pakiet podstawowy
                                        </option>
                                        <option>
                                            Pakiet rozszerzony
                                        </option>
                                        <option>
                                            Pakiet gold
                                        </option>
                                        <option>
                                            Pakiet premium gold
                                        </option>
                                </Input>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Kup pakiet</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}