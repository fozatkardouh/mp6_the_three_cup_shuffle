package at.refugeescode.mp6_the_three_cup_shuffle.endpoints;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CupTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl() {
        return "http://localhost:" + port + "/coin";
    }

    @Test
    void askIsCoinHere() {
        deleteCoin();
        Boolean responseBody = getResultFromServer();
        assertEquals(false, responseBody);

        placeCoin();
    }

    @Test
    void deleteCoin() {
        placeCoin();
        restTemplate.delete(getUrl());
        Boolean responseBody = getResultFromServer();
        assertEquals(false, responseBody);
    }

    @Test
    void placeCoin() {
        restTemplate.put(getUrl(), true);
        Boolean responseBody = getResultFromServer();
        assertEquals(true, responseBody);
    }

    private Boolean getResultFromServer() {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(getUrl(), Boolean.class);
        return response.getBody();
    }

}