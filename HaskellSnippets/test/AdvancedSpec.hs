module AdvancedSpec where

import Advanced 
import Test.HUnit

avancedSpec :: IO()
avancedSpec = do
  putStrLn "Testing Advanced"
  runTestTT tests
  return ()
  
tests = TestList [testA]  

testA :: Test
testA = TestCase(assertEqual "bad square" [1, 4, 9, 16, 25] mapped)
