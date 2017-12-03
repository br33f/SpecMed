import React from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
import _ from 'lodash';
import {Component} from 'react';
import {TableHeader} from './TableHeader.jsx';
import {Table, Pagination, PaginationItem, PaginationLink} from 'reactstrap';
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
        };
    }

    componentDidMount() {
        this.fetchData();
    }

    fetchData() {
        axios.post('http://localhost:8080/employee/list', {
            pageCriteria: {
                currentPage: this.currentPage,
                pageSize: this.pageSize,
                sortKey: this.sortKey,
                sortDirection: this.sortDirection
            },
            searchCriteria: {
            }
        }).then(res => {
            this.setState({
                employeeList: res.data.content.data,
                totalCount: res.data.content.totalCount,
                totalPages: res.data.content.totalPages
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
        const currentPage = this.currentPage;
        const headerDefinition = this.props.headerDefintion;

        if (this.state.employeeList && this.state.employeeList.length > 0) {
            return (
                <div className="postPageableTable">
                    <Table striped>
                        <TableHeader headerDefinition={headerDefinition} sortingHandler={this.setSorting.bind(this)} />
                        <tbody>
                        {
                            this.state.employeeList.map(employee =>
                                <tr key={employee.id}>
                                    {headerDefinition.map(column =>
                                        <td key={employee.id + '-' + column.key}>{_.get(employee, column.key)}</td>
                                    )}
                                </tr>
                            )
                        }
                        </tbody>
                    </Table>
                    <Pagination className="mx-auto">
                        <PaginationItem disabled={currentPage <= 0}>
                            <PaginationLink previous onClick={currentPage > 0 ? this.setPage.bind(this, currentPage - 1) : () => {}} />
                        </PaginationItem>
                        {
                            Array.apply(0, Array(this.state.totalPages)).map((x, i) =>
                                <PaginationItem key={i} active={ currentPage === i }>
                                    <PaginationLink onClick={this.setPage.bind(this, i)}>{i + 1}</PaginationLink>
                                </PaginationItem>
                            )
                        }
                        <PaginationItem disabled={currentPage >= this.state.totalPages - 1}>
                            <PaginationLink next onClick={currentPage < this.state.totalPages - 1 ? this.setPage.bind(this, currentPage + 1) : () => {}} />
                        </PaginationItem>
                    </Pagination>
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
        headerDefintion: PropTypes.array.isRequired
    };

    static defaultProps = {
        pageSize: DEFAULT_PAGE_SIZE,
        currentPage: DEFAULT_CURRENT_PAGE
    };
}