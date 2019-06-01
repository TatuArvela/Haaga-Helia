/* 
/  BASIC APP FEATURES
*/
var app = {
    initialize: function() {
        this.bindEvents();
    },
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
    },
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
    start();
        //console.log('Received Event: ' + id);
    }
};

if (!window.device) {
  $(function() {
    start();
  });
}

// Once the device is ready, start the application
function start() {
  // Set title
  $("#wdata-city").text("Sunflower");
  
  // Start the clock
  clock();
  setInterval(clock,10000);
  
  // Track settings
  $("#setting-animation").on("change", function(){
    if ($(this).val() == "off") {
      clearTimeout(canvasTimeout);
      canvasResize();
    }
    if ($(this).val() == "on") {
      canvasTimeout = setTimeout(canvasLoop, 1000/fps);
    }
  });
  $("#setting-fps").on("change keyup paste click", function(){
    fps = $(this).val();
  });
  $("#setting-scale").on("change keyup paste click", function(){
    scale = $(this).val()/100;
  });
  
  // Get position via geolocation
  getPosition();
  $("#title").click(function() {
    getPosition();
  });
  $("#title").children().andSelf().css("cursor", "pointer");
}

// Swipe right to open settings menu
$( document ).on( "pageinit", "#main", function() {
    $( document ).on( "swiperight", "#main", function( e ) {
        // We check if there is no open panel on the page because otherwise
        // a swipe to close the left panel would also open the right panel (and v.v.).
        // We do this by checking the data that the framework stores on the page element (panel: open).
        if ( $.mobile.activePage.jqmData( "panel" ) !== "open" ) {
            if ( e.type === "swiperight" ) {
                $( "#settings" ).panel( "open" );
            }
        }
    });
});

// Clock
function clock() {
  var d = new Date();
  var nhour=d.getHours(),
    nmin=d.getMinutes();
  if(nhour<=9) nhour="0"+nhour;
  if(nmin<=9) nmin="0"+nmin;
  
  $("#time").html(nhour+":"+nmin);
}

// Find position and fetch weather information
function getPosition() {
  $("#wdata-city").text("Locating...");
  navigator.geolocation.getCurrentPosition(
    getWeather,
    geoError, 
    {timeout: 5000});
}


/* 
/  WEATHER
*/
var city, humidity, visibility, wind, temperature, clouds;
var sunrise, sunset, sunposition;
var moonrise, moonset, moonposition, moonphase;
var season;

// Fetch weather data from OpenWeatherMap
function getWeather(position) {
  var key = "56763be4b76dc65e10443c6df535d4fc";
  var query = "https://api.openweathermap.org/data/2.5/weather?lang=fi&lat=" + position.coords.latitude + "&lon=" + position.coords.longitude + "&units=metric&APPID=" + key;
  var error = false;
  
  $("#wdata-city").text("Fetching weather...");
  setTimeout(
    function() {
      if ($("#wdata-city").text() == "Fetching weather...") {
        $("#wdata-city").text("Internal error");
      }
    },
    30*1000);
  $.getJSON(query, function(data) {
    city = data.name;
    humidity = data.main.humidity;
    visibility = data.visibility;
    wind = data.wind.speed;
    temperature = data.main.temp;
    clouds = data.clouds.all;

    var time = new Date();
    var suntimes = SunCalc.getTimes(time, position.coords.latitude, position.coords.longitude);
    sunrise = suntimes.sunrise;
    sunset = suntimes.sunset;
    sunposition = (time - sunrise)/(sunset - sunrise);
    if (sunposition < 0) {
      sunposition = 0;
    } else if (sunposition > 1) {
      sunposition = 1;
    }

    var moontimes = SunCalc.getMoonTimes(time, position.coords.latitude, position.coords.longitude);
    moonrise = moontimes.rise;
    moonset = moontimes.set;
    // A bug in SunCalc: The script gives the set and rise values within the same date,
    // so it often gives the moonset value for the moon from the previous date
    if (moonset < moonrise) {
      moonset += 86400000;
    }
    moonphase = SunCalc.getMoonIllumination(time).phase;
    moonposition = (time - moonrise)/(moonset - moonrise);
    if (moonposition < 0) {
      moonposition = 0;
    } else if (moonposition > 1) {
      moonposition = 1;
    }

    getSeason();

    document.title = city + " - Sunflower";
    drawInformation();
    canvasInit();
  }).fail(function() {
    error = true;
  });
  if (error) {
    city = "Connection error";
    drawInformation();
    canvasInit();
  }
}

