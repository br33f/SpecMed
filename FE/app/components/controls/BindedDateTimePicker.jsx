import React from 'react';
import {Component} from 'react';
import {DateTimePicker} from 'react-widgets'

export class BindedDateTimePicker extends Component {
    constructor(props){
        super(props);

        this.Form = this.props.form;
    }

    bindDateToModel(date) {
        let event = {
            target: {
                name: this.props.name,
                value: date.getTime()
            }
        };

        this.Form.bindValueToModel(event);
    }

    render() {
        return (
          <div>
              <DateTimePicker
                  id={this.props.id}
                  name={this.props.name}
                  placeholder={this.props.placeholder}
                  defaultValue={this.props.value || new Date(this.Form.state.model.get(this.props.name))}
                  dropUp={this.props.value || true}
                  onChange={this.props.onChange || this.bindDateToModel.bind(this)}
                  time={this.props.time}
                  step={this.props.step || 15}
              />
              {this.Form.getDatepickerValidationFeedbackFor(this.props.name)}
          </div>
        );
    }
}