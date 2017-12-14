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
        // w głownym modelu znajdują się oddzielne modele dla każdej sekcji
        // przekazywane są przez props do widoku child jako referencja, zatem zawsze mamy aktualne dane!
        addressData: new BaseModel(),
        personalData: new BaseModel(),
        contactData: new BaseModel()
    },
    saveUrl: '/customer/save'
});

export class CustomerEdit extends FormComponent {
    constructor(props) {
        let customerModel = new BaseModelConfigured();
        super(props, customerModel);

        this.getRoutingData(props);

        this.state = {
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    componentWillReceiveProps(newProps) {
        // metoda jest wywoływana tuż przed otrzymaniem parametów z routera
        // np. gdy zmieniamy ekran z /customer/edit na /customer/new
        // newProps zawiera nowe parametry
        this.getRoutingData(newProps);
    }

    getRoutingData(props) {
        this.customerId = props.match.params.customerId;
        this.model.set('id', this.customerId);
    }

    onFormSave() {
        this.validateChildren();

        if (!this.hasErrors()) {
            this.setState({
                isLoading: true
            });

            this.model.save().then(() => {
                this.setState({
                    isLoading: false,
                    isSaved: true
                });
                this.clearChildrenModels();
            });
        }

        this.forceUpdate();
    }

    validateChildren() {
        this.refs.address.validate();
        this.refs.personal.validate();
        this.refs.contact.validate();

        this.errors = Object.assign({}, this.refs.address.errors, this.refs.personal.errors, this.refs.contact.errors);
    }

    onFormClear() {
        this.clearChildrenModels()
        delete this.customerId;
    }

    clearChildrenModels() {
        this.model.get('addressData').clear();
        this.model.get('personalData').clear();
        this.model.get('contactData').clear();
    }

    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {this.customerId ? 'Edycja' : 'Dodawanie nowego'} pacjenta
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                    {this.customerId ? 'Pomyślnie zapisano pacjenta.' : 'Pomyślnie dodano pacjenta.'}
                </div>
                <Row>
                    <Col md={4} sm={6} xs={12}>
                        <AddressEdit ref="address" model={this.model.get('addressData')} customerId={this.customerId}/>
                    </Col>
                    <Col md={4} sm={6} xs={12}>
                        <PersonalEdit ref="personal" model={this.model.get('personalData')} customerId={this.customerId}/>
                    </Col>
                    <Col md={4} sm={6} xs={12}>
                        <ContactEdit ref="contact" model={this.model.get('contactData')} customerId={this.customerId}/>
                    </Col>
                </Row>
                <div>
                    <hr/>
                    <Button outline type="button" className="mr-1"
                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                    <Button outline color="primary" type="button"
                            onClick={this.onFormSave.bind(this)}>Zapisz pacjenta</Button>
                </div>
            </Container>
        );
    }
}