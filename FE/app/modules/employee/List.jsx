import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

/**
 * Klasa odpowiedzialna za wyswietlanie listy pracowników.
 * @augments Component
 */
export class EmployeeList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Generowanie linku do edycji
     * @param employeeId identyfikator pracownika
     * @returns {XML} zwrocony link do generowania
     */
    generateEditLink(employeeId) {
        let editUrl = `/employee/edit/${employeeId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-pencil-square fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }


    /**
     * funkcja odpowiedzialna za generowanie nagłówka
     * @returns nagłowek dancyh dla widoku listowania pracowników
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
     * Funkcja odpowiedzialna za renderowanie listy pracowników
     * @returns {XML} Lista praocowników do wyświetlenia
     */
        render()
        {
            return (
                <Container fluid={true}>
                    <p className="contentTitle">Lista pracowników</p>
                    <PostPageableTable
                        headerDefinition={this.getHeaderDefinition()}
                        dataUrl="/employee/list"
                    />

                </Container>
            );
        }
    }