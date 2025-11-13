package Spring.AI.Artificial.Intelligence.mcp;

import java.util.List;

public record Conference(String name, int year, String[] dates, String location, List<Session> sessions) {
}
