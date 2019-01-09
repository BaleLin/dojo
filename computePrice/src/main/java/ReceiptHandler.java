import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReceiptHandler {

  private String stateCode;
  private List<ReceiptItem> receiptItemArray;
  private double totalWithoutTaxes;
  private double discountRate = 0;
  private double tax;
  private double addMoneyWithTax;
  private double reduceMoneyWithDiscount;
  private double actualPrice;
  private String formatItemsStatement;
  private String formatTotalWithoutTaxesStatement;
  private String formatDiscountStatement;
  private String formatTaxStatement;
  private String formatActualPriceStatement;
  private StringBuilder sb;
  private Map<String, Double> stateCodeMap;
  private Map<Double, Double> discountMap;

  public ReceiptHandler() {
    receiptItemArray = new ArrayList<ReceiptItem>();
    sb = new StringBuilder();
    stateCodeMap = new HashMap<String, Double>();
    discountMap = new TreeMap<Double, Double>();
    initStateCode();
    initDiscountMap();
    initFormatStatement();
  }


  private String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public List<ReceiptItem> getReceiptItemArray() {
    return receiptItemArray;
  }

  public void setReceiptItemArray(ArrayList<ReceiptItem> receiptItemArray) {
    this.receiptItemArray = receiptItemArray;
  }


  public void addItem(ReceiptItem receiptItem) {
    this.receiptItemArray.add(receiptItem);
  }

  public String generateReceipt() {
    generateItemsInfo();
    calculatePrice();
    generateTotalPriceInfo();
    return sb.toString();
  }

  private void calculatePrice() {
    tax = getTaxRate(this.getStateCode());
    addMoneyWithTax = calculateAddMoneyWithTax();
    reduceMoneyWithDiscount = calculateReduceMoneyWithDiscount();
    actualPrice = calculateActualPrice();
  }

  private double calculateAddMoneyWithTax() {
    return totalWithoutTaxes * tax;
  }

  private double calculateReduceMoneyWithDiscount() {
    discountRate = getDiscountRate();
    return totalWithoutTaxes * discountRate;
  }

  private double calculateActualPrice() {
    return totalWithoutTaxes + addMoneyWithTax - reduceMoneyWithDiscount;
  }

  private double getDiscountRate() {
    for (Double priceKey : discountMap.keySet()) {
      if (totalWithoutTaxes < priceKey) {
        return discountRate;
      }
      discountRate = discountMap.get(priceKey);
    }
    return discountRate;
  }

  private double getTaxRate(String stateCode) {
    return stateCodeMap.get(stateCode);
  }

  private void initFormatStatement() {
    formatItemsStatement = "%s     %d   %.2f   %.2f\n";
    formatTotalWithoutTaxesStatement = "Total without taxes                               %.2f\n";
    formatDiscountStatement = "Discount %.2f%%                                   -%.2f\n";
    formatTaxStatement = "Tax %.2f%%                                        +%.2f\n";
    formatActualPriceStatement = "Total price                                      %.2f\n";
  }

  private void initStateCode() {
    stateCodeMap.put("UT", 0.0685);
    stateCodeMap.put("NV", 0.08);
    stateCodeMap.put("TX", 0.0625);
    stateCodeMap.put("AL", 0.04);
    stateCodeMap.put("CA", 0.0825);
  }

  private void initDiscountMap() {
    discountMap.put(1000.00, 0.03);
    discountMap.put(5000.00, 0.05);
    discountMap.put(7000.00, 0.07);
    discountMap.put(10000.00, 0.10);
    discountMap.put(50000.00, 0.15);
  }

  private void generateItemsInfo() {
    for (ReceiptItem receiptItem : receiptItemArray) {
      String itemName = receiptItem.getItemName();
      int quantity = receiptItem.getQuantity();
      double unitPrice = receiptItem.getUnitPrice();
      double singleTotalPrice = receiptItem.getItemTotalPrice();
      totalWithoutTaxes = totalWithoutTaxes + singleTotalPrice;
      sb.append(String.format(formatItemsStatement, itemName, quantity, unitPrice, singleTotalPrice));
    }
  }

  private void generateTotalPriceInfo() {
    sb.append("\n" + "-----------------------------------------------------" + "\n");
    sb.append(String.format(formatTotalWithoutTaxesStatement, totalWithoutTaxes));
    sb.append(String.format(formatDiscountStatement, discountRate * 100, reduceMoneyWithDiscount));
    sb.append(String.format(formatTaxStatement, tax * 100, addMoneyWithTax));
    sb.append("\n" + "-----------------------------------------------------" + "\n");
    sb.append(String.format(formatActualPriceStatement, actualPrice));
  }

}