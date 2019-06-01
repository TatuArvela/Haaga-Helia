import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import AppBar from 'material-ui/AppBar';

import List from '../components/List';

import entries from '../actions/entries';
import entryInput from '../actions/entryInput';

import theme from '../styles/theme';

class Admin extends Component {
  constructor(props) {
    super(props);

    // Main functions
    this.getAllEntries = entries.getAllEntries.bind(this);
    this.getEntriesByUser = entries.getEntriesByUser.bind(this);
    this.postEntry = entries.postEntry.bind(this);
    this.putEntry = entries.putEntry.bind(this);
    this.deleteEntry = entries.deleteEntry.bind(this);
    this.emptyForm = entries.emptyForm.bind(this);

    // Input handlers
    this.handleChange = entryInput.handleChange.bind(this);
    this.handleStartTimeChange = entryInput.handleStartTimeChange.bind(this);
    this.handleEndTimeChange = entryInput.handleEndTimeChange.bind(this);

    // Initialization
    this.state = {
      loading: true,
      form: {
        user: "",
        starttime: new Date(),
        endtime: new Date(),
        description: ""
      }
    }

    this.title = "Työajan kirjaus | Hallinta"
  }

  componentWillMount() {
    this.getAllEntries(this);
    document.title = this.title;
  }

  render() {
    return (
      !this.state.loading &&
      <MuiThemeProvider muiTheme={theme}>
        <div id="view">
          <AppBar
            title={this.title}
            showMenuIconButton={false}
            className="title"
          />
          <div className="container">
            <List
              form={this.state.form}
              entries={this.state.entries}

              postEntry={this.postEntry}
              putEntry={this.putEntry}
              deleteEntry={this.deleteEntry}

              handleChange={this.handleChange}
              handleStartTimeChange={this.handleStartTimeChange}
              handleEndTimeChange={this.handleEndTimeChange}
            />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default Admin;