const mongoose = require('mongoose')
const Schema = mongoose.Schema

module.exports = mongoose.model('entry', new Schema({ 
  user: {
    type: String,
    required: true
  },
  starttime: {
    type: Date,
    required: true
  },
  endtime: {
    type: Date,
    required: true
  },
  breaks: {
    type: Number
  },
  description: {
    type: String
  }
}, {
  versionKey: false
}
))