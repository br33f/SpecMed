import React from 'react';
import {Component} from 'react';

import {Container, Row, Col} from 'reactstrap';

import { Card, CardImg, CardText,
    CardTitle, CardSubtitle, CardBlock } from 'reactstrap';

import image1 from '../../assets/images/1.jpg';
import image2 from '../../assets/images/2.jpg';
import image3 from '../../assets/images/3.jpg';

/**
 * Klasa głównej strony aplikacji
 * @augments Component Komponent
 */
export class Main extends Component {
    /**
     * Funkcja odpowiedzialna na generowanie głownej strony serwisu
     */
    render() {
        return (
            <Container fluid={true}>
                <p className="contentTitle">Kadra pracownicza</p>
                <Row>
                    <Col lg={2} md={3} sm={4} xs={12}>
                        <Card>
                            <CardImg top width="100%" src={image2} />
                            <CardBlock>
                                <CardTitle>Marzena Kowalska</CardTitle>
                                <CardSubtitle>Pediatra</CardSubtitle>
                                <CardText>Specjalistka z doskonałym kontaktem z dziećmi.</CardText>
                            </CardBlock>
                        </Card>
                    </Col>
                    <Col lg={2} md={3} sm={4} xs={12}>
                        <Card>
                            <CardImg top width="100%" src={image1} />
                            <CardBlock>
                                <CardTitle>Mariusz Biały</CardTitle>
                                <CardSubtitle>Onkolog</CardSubtitle>
                                <CardText>Profesjonalny, pewny siebie lekarz z wieloletnim doświadczeniem.</CardText>
                            </CardBlock>
                        </Card>
                    </Col>
                    <Col lg={2} md={3} sm={4} xs={12}>
                        <Card>
                            <CardImg top width="100%" src={image3} />
                            <CardBlock>
                                <CardTitle>Jan Tornado</CardTitle>
                                <CardSubtitle>Chirurg</CardSubtitle>
                                <CardText>Profesjonalnizm to jego drugie imie, pewna ręka i lata doświadczenia.</CardText>
                            </CardBlock>
                        </Card>
                    </Col>
                </Row>

                <p className="contentTitle mt-4">Dlaczego my?</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ullamcorper gravida condimentum. Pellentesque pulvinar tincidunt maximus. Nullam at metus erat. Sed sagittis venenatis nibh, et blandit mi tempor non. Integer sollicitudin metus eget urna malesuada suscipit id et ex. Fusce sit amet metus cursus, tempus nulla consequat, egestas justo. In accumsan mi ante, id accumsan sem efficitur lacinia. Integer quis condimentum metus. Nunc nec tincidunt arcu.

                    Maecenas quis bibendum nulla. Duis auctor a elit eget pulvinar. Integer vitae cursus augue, in malesuada risus. Praesent dapibus nec nunc sed tincidunt. Nunc laoreet vel metus a tincidunt. Vestibulum tortor orci, ultrices in iaculis vitae, vestibulum sit amet nunc. Aenean feugiat scelerisque elementum. Donec mauris dolor, dignissim ut commodo at, hendrerit et nisl. Duis interdum orci urna, sit amet euismod nisi luctus blandit. Morbi porta faucibus est, sed faucibus metus.</p>

                <p className="contentTitle mt-4">Atrakcyjne pakiety ubezpiećzeń</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ullamcorper gravida condimentum. Pellentesque pulvinar tincidunt maximus. Nullam at metus erat. Sed sagittis venenatis nibh, et blandit mi tempor non. Integer sollicitudin metus eget urna malesuada suscipit id et ex. Fusce sit amet metus cursus, tempus nulla consequat, egestas justo. In accumsan mi ante, id accumsan sem efficitur lacinia. Integer quis condimentum metus. Nunc nec tincidunt arcu.

                    Maecenas quis bibendum nulla. Duis auctor a elit eget pulvinar. Integer vitae cursus augue, in malesuada risus. Praesent dapibus nec nunc sed tincidunt. Nunc laoreet vel metus a tincidunt. Vestibulum tortor orci, ultrices in iaculis vitae, vestibulum sit amet nunc. Aenean feugiat scelerisque elementum. Donec mauris dolor, dignissim ut commodo at, hendrerit et nisl. Duis interdum orci urna, sit amet euismod nisi luctus blandit. Morbi porta faucibus est, sed faucibus metus.</p>


            </Container>
        );
    }
}