package com.bbc.km.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app.domain")
public class DomainProperties {

    private Map<Integer, String> categories = new HashMap<>();
    private Map<Integer, String> menuItems = new HashMap<>();

    @PostConstruct
    public void initDefaults() {
        if (categories.isEmpty()) {
            categories.put(1, "panini");
            categories.put(2, "piadine");
            categories.put(3, "toast");
            categories.put(6, "piatti unici");
            categories.put(8, "panini special");
        }
    }

    public Map<Integer, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<Integer, String> categories) {
        this.categories = categories;
    }

    public Map<Integer, String> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Map<Integer, String> menuItems) {
        this.menuItems = menuItems;
    }
}
