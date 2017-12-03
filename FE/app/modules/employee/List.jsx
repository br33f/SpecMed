import React from 'react';
import {Component} from 'react';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/PostPageableTable/PostPageableTable.jsx';

export class EmployeeList extends Component {
    constructor(props) {
        super(props);
    }

    getHeaderDefinition() {
        return [
            {key: 'personalData.name', label: 'Imię pracownika', sortable: true},
            {key: 'personalData.surname', label: 'Nazwisko pracownika', sortable: true},
            {key: 'personalData.pesel', label: 'PESEL'},
            {key: 'personalData.birthday', label: 'Urodziny', format: 'date'},
            {key: 'personalData.gender', label: 'Płeć', format: {type: 'dictionary', dictionaryName: "GENDER"}},
        ];
    }

    render() {
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