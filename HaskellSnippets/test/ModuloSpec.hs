module ModuloSpec where

import Modulo
import Test.HUnit

moduloSpec :: IO()
moduloSpec = do
  putStrLn "Testing modulo"
  runTestTT tests
  return ()
  
tests = TestList [test1, test2, test3, test4, test5, test6,
        total1, total2,
        outlist1]  

test1, test2, test3, test4, test5, test6 :: Test
test1 = TestCase(assertEqual "expected 3 " 3 (ggd 9 15))
test2 = TestCase(assertEqual "expected 3 " 3 (ggd 15 9))
test3 = TestCase(assertEqual "expected 3 " 3 (ggd 15 (-9)))
test4 = TestCase(assertEqual "expected 3 " 3 (ggd 9 (-15)))
test5 = TestCase(assertEqual "expected 3 " 3 (ggd (-15) (-9)))
test6 = TestCase(assertEqual "expected 3 " 3 (ggd (-9) (-15)))
--test3 = TestCase(assertEqual "expected 3 " 3 (ggd 15 -9))
--test4 = TestCase(assertEqual "expected 3 " 3 (ggd 15 9))

total1 = TestCase(assertEqual "total " 6 sixa)
total2 = TestCase(assertEqual "total " 6 sixb)
total3 = TestCase(assertEqual "total " 6 sixc)

outlist1 = TestCase(assertEqual "list comprehension" [1, 9, 25] outlist)