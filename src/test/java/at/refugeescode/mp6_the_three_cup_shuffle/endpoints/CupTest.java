package at.refugeescode.mp6_the_three_cup_shuffle.endpoints;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CupTest {

    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:9052/coin";

    @Test
    void askIsCoinHere() {
        Boolean responseBody = getResultFromServer();
        assertEquals(false, responseBody);
    }

    @Test
    void deleteCoin() {
        restTemplate.delete(url);
    }

    @Test
    void placeCoin() {
        restTemplate.put(url, true);
        Boolean responseBody = getResultFromServer();
        assertEquals(true, responseBody);
    }

    private Boolean getResultFromServer() {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
        return response.getBody();
    }

}