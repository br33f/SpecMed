import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import BaseModel from 'components/models/BaseModel';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import ReactStars from 'react-stars';
import {BindedInput} from "components/controls/BindedInput.jsx";
import axios from 'axios';


const BaseModelConfigured = BaseModel.extend({
    defaults: {
        comment: "",
        stars: "",
        visitId: ""
    },
    saveUrl: 'opinion/save'

});

export class OpinionAdd extends FormComponent {

    constructor(props) {
        // Utworz model i przekaż go w konstruktorze do rodzica
        let localModel = new BaseModelConfigured();
        super(props, localModel);

        this.state = {
            freeVisitsList: [],
            model: this.model,
            isLoading: false,
            isSaved: false
        };
    }

    fetchMyVisits() {
        axios.post('/visit/list/mine', {}).then(response => {
            if (response.data.content) {
                this.setState({
                    freeVisitsList: response.data.content
                })
            }
        });
    }

    componentDidMount() {
        this.employeeId && this.model.fetch();
        this.fetchMyVisits();
    }

    sendOpinionRequest() {
        let SaveModel = BaseModel.extend({
            saveUrl: '/opinion/save'
        });

        let saveModel = new SaveModel();
        saveModel.set('visitId', this.model.get('visitId'));
        saveModel.set('comment', this.model.get('comment'));
        saveModel.set('stars', this.model.stars);

        return saveModel.save();
    }

    onFormSave() {
        this.setState({
            isLoading: true
        });
        this.sendOpinionRequest().then(() => {

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

    ratingChanged = (newRating) => {
        this.model.stars =  newRating;
    };

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
                                <Label for="visit">Wybierz wizytę</Label>
                                <BindedInput form={this} type="select" name="visitId" id="visit">
                                    <option
                                        key="PLACEHOLDER_ITEM"
                                        value="">
                                        Wybierz wizytę z listy
                                    </option>
                                    {this.state.freeVisitsList.map(visitObj =>
                                        <option
                                            key={visitObj.id}
                                            value={visitObj.id}>
                                            {visitObj.place + " - Od " + SM.Utils.formatDateTime(visitObj.dateStart) + " Do " + SM.Utils.formatDateTime(visitObj.dateEnd) + " - Cena: " + visitObj.price + "zł"}
                                        </option>)}
                                </BindedInput>
                            </FormGroup>
                            <FormGroup>
                                <Label for="description">Ocena wizyty:</Label>
                                <br/>
                                <textarea rows="5" cols="60" type="text" name="comment" id="comment"
                                          placeholder="Twoja opinia..."
                                          value={this.state.model.get('comment')}
                                          onChange={this.bindValueToModel}/>
                            </FormGroup>
                            <ReactStars count={5} onChange={this.ratingChanged} size={24} color2={'#ffd700'}/>
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
        console.log("render https://www.npmjs.com/package/react-stars");
    }
}
