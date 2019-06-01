const express = require('express')

const clientRoutes = express.Router()


// ENABLE STATIC FILES
clientRoutes.use('/', express.static('public'))


// MAIN VIEW
clientRoutes.get('/',
  function(req, res) {
    res.sendFile(__dirname + '/public/index.html')
})


module.exports = clientRoutes