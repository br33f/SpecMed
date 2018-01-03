import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

/**
 * Klasa odpowiedzialna za wyswietlanei listy pracowników medycznych
 * @augments Component
 */
export class MedicalEmployeeList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Generowanie linku do edycji
     * @param employeeId identyfikator pracownika medycznego
     * @returns {XML} zwrocony link do generowania
     */
    generateEditLink(employeeId) {
        //to nizej jeszcze nie jest w controlerze
        let editUrl = `/medical-employee/edit/${employeeId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-pencil-square fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }

    /**
     * funkcja odpowiedzialna za generowanie nagłówka dla pracowników medycznych
     * @returns nagłowek dancyh dla widoku listowania pracowników medycznych
     */
    getHeaderDefinition() {
        return [
            {key: 'personalData.name', label: 'Imię pracownika', sortable: true},
            {key: 'personalData.surname', label: 'Nazwisko pracownika', sortable: true},
            {key: 'personalData.pesel', label: 'PESEL'},
            {key: 'personalData.birthday', label: 'Urodziny', format: 'date'},
            {key: 'personalData.gender', label: 'Płeć', format: {type: 'dictionary', dictionaryName: "GENDER"}},
            {key: 'id', label: 'Edycja', format: {type: 'custom', fn: this.generateEditLink}}
        ];
    }

    /**
     * Funkcja odpowiedzialna za wyswietlenie listy pracownikow medycznych
     * @returns {XML} element listy pracowników medycznych
     */
    render()
    {
        return (
            <Container fluid={true}>
                <p className="contentTitle">Lista pracowników medycznych</p>
                <PostPageableTable
                    headerDefinition={this.getHeaderDefinition()}
                    dataUrl="/medical-employee/list/full"
                />

            </Container>
        );
    }
}