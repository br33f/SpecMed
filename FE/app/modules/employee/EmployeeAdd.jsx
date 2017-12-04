import React from 'react';
import {Component} from 'react';
import {Container, FormGroup, Input, Label} from 'reactstrap';
import {PostPageableTable} from '../../components/PostPageableTable/PostPageableTable.jsx';



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

                <FormGroup>
                    <Label for="exampleEmail">Zwykły input</Label>
                    <Input type="text" name="jakistam" id="musiBycUnikalne" placeholder="with a placeholder" />
                </FormGroup>


                {/*<p className="contentTitle">Lista pracowników</p>*/}
                {/*<PostPageableTable*/}
                    {/*headerDefinition={this.getHeaderDefinition()}*/}
                    {/*dataUrl="/employee/list"*/}
                {/*/>*/}

            </Container>
        );
    }

}