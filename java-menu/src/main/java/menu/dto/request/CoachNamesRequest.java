package menu.dto.request;

import java.util.List;

public class CoachNamesRequest {
    List<String> names;

    private CoachNamesRequest(List<String> names) {
        this.names = names;
    }

    public static CoachNamesRequest from(String coachesName) {
        List<String> names = List.of(coachesName.split(",", -1));

        return new CoachNamesRequest(names);
    }

    public List<String> getNames() {
        return List.copyOf(names);
    }
}
