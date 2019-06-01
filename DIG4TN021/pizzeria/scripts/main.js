$(document).on("click", ".focus", function(event){
	event.preventDefault();

	$("html, body").animate({
		scrollTop: $( $.attr(this, "href") ).offset().top - 100
	}, 500);
});

$(function() {
	$( ".accordion" ).accordion({
		animate: false,
		collapsible: true,
		active: false
	});
});

$(function() {
	$( ".tabs" ).tabs();
});

$(function() {
	$( ".datepicker" ).datepicker({
		minDate: 0
	});
});

var i = 0;

$(document).ready(function() {
	$("#varaus").submit(function(e){
		$("#varaus").slideToggle('slow');
		$("#yhteenveto").slideToggle('slow');
		$("#yhteenveto-name").html($("#varaus-name").val());
		$("#yhteenveto-email").html($("#varaus-email").val());
		$("#yhteenveto-tel").html($("#varaus-tel").val());
		$("#yhteenveto-date").html($("#varaus-date").val());
		$("#yhteenveto-time").html($("#varaus-time").val());
		$("#yhteenveto-number").html($("#varaus-number").val());
		$("#yhteenveto-information").html($("#varaus-information").val());
		return false;
	});

	$("#varaus-reset").click(function(e){
		$("#varaus").slideToggle('slow');
		$("#yhteenveto").slideToggle('slow');
		$("#varaus").find("input, textarea").val("");
	});

	$("#otayhteytta").click(function(e){
		if (i++ % 2 == 0) {
			$("#otayhteytta").text("Sulje yhteydenottolomake");
		}
		else {
			$("#otayhteytta").text("Avaa yhteydenottolomake");
		}
		if ($("#viestilahetetty").is(":visible")) {
			$("#viestilahetetty").slideToggle('slow');
		}
		else {
			$("#yhteydenotto").slideToggle('slow');
		}
	});

	$("#yhteydenotto").submit(function(e){
		$("#viestilahetetty").slideToggle('slow');
		$("#yhteydenotto").slideToggle('slow');
		return false;
	});

	$("#uusiviesti").click(function(e){
		$("#viestilahetetty").slideToggle('slow');
		$("#yhteydenotto").slideToggle('slow');
		$("#yhteydenotto").find("input, textarea").val("");
	});
});