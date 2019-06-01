/*
	Copyright: Tatu Arvela
*/

/* 
	Application base features
*/

// Initialize Firebase
var config = {
	apiKey: "AIzaSyDqHwgncHUx_2ja-vbdohkr-3WVDQxGFiQ",
	authDomain: "digitekniikat-harjoitus.firebaseapp.com",
	databaseURL: "https://digitekniikat-harjoitus.firebaseio.com",
	storageBucket: "digitekniikat-harjoitus.appspot.com",
	messagingSenderId: "1074786489645"
};
firebase.initializeApp(config);

var auth = firebase.auth();
var storageRef = firebase.storage().ref();
var databaseRef = firebase.database().ref();

$(function() {
	auth.onAuthStateChanged(function(user) {
		if (user) {
			console.log('Anonymous user signed-in.', user);
		} else {
			console.log('There was no anonymous session. Creating a new anonymous user.');
			// Sign the user in anonymously since accessing Storage requires the user to be authorized.
			auth.signInAnonymously();
		}
	});
})


// Lightbox options
lightbox.option({
	'wrapAround': true,
	'albumLabel': 'Kuva %1 / %2',
	'alwaysShowNavOnTouchDevices': true
});


// Initialize app
$(function() {
	mainResize();
	messagesResize();
	loadMessages();
	$('.modal').modal();

	$('ul.tabs').on('click', 'a', function(e) {
		mainResize();
		messagesResize();
		// loadMessages();
		$("#messages").scrollTop($("#messages")[0].scrollHeight);
	});
});

var messages = [];


// Get location
function getLocation(callback) {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(callback, console.log("Paikannusvirhe"));
	} else {
		alert("Laitteesi ei tue paikannusta tai se on pois käytöstä. Viestiäsi ei lähetetty.");
	}
}


// Get messages
function loadMessages() {
	messages = [];

	databaseRef.child('messages').once('value', snap => {
		snap.forEach(item => {
			messages.push({
				key: item.key,
				timestamp: item.child("timestamp").val(),
				message: item.child("message").val(),
				sender: item.child("user").val(),
				coords: item.child("coords").val(),
				address: item.child("address").val(),
				url: item.child("url").val()
			});
		});
		chatLoadMessages();
		galleryLoadImages();
		initMap();
	});
}


// Format timestamps
function timeStampFormatter(timestamp) {
	var year=timestamp.getFullYear(),
		month=timestamp.getMonth()+1,
		date=timestamp.getDate(),
		hour=timestamp.getHours(),
		min=timestamp.getMinutes();
		// sec=timestamp.getSeconds();
	// if(month<=9) month="0"+month
	// if(date<=9) date="0"+date
	// if(hour<=9) hour="0"+hour
	if(min<=9) min="0"+min
	// if(sec<=9) sec="0"+sec;

	// var timestampFormatted = hour + ":" + min + " (" + year + "-" + month + "-" + date + ")";
	var timestampFormatted = date + "." + month + "." + year + " " + hour + ":" + min;
	return timestampFormatted;
}



/*
	Settings
*/

// Initialize settings
$(function() {
	if (!localStorage.getItem("username")) {
		localStorage.setItem("username", "anonyymi");
	}

	$("#username").val(localStorage.getItem("username"));
});


// Save settings
function saveSettings() {
	localStorage.setItem("username", $("#username").val());
}



/*
	Map
*/

// Initialize map
function initMap() {
	getLocation(function(position) {
		var userPosition = {lat: position.coords.latitude, lng: position.coords.longitude};

		var map = new google.maps.Map(document.getElementById('googlemap'), {
			zoom: 20,
			center: userPosition
		});

		var userposition = new google.maps.Marker({
			icon: 'images/gps_fixed.svg',
			position: userPosition,
			map: map
		});

		var infowindow = new google.maps.InfoWindow({
			content: 'Nothing'
		});

		for (var i = 0, len = messages.length; i < len; i++) {
			var html;
			if (messages[i].url) {
				html = '<img class="map-image" src="' + messages[i].url + '" />'
				+ '<strong>' + messages[i].sender + ' ' + timeStampFormatter(new Date(messages[i].timestamp)) + '</strong>' + '<br/>'
				+ messages[i].message
			}
			else {
				html = '<strong>' + messages[i].sender + ' (' + timeStampFormatter(new Date(messages[i].timestamp)) + ')</strong>' + '<br/>'
				+ messages[i].message
			}


			var marker = new google.maps.Marker({
				icon: 'images/message.svg',
				position: {
					lat: messages[i].coords.latitude,
					lng: messages[i].coords.longitude
				},
				map: map,
				html: html
			});

			marker.addListener('click', function() {
				infowindow.setContent(this.html);
				infowindow.open(map, this);
			});
		}

		// Fixes grey map issue
		google.maps.event.addListenerOnce(map, 'idle', function() {
			google.maps.event.trigger(map, 'resize');
		});
	});
}



/*
	Chat
*/


