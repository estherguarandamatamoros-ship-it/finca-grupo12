# ApiColmena - Grupo 12

## 1. Descripción del proyecto

ApiColmena es un sistema desarrollado para apoyar el monitoreo de colmenas mediante la generación y registro de alertas. El proyecto forma parte del Taller Grupal N.º 4 de la asignatura Programación 3.

El sistema permite evaluar las condiciones de una colmena y detectar tres situaciones:

- **VARROA_ALTA:** cuando el porcentaje de varroa supera el umbral configurado.
- **SIN_INSPECCION:** cuando han transcurrido más días que los permitidos sin realizar una inspección.
- **CAIDA_RENDIMIENTO:** cuando la disminución del rendimiento supera el porcentaje establecido.

Para organizar el código se aplicaron principios SOLID y los patrones de diseño Factory y Observer.

## 2. Datos del grupo

| Dato | Información |
|---|---|
| Grupo | 12 |
| Proyecto | ApiColmena |
| Asignatura | Programación 3 |
| Docente | Ing. Hamilton Francisco Villamar Barros, MSc. |
| Universidad | Universidad Agraria del Ecuador |
| Período académico | 2026-2027 CII |
| Paralelo | 4SC |

## 3. Integrantes y roles

| Integrante | Rol |
|---|---|
| Esther Guaranda Matamoros | Líder de integración y responsable de documentación |
| Ángel Ceme | Responsable de pruebas |
| Bryan Solis | Verificador |
| Katherine Montaño | Verificadora |
| Elkin Mindiola | Verificador |

## 4. Tecnologías utilizadas

| Herramienta | Versión o tecnología |
|---|---|
| Java | JDK 21 |
| Maven | 3.10.0 |
| JUnit Jupiter | 5.10.2 |
| IntelliJ IDEA | Entorno de desarrollo |
| Git | Control de versiones |
| GitHub | Repositorio y colaboración |

## 5. Organización del proyecto

El código se organiza en los siguientes paquetes:

| Paquete | Responsabilidad |
|---|---|
| `dominio` | Contiene los modelos Colmena, Floracion e InsumoApicola, además de la interfaz Trazable. |
| `fabrica` | Contiene las fábricas, los tipos de productos y las estructuras de datos utilizadas para su creación. |
| `reglas` | Contiene las reglas de evaluación y la configuración de los umbrales de alertas. |
| `alertas` | Contiene las alertas, el motor de evaluación, los observadores y la bitácora. |

## 6. Diagnóstico y mejoras realizadas

Durante la revisión del proyecto se identificaron oportunidades para mejorar la organización y el mantenimiento del código.

| Aspecto revisado | Mejora realizada |
|---|---|
| Creación de objetos | Se centralizó la creación de productos mediante `FabricaProductos`. |
| Validación de datos | Se incorporaron estructuras de datos específicas para cada tipo de producto. |
| Umbrales de alertas | Se trasladaron los valores a `ConfiguracionAlertas`. |
| Generación de alertas | Se organizaron las reglas en clases independientes. |
| Notificación de alertas | Se incorporó el patrón Observer para comunicar las alertas a distintos observadores. |
| Registro de alertas | Se implementaron observadores para consola, archivo y bitácora en memoria. |

## 7. Principios SOLID y patrones aplicados

| Principio o patrón | Aplicación en ApiColmena |
|---|---|
| SRP - Responsabilidad única | Las reglas, el motor, las fábricas y los observadores tienen responsabilidades separadas. |
| OCP - Abierto/cerrado | Se pueden incorporar nuevas reglas y observadores sin concentrar toda la lógica en una sola clase. |
| LSP - Sustitución de Liskov | Las implementaciones de `ObservadorAlerta` pueden utilizarse mediante su interfaz común. |
| ISP - Segregación de interfaces | Se utilizan interfaces específicas para las responsabilidades de trazabilidad y observación. |
| DIP - Inversión de dependencias | El motor trabaja con abstracciones como `ReglaAlerta` y `ObservadorAlerta`. |
| Factory | `FabricaProductos` centraliza la creación de los productos del dominio. |
| Observer | `MotorAlertas` notifica a los observadores cuando se genera una alerta. |

## 8. Configuración de las alertas

Los umbrales se encuentran centralizados en `ConfiguracionAlertas`, lo que facilita su modificación sin cambiar directamente las reglas.

| Alerta | Umbral predeterminado | Condición para generar alerta |
|---|---:|---|
| VARROA_ALTA | 3 % | Varroa superior al 3 %. |
| SIN_INSPECCION | 30 días | Más de 30 días sin inspección. |
| CAIDA_RENDIMIENTO | 20 % | Caída superior al 20 %. |

Se utiliza una comparación estricta para los umbrales porcentuales. Por ello, alcanzar exactamente el límite no genera una alerta; esta se produce cuando el valor lo supera.

## 9. Observadores implementados

El sistema utiliza tres mecanismos de notificación:

- `ObservadorConsola`: presenta la información de la alerta en consola.
- `ObservadorArchivo`: registra las alertas en un archivo de texto.
- `BitacoraAlertas`: conserva las alertas en una lista en memoria.

Los observadores se crean mediante `FabricaObservadores` y se suscriben al `MotorAlertas`.

## 10. Pruebas y ejecución

El proyecto utiliza JUnit 5 para verificar la creación de productos, el comportamiento de las reglas y el funcionamiento del motor y los observadores.

Para ejecutar las pruebas y verificar el proyecto se utiliza:

```bash
mvn clean verify
```

La ejecución final del proyecto debe mostrar `BUILD SUCCESS`.

## 11. Uso de inteligencia artificial

Se utilizó ChatGPT como herramienta de apoyo durante el desarrollo para revisar la organización del código, analizar errores de compilación, proponer mejoras y orientar la elaboración de pruebas y documentación.

Las propuestas fueron revisadas y adaptadas por los integrantes del grupo. El equipo realizó las modificaciones y verificó el funcionamiento mediante la ejecución de las pruebas y la compilación del proyecto.

## 12. Conclusión

El desarrollo de ApiColmena permitió aplicar principios SOLID y los patrones Factory y Observer en un proyecto de monitoreo de colmenas. La separación de responsabilidades facilita el mantenimiento y la ampliación del sistema. Además, la configuración externa de los umbrales permite ajustar las reglas sin modificar su lógica interna.

La ejecución satisfactoria de las pruebas mediante Maven permitió comprobar el funcionamiento de los componentes implementados.