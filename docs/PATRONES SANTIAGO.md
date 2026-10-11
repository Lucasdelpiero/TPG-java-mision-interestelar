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