// Load messages to chat
function chatLoadMessages() {
	$("#loading-messages").show();
	$.when($(".message").remove()).then(function() {
		for (var i = 0, len = messages.length; i < len; i++) {

			var key = messages[i].key;
			var timestamp = timeStampFormatter(new Date(messages[i].timestamp));
			var message = messages[i].message;
			var sender = messages[i].sender;
			var address = messages[i].address;
			var coords = messages[i].coords;
			var url = messages[i].url;

			// Compose message row
			var messagerow = '<div class="messagerow">';

			if (sender === localStorage.getItem("username")) {
				messagerow = messagerow 
				+ '<ul class="collapsible card-panel message mymessage" data-collapsible="accordion" data-key="' + key + '">'
				+ '<li>';
			}
			else {
				messagerow = messagerow 
				+ '<ul class="collapsible card-panel message" data-collapsible="accordion" data-key="' + key + '">'
				+ '<li>';
			}

			messagerow = messagerow 
				+ '<div class="collapsible-header grey lighten-2">'
				+ '<span class="message-sender">' + sender + '</span>';

			if (url) {
				messagerow = messagerow 
				+ '<img class="message-image" src="' + url + '" />';
			}

			messagerow = messagerow
				+ '<span class="message-message">' + message + '</span>'
				+ '</div>'
				+ '<div class="collapsible-body grey">'
				+ '<p class="message-info-label">' + 'Lähetetty: '
				+ '<span class="message-info">' + timestamp + '</span></p>'
				+ '<p class="message-info-label">' + 'Osoite: '
				+ '<span class="message-info">' + address + '</span></p>'
				+ '<p class="message-info-label">' + 'Koordinaatit: '
				+ '<span class="message-info">' + coords.latitude + ', ' + coords.longitude + '</span></p>'
				+ '</div>'
				+ '</li>'
				+ '</ul>'
				+ '</div>'

			$("#messages").append(messagerow);
		}

		$("#loading-messages").hide();
		$('.collapsible').collapsible();
		$("#messages").scrollTop($("#messages")[0].scrollHeight);
	});
}


// Select image
$(function() {
	document.getElementById('image').addEventListener('change', handleFileSelect, false);
});

function handleFileSelect(evt) {
	evt.stopPropagation();
	evt.preventDefault();
	imagefile = evt.target.files[0];
}

var imagefile;


// Send chat message
function sendMessage(position){
	if ($("#message").val().length < 1) {
		return false;
	}

	if ($("#message").val().length > 140) {
		alert("Viestisi on liian pitkä (maksimipituus 140 merkkiä).");
		return false;
	}

	// Get location address from coordinates
	$.getJSON( 
		'https://maps.googleapis.com/maps/api/geocode/json?latlng=' + position.coords.latitude + ',' + position.coords.longitude + '&key=' + config.apiKey, 
		function( data ) {
			$("#message").addClass("disabled");
			var message = $("#message").val();
			var time = new Date();
			var address = data.results[0].formatted_address;
			sessionStorage.setItem("imageurl", "");

			// If the message contains an image, upload it to storage
			if (imagefile) {
				var metadata = {'contentType': imagefile.type};
				storageRef.child('images/' + $.now() + '-' + imagefile.name).put(imagefile, metadata).then(function(snapshot) {
					// console.log('Uploaded', snapshot.totalBytes, 'bytes.');
					// console.log(snapshot.metadata);
					sessionStorage.setItem("imageurl", snapshot.downloadURL);
					postMessage();
				}).catch(function(error) {
					console.error('Upload failed:', error);
				});
			}
			else {
				postMessage();
			}

			// Post message to database
			function postMessage() {
				$("#message").removeClass("disabled");
				databaseRef.child('messages').push({
					timestamp: $.now(),
					message: message,
					user: localStorage.getItem("username"),
					address: address,
					coords: {
						accuracy: position.coords.accuracy,
						latitude: position.coords.latitude,
						longitude: position.coords.longitude,
					},
					url: sessionStorage.getItem("imageurl")
				});

				// Reload messages
				loadMessages();

				// Clear fields and cache
				$("#message").val("");
				$("#image").val("");
				imagefile = "";
			}
		}
	);
}



/*
	Images
*/


// Load all messages with image to a gallery
function galleryLoadImages() {
	$("#loading-images").show();
	$(".imagespace").remove();
	for (var i = 0, len = messages.length; i < len; i++) {
		console.log(i, messages[i].url);
		if (messages[i].url) {
			// var key = messages[i].key;
			var timestamp = timeStampFormatter(new Date(messages[i].timestamp));
			var message = messages[i].message;
			var sender = messages[i].sender;
			// var address = messages[i].address;
			// var coords = messages[i].coords;
			var url = messages[i].url;

			// Compose message row
			var image = '<div class="col s6 m3 imagespace">'
			+ '<div class="imagecard card-panel grey lighten-4">'
			+ '<a href="' + url + '" data-lightbox="images" data-title="' + sender + ' (' + timestamp + '): ' + message + '">'
			+ '<img src="' + url + '" class="image"/>'
			+ '</a>'
			+ '</div>';
			+ '</div>';

			$("#images").append(image);
		}
	}

	$("#loading-images").hide();
	$("#images").scrollTop($("#images")[0].scrollHeight);
}



/*
	Resize UI elements
*/

// Event handler

	// Never override the window.onresize function.
	// Instead, create a function to add an Event Listener to the object or element.
	// This checks and incase the listeners don't work, then it overrides the object's function as a last resort.
	// This is the preferred method used in libraries such as jQuery.

	// object: the element or window object
	// type: resize, scroll (event type)
	// callback: the function reference
	
var addEvent = function(object, type, callback) {
	if (object == null || typeof(object) == 'undefined') return;
	if (object.addEventListener) {
		object.addEventListener(type, callback, false);
	} else if (object.attachEvent) {
		object.attachEvent("on" + type, callback);
	} else {
		object["on"+type] = callback;
	}
};


// Resize main container
function mainResize() {
	var newValue = 	$( window ).height() - $( "#nav" ).height();
	$( "#main" ).css('margin-top',$( "#nav" ).height());
	$( "#main" ).height(newValue);
}
addEvent(window, "resize", mainResize);


// Resize messages
function messagesResize() {
	var newValue = 	$( "#main" ).outerHeight() - $( "#messageform" ).outerHeight();
	$( "#messages" ).height(newValue);
}
addEvent(window, "resize", messagesResize);