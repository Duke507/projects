//***********************************
// S24 YOSHII: N-gram probability use
// The input file can be of any format
// ***********************************
//-------------------------------------
// YOUR NAME: Connor
// Tested on Empress using g++
//-------------------------------------
// add includes here **
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

// This program uses trigram probabilities to produce P(word) 
int main()
{
  string wordInput;
  string temp;
  string fileName;
  vector <string> tProbs;
  float prob = 0;


  cout << "What is the name of the input file? : ";
  cin >> fileName;
  ifstream myFile(fileName);
  while(!myFile) {
    cout << "File name not found please enter again : ";
    cin >> fileName;

    myFile.open(fileName);
  }

  while(myFile)
  {
    string toPush ;
    getline(myFile, toPush);
    tProbs.push_back(toPush) ;
  }
  // ** Read in the trigram probabilties

  // ** Repeat the following until the user
  //    wants to quit:

  // ** Ask the user to enter a word
  while(true)
  {
    cout << "Please enter a word: ";
    cin >> wordInput;

    for (int i = 0; i < wordInput.length(); i++)
    {
      //creating similar format to vector strings
      if(i == 0) {
        temp = wordInput[i] + '|' + ' ' ;
      }
      else if(i == 1) {
        temp = wordInput[i] + "|" + wordInput[i-1] ;
        cout << " * ";
      }
      else {
        temp = wordInput[i] + "|" + wordInput[i-2] + wordInput[i-1];
        cout << " * ";
      }
      for (int j = 0; j < tProbs.size(); j++)
      {
        if (tProbs[j].substr(0,temp.length()) == temp)
        {
          cout << tProbs[j];
          int tempPos = tProbs[j].find("0.");
          string probConv = tProbs[j].substr(tempPos);
          if (prob == 0)
          {
            prob = stof(probConv);
          }
          else
          {
            prob = prob * stof(probConv);
          }
        }
      }
    }
  }
  // ** Show all the probabilities you used
  //    to calculate the P(word) as I did in prob.out

  // ** Show the P(word) as I did in prob.out
  
  // ** end of loop

} 
