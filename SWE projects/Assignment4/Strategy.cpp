#include <iostream>

using namespace std;
class Bills
{
  public:
  Bills()
  {
    totalPrice = 0;
    tax = 0.8;
  }
  
  Bills(float tax)
  {
    totalPrice = 0;
    this-> tax = tax;
  }
  void add(float price) 
  {
    totalPrice += price;
  }
  float checkoutNormal()
  {
    return totalPrice * (1 + tax) ;
  }
  float checkoutHappyHour()
  {
    return totalPrice * 0.8 * (1 + tax) ;
  }
  float checkoutHoliday()
  {
    return totalPrice * 1.2 * (1 + tax) ;
  }

protected:
  int totalPrice;
  float tax;  

} ;


int main()
{
  Bills bill;
  
  bill.add(123) ;
  cout << "The total price for the normal bill is: " << bill.checkoutNormal() << endl;
  cout << "The total price for the happy hour bill is: " << bill.checkoutHappyHour() << endl;
  cout << "The total price for the holiday bill is: " << bill.checkoutHoliday() << endl;
}
