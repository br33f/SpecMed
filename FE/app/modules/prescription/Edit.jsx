import React from 'react';
import {Component} from 'react';
import BaseModel from 'components/models/BaseModel';
import {BindedInput} from 'components/controls/BindedInput.jsx';
import {BindedDateTimePicker} from 'components/controls/BindedDateTimePicker.jsx';
import {Container, Button, Form, FormGroup, Label, Row, Col} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import FontAwesome from 'react-fontawesome';

const ONE_MONTH = 30 * 24 * 60 * 60 * 1000;

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        customerId: "",
        nfzunit: "",
        rows: [
            {medicineName: "", dose: ""}
        ],
        creationDate: Date.now(),
        validDate: Date.now() + ONE_MONTH,
        discount: ""
    },
    saveUrl: "/prescription/save"
});

export class Prescription extends FormComponent {
    constructor(props) {
        let personalModel = new BaseModelConfigured();
        super(props, personalModel);

        this.getRoutingData(props);

        this.state = {
            NFZDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };

        this.addValidators();
    }

    addValidators() {
        this.rules = {
            "nfzunit": {validator: "required"},
            "creationDate": {validator: "required"},
            "rows": {validator: "required"},
            "validDate": {validator: "required"},
            "discount": {validator: "required"}
        };
    }

    componentDidMount() {
        this.fetchDictionaries();
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

    getRoutingData(props) {
        this.customerId = props.match.params.customerId;

        if (!this.customerId) {
            this.props.history.push('/login');
        }

        this.model.set('customerId', this.customerId);
    }

    onFormClear() {
        this.model.clear();
    }

    onFormSave() {
        this.clearEmptyPrescriptionRows();
        this.validate();

        if (!this.hasErrors()) {
            this.setState({
                isLoading: true
            });

            this.model.save().then(() => {
                this.setState({
                    isLoading: false,
                    isSaved: true
                });
                this.model.clear();
            });
        }

        this.forceUpdate();
    }

    clearEmptyPrescriptionRows() {
        let rows = this.model.get('rows');
        let notEmptyRows = rows.filter(row => row.medicineName || row.dose);
        this.model.set('rows', notEmptyRows);

        if (notEmptyRows.length === 0) {
            this.addRow();
        }
    }

    addRow() {
        let rows = this.model.get('rows');
        rows.push({medicineName: '', dose: ''});

        this.model.set('rows', rows);
    }

    formatPrescriptionRow(idx) {
        let medicineName = `rows[${idx}].medicineName`;
        let doseName = `rows[${idx}].dose`;
        let medicineId = `row-medicine-${idx}`;
        let doseId = `row-dose-${idx}`;

        return (
            <FormGroup>
                <Row>
                    <Col sm={7}>
                        <BindedInput form={this} type="text" name={medicineName} id={medicineId}
                                     placeholder="Nazwa leku"/>
                    </Col>
                    <Col sm={5}>
                        <BindedInput form={this} type="text" name={doseName} id={doseId} placeholder="Dawka"/>
                    </Col>
                </Row>
            </FormGroup>
        );
    }

    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Wystawianie recepty
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                    Pomyślnie wystawiono receptę
                </div>
                <Form>
                    <FormGroup>
                        <Label for="NFZunit">Oddział NFZ</Label>
                        <BindedInput form={this} type="select" name="nfzunit" id="NFZunit">
                            <option
                                key="PLACEHOLDER_VAR"
                                value="">
                                Wybierz oddział NFZ
                            </option>
                            )
                            {this.state.NFZDictionary.map(NFZObj =>
                                <option
                                    key={NFZObj.id}
                                    value={NFZObj.id}>
                                    {NFZObj.label}
                                </option>)}
                        </BindedInput>
                    </FormGroup>
                    <FormGroup className="mb-0">
                        <Label>Lista leków</Label>
                    </FormGroup>
                    {this.model.get('rows').map((row, idx) => this.formatPrescriptionRow(idx))}
                    <FormGroup>
                        <Button outline type="button" onClick={this.addRow.bind(this)}>
                            <FontAwesome name="plus-circle" size="lg"/> Kolejny lek
                        </Button>
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
                    <div className="text-right">
                        <hr/>
                        <Button outline type="button" className="mr-1"
                                onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                        <Button outline color="primary" type="button"
                                onClick={this.onFormSave.bind(this)}>Wystaw receptę</Button>
                    </div>
                </Form>
            </Container>
        );
    }
}