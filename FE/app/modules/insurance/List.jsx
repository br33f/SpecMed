import React from 'react';
import {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container} from 'reactstrap';
import {PostPageableTable} from '../../components/post-pageable-collection/PostPageableTable.jsx';

export class InsuranceList extends Component {
    constructor(props) {
        super(props);
    }

    generateEditLink(insuranceId) {
        let editUrl = `/insurance/edit/${insuranceId}`;
        return (
            <Link to={editUrl}>
                <i className="fa fa-pencil-square fa-lg" aria-hidden="true"></i>
            </Link>
        );
    }

    getHeaderDefinition() {
        return [
            {key: 'name', label: 'Nazwa ubezpieczenia', sortable: true},
            {key: 'price', label: 'Cena ubezpieczenia', sortable: true},
            {key: 'notes', label: 'Opis'},
            {key: 'insurancePolicyNumber', label: 'Numer polisy ubezpieczeniowej', sortable: true},
            {key: 'id', label: 'Edycja', format: {type: 'custom', fn: this.generateEditLink}}
        ];
    }

        render()
        {
            return (
                <Container fluid={true}>
                    <p className="contentTitle">Lista ubezpieczeń</p>
                    <PostPageableTable
                        headerDefinition={this.getHeaderDefinition()}
                        dataUrl="/insurance/list"
                    />

                </Container>
            );
        }
    }