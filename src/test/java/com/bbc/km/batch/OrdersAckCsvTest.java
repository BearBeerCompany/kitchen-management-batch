package com.bbc.km.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrdersAckCsvTest {

  @Test
  void shouldSetAndGetAllFields() {
    OrdersAckCsv csv = new OrdersAckCsv();

    csv.setId(1);
    csv.setOrderNumber(100);
    csv.setTableNumber("T5");
    csv.setInsertDate("2025-01-15");
    csv.setInsertTime("12:30:00");
    csv.setClientName("Mario Rossi");
    csv.setTakeAway(false);
    csv.setOrderNotes("No onions");
    csv.setQuantity(2);
    csv.setMenuItemId(10);
    csv.setMenuItemName("Panino Classico");
    csv.setMenuItemNotes("Extra cheese");
    csv.setCategoryId(1);
    csv.setAck(true);

    assertEquals(1, csv.getId());
    assertEquals(100, csv.getOrderNumber());
    assertEquals("T5", csv.getTableNumber());
    assertEquals("2025-01-15", csv.getInsertDate());
    assertEquals("12:30:00", csv.getInsertTime());
    assertEquals("Mario Rossi", csv.getClientName());
    assertFalse(csv.getTakeAway());
    assertEquals("No onions", csv.getOrderNotes());
    assertEquals(2, csv.getQuantity());
    assertEquals(10, csv.getMenuItemId());
    assertEquals("Panino Classico", csv.getMenuItemName());
    assertEquals("Extra cheese", csv.getMenuItemNotes());
    assertEquals(1, csv.getCategoryId());
    assertTrue(csv.getAck());
  }

  @Test
  void shouldHandleNullValues() {
    OrdersAckCsv csv = new OrdersAckCsv();

    assertNull(csv.getId());
    assertNull(csv.getOrderNumber());
    assertNull(csv.getTableNumber());
    assertNull(csv.getInsertDate());
    assertNull(csv.getInsertTime());
    assertNull(csv.getClientName());
    assertNull(csv.getTakeAway());
    assertNull(csv.getOrderNotes());
    assertNull(csv.getQuantity());
    assertNull(csv.getMenuItemId());
    assertNull(csv.getMenuItemName());
    assertNull(csv.getMenuItemNotes());
    assertNull(csv.getCategoryId());
    assertNull(csv.getAck());
  }
}
