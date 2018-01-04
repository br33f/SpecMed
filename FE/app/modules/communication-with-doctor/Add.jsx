import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import axios from 'axios';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        content: "",
        medicalEmployeeId: "",
        customerId: "" /* To powinnismy brac w aplikacji z ApplicationContext.getCurentUser() - cos takiego */
    },
    saveUrl: '/communication/save'
});

export class CommunicationWithDoctor extends FormComponent {

    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);


        this.state = {
            medicalEmployeeList: [],
            specializationDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "content": [
                {
                    validator: "minLength",
                    params: {
                        length: 5
                    }
                }
            ],
            "medicalEmployeeId":
                {
                    validator: "required"
                },
            "specialization":
        {
            validator: "required"
        },
        };
    }

    componentDidMount() {
        this.employeeId && this.model.fetch();
        this.fetchDictionaries();
    }

    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("SPECIALIZATION").then(dict => {
            this.setState({
                specializationDictionary: dict
            });
        });
    }

    /**
     * Metoda pobiera pracowników medycznych
     * @private
     */
    fetchMedicalEmployees(specialization) {
        axios.post('/medical-employee/specialization/list', {
            chunkData: specialization
        }).then(response => {
            if (response.data.content) {
                this.setState({
                    medicalEmployeeList: response.data.content
                });
            }
        });
    }

    onSpecializationChange() {
        this.bindValueToModel(...arguments);
        this.fetchMedicalEmployees(this.model.get('specialization'));
    }

    onFormSave() {
        this.validate();
        if (!this.hasErrors()) {
            this.setState({
                isLoading: true
            });
            this.sendNewMessageRequest().then(() => {
                this.setState({
                    isLoading: false,
                    isSaved: true
                });
            });
        } else {
            this.forceUpdate();
        }
        console.log(this.errors);
    }

    sendNewMessageRequest() {
        let SaveModel = BaseModel.extend({
            saveUrl: 'communication/save'
        });

        let saveModel = new SaveModel();
        saveModel.set('medicalEmployeeId', this.model.get('medicalEmployeeId'));
        saveModel.set("content", this.model.get('content'));
        saveModel.set('customerId', SM.Auth.getEntityId());

        return saveModel.save();
    }

    onFormClear() {
        console.log("Clear!");
        this.model.clear();
    }

    onMedicalEmployeeChange() {
        this.bindValueToModel(...arguments);
    }

    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {'Wyślij wiadomość do lekarza'}
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            {'Pomyślnie wysłano wiadomość.'}
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="specialization">Specjalizacja</Label>
                                <BindedInput form={this} type="select" name="specialization" id="specialization"
                                             onChange={this.onSpecializationChange.bind(this)}>
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
                            <FormGroup>
                                <Label for="employee">Wybierz pracownika</Label>
                                <BindedInput form={this} type="select" name="medicalEmployeeId" id="employee"
                                             onChange={this.onMedicalEmployeeChange.bind(this)}>
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz pracownika z listy
                                    </option>
                                    {this.state.medicalEmployeeList.map(meObj =>
                                        <option
                                            key={meObj.id}
                                            value={meObj.id}>
                                            {meObj.name + ' ' + meObj.surname}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup>
                                <Label for="contentId">Twoja wiadomość:</Label>
                                <br/>
                                <textarea rows="5" cols="60" type="text" name="content" id="contentId"
                                          placeholder="Treść wiadomości..."
                                          value={this.state.model.get('content')}
                                          onChange={this.bindValueToModel}/>
                            </FormGroup>

                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Wyślij wiadomość</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
        console.log("render");
    }
}
