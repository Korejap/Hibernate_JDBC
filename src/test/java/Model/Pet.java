package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class Pet {

    private Long id;
    private String name;
    private String ownerName;
    private int age;
    private double weight;
    private boolean pureRace;
    private Race race;


}
