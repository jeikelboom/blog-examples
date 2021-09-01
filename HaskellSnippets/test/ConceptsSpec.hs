module ConceptsSpec where
import Concepts
import Test.HUnit

conceptsSpec :: IO()
conceptsSpec = do
  runTestTT tests
  return ()
  
tests = TestList [test1, test2]  

test1 :: Test
test1 = TestCase(assertEqual "No commute g.f" false ((g . f . lek) Unit))

test2 :: Test
test2 = TestCase(assertEqual "No commute h" false ((h . lek) Unit))
