import React from 'react';

import { Link } from 'react-router-dom';
import { Jumbotron } from 'reactstrap';

export class Jumbo extends React.Component {
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