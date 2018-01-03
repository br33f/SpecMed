import React from 'react';
import {Component} from 'react';
import {Container} from 'reactstrap';
import {ListGroup, ListGroupItem} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import axios from 'axios';
import {Link} from 'react-router-dom';

export class Conversations extends Component {

    constructor(props) {
        super(props);
        this.state = {
            targetList: [],
            specializationDictionary: {},
            isLoading: true
        };
    }

    componentDidMount() {
        this.fetchTargets();
        this.fetchDictionaries();
    }

    fetchDictionaries() {
        SM.DictionaryManager.getDict("SPECIALIZATION").then(dict => {
            this.setState({
                specializationDictionary: dict
            });
        });
    }

    /**
     * Metoda pobiera rozmówców
     * @private
     */
    fetchTargets() {
        axios.get('/communication/mine/targets').then(response => {
            if (response.data.content) {
                this.setState({
                    targetList: response.data.content,
                    isLoading: false
                });
            }
        });
    }


    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {'Lista konwersacji'}
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <ListGroup>
                    {this.state.targetList.map(target =>
                        <ListGroupItem action key={target.id} tag={Link} to={`/contact/conversation/${target.id}`}>
                            {"Konwersacja z "}
                            <b>
                                {target.personalData.name + ' ' + target.personalData.surname + ' ( ' + target.specializationList.map(specializationCode => this.state.specializationDictionary[specializationCode] + ' ') + ') '}
                            </b>
                            <span className="text-muted">
                                kliknij, aby przejść do konwersacji
                            </span>
                        </ListGroupItem>
                    )}
                </ListGroup>
            </Container>
        );
    }
}
