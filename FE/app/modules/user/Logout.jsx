import React from 'react';
import {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import Utils from 'components/helpers/Utils';
import {Link} from 'react-router-dom';

/**
 * Klasa odpowiedzialna za wylogowywanie
 * @extends Component
 */
export class Logout extends Component {
    /**
     * Konstruktor
     * @constructor
     * @param {object} props parametry przekazane do komponentu
     */
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.logout();
    }

    /**
     * Metoda powoduje wylogowanie użytkownika
     * @public
     */
    logout() {
        localStorage.removeItem("authToken");
        Utils.setAuthorizationHeader();
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie widoku edycji oddziału
     * @returns {XML}
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">
                    <i className="fa fa-sign-out text-muted mr-1" aria-hidden="true"></i>
                    Wylogowywanie
                </p>
                <Row>
                    <Col md={6}>
                        <div className="alert alert-success" role="alert">
                            Pomyślnie wylogowano użytkownika.
                        </div>
                        <p>Chcesz się zalogować ponownie? Kliknij tutaj: <Link to="/login">Logowanie</Link></p>
                        <p>Lub <Link to="/">tutaj</Link>, aby wrócić do strony głównej</p>
                    </Col>
                </Row>
            </Container>
        );
    }
}