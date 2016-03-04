# Projekt z BO #

Do zaimplementowania wyszukanie najbardziej eficjentnej scieżki dojazdu oraz rozkładu pizzy w zależności od mapy i liczności dostawców.

## Wymagania ##

W implementacji **obowiązkowo** należy użyć algorytmów genetycznych (GA) oraz optymalizacji rojem cząstek (PSO). Należy pamiętać aby umożliwić użytkownikowi możliwie jak największą dowolność parametryzacji działania algorytmów.

Dodatkowo wymagana pomoc (najlepiej dostępna z poziomu aplikacji).

## Podział problemów ##
  1. Algorytm Genetyczny - albertkuzma@gmail.com, pawel.batko@gmail.com, dominik.gawlik@gmail.com
  1. Rój Cząstek (PSO) - Damian Goik, Grzegorz Legień, grzesiek.mrozu@gmail.com
  1. GUI - alejajestemmondry@gmail.com, bigm16@gmail.com
  1. Model (CHUJ) - orzech.orzechowski@gmail.com, lsekalski@gmail.com

## Postanowienia ##
  * Mapa będzie reprezentowana jako graf (nie używamy google maps api)
  * Odległość między punktami liczymy według geometrii euklidesowej, ewentualnie przemnażamy przez współczynnik zatoru
  * Ograniczona liczba dostawców (parametryzowana)
  * Dostawcy mogą mieć różną "pojemność" (ilość pizz, które mogą naraz zabrać)
  * Co 0,5h paczka pizz do rozwiezienia
  * Pizze wrzucamy na kolejkę, przy wysyłce ściągamy pierwszych n pizz, gdzie n to łączna pojemność dostępnych dostawców
  * Dokumentacja w LaTeX

### Struktura pliku ###
Struktura pliku w którym umieszczone są dane na temat miasta
```
wspolrzedna_x,wspolrzedna_y,[nr_wezlow_do_ktorych_dochodzi_sciezka]
```

Jeszcze trzeba wymyslec jak bedziemy oznaczac ktory wezel jest pizzeria (pierwszy, ostatni albo jakis dodatkowy parametr).