#include<string>
#include<vector>
#include<iostream>
#include<stdlib.h>  /* to use exit */
using namespace std;

// S24
// Update this file for HW2 FWDC (Yoshii)
// The general version of A* is in the Project folder.

// Read the main() first and write down all the functions it calls!
// Then, look for ** using control-S and add code. 
// ** always marks where you need to change!
//--------------------------------------------------
// YOUR NAME: Connor Penn
// Tested on Empress using g++  
//--------------------------------------------------

// State description class.
// Could have used struct.
// Items here is a string of L or R for FWDC in that order
//    e.g. RRRR for the initial state; LLLL for the goal. 
// Choosing the "easy to change" representation for items is important!
class state
{
public:
  string items;  // made up of L and R going from RRRR to LLLL 
  string camefrom; // the parent on the solution path
  int g;   // cost so far
  int h;   // estimated cost to the goal (# of items on the right)
  int f;   // g+h
};

// Major data structures:
vector<state> frontier;  // Frontier states - will pick one of these to expand
vector<state> beenThere; // already expanded states

// ========== Utility Functions ===================

// utility to add x to the beenthere
void addtoBeenThere(state x)
{  beenThere.push_back(x); }

// utility to add x to the frontier
void addtoFrontier(state x)
{  frontier.push_back(x); }

// to remove state x from the frontier when it has been expanded
void removefromFrontier(state x)
{  
  vector<state> newfrontier;
  for (int k = 0; k < frontier.size(); k++)
    if (frontier[k].items != x.items)
      newfrontier.push_back(frontier[k]);

  frontier = newfrontier;
}

// Called from Generate
bool inBeenThere(state next)
{
  for (int i = 0; i < beenThere.size(); i++) {
    if (beenThere[i].items == next.items) {
      return true ;
    }
  }
  return false;
  // ** return true if the state is already in beenThere else false
}

// Called from Generate
int computeH(state next)
{
  //intialize h
  int h = 0 ;
  //looping if statement
  for (int i = 1; i < 4; i++) {
    //if item is on right add two points
    if (next.items[i] == 'R') {
      h += 2;
    }
  }
  if (next.items[0] == 'L') {
    //if farmer on left add one point
    h ++;
  }
  //subtract one point
  h -- ;
  return h ;
}

//============= At the Very End ====================

// Called from Generate:
// Trace and display the solution path from goal to initial.
// Note that camefrom of each state should be used. (called from generate)
void tracePath(state goal)
{
  cout << "came from\n" ;
  cout << goal.camefrom << endl ;
  for (int i = 0; i < beenThere.size(); i++) {
    if (goal.camefrom == beenThere[i].items) {
      goal = beenThere[i] ;
    }
  }
  if (goal.items != "RRRR") {
    tracePath(goal) ;
  }
  // ** add code here to display the path from goal to initial
  // using cameFrom's  -- recursion is useful to not to depend on a particular order of states in beenThere!
  // yes all camefrom states are in beenThere so find it and recurse from there.  
}// end of tracePath  

//======= For Generating a new state to add to Frontier  =============

// Called from Generate:
// Check to see if next items is already in frontier and return true or false
//   If it is already in frontier, 
//   and if the next's f is better, 
//   update the frontier state to next.  
bool inFrontier(state next)
{
  bool frontierBool ;
  for(int i = 0; i < frontier.size(); i++) {
    if (frontier[i].items == next.items) {
      frontierBool = true; 
    }
    if (frontier[i].f < next.f) {
      cout << "frontier node " << i << " has been updated with a better f" ;
      frontier[i].f = next.f ;
    }
  }
  return frontierBool ;
  // ** add code here, possibly updating
  // the same frontier state with next if f is better.
  // Please cout a message in that case stating that you updated with better f
  // and return true. 
  // Return true or false (false = not in Frontier at all).
}// end of inFrontier


// called from Generate
bool isUnsafe(state next)
{ // ** return true of the next state is unsafe (i.e. WD without farmer or
  //   DC without farmer 
  if (next.items == "LRRL" || next.items == "RLLR" || next.items == "RRLL" || next.items == "LLRR" || next.items == "LRRR" || next.items == "RLLL") {
    return true ;
  }
  else {
    return false ;
  }
}

