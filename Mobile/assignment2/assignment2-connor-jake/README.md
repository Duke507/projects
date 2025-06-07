<h1 align="center">Assignment 2: Fragments</h1>

Before working on this or any assignment, you will need to set up the **Android Studio IDE** on your computer. You will create an Android project with the name **Fragment_YOURNAME_YOURPARTNERNAME** by selecting **Kotlin** as the language. We suggest that you do this as early as possible, because some students may get stuck and need time to get the IDE set up. 

<h4>Assignment Description:</h4>

For this second assignment, we'll practice whatever topics we covered in the class. You should develop an app that has the following: 
1. Your app should be set up as an **Android Studio project**, so it can easily be opened/graded by others. 
2. Create two buttons named Bar and Line in MainActivity. Then create one FragmentContainer. Your app will display a Line graph (LineFragment) by default into the FragmentContainer. Based on the button click, it will display either a Line graph and Bar graph(BarFragment). You have to dynamically load either one of the graphs. Please use the below data to produce your graphs. Here, a, b, c are series, and 1, 2, 3, 4 are domain labels. For drawing the graph, you can use the following library:  [https://github.com/halfhp/androidplot](https://github.com/halfhp/androidplot)
<p align="center">
  <img src="/images/fragmentpic.png" width="350" title="a fragment of main activity">
  <img src="/images/barandline.png" width="350" title="bar and line data">
</p>

:stop_sign:**```Please don’t load images of Bar and Line, rather draw graphs based on sample data.```**


If you want help, please feel free to show your code to others or ask for help in our online discussion forum. Feel free to make an app as simple or as complex as you like, relative to your familiarity level and time constraints. If you work on your solution for a few hours and are still not done, you can turn it in, and we will award you credit. You can do it! 
 
**For submitting the project, you have to follow three steps:**

1. Make a small video showing your program functionality in the emulator and show your code as well. 1~2 minutes video is fine. 
2. Please take screenshots of the Kotlin code including the left bar of IDE, test the running console, and then make a PDF file like Class Activities. At the top of the file, please mention both partners' names. 
3. **You have to upload the PDF and Video into your Canvas.**
4. Push your code, pdf, and video in this Github for your future reference.

It does not need to be perfect or bug-free to receive credit. Your code will not be graded on style, but we still encourage you to follow a good overall coding style for your own sake. 

<h5>Grading Rubrics:</h5>

```diff
- Successfully load the dynamic fragments into MainActivity: 3 points
- Successfully draw the Bar graph using the provided library with sample data: 3 points
- Successfully draw the Line graph using the provided library with sample data: 3 points
- Overall functionality and UI/UX: 1 pts
```

