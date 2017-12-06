import React from 'react';
import './jumbotron.scss';

import { Link } from 'react-router-dom';
import { Jumbotron } from 'reactstrap';

/**
 * Klasa odpowiedzialna za generowanie oraz obsluge nagłówka
 */
export class Jumbo extends React.Component {
    /**
     * Metoda odpowiedzialna za zwrocenie nagłówka aplikacji
     * @returns {XML} głowny nagłowek aplikacji
     */
    render() {
        return (
            <Jumbotron className="specJumbo">
                <div className="headingTile">
                    <h1 className="display-3 d-inline-block">SpecMed</h1>
                    <br />
                    <p className="lead d-inline-block">Witamy w aplikacji SpecMed - obsługa specjalistycznego szpitala!</p>
                </div>
            </Jumbotron>
        );
    }
}