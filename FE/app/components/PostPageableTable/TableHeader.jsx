import React from 'react';
import {Component} from 'react';

export class TableHeader extends Component {
    render() {
        return (
            <thead>
            <tr>
                {this.props.headerDefinition.map(column =>
                    <th key={"column-" + column.key}>{column.label}</th>
                )}
            </tr>
            </thead>
        );
    }
}