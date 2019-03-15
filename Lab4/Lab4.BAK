
% AI - Lab 4
% Shakey the robot problem


% actions
act( go(X,Y),                                               % action name
     [at(shackey, X), passage(X, Y)],                              % preconditions
     [at(shackey, X)],                                        % delete
     [at(shackey, Y)]                                       % add
     ).

act( climbup(B),                                        % action name
     [at(shackey, X), at(B, X), box(B), on(shackey, floor)],                      % preconditions
     [on(shackey, floor)],                                        % delete
     [on(shackey, B)]                                        % add
     ).

act( climbdown(B),                                        % action name
     [on(shackey, B), box(B)],                      % preconditions
     [on(shackey, B)],                                        % delete
     [on(shackey, floor)]                                      % add
     ).


act( turnon(L),                                        % action name
     [at(shackey, X), at(B, X), on(shackey, B), lightswitch(L, X), lightoff(X)],                      % preconditions
     [lightoff(X)],                                        % delete
     [lighton(X)]                                        % add
     ).

act( turnoff(L),                                        % action name
     [at(shackey, X), at(B, X), on(shackey, B), lightswitch(L, X), lighton(X)],                      % preconditions
     [lighton(X)],                                        % delete
     [lightoff(X)]                                       % add
     ).
     
     
act( push(B,X,Y),                                                          % action name
     [at(shackey, X), at(B, X), on(shackey, floor), box(B), passage(X,Y)],                      % preconditions
     [at(shackey, X),at(B, X)],                                                                                      % delete
     [at(shackey, Y),at(B, Y)]                                                              % add
     ).
     

%goal_state( [at(shackey, room1) ]).
%goal_state( [lightoff(room1) ]).
goal_state( [at(box2, room2) ]).

initial_state(
     [
      passage(door1, room1),
      passage(room1, door1),
      passage(door1, corridor),
      passage(corridor, door1),

      passage(door2, room2),
      passage(room2, door2),
      passage(door2, corridor),
      passage(corridor, door2),

      passage(door3, room3),
      passage(room3, door3),
      passage(door3, corridor),
      passage(corridor, door3),

      passage(door4, room4),
      passage(room4, door4),
      passage(door4, corridor),
      passage(corridor, door4),
      
      at(box1, room1),
      at(box2, room1),
      at(box3, room1),
      at(box4, room1),
      at(shackey, room3),
      on(shackey, floor),
      
      box(box1),
      box(box2),
      box(box3),
      box(box4),
      
      lightswitch(light1, room1),
      lightswitch(light2, room2),
      lightswitch(light3, room3),
      lightswitch(light4, room4),
      
      lighton(room1),
      lightoff(room2),
      lightoff(room3),
      lighton(room4)
      
     ]).

