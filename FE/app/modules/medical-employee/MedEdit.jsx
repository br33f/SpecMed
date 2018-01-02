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
import {BindedInput} from 'components/controls/BindedInput.jsx';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        // w głownym modelu znajdują się oddzielne modele dla każdej sekcji
        // przekazywane są przez props do widoku child jako referencja, zatem zawsze mamy aktualne dane!
        addressData: new BaseModel(),
        personalData: new BaseModel(),
        contactData: new BaseModel(),
        specializationList: ""
    },
    saveUrl: '/medical-employee/save'
});

export class MedicalEmployeeEdit extends FormComponent {
    constructor(props) {
        let customerModel = new BaseModelConfigured();
        super(props, customerModel);

        this.componentRefresh(props);

        this.state = {
            model: this.model,
            specializationDictionary: [],
            isLoading: false,
            isSaved: false
        };
    }

    componentWillReceiveProps(newProps) {
        // metoda jest wywoływana tuż przed otrzymaniem parametów z routera
        // np. gdy zmieniamy ekran z /customer/edit na /customer/new
        // newProps zawiera nowe parametry
        this.componentRefresh(newProps);
    }

    componentRefresh(props) {
        this.getRoutingData(props);
        this.fetchSpecialization();
        this.fetchDictionaries();
    }

    getRoutingData(props) {
        this.employeeId = props.match.params.employeeId;
        this.model.set('id', this.employeeId);
    }

    fetchSpecialization() {
        if (this.employeeId) {
            let specializationModel = new BaseModel();
            specializationModel.fetchUrl = `/medical-employee/get/${this.employeeId}/specialization`;
            specializationModel.fetch().then(res => {
                this.model.set('specializationList', res.data.content);
            });
        }
    }

    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("SPECIALIZATION").then(dict => {
            this.setState({
                specializationDictionary: dict
            });
        });
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
        this.clearChildrenModels();
        delete this.employeeId;
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
                    {this.employeeId ? 'Edycja' : 'Dodawanie nowego'} pracownika medycznego
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                    {this.employeeId ? 'Pomyślnie zapisano pracownika medycznego.' : 'Pomyślnie dodano pracownika medycznego.'}
                </div>
                <Row>
                    <Col md={4} sm={6} xs={12}>
                        <AddressEdit ref="address" model={this.model.get('addressData')} medicalEmployeeId={this.employeeId}/>
                    </Col>
                    <Col md={4} sm={6} xs={12}>
                        <PersonalEdit ref="personal" model={this.model.get('personalData')} medicalEmployeeId={this.employeeId}/>
                    </Col>
                    <Col md={4} sm={6} xs={12}>
                        <ContactEdit ref="contact" model={this.model.get('contactData')} medicalEmployeeId={this.employeeId}/>
                        <FormGroup>
                            <Label for="specialization">Specjalizacja</Label>
                            <BindedInput form={this} type="select" name="specializationList" id="specialization" multiple >
                                <option
                                    key="PLACEHOLDER_ITEM"
                                    value="">
                                    Wybierz specjalizację z listy
                                </option>
                                {this.state.specializationDictionary.map(specObj =>
                                    <option
                                        key={specObj.id}
                                        value={specObj.id}>
                                        {specObj.label}
                                    </option>)}
                            </BindedInput>
                        </FormGroup>
                    </Col>
                </Row>
                <div>
                    <hr/>
                    <Button outline type="button" className="mr-1"
                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                    <Button outline color="primary" type="button"
                            onClick={this.onFormSave.bind(this)}>Zapisz pracownika</Button>
                </div>
            </Container>
        );
    }
}