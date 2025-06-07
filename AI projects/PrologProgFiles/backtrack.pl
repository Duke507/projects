
% Yoshii- Prolog Backtracking example
% Run this with a(What) to find all possible answers.

% gprolog requires the same predicates to be grouped together

d(apple).
d(apricot).

e(orange).

f(apricot).
f(orange).

a(X) :- b(X), c(X).

b(X) :- d(X).
b(X) :- e(X).

c(X) :- f(X).

%goals:
%a(What). a(What) :- b(What), c(What).
%b(What). b(What) :- d(What).
%d(What). d(apple)  What = apple c(What)is still left
%but What = apple
%c(apple). c(apple) :- f(apple)
%f(apple).  FAILS   backtrack
%d(What). d(apricot) What = apricot  c(What) is still left
%c(apricot).  c(apricot) :- f(apricot)
%    f(apricot).  MATCH  ;
%d(What). FAIL
%b(What). b(What) :- e(What).
%e(What). What = orange  c(What)
%c(orange)  ==> f(orange)
%    f(orange). MATCH.  ;
