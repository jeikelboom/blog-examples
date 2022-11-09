module Advanced where
import Control.Applicative

square :: Int -> Int
square a = a * a

functorDemo:: IO()
functorDemo = do
  putStrLn ("fmap square [1,2,3,4,5] = " ++ (show (fmap square [1,2,3,4,5])))
  putStrLn ("fmap square [] = " ++ (show (fmap square [])))
  putStrLn ("fmap square (Just 4) = " ++ (show (fmap square (Just 4))))
  putStrLn ("fmap square Nothing = " ++ (show (fmap square Nothing)))
  putStrLn (show (fmap square [1,2,3,4,5] == [1,4,9,16,25]))
  putStrLn (show (fmap square [] == []))
  putStrLn (show (fmap length ["Hello", "world", "how", "do", "you", "do"] == [5,5,3,2,3,2]))
  return ()

applicativeDemo :: IO()
applicativeDemo = do
  putStrLn ("pure square <*> [1,2,3,4,5] = " ++ (show (pure square <*> [1,2,3,4,5])))
  putStrLn ("pure (+) <*> [1,2] <*> [10, 20] = " ++ (show(pure (+) <*> [1,2] <*> [10, 20])))
  putStrLn ("pure (+) <*> (Just 4) <*> (Just 6) = " ++ (show (pure (+) <*> (Just 4) <*> (Just 6))))
  putStrLn ("pure (+) <*> Nothing <*> (Just 6) = " ++ (show (pure (+) <*> Nothing <*> (Just 6))))
  putStrLn ("applicativeAdd [1,2] [10,20] == " ++ show(applicativeAdd [1,2] [10,20] ))
  return ()
  
applicativeAdd :: [Int] -> [Int] -> [Int]  
applicativeAdd = liftA2 (+)

hello = ["Hello ", "world ", "how ", "do ", "you ", "do."]

makeString :: Int -> String
makeString n = ["Hello ", "world ", "how ", "do ", "you ", "do."] !! n
mapped = fmap makeString [0..5]

flatmapped = do
  n <- [0..5]
  t <- makeString n
  return t

outerList = [[1,1],[2,3,5],[8,13,21,34]] 
outerListFlat = do 
  innerlist <- outerList
  elt <- innerlist
  return (elt)

a = sqrt 16
maybeSqrt :: Float -> Maybe Float
maybeSqrt x  | x < 0 = Nothing
             | otherwise = Just (sqrt x)

maybeLog :: Float -> Maybe Float
maybeLog x | x <= 0 = Nothing
           | otherwise = Just (log x)

combined :: Float -> Maybe Float
combined x =  maybeLog x >>= maybeSqrt 

combined2 x = do
  x2 <- maybeLog x
  maybeSqrt x2
  
withfmap x = fmap  maybeSqrt (maybeLog x) 

monadDemo1 :: IO()
monadDemo1 = do
  putStrLn ( "mapped: " ++ show mapped)
  putStrLn ( "flatmapped: " ++(show flatmapped))
  putStrLn ( "outerListFlat: " ++ (show outerListFlat))
  putStrLn ( "combined 100 " ++ (show (combined 100)))
  putStrLn ( "combined 0.01 " ++ (show (combined 0.01)))
  putStrLn ( "combined 1 " ++ (show (combined 1)))
  putStrLn ( "combined2 100 " ++ (show (combined 100)))
  putStrLn ( "combined2 0.01 " ++ (show (combined 0.01)))
  putStrLn ( "combined2 1 " ++ (show (combined 1)))
  putStrLn ( "withfmap 0.01 == " ++ (show (withfmap 0.01)))
  putStrLn ( "withfmap 100 == " ++ (show (withfmap 100)))

monadDemo2 :: IO()
monadDemo2 = do
  putStrLn ("maybeSqrt 4.0" ++ show (maybeSqrt 4.0))
  putStrLn ("maybeSqrt (-4.0)" ++ show (maybeSqrt (-4.0)))
  putStrLn ("maybeLog 4.0" ++ show (maybeLog 10.0))
  putStrLn ("maybeLog (-4.0)" ++ show (maybeLog (0.01)))

  