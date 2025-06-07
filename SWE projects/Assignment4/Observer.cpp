#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Listener
{
  public:
  virtual void action(string s) = 0 ;
};

class Alarm
{
  vector <Listener *> listeners;
  public:
    void notify (string s) {
      for(int i = 0; i < listeners.size(); i++) {
        listeners[i]->action(s);
      }
    }
    void addListener(Listener* l) {
      listeners.push_back(l);
    }
};

class People : public Listener
{
  public:
    People(string s) {
        name = s;
    }
    void action(string s) override
    {
        cout << name << " is running away from a " << s << endl;
    }
  protected:
    string name;
};

class Organization : public Listener
{
  public:
    Organization(string s) {
        name = s;
    }
    void action(string s) override
    {
        cout << name << " is paying attention on a " << s << endl;
    }
  protected:
    string name;
};

class FireDept : public Alarm
{
    string location;
  public:
  
    void setAlarm(string locat)
    {
        location = locat;
        notify("fire alarm near " + location);
    }
};

class PoliceDept : public Alarm
{
    string location;

  public:
    void setAlarm(string locat)
    {
        location = locat;
        notify("attack alarm near " + location);
    }
};

int main()
{
  FireDept dept1;
  PoliceDept dept2;
  
  dept1.addListener(new People("John"));
  dept2.addListener(new People("Jane"));
  Listener* CSUSM = new Organization("CSUSM");
  dept1.addListener(CSUSM);
  dept2.addListener(CSUSM);
  dept1.setAlarm("San Diego");
  dept2.setAlarm("Irvine");
}
