import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

/**
 * Klasa odpowiedzialna za wyswietlanei listy placówek medycznych
 * @augments Component
 */
export class UnitList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * funkcja odpowiedzialna za generowanie nagłówka dla placówek medycznych
     * @returns nagłowek dancyh dla widoku listowania placówek medycznych
     */
    getHeaderDefinition() {
        return [
            {key: 'addressData.cityName', label: 'Miasto', sortable: true},
            {key: 'addressData.postalCode', label: 'Kod pocztowy', sortable: true},
            {key: 'contactData.email', label: 'Email'},
            {key: 'contactData.telephoneNumber', label: 'Numer telefonu'}
        ];
    }

    /**
     * Funkcja odpowiedzialna za wyswietlenie listy placówek medycznych
     * @returns {XML} element listy pracowników medycznych
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">Lista placówek medycznych</p>
                <PostPageableTable
                    headerDefinition={this.getHeaderDefinition()}
                    dataUrl="/unit/list"
                />

            </Container>
        );
    }
}