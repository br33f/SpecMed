import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

/**
 * Klasa odpowiedzialna za wyswietlenie listy ubezpieczeń
 * @augments Component
 */
export class InsuranceList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Generowanie linku do edycji
     * @param insuranceId identyfikator ubezpieczenia
     * @returns {XML} zwrocony link do generowania
     */
    generateEditLink(insuranceId) {
        let editUrl = `/insurance/edit/${insuranceId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-pencil-square fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }
    /**
     * funkcja odpowiedzialna za generowanie nagłówka dla ubezpieczen
     * @returns nagłowek danych dla widoku listowania ubezpieczen
     */
    getHeaderDefinition() {
        return [
            {key: 'name', label: 'Nazwa ubezpieczenia', sortable: true},
            {key: 'price', label: 'Cena ubezpieczenia', sortable: true},
            {key: 'notes', label: 'Opis'},
            {key: 'insurancePolicyNumber', label: 'Numer polisy ubezpieczeniowej', sortable: true},
            {key: 'id', label: 'Edycja', format: {type: 'custom', fn: this.generateEditLink}}
        ];
    }

    /**
     * Funckja odpowiedzialna za wyswietlanie listy ubezpieczen
     * @returns {XML}
     */
        render()
        {
            return (
                <Container fluid={true}>
                    <p className="contentTitle">Lista ubezpieczeń</p>
                    <PostPageableTable
                        headerDefinition={this.getHeaderDefinition()}
                        dataUrl="/insurance/list"
                    />

                </Container>
            );
        }
    }