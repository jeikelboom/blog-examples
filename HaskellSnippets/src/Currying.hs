module Currying where

add1 :: (Int, Int) -> Int
add1 (m, n) = m + n

add2 :: Int -> Int -> Int
add2 m n = m + n

inc :: Int -> Int
inc = add2 1

fiveA = add1 (1, 4)
fiveB = add2 1 4
fiveC = inc 4
