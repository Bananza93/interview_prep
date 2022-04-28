package lesson_1.task_2;

/**
 * Ошибки:
 * 1. Отсутствие реализации класса Engine;
 * 2. В классе Car и его наследниках отсутствует конструктор, а все переменные д.б. определены при создании объекта (иначе это не машина);
 * 3. В классе Lorry д.б. extends Car implements Moveable, Stopable;
 * 4. В классе Lorry не реализован метод open().
 *
 * Придирки:
 * 1. Модификатор переменной engine класса Car, по хорошему, д.б. private;
 * 2. Модификаторы методов start() и open() класса Car можно сделать public (интерфейс взаимодействия с автомобилем).
 *
 * Оптимизация:
 * 1. По хорошему, объединить интерфейсы Moveable и Stopable в один, т.к. один без другого особого смысла не имеет;
 * 2. Реализовать получившийся при объединении интерфейс в классе Car, т.к. это общее поведение для всех машин;
 * 3. Реализовать базовое повдеение методов start(), open(), move() и stop() в классе Car. При необходимости, переопределять методы в классах-наследниках.
 */
public class Main {

    public static void main(String[] args) {

    }
}
