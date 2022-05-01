package cs353.group14.responses;


import java.util.List;

public class NonCodingChallengeAuthorCategoryResponse {
    private List<String> categories;
    private String authorName;

    public NonCodingChallengeAuthorCategoryResponse(List<String> categories, String authorName) {
        this.categories = categories;
        this.authorName = authorName;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
