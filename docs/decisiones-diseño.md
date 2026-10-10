
# Martin

1. Se opto por usar el patron state, para evitar el uso de multiples if para modelar cada posibles combinacion de transicion de estados.
2. Se opto por usar Integer en bitacora en lugar de int, para poder modelar el caso de registrar un evento que no esta relacionado a una mision directamente mandando null como el idMision, algo que usando el tipo de dato primitivo int no es posible.

// PARA LUCAS: Explicar porque es mejor usar excepciones en el motor warp y no retornar boolean, no entendi y no estoy de acuerdo con el enfoque

# Lucas
Misiones:
-El template method lo que hace es que se ejecuten ciertas acciones siempre en un mismo orden y se pueden ejecutar tambien acciones opcionales.
-Para que no se ejecute una mision si no estan dadas las condiciones lo que se hace es:
    -preparar() setea un booleano "puedeIniciarMision"
    -ejecutar() dentro de la funcion checkea por "puedeIniciarMision" y segun el valor va a gastar o no recursos

(modificacion)Motor warp: 
-Se cambio el enfoque de devolver booleanos por excepciones para saber si se puede hacer transicion por 2 razones
    - Separar la seccion de "codigo limpio" con la seccion que de recuperacion
    - Que si se quiere se pueda recuperar de la excepcion de manera prolija o mostrar el error que devuelve

(modificacion)Nave Factory:
-Se agregaron enums para el tipo de nave asi es mas facil crear una nave
-Se modifico cada nave especifica para que contenga la cantidad de recursos iniciales en vez de pasarla por argumentos

