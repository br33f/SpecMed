import React from 'react';
import {Component} from 'react';

export class TableContent extends Component {
    render() {
        return (
            <tbody>
            {
                this.props.employeeList.map(employee => this.generateRow(employee))
            }
            </tbody>
        );
    }

    generateRow(employee) {
        const headerDefinition = this.props.headerDefinition;

        let columns = headerDefinition.map(column => this.generateColumn(employee, column));

        return (
            <tr key={employee.id}>
                {columns}
            </tr>
        );
    }

    generateColumn(employee, column) {
        let value = _.get(employee, column.key);

        if (column.format) {
            value = this.formatValue(value, column.format);
        }

        return (
            <td key={employee.id + '-' + column.key}>{value}</td>
        );
    }

    formatValue(value, format) {
        let formatType = format instanceof Object ? format.type : format;
        switch (formatType) {
            case "date":
                return SM.Utils.formatDate(value);
                break;
            case "time":
                return SM.Utils.formatTime(value);
                break;
            case "datetime":
                return SM.Utils.formatDateTime(value);
                break;
            case "dictionary":
                return this.props.dictionaries[format.dictionaryName] && this.props.dictionaries[format.dictionaryName][value];
                break;
            case "custom":
                return format.fn(value);
                break;
            default:
                return value;
                break;
        }

    }
}