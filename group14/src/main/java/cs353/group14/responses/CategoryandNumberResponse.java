package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryandNumberResponse {
    String category;
    int number;

    public CategoryandNumberResponse(String category, int number) {
        this.category = category;
        this.number = number;
    }
}

