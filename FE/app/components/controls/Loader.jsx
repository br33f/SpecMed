import React from 'react';
import {Component} from 'react';

export class Loader extends Component {
    render() {
        let classNames ="fa fa-circle-o-notch fa-spin specmed-spinner";
        !this.props.isEnabled && (classNames += ' d-none');
        return (
            <i className={classNames} />
        )
    }
}