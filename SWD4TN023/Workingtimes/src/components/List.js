import React, { Component } from 'react';

import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow} from 'material-ui/Table';
import Paper from 'material-ui/Paper';

import ListRow from './ListRow';

class List extends Component {
  render() {
    return (
      <Paper className="table">
        <Table selectable={false}>

          <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
            <TableRow>
              <TableHeaderColumn>Nimi</TableHeaderColumn>
              <TableHeaderColumn>Aloitusaika</TableHeaderColumn>
              <TableHeaderColumn>Lopetusaika</TableHeaderColumn>
              <TableHeaderColumn>Tauot (min)</TableHeaderColumn>
              <TableHeaderColumn>Kuvaus</TableHeaderColumn>
              <TableHeaderColumn></TableHeaderColumn>
            </TableRow>
          </TableHeader>

          <TableBody
            displayRowCheckbox={false}
            stripedRows={true}
            preScanRows={false}
          >

            {/*Form*/}
            <ListRow
              new={true}

              user={this.props.form.user}
              starttime={this.props.form.starttime}
              endtime={this.props.form.endtime}
              breaks={this.props.form.breaks}
              description={this.props.form.description}
              _id={null}

              handleChange={this.props.handleChange}
              handleStartTimeChange={this.props.handleStartTimeChange}
              handleEndTimeChange={this.props.handleEndTimeChange}

              postEntry={this.props.postEntry}
            />

            {/*Entries*/}
            {this.props.entries.map(function(entry, index) {
              return (
              <ListRow
                user={entry.user}
                starttime={entry.starttime}
                endtime={entry.endtime}
                breaks={entry.breaks}
                description={entry.description}
                _id={entry._id}

                key={index}

                handleChange={this.props.handleChange}
                handleStartTimeChange={this.props.handleStartTimeChange}
                handleEndTimeChange={this.props.handleEndTimeChange}

                putEntry={this.props.putEntry}
                deleteEntry={this.props.deleteEntry}
              />
              );
            }.bind(this))}
          </TableBody>

        </Table>
      </Paper>
    );
  }
}

export default List;
