package at.refugeescode.Trickster.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class Trickster {

    private List<Integer> ports = Arrays.asList(9052, 9053, 9054);
    private String urlPartOne = "http://localhost:";
    private String urlPartTwo = "/coin";
    private RestTemplate restTemplate;

    public Trickster(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/play")
    public void play() {
        ports.forEach(integer -> restTemplate.delete(urlPartOne + integer + urlPartTwo));
        hideCoinInOneOfTheServers();
    }

    private void hideCoinInOneOfTheServers() {
        Collections.shuffle(ports);
        restTemplate.put(urlPartOne + ports.get(0) + urlPartTwo, true);
    }

    @GetMapping("/guess/{number}")
    @ResponseBody
    public Boolean guess(@PathVariable Integer number) {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(urlPartOne + ports.get(number) + urlPartTwo
                , Boolean.class);
        return response.getBody();
    }

}
