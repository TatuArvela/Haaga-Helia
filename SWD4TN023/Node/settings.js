var fs = require('fs');
var pug = require('pug');

var helloworld = "vinkeää kivaa";

var html = fs.readFileSync(__dirname + '/index.html', 'utf8');
html = html.replace('{title}', helloworld);
html = html.replace('{content}', helloworld);

var pughtml = pug.renderFile(
	'./index.pug', 
	{
		pretty: true,
		title: helloworld,
		message: helloworld
	}
);

exports.port = "8080";
exports.message = helloworld;
exports.content = pughtml;