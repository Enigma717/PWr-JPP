-- Marek Traczyński (261748)
-- Języki i Paradygmaty Programowania
-- Lista 4 Zadanie 2


import Control.Monad.Writer


---------------
-- Factorial --
---------------

myFactorial :: (Integral a) => a -> a
myFactorial n = product [1..n]


---------
-- GCD --
---------

myGCD :: (Integral a) => a -> a -> a
myGCD a b = if b == 0 then a
            else myGCD b (a `mod` b)


--------------------------
-- Diophantine equation --
--------------------------

dioGCD :: (Show a, Integral a) => a -> a -> Writer String (a, a, a)
dioGCD a b = do 
               if b == 0 then return (a, 1, 0)
               else do
                      (g, x, y) <- dioGCD b (a `mod` b)
                      let resx = y
                      let resy = x - (a `div` b) * y
                      tell $ "GCD(" ++ show a ++ ", " ++ show b ++ ") = " ++ show g ++ ": "
                      tell $ "(x = " ++ show resx ++ ", y = " ++ show resy ++ "); "
                      return (g, resx, resy)

                    
myWriterDIO :: (Show a, Integral a) => a -> a -> a -> Writer String (Maybe (a, a))
myWriterDIO 0 0 c = return Nothing
myWriterDIO a b c = do
                      (g, x, y) <- dioGCD a b
                      if c `mod` g == 0 then do
                            tell "Znaleziono rozwiazanie"
                            return (Just (x * (c `div` g), y * (c `div` g)))
                      else do
                            tell "Nie znaleziono rozwiazania"
                            return Nothing


getSolutionDIO :: (Show a, Integral a) => a -> a -> a -> Maybe (a, a)
getSolutionDIO a b c = fst $ runWriter $ (myWriterDIO a b c)                        

