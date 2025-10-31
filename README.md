🧭 GitHubClient — Visualizador de usuarios y repositorios de GitHub (JavaFX + Maven)

Aplicación de escritorio desarrollada en Java 17 / JavaFX 23 que consume la API pública de GitHub
para mostrar información de usuarios, repositorios y estadísticas de lenguajes usados.

🚀 Características principales

🔍 Búsqueda de usuarios de GitHub por nombre de usuario (login).

👤 Muestra información del perfil:

Nombre real, biografía, seguidores, seguidos y avatar.

📦 Tabla con todos los repositorios públicos del usuario:

Nombre, lenguaje principal, número de estrellas ⭐ y fecha de última actualización.

📊 Gráfico circular (PieChart) con los lenguajes más usados.

⚙️ Implementa consumo HTTP asíncrono mediante HttpClient + Jackson para parseo JSON.

🎨 Interfaz JavaFX (FXML) moderna, adaptable y separada en capas (UI, Service, Domain, Util).

🧩 Tecnologías utilizadas
Tecnología	Uso principal
☕ Java 17	Lenguaje base del proyecto
🎨 JavaFX 23	Interfaz gráfica (FXML, TableView, PieChart)
⚙️ Maven 3.9+	Construcción y dependencias
🌐 Java HTTP Client (java.net.http)	Peticiones HTTP a la API de GitHub
🧠 Jackson 2.18	Mapeo JSON → objetos Java (User, Repo)
🧰 JUnit 5	Pruebas unitarias
🧑‍💻 NetBeans 24	IDE de desarrollo principal
🏗️ Estructura del proyecto
GitHubClient/
├── pom.xml
├── module-info.java
├── src/
│   ├── main/
│   │   ├── java/com/una/githubclient/
│   │   │   ├── ui/         → Controladores JavaFX (App.java, PrimaryController.java)
│   │   │   ├── service/    → Lógica de conexión a la API (GitHubApi, HttpClientFactory)
│   │   │   ├── domain/     → Modelos de datos (User, Repo, LanguageStats)
│   │   │   └── util/       → Utilidades (Formatter, RateLimitInfo)
│   │   └── resources/com/una/githubclient/ui/
│   │       └── primary.fxml → Interfaz principal
│   └── test/ → Pruebas (JUnit)
└── README.md

⚙️ Configuración y ejecución
🔹 Requisitos previos

JDK 17 o superior

Maven 3.9+

Conexión a internet (para consumir la API de GitHub)

🔹 Ejecutar desde NetBeans

Abre el proyecto en NetBeans.

Limpia y compila: Run → Clean and Build.

Ejecuta: Run → Run Project.

🔹 Ejecutar desde terminal
cd GitHubClient
mvn clean javafx:run

🧠 Cómo usarlo

Ingresa un nombre de usuario de GitHub en el campo superior (por ejemplo: octocat o tu usuario propio).

Presiona el botón Buscar.

La aplicación mostrará:

Datos del perfil (nombre, bio, seguidores).

Repositorios públicos en la tabla central.

Gráfico circular de lenguajes usados.

🔑 (Opcional) Aumentar límite de peticiones de la API

Por defecto, GitHub limita a 60 peticiones por hora si no estás autenticado.
Para aumentarlo a 5,000/hora:

Genera un token personal desde
👉 GitHub → Settings → Developer Settings → Personal Access Tokens → Fine-grained tokens

En Windows:

setx GITHUB_TOKEN "tu_token_aqui"


Cierra y vuelve a abrir NetBeans o la consola.

El token se usará automáticamente (el programa lo detecta con System.getenv("GITHUB_TOKEN")).

🖼️ Capturas de pantalla

(Agrega aquí tus imágenes reales del programa en ejecución)

Perfil cargado	Repositorios y gráfico

	
🧩 Ejemplo de uso
Usuario de prueba	Resultado
octocat	Perfil oficial de GitHub con ~8 repos
torvalds	Muestra repos de Linus Torvalds (Linux Kernel, Subsurface, etc.)
wagne03	Tu perfil y repos personales 😎
🧭 Flujo del proyecto
flowchart TD
    A[PrimaryController] -->|Captura usuario| B[GitHubApi]
    B -->|GET /users/{user}| C[User JSON → User.java]
    B -->|GET /users/{user}/repos| D[List JSON → Repo.java]
    C -->|Actualiza labels| E[UI Panel Izquierdo]
    D -->|Llena tabla| F[TableView]
    D -->|Agrupa por lenguaje| G[LanguageStats → PieChart]

🤝 Autor

Wagner @wagne03

Proyecto académico y demostrativo — Universidad Nacional (UNA)

“Procesos con inteligencia, resultados con impacto.”
