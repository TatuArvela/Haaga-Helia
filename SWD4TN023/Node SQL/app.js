// projektin aloittamiseksi annettiin komennot
// npm init
// npm install express --save
// npm install mysql --save
// npm install ejs --save
// entry-point muutettu app.js nyt ennen index.js

// Meaning of rest: HTTP verbs and URL's mean something

var express = require('express');
var app = express();

var bodyParser = require('body-parser')

var mysql = require('mysql')
var connection = mysql.createConnection({
  host: 'localhost',
  user: 'tatu',
  password: 'RottaRaketti',
  database: 'tatu'
});
connection.connect()

var apiController = require('./controllers/apiController');
var htmlController = require('./controllers/htmlController');

var port = process.env.PORT || 3000;

app.use(bodyParser.urlencoded({extended: true}))
app.use(bodyParser.json())

app.use('/assets', express.static(__dirname + '/public'));

app.set('view engine', 'ejs');

app.use('/', function(req, res, next) {
    console.log('Request Url:' + req.url);
    next();
});
htmlController(app);
apiController(app, connection);
app.listen(port);