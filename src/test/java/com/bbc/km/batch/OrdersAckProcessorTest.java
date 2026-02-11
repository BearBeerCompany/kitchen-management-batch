package com.bbc.km.batch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdersAckProcessorTest {

  private DomainProperties domainProperties;
  private OrdersAckProcessor processor;

  @BeforeEach
  void setUp() {
    domainProperties = new DomainProperties();
    domainProperties.initDefaults(); // loads default categories
    processor = new OrdersAckProcessor(domainProperties);
  }

  @Test
  void shouldReturnItemWhenCategoryIsValid() {
    OrdersAckCsv item = buildItem(1, 10);

    OrdersAckCsv result = processor.process(item);

    assertNotNull(result);
    assertSame(item, result);
  }

  @Test
  void shouldThrowWhenCategoryIdIsInvalid() {
    OrdersAckCsv item = buildItem(999, 10);

    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> processor.process(item));
    assertEquals("Invalid category id: 999", ex.getMessage());
  }

  @Test
  void shouldReturnItemWhenMenuItemsMapIsEmpty() {
    // menu items map is empty by default, so validation is skipped
    OrdersAckCsv item = buildItem(1, 999);

    OrdersAckCsv result = processor.process(item);

    assertNotNull(result);
    assertSame(item, result);
  }

  @Test
  void shouldReturnItemWhenMenuItemIdIsValid() {
    Map<Integer, String> menuItems = new HashMap<>();
    menuItems.put(10, "Panino Classico");
    domainProperties.setMenuItems(menuItems);

    OrdersAckCsv item = buildItem(1, 10);

    OrdersAckCsv result = processor.process(item);

    assertNotNull(result);
    assertSame(item, result);
  }

  @Test
  void shouldThrowWhenMenuItemIdIsInvalid() {
    Map<Integer, String> menuItems = new HashMap<>();
    menuItems.put(10, "Panino Classico");
    domainProperties.setMenuItems(menuItems);

    OrdersAckCsv item = buildItem(1, 999);

    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> processor.process(item));
    assertEquals("Invalid menu item id: 999", ex.getMessage());
  }

  @Test
  void shouldValidateAllDefaultCategories() {
    for (Integer categoryId : domainProperties.getCategories().keySet()) {
      OrdersAckCsv item = buildItem(categoryId, 10);
      OrdersAckCsv result = processor.process(item);
      assertNotNull(result);
    }
  }

  private OrdersAckCsv buildItem(Integer categoryId, Integer menuItemId) {
    OrdersAckCsv item = new OrdersAckCsv();
    item.setId(1);
    item.setOrderNumber(100);
    item.setTableNumber("T5");
    item.setInsertDate("2025-01-15");
    item.setInsertTime("12:30:00");
    item.setClientName("Mario Rossi");
    item.setTakeAway(false);
    item.setOrderNotes("No onions");
    item.setQuantity(2);
    item.setMenuItemId(menuItemId);
    item.setMenuItemName("Test Item");
    item.setMenuItemNotes("");
    item.setCategoryId(categoryId);
    item.setAck(true);
    return item;
  }
}
