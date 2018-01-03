import React from 'react';
import {Component} from 'react';
import BaseModel from 'components/models/BaseModel';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {BindedDateTimePicker} from 'components/controls/BindedDateTimePicker.jsx';
import {Button, Form, FormGroup, Label} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import axios from 'axios';

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        NFZunit: "",
        medicalEmployee: Object,
        rows: [],
        creationDate: Date.now(),
        validDate: Date.now(),
        customer: "",
        discount: ""
    },
    saveUrl: "/prescription/save"
});

export class Prescription extends FormComponent {
    constructor(props) {
        let personalModel = new BaseModelConfigured();
        super(props, personalModel);

        this.getRoutingData(props);
        this.fetchCustomer();

        // personalModel.setDefaults(modelDefaults);
        // personalModel.set(modelDefaults);

        this.state = {
            customerdata: "",
            NFZDictionary: [],
            medicalEmployeeList: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };



        this.addValidators();

    }



    addValidators() {
        this.rules = {
            "NFZunit": {validator: "required"},
            "medicalEmployee": {validator: "required"},
            "creationDate": {validator: "required"},
            "validDate": {validator: "required"},
            "discount": {validator: "required"}
        };
    }


    componentDidMount() {
        this.handleFetch(this.props);
        this.fetchDictionaries();
        this.fetchMedicalEmployees();

    }

    componentWillReceiveProps(newProps) {
        this.handleFetch(newProps);
    }

    handleFetch(props) {
        if (props.customerId) {
            this.model.fetchUrl = `/customer/get/${props.customerId}/personal`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
        if (props.employeeId) {
            this.model.fetchUrl = `/employee/get/${props.employeeId}/personal`;
            this.model.fetch();
        } else {
            this.model.clear();
        }
    }

    /**
     * Metoda wywoływana po inicjalizacji obiektu. odpowiedzialna ze pobranie oddziału NFZ ze słownika
     */
    fetchDictionaries() {
        SM.DictionaryManager.getDictAsArray("NFZ_UNIT").then(dict => {
            this.setState({
                NFZDictionary: dict
            });
        });

    }
    fetchMedicalEmployees() {
        axios.get('/medical-employee/list/full', {
        }).then(response => {
            if (response.data.content) {
                this.setState({
                    medicalEmployeeList: response.data.content
                });
            }
        });
    }

    getRoutingData(props) {
        this.customerId = props.match.params.customerId;

    }

    fetchCustomer() {
        axios.get('/customer/get/'+this.customerId, {
        }).then(response => {
            if (response.data.content) {
                this.setState({
                    customerdata: response.data.content
                });
            }
        });
    }

    onFormClear() {
        this.model.clear();
    }

    onFormSave() {

        this.model.set('customer',this.state.customerdata);
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

    render() {
        return (
            <Form>

                <FormGroup>
                <Label for="employee">Wybierz pracownika</Label>
                <BindedInput form={this} type="select" name="medicalEmployee" id="medicalEmployee">
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
                    <Label for="NFZunit">Oddział NFZ</Label>
                    <BindedInput form={this} type="select" name="NFZunit" id="NFZunit">
                        <option
                            key="PLACEHOLDER_VAR"
                            value="">
                            Wybierz oddział NFZ
                        </option>)
                        {this.state.NFZDictionary.map(NFZObj =>
                            <option
                                key={NFZObj.id}
                                value={NFZObj.id}>
                                {NFZObj.label}
                            </option>)}
                    </BindedInput>
                </FormGroup>
                <FormGroup>
                    <Label for="prescriptionrow">Lista leków</Label>  {/*TODO: dodać prawdziwą listę leków*/}
                    <BindedInput form={this} type="textarea" name="rows" id="rows" placeholder="Lista leków"/>
                </FormGroup>
                <FormGroup>
                    <Label for="creationDate">Data wystawienia</Label>
                    <BindedDateTimePicker form={this} id="creationDate" name="creationDate" time={false}
                                          placeholder="Wybierz datę wystawienia"/>
                </FormGroup>
                <FormGroup>
                    <Label for="validDate">Data ważności</Label>
                    <BindedDateTimePicker form={this} id="validDate" name="validDate" time={false}
                                          placeholder="Wybierz datę ważności"/>
                </FormGroup>
                <FormGroup>
                    <Label for="discount">Zniżka</Label>
                    <BindedInput form={this} type="text" name="discount" id="discount" placeholder="Zniżka"/>
                </FormGroup>
                <div className="pull-right">
                    <Button outline type="button" className="mr-1"
                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                    <Button outline color="primary" type="button"
                            onClick={this.onFormSave.bind(this)}>Wystaw receptę</Button>
                </div>
            </Form>
        );
    }
}