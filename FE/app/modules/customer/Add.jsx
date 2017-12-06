import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        personalData: {
            name: "",
            surname: "",
            pesel: "",
            birthday: Date.now(),
            gender: "1"
        }
    },
    saveUrl: 'employee/save'
});

export class CustomerEdit extends FormComponent {
    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // jeżeli przekazano employeeId w url
        this.employeeId = props.match.params.employeeId;
        localModel.fetchUrl = "/employee/get/" + this.employeeId;

        this.state = {
            genderDictionary: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    componentDidMount() {
        this.employeeId && this.model.fetch();
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
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano pacjenta.
                        </div>
                        <Form>
                            <div className="pull-left">
                                <div className="h5">Dane osobowe:</div>
                                <FormGroup>
                                    <Label for="employeeName">Imię</Label>
                                    <Input type="text" name="personalData.name" id="employeeName" placeholder="Imię"
                                           value={this.state.model.get('personalData.name')}
                                           onChange={this.bindValueToModel}/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Nazwisko</Label>
                                    <Input type="text" name="personalData.surname" id="employeeSurname"
                                           placeholder="Nazwisko" value={this.state.model.get('personalData.surname')}
                                           onChange={this.bindValueToModel}/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeePesel">Numer PESEL</Label>
                                    <Input type="text" name="personalData.pesel" id="employeePesel"
                                           placeholder="PESEL" maxLength="11"
                                           value={this.state.model.get('personalData.pesel')}
                                           onChange={this.bindValueToModel}/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeBirthday">Data urodzenia</Label>
                                    <Input type="date" name="personalData.birthday" id="employeeBirthday"
                                           placeholder="Data urodzenia"
                                           value={this.state.model.get('personalData.birthday') && SM.Utils.customFormat(this.state.model.get('personalData.birthday'), "yyyy-mm-dd")}
                                           onChange={this.bindValueToModel}/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeGender">Płeć</Label>
                                    <Input type="select" name="personalData.gender" id="employeeGender"
                                           value={this.state.model.get('personalData.gender')}
                                           onChange={this.bindValueToModel}>
                                        {this.getSampleGender().map(genderObj =>
                                            <option
                                                key={genderObj}
                                                value={genderObj}>
                                                {genderObj}
                                            </option>)}
                                    </Input>
                                </FormGroup>
                            </div>
                            <div className="pull-right">
                                <div className="h5">Dane adresowe:</div>
                                <FormGroup>
                                    <Label for="employeeName">Miasto</Label>
                                    <Input type="text" name="personalData.name" id="employeeName" placeholder="Miasto"
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Ulica</Label>
                                    <Input type="text" name="personalData.surname" id="employeeSurname"
                                           placeholder="Ulica"/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Numer domu</Label>
                                    <Input type="text" name="personalData.surname" id="employeeSurname"
                                           placeholder="Numer domu"/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Numer mieszkania</Label>
                                    <Input type="text" name="personalData.surname" id="employeeSurname"
                                           placeholder="Numer mieszkania"/>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="employeeSurname">Kod pocztowy</Label>
                                    <Input type="text" name="personalData.surname" id="employeeSurname"
                                           placeholder="__-___"/>
                                </FormGroup>
                                <div>
                                    <hr/>
                                    <Button outline type="button" className="mr-1"
                                            onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                    <Button outline color="primary" type="button"
                                            onClick={this.onFormSave.bind(this)}>Aktualizuj dane pacjenta</Button>
                                </div>
                            </div>

                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }

    getSampleGender() {
        return ['Kobieta', 'Mezczyzna'];
    }
}