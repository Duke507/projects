% Yoshii - backtracking example using likes
% gprolog requires the same predicates to be grouped together

likes(john,bill).
likes(john,wine).
likes(mary,bill).
likes(sue,john).

likes(john,X) :- likes(X,wine).              
likes(john,X) :- female(X), likes(X,john).   

female(mary).
female(sue).
