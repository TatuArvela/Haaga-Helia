const mongoose = require('mongoose')
const Schema = mongoose.Schema

module.exports = mongoose.model('user', new Schema({ 
  name: {
    type: String,
    required: true
  },
  username: {
    type: String,
    required: true,
    unique: true
  },
  password: {
    type: String,
    required: true
  },
  admin: {
    type: Boolean,
    required: true
  }
}, {
  versionKey: false
}
))