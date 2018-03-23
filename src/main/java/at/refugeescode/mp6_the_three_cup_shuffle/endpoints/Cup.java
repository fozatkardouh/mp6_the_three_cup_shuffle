package at.refugeescode.mp6_the_three_cup_shuffle.endpoints;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
public class Cup {

    private Boolean isCoinHere = false;

    @GetMapping
    public boolean askIsCoinHere() {
        return isCoinHere;
    }

    @DeleteMapping
    public void deleteCoin() {
        isCoinHere = false;
    }

    @PutMapping
    public void placeCoin() {
        isCoinHere = true;
    }

}


