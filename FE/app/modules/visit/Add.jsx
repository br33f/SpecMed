import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";

const BaseModelConfigured = BaseModel.extend({
    defaults: {
        price: "",
        place: "",
        date: Date.now(),
        status: "1"
    },
    saveUrl: 'employee/save'

});

export class VisitAdd extends FormComponent {

    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        // rozdział na edycje i dodawanie jest bo 16 nie damy rady zrobic
        // albo cos

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
        SM.DictionaryManager.getDictAsArray("VISIT_STATUS").then(dict => {
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
                    { 'Dodanie nowej wizyty' }
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie zapisano wizytę.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="visitPlace">Miejsce</Label>
                                <Input type="text" name="personalData.name" id="visitPlace" placeholder="Miejsce"
                                       value={this.state.model.get('place')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitDate">Data</Label>
                                <Input type="date" name="date" id="visitDate"
                                       placeholder="Data" value={this.state.model.get('date')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="visitPrice">Cena</Label>
                                <Input type="text" name="price" id="employeePesel" placeholder="Cena"
                                       value={this.state.model.get('price')}
                                       onChange={this.bindValueToModel}/>
                            </FormGroup>
                            {/*<FormGroup>*/}
                                {/*<Label for="employeeBirthday">Data urodzenia</Label>*/}
                                {/*<Input type="date" name="personalData.birthday" id="employeeBirthday"*/}
                                       {/*placeholder="Data urodzenia"*/}
                                       {/*value={this.state.model.get('personalData.birthday') && SM.Utils.customFormat(this.state.model.get('personalData.birthday'), "yyyy-mm-dd")}*/}
                                       {/*onChange={this.bindValueToModel}/>*/}
                            {/*</FormGroup>*/}
                            <FormGroup>
                                <Label for="visitStatus">Status początkowy</Label>
                                <Input type="select" name="status" id="visitStatus"
                                       value={this.state.model.get('status')}
                                       onChange={this.bindValueToModel}>
                                    {this.state.genderDictionary.map(statusObj =>
                                        <option
                                            key={statusObj.id}
                                            value={statusObj.id}>
                                            {statusObj.label}
                                        </option>)}
                                </Input>
                            </FormGroup>
                            <div className="pull-right">
                                <Button outline type="button" className="mr-1"
                                        onClick={this.onFormClear.bind(this)}>Wyczyść formularz</Button>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Zapisz wizytę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
    }
}
