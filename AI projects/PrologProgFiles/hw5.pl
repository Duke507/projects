
% Yoshii S24 - HW5 program

% gprolog requires the same predicates to be grouped together

% the top level goal is find to bind Who 
find(Who) :- wings(Who), write('The answer is: '), write(Who).

wings(X) :- fly(X).

eagle(bill).

airplane(dc10).


fly(mary).
fly(Y) :- bird(Y).
fly(X) :- airplane(X).


bird(Z) :- eagle(Z).




