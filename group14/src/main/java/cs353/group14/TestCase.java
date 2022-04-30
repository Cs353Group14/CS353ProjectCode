package cs353.group14;

public class TestCase {

    int case_id;
    int challenge_id;
    String inputs;
    String outputs;

    public TestCase(int case_id, int challenge_id, String inputs, String outputs) {
        this.case_id = case_id;
        this.challenge_id = challenge_id;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public int getChallenge_id() {
        return challenge_id;
    }

    public void setChallenge_id(int challenge_id) {
        this.challenge_id = challenge_id;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }
}