// Handle geolocation error
function geoError() {
  city = "Geolocation error";
  drawInformation();
  canvasInit();
}

// Populate weather information bar
function drawInformation() {
  $("#wdata-city").text(city);
  $("#wdata-humidity").html(humidity);
  $("#wdata-wind").html(wind);
  $("#wdata-temperature").html(temperature);
  if (clouds === 0 || clouds === null) {
    $("#wdata-clouds").html("???");
  }
  else {
    $("#wdata-clouds").html(clouds);
  }
}

// Determine current season
function getSeason() {
  var d = new Date();
  var month = d.getMonth() + 1;
  if (month == 12 || month == 1 || month == 2) {
    season = "winter";
  }
  if (month == 3 || month == 4 || month == 5) {
    season = "spring";
  }
  if (month == 6 || month == 7 || month == 8) {
    season = "summer";
  }
  if (month == 9 || month == 10 || month == 11) {
    if (temperature < 0) {
      season = "winter";
    }
    else {
      season = "autumn";
    }
  }
}


/*
/  ANIMATION CANVAS
*/
var canvas;
var context;
var scale = 1;
var fps = 60;
var canvasRefreshInterval = false;
var canvasTimeout;
var test = 0;


function canvasInit() {
  // Check if the canvas is already initialized
  if (canvasRefreshInterval === false) {
    // Initialize animation canvas
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    canvasResize();
    window.onresize = function(event) {
      canvasResize();
    };

    // Keep animation canvas fresh and looping
    canvasRefreshInterval = setInterval(canvasResize, 1000*60);
    canvasTimeout = setTimeout(canvasLoop, 1000/fps);
  }
}

function canvasResize() {
    canvas.width = $(window).width();
  canvas.height = $(window).height();
}

function canvasLoop() {
  context.clearRect(0, 0, canvas.width, canvas.height);
  loadGraphics();
  
  animate();
  drawSky();
  drawSun();
  drawMoon();
  drawClouds();
  drawGround();
  drawPlant();
  drawTint();
  
  clearTimeout(canvasLoop);
  canvasTimeout = setTimeout(canvasLoop, 1000/fps);
}

var ground = new Image();

var plant = new Image();
plant.rotation = 0;
plant.rotationWas = 0;
plant.rotationTarget = 0;
plant.rotationInProgress = false;
plant.OffsetX = 0;
plant.OffsetY = 0;

var flower = new Image();
flower.skew = 0;
flower.skewWas = 0;
flower.skewTarget = 0;
flower.skewInProgress = false;
flower.rotation = 0;

var leaf1 = new Image();
var leaf2 = new Image();
var leaf3 = new Image();
var leaf4 = new Image();
var allLeaves = [leaf1,leaf2,leaf3,leaf4];
for (var j = 0; j < allLeaves.length; j++) {
  allLeaves[j].skew = 0;
  allLeaves[j].skewWas = 0;
  allLeaves[j].skewTarget = 0;
  allLeaves[j].skewInProgress = false;
  allLeaves[j].rotation = 0;
  allLeaves[j].rotationWas = 0;
  allLeaves[j].rotationTarget = 0;
  allLeaves[j].rotationInProgress = false;
}

var plantpot = new Image();

var sun = new Image();
var moon = new Image();

