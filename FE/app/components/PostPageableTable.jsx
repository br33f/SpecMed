import React from 'react';
import axios from 'axios';
import {Component} from 'react';

import {Table} from 'reactstrap';

// TODO: przepisać na właściwą PostPageableTable
export class PostPageableTable extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employeeList: []
        };
    }

    componentDidMount() {
        axios.post('http://localhost:8080/employee/list', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(res => {
            this.setState({employeeList: res.data});
        });
    }

    render() {
        if (this.state.employeeList && this.state.employeeList.length > 0) {
            let columnDefinition = this.props.columnDefinition;

            return (
                <Table striped>
                    <thead>
                        <tr>
                            {columnDefinition.map(column =>
                                <th key={"column-" + column.key}>{column.label}</th>
                            )}
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.employeeList.map(employee =>
                        <tr key={employee.id}>
                            {columnDefinition.map(column =>
                                <td>{employee[column.key]}</td>
                            )}
                        </tr>
                    )}
                    </tbody>
                </Table>
            );
        } else {
            return (
                <span>Brak danych do tabeli</span>
            )
        }
    }
}