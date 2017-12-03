import React from 'react';
import {Component} from 'react';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/PostPageableTable/PostPageableTable.jsx';

export class EmployeeList extends Component {
    render() {
        return (
            <Container fluid={true}>
                <p className="lead">Lista pracowników</p>
                <PostPageableTable headerDefintion={[
                    {key: 'personalData.name', label: 'Imię pracownika', sortable: true},
                    {key: 'personalData.surname', label: 'Nazwisko pracownika'},
                    {key: 'personalData.pesel', label: 'PESEL'},
                    {key: 'personalData.birthday', label: 'Urodziny'}
                    ]}/>
            </Container>
        );
    }
}