// Called from GenerateAll:
// Try to add next to frontier but stop if LLLL 
void generate(state next)
{ 
  cout << "Trying to add a state: " << next.items << " "; 

  if (next.items  == "LLLL")  // the goal is reached
    { cout << ">>Reached: " << next.items << endl;
      tracePath(next);  // display the solution path
      exit(0); }//done

  // ** if been there before (call inBeenThere), 
  //   do not add to frontier. Get out of the function.
  if(inBeenThere(next)) {
    cout << "Been there already\n" ;
    return ;
  }
  // ** if unsafe (call isUnsafe), do not add to frontier. Get out of the function.
  // For other puzzles this probably is not needed
  if (isUnsafe(next)) {
    cout << "is unsafe!\n" ;
    return ;
  }
  cout << "is safe!\n" ;

  // else compute h and then f for next (computeH function)
  int h = 0;// start point for h; g is already set
  h = computeH(next) ;
  next.h = h ;
  // ** update next's h based on next.items and then set f
  next.f = next.g + next.h; 
  if (!inFrontier(next))  // checks to see if in Frontier already
      addtoFrontier(next); // add new next to Frontier 

}// end of generate   



// Called from Main:
// Generate all new states from current 
// This calls generate for each new state
void generateAll(state current)
{
  state next;  // a generated one

  // each next will be modified from current for ease of modification
  // so set the current to be ready for that:
  current.g = current.g + 1; // to update g to be used for next
  // storing the parent so that we can produce the solution path
  current.camefrom = current.items; 

  // check all possibilities as follows..

  // move F alone to L
  next = current;  // starting point of next is current
  if (current.items[0] == 'R')
    { next.items[0]='L'; generate(next);};

  next = current; // starting point of next
  // move F alone to R
  if (current.items[0] == 'L')
    { next.items[0]='R'; generate(next);};

  next = current; // starting point of next
  // move FW to L
  if (current.items[0] == 'R' && current.items[1] == 'R')
    { next.items[0]='L'; next.items[1] = 'L'; generate(next);};  

  next = current; // starting point of next
  // move FW to R
  if (current.items[0] == 'L' && current.items[1] == 'L')
    { next.items[0]='R'; next.items[1] = 'R'; generate(next);};  
  
  next = current; // starting point of next
  // move FD to L
  if (current.items[0] == 'R' && current.items[2] == 'R')
    { next.items[0]='L'; next.items[2] = 'L'; generate(next);};  

  next = current; // starting point of next
  // move FD to R
  if (current.items[0] == 'L' && current.items[2] == 'L')
    { next.items[0]='R'; next.items[2] = 'R'; generate(next);};  

  next = current; // starting point of next
  // move FC to L
  if (current.items[0] == 'R' && current.items[3] == 'R')
    { next.items[0]='L'; next.items[3] = 'L'; generate(next);};  

  next = current; // starting point of next
  // move FC to R 
  if (current.items[0] == 'L' && current.items[3] == 'L')
    { next.items[0] == 'R'; next.items[3] == 'R';}  
  
}// end of generateAll


// Called from Main:
// Find the best f state out of the frontier and return it 
// so that it can be expanded
state bestofFrontier()
{ 
  int best = 0;
  for(int i = 0; i < frontier.size(); i++) {
    if (frontier[i].f < frontier[best].f) {
      best = i; 
    }
  }
  return frontier[best] ;
}//end of bestofFrontier

// Called from Main:
// Display the states in the frontier 
void displayFrontier()

{
  for (int k = 0; k < frontier.size(); k++)
    { cout << frontier[k].items << " ";  
      cout << "g = " <<frontier[k].g << " ";
      cout << "h = " <<frontier[k].h << " ";
      cout << "f = " <<frontier[k].f << endl; 
    }
}// end of displayFrontier

//================= Main ===========================

int main() 
{

  state  current = {"RRRR","", 0, 3, 3};  // initial
  addtoFrontier(current);

  char ans;
  while ( ans != 'n')
    { 
      removefromFrontier(current);      
      addtoBeenThere(current);

      cout << ">>>Expand:" << current.items << endl; 
      generateAll(current);  // new states are added to frontier

      cout << "Frontier is:" << endl;
      displayFrontier();

      current = bestofFrontier(); // pick the best state out of frontier
      cout << "Type any key to continue: " ;
      cin >> ans;

    }// end while

}//end of main

