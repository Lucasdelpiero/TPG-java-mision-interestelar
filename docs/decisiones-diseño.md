# DECISIONES DE DISEÑO
Los siguientes patrones fueron utilizados para la realizacion del proyecto de acuerdo a los requisitos planteados dentro del Trabajo Practico Grupal.

---

# *FACTORY*

Este patron fue utilizado para la simplificacion de creacion de los objetos de los distintos tipos de NAVE y TRIPULANTES, devolviendo una instancia hacia el objeto especifico deseado pasado como argumento.
### Nave:
Desde el programa `main` es posible hacer la creacion de cualquier tipo de nave, ya sea "Exploradora","Carguero" o "Combate" con el metodo:

```
NaveFactory.getNave(NaveFactory.tipoNave.*tipodeseado*)
```

Siendo `*tipodeseado*` un ENUM que contiene los tipos de nave, evitando asi el uso de Strings.

Ejemplo:
```
Nave nave1 = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);

Nave nave2 = NaveFactory.getNave(NaveFactory.tipoNave.CARGUERO);

Nave nave3 = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
```

Cada nave tiene sus respectivos atributos, como combustible y energia distintos.
### Tripulante:
La creacion de un tripulante se basa en 2 patrones: Uno para establecer el origen y otro para establecer el cargo del tripulante.
Para establecer los cargos primero se crea `factoryCargo`:

```
FactoryCargo factoryCargo = new FactoryCargo();
```

Para crear un tripulante se hace de la siguiente manera:

```
Tripulante t1 = new Tripulante(<NombreDelTripulante>,<Cargo>,<EnteroAntiguedad>);
```

Para establecer el origen, se crea primero `factoryOrigen`:

```
FactoryPlanetaOrigen factoryOrigen = new FactoryPlanetaOrigen();
```

Luego se asigna un origen a un tripulante (ya creado anteriormente) mandando por parametro al mismo:

```
PlanetaOrigen p1 = factoryOrigen.factoryPlanetaOrigen(<StrOrigen>,<ObjTripulante>)
```

Ejemplo: Se crea un cargo "Capitan", se crea un tripulante "t1" y a este se le asigna el origen "Terricola".

```
FactoryCargo factoryCargo = new FactoryCargo();
FactoryPlanetaOrigen factoryOrigen = new FactoryPlanetaOrigen();

Cargo c1 = factoryCargo.crearCargo("Capitan");

Tripulante t1 = new Tripulante("Leonel", c1, 7);

PlanetaOrigen p1 = factoryOrigen.factoryPlanetaOrigen("Terricola", t1);

```

Al tener una combinacion entre lugar de Origen y el Cargo, era una buena idea implementar un patron Decorator donde se le puede aplicar dinamicamente estos aspectos a un tripulante en particular. Pero este patron solo se implementara para calcular los **haberes** de los tripulantes


---

# *TEMPLATE-METHOD*

Este patron es utilizado para realizar las misiones propuestas hacia un Asistente de Comandos en especifico. Se utiliza este patron para hacer el 'paso a paso' necesario para el cumplimiento de la mision, siendo estos:
- Preparar(): Se hacen las verificaciones necesarias de recursos de la nave que realiza la mision. La nave asignada tiene que tener los recursos suficientes para realizar el siguiente paso. En caso de no tenerlos, se cancela la mision.
- Ejecutar(): Se hace la ejecucion de la mision. En este mismo, a la nave se restan los recursos que requiere la mision para ser completada.
- Evaluar(): Se hacen los calculos necesarios para hacer comparaciones entre el estado inicial de la nave con el estado final. Tambien se muestra un registro completo acerca de la mision ejecutada, su resultado y los recursos finales.
- Cerrar(): Cierra la mision y termina el algoritmo.

Las siguientes misiones cumplen los mismos pasos escritos arriba:
- M-01: Intercepcion y Asistencia
- M-02: Recoleccion
- M-03: Retorno seguro

Para las 3 misiones, el paso Ejecutar() es distinto para cada uno, entonces se lo declaro como metodo abstracto.

El paso a paso se encontrara en la clase abstracta Mision:
```
public void hacerMision(AsistenteComando AC){
	assert(AC != null):"Error: AC es null";
	
	preparar();
	ejecutar();
	evaluar(); // Abstracto
	cerrar();
}
```


---

# *STATE*

Se optó por usar el patrón State para modelar el motor warp de la nave, evitando estructuras condicionales complejas (if / switch) para evaluar transiciones de estado. A través de la interfaz EstadoMotor y sus implementaciones concretas (DisponibleState, PreparandoSaltoState, SaltoWarpState y EnfriamientoState), cada fase encapsula su propio comportamiento, garantizando el flujo secuencial del ciclo de salto y protegiendo la integridad del sistema al disparar una MotorTransicionException ante cualquier acción no permitida.

El desarrollo del subsistema consistió en la implementación de la clase MotorWarp y del ciclo de vida de los estados del motor de la nave. Para ello, se aplicó el patrón de diseño State, con el objetivo de modelar el comportamiento dinámico del motor y evitar el uso de múltiples estructuras condicionales para evaluar cada transición de estado.

La clase MotorWarp actúa como el contexto que delega las operaciones de transición a su estado actual mediante una referencia polimórfica a la interfaz EstadoMotor. Esta clase proporciona operaciones para gestionar el ciclo completo de propulsión (preparar salto, saltar, enfriar y finalizar enfriamiento), garantizando una secuencia estricta y segura. Ante cualquier intento de transición inválida, se dispara una excepción propia (MotorTransicionException), protegiendo la integridad operativa de la nave.

