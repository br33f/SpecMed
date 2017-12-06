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
        hour: "",
        status: "1",
        doctor: ""
    },
    saveUrl: 'employee/save'

});

export class OpinionAdd extends FormComponent {

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

    getSample() {
        return ['Adam Abacki', 'Marcin Babacki'];
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
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    { 'Ocena odbytej wizyty' }
                    <Loader isEnabled={this.state.isLoading}/>
                </p>

                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" hidden={!this.state.isSaved} role="alert">
                            Pomyślnie dodano opinię.
                        </div>
                        <Form>
                            <FormGroup>
                                <Label for="description">Ocena wizyty:</Label>
                                <br/>
                                <textarea rows="5" cols="60" type="text" name="description" id="description"
                                       placeholder="Twoja opinia..."/>
                            </FormGroup>
                            {/*dobry hardcode*/}
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="star-rating">
                                        <span className="fa fa-star" data-rating="1"></span>
                                        <span className="fa fa-star" data-rating="2"></span>
                                        <span className="fa fa-star" data-rating="3"></span>
                                        <span className="fa fa-star-half-empty" data-rating="4"></span>
                                        <span className="fa fa-star-o" onClick={(e)=>this.setGold(e)} data-rating="5"></span>
                                        <input type="hidden" name="whatever1" className="rating-value" value="2.56"/>
                                    </div>
                                </div>
                            </div>
                            <div className="pull-left">
                                <hr/>
                                <Button outline color="primary" type="button"
                                        onClick={this.onFormSave.bind(this)}>Oceń wizytę</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
        );
        console.log("render");
    }
}
