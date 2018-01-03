import React from 'react';
import './footer.scss';

import { Jumbotron } from 'reactstrap';

/**
 * Klasa odpowiedzialna za generowanie oraz obsluge stopki
 */
export class Footer extends React.Component {
    /**
     * Metoda odpowiedzialna za zwrocenie stopki aplikacji
     * @returns {XML} głowny stopki aplikacji
     */
    render() {
        return (
            <Jumbotron className="footerJumbo">
                <span>SPECMED ®2017</span>
            </Jumbotron>
        );
    }
}