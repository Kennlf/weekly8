package com.example.part2externalapimomondoassignment.api;


import com.example.part2externalapimomondoassignment.dto.Age;
import com.example.part2externalapimomondoassignment.dto.Gender;
import com.example.part2externalapimomondoassignment.dto.NameResponse;
import com.example.part2externalapimomondoassignment.dto.Nationality;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class NameController {

    @GetMapping("/name-info")
    public NameResponse getDetails(@RequestParam String name){
        Mono<Age> age = getAge(name);
        Mono<Gender> gender = getGender(name);
        Mono<Nationality> nationality = getNationality(name);

        var resMono = Mono.zip(age, gender, nationality).map(t -> {
            NameResponse ns = new NameResponse();
            ns.setAge(t.getT1().getAge());
            ns.setAgeCount(t.getT1().getCount());

            ns.setGender(t.getT2().getGender());
            ns.setGenderProbability(t.getT2().getProbability());

            ns.setCountry(t.getT3().getCountry().get(0).getCountry_id());
            ns.setCountryProbability(t.getT3().getCountry().get(0).getProbability());

            return ns;
        });
        NameResponse response = resMono.block();
        response.setName(name);
        return response;
    }


    Mono<Age> getAge(String name){
        WebClient client = WebClient.create();
        Mono<Age> age = client.get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .bodyToMono(Age.class);
        return age;
    }

    Mono<Gender> getGender(String name){
        WebClient client = WebClient.create();
        Mono<Gender> gender = client.get()
                .uri("https://api.genderize.io?name=" + name)
                .retrieve()
                .bodyToMono(Gender.class);
        return gender;
    }

    Mono<Nationality> getNationality(String name){
        WebClient client = WebClient.create();
        Mono<Nationality> nationality = client.get()
                .uri("https://api.nationalize.io?name=" + name)
                .retrieve()
                .bodyToMono(Nationality.class);
    return nationality;
    }
}