//Clouds / Rain
//  Graphics
//    Rain
//    Snow
//  Show clouds if cloudy
//    Clouds move by wind speed
//  Show rain if raining / snow if snowing
//    Increase opacity by amount of rain
//    Skew by wind speed
var cloud1 = new Image();
var cloud2 = new Image();
var cloud3 = new Image();
var cloud4 = new Image();
var allClouds = [cloud1,cloud2,cloud3,cloud4];
for (var k = 0; k < allClouds.length; k++) {
  allClouds[k].positionX = -1000;
  allClouds[k].positionY = -1000;
}

var filter;
var tint;
var tintAlpha;

// Set graphics
function loadGraphics() {
  // Temperature suffix
  var suffix = "";
  if (temperature <= 0) {
    suffix = "_frozen";
  }
  suffix = "";
  if (!season) {
    season = "summer";
  }
  
  var graphics = [ground, flower, leaf1, leaf2, leaf3, leaf4, plantpot,
          sun, moon, cloud1, cloud2, cloud3, cloud4];
  var graphicsSrc = ["img/ground_" + season + ".svg",
             "img/sunflower" + suffix + ".svg",
             "img/leaf1"+ suffix + ".svg",
             "img/leaf2" + suffix + ".svg",
             "img/leaf3"+ suffix + ".svg",
             "img/leaf4" + suffix + ".svg",
             "img/plantpot" + suffix + ".svg",
             "img/sun.svg",
             "img/moon.svg",
             "img/cloud1.svg",
             "img/cloud2.svg",
             "img/cloud3.svg",
             "img/cloud4.svg"];
  for (var i = 0; i < graphics.length; i++) {
    if (!graphics[i].src.endsWith(graphicsSrc[i])) {
      graphics[i].src = graphicsSrc[i];
    }
  }
}

// Animate objects via offset, skew and rotation
function animate() {
  // Clouds
  for (var i = 0; i < allClouds.length; i++) {
    if (allClouds[i].positionX == -1000) {
      allClouds[i].positionX = Math.floor(Math.random() * canvas.width);
    }
    if (allClouds[i].positionY == -1000) {
      allClouds[i].positionY = Math.floor(Math.random() * canvas.height / 2) - canvas.height / 4;
    }
    if (allClouds[i].positionX > canvas.width) {
      allClouds[i].positionX = -allClouds[i].width * 5;
      allClouds[i].positionY = Math.floor(Math.random() * canvas.height / 2) - canvas.height / 4;
    }
    allClouds[i].positionX += (40 / wind + 10) / fps;
  }
  
  // Whole plant
  if (!plant.rotationInProgress) {
    plant.rotationTarget = Math.floor((Math.random() * 22.5) - 11.25);
    plant.rotationInProgress = true;
  }
  if (plant.rotationInProgress) {
    if (plant.rotation < plant.rotationTarget) {
      plant.rotationWas = plant.rotation;
      plant.rotation += Math.round((Math.random() * 5) + wind / fps * 100) / 1000;
    }
    else if (plant.rotation > plant.rotationTarget) {
      plant.rotationWas = plant.rotation;
      plant.rotation -= Math.round((Math.random() * 5) + wind / fps * 100) / 1000;
    }
    if ((plant.rotation > plant.rotationWas && plant.rotation >= plant.rotationTarget) || 
       (plant.rotation < plant.rotationWas && plant.rotation <= plant.rotationTarget)) {
      plant.rotationInProgress = false;
    }
  }
  
  // Leaves
  function skewLeaf(a) {
    if (!a.skewInProgress) {
      a.skewTarget = Math.round(((Math.random() * 0.4) - 0.2) * 100) / 100;
      a.skewInProgress = true;
    }
    if (a.skewInProgress) {
      if (a.skew < a.skewTarget) {
        a.skewWas = a.skew;
        a.skew += Math.random() * a.skewTarget / wind / fps;
      }
      else if (a.skew > a.skewTarget) {
        a.skewWas = a.skew;
        a.skew -= Math.random() * a.skewTarget / wind / fps;
      }
      if ((a.skew > a.skewWas && a.skew >= a.skewTarget) || 
         (a.skew < a.skewWas && a.skew <= a.skewTarget)) {
        a.skewInProgress = false;
      }
    }
  }
  function rotateLeaf(a) {
    if (!a.rotationInProgress) {
      a.rotationTarget = Math.floor((Math.random() * 22.5) - 11.25);
      a.rotationInProgress = true;
    }
    if (a.rotationInProgress) {
      if (a.rotation < a.rotationTarget) {
        a.rotationWas = a.rotation;
        a.rotation += Math.round((Math.random() * 5) + wind / fps * 100) / 100;
      }
      else if (a.rotation > a.rotationTarget) {
        a.rotationWas = a.rotation;
        a.rotation -= Math.round((Math.random() * 5) + wind / fps * 100) / 100;
      }
      if ((a.rotation > a.rotationWas && a.rotation >= a.rotationTarget) || 
         (a.rotation < a.rotationWas && a.rotation <= a.rotationTarget)) {
        a.rotationInProgress = false;
      }
    }
  }
  for (var j = 0; j < allLeaves.length; j++) {
    skewLeaf(allLeaves[j]);
    rotateLeaf(allLeaves[j]);
  }
  
  // Flower rotation
  while (flower.rotation > 360) {
    flower.rotation = flower.rotation - 360;
  }
  if (fps === 0) {
    flower.rotation += 0;
  } else {
    flower.rotation += 50 * (wind / 10) / fps;
  }
}

