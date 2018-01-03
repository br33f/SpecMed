import React from 'react';
import {Component} from 'react';
import {Container} from 'reactstrap';
import {ListGroup, ListGroupItem, Badge} from 'reactstrap';
import {FormComponent} from "components/form-component/FormComponent.jsx";
import {Loader} from "components/controls/Loader.jsx";
import {BindedInput} from "components/controls/BindedInput.jsx";
import axios from 'axios';

export class Conversation extends Component {

    constructor(props) {
        super(props);
        this.state = {
            conversation: [],
            isLoading: true
        };
    }

    componentDidMount() {
        this.getRoutingData();
        this.fetchMessages();
    }

    getRoutingData() {
        this.doctorId = this.props.match.params.doctorId;
        if (!this.doctorId) {
            this.props.history.push('/contact/list');
        }
    }


    /**
     * Metoda pobiera rozmowę
     * @private
     */
    fetchMessages() {
        axios.get(`/communication/mine/with/${this.doctorId}`).then(response => {
            if (response.data.content) {
                this.setState({
                    conversation: response.data.content,
                    isLoading: false
                });
            }
        });
    }

    formatMessage(msg) {
        let content = msg.content;
        let sender = msg.customerSender ? msg.customer.personalData.name + ' ' + msg.customer.personalData.surname : msg.medicalEmployee.personalData.name + ' ' + msg.medicalEmployee.personalData.surname;
        let senderBadge = <Badge pill>{sender}</Badge>;

        return (
            <ListGroupItem color={msg.customerSender ? 'info' : 'light'} className={msg.customerSender ? 'text-left' : 'text-right'} action key={msg.id}>
                {msg.customerSender ? senderBadge : ""}
                {content}
                {!msg.customerSender ? senderBadge : ""}
            </ListGroupItem>
        );
    }

    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    {'Konwersacja'}
                    <Loader isEnabled={this.state.isLoading}/>
                </p>
                <ListGroup>
                    {this.state.conversation.map(msg => this.formatMessage(msg))}
                </ListGroup>
            </Container>
        );
    }
}
