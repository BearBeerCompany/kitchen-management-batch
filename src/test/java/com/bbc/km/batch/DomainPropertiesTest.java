package com.bbc.km.batch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DomainPropertiesTest {

  @Test
  void initDefaultsShouldPopulateCategoriesWhenEmpty() {
    DomainProperties props = new DomainProperties();

    // categories is empty by default
    assertTrue(props.getCategories().isEmpty());

    props.initDefaults();

    Map<Integer, String> categories = props.getCategories();
    assertEquals(5, categories.size());
    assertEquals("panini", categories.get(1));
    assertEquals("piadine", categories.get(2));
    assertEquals("toast", categories.get(3));
    assertEquals("piatti unici", categories.get(6));
    assertEquals("panini special", categories.get(8));
  }

  @Test
  void initDefaultsShouldNotOverrideExistingCategories() {
    DomainProperties props = new DomainProperties();

    Map<Integer, String> custom = new HashMap<>();
    custom.put(99, "custom category");
    props.setCategories(custom);

    props.initDefaults();

    assertEquals(1, props.getCategories().size());
    assertEquals("custom category", props.getCategories().get(99));
  }

  @Test
  void shouldSetAndGetCategories() {
    DomainProperties props = new DomainProperties();

    Map<Integer, String> categories = new HashMap<>();
    categories.put(1, "pizza");
    props.setCategories(categories);

    assertEquals(1, props.getCategories().size());
    assertEquals("pizza", props.getCategories().get(1));
  }

  @Test
  void shouldSetAndGetMenuItems() {
    DomainProperties props = new DomainProperties();

    assertTrue(props.getMenuItems().isEmpty());

    Map<Integer, String> menuItems = new HashMap<>();
    menuItems.put(10, "Margherita");
    menuItems.put(20, "Diavola");
    props.setMenuItems(menuItems);

    assertEquals(2, props.getMenuItems().size());
    assertEquals("Margherita", props.getMenuItems().get(10));
    assertEquals("Diavola", props.getMenuItems().get(20));
  }

  @Test
  void menuItemsShouldBeEmptyByDefault() {
    DomainProperties props = new DomainProperties();
    assertNotNull(props.getMenuItems());
    assertTrue(props.getMenuItems().isEmpty());
  }
}
