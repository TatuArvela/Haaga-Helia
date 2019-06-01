import transport from '../utils/transport';
// import dataMapper from '../utils/dataMapper';

const entries = {

  // Load all entries from the backend
  getAllEntries() {
    transport.getAllEntries(this);
  },


  getEntriesByUser(user) {
    transport.getEntriesByUser(this, user);
  },


  emptyForm() {
    this.setState({
      form: {
        user: "",
        starttime: new Date(),
        endtime: new Date(),
        breaks: 0,
        description: ""
      }
    })
    this.getAllEntries()
  },


  postEntry() {
    transport.postEntry(
      this,
      this.state.form,
      this.emptyForm
    );
  },


  findEntryById(entries, id) { 
    for (var i in entries) {
      if (entries[i]._id === id) {
        return entries[i]
      }
    }
    console.error('findEntryById', 'Error: Found nothing with id ' + id)
  },


  putEntry(id) {
    const entry = entries.findEntryById(this.state.entries, id);
    transport.putEntry(
      this,
      entry,
      id,
      this.getAllEntries
    );
  },


  deleteEntry(id) {
    transport.deleteEntry(
      this,
      id,
      this.getAllEntries
    );
  },

};

export default entries;