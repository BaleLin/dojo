public class ReceiptItem {
  private String itemName;
  private int quantity;
  private double unitPrice;

  public ReceiptItem(String itemName, int quantity, double unitPrice) {
    this.itemName = itemName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getItemTotalPrice(){
    return quantity*unitPrice;
  }
}
