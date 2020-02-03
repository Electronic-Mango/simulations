# PSM - zadania programistyczne
Projekt zawierający trzy zadania programistyczne stworzone na potrzebę przedmiotu PSM.

## Autor
**Paweł Rutkowski** - numer indeksu **S18277**

## Wstęp
Projekt został stworzony w języku **Java** wersja 11.

### Środowisko
Minimalne środowisko **JRE** jest dołączone do projektu, znajduje się w katalogu ```java-runtime```.
Zostanie ono wykorzystane, gdy Java w wersji co najmniej 11 nie zostanie odnaleziona na komputerze.

Jeżeli katalog ```java-runtime``` nie zostanie odnaleziony, a Java 11 nie jest zainstalowana na komputerze użytkownik zostanie poproszony o pobranie **Java Runtime Environment** w wersji co najmniej **11**.

### Uruchamianie
Na platformie Windows programy mogą zostać uruchomione korzystając z plików wykonywalnych ```.exe```.

Na platformach innych niż Windows programy mogą zostać uruchomione z plików ```.jar``` znajdujących się w katalogu ```out```.
```shell
java -jar 'Zadanie 0 - Rzut Ukośny.jar'
java -jar 'Zadanie 1 - Wahadło Podwójne.jar'
java -jar 'Zadanie 2 - Gra w Życie.jar'
```

### Kod źródłowy
Kod źródłowy wszystkich programów znajduje się w katalogu ```src```.

Kod dotyczący poszczególnych zadań znajduje się w pakietach:

| Zadanie                                | Pakiet      | Klasa Główna                    |
| -------------------------------------- | :---------: | :-----------------------------: |
| Zadanie rozgrzewkowe - **Rzut Ukośny** | ```task0``` | ```task0.ProjectileThrowTask``` |
| Zadanie 1 - **Wahadło podwójne**       | ```task1``` | ```task1.DoublePendulumTask```  |
| Zadanie 2 - **Gra w Życie**            | ```task1``` | ```task2.GameOfLifeTask```      |


Część kodu jest w ogólnych pakietach, co pozwala na ich wielokrotne wykorzystanie we wszystkich zadaniach.

## Zadanie rozgrzewkowe - Rzut Ukośny
Program pozwala na symulowania rzutów ukośnych.

Jednocześnie można symulować wiele rzutów.
Każdy nowy rzut jest rozpoczynany przyciskiem ```START``` niezależnie od tego, czy poprzedni rzut już został zakończony.

Wszystkie działające symulacje i ślady po zakończonych symulacjach mogą zostać wyczyszczone przyciskiem ```CZYŚĆ```

## Zadanie 1 - Wahadło Podwójne
Program pozwala na symulację wahadła podwójnego.

Wahadło zostawia ślad, który w każdym momencie można wyczyścić przy pomocy przycisku ```CZYŚĆ```.

W trakcie symulacji możemy zmieniać parametry wahadła:
* długości obu ramion
* wagi obu ciężarków

Zmiana kąta obu wahadeł możliwa jest jedynie gdy symulacja jest zatrzymana.

Możliwa jest również dynamiczna zmiana oporu ośrodka, w którym porusza się wahadło.

## Zadanie 2 - Gra w Życie
Program pozwala na uruchomienie Gry w Życie.

W trakcie działania programu można zmieniać reguły gry, również bez konieczności zatrzymywania danej rozgrywki.

W trakcie gry można dynamicznie zmieniać stan poszczególnych komórek przy użyciu myszki i jej lewego przycisku.

Domyślnie początkowy stan komórej jest losowy, lecz można wyczyścić aktualny stan gry i stworzyć nowy przy użyciu myszki.

Przy pomocy przycisku ```LOSUJ``` można stworzyć nowy, losowy układ komórek.

Przy pomocy przycisku ```ROZMIAR``` można zmienić liczbę kolumn i wierszy dostępnych komórek.
