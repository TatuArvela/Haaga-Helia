// BASE PACKAGES

const express = require('express')
const bodyParser = require('body-parser')
const cors = require('cors')
const mongoose = require('mongoose')

const app = express()
const config = require('./config')
const apiRoutes = require('./controllers/api')
const clientRoutes = require('./controllers/client')


// CONFIGURATION

const port = config.port
mongoose.connect(config.database)

app.use(bodyParser.urlencoded({extended: true}))
app.use(bodyParser.json())
app.use(cors())


// ROUTES

app.use('/api', apiRoutes)
app.use(clientRoutes)


// START APPLICATION

const openBrowser = require('react-dev-utils/openBrowser')

app.listen(port, () => {
  console.log('Server listening on port ' + port)
  openBrowser('http://localhost:' + port)
})