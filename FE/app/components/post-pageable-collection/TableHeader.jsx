import React from 'react';
import {Component} from 'react';
import FontAwesome from 'react-fontawesome';

export class TableHeader extends Component {
    getSorting(column) {
        let sortingElements;
        if (column && column.sortable) {
            sortingElements = (
                <span>
                    <FontAwesome className="sortDescending" size="lg" name='sort-desc' onClick={() => this.props.sortingHandler(column.key, 'ASC')}/>
                    <FontAwesome className="sortAscending" size="lg" name='sort-asc' onClick={() => this.props.sortingHandler(column.key, 'DESC')} />
                </span>
            );
        }

        return sortingElements;
    }

    render() {
        return (
            <thead>
            <tr>
                {this.props.headerDefinition.map(column =>
                    <th key={"column-" + column.key}>
                        {column.label}
                        {this.getSorting(column)}
                    </th>
                )}
            </tr>
            </thead>
        );
    }
}