package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        int added = 0;
        Map<Integer, String> userMap = new HashMap();
        for (User user : current) {
            userMap.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            if (userMap.get(user.getId()) != null
                    && !userMap.get(user.getId()).equals(user.getName())) {
                changed++;
            }
            if (userMap.get(user.getId()) == null) {
                deleted++;
            }
            added = current.size() - previous.size() + deleted;
        }
        return new Info(added, changed, deleted);
    }
}