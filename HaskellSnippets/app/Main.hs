module Main where



main :: IO ()
main = do
  name <- readName
  printGreeting name
  
readName :: IO(String)
readName = do  
  putStrLn "name: "
  name <- getLine
  return name
   
printGreeting :: String -> IO()
printGreeting name = putStrLn greeting 
      where greeting = "Hello " ++ name

