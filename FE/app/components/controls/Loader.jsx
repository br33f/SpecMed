import React from 'react';
import {Component} from 'react';

/**
 * Jest to klasa odpowiedzialna za wczytanie comopnentoz
 */
export class Loader extends Component {
    /**
     *
     * @returns {XML} nazwa komponentu
     */
    render() {
        let classNames ="fa fa-circle-o-notch fa-spin specmed-spinner";
        !this.props.isEnabled && (classNames += ' d-none');
        return (
            <i className={classNames} />
        )
    }
}