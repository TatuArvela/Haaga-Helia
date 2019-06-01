const express = require('express')
var request = require('request');

const app = express()

var port = process.env.PORT || 4000;

// var restaurant = "3238";
// var restaurant = "0083";

app.get('/:id', function (req, res) {
  request.get('http://www.amica.fi/modules/json/json/Index?costNumber=' + req.params.id + '&language=fi&lastDay=2017-12-31', function(err, response, body) {
    if (!err && response.statusCode == 200) {
      res.setHeader('Content-Type', 'application/json');
      res.setHeader('Access-Control-Allow-Origin', '*');
      res.send(body);
    }
  })
})

app.listen(port, function () {
  console.log('Example app listening on port ' + port + '!')
})