Interfaz EstadoMotor: define los métodos comunes de transición y consulta que deben implementar los distintos estados del motor (DisponibleState, PreparandoSaltoState, SaltoWarpState y EnfriamientoState). Cada implementación concreta encapsula la lógica correspondiente a su fase y define hacia qué estado es válido avanzar.


---

# *DECORATOR*

Se implementó el patrón Decorator para asociar dinámicamente a cada tripulante un planeta de origen, modificando su comportamiento según el origen asignado. En particular, este patrón permite personalizar el sueldo del tripulante y obtener información detallada sobre sus características sin modificar directamente la clase base.

---

# *CONCURRENCIA*
Se usa expresar paralelismo entre tareas mediante threads y monitores. 
Se usa para resolver el problema productor-consumidor, en el que hay un recurso compartido (la plataforma con baterias), en la cual el productor pone baterias cargadas y el consumidor las retira para utilizarlas, pero como solo hay espacio para una cantidad limitada de baterías en la plataforma el productor tiene que esperar a depositar la bateria hasta que haya espacio y el consumidor debe esperara a que haya al menos una bateria. Esto de esperar y luego volver a chequear si puede usar el recurso compartido se hace mediante el wait() y el notifyAll() que proveen los hilos.

---
# *PERSISTENCIA*
Se utiliza el patrón DAO y el patrón DTO
### Patrón DAO: 
Nos funciona de nexo entre los datos del programa y la base de datos a la que queremos almacenarlos.
Para guardar/cargar los usuarios se creo una clase UsuarioDAOSQLite para almacenar los datos en SQLite, esta clase tiene todas las funciones necesarias para comunicarse con la base de datos para consultar datos o existencia de tal usuario.

### Patrón DTO:
Es un objeto que usamos para decidir como van a viajar los datos entre las distintas capas del programa con solo los datos que nos interesan tratar.
Esto soluciona un problema de traerse datos de varias tablas en un solo llamado y el que estos datos se puedan juntar en un solo objeto del cual luego haremos uso.
Si no usáramos este patrón habría que recuperar distintos objetos desde la base de datos y hacer varios llamados a esta.

---
# *ENCUENTROS CON ELEM DE UNIVERSO*
La nave puede encontrarse con varios elementos del terreno, para cada uno deberá encararlo de una manera. La nave tiene un método de choque
````
  @Override
    public void choque(ElementoDelUniverso elemento){
        if (elemento instanceof Nave nave){
            this.choqueNave(nave);
        } else if (elemento instanceof Asteroide asteroide){
            this.chocaAsteroide(asteroide);
        } else if (elemento instanceof EstacionDeCarga estacion){
            this.chocaEstacionDeCarga(estacion);
        }
    }
````
### Naves:
Para cada encuentro con naves hay 2 cosas que intervienen en el resultado del encuentro:
- Tipo de nuestra nave
- Tipo de nave enemiga
Como tenemos 3 tipos de naves, si hicieramos un if por cada convinación tendríamos 9 condiciones que evaluar, y en caso de que se añadiera otra nave y tuvieramos 4, habría 16 combinaciones.
Para evitar este tipo de inconvenientes se usa el patron Double Dispatch:
### Double Dispatch encuentros naves:
Con este patrón la interacción entre objetos se da sin que uno necesite saber la clase a la que pertenece el otro objeto pero el propio objeto si conoce el propio, de manera que se puede invocar un método en el otro objeto que involucre a la clase desde la que se llama.
Ej: Mi nave exploradora choca contra una carguera enemiga
Mi nave con el método
````
public void choqueNave(Nave nave) {
        nave.chocaExploradora(this);
    }
````

Luego en la carguera enemiga:
````
public void chocaCombate(Nave nave) {
		nave.destruirNave();
    }
    
````
Así la nave que pierde el combate recibe daño y queda inutilizada.
````
public void destruirNave(){
        tripulacion.matarTripulacion();
        recursos.setDesgaste(100);
    }
````
Cuando la nave es destruida se mata al total de la tripulación y se pone el desgaste al máximo posible.


### Asteroides
Usamos simplemente un IF consultado por los recursos de la nave.
Comprobamos si el objeto chocado es un asteroide, si lo es se descuenta energía y si no tiene energía suficiente entonces la nave es destruida.

### Estaciones de carga
Tan solo cargamos los recursos a la nave.

Para los asteroides y estación de carga no hace ningún patrón complejo porque solo es evaluar una vez a que clase pertenece y luego resolver en la misma clase de nave utilizando los atributos que tienen el Asteroide y la Estación de carga.

---

# *MISIONES*

Para realizar las misiones, estas deben seguir siempre un mismo orden, con 4 tareas a realizar:
-preparar()
-ejecutar()
-evaluar()
-cerrar()

Para que se ejecuten siempre en el mismo orden usamos el patrón template method:
### Template method
Creamos una clase abstracta llamada misión a la cual le declaramos un método:

````
  public final void hacerMision(AsistenteComando AC){
        assert (AC != null): "Error: AC es null" ;
        this.AC = AC;
   
        preparar();
        ejecutar();
        evaluar();
        cerrar();
    }
````

El "final" del método es importante porque así nos evitamos que en las clases concretas se sobre escriba pudiendo así alterar el orden.

##### Luego en la clase abstracta vamos a declarar los 4 métodos haciendo una distinción:
-Los comunes entre las clases de misiones especificas:
	Estas se implementan en la clase abstracta y se usan las clases concretas sin cambios ya que en todas hacen lo mismo.

-Las especificas a cada misión:
	Estas se declaran como métodos abstractos para que sean implementados por cada clase Mision especifica.