package models.Pet;

import lombok.*;
import models.Model;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Model {
        private long id;
        private String name;
}
