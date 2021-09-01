module CurryingSpec where

import Currying
import Test.HUnit

curringSpec :: IO()
curringSpec = do
  putStrLn "Testing currying"
  runTestTT tests
  return ()
  
tests = TestList [testA, testB, testC]  

testA, testB, testC :: Test
testA = TestCase(assertEqual "add wrong" 5 fiveA)
testB = TestCase(assertEqual "add wrong" 5 fiveB)
testC = TestCase(assertEqual "add wrong" 5 fiveC)
