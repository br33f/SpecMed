import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

import {AddressEdit} from '../address/Edit.jsx';
import {PersonalEdit} from '../personal/Edit.jsx';
import {ContactEdit} from '../contact/Edit.jsx';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        addressData: {},
        personalData: {},
        contactData: {}
    },
    saveUrl: '/employee/save'
});

export class CustomerEdit extends FormComponent {
    constructor(props) {
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    onFormSave() {

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
                    Edycja pacjenta
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={4}>
                        <AddressEdit />
                    </Col>
                    <Col md={4}>
                        <PersonalEdit />
                    </Col>
                    <Col md={4}>
                        <ContactEdit />
                    </Col>
                </Row>
                <div>
                    <hr/>
                    <Button outline type="button" className="mr-1"
                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                    <Button outline color="primary" type="button"
                            onClick={this.onFormSave.bind(this)}>Aktualizuj dane pacjenta</Button>
                </div>
            </Container>
        );
    }

    getSampleGender() {
        return ['Kobieta', 'Mezczyzna'];
    }
}