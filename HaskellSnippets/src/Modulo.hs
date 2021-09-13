module Modulo where

ggd :: Int -> Int -> Int
ggd a 0 = a
ggd a b = gcd b (a `mod` b)

add = (+)
foura = 3 `add` 1
fourb = (+) 3 1 
fourc = add 3 2
fourd = 3 + 1


total :: [Int] -> Int
total (a : xs) = a + total xs
total [] = 0 

alist :: [Int]
alist = 3 : 2 : 1 : []

sixa = total alist
sixb = foldl add 0 alist
sixc = foldl (\x y -> x + y) 0 alist


outlist :: [Int]
outlist = [n * n| n <- [1..5], odd(n)]