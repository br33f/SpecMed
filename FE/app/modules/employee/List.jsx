import React from 'react';
import {Component} from 'react';
import {PostPageableTable} from '../../components/PostPageableTable.jsx';

export class EmployeeList extends Component {
    render() {
        return (
            <div>
                <h1 className="display-3">SpecMed</h1>
                <p className="lead">Lista pracowników</p>
                <PostPageableTable columnDefinition={[
                    {key: 'firstName', label: 'Imię pracownika'},
                    {key: 'lastName', label: 'Nazwisko pracownika'}
                    ]}/>
            </div>
        );
    }
}