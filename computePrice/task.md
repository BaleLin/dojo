Task1
```
give lable of item:pen,Quantity:1,Unit price:2, letter state code:UT 
  when calculate
  then output the recipe:
  "pen     1   2.00   2.00
   
   -----------------------------------------------------
   Total without taxes                               2.00
   Discount 0.00%                                   -0.00
   Tax 6.85%                                        +0.14
   
   -----------------------------------------------------
   Total price                                      2.14`
```
Task2
```
give lable of item:pen,Quantity:1,Unit price:3000, letter state code:UT
  when calculate
  then output the recipe:
  "pen     1   3000.00   3000.00
   
   -----------------------------------------------------
   Total without taxes                            3000.00
   Discount 3.00%                                  -90.00
   Tax 6.85%                                      +205.50
   
   -----------------------------------------------------
   Total price                                     3115.5`
```

Task3
```
give lable of item:pen,Quantity:1,Unit price:6000, letter state code:UT
  when calculate
  then output the recipe:
  "pen     1   6000.00   6000.00
   
   -----------------------------------------------------
   Total without taxes                            6000.00
   Discount 5.00%                                 -300.00
   Tax 6.85%                                      +411.00
   
   -----------------------------------------------------
   Total price                                    6111.00`
```

Task4
```
give lable of item:pen,Quantity:1,Unit price:6000, letter state code:NV
  when calculate
  then output the recipe:
  "pen     1   6000.00   6000.00
   
   -----------------------------------------------------
   Total without taxes                            6000.00
   Discount 5.00%                                 -300.00
   Tax 8.00%                                      +480.00
   
   -----------------------------------------------------
   Total price                                    6180.00`
```

Task5
```
  give lable of item:pen,Quantity:2,Unit price:6000 and 
                     mac,Quantity:1,Unit price:12000,
                     and letter state code:NV
  when calculate
  then output the recipe:
  "pen     2   6000.00   12000.00
   mac     1   12000.00  12000.00   
   
   -----------------------------------------------------
   Total without taxes                            24000.00
   Discount 10.00%                                -2400.00
   Tax 8.00%                                      +1920.00
   
   -----------------------------------------------------
   Total price                                    23520.00`
```