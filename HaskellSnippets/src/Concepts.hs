module Concepts where

data A = Lek | Waal
data B = C3 | C4 | C42
data C = T | F deriving (Eq, Show)
data U = Unit

f :: A -> B
f Lek = C3
f Waal = C4
 
g :: B -> C
g C3 = F
g _ = T

lek ::  U -> A
lek _ = Lek

false :: U -> C
false _ = F
      
h :: A -> C
h = g . f

x1, x2, x3 :: C
x1 = (g . f . lek) Unit
x2 = (h . lek) Unit
x3 = false Unit
