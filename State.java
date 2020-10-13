import java.util.HashMap;

public class State {

    private HashMap<Character, Integer> paths;
    private boolean isActivationState;

    public State() {
        this.paths = new HashMap<Character, Integer>();
        this.isActivationState = false;
    }

    public void setActivationState() {
        this.isActivationState = true;
    }

    public boolean isActivationState() { return this.isActivationState; }

    public void addPath(char c, int index) {
        this.paths.put(c, index);
    }

    public int move(char nextCharacter) { return this.paths.get(nextCharacter); }

    @Override
    public String toString() {
        return "State{" +
                "isActivationState=" + this.isActivationState +
                ", paths=" + this.paths +
                '}';
    }
}
