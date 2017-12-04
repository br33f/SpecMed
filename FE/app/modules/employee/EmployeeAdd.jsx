import React from 'react';
import {Component} from 'react';
import {Container, FormGroup, Input, Label} from 'reactstrap';
import {PostPageableTable} from '../../components/PostPageableTable/PostPageableTable.jsx';
import {Button} from "reactstrap/lib/index";



export class EmployeeAdd extends Component {

    constructor(props) {
        super(props);
    }

    getHeaderDefinition() {
        return [
            {key: 'personalData.name', label: 'Imię pracownika', sortable: true},
            {key: 'personalData.surname', label: 'Nazwisko pracownika', sortable: true},
            {key: 'personalData.pesel', label: 'PESEL'},
            {key: 'personalData.birthday', label: 'Urodziny', format: 'date'},
            {key: 'personalData.gender', label: 'Płeć', format: {type: 'dictionary', dictionaryName: "GENDER"}},
        ];
    }

    render() {
        return (
            <Container fluid={true}>
                <h4>Dodaj pracownika</h4>

                <FormGroup>
                    <Label for="exampleEmail">Imię</Label>
                    <Input type="text" name="jakistam" id="musiBycUnikalne" />
                </FormGroup>

                <FormGroup>
                    <Label for="exampleEmail">Nazwisko</Label>
                    <Input type="text" name="jakistam" id="musiBycUnikalne" />
                </FormGroup>

                <FormGroup>
                    <Label for="exampleEmail">PESEL</Label>
                    <Input type="text" name="jakistam" id="musiBycUnikalne" />
                </FormGroup>

                <FormGroup>
                    <Label for="exampleEmail">Data urodzenia</Label>
                    <Input type="text" name="jakistam" id="musiBycUnikalne" />
                </FormGroup>

                <FormGroup>
                    <Label for="exampleEmail">Płeć</Label>
                    <Input type="select" name="select" id="exampleSelect">
                        <option>Mężczyzna</option>
                        <option>Kobieta</option>
                        <option>Mężczyzna ale identyfikuje się jako kobieta</option>
                        <option>Kobieta ale identyfikuje się jako mężczyzna</option>
                        <option>Nieokreślono</option>
                    </Input>                </FormGroup>

                <Button color="primary">Zatwierdź</Button>{'     '}
                <Button color="primary">Anuluj</Button>



            </Container>
        );
    }

}