// Draw sky
function drawSky() {
  // Create lighting prototype
  function Lighting(sky, saturation, brightness, tint, tintAlpha) {
    this.sky = sky;
    this.saturation = saturation;
    this.brightness = brightness;
    this.tint = tint;
    this.tintAlpha = tintAlpha;
  }
  
  // Create sky lighting sets
  var dawn = new Lighting("linear-gradient(to bottom, rgb(137,214,249) 0%,rgb(255, 147, 96) 100%)", 90, 100, "rgb(255, 147, 96)", 0.3);
  var daytime = new Lighting("linear-gradient(to bottom, rgb(96,201,255) 0%,rgb(137,214,249) 100%)", 100, 100, "rgb(137,214,249)", 0.1);
  var fog = new Lighting("linear-gradient(to bottom, rgb(198,221,223) 0%,rgb(160,198,201) 100%)", 80, 90, "rgb(198,221,223)", 0.4);
  var evening = new Lighting("linear-gradient(to bottom, rgb(137, 146, 249) 0%,rgb(255, 125, 64) 100%)", 90, 100, "rgb(255, 125, 64)", 0.3);
  var night = new Lighting("linear-gradient(to bottom, rgb(7, 8, 29) 0%,rgb(27, 21, 45) 100%)", 30, 30, "rgb(27, 21, 45)", 0.6);
  
  // Determine the time of day
  var d = new Date();
  var time = d.getTime();
  var lighting;
  
  if (time >= sunset) {
    lighting = night;
  }
  if (time < sunset) {
    lighting = evening;
  }
  if (time > sunrise && time < sunset) {
    lighting = daytime;
    if (clouds > 70) {
      lighting = fog;
    }
  }
  if (time <= sunrise) {
    lighting = dawn;
  }
  if (!lighting) {
    lighting = daytime;
  }
  
  // Set sky and effects accordingly
  filter = "saturate(" + lighting.saturation + "%) brightness(" + lighting.brightness + "%)";
  tint = lighting.tint;
  tintAlpha = lighting.tintAlpha;
  $(canvas).css("background", lighting.sky);
}

