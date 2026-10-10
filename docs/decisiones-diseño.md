
# Martin

1. Se opto por usar el patron state, para evitar el uso de multiples if para modelar cada posibles combinacion de transicion de estados.
2. Se opto por usar Integer en bitacora en lugar de int, para poder modelar el caso de registrar un evento que no esta relacionado a una mision directamente mandando null como el idMision, algo que usando el tipo de dato primitivo int no es posible.

// PARA LUCAS: Explicar porque es mejor usar excepciones en el motor warp y no retornar boolean, no entendi y no estoy de acuerdo con el enfoque