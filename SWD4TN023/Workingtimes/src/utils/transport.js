// import React from 'react';
import dataMapper from './dataMapper'

const transport = {

// AUTH

  login(self, callback) {
    fetch('http://localhost:3000/api/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: self.state.username,
        password: self.state.password
      })
    })
    .then(result => result.json())
    .then(json => self.props.setAuth(json))
    .then(function() {
      if(callback) {
        callback();
      }
    })
  },




// READ

  getAllEntries(self, callback) {
    fetch('http://localhost:3000/api/entries', {
      // GET method, JSON content type
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'x-access-token': self.props.token,
      },
    })
    .then(result => result.json())
    .then(json => dataMapper.mapEntries(self, json))
    .then(function() {
      if(callback) {
        callback();
      }
    })
  },

  getEntriesByUser(self, user, callback) {
    fetch('http://localhost:3000/api/entries/user=' + user, {
      method: 'GET',
      headers: {
        'x-access-token': self.props.token,
      }
    })
    .then(result => result.json())
    .then(json => dataMapper.mapEntries(self, json))
    .then(function() {
      if(callback) {
        callback();
      }
    })
  },




// ADD

  postEntry(self, entry, callback) {
    fetch('http://localhost:3000/api/entries', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'x-access-token': self.props.token,
      },
      // Request body
      body: JSON.stringify({
        user: entry.user,
        starttime: entry.starttime.getTime(),
        endtime: entry.endtime.getTime(),
        breaks: parseInt(entry.breaks),
        description: entry.description
      })
    })

    .then(function() {
      if(callback) {
        callback();
      }
    })
  },



// SAVE

  putEntry(self, entry, id, callback) {
    fetch('http://localhost:3000/api/entries/id=' + id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'x-access-token': self.props.token,
      },
      // Request body
      body: JSON.stringify({
        user: entry.user,
        starttime: entry.starttime.getTime(),
        endtime: entry.endtime.getTime(),
        breaks: parseInt(entry.breaks),
        description: entry.description
      })
    })

    .then(function() {
      if(callback) {
        callback();
      }
    })
  },



// DELETE

  deleteEntry(self, id, callback) {
    fetch('http://localhost:3000/api/entries/id=' + id, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'x-access-token': self.props.token,
      },
    })
    .then(function() {
      if(callback) {
        callback();
      }
    })
  },

};

export default transport;