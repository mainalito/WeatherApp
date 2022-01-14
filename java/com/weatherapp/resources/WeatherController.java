package com.weatherapp.resources;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.weatherapp.models.Weather;
import com.weatherapp.models.Condition;
import com.weatherapp.models.Current;
import com.weatherapp.models.Location;

import reactor.core.publisher.Mono;

@Controller
public class WeatherController {

	private final String API_URL = "https://api.weatherapi.com/v1/current.json";

	private final WebClient webclient;

	public WeatherController(WebClient.Builder webClientBuilder) {
		this.webclient = webClientBuilder.baseUrl(API_URL).build();

	}
	
	@Value("${api.key}")
	private String apiKey;
	
	@GetMapping("/")
	public String Home() {

		return "index";

	}

	@GetMapping("/search")
	public String fetchWeather(@RequestParam String q, Model model, HttpSession session) {
		Mono<Weather> WeatherMono = this.webclient.get()
				.uri(UriBuilder -> UriBuilder.queryParam("key", apiKey).queryParam("q", q)
						.build())

				.retrieve().bodyToMono(Weather.class);
		Weather weather = WeatherMono.block();
		System.out.println(weather.toString());
		Location l = weather.getLocation();
		Current c = weather.getCurrent();
		Condition cd = c.getCondition();
		session.setAttribute("lat", l.getLat());
		session.setAttribute("lon", l.getLon());
		model.addAttribute("location", l);
		model.addAttribute("current", c);
		model.addAttribute("condition", cd);
		return "index";

	}

}
