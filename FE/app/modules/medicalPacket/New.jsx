import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        name: "",
        price: "",
        description: ""
    },
    saveUrl: 'medicalPacket/save'
});

export class MedicalPacketNew extends FormComponent {
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
                <p className="contentTitle">Edycja/Dodawanie nowego pakietu medycznego</p>
                <Row>
                    <Col md={6}>
                        <Form>
                            <FormGroup>
                                <Label for="medicalPacket">Pakiet Medyczny</Label>
                                <Input type="text" name="medicalPacket" id="medicalPacket" placeholder="Nazwa pakieu medycznego"
                                       value={this.state.model.get('medicalPacket')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="price">Cena</Label>
                                <Input type="text" name="price" id="price"
                                       placeholder="Cena" value={this.state.model.get('price')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="description">Opis pakietu</Label>
                                <Input type="text" name="description" id="description"
                                       placeholder="Cena" value={this.state.model.get('description')}
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