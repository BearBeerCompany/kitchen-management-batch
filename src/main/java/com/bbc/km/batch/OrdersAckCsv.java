package com.bbc.km.batch;

public class OrdersAckCsv {

  private Integer id;
  private Integer orderNumber;
  private String tableNumber;
  private String insertDate;
  private String insertTime;
  private String clientName;
  private Boolean takeAway;
  private String orderNotes;
  private Integer quantity;
  private Integer menuItemId;
  private String menuItemName;
  private String menuItemNotes;
  private Integer categoryId;
  private Boolean ack;

  // Getters and setters omitted for brevity

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(String tableNumber) {
    this.tableNumber = tableNumber;
  }

  public String getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(String insertDate) {
    this.insertDate = insertDate;
  }

  public String getInsertTime() {
    return insertTime;
  }

  public void setInsertTime(String insertTime) {
    this.insertTime = insertTime;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Boolean getTakeAway() {
    return takeAway;
  }

  public void setTakeAway(Boolean takeAway) {
    this.takeAway = takeAway;
  }

  public String getOrderNotes() {
    return orderNotes;
  }

  public void setOrderNotes(String orderNotes) {
    this.orderNotes = orderNotes;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getMenuItemId() {
    return menuItemId;
  }

  public void setMenuItemId(Integer menuItemId) {
    this.menuItemId = menuItemId;
  }

  public String getMenuItemName() {
    return menuItemName;
  }

  public void setMenuItemName(String menuItemName) {
    this.menuItemName = menuItemName;
  }

  public String getMenuItemNotes() {
    return menuItemNotes;
  }

  public void setMenuItemNotes(String menuItemNotes) {
    this.menuItemNotes = menuItemNotes;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Boolean getAck() {
    return ack;
  }

  public void setAck(Boolean ack) {
    this.ack = ack;
  }
}
