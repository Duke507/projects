<h1 align="center">Assignment 5:  Testing in Kotlin</h1>
<h2 align="center">Points: 10</h2>



**Assignment Description:**

For this assignment, we'll practice writing the test cases of Kotlin syntax with some popular interview questions. Here, you have to convert 2 problems ( 3.1, 11.5) using Kotlin syntax.  Then you will write your Unit test cases.  All the problems are given from the book “cracking the coding interview” by Gayle Laakmann McDowell.

**3.1 Describe how you could use a single array to implement three stacks.**

```
int stackSize = 100;
int[] buffer = new int [stackSize * 3];
int[] stackPointer = {-1, -1, -1}; // pointers to track top element

void push(int stackNum, int value) throws Exception {
  /* Check if we have space */
  if (stackPinter[stackNum] + 1 >= stackSize) { // Last element
    throw new Exception("Out of space.");
  }
  /* Increment stack pointer and then update top value */
  stackPointer[stackNum]++;
  buffer[absTopOfStack(stackNum)] = value;
}

int pop(int stackNum) throws Exception {
  if (stackPointer[stackNum] == -1) {
    throw new Exception("Trying to pop an empty stack.");
  }
  int value = buffer[absTopOfStack(stackNum)]; // Get top
  buffer[absTopOfStack(stackNum)] == 0; // Clear index
  stackPointer[stackNum]--; // Decrement pointer
  return value;
}

int peek(int stackNum) {
  int index = absTopOfStack(stackNum);
  return buffer[index]
}

boolean isEmpty(int stackNum) {
  return  stackPointer[stackNum] == -1;
}

/* returns index of top of stack "stackNum", in absolute terms */
int absTopOfStack(int stackNum) {
  return stackNum * stackSize + stackPointer[stackNum];
}
```

**11.5 Given a sorted array of strings which is interspersed with empty strings, write a method to find the location of a given string.**

```
public int searchR(String[] strings, String str, int first, int last) {
  if (first > last) return -1;
  /* Move mid to the middle */
  int mid = (last + first) / 2
  /* if mid is empty, find the closest non-empty string. */
  if (strings[mid].isEmpty()) {
    int left = mid - 1;
    int right = mid + 1;
    while (true) {
      if (left < first && right > last) {
        return -1;
      } else if (right <= last && !strings[right].isEmpty()) {
        mid = right;
        break;
      } else if (left >= first && !strings[left].isEmpty()) {
        mid = left;
        break;
      }
       right++;
       left--;
    }
  }
  
  /* Check for string, and recurse if necessary */
  if (str.equals(strings[mid])) { // Found it!
    return mid;
  } else if (strings[mid].compareTo(str) < 0) { // Search right
    return searchR(strings, str, mid + 1, last);
  } else { // Search left
    return searchR(strings, str, first, mid - 1);
  }
}

public int search(String[] strings, String str) {
  if (strings == null || str == null || str == "") {
    return -1;
  }
  return searchR(strings, str, 0, strings.length - 1);
}
```

:exclamation:**```If you want help, please feel free to show your code to others or ask for help in our online discussion forum. If you work on your solution for a few hours and are still not done, you can turn it in, and we will award you credit. You can do it!```**

**In order to complete this activity, you need to take the following steps:**
1. You will need to create a project name Assignment5_YourName_PartnerName in android studio. 
2. Create two classes in the folder com.example.Assignment5_YourName_PartnerName with name problem1, problem2.
3. Create two classes in the folder com.example.Assignment5_YourName_PartnerName(test) with name problem1Test, problem2Test.
4. Write as many as possible test cases.
5. **It is expected to write 10 or more unique cases for problem 1,  10 or more unique cases for problem 2**

**You can write test cases for input/output validation, proper variable/object creation, all the corner cases (where may fail) in your program.  You may find many test cases in these repos.**


**https://github.com/trending/kotlin**

**For submitting the project, you have to follow three steps:**

1. Make a small video showing your program functionality in the emulator and show your code as well. 1~2 minutes video is fine. 
2. Please take screenshots of the Kotlin code including the left bar of IDE, test the running console, and then make a PDF file like Class Activities. At the top of the file, please mention both partners' names. 
3. **You have to upload the PDF and Video into your Canvas.**
4. Push your code, pdf, and video in this Github for your future reference.

It does not need to be perfect or bug-free to receive credit. Your code will not be graded on style, but we still encourage you to follow a good overall coding style for your own sake. 

:exclamation:**```If you have any confusion, please raise this issue before the class. I will try to clarify as much as possible.```**

<h5>Grading Rubrics:</h5>

```diff
- Successfully convert the first problem into kotlin syntax - 2.5
- Successfully write 10 test cases for the first problem - 2.5
- Successfully convert the second problem into kotlin syntax - 2.5 
- Successfully write 10 test cases for the second problem -  2. 5

```
