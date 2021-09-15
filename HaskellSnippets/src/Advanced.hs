module Advanced where

alist, mapped:: [Int]
alist = [1,2,3,4,5]

square :: Int -> Int
square a = a * a

mapped = fmap square alist

