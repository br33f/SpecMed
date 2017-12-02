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
        axios.post('http://localhost:8080/employee/list').then(res => {
            this.setState({employeeList: res.content});
        });
    }

    render() {
        if (this.state.employeeList && this.state.employeeList.length > 0) {
            return (
                <Table striped>
                    <TableHeader headerDefinition={this.props.headerDefintion} />
                    <tbody>
                    {this.state.employeeList.map(employee =>
                        <tr key={employee.id}>
                            {this.props.headerDefinition.map(column =>
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