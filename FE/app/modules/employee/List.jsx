import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

export class EmployeeList extends Component {
    constructor(props) {
        super(props);
    }

    generateEditLink(employeeId) {
        let editUrl = `/employee/edit/${employeeId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-pencil-square fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }

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