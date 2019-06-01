// Tatu Arvela
// jQuery Datepicker
	$(function() {
		$( ".date, #maksupvm" ).datepicker({
			dateFormat: 'yy-mm-dd',
			onSelect: function(){
				$( ".date, #maksupvm" ).datepicker( "hide" );
				laskeKokonaishinta();
			}
		})
	});
	
	$.datepicker.regional['fi'] = {
		closeText: 'Sulje',
		prevText: 'Edellinen',
		nextText: 'Seuraava',
		currentText: 'T&auml;n&auml;&auml;n',
		monthNames: ['Tammikuu','Helmikuu','Maaliskuu','Huhtikuu','Toukokuu',
			'Kes&auml;kuu','Hein&auml;kuu','Elokuu','Syyskuu','Lokakuu',
			'Marraskuu','Joulukuu'],
		monthNamesShort: ['Tammi','Helmi','Maalis','Huhti','Touko',
			'Kes&auml;','Hein&auml;','Elo','Syys','Loka','Marras','Joulu'],
		dayNamesShort: ['Su','Ma','Ti','Ke','To','Pe','Su'],
		dayNames: ['Sunnuntai','Maanantai','Tiistai','Keskiviikko','Torstai',
			'Perjantai','Lauantai'],
		dayNamesMin: ['Su','Ma','Ti','Ke','To','Pe','La'],
		weekHeader: 'Vk',
		      dateFormat: 'dd.mm.yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['fi']);
	
// Hae asiakastiedot
	function haeAsiakastiedot() {
		var asiakastiedot = document.getElementById("asiakas").value.split(",");	
		if (asiakastiedot[1]){
			document.getElementById("asiakastunnus").value = "#" + asiakastiedot[0];
			document.getElementById("osoite").value = asiakastiedot[1];
			document.getElementById("posti").value = asiakastiedot[2] + " " + asiakastiedot[3];
		}
	}
	
// Hae ajoneuvotiedot
	function haeAjoneuvotiedot() {
		var ajoneuvotiedot = document.getElementById("ajoneuvo").value.split(",");
		
		if (ajoneuvotiedot[1]){
			document.getElementById("rekno").value = ajoneuvotiedot[0];
			document.getElementById("vrkhinta").value = parseFloat(ajoneuvotiedot[1]).toFixed(2) + " EUR";
			if (ajoneuvotiedot[2]) {
				document.getElementById("huoltopvm").value = ajoneuvotiedot[2];
			}
			else {
				document.getElementById("huoltopvm").value = "Auton huoltoa ei ole rekisteröity tietokantaan";
			}
		}
	}

// Laske kokonaishinta
	function laskeKokonaishinta() {
		if ($( "#vrkhinta" ).val()) {
			var vuokrauspvm = (Date.parse($( "#vuokrauspvm" ).val()));
			var paattymispvm = (Date.parse($( "#paattymispvm" ).val()));
			var vrkhinta = parseFloat($( "#vrkhinta" ).val().replace(/\D.*/g, ""));
			var kokonaishinta = ((paattymispvm-vuokrauspvm) / 86400000 + 1) * vrkhinta;
			if (isNaN(kokonaishinta) || kokonaishinta < 0) {
				kokonaishinta = 0;
			}
			$( "#kokonaishinta" ).val(kokonaishinta.toFixed(2) + " EUR");
		}
		else {
			$( "#kokonaishinta" ).val("");
		}
	}
	
// Suorita funktiot automaattisesti
	$(function() {
		$( "select, input" ).click(function(){
			$( this ).removeClass("invalid");
			$( "#alert" ).css("display","none");
			laskeKokonaishinta();
		});
		$( "select, input" ).change(function(){
			haeAsiakastiedot();
			haeAjoneuvotiedot();
			laskeKokonaishinta();
		});
		$( "#maksettu" ).change(function(){
			if ($( "#maksupvm" ).attr("disabled")) {
		        $( "#maksupvm" ).removeAttr("disabled");
		        $( "#maksupvm" ).attr("placeholder", "Valitse maksupäivä");
		    } else {
		        $( "#maksupvm" ).attr("disabled", "disabled");
		        $( "#maksupvm" ).removeAttr("placeholder");
		    }
		});
	});
	
// Varmista lomakkeen tiedot
	function varmistaLomake() {
		laskeKokonaishinta();
		var isValid;
	
	// Jos lomakkeessa on virheitä, merkitään ne
	    if (isNaN(Date.parse($( ".date" ).val()))){
	    	$( ".date" ).addClass("invalid");
	    	isValid = false;
	    }
	    if ((Date.parse($( "#vuokrauspvm" ).val()))	> (Date.parse($( "#paattymispvm" ).val()))){
	    	$( ".date" ).addClass("invalid");
	    	isValid = false;
	    }
	    if ($( "#asiakas option:selected" ).text() == "Valitse asiakas") {
			$( "#asiakas" ).addClass("invalid");
			isValid = false;
		}
	    if ($( "#ajoneuvo option:selected" ).text() == "Valitse ajoneuvo") {
			$( "#ajoneuvo" ).addClass("invalid");
			isValid = false;
		}
	    if ($( "#maksettu" ).is(':checked')){
			if (isNaN(Date.parse($( "#maksupvm" ).val()))){
		    	$( "#maksupvm" ).addClass("invalid");
		    	isValid = false;
		    }
		}

//	// Tarkistetaan että lomakkeessa vaaditut arvot ovat olemassa ja valmistellaan ne
		if ($("#asiakastunnus") && (isValid == null)) {
			if (($("#asiakastunnus").val() && $("#rekno").val() && (isValid == null))) {
				isValid = true;
			}
			isValid = true;
		}
		if (isValid == null) {
			isValid = false;
		}
		if (isValid == true) {
			$( "#kokonaishinta" ).val($( "#kokonaishinta" ).val().replace(/[^0-9.,]*/g, ''));
			$( "#asiakastunnus" ).val($( "#asiakastunnus" ).val().replace(/[^0-9.,]*/g, ''));
		}
		
	// Jos löytyi virheitä, keskeytetään lähettäminen
		if (isValid == false) {
			$( "#alert" ).css("display","inline");
		}

		return isValid;
	}