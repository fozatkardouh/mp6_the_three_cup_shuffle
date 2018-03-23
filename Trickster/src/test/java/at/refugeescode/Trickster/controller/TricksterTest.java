package at.refugeescode.Trickster.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class TricksterTest {

    @Autowired
    private Trickster trickster;
    @MockBean
    private RestTemplate restTemplate;

    @Test
    void play() {
        trickster.play();
        verify(restTemplate, times(3)).delete(anyString());
        verify(restTemplate, times(1)).put(anyString(), anyBoolean());
    }

    @Test
    void guess() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.accepted().body(true));
        trickster.guess(0);
        verify(restTemplate, times(1)).getForEntity(anyString(), any());
    }

}