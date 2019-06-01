import React, { Component } from 'react';

import {TableRow, TableRowColumn} from 'material-ui/Table';
import TextField from 'material-ui/TextField';
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';
import RaisedButton from 'material-ui/RaisedButton';

import AddBox from 'material-ui/svg-icons/content/add-box';
import Save from 'material-ui/svg-icons/content/save';
import DeleteForever from 'material-ui/svg-icons/action/delete-forever'; 

class ListRow extends Component {
  render() {
    // Ugly hack for Material-UI Tables, boo!
    const otherProps = Object.assign({}, this.props)
    delete otherProps.new
    delete otherProps.user
    delete otherProps.starttime
    delete otherProps.endtime
    delete otherProps.breaks
    delete otherProps.description
    delete otherProps._id
    delete otherProps.onStartTimeChange
    delete otherProps.onEndTimeChange
    delete otherProps.postEntry
    delete otherProps.putEntry
    delete otherProps.deleteEntry
    delete otherProps.handleChange
    delete otherProps.handleStartTimeChange
    delete otherProps.handleEndTimeChange

    return (
      <TableRow {...otherProps}>

        <TableRowColumn>
          <TextField
            fullWidth={true}
            name="user"
            value={this.props.user}
            onChange={ (event, value) => this.props.handleChange(event, value, this.props._id) }
          />
        </TableRowColumn>

        <TableRowColumn>
          <DatePicker
            fullWidth={true}
            name="starttime"
            value={this.props.starttime}
            onChange={ (event, value) => this.props.handleStartTimeChange(event, value, this.props._id) }
            DateTimeFormat={global.Intl.DateTimeFormat}
            locale="fi"
            okLabel="OK"
            cancelLabel="Peruuta"
          />
          <TimePicker
            fullWidth={true}
            name="starttime"
            value={this.props.starttime}
            onChange={ (event, value) => this.props.handleStartTimeChange(event, value, this.props._id) }
            format="24hr"
            okLabel="OK"
            cancelLabel="Peruuta"
          />
        </TableRowColumn>

        <TableRowColumn>
          <DatePicker
            fullWidth={true}
            name="endtime"
            value={this.props.endtime}
            onChange={ (event, value) => this.props.handleEndTimeChange(event, value, this.props._id) }
            DateTimeFormat={global.Intl.DateTimeFormat}
            locale="fi"
            okLabel="OK"
            cancelLabel="Peruuta"
          />
          <TimePicker
            fullWidth={true}
            name="endtime"
            value={this.props.endtime}
            onChange={ (event, value) => this.props.handleEndTimeChange(event, value, this.props._id) }
            format="24hr"
            okLabel="OK"
            cancelLabel="Peruuta"
          />
        </TableRowColumn>

        <TableRowColumn>
          <TextField
            fullWidth={true}
            name="breaks"
            value={this.props.breaks}
            onChange={ (event, value) => this.props.handleChange(event, value, this.props._id) }
            type="number"
          />
        </TableRowColumn>

        <TableRowColumn>
          <TextField
            fullWidth={true}
            name="description"
            value={this.props.description}
            onChange={ (event, value) => this.props.handleChange(event, value, this.props._id) }
            rows={4}
            multiLine={true}
          />
        </TableRowColumn>

        {this.props.new &&
          <TableRowColumn>
            <RaisedButton
              icon={<AddBox/>}
              primary
              onClick={this.props.postEntry}
              className="list-button"
            />
          </TableRowColumn>
        }

        {!this.props.new &&
          <TableRowColumn>
            <RaisedButton
              icon={<Save/>}
              primary
              onClick={ () => this.props.putEntry(this.props._id) }
              className="list-button"
            />
            <RaisedButton
              icon={<DeleteForever/>}
              secondary
              onClick={ () => this.props.deleteEntry(this.props._id) }
              className="list-button"
            />
          </TableRowColumn>
        }

      </TableRow>
    );
  }
}

export default ListRow;