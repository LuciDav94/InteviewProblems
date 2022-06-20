A group of friends is going on holiday together. They come to a meeting point(the start of the
journey) using N cars. There are P[K] people and S[K] seats in the K-th car for K in range  [0..N-1].
Some of the seat in the cars may be free, so it is possible for some of the friends to change the car
they are in. The friends have decided that, in order to be ecological, they will leave some cars parked
at the meeting point and travel with as few cars as possible.

Given P[1,4,1] and S[1,5,1] -> the funnction should return 2. A persona from car number 0 can
travel in care number 1 instead. This way, car number 0 can be left parked at the meeting point.

Write an efficient algorithm for the following assumptions:
-N is an integer with ranger [1....100.000];
-each element of arrays P and S is an integer withing the range [1..9];
-every friend had a seat in the car they came in; that is; P[K] <= S[K] for each K within the
range [0...N-1]