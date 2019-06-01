// Base features

$(function() {
    $( "[data-role='navbar']" ).navbar();
    $( "[data-role='header'], [data-role='footer']" ).toolbar();
    $( "[data-role='panel']" ).panel();
});

// Update the contents of the toolbars
$( document ).on( "pagecontainershow", function() {
    // A really ugly interval hack to make the toolbars actually update
    setInterval(function (){
        // Each of the four pages in this demo has a data-title attribute
        // which value is equal to the text of the nav button
        // For example, on first page: <div data-role="page" data-title="Info">
        var current = $( ".ui-page-active" ).jqmData( "title" );
        // Change the heading
        $( "[data-role='header'] h1" ).text( current );
        // Remove active class from nav buttons
        $( "[data-role='navbar'] a.ui-btn-active" ).removeClass( "ui-btn-active" );
        // Add active class to current nav button
        $( "[data-role='navbar'] a" ).each(function() {
            if ( $( this ).text() === current ) {
                $( this ).addClass( "ui-btn-active" );
            }
        });
    }, 50);
});

// Calculator
$(document).on("pagecreate", "#calculator", function() {
    $("#add").click(function() {
        var a = parseFloat($("#a").val());
        var b = parseFloat($("#b").val());
        $("#tulos").text(a+b);
    })
    $("#subtract").click(function() {
        var a = parseFloat($("#a").val());
        var b = parseFloat($("#b").val());
        $("#tulos").text(a-b);
    })
    $("#multiply").click(function() {
        var a = parseFloat($("#a").val());
        var b = parseFloat($("#b").val());
        $("#tulos").text(a*b);
    })
    $("#divide").click(function() {
        var a = parseFloat($("#a").val());
        var b = parseFloat($("#b").val());
        $("#tulos").text(a/b);
    })
});

// Camera
$(document).on("pagecreate", "#camera", function() {
    var video = document.querySelector("#videoElement");
    
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;
    
    if (navigator.getUserMedia) {       
        navigator.getUserMedia({video: true}, handleVideo, videoError);
    }
    
    function handleVideo(stream) {
        video.src = window.URL.createObjectURL(stream);
    }
    
    function videoError(e) {
        // do something
    }
});


// Weather

$(document).on("pagecreate", "#weather", function() {
    $.ajax({
        url: "http://api.openweathermap.org/data/2.5/weather?lang=en&q=helsinki&units=metric&APPID=46713cb5e31153739aee425dd2d14d5d",
        dataType: "json",
        timeout: 5000
    })
    .done(function(data) {
        var description = data.weather[0].description;
        $("#säätiedot-kaupunki").text(data.name);
        $("#säätiedot-kuvaus").text(description.charAt(0).toUpperCase() + description.slice(1));
        $("#säätiedot-lämpötila").text(data.main.temp + " °C");
        $("#säätiedot-tuuli").text(data.wind.speed + " m/s");
        $("#säätiedot-pilvisyys").text(data.clouds.all + " %");
    })
});


// Distance
$(document).on("pagecreate", "#distance", function() {
    $(function() {
        $("#etäisyys-lomake").on('submit',function(e) {
            e.preventDefault();
            haeEtäisyys();
        });
    });

    function haeEtäisyys() {
        var mistä = $("#etäisyys-mistä").val();
        var minne = $("#etäisyys-minne").val();
        $.ajax({
            url: "https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics&origins=" + encodeURIComponent(mistä) +"&destinations=" + encodeURIComponent(minne) + "&key=AIzaSyBdfg3Xc2VZbX5RRl1lMSRM-HaoJ5S6j-Q",
            dataType: "json",
            timeout: 5000
        })
        .done(function(data) {
            $("#etäisyys-tuloste").text("");
            if (typeof(data.rows[0]) != "undefined") {
                if (typeof(data.rows[0].elements[0].distance) != "undefined") {
                    $("#etäisyys-tuloste").append("<p>Matkan pituus on " + data.rows[0].elements[0].distance.text
                    + " ja matka-aika on " + data.rows[0].elements[0].duration.text + ".</p>");
                    $("#etäisyys-tuloste").append('<iframe class="map-top" id="kartta"'
                    + 'src="https://www.google.com/maps/embed/v1/directions'
                    + '?key=AIzaSyBdfg3Xc2VZbX5RRl1lMSRM-HaoJ5S6j-Q'
                    + '&origin=' + encodeURIComponent(mistä)
                    + '&destination=' + encodeURIComponent(minne)
                    + '"></iframe>');
                } else {
                    $("#etäisyys-tuloste").text("Ei tuloksia");
                }
            } else {
                $("#etäisyys-tuloste").text("Ei tuloksia");
            }
        });
    }
});


// Form

$(document).on("pagecreate", "#form", function() {
    $("#lomake").validate({
        errorPlacement: function(error, element) {
            error.appendTo(element.parent("div").prev("label"));
        }
    });

    $("#kayttajatunnus").rules( "add", {
        required: true,
        minlength: 10,
        maxlength: 30
    });

    $("#sahkoposti").rules( "add", {
        required: true,
        email: true
    });

    $("#salasana").rules( "add", {
        required: true,
        minlength: 6
    });

    $("#salasana-vahvista").rules( "add", {
        required: true,
        minlength: 6,
        equalTo: "#salasana"
    });
});