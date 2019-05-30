package tools;
public enum Text {
    TDA("Un TDA o tipo de dato abstrato es un tipo de dato (clase) definida por el programador, que consta de datos (los cuales pueden ser primitivos, definidos por el lenguaje de programación u otro TDA) y operaciones (lo cuales son los metodos y funciones que controlan el comportamiento del TDA) que se pueden realizar sobre ellos."),
    TDA1("Los TDA también constan de dos especificaciones cuyas son:"),
    TDA2("1. La descripción matemática del conjunto de datos, que hace referencia a los atributos de un dato (clase) cuyos podemos evidenciar en la línea número 2 de la clase Punto.\n2. La descripción de las operaciones definidas en ciertos elementos de ese conjunto de datos, que hacen referancia a los metodos y funciones que afectan el comportamiento de los atributos definidos en un dato (clase) como los podemos evidenciar entre las líneas número 7 y 22 de la clase Punto."),
    TDA3("Según lo leído podríamos decir que un TDA puede definirse mediante la siguiente ecuación:"),
    FORMULATDA("TDA = REPRESENTACIÓN(datos) + OPERACIONES(funciones y métodos)"),
    SIMULADORTDA("Instancia el objeto punto asignando sus respectivos parametros X,Y"),
    SIMULADORTDA1("Muestra las coordenadas X,Y dadas"),
    SIMULADORTDA2("Ingrese las coordenadas (x,y)"),
    ABOUT("Descripción explicita de cada Estructura de Datos desde lo conceptual, visión y simulación de las mismas con ejemplos cotidianos resueltos con las estructuras de datos.\n\n\nDesarrollado por\nSergio Andrés Majé Franco\n\n\nVersion 1.0"),
    LINK("https://github.com/Reymon99/SimuladorTDA"),
    MODULARIDAD("La modularidad es la estructura de un TDA, con la cuál nos permite darle un comportamiento específico y deseado al objeto que deseamos trabajar."),
    MODULARIDAD1("La modularidad se enfoca principalmente a la agrupación de métodos que permiten realizar trabajos complejos, mediante la filosofía “divide y vencerás”; también nos permite una mejor comprensión de lo que estamos codificando, de las tareas a realizar y la reutilización de los métodos(módulos) ya utilizados.\n\nCon esto podemos concluir que la modularidad se basa en las acciones que puede realizar un objeto y cuantas veces quiera tomarlas."),
    RECURSIVIDAD("La recursividad es la simplificación de tareas mediante una función que se llama así misma, buscando un resultado explicito por medio de un resultado implícito, a través de un caso base que aproxima esta respuesta.\n\nLa recursividad se estructura por dos casos, domino y base, yendo desde una respuesta implícita, que nos da una solución compuesta, a una repuesta explicita, siendo ya la solución deseada.\n\nLa recursividad tiene dos tipos de implementación, las cuales son:\n•\tLa Recursividad Directa, determinada por una función que se llama a sí misma.\n•\tLa Recursividad Indirecta, determinada por una función que se llama así misma mediante otra función."),
    RECURSIVIDAD1("Directa\tf(x) \u279c f(x) \u279c f(x) \u279c x’\nIndirecta\tf(x) \u279c g(x) \u279c f(x) \u279c x’"),
    ARREGLOS("Como sabemos un arreglo o array es una secuencia de datos dados por ítems consecutivos desde el cero hasta la longitud del arreglo."),
    ARREGLOS1("En estructura de datos son utilizadas para dar forma a estructuras estáticas (determinado por un tamaño limite) como veremos mas adelante en las estructuras de datos lineales (pilas, colas y listas) ayudándonos mediante los índices a crear algoritmos eficientes con una determinada estructura de datos."),
    ARREGLOS2("Además de esto los arreglos pueden ser utilizados en múltiples dimensiones, siendo mayormente utilizadas en la segunda dimensión (arreglos bidimensionales o matrices) que nos permite simular una especie de tabla en la que podemos trabajar con múltiples datos anidados al mismo tiempo, permitiendo resolver distintos problemas determinados.");
    private String n;
    private Text(String n){
        this.n=n;
    }
    /**
     * Contiene todos los textos utilizados en títulos, enunciado, contenidos, y notificaciones de proyecto
     * @return String
     * @author Sergio Majé
     */
    @Override
    public String toString() {
        return n;
    }
}