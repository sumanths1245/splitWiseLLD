import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private static GroupManager instance;
    private List<Group> groups;

    private GroupManager() {
        groups = new ArrayList<>();
    }

    public static GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}