// Draw ground
function drawGround() {
  var width = canvas.width;
  var height = ground.height * (canvas.width / ground.width);
  context.save();
  context.translate(0, 
            0);
  context.filter = filter;
  context.drawImage(ground,
            canvas.width/2 - width/2,
            canvas.height - height,
            width,
            height);
  context.restore();
}

// Draw sun
function drawSun() {
  context.save();
  context.translate(canvas.width / 2,
            canvas.height);
  context.rotate((-45 + sunposition*180) * Math.PI/180);
  context.globalAlpha = 1 - (clouds * 0.01 / 2);
  context.drawImage(sun,
            2 * -canvas.width / 5,
            2 * -canvas.height / 5,
            sun.width / 2,
            sun.height / 2);
  context.restore();
}

// Draw moon
function drawMoon() {
  context.save();
  context.translate(canvas.width / 2,
            canvas.height);
  context.rotate((-45 + moonposition*180) * Math.PI/180);
  context.globalAlpha = 1 - (clouds * 0.01 / 2);
  context.drawImage(moon,
            2 * -canvas.width / 5,
            2 * -canvas.height / 5,
            moon.width / 2 ,
            moon.height / 2);
  context.restore();
}

// Draw clouds
function drawClouds() {
  function drawCloud(a) {
    context.save();
    context.translate(0, 0);
    context.filter = filter;
    context.globalAlpha = clouds * 0.01 * 1.25;
    context.drawImage(a,
              a.positionX,
              a.positionY,
              a.width * 5,
              a.height * 5);
    context.restore();
  }

  for (var i = 0; i < allClouds.length; i++) {
    drawCloud(allClouds[i]);
  }
}

// Draw plant
function drawPlant() {
  // Plantpot
  context.save();
  context.translate(canvas.width / 2,
            canvas.height / 2);
  context.rotate(plant.rotation * Math.PI/180);
  context.filter = filter;
  context.drawImage(plantpot,
            -((plantpot.width - plant.OffsetX) * scale),
            -((plantpot.height - 100 - plant.OffsetY ) * scale),
            plantpot.width * 2 * scale,
            plantpot.height * 2 * scale);
  context.restore();

  // Leaves
  function drawLeaf(a, translateX, translateY, adjust, size, side) {
    context.save();
    context.translate(canvas.width / 2,
              canvas.height / 2);
    context.rotate(plant.rotation * Math.PI/180);
    context.translate((translateX + plant.OffsetX) * scale,
             (translateY + plant.OffsetY) * scale);
    context.transform(1,
              a.skew,
              a.skew,
              1,
              0,
              0);
    context.rotate(a.rotation * Math.PI/180);
    context.filter = filter;
    context.drawImage(a,
              0,
              -((a.height / 2 + adjust) * scale),
              side * a.width * size * scale,
              a.height * size * scale);
    context.restore();
  }
  for (var i = 0; i < 4; i++) {
    drawLeaf(leaf1, 4, 50, -20, 0.4, 1);
    drawLeaf(leaf2, -4, 30, -50, 0.4, -1);
    drawLeaf(leaf3, -4, 20, 0, 0.6, -1);
    drawLeaf(leaf4, 1, 30, -30, 0.7, 1);
  }
  
  // Flower
  context.save();
  context.translate(canvas.width / 2,
            canvas.height / 2);
  context.rotate(plant.rotation * Math.PI/180);
  context.translate(plant.OffsetX,
            -100 * scale + plant.OffsetY);
  context.transform(-1,
            0.5,
            0,
            1,
            0,
            0);
  context.rotate(flower.rotation * Math.PI/180);
  context.filter = filter;
  context.drawImage(flower,
            -(flower.width * scale / 2),
            -(flower.height * scale / 2),
            flower.width * scale,
            flower.height * scale);
  context.restore();
}

// Draw tint
function drawTint() {
  context.save();
  context.fillStyle = tint;
  context.globalAlpha = tintAlpha;
    context.fillRect(0, 0, canvas.width, canvas.height);
  context.restore();
}
