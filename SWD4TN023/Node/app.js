var http = require('http');
var settings = require('./settings');

var server = http.createServer(function (request, response){

	if (request.url === '/') {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(settings.content);
	}

	else if (request.url === '/api') {
		response.writeHead(200, {'Content-Type': 'application/json'});
		var obj = {
			firstname: 'John',
			lastname: 'Doe'
		}
		response.end(JSON.stringify(obj));
	}

	else {
		response.writeHead(404);
		response.end();
	}

});

server.listen(settings.port, function() {
	console.log("Server listening on: http://localhost:%s", settings.port);
})