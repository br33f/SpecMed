import React from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
import {Component} from 'react';

import {TableHeader} from './TableHeader.jsx';
import {TableContent} from './TableContent.jsx';
import {TablePagination} from './TablePagination.jsx';

import {Table} from 'reactstrap';
import './postpageablettable.scss';

const DEFAULT_PAGE_SIZE = 10;
const DEFAULT_CURRENT_PAGE = 0;

export class PostPageableTable extends Component {
    constructor(props) {
        super(props);

        this.sortKey = this.props.sortKey;
        this.sortDirection = this.props.sortDirection;
        this.currentPage = this.props.currentPage;
        this.pageSize = this.props.pageSize;

        this.state = {
            totalCount: 0,
            totalPages: 0,
            employeeList: [],
            dictionaries: {}
        };
    }

    componentDidMount() {
        this.fetchData();
        this.fetchDictionaries();
    }

    componentWillReceiveProps(newProps) {
        if (!_.isEqual(this.props, newProps)) {
            this.fetchData(newProps);
        }
    }

    fetchDictionaries() {
        let dictionariesToFetch = this.props.headerDefinition
            .filter(column => column.format && column.format.type === 'dictionary')
            .map(column => column.format.dictionaryName);

        SM.DictionaryManager.getDicts(...dictionariesToFetch).then(dicts => {
           this.setState({
               dictionaries: dicts
            });
        });
    }

    fetchData(props = this.props) {
        axios.post(props.dataUrl, {
            pageCriteria: {
                currentPage: this.currentPage,
                pageSize: this.pageSize,
                sortKey: this.sortKey,
                sortDirection: this.sortDirection
            },
            searchCriteria: _.extend({}, props.searchCriteria)
        }).then(res => {
            this.setState({
                employeeList: res.data.content,
                totalCount: res.data.totalCount,
                totalPages: res.data.totalPages
            });
        });
    }

    setPage(pageNumber) {
        this.currentPage = pageNumber;
        this.fetchData();
    }

    setSorting(sortKey, sortDirection) {
        this.sortKey = sortKey;
        this.sortDirection = sortDirection;
        this.fetchData();
    }

    render() {
        const headerDefinition = this.props.headerDefinition;

        if (this.state.employeeList && this.state.employeeList.length > 0) {
            return (
                <div className="postPageableTable">
                    <Table striped>
                        <TableHeader headerDefinition={headerDefinition} sortingHandler={this.setSorting.bind(this)}/>
                        <TableContent headerDefinition={headerDefinition} employeeList={this.state.employeeList}
                                      dictionaries={this.state.dictionaries}/>
                    </Table>
                    <TablePagination setPage={this.setPage.bind(this)} currentPage={this.currentPage} totalPages={this.state.totalPages} />
                </div>
            );
        } else {
            return (
                <span>Brak danych do wyświetlenia</span>
            )
        }
    }

    static propTypes = {
        pageSize: PropTypes.number,
        currentPage: PropTypes.number,
        sortKey: PropTypes.string,
        sortDirection: PropTypes.string,
        headerDefinition: PropTypes.array.isRequired,
        dataUrl: PropTypes.string.isRequired
    };

    static defaultProps = {
        pageSize: DEFAULT_PAGE_SIZE,
        currentPage: DEFAULT_CURRENT_PAGE
    };
}