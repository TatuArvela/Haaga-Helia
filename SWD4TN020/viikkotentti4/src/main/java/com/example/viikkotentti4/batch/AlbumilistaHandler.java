package com.example.viikkotentti4.batch;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.viikkotentti4.bean.Albumi;
import com.example.viikkotentti4.dao.AlbumiDAO;

public class AlbumilistaHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		AlbumiDAO dao = (AlbumiDAO)context.getBean("daoLuokka");
		
		System.out.println("KOKOELMASTA LÖYTYVÄT ALBUMIT:");
		System.out.println("-----------------------------");
		
		List<Albumi> albumit = dao.haeKaikki();
		for (Albumi a : albumit) {
			System.out.println("#" + a.getId());
			System.out.println("Nimi: " + a.getNimi());
			System.out.println("Artisti: " + a.getArtisti());
			System.out.println("Julkaisuvuosi: " + a.getJulkaisuvuosi());
			System.out.println("Genre: " + a.getGenre());
			System.out.println("Formaatti: " + a.getFormaatti());
			System.out.println("");
		}
		
		context.close();
		
	}
}
