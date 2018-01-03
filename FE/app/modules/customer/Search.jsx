import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';
import {Container, Row, Col, Form, FormGroup, Label, Button} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import Utils from "components/helpers/Utils";

const SearchModelConfigured = BaseModel.extend({
    defaults: {
        personalData: {
            name: "",
            surname: "",
            pesel: ""
        },
        contactData: {
            telephoneNumber: "",
            email: ""
        }
    }
});

/**
 * Klasa odpowiedzialna za wyszukiwanie pacjentów
 * @augments Component
 */
export class CustomerSearch extends FormComponent {
    constructor(props) {
        let searchModel = new SearchModelConfigured();
        super(props, searchModel);

        this.state = {
            isLoading: false,
            model: this.model,
            searchCriteria: {}
        }
    }

    searchCustomer() {
        this.setState({
            searchCriteria: Utils.omitByRecursively(this.model.getRecursive(), _.isEmpty)
        });
    }

    onFormClear() {
        this.model.clear();
        this.setState({
            searchCriteria: {}
        });
    }

    /**
     * Generowanie linku do edycji
     * @param customerId identyfikator pacjenta
     * @returns {XML} zwrocony link do generowania
     */
    generateMedicalHistoryLink(customerId) {
        let medicalHistoryUrl = `/customer/${customerId}/history`;
        return (
            <Link to={medicalHistoryUrl}>
                <i className="fa fa-archive fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }

    generatePrescriptionLink(customerId) {
        let editUrl = `/prescription/new/${customerId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-book fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }


    /**
     * funkcja odpowiedzialna za generowanie nagłówka
     * @returns nagłowek dancych dla widoku listowania pacjentów
     */
    getHeaderDefinition() {
        return [
            {key: 'personalData.name', label: 'Imię pracownika', sortable: true},
            {key: 'personalData.surname', label: 'Nazwisko pracownika', sortable: true},
            {key: 'personalData.pesel', label: 'PESEL'},
            {key: 'contactData.telephoneNumber', label: 'Numer telefonu'},
            {key: 'contactData.email', label: 'Adres e-mail'},
            {key: 'addressData.cityName', label: 'Miejscowość', sortable: true},
            {key: 'id', label: 'Historia', format: {type: 'custom', fn: this.generateMedicalHistoryLink}},
            {key: 'id', label: 'Recepta', format: {type: 'custom', fn: this.generatePrescriptionLink}}
        ];
    }

    /**
     * Funkcja odpowiedzialna za renderowanie listy pracowników
     * @returns {XML} Lista pacjentów
     * */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    Wyszukiwarka pacjentów
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col>
                        <Form>
                            <h5>Parametry wyszukiwania</h5>
                            <FormGroup>
                                <Label for="firstName">Imię</Label>
                                <BindedInput form={this} type="text" name="personalData.name" id="firstName"
                                             placeholder="Imię"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="lastName">Nazwisko</Label>
                                <BindedInput form={this} type="text" name="personalData.surname" id="lastName"
                                             placeholder="Nazwisko"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="pesel">PESEL</Label>
                                <BindedInput form={this} type="text" name="personalData.pesel" id="pesel"
                                             placeholder="PESEL"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="telephoneNumber">Numer telefonu</Label>
                                <BindedInput form={this} type="text" name="contactData.telephoneNumber" id="telephoneNumber"
                                             placeholder="Numer telefonu"/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="email">Adres e-mail</Label>
                                <BindedInput form={this} type="text" name="contactData.email" id="email"
                                             placeholder="Adres e-mail"/>
                            </FormGroup>
                        </Form>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <div>
                            <Button outline type="button" className="mr-1"
                                    onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                            <Button outline color="primary" type="button"
                                    onClick={this.searchCustomer.bind(this)}>Wyszukaj pacjenta</Button>
                            <hr/>
                        </div>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <h5>Wynik wyszukiwania</h5>
                        <PostPageableTable
                            headerDefinition={this.getHeaderDefinition()}
                            dataUrl="/customer/list"
                            searchCriteria={this.state.searchCriteria}
                        />
                    </Col>
                </Row>
            </Container>
        );
    }
}