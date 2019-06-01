package com.example.viikkotentti3.kello;

import java.util.Date;

/**
 * KelloLahde-rajapinnan toteuttavan luokan täytyy kyetä selvittämään nykyinen
 * ajanhetki jostain.
 */
public interface KelloLahde {

	public Date haeAjanhetki();

}
