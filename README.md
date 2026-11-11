# Universidades API (Arquitectura Hexagonal)
Backend REST para la app Kodular. Spring Boot 3 + PostgreSQL.
- `domain`: modelos y excepciones (sin dependencias de frameworks)
- `application`: puertos (in/out) y casos de uso (services)
- `infrastructure`: adaptadores (REST, JPA, email, seguridad) y configuración

Swagger: `/swagger-ui.html`

Usuario inicial: `admin@demo.com` / `admin123`
