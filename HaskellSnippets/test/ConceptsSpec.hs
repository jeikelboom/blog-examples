module ConceptsSpec where
import Concepts
import Test.HUnit

conceptsSpec :: IO()
conceptsSpec = do
  runTestTT tests
  return ()
  
tests = TestList [test1, test2, test3]  

test1 :: Test
test1 = TestCase(assertEqual "No commute g.f" F x1)

test2 :: Test
test2 = TestCase(assertEqual "No commute h" F x2)

test3 :: Test
test3 = TestCase(assertEqual "No commute h" F x3)
