import React from 'react';
import {Component} from 'react';
import {Pagination, PaginationItem, PaginationLink} from 'reactstrap';

export class TablePagination extends Component {
    changePage(pageNumber) {
        this.props.setPage(pageNumber);
    }

    getPagesList() {
        let pagesList = [];

        let from = 0;
        let to = 0;

        if (this.props.currentPage < 3) {
            // first 2 elements
            from = 0;
            to = 5;
        } else if (this.props.currentPage > this.props.totalPages - 4) {
            // last 2 elements
            from = this.props.totalPages - 6;
            to = this.props.totalPages - 1;
        } else {
            // any other case
            from = this.props.currentPage - 2;
            to = this.props.currentPage + 2;
        }

        for (let candidate = from; candidate <= to; candidate++) {
            if (candidate >= 0 && candidate < this.props.totalPages) {
                pagesList.push(candidate)
            }
        }

        if (pagesList[0] !== 0) {
            pagesList.unshift(0);
        }

        if (pagesList[pagesList.length - 1] !== this.props.totalPages - 1) {
            pagesList.push(this.props.totalPages - 1);
        }

        return pagesList;
    }

    render() {
        let currentPage = this.props.currentPage;
        let totalPages = this.props.totalPages;
        let pagesList = this.getPagesList();

        return (
            <Pagination className="mx-auto">
                <PaginationItem disabled={currentPage <= 0}>
                    <PaginationLink previous
                                    onClick={currentPage > 0 ? this.changePage.bind(this, currentPage - 1) : () => {
                                    }}/>
                </PaginationItem>
                {
                    pagesList.map(i =>
                        <PaginationItem key={i} active={currentPage === i}>
                            <PaginationLink onClick={this.changePage.bind(this, i)}>{i + 1}</PaginationLink>
                        </PaginationItem>
                    )
                }
                <PaginationItem disabled={currentPage >= totalPages - 1}>
                    <PaginationLink next
                                    onClick={currentPage < totalPages - 1 ? this.changePage.bind(this, currentPage + 1) : () => {
                                    }}/>
                </PaginationItem>
            </Pagination>
        );
    }

}