const dataMapper = {

  mapEntries(self, data) {
    const mappedData = [];

    data.forEach(function(dataRow) {
      const user = dataRow.user
      const starttime = dataRow.starttime
      const endtime = dataRow.endtime
      const breaks = dataRow.breaks
      const description = dataRow.description
      const _id = dataRow._id

      const mappedDataRow = {
        user: user,
        starttime: new Date(starttime),
        endtime: new Date(endtime),
        breaks: parseInt(breaks),
        description: description,
        _id: _id
      }

      mappedData.push(mappedDataRow);
    }, this);

    self.setState({
      loading: false,
      entries: mappedData
    })
  },

  setAuth(data) {
    this.setState({
      token: data.token,
      admin: data.admin
    })
  }

}

export default dataMapper;