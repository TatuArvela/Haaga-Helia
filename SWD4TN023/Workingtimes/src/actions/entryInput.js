const entryInput = {

  handleChange(event, value, id) {
    // New entry form
    if (id === null) {
      let newState = this.state
      let target = event.target.name
      newState.form[target] = value
      this.setState(newState)
    }
    // Existing entries
    else {
      let newState = this.state
      let target = event.target.name
      for (var i = 0, len = newState.entries.length; i < len; i++) {
        if (newState.entries[i]._id === id) {
          newState.entries[i][target] = value
        }
      }
      this.setState(newState)
    }
  },


  newDate(previousValue, value) {
    // If the DatePicker was used, preserve the hours and minutes
    if(
      (previousValue.getFullYear() !== value.getFullYear()) ||
      (previousValue.getMonth() !== value.getMonth()) ||
      (previousValue.getDate() !== value.getDate()) 
    ) {
      const newDate = new Date(
        value.getFullYear(),
        value.getMonth(),
        value.getDate(),
        previousValue.getHours(),
        previousValue.getMinutes(),
        0,
        0
      );
      return newDate
    }
    else {
      return value
    }
  },


  // Material-UI:s Datepicker returns a null event for no particular reason, so I have to create a nearly identical function for each date input. Yee.
  handleStartTimeChange(event, value, id) {
    if (id === null) {
      let newState = this.state
      newState.form.starttime = entryInput.newDate(newState.form.starttime, value)
      this.setState(newState)
    }
    else {
      let newState = this.state
      for (var i = 0, len = newState.entries.length; i < len; i++) {
        if (newState.entries[i]._id === id) {
          newState.entries[i].starttime = entryInput.newDate(newState.entries[i].starttime, value)
        }
      }
      this.setState(newState)
    }
  },


  handleEndTimeChange(event, value, id) {
    if (id === null) {
      let newState = this.state
      newState.form.endtime = entryInput.newDate(newState.form.endtime, value)
      this.setState(newState)
    }
    else {
      let newState = this.state
      for (var i = 0, len = newState.entries.length; i < len; i++) {
        if (newState.entries[i]._id === id) {
          newState.entries[i].endtime = entryInput.newDate(newState.entries[i].endtime, value)
        }
      }
      this.setState(newState)
    }
  },

}

export default entryInput